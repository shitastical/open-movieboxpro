#!/usr/bin/env bash
# Rebuild the decoded MovieBoxPro project into a signed, installable APK.
#
#   ./build.sh            build + zipalign + sign
#   ./build.sh --install  also install on the connected device via adb
#
# Tool overrides (env): APKTOOL_JAR, ZIPALIGN, APKSIGNER, KS, KS_PASS, KEY_ALIAS
set -euo pipefail
cd "$(dirname "$0")"

APKTOOL_JAR="${APKTOOL_JAR:-tools/apktool.jar}"
PROJECT="${PROJECT:-apktool_out}"
BUILD_DIR="${BUILD_DIR:-build}"
OUT_UNSIGNED="$BUILD_DIR/rebuilt-unsigned.apk"
OUT_ALIGNED="$BUILD_DIR/rebuilt-aligned.apk"
OUT_SIGNED="${OUT_SIGNED:-MovieBoxPro-13.4-modded.apk}"
KS="${KS:-tools/debug.keystore}"
KS_PASS="${KS_PASS:-android}"
KEY_ALIAS="${KEY_ALIAS:-androiddebugkey}"

find_tool() {
  local name="$1" c root
  if command -v "$name" >/dev/null 2>&1; then command -v "$name"; return 0; fi
  for root in "${ANDROID_HOME:-}" "${ANDROID_SDK_ROOT:-}" "$HOME/Android/Sdk" /usr/lib/android-sdk; do
    [ -n "$root" ] || continue
    for c in "$root"/build-tools/*/"$name"; do
      [ -x "$c" ] && { echo "$c"; return 0; }
    done
  done
  [ -x "tools/android-build-tools/$name" ] && { echo "tools/android-build-tools/$name"; return 0; }
  return 1
}

command -v java >/dev/null || { echo "error: java not found"; exit 1; }
[ -f "$APKTOOL_JAR" ] || { echo "error: $APKTOOL_JAR not found (download apktool_3.0.3.jar)"; exit 1; }
mkdir -p "$BUILD_DIR"

ZIPALIGN="${ZIPALIGN:-$(find_tool zipalign || true)}"
APKSIGNER="${APKSIGNER:-$(find_tool apksigner || true)}"
[ -n "$ZIPALIGN" ] && [ -x "$ZIPALIGN" ] || { echo "error: zipalign not found; set ZIPALIGN=/path/to/zipalign"; exit 1; }
[ -n "$APKSIGNER" ] && [ -x "$APKSIGNER" ] || { echo "error: apksigner not found; set APKSIGNER=/path/to/apksigner"; exit 1; }

# apktool 3.x bundles a patched aapt2 that accepts '$' resource names.
echo "==> building resources + smali"
java -Xmx4g -jar "$APKTOOL_JAR" b -f "$PROJECT" -o "$OUT_UNSIGNED"

echo "==> aligning"
"$ZIPALIGN" -p -f 4 "$OUT_UNSIGNED" "$OUT_ALIGNED"

if [ ! -f "$KS" ]; then
  echo "==> creating debug keystore"
  keytool -genkeypair -keystore "$KS" -storepass "$KS_PASS" -keypass "$KS_PASS" \
    -alias "$KEY_ALIAS" -dname "CN=Android Debug,O=Android,C=US" \
    -keyalg RSA -keysize 2048 -validity 10000 >/dev/null 2>&1
fi

echo "==> signing"
"$APKSIGNER" sign --ks "$KS" --ks-pass "pass:$KS_PASS" --key-pass "pass:$KS_PASS" \
  --out "$OUT_SIGNED" "$OUT_ALIGNED"
"$APKSIGNER" verify --verbose "$OUT_SIGNED" | head -8
echo "==> built $OUT_SIGNED"

if [ "${1:-}" = "--install" ]; then
  command -v adb >/dev/null || { echo "error: adb not found"; exit 1; }
  adb install -r "$OUT_SIGNED"
fi

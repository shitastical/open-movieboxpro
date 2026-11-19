# MovieBoxPro 13.4 (126) — decompiled, rebuildable project

Source APK: `MovieBoxProtv_2026-10-29-19-47_13.4_126_movie_tvRelease.apk`
Package: `com.movieboxpro.androidtv` · versionName `13.4` · versionCode `126`
minSdk 21 · targetSdk 33 · compileSdk 35 · ABIs: `arm64-v8a`, `armeabi-v7a`

## What you have

| Path | Contents | Rebuildable? |
|---|---|---|
| `apktool_out/` | **Exact** decoded app: `smali/` + `smali_classes2..6/` (30,955 classes), `res/` (values/layouts/drawables), `apktool.yml`, `AndroidManifest.xml`, `assets/`, `lib/`, `META-INF/`, `original/` | **Yes** — this is what `build.sh` compiles |
| `jadx_out/` | Readable Java sources (13,593 files; 979 in `com.movieboxpro.android`) for understanding/editing logic | No — reference only |
| `build.sh` | Build → zipalign → sign → verify (optionally install) | — |
| `tools/`, `*.apk` | Local toolchain and build outputs — **not committed** (see below) | — |

This repo contains source only: `tools/` (third-party binaries) and all `*.apk`
artifacts are intentionally untracked, as is generated `apktool_out/build/`.
Fetch the toolchain with:

```bash
mkdir -p tools
curl -L -o tools/apktool.jar \
  https://github.com/iBotPeaches/Apktool/releases/download/v3.0.3/apktool_3.0.3.jar
curl -L -o tools/jadx.zip \
  https://github.com/skylot/jadx/releases/download/v1.5.7/jadx-1.5.7.zip
unzip -q tools/jadx.zip -d tools
```

`zipalign` and `apksigner` ship with Android SDK build-tools; `build.sh` finds
them via `PATH`, `ANDROID_HOME`/`ANDROID_SDK_ROOT`, or the `ZIPALIGN` and
`APKSIGNER` environment variables.

There is no LLVM step for app code: Android app logic is DEX bytecode, not LLVM IR.
LLVM/Clang only produced the native `.so` libraries (verified: `libapisecrets.so`
`.comment` = `clang version 17.0.2`, `Linker: LLD 17.0.2`, NDK r26b). Those are
prebuilt third-party/media libraries (VLC, FFmpeg, ijkplayer) plus two small app JNI
libs, and are copied byte-for-byte, not recompiled. Recovering C/C++ source for them
is a separate native-RE task (Ghidra/IDA), and even then it is pseudocode, not
original source.

## Build

```bash
./build.sh              # rebuild, align, sign -> MovieBoxPro-13.4-modded.apk
./build.sh --install    # same, then adb install -r
```

The build uses apktool 3.0.3 and apktool's patched aapt2. Do not rebuild this
project with apktool 2.x — stock `aapt`/`aapt2` rejects the 40 resource files
whose names start with `$` (Material Components AVDs), and the build fails.

## Editing

**Resources / manifest / assets** — edit directly:
- Text: `apktool_out/res/values/strings.xml`, `colors.xml`, `styles.xml`, layouts in `res/layout*/`
- Manifest: `apktool_out/AndroidManifest.xml`
- Assets: `apktool_out/assets/`

**Code** — edit the smali that `jadx_out` shows you in Java. Find the class, then
open the matching smali file:

```bash
# e.g. locate the implementation behind SplashActivity
grep -rl "SplashActivity" apktool_out/smali*/com/movieboxpro/android | head
sed -n '1,120p' apktool_out/smali/com/movieboxpro/android/view/activity/SplashActivity.smali
```

`splash`/Java package structure maps 1:1 to `smali*/` paths. Smali is the
assembled form of the app, so edits here are exact and rebuild cleanly. Java
from `jadx_out` is for reading only: it cannot be compiled back into this APK
(no Gradle project, no original resource bindings, Kotlin metadata, or
dependency graph).

**Package rename** (to install alongside the original instead of replacing it):
edit `package=` in `AndroidManifest.xml`, then rename the smali package dirs and
run `apktool` again. Simpler alternative: keep the package and uninstall the
original first (see below).

## Verification of the rebuilt APK

| Check | Result |
|---|---|
| Build | `apktool b` succeeded with patched aapt2 |
| Classes | 30,955 vs 30,955 — **0 differences** |
| Methods | 250,131 vs 250,131 — **identical** |
| Resource IDs | 8,936 vs 8,936 — **0 differences** |
| Native libs | byte-identical SHA-256 (all 32) |
| Signature | v1 + v2 + v3 verified (`apksigner verify`) |
| Badging | same package, versionCode 126, versionName 13.4, same launcher activity |
| On-device | installed on the attached device, `SplashActivity` launched, process alive after 20 s, no `FATAL EXCEPTION` |

The APK is not byte-identical to the original (impossible: recompiled DEX layout
and a new signing certificate), but it is code- and resource-equivalent.

## Installing

The rebuilt APK is signed with a local debug key, so Android will reject it over
an existing Play/other-signed install:

```bash
adb uninstall com.movieboxpro.androidtv   # removes app data
adb install MovieBoxPro-13.4-modded.apk
```

`./build.sh --install` only works after the old signature is gone.

## Known gaps / caveats

- **JADX errors:** 49 of 13,593 files contain `JADX ERROR` stubs (98.7% clean).
  Only 3 are app code: `tv/search/SearchVideoListFragment.java`,
  `utils/ShellUtil.java`, `player/IjkPlayerView2.java`; the rest are Kotlin
  coroutines internals. The smali for those classes is complete and buildable.
- **Adding new resources** can shift resource IDs. Editing existing values is
  safe; if you add resources, re-check any hardcoded `0x7f...` IDs in smali.
- **Native code** is not decompiled. `libapisecrets.so` exports no `Java_*`
  symbols (likely `RegisterNatives`/data), `libtestjni.so` exports
  `Java_com_movieboxpro_android_c_q_stringFromJNI{,2}`. Everything else is
  VLC/FFmpeg/ijkplayer.
- **No source maps / debug info** survive in the APK; original comments, local
  variable names, and formatting are unrecoverable. That is a property of
  compiled Android apps, not a limitation of this decode.

## Regenerating the Java view

```bash
tools/bin/jadx -j 4 --no-res -d jadx_out MovieBoxProtv_2026-10-29-19-47_13.4_126_movie_tvRelease.apk
```

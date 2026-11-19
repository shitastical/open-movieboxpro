package com.alibaba.fastjson.parser;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.media3.common.C;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.util.IOUtils;
import com.kaopiz.kprogresshud.BuildConfig;
import java.io.Closeable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.text.Typography;
import okhttp3.internal.http.HttpStatusCodesKt;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes4.dex */
public abstract class JSONLexerBase implements JSONLexer, Closeable {
    protected static final int INT_MULTMIN_RADIX_TEN = -214748364;
    protected static final long MULTMIN_RADIX_TEN = -922337203685477580L;
    protected int bp;
    protected char ch;
    protected int eofPos;
    protected int features;
    protected boolean hasSpecial;
    protected int np;
    protected int pos;
    protected char[] sbuf;
    protected int sp;
    protected String stringDefaultValue;
    protected int token;
    private static final ThreadLocal<char[]> SBUF_LOCAL = new ThreadLocal<>();
    protected static final char[] typeFieldName = ("\"" + JSON.DEFAULT_TYPE_KEY + "\":\"").toCharArray();
    protected static final int[] digits = new int[HttpStatusCodesKt.HTTP_EARLY_HINTS];
    protected Calendar calendar = null;
    protected TimeZone timeZone = JSON.defaultTimeZone;
    protected Locale locale = JSON.defaultLocale;
    public int matchStat = 0;

    public static boolean isWhitespace(char c) {
        return c <= ' ' && (c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == '\f' || c == '\b');
    }

    public abstract String addSymbol(int i, int i2, int i3, SymbolTable symbolTable);

    protected abstract void arrayCopy(int i, char[] cArr, int i2, int i3);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract byte[] bytesValue();

    protected abstract boolean charArrayCompare(char[] cArr);

    public abstract char charAt(int i);

    protected abstract void copyTo(int i, int i2, char[] cArr);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract BigDecimal decimalValue();

    public abstract int indexOf(char c, int i);

    public abstract boolean isEOF();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract char next();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract String numberString();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract String stringVal();

    public abstract String subString(int i, int i2);

    protected abstract char[] sub_chars(int i, int i2);

    protected void lexError(String str, Object... objArr) {
        this.token = 1;
    }

    static {
        for (int i = 48; i <= 57; i++) {
            digits[i] = i - 48;
        }
        for (int i2 = 97; i2 <= 102; i2++) {
            digits[i2] = i2 - 87;
        }
        for (int i3 = 65; i3 <= 70; i3++) {
            digits[i3] = i3 - 55;
        }
    }

    public JSONLexerBase(int i) {
        this.stringDefaultValue = null;
        this.features = i;
        if ((i & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
        char[] cArr = SBUF_LOCAL.get();
        this.sbuf = cArr;
        if (cArr == null) {
            this.sbuf = new char[512];
        }
    }

    public final int matchStat() {
        return this.matchStat;
    }

    public void setToken(int i) {
        this.token = i;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextToken() {
        this.sp = 0;
        while (true) {
            this.pos = this.bp;
            char c = this.ch;
            if (c == '/') {
                skipComment();
            } else {
                if (c == '\"') {
                    scanString();
                    return;
                }
                if (c == ',') {
                    next();
                    this.token = 16;
                    return;
                }
                if (c >= '0' && c <= '9') {
                    scanNumber();
                    return;
                }
                if (c == '-') {
                    scanNumber();
                    return;
                }
                switch (c) {
                    case '\b':
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                        next();
                        break;
                    case '\'':
                        if (!isEnabled(Feature.AllowSingleQuotes)) {
                            throw new JSONException("Feature.AllowSingleQuotes is false");
                        }
                        scanStringSingleQuote();
                        return;
                    case '(':
                        next();
                        this.token = 10;
                        return;
                    case ')':
                        next();
                        this.token = 11;
                        return;
                    case '+':
                        next();
                        scanNumber();
                        return;
                    case '.':
                        next();
                        this.token = 25;
                        return;
                    case Opcodes.ASTORE /* 58 */:
                        next();
                        this.token = 17;
                        return;
                    case ';':
                        next();
                        this.token = 24;
                        return;
                    case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64 /* 78 */:
                    case 'S':
                    case 'T':
                    case 'u':
                        scanIdent();
                        return;
                    case '[':
                        next();
                        this.token = 14;
                        return;
                    case ']':
                        next();
                        this.token = 15;
                        return;
                    case 'f':
                        scanFalse();
                        return;
                    case IjkMediaMeta.FF_PROFILE_H264_HIGH_10 /* 110 */:
                        scanNullOrNew();
                        return;
                    case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /* 116 */:
                        scanTrue();
                        return;
                    case BuildConfig.VERSION_CODE /* 120 */:
                        scanHex();
                        return;
                    case '{':
                        next();
                        this.token = 12;
                        return;
                    case '}':
                        next();
                        this.token = 13;
                        return;
                    default:
                        if (isEOF()) {
                            if (this.token == 20) {
                                throw new JSONException("EOF error");
                            }
                            this.token = 20;
                            int i = this.eofPos;
                            this.bp = i;
                            this.pos = i;
                            return;
                        }
                        char c2 = this.ch;
                        if (c2 <= 31 || c2 == 127) {
                            next();
                        } else {
                            lexError("illegal.char", String.valueOf((int) c2));
                            next();
                            return;
                        }
                        break;
                        break;
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:103:0x0073 A[SYNTHETIC] */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextToken(int i) {
        this.sp = 0;
        while (true) {
            if (i == 2) {
                char c = this.ch;
                if (c >= '0' && c <= '9') {
                    this.pos = this.bp;
                    scanNumber();
                    return;
                }
                if (c == '\"') {
                    this.pos = this.bp;
                    scanString();
                    return;
                } else if (c == '[') {
                    this.token = 14;
                    next();
                    return;
                } else if (c == '{') {
                    this.token = 12;
                    next();
                    return;
                }
            } else if (i == 4) {
                char c2 = this.ch;
                if (c2 == '\"') {
                    this.pos = this.bp;
                    scanString();
                    return;
                }
                if (c2 >= '0' && c2 <= '9') {
                    this.pos = this.bp;
                    scanNumber();
                    return;
                } else if (c2 == '[') {
                    this.token = 14;
                    next();
                    return;
                } else if (c2 == '{') {
                    this.token = 12;
                    next();
                    return;
                }
            } else if (i == 12) {
                char c3 = this.ch;
                if (c3 == '{') {
                    this.token = 12;
                    next();
                    return;
                } else if (c3 == '[') {
                    this.token = 14;
                    next();
                    return;
                }
            } else {
                if (i == 18) {
                    nextIdent();
                    return;
                }
                if (i != 20) {
                    switch (i) {
                        case 14:
                            char c4 = this.ch;
                            if (c4 == '[') {
                                this.token = 14;
                                next();
                            } else if (c4 == '{') {
                                this.token = 12;
                                next();
                            }
                            break;
                        case 15:
                            if (this.ch == ']') {
                                this.token = 15;
                                next();
                            }
                            if (this.ch == 26) {
                                this.token = 20;
                            }
                            break;
                        case 16:
                            char c5 = this.ch;
                            if (c5 == ',') {
                                this.token = 16;
                                next();
                            } else if (c5 == '}') {
                                this.token = 13;
                                next();
                            } else if (c5 == ']') {
                                this.token = 15;
                                next();
                            } else if (c5 == 26) {
                                this.token = 20;
                            }
                            break;
                    }
                    return;
                }
                if (this.ch == 26) {
                    this.token = 20;
                    return;
                }
            }
            char c6 = this.ch;
            if (c6 == ' ' || c6 == '\n' || c6 == '\r' || c6 == '\t' || c6 == '\f' || c6 == '\b') {
                next();
            } else {
                nextToken();
                return;
            }
        }
    }

    public final void nextIdent() {
        while (isWhitespace(this.ch)) {
            next();
        }
        char c = this.ch;
        if (c == '_' || c == '$' || Character.isLetter(c)) {
            scanIdent();
        } else {
            nextToken();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextTokenWithColon() {
        nextTokenWithChar(':');
    }

    public final void nextTokenWithChar(char c) {
        this.sp = 0;
        while (true) {
            char c2 = this.ch;
            if (c2 == c) {
                next();
                nextToken();
                return;
            }
            if (c2 == ' ' || c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == '\f' || c2 == '\b') {
                next();
            } else {
                throw new JSONException("not match " + c + " - " + this.ch + ", info : " + info());
            }
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int token() {
        return this.token;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String tokenName() {
        return JSONToken.name(this.token);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int pos() {
        return this.pos;
    }

    public final String stringDefaultValue() {
        return this.stringDefaultValue;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x005a  */
    /* JADX WARN: Code duplicated, block: B:24:0x006b  */
    /* JADX WARN: Code duplicated, block: B:26:0x0075  */
    /* JADX WARN: Code duplicated, block: B:28:0x0080  */
    /* JADX WARN: Code duplicated, block: B:30:0x008a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x008a -> B:19:0x0054). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final java.lang.Number integerValue() throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instruction units count: 234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.integerValue():java.lang.Number");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextTokenWithColon(int i) {
        nextTokenWithChar(':');
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public float floatValue() {
        char cCharAt;
        String strNumberString = numberString();
        float f = Float.parseFloat(strNumberString);
        if ((f != 0.0f && f != Float.POSITIVE_INFINITY) || (cCharAt = strNumberString.charAt(0)) <= '0' || cCharAt > '9') {
            return f;
        }
        throw new JSONException("float overflow : " + strNumberString);
    }

    public double doubleValue() {
        return Double.parseDouble(numberString());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void config(Feature feature, boolean z) {
        int iConfig = Feature.config(this.features, feature, z);
        this.features = iConfig;
        if ((iConfig & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isEnabled(Feature feature) {
        return isEnabled(feature.mask);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isEnabled(int i) {
        return (i & this.features) != 0;
    }

    public final boolean isEnabled(int i, int i2) {
        return ((this.features & i2) == 0 && (i & i2) == 0) ? false : true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final char getCurrent() {
        return this.ch;
    }

    protected void skipComment() {
        char c;
        next();
        char c2 = this.ch;
        if (c2 == '/') {
            do {
                next();
                c = this.ch;
                if (c == '\n') {
                    next();
                    return;
                }
            } while (c != 26);
            return;
        }
        if (c2 == '*') {
            next();
            while (true) {
                char c3 = this.ch;
                if (c3 == 26) {
                    return;
                }
                if (c3 == '*') {
                    next();
                    if (this.ch == '/') {
                        next();
                        return;
                    }
                } else {
                    next();
                }
            }
        } else {
            throw new JSONException("invalid comment");
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbol(SymbolTable symbolTable) {
        skipWhitespace();
        char c = this.ch;
        if (c == '\"') {
            return scanSymbol(symbolTable, Typography.quote);
        }
        if (c == '\'') {
            if (!isEnabled(Feature.AllowSingleQuotes)) {
                throw new JSONException("syntax error");
            }
            return scanSymbol(symbolTable, '\'');
        }
        if (c == '}') {
            next();
            this.token = 13;
            return null;
        }
        if (c == ',') {
            next();
            this.token = 16;
            return null;
        }
        if (c == 26) {
            this.token = 20;
            return null;
        }
        if (!isEnabled(Feature.AllowUnQuotedFieldNames)) {
            throw new JSONException("syntax error");
        }
        return scanSymbolUnQuoted(symbolTable);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbol(SymbolTable symbolTable, char c) {
        String strAddSymbol;
        this.np = this.bp;
        this.sp = 0;
        boolean z = false;
        int i = 0;
        while (true) {
            char next = next();
            if (next == c) {
                this.token = 4;
                if (!z) {
                    int i2 = this.np;
                    strAddSymbol = addSymbol(i2 == -1 ? 0 : i2 + 1, this.sp, i, symbolTable);
                } else {
                    strAddSymbol = symbolTable.addSymbol(this.sbuf, 0, this.sp, i);
                }
                this.sp = 0;
                next();
                return strAddSymbol;
            }
            if (next == 26) {
                throw new JSONException("unclosed.str");
            }
            if (next == '\\') {
                if (!z) {
                    int i3 = this.sp;
                    char[] cArr = this.sbuf;
                    if (i3 >= cArr.length) {
                        int length = cArr.length * 2;
                        if (i3 <= length) {
                            i3 = length;
                        }
                        char[] cArr2 = new char[i3];
                        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                        this.sbuf = cArr2;
                    }
                    arrayCopy(this.np + 1, this.sbuf, 0, this.sp);
                    z = true;
                }
                char next2 = next();
                if (next2 == '\"') {
                    i = (i * 31) + 34;
                    putChar(Typography.quote);
                } else if (next2 != '\'') {
                    if (next2 != 'F') {
                        if (next2 == '\\') {
                            i = (i * 31) + 92;
                            putChar('\\');
                        } else if (next2 == 'b') {
                            i = (i * 31) + 8;
                            putChar('\b');
                        } else if (next2 != 'f') {
                            if (next2 == 'n') {
                                i = (i * 31) + 10;
                                putChar('\n');
                            } else if (next2 == 'r') {
                                i = (i * 31) + 13;
                                putChar('\r');
                            } else if (next2 != 'x') {
                                switch (next2) {
                                    case '/':
                                        i = (i * 31) + 47;
                                        putChar('/');
                                        break;
                                    case '0':
                                        i = (i * 31) + next2;
                                        putChar((char) 0);
                                        break;
                                    case '1':
                                        i = (i * 31) + next2;
                                        putChar((char) 1);
                                        break;
                                    case '2':
                                        i = (i * 31) + next2;
                                        putChar((char) 2);
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                                        i = (i * 31) + next2;
                                        putChar((char) 3);
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                                        i = (i * 31) + next2;
                                        putChar((char) 4);
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                                        i = (i * 31) + next2;
                                        putChar((char) 5);
                                        break;
                                    case '6':
                                        i = (i * 31) + next2;
                                        putChar((char) 6);
                                        break;
                                    case '7':
                                        i = (i * 31) + next2;
                                        putChar((char) 7);
                                        break;
                                    default:
                                        switch (next2) {
                                            case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /* 116 */:
                                                i = (i * 31) + 9;
                                                putChar('\t');
                                                break;
                                            case 'u':
                                                int i4 = Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16);
                                                i = (i * 31) + i4;
                                                putChar((char) i4);
                                                break;
                                            case 'v':
                                                i = (i * 31) + 11;
                                                putChar((char) 11);
                                                break;
                                            default:
                                                this.ch = next2;
                                                throw new JSONException("unclosed.str.lit");
                                        }
                                        break;
                                }
                            } else {
                                char next3 = next();
                                this.ch = next3;
                                char next4 = next();
                                this.ch = next4;
                                int[] iArr = digits;
                                char c2 = (char) ((iArr[next3] * 16) + iArr[next4]);
                                i = (i * 31) + c2;
                                putChar(c2);
                            }
                        }
                    }
                    i = (i * 31) + 12;
                    putChar('\f');
                } else {
                    i = (i * 31) + 39;
                    putChar('\'');
                }
            } else {
                i = (i * 31) + next;
                if (!z) {
                    this.sp++;
                } else {
                    int i5 = this.sp;
                    char[] cArr3 = this.sbuf;
                    if (i5 == cArr3.length) {
                        putChar(next);
                    } else {
                        this.sp = i5 + 1;
                        cArr3[i5] = next;
                    }
                }
            }
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void resetStringPosition() {
        this.sp = 0;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String info() {
        return "";
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        if (this.token == 1 && this.pos == 0 && this.bp == 1) {
            this.bp = 0;
        }
        boolean[] zArr = IOUtils.firstIdentifierFlags;
        int i = this.ch;
        if (i < zArr.length && !zArr[i]) {
            throw new JSONException("illegal identifier : " + this.ch + info());
        }
        boolean[] zArr2 = IOUtils.identifierFlags;
        this.np = this.bp;
        this.sp = 1;
        while (true) {
            char next = next();
            if (next < zArr2.length && !zArr2[next]) {
                break;
            }
            i = (i * 31) + next;
            this.sp++;
        }
        this.ch = charAt(this.bp);
        this.token = 18;
        if (this.sp == 4 && i == 3392903 && charAt(this.np) == 'n' && charAt(this.np + 1) == 'u' && charAt(this.np + 2) == 'l' && charAt(this.np + 3) == 'l') {
            return null;
        }
        if (symbolTable == null) {
            return subString(this.np, this.sp);
        }
        return addSymbol(this.np, this.sp, i, symbolTable);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void scanString() {
        this.np = this.bp;
        this.hasSpecial = false;
        while (true) {
            char next = next();
            if (next == '\"') {
                this.token = 4;
                this.ch = next();
                return;
            }
            if (next == 26) {
                if (isEOF()) {
                    throw new JSONException("unclosed string : " + next);
                }
                putChar(JSONLexer.EOI);
            } else if (next == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    int i = this.sp;
                    char[] cArr = this.sbuf;
                    if (i >= cArr.length) {
                        int length = cArr.length * 2;
                        if (i <= length) {
                            i = length;
                        }
                        char[] cArr2 = new char[i];
                        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                        this.sbuf = cArr2;
                    }
                    copyTo(this.np + 1, this.sp, this.sbuf);
                }
                char next2 = next();
                if (next2 == '\"') {
                    putChar(Typography.quote);
                } else if (next2 != '\'') {
                    if (next2 != 'F') {
                        if (next2 == '\\') {
                            putChar('\\');
                        } else if (next2 == 'b') {
                            putChar('\b');
                        } else if (next2 != 'f') {
                            if (next2 == 'n') {
                                putChar('\n');
                            } else if (next2 == 'r') {
                                putChar('\r');
                            } else if (next2 != 'x') {
                                switch (next2) {
                                    case '/':
                                        putChar('/');
                                        break;
                                    case '0':
                                        putChar((char) 0);
                                        break;
                                    case '1':
                                        putChar((char) 1);
                                        break;
                                    case '2':
                                        putChar((char) 2);
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                                        putChar((char) 3);
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                                        putChar((char) 4);
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                                        putChar((char) 5);
                                        break;
                                    case '6':
                                        putChar((char) 6);
                                        break;
                                    case '7':
                                        putChar((char) 7);
                                        break;
                                    default:
                                        switch (next2) {
                                            case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /* 116 */:
                                                putChar('\t');
                                                break;
                                            case 'u':
                                                putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                                break;
                                            case 'v':
                                                putChar((char) 11);
                                                break;
                                            default:
                                                this.ch = next2;
                                                throw new JSONException("unclosed string : " + next2);
                                        }
                                        break;
                                }
                            } else {
                                char next3 = next();
                                char next4 = next();
                                int[] iArr = digits;
                                putChar((char) ((iArr[next3] * 16) + iArr[next4]));
                            }
                        }
                    }
                    putChar('\f');
                } else {
                    putChar('\'');
                }
            } else if (!this.hasSpecial) {
                this.sp++;
            } else {
                int i2 = this.sp;
                char[] cArr3 = this.sbuf;
                if (i2 == cArr3.length) {
                    putChar(next);
                } else {
                    this.sp = i2 + 1;
                    cArr3[i2] = next;
                }
            }
        }
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public Locale getLocale() {
        return this.locale;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int intValue() {
        int i;
        boolean z;
        int i2 = 0;
        if (this.np == -1) {
            this.np = 0;
        }
        int i3 = this.np;
        int i4 = this.sp + i3;
        if (charAt(i3) == '-') {
            i3++;
            i = Integer.MIN_VALUE;
            z = true;
        } else {
            i = C.RATE_UNSET_INT;
            z = false;
        }
        if (i3 < i4) {
            i2 = -(charAt(i3) - '0');
            i3++;
        }
        while (i3 < i4) {
            int i5 = i3 + 1;
            char cCharAt = charAt(i3);
            if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B') {
                i3 = i5;
                break;
            }
            int i6 = cCharAt - '0';
            if (i2 < -214748364) {
                throw new NumberFormatException(numberString());
            }
            int i7 = i2 * 10;
            if (i7 < i + i6) {
                throw new NumberFormatException(numberString());
            }
            i2 = i7 - i6;
            i3 = i5;
        }
        if (!z) {
            return -i2;
        }
        if (i3 > this.np + 1) {
            return i2;
        }
        throw new NumberFormatException(numberString());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        char[] cArr = this.sbuf;
        if (cArr.length <= 8192) {
            SBUF_LOCAL.set(cArr);
        }
        this.sbuf = null;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isRef() {
        return this.sp == 4 && charAt(this.np + 1) == '$' && charAt(this.np + 2) == 'r' && charAt(this.np + 3) == 'e' && charAt(this.np + 4) == 'f';
    }

    public final int scanType(String str) {
        int i;
        this.matchStat = 0;
        char[] cArr = typeFieldName;
        if (!charArrayCompare(cArr)) {
            return -2;
        }
        int length = this.bp + cArr.length;
        int length2 = str.length();
        for (int i2 = 0; i2 < length2; i2++) {
            if (str.charAt(i2) != charAt(length + i2)) {
                return -1;
            }
        }
        int i3 = length + length2;
        if (charAt(i3) != '\"') {
            return -1;
        }
        int i4 = i3 + 1;
        char cCharAt = charAt(i4);
        this.ch = cCharAt;
        if (cCharAt == ',') {
            int i5 = i3 + 2;
            this.ch = charAt(i5);
            this.bp = i5;
            this.token = 16;
            return 3;
        }
        if (cCharAt == '}') {
            i4 = i3 + 2;
            char cCharAt2 = charAt(i4);
            this.ch = cCharAt2;
            if (cCharAt2 == ',') {
                this.token = 16;
                i = i3 + 3;
                this.ch = charAt(i);
            } else if (cCharAt2 == ']') {
                this.token = 15;
                i = i3 + 3;
                this.ch = charAt(i);
            } else {
                if (cCharAt2 == '}') {
                    this.token = 13;
                    i = i3 + 3;
                    this.ch = charAt(i);
                } else {
                    if (cCharAt2 != 26) {
                        return -1;
                    }
                    this.token = 20;
                }
                this.matchStat = 4;
            }
            i4 = i;
            this.matchStat = 4;
        }
        this.bp = i4;
        return this.matchStat;
    }

    public final boolean matchField(char[] cArr) {
        while (!charArrayCompare(cArr)) {
            if (!isWhitespace(this.ch)) {
                return false;
            }
            next();
        }
        int length = this.bp + cArr.length;
        this.bp = length;
        char cCharAt = charAt(length);
        this.ch = cCharAt;
        if (cCharAt == '{') {
            next();
            this.token = 12;
        } else if (cCharAt == '[') {
            next();
            this.token = 14;
        } else if (cCharAt == 'S' && charAt(this.bp + 1) == 'e' && charAt(this.bp + 2) == 't' && charAt(this.bp + 3) == '[') {
            int i = this.bp + 3;
            this.bp = i;
            this.ch = charAt(i);
            this.token = 21;
        } else {
            nextToken();
        }
        return true;
    }

    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return stringDefaultValue();
        }
        int length = cArr.length;
        int i = length + 1;
        if (charAt(this.bp + length) != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int iIndexOf = indexOf(Typography.quote, this.bp + cArr.length + 1);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
        int length2 = this.bp + cArr.length + 1;
        String strSubString = subString(length2, iIndexOf - length2);
        if (strSubString.indexOf(92) != -1) {
            while (true) {
                int i2 = 0;
                for (int i3 = iIndexOf - 1; i3 >= 0 && charAt(i3) == '\\'; i3--) {
                    i2++;
                }
                if (i2 % 2 == 0) {
                    break;
                }
                iIndexOf = indexOf(Typography.quote, iIndexOf + 1);
            }
            int i4 = this.bp;
            int length3 = iIndexOf - ((cArr.length + i4) + 1);
            strSubString = readString(sub_chars(i4 + cArr.length + 1, length3), length3);
        }
        int i5 = this.bp;
        int length4 = i + (iIndexOf - ((cArr.length + i5) + 1)) + 1;
        int i6 = length4 + 1;
        char cCharAt = charAt(i5 + length4);
        if (cCharAt == ',') {
            int i7 = this.bp + i6;
            this.bp = i7;
            this.ch = charAt(i7);
            this.matchStat = 3;
            return strSubString;
        }
        if (cCharAt == '}') {
            int i8 = length4 + 2;
            char cCharAt2 = charAt(this.bp + i6);
            if (cCharAt2 == ',') {
                this.token = 16;
                int i9 = this.bp + i8;
                this.bp = i9;
                this.ch = charAt(i9);
            } else if (cCharAt2 == ']') {
                this.token = 15;
                int i10 = this.bp + i8;
                this.bp = i10;
                this.ch = charAt(i10);
            } else if (cCharAt2 == '}') {
                this.token = 13;
                int i11 = this.bp + i8;
                this.bp = i11;
                this.ch = charAt(i11);
            } else if (cCharAt2 == 26) {
                this.token = 20;
                this.bp += length4 + 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
            this.matchStat = 4;
            return strSubString;
        }
        this.matchStat = -1;
        return stringDefaultValue();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String scanString(char c) {
        this.matchStat = 0;
        char cCharAt = charAt(this.bp);
        if (cCharAt == 'n') {
            if (charAt(this.bp + 1) == 'u' && charAt(this.bp + 2) == 'l' && charAt(this.bp + 3) == 'l') {
                if (charAt(this.bp + 4) == c) {
                    int i = this.bp + 5;
                    this.bp = i;
                    this.ch = charAt(i);
                    this.matchStat = 3;
                    return null;
                }
                this.matchStat = -1;
                return null;
            }
            this.matchStat = -1;
            return null;
        }
        int i2 = 1;
        while (cCharAt != '\"') {
            if (isWhitespace(cCharAt)) {
                cCharAt = charAt(this.bp + i2);
                i2++;
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        }
        int i3 = this.bp + i2;
        int iIndexOf = indexOf(Typography.quote, i3);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
        String strSubString = subString(this.bp + i2, iIndexOf - i3);
        if (strSubString.indexOf(92) != -1) {
            while (true) {
                int i4 = 0;
                for (int i5 = iIndexOf - 1; i5 >= 0 && charAt(i5) == '\\'; i5--) {
                    i4++;
                }
                if (i4 % 2 == 0) {
                    break;
                }
                iIndexOf = indexOf(Typography.quote, iIndexOf + 1);
            }
            int i6 = iIndexOf - i3;
            strSubString = readString(sub_chars(this.bp + 1, i6), i6);
        }
        int i7 = i2 + (iIndexOf - i3) + 1;
        int i8 = i7 + 1;
        char cCharAt2 = charAt(this.bp + i7);
        while (cCharAt2 != c) {
            if (isWhitespace(cCharAt2)) {
                cCharAt2 = charAt(this.bp + i8);
                i8++;
            } else {
                this.matchStat = -1;
                return strSubString;
            }
        }
        int i9 = this.bp + i8;
        this.bp = i9;
        this.ch = charAt(i9);
        this.matchStat = 3;
        return strSubString;
    }

    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = cArr.length;
        int i = length + 1;
        if (charAt(this.bp + length) != '\"') {
            this.matchStat = -1;
            return 0L;
        }
        long j = -3750763034362895579L;
        while (true) {
            int i2 = i + 1;
            char cCharAt = charAt(this.bp + i);
            if (cCharAt == '\"') {
                int i3 = i + 2;
                char cCharAt2 = charAt(this.bp + i2);
                if (cCharAt2 == ',') {
                    int i4 = this.bp + i3;
                    this.bp = i4;
                    this.ch = charAt(i4);
                    this.matchStat = 3;
                    return j;
                }
                if (cCharAt2 == '}') {
                    int i5 = i + 3;
                    char cCharAt3 = charAt(this.bp + i3);
                    if (cCharAt3 == ',') {
                        this.token = 16;
                        int i6 = this.bp + i5;
                        this.bp = i6;
                        this.ch = charAt(i6);
                    } else if (cCharAt3 == ']') {
                        this.token = 15;
                        int i7 = this.bp + i5;
                        this.bp = i7;
                        this.ch = charAt(i7);
                    } else if (cCharAt3 == '}') {
                        this.token = 13;
                        int i8 = this.bp + i5;
                        this.bp = i8;
                        this.ch = charAt(i8);
                    } else if (cCharAt3 == 26) {
                        this.token = 20;
                        this.bp += i + 2;
                        this.ch = JSONLexer.EOI;
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                    this.matchStat = 4;
                    return j;
                }
                this.matchStat = -1;
                return 0L;
            }
            j = (j ^ ((long) cCharAt)) * 1099511628211L;
            if (cCharAt == '\\') {
                this.matchStat = -1;
                return 0L;
            }
            i = i2;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public Enum<?> scanEnum(Class<?> cls, SymbolTable symbolTable, char c) {
        String strScanSymbolWithSeperator = scanSymbolWithSeperator(symbolTable, c);
        if (strScanSymbolWithSeperator == null) {
            return null;
        }
        return Enum.valueOf(cls, strScanSymbolWithSeperator);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String scanSymbolWithSeperator(SymbolTable symbolTable, char c) {
        int i = 0;
        this.matchStat = 0;
        char cCharAt = charAt(this.bp);
        if (cCharAt == 'n') {
            if (charAt(this.bp + 1) == 'u' && charAt(this.bp + 2) == 'l' && charAt(this.bp + 3) == 'l') {
                if (charAt(this.bp + 4) == c) {
                    int i2 = this.bp + 5;
                    this.bp = i2;
                    this.ch = charAt(i2);
                    this.matchStat = 3;
                    return null;
                }
                this.matchStat = -1;
                return null;
            }
            this.matchStat = -1;
            return null;
        }
        if (cCharAt != '\"') {
            this.matchStat = -1;
            return null;
        }
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char cCharAt2 = charAt(this.bp + i3);
            if (cCharAt2 == '\"') {
                int i5 = this.bp;
                int i6 = i5 + 1;
                String strAddSymbol = addSymbol(i6, ((i5 + i4) - i6) - 1, i, symbolTable);
                int i7 = i3 + 2;
                char cCharAt3 = charAt(this.bp + i4);
                while (cCharAt3 != c) {
                    if (isWhitespace(cCharAt3)) {
                        cCharAt3 = charAt(this.bp + i7);
                        i7++;
                    } else {
                        this.matchStat = -1;
                        return strAddSymbol;
                    }
                }
                int i8 = this.bp + i7;
                this.bp = i8;
                this.ch = charAt(i8);
                this.matchStat = 3;
                return strAddSymbol;
            }
            i = (i * 31) + cCharAt2;
            if (cCharAt2 == '\\') {
                this.matchStat = -1;
                return null;
            }
            i3 = i4;
        }
    }

    public Collection<String> newCollectionByType(Class<?> cls) {
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public Collection<String> scanFieldStringArray(char[] cArr, Class<?> cls) {
        char cCharAt;
        int i;
        int i2;
        char cCharAt2;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        Collection<String> collectionNewCollectionByType = newCollectionByType(cls);
        int length = cArr.length;
        int i3 = length + 1;
        if (charAt(this.bp + length) != '[') {
            this.matchStat = -1;
            return null;
        }
        int i4 = length + 2;
        char cCharAt3 = charAt(this.bp + i3);
        while (true) {
            if (cCharAt3 == '\"') {
                int iIndexOf = indexOf(Typography.quote, this.bp + i4);
                if (iIndexOf == -1) {
                    throw new JSONException("unclosed str");
                }
                int i5 = this.bp + i4;
                String strSubString = subString(i5, iIndexOf - i5);
                if (strSubString.indexOf(92) != -1) {
                    while (true) {
                        int i6 = 0;
                        for (int i7 = iIndexOf - 1; i7 >= 0 && charAt(i7) == '\\'; i7--) {
                            i6++;
                        }
                        if (i6 % 2 == 0) {
                            break;
                        }
                        iIndexOf = indexOf(Typography.quote, iIndexOf + 1);
                    }
                    int i8 = this.bp;
                    int i9 = iIndexOf - (i8 + i4);
                    strSubString = readString(sub_chars(i8 + i4, i9), i9);
                }
                int i10 = this.bp;
                int i11 = i4 + (iIndexOf - (i10 + i4)) + 1;
                i2 = i11 + 1;
                cCharAt2 = charAt(i10 + i11);
                collectionNewCollectionByType.add(strSubString);
            } else if (cCharAt3 == 'n' && charAt(this.bp + i4) == 'u' && charAt(this.bp + i4 + 1) == 'l' && charAt(this.bp + i4 + 2) == 'l') {
                char cCharAt4 = charAt(this.bp + i4 + 3);
                collectionNewCollectionByType.add(null);
                i2 = i4 + 4;
                cCharAt2 = cCharAt4;
            } else {
                if (cCharAt3 == ']' && collectionNewCollectionByType.size() == 0) {
                    int i12 = i4 + 1;
                    cCharAt = charAt(this.bp + i4);
                    i = i12;
                    break;
                }
                throw new JSONException("illega str");
            }
            if (cCharAt2 != ',') {
                if (cCharAt2 == ']') {
                    i = i2 + 1;
                    cCharAt = charAt(this.bp + i2);
                    break;
                }
                this.matchStat = -1;
                return null;
            }
            int i13 = i2 + 1;
            cCharAt3 = charAt(this.bp + i2);
            i4 = i13;
        }
        if (cCharAt == ',') {
            int i14 = this.bp + i;
            this.bp = i14;
            this.ch = charAt(i14);
            this.matchStat = 3;
            return collectionNewCollectionByType;
        }
        if (cCharAt == '}') {
            int i15 = i + 1;
            char cCharAt5 = charAt(this.bp + i);
            if (cCharAt5 == ',') {
                this.token = 16;
                int i16 = this.bp + i15;
                this.bp = i16;
                this.ch = charAt(i16);
            } else if (cCharAt5 == ']') {
                this.token = 15;
                int i17 = this.bp + i15;
                this.bp = i17;
                this.ch = charAt(i17);
            } else if (cCharAt5 == '}') {
                this.token = 13;
                int i18 = this.bp + i15;
                this.bp = i18;
                this.ch = charAt(i18);
            } else if (cCharAt5 == 26) {
                this.bp += i;
                this.token = 20;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return collectionNewCollectionByType;
        }
        this.matchStat = -1;
        return null;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void scanStringArray(Collection<String> collection, char c) {
        char cCharAt;
        int i;
        int i2;
        char cCharAt2;
        this.matchStat = 0;
        char cCharAt3 = charAt(this.bp);
        char c2 = 'u';
        char c3 = 'l';
        if (cCharAt3 == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 2) == 'l' && charAt(this.bp + 3) == 'l' && charAt(this.bp + 4) == c) {
            int i3 = this.bp + 5;
            this.bp = i3;
            this.ch = charAt(i3);
            this.matchStat = 5;
            return;
        }
        if (cCharAt3 != '[') {
            this.matchStat = -1;
            return;
        }
        char cCharAt4 = charAt(this.bp + 1);
        int i4 = 2;
        while (true) {
            if (cCharAt4 == 'n' && charAt(this.bp + i4) == c2 && charAt(this.bp + i4 + 1) == c3 && charAt(this.bp + i4 + 2) == c3) {
                int i5 = i4 + 3;
                i = i4 + 4;
                cCharAt = charAt(this.bp + i5);
                collection.add(null);
            } else {
                if (cCharAt4 == ']' && collection.size() == 0) {
                    i2 = i4 + 1;
                    cCharAt2 = charAt(this.bp + i4);
                    break;
                }
                if (cCharAt4 != '\"') {
                    this.matchStat = -1;
                    return;
                }
                int i6 = this.bp + i4;
                int iIndexOf = indexOf(Typography.quote, i6);
                if (iIndexOf == -1) {
                    throw new JSONException("unclosed str");
                }
                String strSubString = subString(this.bp + i4, iIndexOf - i6);
                if (strSubString.indexOf(92) != -1) {
                    while (true) {
                        int i7 = 0;
                        for (int i8 = iIndexOf - 1; i8 >= 0 && charAt(i8) == '\\'; i8--) {
                            i7++;
                        }
                        if (i7 % 2 == 0) {
                            break;
                        } else {
                            iIndexOf = indexOf(Typography.quote, iIndexOf + 1);
                        }
                    }
                    int i9 = iIndexOf - i6;
                    strSubString = readString(sub_chars(this.bp + i4, i9), i9);
                }
                int i10 = this.bp;
                int i11 = i4 + (iIndexOf - (i10 + i4)) + 1;
                cCharAt = charAt(i10 + i11);
                collection.add(strSubString);
                i = i11 + 1;
            }
            if (cCharAt != ',') {
                if (cCharAt == ']') {
                    i2 = i + 1;
                    cCharAt2 = charAt(this.bp + i);
                    break;
                } else {
                    this.matchStat = -1;
                    return;
                }
            }
            char cCharAt5 = charAt(this.bp + i);
            i4 = i + 1;
            c2 = 'u';
            c3 = 'l';
            cCharAt4 = cCharAt5;
        }
        if (cCharAt2 == c) {
            int i12 = this.bp + i2;
            this.bp = i12;
            this.ch = charAt(i12);
            this.matchStat = 3;
            return;
        }
        this.matchStat = -1;
    }

    public int scanFieldInt(char[] cArr) {
        int i;
        char cCharAt;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char cCharAt2 = charAt(this.bp + length);
        boolean z = cCharAt2 == '-';
        if (z) {
            cCharAt2 = charAt(this.bp + i2);
            i2 = length + 2;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i3 = cCharAt2 - '0';
        while (true) {
            i = i2 + 1;
            cCharAt = charAt(this.bp + i2);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i3 = (i3 * 10) + (cCharAt - '0');
            i2 = i;
        }
        if (cCharAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if ((i3 < 0 || i > cArr.length + 14) && !(i3 == Integer.MIN_VALUE && i == 17 && z)) {
            this.matchStat = -1;
            return 0;
        }
        if (cCharAt == ',') {
            int i4 = this.bp + i;
            this.bp = i4;
            this.ch = charAt(i4);
            this.matchStat = 3;
            this.token = 16;
            return z ? -i3 : i3;
        }
        if (cCharAt == '}') {
            int i5 = i2 + 2;
            char cCharAt3 = charAt(this.bp + i);
            if (cCharAt3 == ',') {
                this.token = 16;
                int i6 = this.bp + i5;
                this.bp = i6;
                this.ch = charAt(i6);
            } else if (cCharAt3 == ']') {
                this.token = 15;
                int i7 = this.bp + i5;
                this.bp = i7;
                this.ch = charAt(i7);
            } else if (cCharAt3 == '}') {
                this.token = 13;
                int i8 = this.bp + i5;
                this.bp = i8;
                this.ch = charAt(i8);
            } else if (cCharAt3 == 26) {
                this.token = 20;
                this.bp += i2 + 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = 4;
            return z ? -i3 : i3;
        }
        this.matchStat = -1;
        return 0;
    }

    public final int[] scanFieldIntArray(char[] cArr) {
        boolean z;
        int i;
        char cCharAt;
        int i2;
        char cCharAt2;
        int i3;
        int[] iArr;
        this.matchStat = 0;
        int[] iArr2 = null;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i4 = length + 1;
        if (charAt(this.bp + length) != '[') {
            this.matchStat = -2;
            return null;
        }
        int i5 = length + 2;
        char cCharAt3 = charAt(this.bp + i4);
        int[] iArr3 = new int[16];
        if (cCharAt3 == ']') {
            i3 = length + 3;
            cCharAt2 = charAt(this.bp + i5);
            i2 = 0;
        } else {
            int i6 = 0;
            while (true) {
                if (cCharAt3 == '-') {
                    cCharAt3 = charAt(this.bp + i5);
                    i5++;
                    z = true;
                } else {
                    z = false;
                }
                if (cCharAt3 >= '0' && cCharAt3 <= '9') {
                    int i7 = cCharAt3 - '0';
                    while (true) {
                        i = i5 + 1;
                        cCharAt = charAt(this.bp + i5);
                        if (cCharAt < '0' || cCharAt > '9') {
                            break;
                        }
                        i7 = (i7 * 10) + (cCharAt - '0');
                        i5 = i;
                    }
                    if (i6 >= iArr3.length) {
                        int[] iArr4 = new int[(iArr3.length * 3) / 2];
                        System.arraycopy(iArr3, 0, iArr4, 0, i6);
                        iArr3 = iArr4;
                    }
                    i2 = i6 + 1;
                    if (z) {
                        i7 = -i7;
                    }
                    iArr3[i6] = i7;
                    if (cCharAt == ',') {
                        i5 += 2;
                        cCharAt3 = charAt(this.bp + i);
                        iArr = null;
                    } else {
                        if (cCharAt == ']') {
                            cCharAt2 = charAt(this.bp + i);
                            i3 = i5 + 2;
                            break;
                        }
                        iArr = null;
                        cCharAt3 = cCharAt;
                        i5 = i;
                    }
                    iArr2 = iArr;
                    i6 = i2;
                } else {
                    int[] iArr5 = iArr2;
                    this.matchStat = -1;
                    return iArr5;
                }
            }
        }
        if (i2 != iArr3.length) {
            int[] iArr6 = new int[i2];
            System.arraycopy(iArr3, 0, iArr6, 0, i2);
            iArr3 = iArr6;
        }
        if (cCharAt2 == ',') {
            this.bp += i3 - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return iArr3;
        }
        if (cCharAt2 == '}') {
            char cCharAt4 = charAt(this.bp + i3);
            if (cCharAt4 == ',') {
                this.token = 16;
                this.bp += i3;
                next();
            } else if (cCharAt4 == ']') {
                this.token = 15;
                this.bp += i3;
                next();
            } else if (cCharAt4 == '}') {
                this.token = 13;
                this.bp += i3;
                next();
            } else if (cCharAt4 == 26) {
                this.bp += i3;
                this.token = 20;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return iArr3;
        }
        this.matchStat = -1;
        return null;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public boolean scanBoolean(char c) {
        boolean z = false;
        this.matchStat = 0;
        char cCharAt = charAt(this.bp);
        int i = 5;
        if (cCharAt == 't') {
            if (charAt(this.bp + 1) == 'r' && charAt(this.bp + 2) == 'u' && charAt(this.bp + 3) == 'e') {
                cCharAt = charAt(this.bp + 4);
                z = true;
            } else {
                this.matchStat = -1;
                return false;
            }
        } else if (cCharAt != 'f') {
            if (cCharAt == '1') {
                cCharAt = charAt(this.bp + 1);
                z = true;
            } else if (cCharAt == '0') {
                cCharAt = charAt(this.bp + 1);
            } else {
                i = 1;
            }
            i = 2;
        } else if (charAt(this.bp + 1) == 'a' && charAt(this.bp + 2) == 'l' && charAt(this.bp + 3) == 's' && charAt(this.bp + 4) == 'e') {
            cCharAt = charAt(this.bp + 5);
            i = 6;
        } else {
            this.matchStat = -1;
            return false;
        }
        while (cCharAt != c) {
            if (isWhitespace(cCharAt)) {
                cCharAt = charAt(this.bp + i);
                i++;
            } else {
                this.matchStat = -1;
                return z;
            }
        }
        int i2 = this.bp + i;
        this.bp = i2;
        this.ch = charAt(i2);
        this.matchStat = 3;
        return z;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public int scanInt(char c) {
        int i;
        int i2;
        char cCharAt;
        this.matchStat = 0;
        char cCharAt2 = charAt(this.bp);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(this.bp + 1);
            i = 2;
        } else {
            i = 1;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            cCharAt2 = charAt(this.bp + i);
            i++;
        }
        if (cCharAt2 >= '0' && cCharAt2 <= '9') {
            int i3 = cCharAt2 - '0';
            while (true) {
                i2 = i + 1;
                cCharAt = charAt(this.bp + i);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i3 = (i3 * 10) + (cCharAt - '0');
                i = i2;
            }
            if (cCharAt == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (i3 < 0) {
                this.matchStat = -1;
                return 0;
            }
            while (cCharAt != c) {
                if (isWhitespace(cCharAt)) {
                    char cCharAt3 = charAt(this.bp + i2);
                    i2++;
                    cCharAt = cCharAt3;
                } else {
                    this.matchStat = -1;
                    return z2 ? -i3 : i3;
                }
            }
            int i4 = this.bp + i2;
            this.bp = i4;
            this.ch = charAt(i4);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -i3 : i3;
        }
        if (cCharAt2 == 'n' && charAt(this.bp + i) == 'u' && charAt(this.bp + i + 1) == 'l' && charAt(this.bp + i + 2) == 'l') {
            this.matchStat = 5;
            int i5 = i + 4;
            char cCharAt4 = charAt(this.bp + i + 3);
            if (z && cCharAt4 == '\"') {
                cCharAt4 = charAt(this.bp + i5);
                i5 = i + 5;
            }
            while (cCharAt4 != ',') {
                if (cCharAt4 == ']') {
                    int i6 = this.bp + i5;
                    this.bp = i6;
                    this.ch = charAt(i6);
                    this.matchStat = 5;
                    this.token = 15;
                    return 0;
                }
                if (isWhitespace(cCharAt4)) {
                    cCharAt4 = charAt(this.bp + i5);
                    i5++;
                } else {
                    this.matchStat = -1;
                    return 0;
                }
            }
            int i7 = this.bp + i5;
            this.bp = i7;
            this.ch = charAt(i7);
            this.matchStat = 5;
            this.token = 16;
            return 0;
        }
        this.matchStat = -1;
        return 0;
    }

    public boolean scanFieldBoolean(char[] cArr) {
        int i;
        boolean z;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char cCharAt = charAt(this.bp + length);
        if (cCharAt == 't') {
            int i3 = length + 2;
            if (charAt(this.bp + i2) != 'r') {
                this.matchStat = -1;
                return false;
            }
            int i4 = length + 3;
            if (charAt(this.bp + i3) != 'u') {
                this.matchStat = -1;
                return false;
            }
            i = length + 4;
            if (charAt(this.bp + i4) != 'e') {
                this.matchStat = -1;
                return false;
            }
            z = true;
        } else if (cCharAt == 'f') {
            int i5 = length + 2;
            if (charAt(this.bp + i2) != 'a') {
                this.matchStat = -1;
                return false;
            }
            int i6 = length + 3;
            if (charAt(this.bp + i5) != 'l') {
                this.matchStat = -1;
                return false;
            }
            int i7 = length + 4;
            if (charAt(this.bp + i6) != 's') {
                this.matchStat = -1;
                return false;
            }
            i = length + 5;
            if (charAt(this.bp + i7) != 'e') {
                this.matchStat = -1;
                return false;
            }
            z = false;
        } else {
            this.matchStat = -1;
            return false;
        }
        int i8 = i + 1;
        char cCharAt2 = charAt(this.bp + i);
        if (cCharAt2 == ',') {
            int i9 = this.bp + i8;
            this.bp = i9;
            this.ch = charAt(i9);
            this.matchStat = 3;
            this.token = 16;
            return z;
        }
        if (cCharAt2 == '}') {
            int i10 = i + 2;
            char cCharAt3 = charAt(this.bp + i8);
            if (cCharAt3 == ',') {
                this.token = 16;
                int i11 = this.bp + i10;
                this.bp = i11;
                this.ch = charAt(i11);
            } else if (cCharAt3 == ']') {
                this.token = 15;
                int i12 = this.bp + i10;
                this.bp = i12;
                this.ch = charAt(i12);
            } else if (cCharAt3 == '}') {
                this.token = 13;
                int i13 = this.bp + i10;
                this.bp = i13;
                this.ch = charAt(i13);
            } else if (cCharAt3 == 26) {
                this.token = 20;
                this.bp += i + 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return false;
            }
            this.matchStat = 4;
            return z;
        }
        this.matchStat = -1;
        return false;
    }

    public long scanFieldLong(char[] cArr) {
        int i;
        char cCharAt;
        boolean z = false;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char cCharAt2 = charAt(this.bp + length);
        if (cCharAt2 == '-') {
            cCharAt2 = charAt(this.bp + i2);
            i2 = length + 2;
            z = true;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            this.matchStat = -1;
            return 0L;
        }
        long j = cCharAt2 - '0';
        while (true) {
            i = i2 + 1;
            cCharAt = charAt(this.bp + i2);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j = (j * 10) + ((long) (cCharAt - '0'));
            i2 = i;
        }
        if (cCharAt == '.') {
            this.matchStat = -1;
            return 0L;
        }
        if (i - cArr.length >= 21 || (j < 0 && !(j == Long.MIN_VALUE && z))) {
            this.matchStat = -1;
            return 0L;
        }
        if (cCharAt == ',') {
            int i3 = this.bp + i;
            this.bp = i3;
            this.ch = charAt(i3);
            this.matchStat = 3;
            this.token = 16;
            return z ? -j : j;
        }
        if (cCharAt == '}') {
            int i4 = i2 + 2;
            char cCharAt3 = charAt(this.bp + i);
            if (cCharAt3 == ',') {
                this.token = 16;
                int i5 = this.bp + i4;
                this.bp = i5;
                this.ch = charAt(i5);
            } else if (cCharAt3 == ']') {
                this.token = 15;
                int i6 = this.bp + i4;
                this.bp = i6;
                this.ch = charAt(i6);
            } else if (cCharAt3 == '}') {
                this.token = 13;
                int i7 = this.bp + i4;
                this.bp = i7;
                this.ch = charAt(i7);
            } else if (cCharAt3 == 26) {
                this.token = 20;
                this.bp += i2 + 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0L;
            }
            this.matchStat = 4;
            return z ? -j : j;
        }
        this.matchStat = -1;
        return 0L;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public long scanLong(char c) {
        int i;
        int i2;
        char cCharAt;
        this.matchStat = 0;
        char cCharAt2 = charAt(this.bp);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(this.bp + 1);
            i = 2;
        } else {
            i = 1;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            cCharAt2 = charAt(this.bp + i);
            i++;
        }
        if (cCharAt2 >= '0') {
            char c2 = '9';
            if (cCharAt2 <= '9') {
                long j = cCharAt2 - '0';
                while (true) {
                    i2 = i + 1;
                    cCharAt = charAt(this.bp + i);
                    if (cCharAt < '0' || cCharAt > c2) {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt - '0'));
                    i = i2;
                    c2 = '9';
                }
                if (cCharAt == '.') {
                    this.matchStat = -1;
                    return 0L;
                }
                if (j < 0 && (j != Long.MIN_VALUE || !z2)) {
                    throw new NumberFormatException(subString(this.bp, i));
                }
                if (z) {
                    if (cCharAt != '\"') {
                        this.matchStat = -1;
                        return 0L;
                    }
                    cCharAt = charAt(this.bp + i2);
                    i2 = i + 2;
                }
                while (cCharAt != c) {
                    if (isWhitespace(cCharAt)) {
                        cCharAt = charAt(this.bp + i2);
                        i2++;
                    } else {
                        this.matchStat = -1;
                        return j;
                    }
                }
                int i3 = this.bp + i2;
                this.bp = i3;
                this.ch = charAt(i3);
                this.matchStat = 3;
                this.token = 16;
                return z2 ? -j : j;
            }
        }
        if (cCharAt2 == 'n' && charAt(this.bp + i) == 'u' && charAt(this.bp + i + 1) == 'l' && charAt(this.bp + i + 2) == 'l') {
            this.matchStat = 5;
            int i4 = i + 4;
            char cCharAt3 = charAt(this.bp + i + 3);
            if (z && cCharAt3 == '\"') {
                cCharAt3 = charAt(this.bp + i4);
                i4 = i + 5;
            }
            while (cCharAt3 != ',') {
                if (cCharAt3 == ']') {
                    int i5 = this.bp + i4;
                    this.bp = i5;
                    this.ch = charAt(i5);
                    this.matchStat = 5;
                    this.token = 15;
                    return 0L;
                }
                if (isWhitespace(cCharAt3)) {
                    cCharAt3 = charAt(this.bp + i4);
                    i4++;
                } else {
                    this.matchStat = -1;
                    return 0L;
                }
            }
            int i6 = this.bp + i4;
            this.bp = i6;
            this.ch = charAt(i6);
            this.matchStat = 5;
            this.token = 16;
            return 0L;
        }
        this.matchStat = -1;
        return 0L;
    }

    public final float scanFieldFloat(char[] cArr) {
        int i;
        char cCharAt;
        int i2;
        int length;
        int i3;
        float f;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0.0f;
        }
        int length2 = cArr.length;
        int i4 = length2 + 1;
        char cCharAt2 = charAt(this.bp + length2);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(this.bp + i4);
            i4 = length2 + 2;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            cCharAt2 = charAt(this.bp + i4);
            i4++;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            if (cCharAt2 == 'n' && charAt(this.bp + i4) == 'u' && charAt(this.bp + i4 + 1) == 'l' && charAt(this.bp + i4 + 2) == 'l') {
                this.matchStat = 5;
                int i5 = i4 + 4;
                char cCharAt3 = charAt(this.bp + i4 + 3);
                if (z && cCharAt3 == '\"') {
                    cCharAt3 = charAt(this.bp + i5);
                    i5 = i4 + 5;
                }
                while (cCharAt3 != ',') {
                    if (cCharAt3 == '}') {
                        int i6 = this.bp + i5;
                        this.bp = i6;
                        this.ch = charAt(i6);
                        this.matchStat = 5;
                        this.token = 13;
                        return 0.0f;
                    }
                    if (isWhitespace(cCharAt3)) {
                        cCharAt3 = charAt(this.bp + i5);
                        i5++;
                    } else {
                        this.matchStat = -1;
                        return 0.0f;
                    }
                }
                int i7 = this.bp + i5;
                this.bp = i7;
                this.ch = charAt(i7);
                this.matchStat = 5;
                this.token = 16;
                return 0.0f;
            }
            this.matchStat = -1;
            return 0.0f;
        }
        int i8 = cCharAt2 - '0';
        while (true) {
            i = i4 + 1;
            cCharAt = charAt(this.bp + i4);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i8 = (i8 * 10) + (cCharAt - '0');
            i4 = i;
        }
        if (cCharAt == '.') {
            int i9 = i4 + 2;
            char cCharAt4 = charAt(this.bp + i);
            if (cCharAt4 < '0' || cCharAt4 > '9') {
                this.matchStat = -1;
                return 0.0f;
            }
            i8 = (i8 * 10) + (cCharAt4 - '0');
            i2 = 10;
            while (true) {
                i = i9 + 1;
                cCharAt = charAt(this.bp + i9);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i8 = (i8 * 10) + (cCharAt - '0');
                i2 *= 10;
                i9 = i;
            }
        } else {
            i2 = 1;
        }
        boolean z3 = cCharAt == 'e' || cCharAt == 'E';
        if (z3) {
            int i10 = i + 1;
            char cCharAt5 = charAt(this.bp + i);
            if (cCharAt5 == '+' || cCharAt5 == '-') {
                i += 2;
                cCharAt = charAt(this.bp + i10);
            } else {
                i = i10;
                cCharAt = cCharAt5;
            }
            while (cCharAt >= '0' && cCharAt <= '9') {
                cCharAt = charAt(this.bp + i);
                i++;
            }
        }
        if (!z) {
            int i11 = this.bp;
            length = cArr.length + i11;
            i3 = ((i11 + i) - length) - 1;
        } else {
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return 0.0f;
            }
            int i12 = i + 1;
            cCharAt = charAt(this.bp + i);
            int i13 = this.bp;
            length = cArr.length + i13 + 1;
            i3 = ((i13 + i12) - length) - 2;
            i = i12;
        }
        if (z3 || i3 >= 18) {
            f = Float.parseFloat(subString(length, i3));
        } else {
            f = i8 / i2;
            if (z2) {
                f = -f;
            }
        }
        if (cCharAt == ',') {
            int i14 = this.bp + i;
            this.bp = i14;
            this.ch = charAt(i14);
            this.matchStat = 3;
            this.token = 16;
            return f;
        }
        if (cCharAt == '}') {
            int i15 = i + 1;
            char cCharAt6 = charAt(this.bp + i);
            if (cCharAt6 == ',') {
                this.token = 16;
                int i16 = this.bp + i15;
                this.bp = i16;
                this.ch = charAt(i16);
            } else if (cCharAt6 == ']') {
                this.token = 15;
                int i17 = this.bp + i15;
                this.bp = i17;
                this.ch = charAt(i17);
            } else if (cCharAt6 == '}') {
                this.token = 13;
                int i18 = this.bp + i15;
                this.bp = i18;
                this.ch = charAt(i18);
            } else if (cCharAt6 == 26) {
                this.bp += i;
                this.token = 20;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0.0f;
            }
            this.matchStat = 4;
            return f;
        }
        this.matchStat = -1;
        return 0.0f;
    }

    /* JADX WARN: Code duplicated, block: B:54:0x00c3 A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x00c5 -> B:51:0x00b4). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final float scanFloat(char r21) {
        /*
            Method dump skipped, instruction units count: 424
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFloat(char):float");
    }

    /* JADX WARN: Code duplicated, block: B:54:0x00c2 A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x00c4 -> B:51:0x00b3). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public double scanDouble(char r21) {
        /*
            Method dump skipped, instruction units count: 429
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanDouble(char):double");
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0097 A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0099 -> B:39:0x0089). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public java.math.BigDecimal scanDecimal(char r18) {
        /*
            Method dump skipped, instruction units count: 447
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanDecimal(char):java.math.BigDecimal");
    }

    public final float[] scanFieldFloatArray(char[] cArr) {
        int i;
        char cCharAt;
        int i2;
        float f;
        float[] fArr;
        this.matchStat = 0;
        float[] fArr2 = null;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i3 = length + 1;
        if (charAt(this.bp + length) != '[') {
            this.matchStat = -2;
            return null;
        }
        int i4 = length + 2;
        char cCharAt2 = charAt(this.bp + i3);
        float[] fArr3 = new float[16];
        int i5 = 0;
        while (true) {
            int i6 = this.bp;
            int i7 = (i6 + i4) - 1;
            boolean z = cCharAt2 == '-';
            if (z) {
                cCharAt2 = charAt(i6 + i4);
                i4++;
            }
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                break;
            }
            int i8 = cCharAt2 - '0';
            while (true) {
                i = i4 + 1;
                cCharAt = charAt(this.bp + i4);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i8 = (i8 * 10) + (cCharAt - '0');
                i4 = i;
            }
            if (cCharAt == '.') {
                int i9 = i4 + 2;
                char cCharAt3 = charAt(this.bp + i);
                if (cCharAt3 < '0' || cCharAt3 > '9') {
                    this.matchStat = -1;
                    return fArr2;
                }
                i8 = (i8 * 10) + (cCharAt3 - '0');
                i2 = 10;
                while (true) {
                    i = i9 + 1;
                    cCharAt = charAt(this.bp + i9);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    i8 = (i8 * 10) + (cCharAt - '0');
                    i2 *= 10;
                    i9 = i;
                }
            } else {
                i2 = 1;
            }
            boolean z2 = cCharAt == 'e' || cCharAt == 'E';
            if (z2) {
                int i10 = i + 1;
                cCharAt = charAt(this.bp + i);
                if (cCharAt == '+' || cCharAt == '-') {
                    i += 2;
                    cCharAt = charAt(this.bp + i10);
                } else {
                    i = i10;
                }
                while (cCharAt >= '0' && cCharAt <= '9') {
                    cCharAt = charAt(this.bp + i);
                    i++;
                }
            }
            int i11 = ((this.bp + i) - i7) - 1;
            if (z2 || i11 >= 10) {
                f = Float.parseFloat(subString(i7, i11));
            } else {
                f = i8 / i2;
                if (z) {
                    f = -f;
                }
            }
            if (i5 >= fArr3.length) {
                float[] fArr4 = new float[(fArr3.length * 3) / 2];
                System.arraycopy(fArr3, 0, fArr4, 0, i5);
                fArr3 = fArr4;
            }
            int i12 = i5 + 1;
            fArr3[i5] = f;
            if (cCharAt == ',') {
                i4 = i + 1;
                cCharAt = charAt(this.bp + i);
                fArr = null;
            } else {
                if (cCharAt == ']') {
                    int i13 = i + 1;
                    char cCharAt4 = charAt(this.bp + i);
                    if (i12 != fArr3.length) {
                        float[] fArr5 = new float[i12];
                        System.arraycopy(fArr3, 0, fArr5, 0, i12);
                        fArr3 = fArr5;
                    }
                    if (cCharAt4 == ',') {
                        this.bp += i;
                        next();
                        this.matchStat = 3;
                        this.token = 16;
                        return fArr3;
                    }
                    if (cCharAt4 == '}') {
                        char cCharAt5 = charAt(this.bp + i13);
                        if (cCharAt5 == ',') {
                            this.token = 16;
                            this.bp += i + 1;
                            next();
                        } else if (cCharAt5 == ']') {
                            this.token = 15;
                            this.bp += i + 1;
                            next();
                        } else if (cCharAt5 == '}') {
                            this.token = 13;
                            this.bp += i + 1;
                            next();
                        } else if (cCharAt5 == 26) {
                            this.bp += i + 1;
                            this.token = 20;
                            this.ch = JSONLexer.EOI;
                        } else {
                            this.matchStat = -1;
                            return null;
                        }
                        this.matchStat = 4;
                        return fArr3;
                    }
                    this.matchStat = -1;
                    return null;
                }
                fArr = null;
                i4 = i;
            }
            i5 = i12;
            cCharAt2 = cCharAt;
            fArr2 = fArr;
        }
        float[] fArr6 = fArr2;
        this.matchStat = -1;
        return fArr6;
    }

    public final float[][] scanFieldFloatArray2(char[] cArr) {
        int i;
        char cCharAt;
        int i2;
        int i3;
        float f;
        int i4;
        int i5;
        char cCharAt2;
        this.matchStat = 0;
        float[][] fArr = null;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i6 = length + 1;
        char c = '[';
        if (charAt(this.bp + length) != '[') {
            this.matchStat = -2;
            return null;
        }
        int i7 = length + 2;
        char cCharAt3 = charAt(this.bp + i6);
        int i8 = 16;
        float[][] fArr2 = new float[16][];
        int i9 = 0;
        loop0: while (true) {
            if (cCharAt3 == c) {
                int i10 = i7 + 1;
                char cCharAt4 = charAt(this.bp + i7);
                float[] fArr3 = new float[i8];
                int i11 = 0;
                while (true) {
                    int i12 = this.bp;
                    int i13 = (i12 + i10) - 1;
                    boolean z = cCharAt4 == '-';
                    if (z) {
                        int i14 = i10 + 1;
                        char cCharAt5 = charAt(i12 + i10);
                        i10 = i14;
                        cCharAt4 = cCharAt5;
                    }
                    if (cCharAt4 < '0' || cCharAt4 > '9') {
                        break loop0;
                    }
                    int i15 = cCharAt4 - '0';
                    while (true) {
                        i = i10 + 1;
                        cCharAt = charAt(this.bp + i10);
                        if (cCharAt < '0' || cCharAt > '9') {
                            break;
                        }
                        i15 = (i15 * 10) + (cCharAt - '0');
                        i10 = i;
                    }
                    if (cCharAt == '.') {
                        int i16 = i10 + 2;
                        char cCharAt6 = charAt(this.bp + i);
                        if (cCharAt6 < '0' || cCharAt6 > '9') {
                            this.matchStat = -1;
                            return fArr;
                        }
                        int i17 = (i15 * 10) + (cCharAt6 - '0');
                        int i18 = 10;
                        while (true) {
                            i = i16 + 1;
                            cCharAt2 = charAt(this.bp + i16);
                            if (cCharAt2 < '0' || cCharAt2 > '9') {
                                break;
                            }
                            i17 = (i17 * 10) + (cCharAt2 - '0');
                            i18 *= 10;
                            i16 = i;
                        }
                        int i19 = i17;
                        i3 = i18;
                        cCharAt = cCharAt2;
                        i2 = i19;
                    } else {
                        i2 = i15;
                        i3 = 1;
                    }
                    boolean z2 = cCharAt == 'e' || cCharAt == 'E';
                    if (z2) {
                        int i20 = i + 1;
                        cCharAt = charAt(this.bp + i);
                        if (cCharAt == '+' || cCharAt == '-') {
                            i += 2;
                            cCharAt = charAt(this.bp + i20);
                        } else {
                            i = i20;
                        }
                        while (cCharAt >= '0' && cCharAt <= '9') {
                            cCharAt = charAt(this.bp + i);
                            i++;
                        }
                    }
                    int i21 = ((this.bp + i) - i13) - 1;
                    if (z2 || i21 >= 10) {
                        f = Float.parseFloat(subString(i13, i21));
                    } else {
                        f = i2 / i3;
                        if (z) {
                            f = -f;
                        }
                    }
                    if (i11 >= fArr3.length) {
                        float[] fArr4 = new float[(fArr3.length * 3) / 2];
                        System.arraycopy(fArr3, 0, fArr4, 0, i11);
                        fArr3 = fArr4;
                    }
                    int i22 = i11 + 1;
                    fArr3[i11] = f;
                    if (cCharAt == ',') {
                        cCharAt = charAt(this.bp + i);
                        i10 = i + 1;
                        fArr = null;
                    } else {
                        if (cCharAt == ']') {
                            int i23 = i + 1;
                            char cCharAt7 = charAt(this.bp + i);
                            if (i22 != fArr3.length) {
                                float[] fArr5 = new float[i22];
                                i4 = 0;
                                System.arraycopy(fArr3, 0, fArr5, 0, i22);
                                fArr3 = fArr5;
                            } else {
                                i4 = 0;
                            }
                            if (i9 >= fArr2.length) {
                                fArr2 = new float[(fArr2.length * 3) / 2][];
                                System.arraycopy(fArr3, i4, fArr2, i4, i22);
                            }
                            int i24 = i9 + 1;
                            fArr2[i9] = fArr3;
                            if (cCharAt7 == ',') {
                                i5 = i + 2;
                                cCharAt3 = charAt(this.bp + i23);
                                fArr = null;
                                i8 = 16;
                            } else {
                                if (cCharAt7 == ']') {
                                    int i25 = i + 2;
                                    char cCharAt8 = charAt(this.bp + i23);
                                    if (i24 != fArr2.length) {
                                        float[][] fArr6 = new float[i24][];
                                        System.arraycopy(fArr2, 0, fArr6, 0, i24);
                                        fArr2 = fArr6;
                                    }
                                    if (cCharAt8 == ',') {
                                        this.bp += i + 1;
                                        next();
                                        this.matchStat = 3;
                                        this.token = 16;
                                        return fArr2;
                                    }
                                    if (cCharAt8 == '}') {
                                        char cCharAt9 = charAt(this.bp + i25);
                                        if (cCharAt9 == ',') {
                                            this.token = 16;
                                            this.bp += i + 2;
                                            next();
                                        } else if (cCharAt9 == ']') {
                                            this.token = 15;
                                            this.bp += i + 2;
                                            next();
                                        } else if (cCharAt9 == '}') {
                                            this.token = 13;
                                            this.bp += i + 2;
                                            next();
                                        } else if (cCharAt9 == 26) {
                                            this.bp += i + 2;
                                            this.token = 20;
                                            this.ch = JSONLexer.EOI;
                                        } else {
                                            this.matchStat = -1;
                                            return null;
                                        }
                                        this.matchStat = 4;
                                        return fArr2;
                                    }
                                    this.matchStat = -1;
                                    return null;
                                }
                                fArr = null;
                                i8 = 16;
                                cCharAt3 = cCharAt7;
                                i5 = i23;
                            }
                            i9 = i24;
                            i7 = i5;
                            c = '[';
                            break;
                        }
                        fArr = null;
                        i10 = i;
                    }
                    i11 = i22;
                    cCharAt4 = cCharAt;
                }
            }
        }
        this.matchStat = -1;
        return fArr;
    }

    public final double scanFieldDouble(char[] cArr) {
        int i;
        char cCharAt;
        long j;
        int length;
        int i2;
        double d;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0.0d;
        }
        int length2 = cArr.length;
        int i3 = length2 + 1;
        char cCharAt2 = charAt(this.bp + length2);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(this.bp + i3);
            i3 = length2 + 2;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            cCharAt2 = charAt(this.bp + i3);
            i3++;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            if (cCharAt2 == 'n' && charAt(this.bp + i3) == 'u' && charAt(this.bp + i3 + 1) == 'l' && charAt(this.bp + i3 + 2) == 'l') {
                this.matchStat = 5;
                int i4 = i3 + 4;
                char cCharAt3 = charAt(this.bp + i3 + 3);
                if (z && cCharAt3 == '\"') {
                    cCharAt3 = charAt(this.bp + i4);
                    i4 = i3 + 5;
                }
                while (cCharAt3 != ',') {
                    if (cCharAt3 == '}') {
                        int i5 = this.bp + i4;
                        this.bp = i5;
                        this.ch = charAt(i5);
                        this.matchStat = 5;
                        this.token = 13;
                        return 0.0d;
                    }
                    if (isWhitespace(cCharAt3)) {
                        cCharAt3 = charAt(this.bp + i4);
                        i4++;
                    } else {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                }
                int i6 = this.bp + i4;
                this.bp = i6;
                this.ch = charAt(i6);
                this.matchStat = 5;
                this.token = 16;
                return 0.0d;
            }
            this.matchStat = -1;
            return 0.0d;
        }
        long j2 = cCharAt2 - '0';
        while (true) {
            i = i3 + 1;
            cCharAt = charAt(this.bp + i3);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j2 = (j2 * 10) + ((long) (cCharAt - '0'));
            i3 = i;
        }
        if (cCharAt == '.') {
            int i7 = i3 + 2;
            char cCharAt4 = charAt(this.bp + i);
            if (cCharAt4 < '0' || cCharAt4 > '9') {
                this.matchStat = -1;
                return 0.0d;
            }
            j2 = (j2 * 10) + ((long) (cCharAt4 - '0'));
            j = 10;
            while (true) {
                i = i7 + 1;
                cCharAt = charAt(this.bp + i7);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                j2 = (j2 * 10) + ((long) (cCharAt - '0'));
                j *= 10;
                i7 = i;
            }
        } else {
            j = 1;
        }
        boolean z3 = cCharAt == 'e' || cCharAt == 'E';
        if (z3) {
            int i8 = i + 1;
            cCharAt = charAt(this.bp + i);
            if (cCharAt == '+' || cCharAt == '-') {
                i += 2;
                cCharAt = charAt(this.bp + i8);
            } else {
                i = i8;
            }
            while (cCharAt >= '0' && cCharAt <= '9') {
                char cCharAt5 = charAt(this.bp + i);
                i++;
                cCharAt = cCharAt5;
            }
        }
        if (!z) {
            int i9 = this.bp;
            length = cArr.length + i9;
            i2 = ((i9 + i) - length) - 1;
        } else {
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return 0.0d;
            }
            int i10 = i + 1;
            char cCharAt6 = charAt(this.bp + i);
            int i11 = this.bp;
            length = cArr.length + i11 + 1;
            i2 = ((i11 + i10) - length) - 2;
            i = i10;
            cCharAt = cCharAt6;
        }
        if (z3 || i2 >= 20) {
            d = Double.parseDouble(subString(length, i2));
        } else {
            d = j2 / j;
            if (z2) {
                d = -d;
            }
        }
        if (cCharAt == ',') {
            int i12 = this.bp + i;
            this.bp = i12;
            this.ch = charAt(i12);
            this.matchStat = 3;
            this.token = 16;
            return d;
        }
        if (cCharAt == '}') {
            int i13 = i + 1;
            char cCharAt7 = charAt(this.bp + i);
            if (cCharAt7 == ',') {
                this.token = 16;
                int i14 = this.bp + i13;
                this.bp = i14;
                this.ch = charAt(i14);
            } else if (cCharAt7 == ']') {
                this.token = 15;
                int i15 = this.bp + i13;
                this.bp = i15;
                this.ch = charAt(i15);
            } else if (cCharAt7 == '}') {
                this.token = 13;
                int i16 = this.bp + i13;
                this.bp = i16;
                this.ch = charAt(i16);
            } else if (cCharAt7 == 26) {
                this.token = 20;
                this.bp += i;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0.0d;
            }
            this.matchStat = 4;
            return d;
        }
        this.matchStat = -1;
        return 0.0d;
    }

    public BigDecimal scanFieldDecimal(char[] cArr) {
        int i;
        char cCharAt;
        int length;
        int i2;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length2 = cArr.length;
        int i3 = length2 + 1;
        char cCharAt2 = charAt(this.bp + length2);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(this.bp + i3);
            i3 = length2 + 2;
        }
        if (cCharAt2 == '-') {
            cCharAt2 = charAt(this.bp + i3);
            i3++;
        }
        if (cCharAt2 >= '0' && cCharAt2 <= '9') {
            while (true) {
                i = i3 + 1;
                cCharAt = charAt(this.bp + i3);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i3 = i;
            }
            if (cCharAt == '.') {
                int i4 = i3 + 2;
                char cCharAt3 = charAt(this.bp + i);
                if (cCharAt3 < '0' || cCharAt3 > '9') {
                    this.matchStat = -1;
                    return null;
                }
                while (true) {
                    i = i4 + 1;
                    cCharAt = charAt(this.bp + i4);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    i4 = i;
                }
            }
            if (cCharAt == 'e' || cCharAt == 'E') {
                int i5 = i + 1;
                cCharAt = charAt(this.bp + i);
                if (cCharAt == '+' || cCharAt == '-') {
                    i += 2;
                    cCharAt = charAt(this.bp + i5);
                } else {
                    i = i5;
                }
                while (cCharAt >= '0' && cCharAt <= '9') {
                    char cCharAt4 = charAt(this.bp + i);
                    i++;
                    cCharAt = cCharAt4;
                }
            }
            if (!z) {
                int i6 = this.bp;
                length = cArr.length + i6;
                i2 = ((i6 + i) - length) - 1;
            } else {
                if (cCharAt != '\"') {
                    this.matchStat = -1;
                    return null;
                }
                int i7 = i + 1;
                cCharAt = charAt(this.bp + i);
                int i8 = this.bp;
                length = cArr.length + i8 + 1;
                i2 = ((i8 + i7) - length) - 2;
                i = i7;
            }
            BigDecimal bigDecimal = new BigDecimal(sub_chars(length, i2));
            if (cCharAt == ',') {
                int i9 = this.bp + i;
                this.bp = i9;
                this.ch = charAt(i9);
                this.matchStat = 3;
                this.token = 16;
                return bigDecimal;
            }
            if (cCharAt == '}') {
                int i10 = i + 1;
                char cCharAt5 = charAt(this.bp + i);
                if (cCharAt5 == ',') {
                    this.token = 16;
                    int i11 = this.bp + i10;
                    this.bp = i11;
                    this.ch = charAt(i11);
                } else if (cCharAt5 == ']') {
                    this.token = 15;
                    int i12 = this.bp + i10;
                    this.bp = i12;
                    this.ch = charAt(i12);
                } else if (cCharAt5 == '}') {
                    this.token = 13;
                    int i13 = this.bp + i10;
                    this.bp = i13;
                    this.ch = charAt(i13);
                } else if (cCharAt5 == 26) {
                    this.token = 20;
                    this.bp += i;
                    this.ch = JSONLexer.EOI;
                } else {
                    this.matchStat = -1;
                    return null;
                }
                this.matchStat = 4;
                return bigDecimal;
            }
            this.matchStat = -1;
            return null;
        }
        if (cCharAt2 == 'n' && charAt(this.bp + i3) == 'u' && charAt(this.bp + i3 + 1) == 'l' && charAt(this.bp + i3 + 2) == 'l') {
            this.matchStat = 5;
            int i14 = i3 + 4;
            char cCharAt6 = charAt(this.bp + i3 + 3);
            if (z && cCharAt6 == '\"') {
                cCharAt6 = charAt(this.bp + i14);
                i14 = i3 + 5;
            }
            while (cCharAt6 != ',') {
                if (cCharAt6 == '}') {
                    int i15 = this.bp + i14;
                    this.bp = i15;
                    this.ch = charAt(i15);
                    this.matchStat = 5;
                    this.token = 13;
                    return null;
                }
                if (isWhitespace(cCharAt6)) {
                    cCharAt6 = charAt(this.bp + i14);
                    i14++;
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
            int i16 = this.bp + i14;
            this.bp = i16;
            this.ch = charAt(i16);
            this.matchStat = 5;
            this.token = 16;
            return null;
        }
        this.matchStat = -1;
        return null;
    }

    public BigInteger scanFieldBigInteger(char[] cArr) {
        int i;
        char cCharAt;
        int length;
        int i2;
        BigInteger bigIntegerValueOf;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length2 = cArr.length;
        int i3 = length2 + 1;
        char cCharAt2 = charAt(this.bp + length2);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(this.bp + i3);
            i3 = length2 + 2;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            char cCharAt3 = charAt(this.bp + i3);
            i3++;
            cCharAt2 = cCharAt3;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            if (cCharAt2 == 'n' && charAt(this.bp + i3) == 'u' && charAt(this.bp + i3 + 1) == 'l' && charAt(this.bp + i3 + 2) == 'l') {
                this.matchStat = 5;
                int i4 = i3 + 4;
                char cCharAt4 = charAt(this.bp + i3 + 3);
                if (z && cCharAt4 == '\"') {
                    cCharAt4 = charAt(this.bp + i4);
                    i4 = i3 + 5;
                }
                while (cCharAt4 != ',') {
                    if (cCharAt4 == '}') {
                        int i5 = this.bp + i4;
                        this.bp = i5;
                        this.ch = charAt(i5);
                        this.matchStat = 5;
                        this.token = 13;
                        return null;
                    }
                    if (isWhitespace(cCharAt4)) {
                        cCharAt4 = charAt(this.bp + i4);
                        i4++;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                int i6 = this.bp + i4;
                this.bp = i6;
                this.ch = charAt(i6);
                this.matchStat = 5;
                this.token = 16;
                return null;
            }
            this.matchStat = -1;
            return null;
        }
        long j = cCharAt2 - '0';
        while (true) {
            i = i3 + 1;
            cCharAt = charAt(this.bp + i3);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j = (j * 10) + ((long) (cCharAt - '0'));
            i3 = i;
        }
        if (!z) {
            int i7 = this.bp;
            length = cArr.length + i7;
            i2 = ((i7 + i) - length) - 1;
        } else {
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return null;
            }
            int i8 = i3 + 2;
            cCharAt = charAt(this.bp + i);
            int i9 = this.bp;
            length = cArr.length + i9 + 1;
            i2 = ((i9 + i8) - length) - 2;
            i = i8;
        }
        if (i2 < 20 || (z2 && i2 < 21)) {
            if (z2) {
                j = -j;
            }
            bigIntegerValueOf = BigInteger.valueOf(j);
        } else {
            bigIntegerValueOf = new BigInteger(subString(length, i2));
        }
        if (cCharAt == ',') {
            int i10 = this.bp + i;
            this.bp = i10;
            this.ch = charAt(i10);
            this.matchStat = 3;
            this.token = 16;
            return bigIntegerValueOf;
        }
        if (cCharAt == '}') {
            int i11 = i + 1;
            char cCharAt5 = charAt(this.bp + i);
            if (cCharAt5 == ',') {
                this.token = 16;
                int i12 = this.bp + i11;
                this.bp = i12;
                this.ch = charAt(i12);
            } else if (cCharAt5 == ']') {
                this.token = 15;
                int i13 = this.bp + i11;
                this.bp = i13;
                this.ch = charAt(i13);
            } else if (cCharAt5 == '}') {
                this.token = 13;
                int i14 = this.bp + i11;
                this.bp = i14;
                this.ch = charAt(i14);
            } else if (cCharAt5 == 26) {
                this.token = 20;
                this.bp += i;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return bigIntegerValueOf;
        }
        this.matchStat = -1;
        return null;
    }

    public Date scanFieldDate(char[] cArr) {
        char cCharAt;
        int i;
        long j;
        Date date;
        int i2;
        char cCharAt2;
        boolean z = false;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i3 = length + 1;
        char cCharAt3 = charAt(this.bp + length);
        if (cCharAt3 == '\"') {
            int iIndexOf = indexOf(Typography.quote, this.bp + cArr.length + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int length2 = this.bp + cArr.length + 1;
            String strSubString = subString(length2, iIndexOf - length2);
            if (strSubString.indexOf(92) != -1) {
                while (true) {
                    int i4 = 0;
                    for (int i5 = iIndexOf - 1; i5 >= 0 && charAt(i5) == '\\'; i5--) {
                        i4++;
                    }
                    if (i4 % 2 == 0) {
                        break;
                    }
                    iIndexOf = indexOf(Typography.quote, iIndexOf + 1);
                }
                int i6 = this.bp;
                int length3 = iIndexOf - ((cArr.length + i6) + 1);
                strSubString = readString(sub_chars(i6 + cArr.length + 1, length3), length3);
            }
            int i7 = this.bp;
            int length4 = i3 + (iIndexOf - ((cArr.length + i7) + 1)) + 1;
            i = length4 + 1;
            cCharAt = charAt(i7 + length4);
            JSONScanner jSONScanner = new JSONScanner(strSubString);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    date = jSONScanner.getCalendar().getTime();
                    jSONScanner.close();
                } else {
                    this.matchStat = -1;
                    jSONScanner.close();
                    return null;
                }
            } catch (Throwable th) {
                jSONScanner.close();
                throw th;
            }
        } else {
            if (cCharAt3 != '-' && (cCharAt3 < '0' || cCharAt3 > '9')) {
                this.matchStat = -1;
                return null;
            }
            if (cCharAt3 == '-') {
                cCharAt3 = charAt(this.bp + i3);
                i3 = length + 2;
                z = true;
            }
            if (cCharAt3 < '0' || cCharAt3 > '9') {
                cCharAt = cCharAt3;
                i = i3;
                j = 0;
            } else {
                j = cCharAt3 - '0';
                while (true) {
                    i2 = i3 + 1;
                    cCharAt2 = charAt(this.bp + i3);
                    if (cCharAt2 < '0' || cCharAt2 > '9') {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt2 - '0'));
                    i3 = i2;
                }
                cCharAt = cCharAt2;
                i = i2;
            }
            if (j < 0) {
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            date = new Date(j);
        }
        if (cCharAt == ',') {
            int i8 = this.bp + i;
            this.bp = i8;
            this.ch = charAt(i8);
            this.matchStat = 3;
            return date;
        }
        if (cCharAt == '}') {
            int i9 = i + 1;
            char cCharAt4 = charAt(this.bp + i);
            if (cCharAt4 == ',') {
                this.token = 16;
                int i10 = this.bp + i9;
                this.bp = i10;
                this.ch = charAt(i10);
            } else if (cCharAt4 == ']') {
                this.token = 15;
                int i11 = this.bp + i9;
                this.bp = i11;
                this.ch = charAt(i11);
            } else if (cCharAt4 == '}') {
                this.token = 13;
                int i12 = this.bp + i9;
                this.bp = i12;
                this.ch = charAt(i12);
            } else if (cCharAt4 == 26) {
                this.token = 20;
                this.bp += i;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return date;
        }
        this.matchStat = -1;
        return null;
    }

    public Date scanDate(char c) {
        long j;
        char cCharAt;
        int i;
        Date date;
        int i2;
        char cCharAt2;
        boolean z = false;
        this.matchStat = 0;
        char cCharAt3 = charAt(this.bp);
        int i3 = 1;
        if (cCharAt3 == '\"') {
            int iIndexOf = indexOf(Typography.quote, this.bp + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int i4 = this.bp + 1;
            String strSubString = subString(i4, iIndexOf - i4);
            if (strSubString.indexOf(92) != -1) {
                while (true) {
                    int i5 = 0;
                    for (int i6 = iIndexOf - 1; i6 >= 0 && charAt(i6) == '\\'; i6--) {
                        i5++;
                    }
                    if (i5 % 2 == 0) {
                        break;
                    }
                    iIndexOf = indexOf(Typography.quote, iIndexOf + 1);
                }
                int i7 = this.bp;
                int i8 = iIndexOf - (i7 + 1);
                strSubString = readString(sub_chars(i7 + 1, i8), i8);
            }
            int i9 = this.bp;
            int i10 = iIndexOf - (i9 + 1);
            int i11 = i10 + 2;
            i = i10 + 3;
            cCharAt = charAt(i9 + i11);
            JSONScanner jSONScanner = new JSONScanner(strSubString);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    date = jSONScanner.getCalendar().getTime();
                    jSONScanner.close();
                } else {
                    this.matchStat = -1;
                    jSONScanner.close();
                    return null;
                }
            } catch (Throwable th) {
                jSONScanner.close();
                throw th;
            }
        } else {
            char c2 = '9';
            if (cCharAt3 == '-' || (cCharAt3 >= '0' && cCharAt3 <= '9')) {
                if (cCharAt3 == '-') {
                    cCharAt3 = charAt(this.bp + 1);
                    z = true;
                    i3 = 2;
                }
                if (cCharAt3 < '0' || cCharAt3 > '9') {
                    j = 0;
                    int i12 = i3;
                    cCharAt = cCharAt3;
                    i = i12;
                } else {
                    j = cCharAt3 - '0';
                    while (true) {
                        i2 = i3 + 1;
                        cCharAt2 = charAt(this.bp + i3);
                        if (cCharAt2 < '0' || cCharAt2 > c2) {
                            break;
                        }
                        j = (j * 10) + ((long) (cCharAt2 - '0'));
                        i3 = i2;
                        c2 = '9';
                    }
                    cCharAt = cCharAt2;
                    i = i2;
                }
                if (j < 0) {
                    this.matchStat = -1;
                    return null;
                }
                if (z) {
                    j = -j;
                }
                date = new Date(j);
            } else if (cCharAt3 == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 2) == 'l' && charAt(this.bp + 3) == 'l') {
                i = 5;
                this.matchStat = 5;
                cCharAt = charAt(this.bp + 4);
                date = null;
            } else {
                this.matchStat = -1;
                return null;
            }
        }
        if (cCharAt == ',') {
            int i13 = this.bp + i;
            this.bp = i13;
            this.ch = charAt(i13);
            this.matchStat = 3;
            this.token = 16;
            return date;
        }
        if (cCharAt == ']') {
            int i14 = i + 1;
            char cCharAt4 = charAt(this.bp + i);
            if (cCharAt4 == ',') {
                this.token = 16;
                int i15 = this.bp + i14;
                this.bp = i15;
                this.ch = charAt(i15);
            } else if (cCharAt4 == ']') {
                this.token = 15;
                int i16 = this.bp + i14;
                this.bp = i16;
                this.ch = charAt(i16);
            } else if (cCharAt4 == '}') {
                this.token = 13;
                int i17 = this.bp + i14;
                this.bp = i17;
                this.ch = charAt(i17);
            } else if (cCharAt4 == 26) {
                this.token = 20;
                this.bp += i;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return date;
        }
        this.matchStat = -1;
        return null;
    }

    public UUID scanFieldUUID(char[] cArr) {
        char cCharAt;
        int i;
        UUID uuid;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i9 = length + 1;
        char cCharAt2 = charAt(this.bp + length);
        char c = 4;
        if (cCharAt2 == '\"') {
            int iIndexOf = indexOf(Typography.quote, this.bp + cArr.length + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int length2 = this.bp + cArr.length + 1;
            int i10 = iIndexOf - length2;
            char c2 = 'f';
            char c3 = 'A';
            char c4 = '9';
            char c5 = 'a';
            if (i10 == 36) {
                int i11 = 0;
                long j = 0;
                while (i11 < 8) {
                    char cCharAt3 = charAt(length2 + i11);
                    if (cCharAt3 >= '0' && cCharAt3 <= '9') {
                        i8 = cCharAt3 - '0';
                    } else if (cCharAt3 >= 'a' && cCharAt3 <= 'f') {
                        i8 = cCharAt3 - 'W';
                    } else {
                        if (cCharAt3 < 'A' || cCharAt3 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i8 = cCharAt3 - '7';
                    }
                    j = (j << c) | ((long) i8);
                    i11++;
                    c = 4;
                }
                for (int i12 = 9; i12 < 13; i12++) {
                    char cCharAt4 = charAt(length2 + i12);
                    if (cCharAt4 >= '0' && cCharAt4 <= '9') {
                        i7 = cCharAt4 - '0';
                    } else if (cCharAt4 >= 'a' && cCharAt4 <= 'f') {
                        i7 = cCharAt4 - 'W';
                    } else {
                        if (cCharAt4 < 'A' || cCharAt4 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i7 = cCharAt4 - '7';
                    }
                    j = (j << 4) | ((long) i7);
                }
                int i13 = 14;
                long j2 = j;
                while (i13 < 18) {
                    char cCharAt5 = charAt(length2 + i13);
                    if (cCharAt5 >= '0' && cCharAt5 <= '9') {
                        i6 = cCharAt5 - '0';
                    } else if (cCharAt5 >= 'a' && cCharAt5 <= c2) {
                        i6 = cCharAt5 - 'W';
                    } else {
                        if (cCharAt5 < 'A' || cCharAt5 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i6 = cCharAt5 - '7';
                    }
                    j2 = (j2 << 4) | ((long) i6);
                    i13++;
                    iIndexOf = iIndexOf;
                    c2 = 'f';
                }
                int i14 = iIndexOf;
                int i15 = 19;
                long j3 = 0;
                while (i15 < 23) {
                    char cCharAt6 = charAt(length2 + i15);
                    if (cCharAt6 >= '0' && cCharAt6 <= '9') {
                        i5 = cCharAt6 - '0';
                    } else if (cCharAt6 >= c5 && cCharAt6 <= 'f') {
                        i5 = cCharAt6 - 'W';
                    } else {
                        if (cCharAt6 < 'A' || cCharAt6 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i5 = cCharAt6 - '7';
                    }
                    j3 = (j3 << 4) | ((long) i5);
                    i15++;
                    j2 = j2;
                    c5 = 'a';
                }
                long j4 = j2;
                int i16 = 24;
                long j5 = j3;
                while (i16 < 36) {
                    char cCharAt7 = charAt(length2 + i16);
                    if (cCharAt7 >= '0' && cCharAt7 <= c4) {
                        i4 = cCharAt7 - '0';
                    } else if (cCharAt7 >= 'a' && cCharAt7 <= 'f') {
                        i4 = cCharAt7 - 'W';
                    } else {
                        if (cCharAt7 < c3 || cCharAt7 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i4 = cCharAt7 - '7';
                    }
                    j5 = (j5 << 4) | ((long) i4);
                    i16++;
                    c3 = 'A';
                    c4 = '9';
                }
                uuid = new UUID(j4, j5);
                int i17 = this.bp;
                int length3 = i9 + (i14 - ((cArr.length + i17) + 1)) + 1;
                i = length3 + 1;
                cCharAt = charAt(i17 + length3);
            } else {
                if (i10 == 32) {
                    int i18 = 0;
                    long j6 = 0;
                    for (int i19 = 16; i18 < i19; i19 = 16) {
                        char cCharAt8 = charAt(length2 + i18);
                        if (cCharAt8 >= '0' && cCharAt8 <= '9') {
                            i3 = cCharAt8 - '0';
                        } else if (cCharAt8 >= 'a' && cCharAt8 <= 'f') {
                            i3 = cCharAt8 - 'W';
                        } else {
                            if (cCharAt8 < 'A' || cCharAt8 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i3 = cCharAt8 - '7';
                        }
                        j6 = (j6 << 4) | ((long) i3);
                        i18++;
                    }
                    int i20 = 16;
                    long j7 = 0;
                    for (int i21 = 32; i20 < i21; i21 = 32) {
                        char cCharAt9 = charAt(length2 + i20);
                        if (cCharAt9 < '0' || cCharAt9 > '9') {
                            if (cCharAt9 >= 'a' && cCharAt9 <= 'f') {
                                i2 = cCharAt9 - 'W';
                            } else {
                                if (cCharAt9 < 'A' || cCharAt9 > 'F') {
                                    this.matchStat = -2;
                                    return null;
                                }
                                i2 = cCharAt9 - '7';
                            }
                            j7 = (j7 << 4) | ((long) i2);
                            i20++;
                        } else {
                            i2 = cCharAt9 - '0';
                        }
                        j7 = (j7 << 4) | ((long) i2);
                        i20++;
                    }
                    uuid = new UUID(j6, j7);
                    int i22 = this.bp;
                    int length4 = i9 + (iIndexOf - ((cArr.length + i22) + 1)) + 1;
                    i = length4 + 1;
                    cCharAt = charAt(i22 + length4);
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
        } else {
            if (cCharAt2 == 'n') {
                int i23 = length + 2;
                if (charAt(this.bp + i9) == 'u') {
                    int i24 = length + 3;
                    if (charAt(this.bp + i23) == 'l') {
                        int i25 = length + 4;
                        if (charAt(this.bp + i24) == 'l') {
                            int i26 = length + 5;
                            cCharAt = charAt(this.bp + i25);
                            i = i26;
                            uuid = null;
                        }
                    }
                }
            }
            this.matchStat = -1;
            return null;
        }
        if (cCharAt == ',') {
            int i27 = this.bp + i;
            this.bp = i27;
            this.ch = charAt(i27);
            this.matchStat = 3;
            return uuid;
        }
        if (cCharAt == '}') {
            int i28 = i + 1;
            char cCharAt10 = charAt(this.bp + i);
            if (cCharAt10 == ',') {
                this.token = 16;
                int i29 = this.bp + i28;
                this.bp = i29;
                this.ch = charAt(i29);
            } else if (cCharAt10 == ']') {
                this.token = 15;
                int i30 = this.bp + i28;
                this.bp = i30;
                this.ch = charAt(i30);
            } else if (cCharAt10 == '}') {
                this.token = 13;
                int i31 = this.bp + i28;
                this.bp = i31;
                this.ch = charAt(i31);
            } else if (cCharAt10 == 26) {
                this.token = 20;
                this.bp += i;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return uuid;
        }
        this.matchStat = -1;
        return null;
    }

    public UUID scanUUID(char c) {
        char cCharAt;
        int i;
        UUID uuid;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        this.matchStat = 0;
        char cCharAt2 = charAt(this.bp);
        if (cCharAt2 == '\"') {
            int iIndexOf = indexOf(Typography.quote, this.bp + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int i9 = this.bp + 1;
            int i10 = iIndexOf - i9;
            char c2 = 'F';
            char c3 = 'A';
            char c4 = 'a';
            char c5 = '0';
            if (i10 == 36) {
                int i11 = 0;
                long j = 0;
                while (i11 < 8) {
                    char cCharAt3 = charAt(i9 + i11);
                    if (cCharAt3 >= '0' && cCharAt3 <= '9') {
                        i8 = cCharAt3 - '0';
                    } else if (cCharAt3 >= 'a' && cCharAt3 <= 'f') {
                        i8 = cCharAt3 - 'W';
                    } else {
                        if (cCharAt3 < c3 || cCharAt3 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i8 = cCharAt3 - '7';
                    }
                    j = (j << 4) | ((long) i8);
                    i11++;
                    iIndexOf = iIndexOf;
                    c3 = 'A';
                }
                int i12 = iIndexOf;
                for (int i13 = 9; i13 < 13; i13++) {
                    char cCharAt4 = charAt(i9 + i13);
                    if (cCharAt4 >= '0' && cCharAt4 <= '9') {
                        i7 = cCharAt4 - '0';
                    } else if (cCharAt4 >= 'a' && cCharAt4 <= 'f') {
                        i7 = cCharAt4 - 'W';
                    } else {
                        if (cCharAt4 < 'A' || cCharAt4 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i7 = cCharAt4 - '7';
                    }
                    j = (j << 4) | ((long) i7);
                }
                int i14 = 14;
                long j2 = j;
                while (i14 < 18) {
                    char cCharAt5 = charAt(i9 + i14);
                    if (cCharAt5 >= '0' && cCharAt5 <= '9') {
                        i6 = cCharAt5 - '0';
                    } else if (cCharAt5 >= c4 && cCharAt5 <= 'f') {
                        i6 = cCharAt5 - 'W';
                    } else {
                        if (cCharAt5 < 'A' || cCharAt5 > c2) {
                            this.matchStat = -2;
                            return null;
                        }
                        i6 = cCharAt5 - '7';
                    }
                    j2 = (j2 << 4) | ((long) i6);
                    i14++;
                    c4 = 'a';
                    c2 = 'F';
                }
                int i15 = 19;
                long j3 = 0;
                while (i15 < 23) {
                    char cCharAt6 = charAt(i9 + i15);
                    if (cCharAt6 >= c5 && cCharAt6 <= '9') {
                        i5 = cCharAt6 - '0';
                    } else if (cCharAt6 >= 'a' && cCharAt6 <= 'f') {
                        i5 = cCharAt6 - 'W';
                    } else {
                        if (cCharAt6 < 'A' || cCharAt6 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i5 = cCharAt6 - '7';
                    }
                    j3 = (j3 << 4) | ((long) i5);
                    i15++;
                    c5 = '0';
                }
                long j4 = j3;
                for (int i16 = 24; i16 < 36; i16++) {
                    char cCharAt7 = charAt(i9 + i16);
                    if (cCharAt7 >= '0' && cCharAt7 <= '9') {
                        i4 = cCharAt7 - '0';
                    } else if (cCharAt7 >= 'a' && cCharAt7 <= 'f') {
                        i4 = cCharAt7 - 'W';
                    } else {
                        if (cCharAt7 < 'A' || cCharAt7 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i4 = cCharAt7 - '7';
                    }
                    j4 = (j4 << 4) | ((long) i4);
                }
                uuid = new UUID(j2, j4);
                int i17 = this.bp;
                int i18 = i12 - (i17 + 1);
                int i19 = i18 + 2;
                i = i18 + 3;
                cCharAt = charAt(i17 + i19);
            } else {
                if (i10 == 32) {
                    int i20 = 0;
                    long j5 = 0;
                    for (int i21 = 16; i20 < i21; i21 = 16) {
                        char cCharAt8 = charAt(i9 + i20);
                        if (cCharAt8 >= '0' && cCharAt8 <= '9') {
                            i3 = cCharAt8 - '0';
                        } else if (cCharAt8 >= 'a' && cCharAt8 <= 'f') {
                            i3 = cCharAt8 - 'W';
                        } else {
                            if (cCharAt8 < 'A' || cCharAt8 > 'F') {
                                this.matchStat = -2;
                                return null;
                            }
                            i3 = cCharAt8 - '7';
                        }
                        j5 = (j5 << 4) | ((long) i3);
                        i20++;
                    }
                    int i22 = 16;
                    long j6 = 0;
                    for (int i23 = 32; i22 < i23; i23 = 32) {
                        char cCharAt9 = charAt(i9 + i22);
                        if (cCharAt9 < '0' || cCharAt9 > '9') {
                            if (cCharAt9 >= 'a' && cCharAt9 <= 'f') {
                                i2 = cCharAt9 - 'W';
                            } else {
                                if (cCharAt9 < 'A' || cCharAt9 > 'F') {
                                    this.matchStat = -2;
                                    return null;
                                }
                                i2 = cCharAt9 - '7';
                            }
                            j6 = (j6 << 4) | ((long) i2);
                            i22++;
                        } else {
                            i2 = cCharAt9 - '0';
                        }
                        j6 = (j6 << 4) | ((long) i2);
                        i22++;
                    }
                    uuid = new UUID(j5, j6);
                    int i24 = this.bp;
                    int i25 = iIndexOf - (i24 + 1);
                    int i26 = i25 + 2;
                    i = i25 + 3;
                    cCharAt = charAt(i24 + i26);
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
        } else if (cCharAt2 == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 2) == 'l' && charAt(this.bp + 3) == 'l') {
            cCharAt = charAt(this.bp + 4);
            i = 5;
            uuid = null;
        } else {
            this.matchStat = -1;
            return null;
        }
        if (cCharAt == ',') {
            int i27 = this.bp + i;
            this.bp = i27;
            this.ch = charAt(i27);
            this.matchStat = 3;
            return uuid;
        }
        if (cCharAt == ']') {
            int i28 = i + 1;
            char cCharAt10 = charAt(this.bp + i);
            if (cCharAt10 == ',') {
                this.token = 16;
                int i29 = this.bp + i28;
                this.bp = i29;
                this.ch = charAt(i29);
            } else if (cCharAt10 == ']') {
                this.token = 15;
                int i30 = this.bp + i28;
                this.bp = i30;
                this.ch = charAt(i30);
            } else if (cCharAt10 == '}') {
                this.token = 13;
                int i31 = this.bp + i28;
                this.bp = i31;
                this.ch = charAt(i31);
            } else if (cCharAt10 == 26) {
                this.token = 20;
                this.bp += i;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return uuid;
        }
        this.matchStat = -1;
        return null;
    }

    public final void scanTrue() {
        if (this.ch != 't') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'r') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'u') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse true");
        }
        next();
        char c = this.ch;
        if (c == ' ' || c == ',' || c == '}' || c == ']' || c == '\n' || c == '\r' || c == '\t' || c == 26 || c == '\f' || c == '\b' || c == ':' || c == '/') {
            this.token = 6;
            return;
        }
        throw new JSONException("scan true error");
    }

    public final void scanNullOrNew() {
        if (this.ch != 'n') {
            throw new JSONException("error parse null or new");
        }
        next();
        char c = this.ch;
        if (c != 'u') {
            if (c != 'e') {
                throw new JSONException("error parse new");
            }
            next();
            if (this.ch != 'w') {
                throw new JSONException("error parse new");
            }
            next();
            char c2 = this.ch;
            if (c2 == ' ' || c2 == ',' || c2 == '}' || c2 == ']' || c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == 26 || c2 == '\f' || c2 == '\b') {
                this.token = 9;
                return;
            }
            throw new JSONException("scan new error");
        }
        next();
        if (this.ch != 'l') {
            throw new JSONException("error parse null");
        }
        next();
        if (this.ch != 'l') {
            throw new JSONException("error parse null");
        }
        next();
        char c3 = this.ch;
        if (c3 == ' ' || c3 == ',' || c3 == '}' || c3 == ']' || c3 == '\n' || c3 == '\r' || c3 == '\t' || c3 == 26 || c3 == '\f' || c3 == '\b') {
            this.token = 8;
            return;
        }
        throw new JSONException("scan null error");
    }

    public final void scanFalse() {
        if (this.ch != 'f') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'a') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'l') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 's') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse false");
        }
        next();
        char c = this.ch;
        if (c == ' ' || c == ',' || c == '}' || c == ']' || c == '\n' || c == '\r' || c == '\t' || c == 26 || c == '\f' || c == '\b' || c == ':' || c == '/') {
            this.token = 7;
            return;
        }
        throw new JSONException("scan false error");
    }

    public final void scanIdent() {
        this.np = this.bp - 1;
        this.hasSpecial = false;
        do {
            this.sp++;
            next();
        } while (Character.isLetterOrDigit(this.ch));
        String strStringVal = stringVal();
        if ("null".equalsIgnoreCase(strStringVal)) {
            this.token = 8;
            return;
        }
        if ("new".equals(strStringVal)) {
            this.token = 9;
            return;
        }
        if ("true".equals(strStringVal)) {
            this.token = 6;
            return;
        }
        if ("false".equals(strStringVal)) {
            this.token = 7;
            return;
        }
        if ("undefined".equals(strStringVal)) {
            this.token = 23;
            return;
        }
        if ("Set".equals(strStringVal)) {
            this.token = 21;
        } else if ("TreeSet".equals(strStringVal)) {
            this.token = 22;
        } else {
            this.token = 18;
        }
    }

    /* JADX WARN: Code duplicated, block: B:47:0x00f0  */
    public static String readString(char[] cArr, int i) {
        int i2;
        int i3;
        char[] cArr2 = new char[i];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            char c = cArr[i4];
            if (c != '\\') {
                cArr2[i5] = c;
                i5++;
            } else {
                int i6 = i4 + 1;
                char c2 = cArr[i6];
                if (c2 == '\"') {
                    i2 = i5 + 1;
                    cArr2[i5] = Typography.quote;
                } else if (c2 == '\'') {
                    i2 = i5 + 1;
                    cArr2[i5] = '\'';
                } else if (c2 == 'F') {
                    i2 = i5 + 1;
                    cArr2[i5] = '\f';
                } else if (c2 == '\\') {
                    i2 = i5 + 1;
                    cArr2[i5] = '\\';
                } else if (c2 == 'b') {
                    i2 = i5 + 1;
                    cArr2[i5] = '\b';
                } else if (c2 == 'f') {
                    i2 = i5 + 1;
                    cArr2[i5] = '\f';
                } else if (c2 == 'n') {
                    i2 = i5 + 1;
                    cArr2[i5] = '\n';
                } else if (c2 != 'r') {
                    if (c2 != 'x') {
                        switch (c2) {
                            case '/':
                                i2 = i5 + 1;
                                cArr2[i5] = '/';
                                break;
                            case '0':
                                i2 = i5 + 1;
                                cArr2[i5] = 0;
                                break;
                            case '1':
                                i2 = i5 + 1;
                                cArr2[i5] = 1;
                                break;
                            case '2':
                                i2 = i5 + 1;
                                cArr2[i5] = 2;
                                break;
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                                i2 = i5 + 1;
                                cArr2[i5] = 3;
                                break;
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                                i2 = i5 + 1;
                                cArr2[i5] = 4;
                                break;
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                                i2 = i5 + 1;
                                cArr2[i5] = 5;
                                break;
                            case '6':
                                i2 = i5 + 1;
                                cArr2[i5] = 6;
                                break;
                            case '7':
                                i2 = i5 + 1;
                                cArr2[i5] = 7;
                                break;
                            default:
                                switch (c2) {
                                    case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /* 116 */:
                                        i2 = i5 + 1;
                                        cArr2[i5] = '\t';
                                        break;
                                    case 'u':
                                        i3 = i5 + 1;
                                        char c3 = cArr[i4 + 2];
                                        char c4 = cArr[i4 + 3];
                                        char c5 = cArr[i4 + 4];
                                        i4 += 5;
                                        cArr2[i5] = (char) Integer.parseInt(new String(new char[]{c3, c4, c5, cArr[i4]}), 16);
                                        break;
                                    case 'v':
                                        i2 = i5 + 1;
                                        cArr2[i5] = 11;
                                        break;
                                    default:
                                        throw new JSONException("unclosed.str.lit");
                                }
                                break;
                        }
                    } else {
                        i3 = i5 + 1;
                        int[] iArr = digits;
                        int i7 = iArr[cArr[i4 + 2]] * 16;
                        i4 += 3;
                        cArr2[i5] = (char) (i7 + iArr[cArr[i4]]);
                    }
                    i5 = i3;
                } else {
                    i2 = i5 + 1;
                    cArr2[i5] = '\r';
                }
                i5 = i2;
                i4 = i6;
            }
            i4++;
        }
        return new String(cArr2, 0, i5);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public boolean isBlankInput() {
        int i = 0;
        while (true) {
            char cCharAt = charAt(i);
            if (cCharAt == 26) {
                this.token = 20;
                return true;
            }
            if (!isWhitespace(cCharAt)) {
                return false;
            }
            i++;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void skipWhitespace() {
        while (true) {
            char c = this.ch;
            if (c > '/') {
                return;
            }
            if (c == ' ' || c == '\r' || c == '\n' || c == '\t' || c == '\f' || c == '\b') {
                next();
            } else if (c != '/') {
                return;
            } else {
                skipComment();
            }
        }
    }

    private void scanStringSingleQuote() {
        this.np = this.bp;
        this.hasSpecial = false;
        while (true) {
            char next = next();
            if (next == '\'') {
                this.token = 4;
                next();
                return;
            }
            if (next == 26) {
                if (!isEOF()) {
                    putChar(JSONLexer.EOI);
                } else {
                    throw new JSONException("unclosed single-quote string");
                }
            } else if (next == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    int i = this.sp;
                    char[] cArr = this.sbuf;
                    if (i > cArr.length) {
                        char[] cArr2 = new char[i * 2];
                        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                        this.sbuf = cArr2;
                    }
                    copyTo(this.np + 1, this.sp, this.sbuf);
                }
                char next2 = next();
                if (next2 == '\"') {
                    putChar(Typography.quote);
                } else if (next2 != '\'') {
                    if (next2 != 'F') {
                        if (next2 == '\\') {
                            putChar('\\');
                        } else if (next2 == 'b') {
                            putChar('\b');
                        } else if (next2 != 'f') {
                            if (next2 == 'n') {
                                putChar('\n');
                            } else if (next2 == 'r') {
                                putChar('\r');
                            } else if (next2 != 'x') {
                                switch (next2) {
                                    case '/':
                                        putChar('/');
                                        break;
                                    case '0':
                                        putChar((char) 0);
                                        break;
                                    case '1':
                                        putChar((char) 1);
                                        break;
                                    case '2':
                                        putChar((char) 2);
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                                        putChar((char) 3);
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                                        putChar((char) 4);
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                                        putChar((char) 5);
                                        break;
                                    case '6':
                                        putChar((char) 6);
                                        break;
                                    case '7':
                                        putChar((char) 7);
                                        break;
                                    default:
                                        switch (next2) {
                                            case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /* 116 */:
                                                putChar('\t');
                                                break;
                                            case 'u':
                                                putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                                break;
                                            case 'v':
                                                putChar((char) 11);
                                                break;
                                            default:
                                                this.ch = next2;
                                                throw new JSONException("unclosed single-quote string");
                                        }
                                        break;
                                }
                            } else {
                                int[] iArr = digits;
                                putChar((char) ((iArr[next()] * 16) + iArr[next()]));
                            }
                        }
                    }
                    putChar('\f');
                } else {
                    putChar('\'');
                }
            } else if (!this.hasSpecial) {
                this.sp++;
            } else {
                int i2 = this.sp;
                char[] cArr3 = this.sbuf;
                if (i2 == cArr3.length) {
                    putChar(next);
                } else {
                    this.sp = i2 + 1;
                    cArr3[i2] = next;
                }
            }
        }
    }

    protected final void putChar(char c) {
        int i = this.sp;
        char[] cArr = this.sbuf;
        if (i == cArr.length) {
            char[] cArr2 = new char[cArr.length * 2];
            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
            this.sbuf = cArr2;
        }
        char[] cArr3 = this.sbuf;
        int i2 = this.sp;
        this.sp = i2 + 1;
        cArr3[i2] = c;
    }

    public final void scanHex() {
        char next;
        if (this.ch != 'x') {
            throw new JSONException("illegal state. " + this.ch);
        }
        next();
        if (this.ch != '\'') {
            throw new JSONException("illegal state. " + this.ch);
        }
        this.np = this.bp;
        next();
        while (true) {
            next = next();
            if ((next < '0' || next > '9') && (next < 'A' || next > 'F')) {
                break;
            } else {
                this.sp++;
            }
        }
        if (next == '\'') {
            this.sp++;
            next();
            this.token = 26;
        } else {
            throw new JSONException("illegal state. " + next);
        }
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:52:0x00cb  */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void scanNumber() {
        char c;
        boolean z;
        char c2;
        this.np = this.bp;
        boolean z2 = true;
        if (this.ch == '-') {
            this.sp++;
            next();
        }
        while (true) {
            c = this.ch;
            if (c < '0' || c > '9') {
                break;
            }
            this.sp++;
            next();
        }
        if (c == '.') {
            this.sp++;
            next();
            while (true) {
                char c3 = this.ch;
                if (c3 < '0' || c3 > '9') {
                    break;
                }
                this.sp++;
                next();
            }
            z = true;
        } else {
            z = false;
        }
        char c4 = this.ch;
        if (c4 != 'L' && c4 != 'S' && c4 != 'B') {
            if (c4 == 'F' || c4 == 'D') {
                this.sp++;
                next();
            } else if (c4 == 'e' || c4 == 'E') {
                this.sp++;
                next();
                char c5 = this.ch;
                if (c5 == '+' || c5 == '-') {
                    this.sp++;
                    next();
                }
                while (true) {
                    c2 = this.ch;
                    if (c2 < '0' || c2 > '9') {
                        break;
                    }
                    this.sp++;
                    next();
                }
                if (c2 == 'D' || c2 == 'F') {
                    this.sp++;
                    next();
                }
            }
            if (z2) {
                this.token = 3;
            } else {
                this.token = 2;
            }
        }
        this.sp++;
        next();
        z2 = z;
        if (z2) {
            this.token = 3;
        } else {
            this.token = 2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0034  */
    /* JADX WARN: Code duplicated, block: B:32:0x0076  */
    /* JADX WARN: Code duplicated, block: B:34:0x007b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:35:0x007c  */
    /* JADX WARN: Code duplicated, block: B:37:0x0086  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x005d -> B:11:0x002e). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final long longValue() throws NumberFormatException {
        long j;
        long j2;
        int i;
        char cCharAt;
        boolean z = false;
        if (this.np == -1) {
            this.np = 0;
        }
        int i2 = this.np;
        int i3 = this.sp + i2;
        if (charAt(i2) == '-') {
            i2++;
            j = Long.MIN_VALUE;
            z = true;
        } else {
            j = C.TIME_UNSET;
        }
        if (i2 >= i3) {
            j2 = 0;
            if (i2 < i3) {
                i = i2 + 1;
                cCharAt = charAt(i2);
                if (cCharAt != 'L' || cCharAt == 'S' || cCharAt == 'B') {
                    i2 = i;
                } else {
                    int i4 = cCharAt - '0';
                    if (j2 < -922337203685477580L) {
                        throw new NumberFormatException(numberString());
                    }
                    long j3 = j2 * 10;
                    long j4 = i4;
                    if (j3 < j + j4) {
                        throw new NumberFormatException(numberString());
                    }
                    j2 = j3 - j4;
                }
            }
            if (z) {
                return -j2;
            }
            if (i2 > this.np + 1) {
                return j2;
            }
            throw new NumberFormatException(numberString());
        }
        i = i2 + 1;
        j2 = -(charAt(i2) - '0');
        i2 = i;
        if (i2 < i3) {
            i = i2 + 1;
            cCharAt = charAt(i2);
            if (cCharAt != 'L') {
            }
            i2 = i;
        }
        if (z) {
            return -j2;
        }
        if (i2 > this.np + 1) {
            return j2;
        }
        throw new NumberFormatException(numberString());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final Number decimalValue(boolean z) {
        char cCharAt = charAt((this.np + this.sp) - 1);
        try {
            if (cCharAt == 'F') {
                return Float.valueOf(Float.parseFloat(numberString()));
            }
            if (cCharAt == 'D') {
                return Double.valueOf(Double.parseDouble(numberString()));
            }
            if (z) {
                return decimalValue();
            }
            return Double.valueOf(doubleValue());
        } catch (NumberFormatException e) {
            throw new JSONException(e.getMessage() + ", " + info());
        }
    }

    public String[] scanFieldStringArray(char[] cArr, int i, SymbolTable symbolTable) {
        throw new UnsupportedOperationException();
    }

    public boolean matchField2(char[] cArr) {
        throw new UnsupportedOperationException();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public int getFeatures() {
        return this.features;
    }
}

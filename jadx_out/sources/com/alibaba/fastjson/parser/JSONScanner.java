package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.IOUtils;
import j$.util.DesugarTimeZone;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;
import kotlin.text.Typography;
import org.joda.time.DateTimeConstants;

/* JADX INFO: loaded from: classes4.dex */
public final class JSONScanner extends JSONLexerBase {
    private final int len;
    private final String text;

    static boolean checkDate(char c, char c2, char c3, char c4, char c5, char c6, int i, int i2) {
        if (c >= '1' && c <= '3' && c2 >= '0' && c2 <= '9' && c3 >= '0' && c3 <= '9' && c4 >= '0' && c4 <= '9') {
            if (c5 == '0') {
                if (c6 < '1' || c6 > '9') {
                    return false;
                }
            } else if (c5 != '1' || (c6 != '0' && c6 != '1' && c6 != '2')) {
                return false;
            }
            if (i == 48) {
                return i2 >= 49 && i2 <= 57;
            }
            if (i != 49 && i != 50) {
                return i == 51 && (i2 == 48 || i2 == 49);
            }
            if (i2 >= 48 && i2 <= 57) {
                return true;
            }
        }
        return false;
    }

    private boolean checkTime(char c, char c2, char c3, char c4, char c5, char c6) {
        if (c == '0') {
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        } else {
            if (c != '1') {
                if (c == '2' && c2 >= '0' && c2 <= '4') {
                }
                return false;
            }
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        }
        if (c3 < '0' || c3 > '5') {
            if (c3 != '6' || c4 != '0') {
                return false;
            }
        } else if (c4 < '0' || c4 > '9') {
            return false;
        }
        if (c5 < '0' || c5 > '5') {
            return c5 == '6' && c6 == '0';
        }
        return c6 >= '0' && c6 <= '9';
    }

    public JSONScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(String str, int i) {
        super(i);
        this.text = str;
        this.len = str.length();
        this.bp = -1;
        next();
        if (this.ch == 65279) {
            next();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char charAt(int i) {
        return i >= this.len ? JSONLexer.EOI : this.text.charAt(i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final char next() {
        int i = this.bp + 1;
        this.bp = i;
        char cCharAt = i >= this.len ? JSONLexer.EOI : this.text.charAt(i);
        this.ch = cCharAt;
        return cCharAt;
    }

    public JSONScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    protected final void copyTo(int i, int i2, char[] cArr) {
        this.text.getChars(i, i2 + i, cArr, 0);
    }

    static boolean charArrayCompare(String str, int i, char[] cArr) {
        int length = cArr.length;
        if (length + i > str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (cArr[i2] != str.charAt(i + i2)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final boolean charArrayCompare(char[] cArr) {
        return charArrayCompare(this.text, this.bp, cArr);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final int indexOf(char c, int i) {
        return this.text.indexOf(c, i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.text, i, i2, i3);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public byte[] bytesValue() {
        if (this.token == 26) {
            int i = this.np + 1;
            int i2 = this.sp;
            if (i2 % 2 != 0) {
                throw new JSONException("illegal state. " + i2);
            }
            int i3 = i2 / 2;
            byte[] bArr = new byte[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = (i4 * 2) + i;
                char cCharAt = this.text.charAt(i5);
                char cCharAt2 = this.text.charAt(i5 + 1);
                char c = '0';
                int i6 = cCharAt - (cCharAt <= '9' ? '0' : '7');
                if (cCharAt2 > '9') {
                    c = '7';
                }
                bArr[i4] = (byte) ((i6 << 4) | (cCharAt2 - c));
            }
            return bArr;
        }
        return IOUtils.decodeBase64(this.text, this.np + 1, this.sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String stringVal() {
        if (!this.hasSpecial) {
            return subString(this.np + 1, this.sp);
        }
        return new String(this.sbuf, 0, this.sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String subString(int i, int i2) {
        if (ASMUtils.IS_ANDROID) {
            if (i2 < this.sbuf.length) {
                this.text.getChars(i, i + i2, this.sbuf, 0);
                return new String(this.sbuf, 0, i2);
            }
            char[] cArr = new char[i2];
            this.text.getChars(i, i2 + i, cArr, 0);
            return new String(cArr);
        }
        return this.text.substring(i, i2 + i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char[] sub_chars(int i, int i2) {
        if (ASMUtils.IS_ANDROID && i2 < this.sbuf.length) {
            this.text.getChars(i, i2 + i, this.sbuf, 0);
            return this.sbuf;
        }
        char[] cArr = new char[i2];
        this.text.getChars(i, i2 + i, cArr, 0);
        return cArr;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String numberString() {
        char cCharAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i--;
        }
        return subString(this.np, i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final BigDecimal decimalValue() {
        char cCharAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i--;
        }
        int i2 = this.np;
        if (i < this.sbuf.length) {
            this.text.getChars(i2, i2 + i, this.sbuf, 0);
            return new BigDecimal(this.sbuf, 0, i);
        }
        char[] cArr = new char[i];
        this.text.getChars(i2, i + i2, cArr, 0);
        return new BigDecimal(cArr);
    }

    public boolean scanISO8601DateIfMatch() {
        return scanISO8601DateIfMatch(true);
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        return scanISO8601DateIfMatch(z, this.len - this.bp);
    }

    /* JADX WARN: Code duplicated, block: B:122:0x01f0 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:124:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:171:0x0316 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:173:0x0318  */
    /* JADX WARN: Code duplicated, block: B:175:0x0335  */
    /* JADX WARN: Code duplicated, block: B:190:0x0365  */
    /* JADX WARN: Code duplicated, block: B:196:0x037d  */
    /* JADX WARN: Code duplicated, block: B:210:0x03c1  */
    /* JADX WARN: Code duplicated, block: B:212:0x03d1  */
    /* JADX WARN: Code duplicated, block: B:251:0x0471 A[ADDED_TO_REGION, ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:255:0x0475  */
    private boolean scanISO8601DateIfMatch(boolean z, int i) {
        char cCharAt;
        char c;
        char c2;
        char c3;
        int i2;
        int i3;
        int i4;
        int i5;
        char c4;
        char c5;
        char cCharAt2;
        char c6;
        char c7;
        int i6;
        char c8;
        int i7;
        char c9;
        char cCharAt3;
        int i8;
        char cCharAt4;
        char cCharAt5;
        char cCharAt6;
        char cCharAt7;
        char cCharAt8;
        char cCharAt9;
        char cCharAt10;
        int i9;
        char cCharAt11;
        int i10;
        int i11;
        char cCharAt12;
        char cCharAt13;
        char cCharAt14;
        char c10;
        char cCharAt15;
        char cCharAt16;
        char cCharAt17;
        char cCharAt18;
        char cCharAt19;
        if (i < 8) {
            return false;
        }
        char cCharAt20 = charAt(this.bp);
        char cCharAt21 = charAt(this.bp + 1);
        char cCharAt22 = charAt(this.bp + 2);
        int i12 = 3;
        char cCharAt23 = charAt(this.bp + 3);
        char cCharAt24 = charAt(this.bp + 4);
        char cCharAt25 = charAt(this.bp + 5);
        char cCharAt26 = charAt(this.bp + 6);
        char cCharAt27 = charAt(this.bp + 7);
        if (!z && i > 13) {
            char cCharAt28 = charAt((this.bp + i) - 1);
            char cCharAt29 = charAt((this.bp + i) - 2);
            if (cCharAt20 == '/' && cCharAt21 == 'D' && cCharAt22 == 'a' && cCharAt23 == 't' && cCharAt24 == 'e' && cCharAt25 == '(' && cCharAt28 == '/' && cCharAt29 == ')') {
                int i13 = -1;
                for (int i14 = 6; i14 < i; i14++) {
                    char cCharAt30 = charAt(this.bp + i14);
                    if (cCharAt30 != '+') {
                        if (cCharAt30 < '0' || cCharAt30 > '9') {
                            break;
                        }
                    } else {
                        i13 = i14;
                    }
                }
                if (i13 == -1) {
                    return false;
                }
                int i15 = this.bp + 6;
                long j = Long.parseLong(subString(i15, (this.bp + i13) - i15));
                this.calendar = Calendar.getInstance(this.timeZone, this.locale);
                this.calendar.setTimeInMillis(j);
                this.token = 5;
                return true;
            }
        }
        if (i == 8 || i == 14 || ((i == 16 && ((cCharAt19 = charAt(this.bp + 10)) == 'T' || cCharAt19 == ' ')) || (i == 17 && charAt(this.bp + 6) != '-'))) {
            if (z) {
                return false;
            }
            char cCharAt31 = charAt(this.bp + 8);
            boolean z2 = cCharAt24 == '-' && cCharAt27 == '-';
            boolean z3 = z2 && i == 16;
            boolean z4 = z2 && i == 17;
            if (z4 || z3) {
                cCharAt = charAt(this.bp + 9);
                c = cCharAt25;
                c2 = cCharAt26;
                c3 = cCharAt31;
            } else {
                c = cCharAt24;
                c2 = cCharAt25;
                c3 = cCharAt26;
                cCharAt = cCharAt27;
            }
            if (!checkDate(cCharAt20, cCharAt21, cCharAt22, cCharAt23, c, c2, c3, cCharAt)) {
                return false;
            }
            setCalendar(cCharAt20, cCharAt21, cCharAt22, cCharAt23, c, c2, c3, cCharAt);
            if (i != 8) {
                char cCharAt32 = charAt(this.bp + 9);
                char cCharAt33 = charAt(this.bp + 10);
                char cCharAt34 = charAt(this.bp + 11);
                char cCharAt35 = charAt(this.bp + 12);
                char cCharAt36 = charAt(this.bp + 13);
                if ((z4 && cCharAt33 == 'T' && cCharAt36 == ':' && charAt(this.bp + 16) == 'Z') || (z3 && ((cCharAt33 == ' ' || cCharAt33 == 'T') && cCharAt36 == ':'))) {
                    char cCharAt37 = charAt(this.bp + 14);
                    cCharAt2 = charAt(this.bp + 15);
                    c5 = cCharAt37;
                    cCharAt31 = cCharAt34;
                    c4 = cCharAt35;
                    c6 = '0';
                    c7 = '0';
                } else {
                    c4 = cCharAt32;
                    c5 = cCharAt33;
                    cCharAt2 = cCharAt34;
                    c6 = cCharAt35;
                    c7 = cCharAt36;
                }
                if (!checkTime(cCharAt31, c4, c5, cCharAt2, c6, c7)) {
                    return false;
                }
                if (i != 17 || z4) {
                    i6 = 0;
                } else {
                    char cCharAt38 = charAt(this.bp + 14);
                    char cCharAt39 = charAt(this.bp + 15);
                    char cCharAt40 = charAt(this.bp + 16);
                    if (cCharAt38 < '0' || cCharAt38 > '9' || cCharAt39 < '0' || cCharAt39 > '9' || cCharAt40 < '0' || cCharAt40 > '9') {
                        return false;
                    }
                    i6 = ((cCharAt38 - '0') * 100) + ((cCharAt39 - '0') * 10) + (cCharAt40 - '0');
                }
                i3 = ((c6 - '0') * 10) + (c7 - '0');
                i5 = ((cCharAt31 - '0') * 10) + (c4 - '0');
                i4 = i6;
                i2 = ((c5 - '0') * 10) + (cCharAt2 - '0');
            } else {
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
            }
            this.calendar.set(11, i5);
            this.calendar.set(12, i2);
            this.calendar.set(13, i3);
            this.calendar.set(14, i4);
            this.token = 5;
            return true;
        }
        if (i < 9) {
            return false;
        }
        char cCharAt41 = charAt(this.bp + 8);
        cCharAt41 = charAt(this.bp + 9);
        if ((cCharAt24 != '-' || cCharAt27 != '-') && (cCharAt24 != '/' || cCharAt27 != '/')) {
            if (cCharAt24 == '-' && cCharAt26 == '-') {
                if (cCharAt41 == ' ') {
                    cCharAt22 = cCharAt22;
                    c9 = cCharAt25;
                    cCharAt41 = cCharAt27;
                    cCharAt23 = cCharAt23;
                    cCharAt20 = cCharAt20;
                    cCharAt21 = cCharAt21;
                    cCharAt25 = '0';
                    c8 = '0';
                    i7 = 8;
                } else {
                    cCharAt25 = '0';
                    i7 = 9;
                    c9 = cCharAt25;
                    cCharAt21 = cCharAt21;
                    c8 = cCharAt27;
                    cCharAt23 = cCharAt23;
                }
            } else if ((cCharAt22 == '.' && cCharAt25 == '.') || (cCharAt22 == '-' && cCharAt25 == '-')) {
                cCharAt21 = cCharAt27;
                cCharAt23 = cCharAt41;
                cCharAt25 = cCharAt23;
                c8 = cCharAt20;
                cCharAt41 = cCharAt21;
                i7 = 10;
                c9 = cCharAt24;
                cCharAt20 = cCharAt26;
                cCharAt22 = cCharAt41;
            } else {
                if (cCharAt24 != 24180 && cCharAt24 != 45380) {
                    return false;
                }
                if (cCharAt27 == 26376 || cCharAt27 == 50900) {
                    if (cCharAt41 == 26085 || cCharAt41 == 51068) {
                        cCharAt41 = cCharAt41;
                        cCharAt23 = cCharAt23;
                        cCharAt20 = cCharAt20;
                        c8 = '0';
                        i7 = 10;
                    } else {
                        if (charAt(this.bp + 10) != 26085 && charAt(this.bp + 10) != 51068) {
                            return false;
                        }
                        i7 = 11;
                    }
                    c9 = cCharAt26;
                    cCharAt22 = cCharAt22;
                } else {
                    if (cCharAt26 != 26376 && cCharAt26 != 50900) {
                        return false;
                    }
                    if (cCharAt41 == 26085 || cCharAt41 == 51068) {
                        cCharAt22 = cCharAt22;
                        c9 = cCharAt25;
                        cCharAt41 = cCharAt27;
                        cCharAt23 = cCharAt23;
                        cCharAt20 = cCharAt20;
                        cCharAt21 = cCharAt21;
                        cCharAt25 = '0';
                        c8 = '0';
                        i7 = 10;
                    } else {
                        if (cCharAt41 != 26085 && cCharAt41 != 51068) {
                            return false;
                        }
                        cCharAt25 = '0';
                        i7 = 10;
                        c9 = cCharAt25;
                        cCharAt21 = cCharAt21;
                        c8 = cCharAt27;
                        cCharAt23 = cCharAt23;
                    }
                }
            }
            if (!checkDate(cCharAt20, cCharAt21, cCharAt22, cCharAt23, cCharAt25, c9, c8, cCharAt41)) {
                return false;
            }
            setCalendar(cCharAt20, cCharAt21, cCharAt22, cCharAt23, cCharAt25, c9, c8, cCharAt41);
            cCharAt3 = charAt(this.bp + i7);
            if (cCharAt3 == 'T' && (cCharAt3 != ' ' || z)) {
                if (cCharAt3 == '\"' || cCharAt3 == 26 || cCharAt3 == 26085 || cCharAt3 == 51068) {
                    this.calendar.set(11, 0);
                    this.calendar.set(12, 0);
                    this.calendar.set(13, 0);
                    this.calendar.set(14, 0);
                    int i16 = this.bp + i7;
                    this.bp = i16;
                    this.ch = charAt(i16);
                    this.token = 5;
                    return true;
                }
                if ((cCharAt3 != '+' && cCharAt3 != '-') || this.len != i7 + 6 || charAt(this.bp + i7 + 3) != ':' || charAt(this.bp + i7 + 4) != '0' || charAt(this.bp + i7 + 5) != '0') {
                    return false;
                }
                setTime('0', '0', '0', '0', '0', '0');
                this.calendar.set(14, 0);
                setTimeZone(cCharAt3, charAt(this.bp + i7 + 1), charAt(this.bp + i7 + 2));
                return true;
            }
            i8 = i7 + 9;
            if (i >= i8 || charAt(this.bp + i7 + 3) != ':' || charAt(this.bp + i7 + 6) != ':') {
                return false;
            }
            cCharAt4 = charAt(this.bp + i7 + 1);
            cCharAt5 = charAt(this.bp + i7 + 2);
            cCharAt6 = charAt(this.bp + i7 + 4);
            cCharAt7 = charAt(this.bp + i7 + 5);
            cCharAt8 = charAt(this.bp + i7 + 7);
            cCharAt9 = charAt(this.bp + i7 + 8);
            if (!checkTime(cCharAt4, cCharAt5, cCharAt6, cCharAt7, cCharAt8, cCharAt9)) {
                return false;
            }
            setTime(cCharAt4, cCharAt5, cCharAt6, cCharAt7, cCharAt8, cCharAt9);
            cCharAt10 = charAt(this.bp + i7 + 9);
            if (cCharAt10 == '.') {
                this.calendar.set(14, 0);
                int i17 = this.bp + i8;
                this.bp = i17;
                this.ch = charAt(i17);
                this.token = 5;
                if (cCharAt10 != 'Z' && this.calendar.getTimeZone().getRawOffset() != 0) {
                    String[] availableIDs = TimeZone.getAvailableIDs(0);
                    if (availableIDs.length <= 0) {
                        return true;
                    }
                    this.calendar.setTimeZone(DesugarTimeZone.getTimeZone(availableIDs[0]));
                    return true;
                }
            }
            i9 = i7 + 11;
            if (i >= i9 || (cCharAt11 = charAt(this.bp + i7 + 10)) < '0' || cCharAt11 > '9') {
                return false;
            }
            int i18 = cCharAt11 - '0';
            if (i > i9 || (cCharAt18 = charAt(this.bp + i7 + 11)) < '0' || cCharAt18 > '9') {
                i10 = 1;
            } else {
                i18 = (i18 * 10) + (cCharAt18 - '0');
                i10 = 2;
            }
            if (i10 == 2 || (cCharAt17 = charAt(this.bp + i7 + 12)) < '0' || cCharAt17 > '9') {
                i11 = i10;
            } else {
                i18 = (i18 * 10) + (cCharAt17 - '0');
                i11 = 3;
            }
            this.calendar.set(14, i18);
            cCharAt12 = charAt(this.bp + i7 + 10 + i11);
            if (cCharAt12 != '+' || cCharAt12 == '-') {
                cCharAt13 = charAt(this.bp + i7 + 10 + i11 + 1);
                if (cCharAt13 >= '0' || cCharAt13 > '1' || (cCharAt14 = charAt(this.bp + i7 + 10 + i11 + 2)) < '0' || cCharAt14 > '9') {
                    return false;
                }
                char cCharAt42 = charAt(this.bp + i7 + 10 + i11 + 3);
                if (cCharAt42 == ':') {
                    char cCharAt43 = charAt(this.bp + i7 + 10 + i11 + 4);
                    if ((cCharAt43 != '0' && cCharAt43 != '3') || (cCharAt15 = charAt(this.bp + i7 + 10 + i11 + 5)) != '0') {
                        return false;
                    }
                    c10 = cCharAt43;
                    i12 = 6;
                } else if (cCharAt42 == '0') {
                    char cCharAt44 = charAt(this.bp + i7 + 10 + i11 + 4);
                    if (cCharAt44 != '0' && cCharAt44 != '3') {
                        return false;
                    }
                    c10 = cCharAt44;
                    cCharAt15 = '0';
                    i12 = 5;
                } else {
                    c10 = '0';
                    cCharAt15 = '0';
                }
                setTimeZone(cCharAt12, cCharAt13, cCharAt14, c10, cCharAt15);
            } else if (cCharAt12 == 'Z') {
                if (this.calendar.getTimeZone().getRawOffset() != 0) {
                    String[] availableIDs2 = TimeZone.getAvailableIDs(0);
                    if (availableIDs2.length > 0) {
                        this.calendar.setTimeZone(DesugarTimeZone.getTimeZone(availableIDs2[0]));
                    }
                }
                i12 = 1;
            } else {
                i12 = 0;
            }
            int i19 = i7 + 10 + i11 + i12;
            cCharAt16 = charAt(this.bp + i19);
            if (cCharAt16 == 26 && cCharAt16 != '\"') {
                return false;
            }
            int i20 = this.bp + i19;
            this.bp = i20;
            this.ch = charAt(i20);
            this.token = 5;
            return true;
        }
        i7 = 10;
        c8 = cCharAt41;
        c9 = cCharAt26;
        cCharAt22 = cCharAt22;
        if (!checkDate(cCharAt20, cCharAt21, cCharAt22, cCharAt23, cCharAt25, c9, c8, cCharAt41)) {
            return false;
        }
        setCalendar(cCharAt20, cCharAt21, cCharAt22, cCharAt23, cCharAt25, c9, c8, cCharAt41);
        cCharAt3 = charAt(this.bp + i7);
        if (cCharAt3 == 'T') {
        }
        i8 = i7 + 9;
        if (i >= i8) {
            return false;
        }
        cCharAt4 = charAt(this.bp + i7 + 1);
        cCharAt5 = charAt(this.bp + i7 + 2);
        cCharAt6 = charAt(this.bp + i7 + 4);
        cCharAt7 = charAt(this.bp + i7 + 5);
        cCharAt8 = charAt(this.bp + i7 + 7);
        cCharAt9 = charAt(this.bp + i7 + 8);
        if (!checkTime(cCharAt4, cCharAt5, cCharAt6, cCharAt7, cCharAt8, cCharAt9)) {
            return false;
        }
        setTime(cCharAt4, cCharAt5, cCharAt6, cCharAt7, cCharAt8, cCharAt9);
        cCharAt10 = charAt(this.bp + i7 + 9);
        if (cCharAt10 == '.') {
            this.calendar.set(14, 0);
            int i110 = this.bp + i8;
            this.bp = i110;
            this.ch = charAt(i110);
            this.token = 5;
            return cCharAt10 != 'Z' ? true : true;
        }
        i9 = i7 + 11;
        if (i >= i9) {
            return false;
        }
        int i111 = cCharAt11 - '0';
        if (i > i9) {
            i10 = 1;
        } else {
            i10 = 1;
        }
        if (i10 == 2) {
            i11 = i10;
        } else {
            i11 = i10;
        }
        this.calendar.set(14, i111);
        cCharAt12 = charAt(this.bp + i7 + 10 + i11);
        if (cCharAt12 != '+') {
            cCharAt13 = charAt(this.bp + i7 + 10 + i11 + 1);
            if (cCharAt13 >= '0') {
                return false;
            }
            return false;
        }
        cCharAt13 = charAt(this.bp + i7 + 10 + i11 + 1);
        if (cCharAt13 >= '0') {
            return false;
        }
        return false;
        int i112 = i7 + 10 + i11 + i12;
        cCharAt16 = charAt(this.bp + i112);
        if (cCharAt16 == 26) {
        }
        int i21 = this.bp + i112;
        this.bp = i21;
        this.ch = charAt(i21);
        this.token = 5;
        return true;
    }

    protected void setTime(char c, char c2, char c3, char c4, char c5, char c6) {
        this.calendar.set(11, ((c - '0') * 10) + (c2 - '0'));
        this.calendar.set(12, ((c3 - '0') * 10) + (c4 - '0'));
        this.calendar.set(13, ((c5 - '0') * 10) + (c6 - '0'));
    }

    protected void setTimeZone(char c, char c2, char c3) {
        setTimeZone(c, c2, c3, '0', '0');
    }

    protected void setTimeZone(char c, char c2, char c3, char c4, char c5) {
        int i = ((((c2 - '0') * 10) + (c3 - '0')) * DateTimeConstants.MILLIS_PER_HOUR) + ((((c4 - '0') * 10) + (c5 - '0')) * DateTimeConstants.MILLIS_PER_MINUTE);
        if (c == '-') {
            i = -i;
        }
        if (this.calendar.getTimeZone().getRawOffset() != i) {
            String[] availableIDs = TimeZone.getAvailableIDs(i);
            if (availableIDs.length > 0) {
                this.calendar.setTimeZone(DesugarTimeZone.getTimeZone(availableIDs[0]));
            }
        }
    }

    private void setCalendar(char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8) {
        this.calendar = Calendar.getInstance(this.timeZone, this.locale);
        this.calendar.set(1, ((c - '0') * 1000) + ((c2 - '0') * 100) + ((c3 - '0') * 10) + (c4 - '0'));
        this.calendar.set(2, (((c5 - '0') * 10) + (c6 - '0')) - 1);
        this.calendar.set(5, ((c7 - '0') * 10) + (c8 - '0'));
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean isEOF() {
        if (this.bp != this.len) {
            return this.ch == 26 && this.bp + 1 == this.len;
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public int scanFieldInt(char[] cArr) {
        int i;
        char cCharAt;
        this.matchStat = 0;
        int i2 = this.bp;
        char c = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = this.bp + cArr.length;
        int i3 = length + 1;
        char cCharAt2 = charAt(length);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(i3);
            i3 = length + 2;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            cCharAt2 = charAt(i3);
            i3++;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i4 = cCharAt2 - '0';
        while (true) {
            i = i3 + 1;
            cCharAt = charAt(i3);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i4 = (i4 * 10) + (cCharAt - '0');
            i3 = i;
        }
        if (cCharAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (i4 < 0) {
            this.matchStat = -1;
            return 0;
        }
        if (z) {
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return 0;
            }
            int i5 = i3 + 2;
            cCharAt = charAt(i);
            i = i5;
        }
        while (cCharAt != ',' && cCharAt != '}') {
            if (isWhitespace(cCharAt)) {
                int i6 = i + 1;
                cCharAt = charAt(i);
                i = i6;
            } else {
                this.matchStat = -1;
                return 0;
            }
        }
        int i7 = i - 1;
        this.bp = i7;
        if (cCharAt == ',') {
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -i4 : i4;
        }
        if (cCharAt == '}') {
            this.bp = i7;
            int i9 = this.bp + 1;
            this.bp = i9;
            char cCharAt3 = charAt(i9);
            while (true) {
                if (cCharAt3 == ',') {
                    this.token = 16;
                    int i10 = this.bp + 1;
                    this.bp = i10;
                    this.ch = charAt(i10);
                    break;
                }
                if (cCharAt3 == ']') {
                    this.token = 15;
                    int i11 = this.bp + 1;
                    this.bp = i11;
                    this.ch = charAt(i11);
                    break;
                }
                if (cCharAt3 == '}') {
                    this.token = 13;
                    int i12 = this.bp + 1;
                    this.bp = i12;
                    this.ch = charAt(i12);
                    break;
                }
                if (cCharAt3 == 26) {
                    this.token = 20;
                    break;
                }
                if (isWhitespace(cCharAt3)) {
                    int i13 = this.bp + 1;
                    this.bp = i13;
                    cCharAt3 = charAt(i13);
                } else {
                    this.bp = i2;
                    this.ch = c;
                    this.matchStat = -1;
                    return 0;
                }
            }
            this.matchStat = 4;
        }
        return z2 ? -i4 : i4;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        int i = this.bp;
        char c = this.ch;
        while (!charArrayCompare(this.text, this.bp, cArr)) {
            if (isWhitespace(this.ch)) {
                next();
            } else {
                this.matchStat = -2;
                return stringDefaultValue();
            }
        }
        int length = this.bp + cArr.length;
        int i2 = length + 1;
        if (charAt(length) != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int iIndexOf = indexOf(Typography.quote, i2);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
        String strSubString = subString(i2, iIndexOf - i2);
        if (strSubString.indexOf(92) != -1) {
            while (true) {
                int i3 = 0;
                for (int i4 = iIndexOf - 1; i4 >= 0 && charAt(i4) == '\\'; i4--) {
                    i3++;
                }
                if (i3 % 2 == 0) {
                    break;
                }
                iIndexOf = indexOf(Typography.quote, iIndexOf + 1);
            }
            int length2 = iIndexOf - ((this.bp + cArr.length) + 1);
            strSubString = readString(sub_chars(this.bp + cArr.length + 1, length2), length2);
        }
        char cCharAt = charAt(iIndexOf + 1);
        while (cCharAt != ',' && cCharAt != '}') {
            if (isWhitespace(cCharAt)) {
                char cCharAt2 = charAt(iIndexOf + 2);
                iIndexOf++;
                cCharAt = cCharAt2;
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        }
        this.bp = iIndexOf + 1;
        this.ch = cCharAt;
        if (cCharAt == ',') {
            int i5 = this.bp + 1;
            this.bp = i5;
            this.ch = charAt(i5);
            this.matchStat = 3;
            return strSubString;
        }
        int i6 = this.bp + 1;
        this.bp = i6;
        char cCharAt3 = charAt(i6);
        if (cCharAt3 == ',') {
            this.token = 16;
            int i7 = this.bp + 1;
            this.bp = i7;
            this.ch = charAt(i7);
        } else if (cCharAt3 == ']') {
            this.token = 15;
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
        } else if (cCharAt3 == '}') {
            this.token = 13;
            int i9 = this.bp + 1;
            this.bp = i9;
            this.ch = charAt(i9);
        } else if (cCharAt3 == 26) {
            this.token = 20;
        } else {
            this.bp = i;
            this.ch = c;
            this.matchStat = -1;
            return stringDefaultValue();
        }
        this.matchStat = 4;
        return strSubString;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Date scanFieldDate(char[] cArr) {
        long j;
        Date date;
        char cCharAt;
        char cCharAt2;
        boolean z = false;
        this.matchStat = 0;
        int i = this.bp;
        char c = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = this.bp + cArr.length;
        int i2 = length + 1;
        char cCharAt3 = charAt(length);
        if (cCharAt3 == '\"') {
            int iIndexOf = indexOf(Typography.quote, i2);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            this.bp = i2;
            if (scanISO8601DateIfMatch(false, iIndexOf - i2)) {
                date = this.calendar.getTime();
                cCharAt = charAt(iIndexOf + 1);
                this.bp = i;
                while (cCharAt != ',' && cCharAt != '}') {
                    if (isWhitespace(cCharAt)) {
                        int i3 = iIndexOf + 1;
                        char cCharAt4 = charAt(iIndexOf + 2);
                        iIndexOf = i3;
                        cCharAt = cCharAt4;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.bp = iIndexOf + 1;
                this.ch = cCharAt;
            } else {
                this.bp = i;
                this.matchStat = -1;
                return null;
            }
        } else {
            char c2 = '9';
            char c3 = '0';
            if (cCharAt3 != '-' && (cCharAt3 < '0' || cCharAt3 > '9')) {
                this.matchStat = -1;
                return null;
            }
            if (cCharAt3 == '-') {
                cCharAt3 = charAt(i2);
                i2 = length + 2;
                z = true;
            }
            if (cCharAt3 < '0' || cCharAt3 > '9') {
                j = 0;
            } else {
                long j2 = cCharAt3 - '0';
                while (true) {
                    int i4 = i2 + 1;
                    cCharAt2 = charAt(i2);
                    if (cCharAt2 < c3 || cCharAt2 > c2) {
                        break;
                    }
                    j2 = (j2 * 10) + ((long) (cCharAt2 - '0'));
                    i2 = i4;
                    c2 = '9';
                    c3 = '0';
                }
                if (cCharAt2 == ',' || cCharAt2 == '}') {
                    this.bp = i2;
                }
                long j3 = j2;
                cCharAt3 = cCharAt2;
                j = j3;
            }
            if (j < 0) {
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            date = new Date(j);
            cCharAt = cCharAt3;
        }
        if (cCharAt == ',') {
            int i5 = this.bp + 1;
            this.bp = i5;
            this.ch = charAt(i5);
            this.matchStat = 3;
            this.token = 16;
            return date;
        }
        int i6 = this.bp + 1;
        this.bp = i6;
        char cCharAt5 = charAt(i6);
        if (cCharAt5 == ',') {
            this.token = 16;
            int i7 = this.bp + 1;
            this.bp = i7;
            this.ch = charAt(i7);
        } else if (cCharAt5 == ']') {
            this.token = 15;
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
        } else if (cCharAt5 == '}') {
            this.token = 13;
            int i9 = this.bp + 1;
            this.bp = i9;
            this.ch = charAt(i9);
        } else if (cCharAt5 == 26) {
            this.token = 20;
        } else {
            this.bp = i;
            this.ch = c;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = this.bp + cArr.length;
        int i = length + 1;
        if (charAt(length) != '\"') {
            this.matchStat = -1;
            return 0L;
        }
        long j = -3750763034362895579L;
        while (true) {
            int i2 = i + 1;
            char cCharAt = charAt(i);
            if (cCharAt == '\"') {
                this.bp = i2;
                char cCharAt2 = charAt(this.bp);
                this.ch = cCharAt2;
                while (cCharAt2 != ',') {
                    if (cCharAt2 == '}') {
                        next();
                        skipWhitespace();
                        char current = getCurrent();
                        if (current == ',') {
                            this.token = 16;
                            int i3 = this.bp + 1;
                            this.bp = i3;
                            this.ch = charAt(i3);
                        } else if (current == ']') {
                            this.token = 15;
                            int i4 = this.bp + 1;
                            this.bp = i4;
                            this.ch = charAt(i4);
                        } else if (current == '}') {
                            this.token = 13;
                            int i5 = this.bp + 1;
                            this.bp = i5;
                            this.ch = charAt(i5);
                        } else if (current == 26) {
                            this.token = 20;
                        } else {
                            this.matchStat = -1;
                            return 0L;
                        }
                        this.matchStat = 4;
                        return j;
                    }
                    if (isWhitespace(cCharAt2)) {
                        int i6 = this.bp + 1;
                        this.bp = i6;
                        cCharAt2 = charAt(i6);
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                }
                int i7 = this.bp + 1;
                this.bp = i7;
                this.ch = charAt(i7);
                this.matchStat = 3;
                return j;
            }
            if (i2 > this.len) {
                this.matchStat = -1;
                return 0L;
            }
            j = (j ^ ((long) cCharAt)) * 1099511628211L;
            i = i2;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
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

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Collection<String> scanFieldStringArray(char[] cArr, Class<?> cls) {
        int i;
        char cCharAt;
        char cCharAt2;
        int i2;
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return null;
        }
        Collection<String> collectionNewCollectionByType = newCollectionByType(cls);
        int length = this.bp + cArr.length;
        int i3 = length + 1;
        if (charAt(length) != '[') {
            if (this.text.startsWith("ull", i3)) {
                int i4 = length + 4;
                i = length + 5;
                cCharAt = charAt(i4);
                collectionNewCollectionByType = null;
            } else {
                this.matchStat = -1;
                return null;
            }
        } else {
            int i5 = length + 2;
            char cCharAt3 = charAt(i3);
            while (true) {
                if (cCharAt3 != '\"') {
                    if (cCharAt3 == 'n' && this.text.startsWith("ull", i5)) {
                        int i6 = i5 + 3;
                        int i7 = i5 + 4;
                        char cCharAt4 = charAt(i6);
                        collectionNewCollectionByType.add(null);
                        cCharAt2 = cCharAt4;
                        i2 = i7;
                    } else {
                        if (cCharAt3 == ']' && collectionNewCollectionByType.size() == 0) {
                            int i8 = i5 + 1;
                            char cCharAt5 = charAt(i5);
                            i = i8;
                            cCharAt = cCharAt5;
                            break;
                        }
                        this.matchStat = -1;
                        return null;
                    }
                } else {
                    int iIndexOf = indexOf(Typography.quote, i5);
                    if (iIndexOf == -1) {
                        throw new JSONException("unclosed str");
                    }
                    String strSubString = subString(i5, iIndexOf - i5);
                    if (strSubString.indexOf(92) != -1) {
                        while (true) {
                            int i9 = 0;
                            for (int i10 = iIndexOf - 1; i10 >= 0 && charAt(i10) == '\\'; i10--) {
                                i9++;
                            }
                            if (i9 % 2 == 0) {
                                break;
                            }
                            iIndexOf = indexOf(Typography.quote, iIndexOf + 1);
                        }
                        int i11 = iIndexOf - i5;
                        strSubString = readString(sub_chars(i5, i11), i11);
                    }
                    int i12 = iIndexOf + 1;
                    i2 = iIndexOf + 2;
                    cCharAt2 = charAt(i12);
                    collectionNewCollectionByType.add(strSubString);
                }
                if (cCharAt2 != ',') {
                    if (cCharAt2 == ']') {
                        i = i2 + 1;
                        cCharAt = charAt(i2);
                        while (isWhitespace(cCharAt)) {
                            int i13 = i + 1;
                            char cCharAt6 = charAt(i);
                            i = i13;
                            cCharAt = cCharAt6;
                        }
                        break;
                    }
                    this.matchStat = -1;
                    return null;
                }
                i5 = i2 + 1;
                cCharAt3 = charAt(i2);
            }
        }
        this.bp = i;
        if (cCharAt == ',') {
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return collectionNewCollectionByType;
        }
        if (cCharAt == '}') {
            char cCharAt7 = charAt(this.bp);
            while (cCharAt7 != ',') {
                if (cCharAt7 == ']') {
                    this.token = 15;
                    int i14 = this.bp + 1;
                    this.bp = i14;
                    this.ch = charAt(i14);
                } else if (cCharAt7 == '}') {
                    this.token = 13;
                    int i15 = this.bp + 1;
                    this.bp = i15;
                    this.ch = charAt(i15);
                } else if (cCharAt7 == 26) {
                    this.token = 20;
                    this.ch = cCharAt7;
                } else {
                    boolean z = false;
                    while (isWhitespace(cCharAt7)) {
                        int i16 = i + 1;
                        char cCharAt8 = charAt(i);
                        this.bp = i16;
                        z = true;
                        i = i16;
                        cCharAt7 = cCharAt8;
                    }
                    if (!z) {
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.matchStat = 4;
                return collectionNewCollectionByType;
            }
            this.token = 16;
            int i17 = this.bp + 1;
            this.bp = i17;
            this.ch = charAt(i17);
            this.matchStat = 4;
            return collectionNewCollectionByType;
        }
        this.matchStat = -1;
        return null;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public long scanFieldLong(char[] cArr) {
        int i;
        char cCharAt;
        boolean z = false;
        this.matchStat = 0;
        int i2 = this.bp;
        char c = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = this.bp + cArr.length;
        int i3 = length + 1;
        char cCharAt2 = charAt(length);
        boolean z2 = cCharAt2 == '\"';
        if (z2) {
            cCharAt2 = charAt(i3);
            i3 = length + 2;
        }
        if (cCharAt2 == '-') {
            cCharAt2 = charAt(i3);
            i3++;
            z = true;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            this.bp = i2;
            this.ch = c;
            this.matchStat = -1;
            return 0L;
        }
        long j = cCharAt2 - '0';
        while (true) {
            i = i3 + 1;
            cCharAt = charAt(i3);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j = (j * 10) + ((long) (cCharAt - '0'));
            i3 = i;
        }
        if (cCharAt == '.') {
            this.matchStat = -1;
            return 0L;
        }
        if (z2) {
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return 0L;
            }
            cCharAt = charAt(i);
            i = i3 + 2;
        }
        if (cCharAt == ',' || cCharAt == '}') {
            this.bp = i - 1;
        }
        if (j < 0 && (j != Long.MIN_VALUE || !z)) {
            this.bp = i2;
            this.ch = c;
            this.matchStat = -1;
            return 0L;
        }
        while (cCharAt != ',') {
            if (cCharAt == '}') {
                int i4 = 1;
                int i5 = this.bp + 1;
                this.bp = i5;
                char cCharAt3 = charAt(i5);
                while (true) {
                    if (cCharAt3 == ',') {
                        this.token = 16;
                        int i6 = this.bp + i4;
                        this.bp = i6;
                        this.ch = charAt(i6);
                        break;
                    }
                    if (cCharAt3 == ']') {
                        this.token = 15;
                        int i7 = this.bp + i4;
                        this.bp = i7;
                        this.ch = charAt(i7);
                        break;
                    }
                    if (cCharAt3 == '}') {
                        this.token = 13;
                        int i8 = this.bp + i4;
                        this.bp = i8;
                        this.ch = charAt(i8);
                        break;
                    }
                    if (cCharAt3 == 26) {
                        this.token = 20;
                        break;
                    }
                    if (isWhitespace(cCharAt3)) {
                        int i9 = this.bp + 1;
                        this.bp = i9;
                        cCharAt3 = charAt(i9);
                        i4 = 1;
                    } else {
                        this.bp = i2;
                        this.ch = c;
                        this.matchStat = -1;
                        return 0L;
                    }
                }
                this.matchStat = 4;
                return z ? -j : j;
            }
            if (isWhitespace(cCharAt)) {
                this.bp = i;
                cCharAt = charAt(i);
                i++;
            } else {
                this.matchStat = -1;
                return 0L;
            }
        }
        int i10 = this.bp + 1;
        this.bp = i10;
        this.ch = charAt(i10);
        this.matchStat = 3;
        this.token = 16;
        return z ? -j : j;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x016d  */
    /* JADX WARN: Code duplicated, block: B:103:0x0173 A[LOOP:0: B:78:0x00f5->B:103:0x0173, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:108:0x0110 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:109:0x017e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:112:0x016a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:114:0x012d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:115:0x013f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:116:0x0153 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:0x010c  */
    /* JADX WARN: Code duplicated, block: B:86:0x0129  */
    /* JADX WARN: Code duplicated, block: B:89:0x013d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:91:0x014f  */
    /* JADX WARN: Code duplicated, block: B:96:0x015a  */
    /* JADX WARN: Code duplicated, block: B:98:0x0160 A[LOOP:1: B:84:0x0119->B:98:0x0160, LOOP_END] */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean scanFieldBoolean(char[] cArr) {
        char cCharAt;
        boolean z;
        char cCharAt2;
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return false;
        }
        int i = this.bp;
        int length = this.bp + cArr.length;
        int i2 = length + 1;
        char cCharAt3 = charAt(length);
        boolean z2 = cCharAt3 == '\"';
        if (z2) {
            cCharAt3 = charAt(i2);
            i2 = length + 2;
        }
        if (cCharAt3 == 't') {
            int i3 = i2 + 1;
            if (charAt(i2) != 'r') {
                this.matchStat = -1;
                return false;
            }
            int i4 = i2 + 2;
            if (charAt(i3) != 'u') {
                this.matchStat = -1;
                return false;
            }
            int i5 = i2 + 3;
            if (charAt(i4) != 'e') {
                this.matchStat = -1;
                return false;
            }
            if (z2) {
                int i6 = i2 + 4;
                if (charAt(i5) != '\"') {
                    this.matchStat = -1;
                    return false;
                }
                i5 = i6;
            }
            this.bp = i5;
            cCharAt = charAt(this.bp);
        } else {
            if (cCharAt3 == 'f') {
                int i7 = i2 + 1;
                if (charAt(i2) != 'a') {
                    this.matchStat = -1;
                    return false;
                }
                int i8 = i2 + 2;
                if (charAt(i7) != 'l') {
                    this.matchStat = -1;
                    return false;
                }
                int i9 = i2 + 3;
                if (charAt(i8) != 's') {
                    this.matchStat = -1;
                    return false;
                }
                int i10 = i2 + 4;
                if (charAt(i9) != 'e') {
                    this.matchStat = -1;
                    return false;
                }
                if (z2) {
                    int i11 = i2 + 5;
                    if (charAt(i10) != '\"') {
                        this.matchStat = -1;
                        return false;
                    }
                    i10 = i11;
                }
                this.bp = i10;
                cCharAt = charAt(this.bp);
            } else if (cCharAt3 == '1') {
                if (z2) {
                    int i12 = i2 + 1;
                    if (charAt(i2) != '\"') {
                        this.matchStat = -1;
                        return false;
                    }
                    i2 = i12;
                }
                this.bp = i2;
                cCharAt = charAt(this.bp);
            } else if (cCharAt3 == '0') {
                if (z2) {
                    int i13 = i2 + 1;
                    if (charAt(i2) != '\"') {
                        this.matchStat = -1;
                        return false;
                    }
                    i2 = i13;
                }
                this.bp = i2;
                cCharAt = charAt(this.bp);
            } else {
                this.matchStat = -1;
                return false;
            }
            z = false;
            while (cCharAt != ',') {
                if (cCharAt == '}') {
                    int i14 = this.bp + 1;
                    this.bp = i14;
                    cCharAt2 = charAt(i14);
                    while (cCharAt2 != ',') {
                        if (cCharAt2 == ']') {
                            this.token = 15;
                            int i15 = this.bp + 1;
                            this.bp = i15;
                            this.ch = charAt(i15);
                        } else if (cCharAt2 == '}') {
                            this.token = 13;
                            int i16 = this.bp + 1;
                            this.bp = i16;
                            this.ch = charAt(i16);
                        } else if (cCharAt2 == 26) {
                            this.token = 20;
                        } else if (isWhitespace(cCharAt2)) {
                            int i17 = this.bp + 1;
                            this.bp = i17;
                            cCharAt2 = charAt(i17);
                        } else {
                            this.matchStat = -1;
                            return false;
                        }
                        this.matchStat = 4;
                        return z;
                    }
                    this.token = 16;
                    int i18 = this.bp + 1;
                    this.bp = i18;
                    this.ch = charAt(i18);
                    this.matchStat = 4;
                    return z;
                }
                if (isWhitespace(cCharAt)) {
                    int i19 = this.bp + 1;
                    this.bp = i19;
                    cCharAt = charAt(i19);
                } else {
                    this.bp = i;
                    charAt(this.bp);
                    this.matchStat = -1;
                    return false;
                }
            }
            int i20 = this.bp + 1;
            this.bp = i20;
            this.ch = charAt(i20);
            this.matchStat = 3;
            this.token = 16;
            return z;
        }
        z = true;
        while (cCharAt != ',') {
            if (cCharAt == '}') {
                int i110 = this.bp + 1;
                this.bp = i110;
                cCharAt2 = charAt(i110);
                while (cCharAt2 != ',') {
                    if (cCharAt2 == ']') {
                        this.token = 15;
                        int i111 = this.bp + 1;
                        this.bp = i111;
                        this.ch = charAt(i111);
                    } else if (cCharAt2 == '}') {
                        this.token = 13;
                        int i112 = this.bp + 1;
                        this.bp = i112;
                        this.ch = charAt(i112);
                    } else if (cCharAt2 == 26) {
                        this.token = 20;
                    } else if (isWhitespace(cCharAt2)) {
                        int i113 = this.bp + 1;
                        this.bp = i113;
                        cCharAt2 = charAt(i113);
                    } else {
                        this.matchStat = -1;
                        return false;
                    }
                    this.matchStat = 4;
                    return z;
                }
                this.token = 16;
                int i114 = this.bp + 1;
                this.bp = i114;
                this.ch = charAt(i114);
                this.matchStat = 4;
                return z;
            }
            if (isWhitespace(cCharAt)) {
                int i115 = this.bp + 1;
                this.bp = i115;
                cCharAt = charAt(i115);
            } else {
                this.bp = i;
                charAt(this.bp);
                this.matchStat = -1;
                return false;
            }
        }
        int i21 = this.bp + 1;
        this.bp = i21;
        this.ch = charAt(i21);
        this.matchStat = 3;
        this.token = 16;
        return z;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final int scanInt(char c) {
        int i;
        char cCharAt;
        this.matchStat = 0;
        int i2 = this.bp;
        int i3 = i2 + 1;
        char cCharAt2 = charAt(i2);
        while (isWhitespace(cCharAt2)) {
            int i4 = i3 + 1;
            char cCharAt3 = charAt(i3);
            i3 = i4;
            cCharAt2 = cCharAt3;
        }
        boolean z = cCharAt2 == '\"';
        if (z) {
            int i5 = i3 + 1;
            char cCharAt4 = charAt(i3);
            i3 = i5;
            cCharAt2 = cCharAt4;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            int i6 = i3 + 1;
            char cCharAt5 = charAt(i3);
            i3 = i6;
            cCharAt2 = cCharAt5;
        }
        if (cCharAt2 >= '0' && cCharAt2 <= '9') {
            int i7 = cCharAt2 - '0';
            while (true) {
                i = i3 + 1;
                cCharAt = charAt(i3);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i7 = (i7 * 10) + (cCharAt - '0');
                i3 = i;
            }
            if (cCharAt == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (z) {
                if (cCharAt != '\"') {
                    this.matchStat = -1;
                    return 0;
                }
                cCharAt = charAt(i);
                i = i3 + 2;
            }
            if (i7 < 0) {
                this.matchStat = -1;
                return 0;
            }
            while (cCharAt != c) {
                if (isWhitespace(cCharAt)) {
                    cCharAt = charAt(i);
                    i++;
                } else {
                    this.matchStat = -1;
                    return z2 ? -i7 : i7;
                }
            }
            this.bp = i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -i7 : i7;
        }
        if (cCharAt2 == 'n') {
            int i8 = i3 + 1;
            if (charAt(i3) == 'u') {
                int i9 = i3 + 2;
                if (charAt(i8) == 'l') {
                    int i10 = i3 + 3;
                    if (charAt(i9) == 'l') {
                        this.matchStat = 5;
                        int i11 = i3 + 4;
                        char cCharAt6 = charAt(i10);
                        if (z && cCharAt6 == '\"') {
                            cCharAt6 = charAt(i11);
                            i11 = i3 + 5;
                        }
                        while (cCharAt6 != ',') {
                            if (cCharAt6 == ']') {
                                this.bp = i11;
                                this.ch = charAt(this.bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0;
                            }
                            if (isWhitespace(cCharAt6)) {
                                char cCharAt7 = charAt(i11);
                                i11++;
                                cCharAt6 = cCharAt7;
                            } else {
                                this.matchStat = -1;
                                return 0;
                            }
                        }
                        this.bp = i11;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public double scanDouble(char c) {
        int i;
        char cCharAt;
        long j;
        int i2;
        int i3;
        double d;
        char cCharAt2;
        this.matchStat = 0;
        int i4 = this.bp;
        int i5 = i4 + 1;
        char cCharAt3 = charAt(i4);
        boolean z = cCharAt3 == '\"';
        if (z) {
            cCharAt3 = charAt(i5);
            i5 = i4 + 2;
        }
        boolean z2 = cCharAt3 == '-';
        if (z2) {
            cCharAt3 = charAt(i5);
            i5++;
        }
        if (cCharAt3 >= '0') {
            char c2 = '9';
            if (cCharAt3 <= '9') {
                long j2 = cCharAt3 - '0';
                while (true) {
                    i = i5 + 1;
                    cCharAt = charAt(i5);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    j2 = (j2 * 10) + ((long) (cCharAt - '0'));
                    i5 = i;
                }
                if (cCharAt == '.') {
                    int i6 = i5 + 2;
                    char cCharAt4 = charAt(i);
                    if (cCharAt4 < '0' || cCharAt4 > '9') {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                    j2 = (j2 * 10) + ((long) (cCharAt4 - '0'));
                    long j3 = 10;
                    while (true) {
                        i = i6 + 1;
                        cCharAt2 = charAt(i6);
                        if (cCharAt2 < '0' || cCharAt2 > c2) {
                            break;
                        }
                        j2 = (j2 * 10) + ((long) (cCharAt2 - '0'));
                        j3 *= 10;
                        i6 = i;
                        c2 = '9';
                    }
                    long j4 = j3;
                    cCharAt = cCharAt2;
                    j = j4;
                } else {
                    j = 1;
                }
                boolean z3 = cCharAt == 'e' || cCharAt == 'E';
                if (z3) {
                    int i7 = i + 1;
                    char cCharAt5 = charAt(i);
                    if (cCharAt5 == '+' || cCharAt5 == '-') {
                        i += 2;
                        cCharAt = charAt(i7);
                    } else {
                        i = i7;
                        cCharAt = cCharAt5;
                    }
                    while (cCharAt >= '0' && cCharAt <= '9') {
                        int i8 = i + 1;
                        char cCharAt6 = charAt(i);
                        i = i8;
                        cCharAt = cCharAt6;
                    }
                }
                if (!z) {
                    i2 = this.bp;
                    i3 = (i - i2) - 1;
                } else {
                    if (cCharAt != '\"') {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                    int i9 = i + 1;
                    char cCharAt7 = charAt(i);
                    i2 = this.bp + 1;
                    i3 = (i9 - i2) - 2;
                    i = i9;
                    cCharAt = cCharAt7;
                }
                if (z3 || i3 >= 20) {
                    d = Double.parseDouble(subString(i2, i3));
                } else {
                    d = j2 / j;
                    if (z2) {
                        d = -d;
                    }
                }
                if (cCharAt == c) {
                    this.bp = i;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    this.token = 16;
                    return d;
                }
                this.matchStat = -1;
                return d;
            }
        }
        if (cCharAt3 == 'n') {
            int i10 = i5 + 1;
            if (charAt(i5) == 'u') {
                int i11 = i5 + 2;
                if (charAt(i10) == 'l') {
                    int i12 = i5 + 3;
                    if (charAt(i11) == 'l') {
                        this.matchStat = 5;
                        int i13 = i5 + 4;
                        char cCharAt8 = charAt(i12);
                        if (z && cCharAt8 == '\"') {
                            cCharAt8 = charAt(i13);
                            i13 = i5 + 5;
                        }
                        while (cCharAt8 != ',') {
                            if (cCharAt8 == ']') {
                                this.bp = i13;
                                this.ch = charAt(this.bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0.0d;
                            }
                            if (isWhitespace(cCharAt8)) {
                                char cCharAt9 = charAt(i13);
                                i13++;
                                cCharAt8 = cCharAt9;
                            } else {
                                this.matchStat = -1;
                                return 0.0d;
                            }
                        }
                        this.bp = i13;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0.0d;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0.0d;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public long scanLong(char c) {
        int i;
        char cCharAt;
        this.matchStat = 0;
        int i2 = this.bp;
        int i3 = i2 + 1;
        char cCharAt2 = charAt(i2);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(i3);
            i3 = i2 + 2;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            cCharAt2 = charAt(i3);
            i3++;
        }
        if (cCharAt2 >= '0' && cCharAt2 <= '9') {
            long j = cCharAt2 - '0';
            while (true) {
                i = i3 + 1;
                cCharAt = charAt(i3);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                j = (j * 10) + ((long) (cCharAt - '0'));
                i3 = i;
            }
            if (cCharAt == '.') {
                this.matchStat = -1;
                return 0L;
            }
            if (z) {
                if (cCharAt != '\"') {
                    this.matchStat = -1;
                    return 0L;
                }
                cCharAt = charAt(i);
                i = i3 + 2;
            }
            if (j < 0 && (j != Long.MIN_VALUE || !z2)) {
                this.matchStat = -1;
                return 0L;
            }
            while (cCharAt != c) {
                if (isWhitespace(cCharAt)) {
                    cCharAt = charAt(i);
                    i++;
                } else {
                    this.matchStat = -1;
                    return j;
                }
            }
            this.bp = i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -j : j;
        }
        if (cCharAt2 == 'n') {
            int i4 = i3 + 1;
            if (charAt(i3) == 'u') {
                int i5 = i3 + 2;
                if (charAt(i4) == 'l') {
                    int i6 = i3 + 3;
                    if (charAt(i5) == 'l') {
                        this.matchStat = 5;
                        int i7 = i3 + 4;
                        char cCharAt3 = charAt(i6);
                        if (z && cCharAt3 == '\"') {
                            cCharAt3 = charAt(i7);
                            i7 = i3 + 5;
                        }
                        while (cCharAt3 != ',') {
                            if (cCharAt3 == ']') {
                                this.bp = i7;
                                this.ch = charAt(this.bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0L;
                            }
                            if (isWhitespace(cCharAt3)) {
                                char cCharAt4 = charAt(i7);
                                i7++;
                                cCharAt3 = cCharAt4;
                            } else {
                                this.matchStat = -1;
                                return 0L;
                            }
                        }
                        this.bp = i7;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0L;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0L;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Date scanDate(char c) {
        char cCharAt;
        long j;
        Date date;
        char cCharAt2;
        boolean z = false;
        this.matchStat = 0;
        int i = this.bp;
        char c2 = this.ch;
        int i2 = this.bp;
        int i3 = i2 + 1;
        char cCharAt3 = charAt(i2);
        if (cCharAt3 == '\"') {
            int iIndexOf = indexOf(Typography.quote, i3);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            this.bp = i3;
            if (scanISO8601DateIfMatch(false, iIndexOf - i3)) {
                date = this.calendar.getTime();
                cCharAt = charAt(iIndexOf + 1);
                this.bp = i;
                while (cCharAt != ',' && cCharAt != ']') {
                    if (isWhitespace(cCharAt)) {
                        cCharAt = charAt(iIndexOf + 2);
                        iIndexOf++;
                    } else {
                        this.bp = i;
                        this.ch = c2;
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.bp = iIndexOf + 1;
                this.ch = cCharAt;
            } else {
                this.bp = i;
                this.ch = c2;
                this.matchStat = -1;
                return null;
            }
        } else {
            if (cCharAt3 != '-' && (cCharAt3 < '0' || cCharAt3 > '9')) {
                if (cCharAt3 == 'n') {
                    int i4 = i2 + 2;
                    if (charAt(i3) == 'u') {
                        int i5 = i2 + 3;
                        if (charAt(i4) == 'l') {
                            int i6 = i2 + 4;
                            if (charAt(i5) == 'l') {
                                cCharAt = charAt(i6);
                                this.bp = i6;
                                date = null;
                            }
                        }
                    }
                }
                this.bp = i;
                this.ch = c2;
                this.matchStat = -1;
                return null;
            }
            if (cCharAt3 == '-') {
                cCharAt3 = charAt(i3);
                i3 = i2 + 2;
                z = true;
            }
            if (cCharAt3 < '0' || cCharAt3 > '9') {
                cCharAt = cCharAt3;
                j = 0;
            } else {
                j = cCharAt3 - '0';
                while (true) {
                    int i7 = i3 + 1;
                    cCharAt2 = charAt(i3);
                    if (cCharAt2 < '0' || cCharAt2 > '9') {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt2 - '0'));
                    i3 = i7;
                }
                if (cCharAt2 == ',' || cCharAt2 == ']') {
                    this.bp = i3;
                }
                cCharAt = cCharAt2;
            }
            if (j < 0) {
                this.bp = i;
                this.ch = c2;
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            date = new Date(j);
        }
        if (cCharAt == ',') {
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
            this.matchStat = 3;
            return date;
        }
        int i9 = this.bp + 1;
        this.bp = i9;
        char cCharAt4 = charAt(i9);
        if (cCharAt4 == ',') {
            this.token = 16;
            int i10 = this.bp + 1;
            this.bp = i10;
            this.ch = charAt(i10);
        } else if (cCharAt4 == ']') {
            this.token = 15;
            int i11 = this.bp + 1;
            this.bp = i11;
            this.ch = charAt(i11);
        } else if (cCharAt4 == '}') {
            this.token = 13;
            int i12 = this.bp + 1;
            this.bp = i12;
            this.ch = charAt(i12);
        } else if (cCharAt4 == 26) {
            this.ch = JSONLexer.EOI;
            this.token = 20;
        } else {
            this.bp = i;
            this.ch = c2;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    protected final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        this.text.getChars(i, i3 + i, cArr, i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public String info() {
        StringBuilder sb = new StringBuilder("pos ");
        sb.append(this.bp);
        sb.append(", json : ");
        sb.append(this.text.length() < 65536 ? this.text : this.text.substring(0, 65536));
        return sb.toString();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public String[] scanFieldStringArray(char[] cArr, int i, SymbolTable symbolTable) {
        int i2;
        char cCharAt;
        int i3 = this.bp;
        char c = this.ch;
        while (isWhitespace(this.ch)) {
            next();
        }
        if (cArr != null) {
            this.matchStat = 0;
            if (!charArrayCompare(cArr)) {
                this.matchStat = -2;
                return null;
            }
            int length = this.bp + cArr.length;
            int i4 = length + 1;
            char cCharAt2 = this.text.charAt(length);
            while (isWhitespace(cCharAt2)) {
                cCharAt2 = this.text.charAt(i4);
                i4++;
            }
            if (cCharAt2 == ':') {
                i2 = i4 + 1;
                cCharAt = this.text.charAt(i4);
                while (isWhitespace(cCharAt)) {
                    cCharAt = this.text.charAt(i2);
                    i2++;
                }
            } else {
                this.matchStat = -1;
                return null;
            }
        } else {
            i2 = this.bp + 1;
            cCharAt = this.ch;
        }
        if (cCharAt == '[') {
            this.bp = i2;
            this.ch = this.text.charAt(this.bp);
            String[] strArr = i >= 0 ? new String[i] : new String[4];
            int i5 = 0;
            while (true) {
                if (isWhitespace(this.ch)) {
                    next();
                } else {
                    if (this.ch != '\"') {
                        this.bp = i3;
                        this.ch = c;
                        this.matchStat = -1;
                        return null;
                    }
                    String strScanSymbol = scanSymbol(symbolTable, Typography.quote);
                    if (i5 == strArr.length) {
                        String[] strArr2 = new String[strArr.length + (strArr.length >> 1) + 1];
                        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
                        strArr = strArr2;
                    }
                    int i6 = i5 + 1;
                    strArr[i5] = strScanSymbol;
                    while (isWhitespace(this.ch)) {
                        next();
                    }
                    if (this.ch == ',') {
                        next();
                        i5 = i6;
                    } else {
                        if (strArr.length != i6) {
                            String[] strArr3 = new String[i6];
                            System.arraycopy(strArr, 0, strArr3, 0, i6);
                            strArr = strArr3;
                        }
                        while (isWhitespace(this.ch)) {
                            next();
                        }
                        if (this.ch == ']') {
                            next();
                            return strArr;
                        }
                        this.bp = i3;
                        this.ch = c;
                        this.matchStat = -1;
                        return null;
                    }
                }
            }
        } else {
            if (cCharAt == 'n' && this.text.startsWith("ull", this.bp + 1)) {
                this.bp += 4;
                this.ch = this.text.charAt(this.bp);
                return null;
            }
            this.matchStat = -1;
            return null;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean matchField2(char[] cArr) {
        while (isWhitespace(this.ch)) {
            next();
        }
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = this.bp + cArr.length;
        int i = length + 1;
        char cCharAt = this.text.charAt(length);
        while (isWhitespace(cCharAt)) {
            cCharAt = this.text.charAt(i);
            i++;
        }
        if (cCharAt == ':') {
            this.bp = i;
            this.ch = charAt(this.bp);
            return true;
        }
        this.matchStat = -2;
        return false;
    }
}

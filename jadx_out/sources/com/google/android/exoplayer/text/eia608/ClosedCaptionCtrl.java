package com.google.android.exoplayer.text.eia608;

/* JADX INFO: loaded from: classes4.dex */
final class ClosedCaptionCtrl extends ClosedCaption {
    public static final byte BACKSPACE = 33;
    public static final byte CARRIAGE_RETURN = 45;
    public static final byte END_OF_CAPTION = 47;
    public static final byte ERASE_DISPLAYED_MEMORY = 44;
    public static final byte ERASE_NON_DISPLAYED_MEMORY = 46;
    public static final byte MID_ROW_CHAN_1 = 17;
    public static final byte MID_ROW_CHAN_2 = 25;
    public static final byte MISC_CHAN_1 = 20;
    public static final byte MISC_CHAN_2 = 28;
    public static final byte RESUME_CAPTION_LOADING = 32;
    public static final byte RESUME_DIRECT_CAPTIONING = 41;
    public static final byte ROLL_UP_CAPTIONS_2_ROWS = 37;
    public static final byte ROLL_UP_CAPTIONS_3_ROWS = 38;
    public static final byte ROLL_UP_CAPTIONS_4_ROWS = 39;
    public static final byte TAB_OFFSET_CHAN_1 = 23;
    public static final byte TAB_OFFSET_CHAN_2 = 31;
    public final byte cc1;
    public final byte cc2;

    protected ClosedCaptionCtrl(byte b, byte b2) {
        super(0);
        this.cc1 = b;
        this.cc2 = b2;
    }

    public boolean isMidRowCode() {
        byte b;
        byte b2 = this.cc1;
        return (b2 == 17 || b2 == 25) && (b = this.cc2) >= 32 && b <= 47;
    }

    public boolean isMiscCode() {
        byte b;
        byte b2 = this.cc1;
        return (b2 == 20 || b2 == 28) && (b = this.cc2) >= 32 && b <= 47;
    }

    public boolean isTabOffsetCode() {
        byte b;
        byte b2 = this.cc1;
        return (b2 == 23 || b2 == 31) && (b = this.cc2) >= 33 && b <= 35;
    }

    public boolean isPreambleAddressCode() {
        byte b;
        byte b2 = this.cc1;
        return b2 >= 16 && b2 <= 31 && (b = this.cc2) >= 64 && b <= 127;
    }

    public boolean isRepeatable() {
        byte b = this.cc1;
        return b >= 16 && b <= 31;
    }
}

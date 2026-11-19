package com.liulishuo.okdownload.core.breakpoint;

import android.database.Cursor;

/* JADX INFO: loaded from: classes5.dex */
public class BlockInfoRow {
    private final int breakpointId;
    private final long contentLength;
    private final long currentOffset;
    private final long startOffset;

    public BlockInfoRow(Cursor cursor) {
        this.breakpointId = cursor.getInt(cursor.getColumnIndex(BreakpointSQLiteKey.HOST_ID));
        this.startOffset = cursor.getLong(cursor.getColumnIndex(BreakpointSQLiteKey.START_OFFSET));
        this.contentLength = cursor.getLong(cursor.getColumnIndex(BreakpointSQLiteKey.CONTENT_LENGTH));
        this.currentOffset = cursor.getLong(cursor.getColumnIndex(BreakpointSQLiteKey.CURRENT_OFFSET));
    }

    public int getBreakpointId() {
        return this.breakpointId;
    }

    public long getStartOffset() {
        return this.startOffset;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public long getCurrentOffset() {
        return this.currentOffset;
    }

    public BlockInfo toInfo() {
        return new BlockInfo(this.startOffset, this.contentLength, this.currentOffset);
    }
}

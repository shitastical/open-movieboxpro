package com.google.android.exoplayer.extractor.ogg;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
final class OggUtil {
    public static final int PAGE_HEADER_SIZE = 27;
    private static final int TYPE_OGGS = Util.getIntegerCodeForString("OggS");

    public static class PacketInfoHolder {
        public int segmentCount;
        public int size;
    }

    public static int readBits(byte b, int i, int i2) {
        return (b >> i2) & (255 >>> (8 - i));
    }

    OggUtil() {
    }

    public static void skipToNextPage(ExtractorInput extractorInput) throws InterruptedException, IOException {
        int i;
        int length = 2048;
        byte[] bArr = new byte[2048];
        while (true) {
            if (extractorInput.getLength() != -1 && extractorInput.getPosition() + ((long) length) > extractorInput.getLength() && (length = (int) (extractorInput.getLength() - extractorInput.getPosition())) < 4) {
                throw new EOFException();
            }
            int i2 = 0;
            extractorInput.peekFully(bArr, 0, length, false);
            while (true) {
                i = length - 3;
                if (i2 < i) {
                    if (bArr[i2] == 79 && bArr[i2 + 1] == 103 && bArr[i2 + 2] == 103 && bArr[i2 + 3] == 83) {
                        extractorInput.skipFully(i2);
                        return;
                    }
                    i2++;
                }
            }
            extractorInput.skipFully(i);
        }
    }

    public static boolean populatePageHeader(ExtractorInput extractorInput, PageHeader pageHeader, ParsableByteArray parsableByteArray, boolean z) throws InterruptedException, IOException {
        parsableByteArray.reset();
        pageHeader.reset();
        if ((extractorInput.getLength() != -1 && extractorInput.getLength() - extractorInput.getPeekPosition() < 27) || !extractorInput.peekFully(parsableByteArray.data, 0, 27, true)) {
            if (z) {
                return false;
            }
            throw new EOFException();
        }
        if (parsableByteArray.readUnsignedInt() != TYPE_OGGS) {
            if (z) {
                return false;
            }
            throw new ParserException("expected OggS capture pattern at begin of page");
        }
        pageHeader.revision = parsableByteArray.readUnsignedByte();
        if (pageHeader.revision != 0) {
            if (z) {
                return false;
            }
            throw new ParserException("unsupported bit stream revision");
        }
        pageHeader.type = parsableByteArray.readUnsignedByte();
        pageHeader.granulePosition = parsableByteArray.readLittleEndianLong();
        pageHeader.streamSerialNumber = parsableByteArray.readLittleEndianUnsignedInt();
        pageHeader.pageSequenceNumber = parsableByteArray.readLittleEndianUnsignedInt();
        pageHeader.pageChecksum = parsableByteArray.readLittleEndianUnsignedInt();
        pageHeader.pageSegmentCount = parsableByteArray.readUnsignedByte();
        parsableByteArray.reset();
        pageHeader.headerSize = pageHeader.pageSegmentCount + 27;
        extractorInput.peekFully(parsableByteArray.data, 0, pageHeader.pageSegmentCount);
        for (int i = 0; i < pageHeader.pageSegmentCount; i++) {
            pageHeader.laces[i] = parsableByteArray.readUnsignedByte();
            pageHeader.bodySize += pageHeader.laces[i];
        }
        return true;
    }

    public static void calculatePacketSize(PageHeader pageHeader, int i, PacketInfoHolder packetInfoHolder) {
        packetInfoHolder.segmentCount = 0;
        packetInfoHolder.size = 0;
        while (packetInfoHolder.segmentCount + i < pageHeader.pageSegmentCount) {
            int[] iArr = pageHeader.laces;
            int i2 = packetInfoHolder.segmentCount;
            packetInfoHolder.segmentCount = i2 + 1;
            int i3 = iArr[i2 + i];
            packetInfoHolder.size += i3;
            if (i3 != 255) {
                return;
            }
        }
    }

    public static final class PageHeader {
        public int bodySize;
        public long granulePosition;
        public int headerSize;
        public final int[] laces = new int[255];
        public long pageChecksum;
        public int pageSegmentCount;
        public long pageSequenceNumber;
        public int revision;
        public long streamSerialNumber;
        public int type;

        public void reset() {
            this.revision = 0;
            this.type = 0;
            this.granulePosition = 0L;
            this.streamSerialNumber = 0L;
            this.pageSequenceNumber = 0L;
            this.pageChecksum = 0L;
            this.pageSegmentCount = 0;
            this.headerSize = 0;
            this.bodySize = 0;
        }
    }
}

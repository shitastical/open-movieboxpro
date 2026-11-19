package com.google.android.exoplayer.extractor.ts;

import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class AdtsExtractor implements Extractor {
    private static final int ID3_TAG = Util.getIntegerCodeForString("ID3");
    private static final int MAX_PACKET_SIZE = 200;
    private static final int MAX_SNIFF_BYTES = 8192;
    private AdtsReader adtsReader;
    private final long firstSampleTimestampUs;
    private final ParsableByteArray packetBuffer;
    private boolean startedPacket;

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void release() {
    }

    public AdtsExtractor() {
        this(0L);
    }

    public AdtsExtractor(long j) {
        this.firstSampleTimestampUs = j;
        this.packetBuffer = new ParsableByteArray(200);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(10);
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.data);
        int i = 0;
        while (true) {
            extractorInput.peekFully(parsableByteArray.data, 0, 10);
            parsableByteArray.setPosition(0);
            if (parsableByteArray.readUnsignedInt24() != ID3_TAG) {
                break;
            }
            int i2 = ((parsableByteArray.data[6] & 127) << 21) | ((parsableByteArray.data[7] & 127) << 14) | ((parsableByteArray.data[8] & 127) << 7) | (parsableByteArray.data[9] & 127);
            i += i2 + 10;
            extractorInput.advancePeekPosition(i2);
        }
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i);
        int i3 = i;
        while (true) {
            int i4 = 0;
            int i5 = 0;
            while (true) {
                extractorInput.peekFully(parsableByteArray.data, 0, 2);
                parsableByteArray.setPosition(0);
                if ((parsableByteArray.readUnsignedShort() & 65526) != 65520) {
                    break;
                }
                i4++;
                if (i4 >= 4 && i5 > 188) {
                    return true;
                }
                extractorInput.peekFully(parsableByteArray.data, 0, 4);
                parsableBitArray.setPosition(14);
                int bits = parsableBitArray.readBits(13);
                if (bits <= 6) {
                    return false;
                }
                extractorInput.advancePeekPosition(bits - 6);
                i5 += bits;
            }
            extractorInput.resetPeekPosition();
            i3++;
            if (i3 - i >= 8192) {
                return false;
            }
            extractorInput.advancePeekPosition(i3);
        }
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.adtsReader = new AdtsReader(extractorOutput.track(0), extractorOutput.track(1));
        extractorOutput.endTracks();
        extractorOutput.seekMap(SeekMap.UNSEEKABLE);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void seek() {
        this.startedPacket = false;
        this.adtsReader.seek();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        int i = extractorInput.read(this.packetBuffer.data, 0, 200);
        if (i == -1) {
            return -1;
        }
        this.packetBuffer.setPosition(0);
        this.packetBuffer.setLimit(i);
        if (!this.startedPacket) {
            this.adtsReader.packetStarted(this.firstSampleTimestampUs, true);
            this.startedPacket = true;
        }
        this.adtsReader.consume(this.packetBuffer);
        return 0;
    }
}

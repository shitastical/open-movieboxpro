package com.google.android.exoplayer.extractor.ogg;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.util.FlacSeekTable;
import com.google.android.exoplayer.util.FlacStreamInfo;
import com.google.android.exoplayer.util.FlacUtil;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: classes4.dex */
final class FlacReader extends StreamReader {
    private static final byte AUDIO_PACKET_TYPE = -1;
    private static final byte SEEKTABLE_PACKET_TYPE = 3;
    private boolean firstAudioPacketProcessed;
    private FlacSeekTable seekTable;
    private FlacStreamInfo streamInfo;

    FlacReader() {
    }

    static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        return parsableByteArray.readUnsignedByte() == 127 && parsableByteArray.readUnsignedInt() == 1179402563;
    }

    @Override // com.google.android.exoplayer.extractor.ogg.StreamReader
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        long position = extractorInput.getPosition();
        if (!this.oggParser.readPacket(extractorInput, this.scratch)) {
            return -1;
        }
        byte[] bArr = this.scratch.data;
        if (this.streamInfo == null) {
            this.streamInfo = new FlacStreamInfo(bArr, 17);
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 9, this.scratch.limit());
            bArrCopyOfRange[4] = -128;
            this.trackOutput.format(MediaFormat.createAudioFormat(null, MimeTypes.AUDIO_FLAC, this.streamInfo.bitRate(), -1, this.streamInfo.durationUs(), this.streamInfo.channels, this.streamInfo.sampleRate, Collections.singletonList(bArrCopyOfRange), null));
        } else {
            byte b = bArr[0];
            if (b == -1) {
                if (!this.firstAudioPacketProcessed) {
                    if (this.seekTable != null) {
                        this.extractorOutput.seekMap(this.seekTable.createSeekMap(position, this.streamInfo.sampleRate));
                        this.seekTable = null;
                    } else {
                        this.extractorOutput.seekMap(SeekMap.UNSEEKABLE);
                    }
                    this.firstAudioPacketProcessed = true;
                }
                this.trackOutput.sampleData(this.scratch, this.scratch.limit());
                this.scratch.setPosition(0);
                this.trackOutput.sampleMetadata(FlacUtil.extractSampleTimestamp(this.streamInfo, this.scratch), 1, this.scratch.limit(), 0, null);
            } else if ((b & 127) == 3 && this.seekTable == null) {
                this.seekTable = FlacSeekTable.parseSeekTable(this.scratch);
            }
        }
        this.scratch.reset();
        return 0;
    }
}

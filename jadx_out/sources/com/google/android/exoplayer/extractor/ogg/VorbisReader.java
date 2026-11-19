package com.google.android.exoplayer.extractor.ogg;

import androidx.media3.extractor.ogg.OggPageHeader;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;
import java.util.ArrayList;
import master.flame.danmaku.danmaku.model.android.DanmakuFactory;

/* JADX INFO: loaded from: classes4.dex */
final class VorbisReader extends StreamReader implements SeekMap {
    private static final long LARGEST_EXPECTED_PAGE_SIZE = 8000;
    private long audioStartPosition;
    private VorbisUtil.CommentHeader commentHeader;
    private long duration;
    private long elapsedSamples;
    private long inputLength;
    private int previousPacketBlockSize;
    private boolean seenFirstAudioPacket;
    private long totalSamples;
    private VorbisUtil.VorbisIdHeader vorbisIdHeader;
    private VorbisSetup vorbisSetup;
    private final OggSeeker oggSeeker = new OggSeeker();
    private long targetGranule = -1;

    VorbisReader() {
    }

    static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        try {
            return VorbisUtil.verifyVorbisHeaderCapturePattern(1, parsableByteArray, true);
        } catch (ParserException unused) {
            return false;
        }
    }

    @Override // com.google.android.exoplayer.extractor.ogg.StreamReader
    public void seek() {
        super.seek();
        this.previousPacketBlockSize = 0;
        this.elapsedSamples = 0L;
        this.seenFirstAudioPacket = false;
    }

    @Override // com.google.android.exoplayer.extractor.ogg.StreamReader
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        if (this.totalSamples == 0) {
            if (this.vorbisSetup == null) {
                this.inputLength = extractorInput.getLength();
                this.vorbisSetup = readSetupHeaders(extractorInput, this.scratch);
                this.audioStartPosition = extractorInput.getPosition();
                this.extractorOutput.seekMap(this);
                if (this.inputLength != -1) {
                    positionHolder.position = Math.max(0L, extractorInput.getLength() - LARGEST_EXPECTED_PAGE_SIZE);
                    return 1;
                }
            }
            this.totalSamples = this.inputLength == -1 ? -1L : this.oggParser.readGranuleOfLastPage(extractorInput);
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.vorbisSetup.idHeader.data);
            arrayList.add(this.vorbisSetup.setupHeaderData);
            this.duration = this.inputLength == -1 ? -1L : (this.totalSamples * 1000000) / this.vorbisSetup.idHeader.sampleRate;
            this.trackOutput.format(MediaFormat.createAudioFormat(null, "audio/vorbis", this.vorbisSetup.idHeader.bitrateNominal, OggPageHeader.MAX_PAGE_PAYLOAD, this.duration, this.vorbisSetup.idHeader.channels, (int) this.vorbisSetup.idHeader.sampleRate, arrayList, null));
            long j = this.inputLength;
            if (j != -1) {
                this.oggSeeker.setup(j - this.audioStartPosition, this.totalSamples);
                positionHolder.position = this.audioStartPosition;
                return 1;
            }
        }
        if (!this.seenFirstAudioPacket && this.targetGranule > -1) {
            OggUtil.skipToNextPage(extractorInput);
            long nextSeekPosition = this.oggSeeker.getNextSeekPosition(this.targetGranule, extractorInput);
            if (nextSeekPosition != -1) {
                positionHolder.position = nextSeekPosition;
                return 1;
            }
            this.elapsedSamples = this.oggParser.skipToPageOfGranule(extractorInput, this.targetGranule);
            this.previousPacketBlockSize = this.vorbisIdHeader.blockSize0;
            this.seenFirstAudioPacket = true;
        }
        if (!this.oggParser.readPacket(extractorInput, this.scratch)) {
            return -1;
        }
        if ((this.scratch.data[0] & 1) != 1) {
            int iDecodeBlockSize = decodeBlockSize(this.scratch.data[0], this.vorbisSetup);
            long j2 = this.seenFirstAudioPacket ? (this.previousPacketBlockSize + iDecodeBlockSize) / 4 : 0;
            if (this.elapsedSamples + j2 >= this.targetGranule) {
                appendNumberOfSamples(this.scratch, j2);
                long j3 = (this.elapsedSamples * 1000000) / this.vorbisSetup.idHeader.sampleRate;
                this.trackOutput.sampleData(this.scratch, this.scratch.limit());
                this.trackOutput.sampleMetadata(j3, 1, this.scratch.limit(), 0, null);
                this.targetGranule = -1L;
            }
            this.seenFirstAudioPacket = true;
            this.elapsedSamples += j2;
            this.previousPacketBlockSize = iDecodeBlockSize;
        }
        this.scratch.reset();
        return 0;
    }

    VorbisSetup readSetupHeaders(ExtractorInput extractorInput, ParsableByteArray parsableByteArray) throws InterruptedException, IOException {
        if (this.vorbisIdHeader == null) {
            this.oggParser.readPacket(extractorInput, parsableByteArray);
            this.vorbisIdHeader = VorbisUtil.readVorbisIdentificationHeader(parsableByteArray);
            parsableByteArray.reset();
        }
        if (this.commentHeader == null) {
            this.oggParser.readPacket(extractorInput, parsableByteArray);
            this.commentHeader = VorbisUtil.readVorbisCommentHeader(parsableByteArray);
            parsableByteArray.reset();
        }
        this.oggParser.readPacket(extractorInput, parsableByteArray);
        byte[] bArr = new byte[parsableByteArray.limit()];
        System.arraycopy(parsableByteArray.data, 0, bArr, 0, parsableByteArray.limit());
        VorbisUtil.Mode[] vorbisModes = VorbisUtil.readVorbisModes(parsableByteArray, this.vorbisIdHeader.channels);
        int iILog = VorbisUtil.iLog(vorbisModes.length - 1);
        parsableByteArray.reset();
        return new VorbisSetup(this.vorbisIdHeader, this.commentHeader, bArr, vorbisModes, iILog);
    }

    static void appendNumberOfSamples(ParsableByteArray parsableByteArray, long j) {
        parsableByteArray.setLimit(parsableByteArray.limit() + 4);
        parsableByteArray.data[parsableByteArray.limit() - 4] = (byte) (j & 255);
        parsableByteArray.data[parsableByteArray.limit() - 3] = (byte) ((j >>> 8) & 255);
        parsableByteArray.data[parsableByteArray.limit() - 2] = (byte) ((j >>> 16) & 255);
        parsableByteArray.data[parsableByteArray.limit() - 1] = (byte) ((j >>> 24) & 255);
    }

    private static int decodeBlockSize(byte b, VorbisSetup vorbisSetup) {
        if (!vorbisSetup.modes[OggUtil.readBits(b, vorbisSetup.iLogModes, 1)].blockFlag) {
            return vorbisSetup.idHeader.blockSize0;
        }
        return vorbisSetup.idHeader.blockSize1;
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public boolean isSeekable() {
        return (this.vorbisSetup == null || this.inputLength == -1) ? false : true;
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public long getPosition(long j) {
        if (j == 0) {
            this.targetGranule = -1L;
            return this.audioStartPosition;
        }
        this.targetGranule = (this.vorbisSetup.idHeader.sampleRate * j) / 1000000;
        long j2 = this.audioStartPosition;
        return Math.max(j2, (((this.inputLength - j2) * j) / this.duration) - DanmakuFactory.MIN_DANMAKU_DURATION);
    }

    static final class VorbisSetup {
        public final VorbisUtil.CommentHeader commentHeader;
        public final int iLogModes;
        public final VorbisUtil.VorbisIdHeader idHeader;
        public final VorbisUtil.Mode[] modes;
        public final byte[] setupHeaderData;

        public VorbisSetup(VorbisUtil.VorbisIdHeader vorbisIdHeader, VorbisUtil.CommentHeader commentHeader, byte[] bArr, VorbisUtil.Mode[] modeArr, int i) {
            this.idHeader = vorbisIdHeader;
            this.commentHeader = commentHeader;
            this.setupHeaderData = bArr;
            this.modes = modeArr;
            this.iLogModes = i;
        }
    }
}

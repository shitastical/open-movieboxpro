package com.google.android.exoplayer.extractor.ts;

import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import com.google.android.exoplayer.extractor.DummyTrackOutput;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class TsExtractor implements Extractor {
    private static final int BASE_EMBEDDED_TRACK_ID = 8192;
    private static final int BUFFER_PACKET_COUNT = 5;
    private static final int BUFFER_SIZE = 940;
    private static final String TAG = "TsExtractor";
    private static final int TS_PACKET_SIZE = 188;
    private static final int TS_PAT_PID = 0;
    private static final int TS_STREAM_TYPE_AAC = 15;
    private static final int TS_STREAM_TYPE_AC3 = 129;
    private static final int TS_STREAM_TYPE_DTS = 138;
    private static final int TS_STREAM_TYPE_E_AC3 = 135;
    private static final int TS_STREAM_TYPE_H262 = 2;
    private static final int TS_STREAM_TYPE_H264 = 27;
    private static final int TS_STREAM_TYPE_H265 = 36;
    private static final int TS_STREAM_TYPE_HDMV_DTS = 130;
    private static final int TS_STREAM_TYPE_ID3 = 21;
    private static final int TS_STREAM_TYPE_MPA = 3;
    private static final int TS_STREAM_TYPE_MPA_LSF = 4;
    private static final int TS_SYNC_BYTE = 71;
    public static final int WORKAROUND_ALLOW_NON_IDR_KEYFRAMES = 1;
    public static final int WORKAROUND_DETECT_ACCESS_UNITS = 8;
    public static final int WORKAROUND_IGNORE_AAC_STREAM = 2;
    public static final int WORKAROUND_IGNORE_H264_STREAM = 4;
    public static final int WORKAROUND_MAP_BY_TYPE = 16;
    private final SparseIntArray continuityCounters;
    Id3Reader id3Reader;
    private int nextEmbeddedTrackId;
    private ExtractorOutput output;
    private final PtsTimestampAdjuster ptsTimestampAdjuster;
    final SparseBooleanArray trackIds;
    private final ParsableByteArray tsPacketBuffer;
    final SparseArray<TsPayloadReader> tsPayloadReaders;
    private final ParsableBitArray tsScratch;
    private final int workaroundFlags;
    private static final long AC3_FORMAT_IDENTIFIER = Util.getIntegerCodeForString("AC-3");
    private static final long E_AC3_FORMAT_IDENTIFIER = Util.getIntegerCodeForString("EAC3");
    private static final long HEVC_FORMAT_IDENTIFIER = Util.getIntegerCodeForString("HEVC");

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void release() {
    }

    static /* synthetic */ int access$208(TsExtractor tsExtractor) {
        int i = tsExtractor.nextEmbeddedTrackId;
        tsExtractor.nextEmbeddedTrackId = i + 1;
        return i;
    }

    public TsExtractor() {
        this(new PtsTimestampAdjuster(0L));
    }

    public TsExtractor(PtsTimestampAdjuster ptsTimestampAdjuster) {
        this(ptsTimestampAdjuster, 0);
    }

    public TsExtractor(PtsTimestampAdjuster ptsTimestampAdjuster, int i) {
        this.ptsTimestampAdjuster = ptsTimestampAdjuster;
        this.workaroundFlags = i;
        this.tsPacketBuffer = new ParsableByteArray(BUFFER_SIZE);
        this.tsScratch = new ParsableBitArray(new byte[3]);
        SparseArray<TsPayloadReader> sparseArray = new SparseArray<>();
        this.tsPayloadReaders = sparseArray;
        sparseArray.put(0, new PatReader());
        this.trackIds = new SparseBooleanArray();
        this.nextEmbeddedTrackId = 8192;
        this.continuityCounters = new SparseIntArray();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        byte[] bArr = this.tsPacketBuffer.data;
        extractorInput.peekFully(bArr, 0, BUFFER_SIZE);
        for (int i = 0; i < 188; i++) {
            int i2 = 0;
            while (true) {
                if (i2 == 5) {
                    extractorInput.skipFully(i);
                    return true;
                }
                if (bArr[(i2 * 188) + i] != 71) {
                    break;
                }
                i2++;
            }
        }
        return false;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.output = extractorOutput;
        extractorOutput.seekMap(SeekMap.UNSEEKABLE);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void seek() {
        this.ptsTimestampAdjuster.reset();
        for (int i = 0; i < this.tsPayloadReaders.size(); i++) {
            this.tsPayloadReaders.valueAt(i).seek();
        }
        this.tsPacketBuffer.reset();
        this.continuityCounters.clear();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        TsPayloadReader tsPayloadReader;
        byte[] bArr = this.tsPacketBuffer.data;
        if (940 - this.tsPacketBuffer.getPosition() < 188) {
            int iBytesLeft = this.tsPacketBuffer.bytesLeft();
            if (iBytesLeft > 0) {
                System.arraycopy(bArr, this.tsPacketBuffer.getPosition(), bArr, 0, iBytesLeft);
            }
            this.tsPacketBuffer.reset(bArr, iBytesLeft);
        }
        while (this.tsPacketBuffer.bytesLeft() < 188) {
            int iLimit = this.tsPacketBuffer.limit();
            int i = extractorInput.read(bArr, iLimit, 940 - iLimit);
            if (i == -1) {
                return -1;
            }
            this.tsPacketBuffer.setLimit(iLimit + i);
        }
        int iLimit2 = this.tsPacketBuffer.limit();
        int position = this.tsPacketBuffer.getPosition();
        while (position < iLimit2 && bArr[position] != 71) {
            position++;
        }
        this.tsPacketBuffer.setPosition(position);
        int i2 = position + 188;
        if (i2 > iLimit2) {
            return 0;
        }
        this.tsPacketBuffer.skipBytes(1);
        this.tsPacketBuffer.readBytes(this.tsScratch, 3);
        if (this.tsScratch.readBit()) {
            this.tsPacketBuffer.setPosition(i2);
            return 0;
        }
        boolean bit = this.tsScratch.readBit();
        this.tsScratch.skipBits(1);
        int bits = this.tsScratch.readBits(13);
        this.tsScratch.skipBits(2);
        boolean bit2 = this.tsScratch.readBit();
        boolean bit3 = this.tsScratch.readBit();
        int bits2 = this.tsScratch.readBits(4);
        int i3 = this.continuityCounters.get(bits, bits2 - 1);
        this.continuityCounters.put(bits, bits2);
        if (i3 == bits2) {
            this.tsPacketBuffer.setPosition(i2);
            return 0;
        }
        boolean z = bits2 != (i3 + 1) % 16;
        if (bit2) {
            this.tsPacketBuffer.skipBytes(this.tsPacketBuffer.readUnsignedByte());
        }
        if (bit3 && (tsPayloadReader = this.tsPayloadReaders.get(bits)) != null) {
            if (z) {
                tsPayloadReader.seek();
            }
            this.tsPacketBuffer.setLimit(i2);
            tsPayloadReader.consume(this.tsPacketBuffer, bit, this.output);
            Assertions.checkState(this.tsPacketBuffer.getPosition() <= i2);
            this.tsPacketBuffer.setLimit(iLimit2);
        }
        this.tsPacketBuffer.setPosition(i2);
        return 0;
    }

    private static abstract class TsPayloadReader {
        public abstract void consume(ParsableByteArray parsableByteArray, boolean z, ExtractorOutput extractorOutput);

        public abstract void seek();

        private TsPayloadReader() {
        }
    }

    private class PatReader extends TsPayloadReader {
        private int crc;
        private final ParsableBitArray patScratch;
        private int sectionBytesRead;
        private final ParsableByteArray sectionData;
        private int sectionLength;

        @Override // com.google.android.exoplayer.extractor.ts.TsExtractor.TsPayloadReader
        public void seek() {
        }

        public PatReader() {
            super();
            this.sectionData = new ParsableByteArray();
            this.patScratch = new ParsableBitArray(new byte[4]);
        }

        @Override // com.google.android.exoplayer.extractor.ts.TsExtractor.TsPayloadReader
        public void consume(ParsableByteArray parsableByteArray, boolean z, ExtractorOutput extractorOutput) {
            if (z) {
                parsableByteArray.skipBytes(parsableByteArray.readUnsignedByte());
                parsableByteArray.readBytes(this.patScratch, 3);
                this.patScratch.skipBits(12);
                this.sectionLength = this.patScratch.readBits(12);
                this.sectionBytesRead = 0;
                this.crc = Util.crc(this.patScratch.data, 0, 3, -1);
                this.sectionData.reset(this.sectionLength);
            }
            int iMin = Math.min(parsableByteArray.bytesLeft(), this.sectionLength - this.sectionBytesRead);
            parsableByteArray.readBytes(this.sectionData.data, this.sectionBytesRead, iMin);
            int i = this.sectionBytesRead + iMin;
            this.sectionBytesRead = i;
            if (i >= this.sectionLength && Util.crc(this.sectionData.data, 0, this.sectionLength, this.crc) == 0) {
                this.sectionData.skipBytes(5);
                int i2 = (this.sectionLength - 9) / 4;
                for (int i3 = 0; i3 < i2; i3++) {
                    this.sectionData.readBytes(this.patScratch, 4);
                    int bits = this.patScratch.readBits(16);
                    this.patScratch.skipBits(3);
                    if (bits == 0) {
                        this.patScratch.skipBits(13);
                    } else {
                        TsExtractor.this.tsPayloadReaders.put(this.patScratch.readBits(13), TsExtractor.this.new PmtReader());
                    }
                }
            }
        }
    }

    private class PmtReader extends TsPayloadReader {
        private int crc;
        private final ParsableBitArray pmtScratch;
        private int sectionBytesRead;
        private final ParsableByteArray sectionData;
        private int sectionLength;

        @Override // com.google.android.exoplayer.extractor.ts.TsExtractor.TsPayloadReader
        public void seek() {
        }

        public PmtReader() {
            super();
            this.pmtScratch = new ParsableBitArray(new byte[5]);
            this.sectionData = new ParsableByteArray();
        }

        /* JADX WARN: Code duplicated, block: B:50:0x0134  */
        @Override // com.google.android.exoplayer.extractor.ts.TsExtractor.TsPayloadReader
        public void consume(ParsableByteArray parsableByteArray, boolean z, ExtractorOutput extractorOutput) {
            ElementaryStreamReader h262Reader;
            ElementaryStreamReader adtsReader;
            int i = 12;
            int i2 = 3;
            if (z) {
                parsableByteArray.skipBytes(parsableByteArray.readUnsignedByte());
                parsableByteArray.readBytes(this.pmtScratch, 3);
                this.pmtScratch.skipBits(12);
                this.sectionLength = this.pmtScratch.readBits(12);
                this.sectionBytesRead = 0;
                this.crc = Util.crc(this.pmtScratch.data, 0, 3, -1);
                this.sectionData.reset(this.sectionLength);
            }
            int iMin = Math.min(parsableByteArray.bytesLeft(), this.sectionLength - this.sectionBytesRead);
            parsableByteArray.readBytes(this.sectionData.data, this.sectionBytesRead, iMin);
            int i3 = this.sectionBytesRead + iMin;
            this.sectionBytesRead = i3;
            if (i3 >= this.sectionLength && Util.crc(this.sectionData.data, 0, this.sectionLength, this.crc) == 0) {
                this.sectionData.skipBytes(7);
                this.sectionData.readBytes(this.pmtScratch, 2);
                this.pmtScratch.skipBits(4);
                int bits = this.pmtScratch.readBits(12);
                this.sectionData.skipBytes(bits);
                if ((TsExtractor.this.workaroundFlags & 16) != 0 && TsExtractor.this.id3Reader == null) {
                    TsExtractor.this.id3Reader = new Id3Reader(extractorOutput.track(21));
                }
                int i4 = ((this.sectionLength - 9) - bits) - 4;
                while (i4 > 0) {
                    this.sectionData.readBytes(this.pmtScratch, 5);
                    int bits2 = this.pmtScratch.readBits(8);
                    this.pmtScratch.skipBits(i2);
                    int bits3 = this.pmtScratch.readBits(13);
                    this.pmtScratch.skipBits(4);
                    int bits4 = this.pmtScratch.readBits(i);
                    if (bits2 == 6) {
                        bits2 = readPrivateDataStreamType(this.sectionData, bits4);
                    } else {
                        this.sectionData.skipBytes(bits4);
                    }
                    i4 -= bits4 + 5;
                    int i5 = (TsExtractor.this.workaroundFlags & 16) != 0 ? bits2 : bits3;
                    if (!TsExtractor.this.trackIds.get(i5)) {
                        if (bits2 == 2) {
                            h262Reader = new H262Reader(extractorOutput.track(i5));
                        } else if (bits2 == i2 || bits2 == 4) {
                            h262Reader = new MpegAudioReader(extractorOutput.track(i5));
                        } else {
                            h262Reader = null;
                            if (bits2 != 15) {
                                if (bits2 != 21) {
                                    if (bits2 != 27) {
                                        if (bits2 == 36) {
                                            h262Reader = new H265Reader(extractorOutput.track(i5), new SeiReader(extractorOutput.track(TsExtractor.access$208(TsExtractor.this))));
                                        } else if (bits2 == 135) {
                                            h262Reader = new Ac3Reader(extractorOutput.track(i5), true);
                                        } else if (bits2 == 138) {
                                            h262Reader = new DtsReader(extractorOutput.track(i5));
                                        } else if (bits2 == 129) {
                                            h262Reader = new Ac3Reader(extractorOutput.track(i5), false);
                                        } else if (bits2 == 130) {
                                            h262Reader = new DtsReader(extractorOutput.track(i5));
                                        }
                                    } else if ((TsExtractor.this.workaroundFlags & 4) == 0) {
                                        adtsReader = new H264Reader(extractorOutput.track(i5), new SeiReader(extractorOutput.track(TsExtractor.access$208(TsExtractor.this))), (TsExtractor.this.workaroundFlags & 1) != 0, (TsExtractor.this.workaroundFlags & 8) != 0);
                                        h262Reader = adtsReader;
                                    }
                                } else if ((TsExtractor.this.workaroundFlags & 16) != 0) {
                                    h262Reader = TsExtractor.this.id3Reader;
                                } else {
                                    h262Reader = new Id3Reader(extractorOutput.track(TsExtractor.access$208(TsExtractor.this)));
                                }
                            } else if ((TsExtractor.this.workaroundFlags & 2) == 0) {
                                adtsReader = new AdtsReader(extractorOutput.track(i5), new DummyTrackOutput());
                                h262Reader = adtsReader;
                            }
                        }
                        if (h262Reader != null) {
                            TsExtractor.this.trackIds.put(i5, true);
                            TsExtractor.this.tsPayloadReaders.put(bits3, new PesReader(h262Reader, TsExtractor.this.ptsTimestampAdjuster));
                        }
                    }
                    i = 12;
                    i2 = 3;
                }
                extractorOutput.endTracks();
            }
        }

        private int readPrivateDataStreamType(ParsableByteArray parsableByteArray, int i) {
            int position = parsableByteArray.getPosition() + i;
            int i2 = -1;
            while (parsableByteArray.getPosition() < position) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                if (unsignedByte == 5) {
                    long unsignedInt = parsableByteArray.readUnsignedInt();
                    if (unsignedInt != TsExtractor.AC3_FORMAT_IDENTIFIER) {
                        if (unsignedInt != TsExtractor.E_AC3_FORMAT_IDENTIFIER) {
                            if (unsignedInt != TsExtractor.HEVC_FORMAT_IDENTIFIER) {
                                break;
                            }
                            i2 = 36;
                            break;
                        }
                        i2 = 135;
                        break;
                    }
                    i2 = 129;
                    break;
                }
                if (unsignedByte == 106) {
                    i2 = 129;
                } else if (unsignedByte == 122) {
                    i2 = 135;
                } else if (unsignedByte == 123) {
                    i2 = 138;
                }
                parsableByteArray.skipBytes(unsignedByte2);
            }
            parsableByteArray.setPosition(position);
            return i2;
        }
    }

    private static final class PesReader extends TsPayloadReader {
        private static final int HEADER_SIZE = 9;
        private static final int MAX_HEADER_EXTENSION_SIZE = 10;
        private static final int PES_SCRATCH_SIZE = 10;
        private static final int STATE_FINDING_HEADER = 0;
        private static final int STATE_READING_BODY = 3;
        private static final int STATE_READING_HEADER = 1;
        private static final int STATE_READING_HEADER_EXTENSION = 2;
        private int bytesRead;
        private boolean dataAlignmentIndicator;
        private boolean dtsFlag;
        private int extendedHeaderLength;
        private int payloadSize;
        private final ElementaryStreamReader pesPayloadReader;
        private final ParsableBitArray pesScratch;
        private boolean ptsFlag;
        private final PtsTimestampAdjuster ptsTimestampAdjuster;
        private boolean seenFirstDts;
        private int state;
        private long timeUs;

        public PesReader(ElementaryStreamReader elementaryStreamReader, PtsTimestampAdjuster ptsTimestampAdjuster) {
            super();
            this.pesPayloadReader = elementaryStreamReader;
            this.ptsTimestampAdjuster = ptsTimestampAdjuster;
            this.pesScratch = new ParsableBitArray(new byte[10]);
            this.state = 0;
        }

        @Override // com.google.android.exoplayer.extractor.ts.TsExtractor.TsPayloadReader
        public void seek() {
            this.state = 0;
            this.bytesRead = 0;
            this.seenFirstDts = false;
            this.pesPayloadReader.seek();
        }

        @Override // com.google.android.exoplayer.extractor.ts.TsExtractor.TsPayloadReader
        public void consume(ParsableByteArray parsableByteArray, boolean z, ExtractorOutput extractorOutput) {
            if (z) {
                int i = this.state;
                if (i == 2) {
                    Log.w(TsExtractor.TAG, "Unexpected start indicator reading extended header");
                } else if (i == 3) {
                    if (this.payloadSize != -1) {
                        Log.w(TsExtractor.TAG, "Unexpected start indicator: expected " + this.payloadSize + " more bytes");
                    }
                    this.pesPayloadReader.packetFinished();
                }
                setState(1);
            }
            while (parsableByteArray.bytesLeft() > 0) {
                int i2 = this.state;
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 == 2) {
                            if (continueRead(parsableByteArray, this.pesScratch.data, Math.min(10, this.extendedHeaderLength)) && continueRead(parsableByteArray, null, this.extendedHeaderLength)) {
                                parseHeaderExtension();
                                this.pesPayloadReader.packetStarted(this.timeUs, this.dataAlignmentIndicator);
                                setState(3);
                            }
                        } else if (i2 == 3) {
                            int iBytesLeft = parsableByteArray.bytesLeft();
                            int i3 = this.payloadSize;
                            int i4 = i3 != -1 ? iBytesLeft - i3 : 0;
                            if (i4 > 0) {
                                iBytesLeft -= i4;
                                parsableByteArray.setLimit(parsableByteArray.getPosition() + iBytesLeft);
                            }
                            this.pesPayloadReader.consume(parsableByteArray);
                            int i5 = this.payloadSize;
                            if (i5 != -1) {
                                int i6 = i5 - iBytesLeft;
                                this.payloadSize = i6;
                                if (i6 == 0) {
                                    this.pesPayloadReader.packetFinished();
                                    setState(1);
                                }
                            }
                        }
                    } else if (continueRead(parsableByteArray, this.pesScratch.data, 9)) {
                        setState(parseHeader() ? 2 : 0);
                    }
                } else {
                    parsableByteArray.skipBytes(parsableByteArray.bytesLeft());
                }
            }
        }

        private void setState(int i) {
            this.state = i;
            this.bytesRead = 0;
        }

        private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i) {
            int iMin = Math.min(parsableByteArray.bytesLeft(), i - this.bytesRead);
            if (iMin <= 0) {
                return true;
            }
            if (bArr == null) {
                parsableByteArray.skipBytes(iMin);
            } else {
                parsableByteArray.readBytes(bArr, this.bytesRead, iMin);
            }
            int i2 = this.bytesRead + iMin;
            this.bytesRead = i2;
            return i2 == i;
        }

        private boolean parseHeader() {
            this.pesScratch.setPosition(0);
            int bits = this.pesScratch.readBits(24);
            if (bits != 1) {
                Log.w(TsExtractor.TAG, "Unexpected start code prefix: " + bits);
                this.payloadSize = -1;
                return false;
            }
            this.pesScratch.skipBits(8);
            int bits2 = this.pesScratch.readBits(16);
            this.pesScratch.skipBits(5);
            this.dataAlignmentIndicator = this.pesScratch.readBit();
            this.pesScratch.skipBits(2);
            this.ptsFlag = this.pesScratch.readBit();
            this.dtsFlag = this.pesScratch.readBit();
            this.pesScratch.skipBits(6);
            int bits3 = this.pesScratch.readBits(8);
            this.extendedHeaderLength = bits3;
            if (bits2 == 0) {
                this.payloadSize = -1;
            } else {
                this.payloadSize = (bits2 - 3) - bits3;
            }
            return true;
        }

        private void parseHeaderExtension() {
            this.pesScratch.setPosition(0);
            this.timeUs = -1L;
            if (this.ptsFlag) {
                this.pesScratch.skipBits(4);
                long bits = ((long) this.pesScratch.readBits(3)) << 30;
                this.pesScratch.skipBits(1);
                long bits2 = bits | ((long) (this.pesScratch.readBits(15) << 15));
                this.pesScratch.skipBits(1);
                long bits3 = bits2 | ((long) this.pesScratch.readBits(15));
                this.pesScratch.skipBits(1);
                if (!this.seenFirstDts && this.dtsFlag) {
                    this.pesScratch.skipBits(4);
                    long bits4 = ((long) this.pesScratch.readBits(3)) << 30;
                    this.pesScratch.skipBits(1);
                    long bits5 = bits4 | ((long) (this.pesScratch.readBits(15) << 15));
                    this.pesScratch.skipBits(1);
                    long bits6 = bits5 | ((long) this.pesScratch.readBits(15));
                    this.pesScratch.skipBits(1);
                    this.ptsTimestampAdjuster.adjustTimestamp(bits6);
                    this.seenFirstDts = true;
                }
                this.timeUs = this.ptsTimestampAdjuster.adjustTimestamp(bits3);
            }
        }
    }
}

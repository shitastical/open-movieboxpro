package com.google.android.exoplayer.extractor.webm;

import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.ChunkIndex;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.text.eia608.ClosedCaptionCtrl;
import com.google.android.exoplayer.util.LongArray;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public final class WebmExtractor implements Extractor {
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int MP3_MAX_INPUT_SIZE = 4096;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final int SUBRIP_TIMECODE_LENGTH = 12;
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNKNOWN = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentDrmInitData;
    private boolean sentSeekMap;
    private final ParsableByteArray subripSample;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, ClosedCaptionCtrl.ERASE_DISPLAYED_MEMORY, 48, 48, 48, 32, ClosedCaptionCtrl.CARRIAGE_RETURN, ClosedCaptionCtrl.CARRIAGE_RETURN, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, ClosedCaptionCtrl.ERASE_DISPLAYED_MEMORY, 48, 48, 48, 10};
    private static final byte[] SUBRIP_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);

    int getElementType(int i) {
        switch (i) {
            case ID_TRACK_TYPE /* 131 */:
            case ID_BLOCK_DURATION /* 155 */:
            case 159:
            case 176:
            case ID_CUE_TIME /* 179 */:
            case ID_PIXEL_HEIGHT /* 186 */:
            case ID_TRACK_NUMBER /* 215 */:
            case ID_TIME_CODE /* 231 */:
            case ID_CUE_CLUSTER_POSITION /* 241 */:
            case ID_REFERENCE_BLOCK /* 251 */:
            case ID_CONTENT_COMPRESSION_ALGORITHM /* 16980 */:
            case ID_DOC_TYPE_READ_VERSION /* 17029 */:
            case ID_EBML_READ_VERSION /* 17143 */:
            case ID_CONTENT_ENCRYPTION_ALGORITHM /* 18401 */:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /* 18408 */:
            case ID_CONTENT_ENCODING_ORDER /* 20529 */:
            case ID_CONTENT_ENCODING_SCOPE /* 20530 */:
            case ID_SEEK_POSITION /* 21420 */:
            case ID_DISPLAY_WIDTH /* 21680 */:
            case ID_DISPLAY_UNIT /* 21682 */:
            case ID_DISPLAY_HEIGHT /* 21690 */:
            case ID_CODEC_DELAY /* 22186 */:
            case ID_SEEK_PRE_ROLL /* 22203 */:
            case ID_AUDIO_BIT_DEPTH /* 25188 */:
            case ID_DEFAULT_DURATION /* 2352003 */:
            case ID_TIMECODE_SCALE /* 2807729 */:
                return 2;
            case 134:
            case 17026:
            case ID_LANGUAGE /* 2274716 */:
                return 3;
            case 160:
            case ID_TRACK_ENTRY /* 174 */:
            case 183:
            case 187:
            case 224:
            case ID_AUDIO /* 225 */:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS /* 18407 */:
            case ID_SEEK /* 19899 */:
            case ID_CONTENT_COMPRESSION /* 20532 */:
            case ID_CONTENT_ENCRYPTION /* 20533 */:
            case ID_CONTENT_ENCODING /* 25152 */:
            case ID_CONTENT_ENCODINGS /* 28032 */:
            case ID_SEEK_HEAD /* 290298740 */:
            case 357149030:
            case ID_TRACKS /* 374648427 */:
            case ID_SEGMENT /* 408125543 */:
            case ID_EBML /* 440786851 */:
            case ID_CUES /* 475249515 */:
            case ID_CLUSTER /* 524531317 */:
                return 1;
            case 161:
            case 163:
            case ID_CONTENT_COMPRESSION_SETTINGS /* 16981 */:
            case ID_CONTENT_ENCRYPTION_KEY_ID /* 18402 */:
            case ID_SEEK_ID /* 21419 */:
            case ID_CODEC_PRIVATE /* 25506 */:
                return 4;
            case 181:
            case ID_DURATION /* 17545 */:
                return 5;
            default:
                return 0;
        }
    }

    boolean isLevel1Element(int i) {
        return i == 357149030 || i == ID_CLUSTER || i == ID_CUES || i == ID_TRACKS;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void release() {
    }

    public WebmExtractor() {
        this(new DefaultEbmlReader());
    }

    WebmExtractor(EbmlReader ebmlReader) {
        this.segmentContentPosition = -1L;
        this.segmentContentSize = -1L;
        this.timecodeScale = -1L;
        this.durationTimecode = -1L;
        this.durationUs = -1L;
        this.cuesContentPosition = -1L;
        this.seekPositionAfterBuildingCues = -1L;
        this.clusterTimecodeUs = -1L;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlReaderOutput());
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subripSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return new Sniffer().sniff(extractorInput);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void seek() {
        this.clusterTimecodeUs = -1L;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetSample();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        this.sampleRead = false;
        boolean z = true;
        while (z && !this.sampleRead) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        return z ? 0 : -1;
    }

    void startMasterElement(int i, long j, long j2) throws ParserException {
        if (i == 160) {
            this.sampleSeenReferenceBlock = false;
            return;
        }
        if (i == ID_TRACK_ENTRY) {
            this.currentTrack = new Track();
            return;
        }
        if (i == 187) {
            this.seenClusterPositionForCurrentCuePoint = false;
            return;
        }
        if (i == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1L;
            return;
        }
        if (i == ID_CONTENT_ENCRYPTION) {
            this.currentTrack.hasContentEncryption = true;
            return;
        }
        if (i == ID_SEGMENT) {
            long j3 = this.segmentContentPosition;
            if (j3 != -1 && j3 != j) {
                throw new ParserException("Multiple Segment elements not supported");
            }
            this.segmentContentPosition = j;
            this.segmentContentSize = j2;
            return;
        }
        if (i == ID_CUES) {
            this.cueTimesUs = new LongArray();
            this.cueClusterPositions = new LongArray();
        } else if (i == ID_CLUSTER && !this.sentSeekMap) {
            if (this.cuesContentPosition != -1) {
                this.seekForCues = true;
            } else {
                this.extractorOutput.seekMap(SeekMap.UNSEEKABLE);
                this.sentSeekMap = true;
            }
        }
    }

    void endMasterElement(int i) throws ParserException {
        if (i == 160) {
            if (this.blockState != 2) {
                return;
            }
            if (!this.sampleSeenReferenceBlock) {
                this.blockFlags |= 1;
            }
            commitSampleToOutput(this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
            this.blockState = 0;
            return;
        }
        if (i == ID_TRACK_ENTRY) {
            if (this.tracks.get(this.currentTrack.number) == null && isCodecSupported(this.currentTrack.codecId)) {
                Track track = this.currentTrack;
                track.initializeOutput(this.extractorOutput, track.number, this.durationUs);
                this.tracks.put(this.currentTrack.number, this.currentTrack);
            }
            this.currentTrack = null;
            return;
        }
        if (i == ID_SEEK) {
            int i2 = this.seekEntryId;
            if (i2 != -1) {
                long j = this.seekEntryPosition;
                if (j != -1) {
                    if (i2 == ID_CUES) {
                        this.cuesContentPosition = j;
                        return;
                    }
                    return;
                }
            }
            throw new ParserException("Mandatory element SeekID or SeekPosition not found");
        }
        if (i == ID_CONTENT_ENCODING) {
            if (this.currentTrack.hasContentEncryption) {
                if (this.currentTrack.encryptionKeyId == null) {
                    throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
                }
                if (this.sentDrmInitData) {
                    return;
                }
                this.extractorOutput.drmInitData(new DrmInitData.Universal(new DrmInitData.SchemeInitData("video/webm", this.currentTrack.encryptionKeyId)));
                this.sentDrmInitData = true;
                return;
            }
            return;
        }
        if (i == ID_CONTENT_ENCODINGS) {
            if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                throw new ParserException("Combining encryption and compression is not supported");
            }
            return;
        }
        if (i == 357149030) {
            if (this.timecodeScale == -1) {
                this.timecodeScale = 1000000L;
            }
            long j2 = this.durationTimecode;
            if (j2 != -1) {
                this.durationUs = scaleTimecodeToUs(j2);
                return;
            }
            return;
        }
        if (i != ID_TRACKS) {
            if (i == ID_CUES && !this.sentSeekMap) {
                this.extractorOutput.seekMap(buildSeekMap());
                this.sentSeekMap = true;
                return;
            }
            return;
        }
        if (this.tracks.size() == 0) {
            throw new ParserException("No valid tracks were found");
        }
        this.extractorOutput.endTracks();
    }

    void integerElement(int i, long j) throws ParserException {
        switch (i) {
            case ID_TRACK_TYPE /* 131 */:
                this.currentTrack.type = (int) j;
                return;
            case ID_BLOCK_DURATION /* 155 */:
                this.blockDurationUs = scaleTimecodeToUs(j);
                return;
            case 159:
                this.currentTrack.channelCount = (int) j;
                return;
            case 176:
                this.currentTrack.width = (int) j;
                return;
            case ID_CUE_TIME /* 179 */:
                this.cueTimesUs.add(scaleTimecodeToUs(j));
                return;
            case ID_PIXEL_HEIGHT /* 186 */:
                this.currentTrack.height = (int) j;
                return;
            case ID_TRACK_NUMBER /* 215 */:
                this.currentTrack.number = (int) j;
                return;
            case ID_TIME_CODE /* 231 */:
                this.clusterTimecodeUs = scaleTimecodeToUs(j);
                return;
            case ID_CUE_CLUSTER_POSITION /* 241 */:
                if (this.seenClusterPositionForCurrentCuePoint) {
                    return;
                }
                this.cueClusterPositions.add(j);
                this.seenClusterPositionForCurrentCuePoint = true;
                return;
            case ID_REFERENCE_BLOCK /* 251 */:
                this.sampleSeenReferenceBlock = true;
                return;
            case ID_CONTENT_COMPRESSION_ALGORITHM /* 16980 */:
                if (j == 3) {
                    return;
                }
                throw new ParserException("ContentCompAlgo " + j + " not supported");
            case ID_DOC_TYPE_READ_VERSION /* 17029 */:
                if (j < 1 || j > 2) {
                    throw new ParserException("DocTypeReadVersion " + j + " not supported");
                }
                return;
            case ID_EBML_READ_VERSION /* 17143 */:
                if (j == 1) {
                    return;
                }
                throw new ParserException("EBMLReadVersion " + j + " not supported");
            case ID_CONTENT_ENCRYPTION_ALGORITHM /* 18401 */:
                if (j == 5) {
                    return;
                }
                throw new ParserException("ContentEncAlgo " + j + " not supported");
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /* 18408 */:
                if (j == 1) {
                    return;
                }
                throw new ParserException("AESSettingsCipherMode " + j + " not supported");
            case ID_CONTENT_ENCODING_ORDER /* 20529 */:
                if (j == 0) {
                    return;
                }
                throw new ParserException("ContentEncodingOrder " + j + " not supported");
            case ID_CONTENT_ENCODING_SCOPE /* 20530 */:
                if (j == 1) {
                    return;
                }
                throw new ParserException("ContentEncodingScope " + j + " not supported");
            case ID_SEEK_POSITION /* 21420 */:
                this.seekEntryPosition = j + this.segmentContentPosition;
                return;
            case ID_DISPLAY_WIDTH /* 21680 */:
                this.currentTrack.displayWidth = (int) j;
                return;
            case ID_DISPLAY_UNIT /* 21682 */:
                this.currentTrack.displayUnit = (int) j;
                return;
            case ID_DISPLAY_HEIGHT /* 21690 */:
                this.currentTrack.displayHeight = (int) j;
                return;
            case ID_CODEC_DELAY /* 22186 */:
                this.currentTrack.codecDelayNs = j;
                return;
            case ID_SEEK_PRE_ROLL /* 22203 */:
                this.currentTrack.seekPreRollNs = j;
                return;
            case ID_AUDIO_BIT_DEPTH /* 25188 */:
                this.currentTrack.audioBitDepth = (int) j;
                return;
            case ID_DEFAULT_DURATION /* 2352003 */:
                this.currentTrack.defaultSampleDurationNs = (int) j;
                return;
            case ID_TIMECODE_SCALE /* 2807729 */:
                this.timecodeScale = j;
                return;
            default:
                return;
        }
    }

    void floatElement(int i, double d) {
        if (i == 181) {
            this.currentTrack.sampleRate = (int) d;
        } else {
            if (i != ID_DURATION) {
                return;
            }
            this.durationTimecode = (long) d;
        }
    }

    void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            this.currentTrack.codecId = str;
            return;
        }
        if (i != 17026) {
            if (i != ID_LANGUAGE) {
                return;
            }
            this.currentTrack.language = str;
        } else {
            if (DOC_TYPE_WEBM.equals(str) || DOC_TYPE_MATROSKA.equals(str)) {
                return;
            }
            throw new ParserException("DocType " + str + " not supported");
        }
    }

    void binaryElement(int i, int i2, ExtractorInput extractorInput) throws InterruptedException, IOException {
        int i3;
        long j;
        int i4;
        int i5;
        int i6;
        if (i != 161 && i != 163) {
            if (i == ID_CONTENT_COMPRESSION_SETTINGS) {
                this.currentTrack.sampleStrippedBytes = new byte[i2];
                extractorInput.readFully(this.currentTrack.sampleStrippedBytes, 0, i2);
                return;
            }
            if (i == ID_CONTENT_ENCRYPTION_KEY_ID) {
                this.currentTrack.encryptionKeyId = new byte[i2];
                extractorInput.readFully(this.currentTrack.encryptionKeyId, 0, i2);
                return;
            }
            if (i == ID_SEEK_ID) {
                Arrays.fill(this.seekEntryIdBytes.data, (byte) 0);
                extractorInput.readFully(this.seekEntryIdBytes.data, 4 - i2, i2);
                this.seekEntryIdBytes.setPosition(0);
                this.seekEntryId = (int) this.seekEntryIdBytes.readUnsignedInt();
                return;
            }
            if (i == ID_CODEC_PRIVATE) {
                this.currentTrack.codecPrivate = new byte[i2];
                extractorInput.readFully(this.currentTrack.codecPrivate, 0, i2);
                return;
            } else {
                throw new ParserException("Unexpected id: " + i);
            }
        }
        int i7 = 1;
        if (this.blockState == 0) {
            this.blockTrackNumber = (int) this.varintReader.readUnsignedVarint(extractorInput, false, true, 8);
            this.blockTrackNumberLength = this.varintReader.getLastLength();
            this.blockDurationUs = -1L;
            this.blockState = 1;
            this.scratch.reset();
        }
        Track track = this.tracks.get(this.blockTrackNumber);
        if (track == null) {
            extractorInput.skipFully(i2 - this.blockTrackNumberLength);
            this.blockState = 0;
            return;
        }
        if (this.blockState == 1) {
            readScratch(extractorInput, 3);
            int i8 = (this.scratch.data[2] & 6) >> 1;
            byte b = 255;
            if (i8 == 0) {
                this.blockLacingSampleCount = 1;
                int[] iArrEnsureArrayCapacity = ensureArrayCapacity(this.blockLacingSampleSizes, 1);
                this.blockLacingSampleSizes = iArrEnsureArrayCapacity;
                iArrEnsureArrayCapacity[0] = (i2 - this.blockTrackNumberLength) - 3;
            } else {
                if (i != 163) {
                    throw new ParserException("Lacing only supported in SimpleBlocks.");
                }
                int i9 = 4;
                readScratch(extractorInput, 4);
                int i10 = (this.scratch.data[3] & 255) + 1;
                this.blockLacingSampleCount = i10;
                int[] iArrEnsureArrayCapacity2 = ensureArrayCapacity(this.blockLacingSampleSizes, i10);
                this.blockLacingSampleSizes = iArrEnsureArrayCapacity2;
                if (i8 == 2) {
                    int i11 = (i2 - this.blockTrackNumberLength) - 4;
                    int i12 = this.blockLacingSampleCount;
                    Arrays.fill(iArrEnsureArrayCapacity2, 0, i12, i11 / i12);
                } else if (i8 == 1) {
                    int i13 = 0;
                    int i14 = 0;
                    while (true) {
                        i4 = this.blockLacingSampleCount;
                        if (i13 >= i4 - 1) {
                            break;
                        }
                        this.blockLacingSampleSizes[i13] = 0;
                        while (true) {
                            i5 = i9 + 1;
                            readScratch(extractorInput, i5);
                            int i15 = this.scratch.data[i9] & 255;
                            int[] iArr = this.blockLacingSampleSizes;
                            i6 = iArr[i13] + i15;
                            iArr[i13] = i6;
                            if (i15 != 255) {
                                break;
                            } else {
                                i9 = i5;
                            }
                        }
                        i14 += i6;
                        i13++;
                        i9 = i5;
                    }
                    this.blockLacingSampleSizes[i4 - 1] = ((i2 - this.blockTrackNumberLength) - i9) - i14;
                } else {
                    if (i8 != 3) {
                        throw new ParserException("Unexpected lacing value: " + i8);
                    }
                    int i16 = 0;
                    int i17 = 0;
                    while (true) {
                        int i18 = this.blockLacingSampleCount;
                        if (i16 < i18 - 1) {
                            this.blockLacingSampleSizes[i16] = 0;
                            int i19 = i9 + 1;
                            readScratch(extractorInput, i19);
                            if (this.scratch.data[i9] == 0) {
                                throw new ParserException("No valid varint length mask found");
                            }
                            int i20 = 0;
                            while (true) {
                                if (i20 >= 8) {
                                    i3 = i16;
                                    j = 0;
                                    break;
                                }
                                int i21 = i7 << (7 - i20);
                                if ((this.scratch.data[i9] & i21) != 0) {
                                    i19 += i20;
                                    readScratch(extractorInput, i19);
                                    i3 = i16;
                                    j = (~i21) & this.scratch.data[i9] & b;
                                    int i22 = i9 + 1;
                                    while (i22 < i19) {
                                        j = (j << 8) | ((long) (this.scratch.data[i22] & b));
                                        i22++;
                                        b = 255;
                                    }
                                    if (i3 <= 0) {
                                        break;
                                    }
                                    j -= (1 << ((i20 * 7) + 6)) - 1;
                                    break;
                                }
                                i20++;
                                i7 = 1;
                                b = 255;
                            }
                            i9 = i19;
                            if (j < -2147483648L || j > 2147483647L) {
                                throw new ParserException("EBML lacing sample size out of range.");
                            }
                            int i23 = (int) j;
                            int[] iArr2 = this.blockLacingSampleSizes;
                            if (i3 != 0) {
                                i23 += iArr2[i3 - 1];
                            }
                            iArr2[i3] = i23;
                            i17 += i23;
                            i16 = i3 + 1;
                            i7 = 1;
                            b = 255;
                        } else {
                            this.blockLacingSampleSizes[i18 - 1] = ((i2 - this.blockTrackNumberLength) - i9) - i17;
                            break;
                        }
                    }
                }
            }
            this.blockTimeUs = this.clusterTimecodeUs + scaleTimecodeToUs((this.scratch.data[0] << 8) | (this.scratch.data[1] & 255));
            this.blockFlags = ((track.type == 2 || (i == 163 && (this.scratch.data[2] & 128) == 128)) ? 1 : 0) | ((this.scratch.data[2] & 8) == 8 ? 134217728 : 0);
            this.blockState = 2;
            this.blockLacingSampleIndex = 0;
        }
        if (i != 163) {
            writeSampleData(extractorInput, track, this.blockLacingSampleSizes[0]);
            return;
        }
        while (true) {
            int i24 = this.blockLacingSampleIndex;
            if (i24 < this.blockLacingSampleCount) {
                writeSampleData(extractorInput, track, this.blockLacingSampleSizes[i24]);
                commitSampleToOutput(track, this.blockTimeUs + ((long) ((this.blockLacingSampleIndex * track.defaultSampleDurationNs) / 1000)));
                this.blockLacingSampleIndex++;
            } else {
                this.blockState = 0;
                return;
            }
        }
    }

    private void commitSampleToOutput(Track track, long j) {
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubripSample(track);
        }
        track.output.sampleMetadata(j, this.blockFlags, this.sampleBytesWritten, 0, track.encryptionKeyId);
        this.sampleRead = true;
        resetSample();
    }

    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = (byte) 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws InterruptedException, IOException {
        if (this.scratch.limit() >= i) {
            return;
        }
        if (this.scratch.capacity() < i) {
            ParsableByteArray parsableByteArray = this.scratch;
            parsableByteArray.reset(Arrays.copyOf(parsableByteArray.data, Math.max(this.scratch.data.length * 2, i)), this.scratch.limit());
        }
        extractorInput.readFully(this.scratch.data, this.scratch.limit(), i - this.scratch.limit());
        this.scratch.setLimit(i);
    }

    private void writeSampleData(ExtractorInput extractorInput, Track track, int i) throws InterruptedException, IOException {
        int i2;
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            byte[] bArr = SUBRIP_PREFIX;
            int length = bArr.length + i;
            if (this.subripSample.capacity() < length) {
                this.subripSample.data = Arrays.copyOf(bArr, length + i);
            }
            extractorInput.readFully(this.subripSample.data, bArr.length, i);
            this.subripSample.setPosition(0);
            this.subripSample.setLimit(length);
            return;
        }
        TrackOutput trackOutput = track.output;
        if (!this.sampleEncodingHandled) {
            if (track.hasContentEncryption) {
                this.blockFlags &= -3;
                if (!this.sampleSignalByteRead) {
                    extractorInput.readFully(this.scratch.data, 0, 1);
                    this.sampleBytesRead++;
                    if ((this.scratch.data[0] & 128) == 128) {
                        throw new ParserException("Extension bit is set in signal byte");
                    }
                    this.sampleSignalByte = this.scratch.data[0];
                    this.sampleSignalByteRead = true;
                }
                byte b = this.sampleSignalByte;
                if ((b & 1) == 1) {
                    boolean z = (b & 2) == 2;
                    this.blockFlags |= 2;
                    if (!this.sampleInitializationVectorRead) {
                        extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                        this.sampleBytesRead += 8;
                        this.sampleInitializationVectorRead = true;
                        this.scratch.data[0] = (byte) ((z ? 128 : 0) | 8);
                        this.scratch.setPosition(0);
                        trackOutput.sampleData(this.scratch, 1);
                        this.sampleBytesWritten++;
                        this.encryptionInitializationVector.setPosition(0);
                        trackOutput.sampleData(this.encryptionInitializationVector, 8);
                        this.sampleBytesWritten += 8;
                    }
                    if (z) {
                        if (!this.samplePartitionCountRead) {
                            extractorInput.readFully(this.scratch.data, 0, 1);
                            this.sampleBytesRead++;
                            this.scratch.setPosition(0);
                            this.samplePartitionCount = this.scratch.readUnsignedByte();
                            this.samplePartitionCountRead = true;
                        }
                        int i3 = this.samplePartitionCount * 4;
                        if (this.scratch.limit() < i3) {
                            this.scratch.reset(new byte[i3], i3);
                        }
                        extractorInput.readFully(this.scratch.data, 0, i3);
                        this.sampleBytesRead += i3;
                        this.scratch.setPosition(0);
                        this.scratch.setLimit(i3);
                        short s = (short) ((this.samplePartitionCount / 2) + 1);
                        int i4 = (s * 6) + 2;
                        ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                        if (byteBuffer == null || byteBuffer.capacity() < i4) {
                            this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i4);
                        }
                        this.encryptionSubsampleDataBuffer.position(0);
                        this.encryptionSubsampleDataBuffer.putShort(s);
                        int i5 = 0;
                        int i6 = 0;
                        while (true) {
                            i2 = this.samplePartitionCount;
                            if (i5 >= i2) {
                                break;
                            }
                            int unsignedIntToInt = this.scratch.readUnsignedIntToInt();
                            if (i5 % 2 == 0) {
                                this.encryptionSubsampleDataBuffer.putShort((short) (unsignedIntToInt - i6));
                            } else {
                                this.encryptionSubsampleDataBuffer.putInt(unsignedIntToInt - i6);
                            }
                            i5++;
                            i6 = unsignedIntToInt;
                        }
                        int i7 = (i - this.sampleBytesRead) - i6;
                        if (i2 % 2 == 1) {
                            this.encryptionSubsampleDataBuffer.putInt(i7);
                        } else {
                            this.encryptionSubsampleDataBuffer.putShort((short) i7);
                            this.encryptionSubsampleDataBuffer.putInt(0);
                        }
                        this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i4);
                        trackOutput.sampleData(this.encryptionSubsampleData, i4);
                        this.sampleBytesWritten += i4;
                    }
                }
            } else if (track.sampleStrippedBytes != null) {
                this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
            }
            this.sampleEncodingHandled = true;
        }
        int iLimit = i + this.sampleStrippedBytes.limit();
        if (!CODEC_ID_H264.equals(track.codecId) && !CODEC_ID_H265.equals(track.codecId)) {
            while (true) {
                int i8 = this.sampleBytesRead;
                if (i8 >= iLimit) {
                    break;
                } else {
                    readToOutput(extractorInput, trackOutput, iLimit - i8);
                }
            }
        } else {
            byte[] bArr2 = this.nalLength.data;
            bArr2[0] = 0;
            bArr2[1] = 0;
            bArr2[2] = 0;
            int i9 = track.nalUnitLengthFieldLength;
            int i10 = 4 - track.nalUnitLengthFieldLength;
            while (this.sampleBytesRead < iLimit) {
                int i11 = this.sampleCurrentNalBytesRemaining;
                if (i11 == 0) {
                    readToTarget(extractorInput, bArr2, i10, i9);
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    trackOutput.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                } else {
                    this.sampleCurrentNalBytesRemaining = i11 - readToOutput(extractorInput, trackOutput, i11);
                }
            }
        }
        if (CODEC_ID_VORBIS.equals(track.codecId)) {
            this.vorbisNumPageSamples.setPosition(0);
            trackOutput.sampleData(this.vorbisNumPageSamples, 4);
            this.sampleBytesWritten += 4;
        }
    }

    private void writeSubripSample(Track track) {
        setSubripSampleEndTimecode(this.subripSample.data, this.blockDurationUs);
        TrackOutput trackOutput = track.output;
        ParsableByteArray parsableByteArray = this.subripSample;
        trackOutput.sampleData(parsableByteArray, parsableByteArray.limit());
        this.sampleBytesWritten += this.subripSample.limit();
    }

    private static void setSubripSampleEndTimecode(byte[] bArr, long j) {
        byte[] bytes;
        if (j == -1) {
            bytes = SUBRIP_TIMECODE_EMPTY;
        } else {
            int i = (int) (j / 3600000000L);
            long j2 = j - (((long) i) * 3600000000L);
            int i2 = (int) (j2 / 60000000);
            long j3 = j2 - ((long) (60000000 * i2));
            int i3 = (int) (j3 / 1000000);
            bytes = String.format(Locale.US, "%02d:%02d:%02d,%03d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) ((j3 - ((long) (1000000 * i3))) / 1000))).getBytes();
        }
        System.arraycopy(bytes, 0, bArr, 19, 12);
    }

    private void readToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        int iMin = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + iMin, i2 - iMin);
        if (iMin > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, iMin);
        }
        this.sampleBytesRead += i2;
    }

    private int readToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws InterruptedException, IOException {
        int iSampleData;
        int iBytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (iBytesLeft > 0) {
            iSampleData = Math.min(i, iBytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, iSampleData);
        } else {
            iSampleData = trackOutput.sampleData(extractorInput, i, false);
        }
        this.sampleBytesRead += iSampleData;
        this.sampleBytesWritten += iSampleData;
        return iSampleData;
    }

    private SeekMap buildSeekMap() {
        LongArray longArray;
        LongArray longArray2;
        if (this.segmentContentPosition == -1 || this.durationUs == -1 || (longArray = this.cueTimesUs) == null || longArray.size() == 0 || (longArray2 = this.cueClusterPositions) == null || longArray2.size() != this.cueTimesUs.size()) {
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
            return SeekMap.UNSEEKABLE;
        }
        int size = this.cueTimesUs.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jArr3[i2] = this.cueTimesUs.get(i2);
            jArr[i2] = this.segmentContentPosition + this.cueClusterPositions.get(i2);
        }
        while (true) {
            int i3 = size - 1;
            if (i < i3) {
                int i4 = i + 1;
                iArr[i] = (int) (jArr[i4] - jArr[i]);
                jArr2[i] = jArr3[i4] - jArr3[i];
                i = i4;
            } else {
                iArr[i3] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i3]);
                jArr2[i3] = this.durationUs - jArr3[i3];
                this.cueTimesUs = null;
                this.cueClusterPositions = null;
                return new ChunkIndex(iArr, jArr, jArr2, jArr3);
            }
        }
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j2 = this.seekPositionAfterBuildingCues;
            if (j2 != -1) {
                positionHolder.position = j2;
                this.seekPositionAfterBuildingCues = -1L;
                return true;
            }
        }
        return false;
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        long j2 = this.timecodeScale;
        if (j2 == -1) {
            throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
        }
        return Util.scaleLargeTimestamp(j, j2, 1000L);
    }

    private static boolean isCodecSupported(String str) {
        return CODEC_ID_VP8.equals(str) || CODEC_ID_VP9.equals(str) || CODEC_ID_MPEG2.equals(str) || CODEC_ID_MPEG4_SP.equals(str) || CODEC_ID_MPEG4_ASP.equals(str) || CODEC_ID_MPEG4_AP.equals(str) || CODEC_ID_H264.equals(str) || CODEC_ID_H265.equals(str) || CODEC_ID_FOURCC.equals(str) || CODEC_ID_OPUS.equals(str) || CODEC_ID_VORBIS.equals(str) || CODEC_ID_AAC.equals(str) || CODEC_ID_MP3.equals(str) || CODEC_ID_AC3.equals(str) || CODEC_ID_E_AC3.equals(str) || CODEC_ID_TRUEHD.equals(str) || CODEC_ID_DTS.equals(str) || CODEC_ID_DTS_EXPRESS.equals(str) || CODEC_ID_DTS_LOSSLESS.equals(str) || CODEC_ID_FLAC.equals(str) || CODEC_ID_ACM.equals(str) || CODEC_ID_PCM_INT_LIT.equals(str) || CODEC_ID_SUBRIP.equals(str) || CODEC_ID_VOBSUB.equals(str) || CODEC_ID_PGS.equals(str);
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        return iArr.length >= i ? iArr : new int[Math.max(iArr.length * 2, i)];
    }

    private final class InnerEbmlReaderOutput implements EbmlReaderOutput {
        private InnerEbmlReaderOutput() {
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public int getElementType(int i) {
            return WebmExtractor.this.getElementType(i);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public boolean isLevel1Element(int i) {
            return WebmExtractor.this.isLevel1Element(i);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public void startMasterElement(int i, long j, long j2) throws ParserException {
            WebmExtractor.this.startMasterElement(i, j, j2);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public void endMasterElement(int i) throws ParserException {
            WebmExtractor.this.endMasterElement(i);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public void integerElement(int i, long j) throws ParserException {
            WebmExtractor.this.integerElement(i, j);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public void floatElement(int i, double d) throws ParserException {
            WebmExtractor.this.floatElement(i, d);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public void stringElement(int i, String str) throws ParserException {
            WebmExtractor.this.stringElement(i, str);
        }

        @Override // com.google.android.exoplayer.extractor.webm.EbmlReaderOutput
        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws InterruptedException, IOException {
            WebmExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    private static final class Track {
        private static final int DISPLAY_UNIT_PIXELS = 0;
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public byte[] encryptionKeyId;
        public boolean hasContentEncryption;
        public int height;
        private String language;
        public int nalUnitLengthFieldLength;
        public int number;
        public TrackOutput output;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int type;
        public int width;

        private Track() {
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0L;
            this.seekPreRollNs = 0L;
            this.language = "eng";
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:151:0x02ba  */
        /* JADX WARN: Code duplicated, block: B:152:0x02d6  */
        /* JADX WARN: Code duplicated, block: B:154:0x02dc  */
        /* JADX WARN: Code duplicated, block: B:156:0x02e0  */
        /* JADX WARN: Code duplicated, block: B:158:0x02e4  */
        /* JADX WARN: Code duplicated, block: B:161:0x02ec  */
        /* JADX WARN: Code duplicated, block: B:165:0x02f4  */
        /* JADX WARN: Code duplicated, block: B:168:0x0306  */
        /* JADX WARN: Code duplicated, block: B:170:0x0324  */
        /* JADX WARN: Code duplicated, block: B:172:0x032a  */
        /* JADX WARN: Code duplicated, block: B:173:0x033d  */
        /* JADX WARN: Code duplicated, block: B:175:0x0343  */
        /* JADX WARN: Code duplicated, block: B:4:0x0012  */
        public void initializeOutput(ExtractorOutput extractorOutput, int i, long j) throws ParserException {
            byte b;
            int pcmEncoding;
            List<byte[]> vorbisCodecPrivate;
            int i2;
            int i3;
            MediaFormat mediaFormatCreateImageFormat;
            int i4;
            float f;
            int i5;
            int i6;
            int i7;
            String str = this.codecId;
            str.hashCode();
            switch (str) {
                case "V_MPEG4/ISO/AP":
                    b = 0;
                    break;
                case "V_MPEG4/ISO/SP":
                    b = 1;
                    break;
                case "A_MS/ACM":
                    b = 2;
                    break;
                case "A_TRUEHD":
                    b = 3;
                    break;
                case "A_VORBIS":
                    b = 4;
                    break;
                case "A_MPEG/L3":
                    b = 5;
                    break;
                case "V_MS/VFW/FOURCC":
                    b = 6;
                    break;
                case "V_MPEG4/ISO/ASP":
                    b = 7;
                    break;
                case "V_MPEG4/ISO/AVC":
                    b = 8;
                    break;
                case "S_VOBSUB":
                    b = 9;
                    break;
                case "A_DTS/LOSSLESS":
                    b = 10;
                    break;
                case "A_AAC":
                    b = Ascii.VT;
                    break;
                case "A_AC3":
                    b = Ascii.FF;
                    break;
                case "A_DTS":
                    b = Ascii.CR;
                    break;
                case "V_VP8":
                    b = Ascii.SO;
                    break;
                case "V_VP9":
                    b = Ascii.SI;
                    break;
                case "S_HDMV/PGS":
                    b = Ascii.DLE;
                    break;
                case "A_DTS/EXPRESS":
                    b = 17;
                    break;
                case "A_PCM/INT/LIT":
                    b = Ascii.DC2;
                    break;
                case "V_MPEGH/ISO/HEVC":
                    b = 19;
                    break;
                case "S_TEXT/UTF8":
                    b = 20;
                    break;
                case "V_MPEG2":
                    b = Ascii.NAK;
                    break;
                case "A_EAC3":
                    b = Ascii.SYN;
                    break;
                case "A_FLAC":
                    b = 23;
                    break;
                case "A_OPUS":
                    b = Ascii.CAN;
                    break;
                default:
                    b = -1;
                    break;
            }
            String str2 = "audio/raw";
            List<byte[]> listSingletonList = null;
            switch (b) {
                case 0:
                case 1:
                case 7:
                    byte[] bArr = this.codecPrivate;
                    listSingletonList = bArr != null ? Collections.singletonList(bArr) : null;
                    str2 = "video/mp4v-es";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else if (MimeTypes.isVideo(str2)) {
                        if (this.displayUnit == 0) {
                            i6 = this.displayWidth;
                            if (i6 == -1) {
                                i6 = this.width;
                            }
                            this.displayWidth = i6;
                            i7 = this.displayHeight;
                            if (i7 == -1) {
                                i7 = this.height;
                            }
                            this.displayHeight = i7;
                        }
                        i4 = this.displayWidth;
                        if (i4 != -1 || (i5 = this.displayHeight) == -1) {
                            f = -1.0f;
                        } else {
                            f = (this.height * i4) / (this.width * i5);
                        }
                        mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                    } else if ("application/x-subrip".equals(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                    } else if (!"application/vobsub".equals(str2) || "application/pgs".equals(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                    } else {
                        throw new ParserException("Unexpected MIME type.");
                    }
                    TrackOutput trackOutputTrack = extractorOutput.track(this.number);
                    this.output = trackOutputTrack;
                    trackOutputTrack.format(mediaFormatCreateImageFormat);
                    return;
                case 2:
                    if (!parseMsAcmCodecPrivate(new ParsableByteArray(this.codecPrivate))) {
                        throw new ParserException("Non-PCM MS/ACM is unsupported");
                    }
                    pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                    if (pcmEncoding == 0) {
                        throw new ParserException("Unsupported PCM bit depth: " + this.audioBitDepth);
                    }
                    i3 = pcmEncoding;
                    vorbisCodecPrivate = null;
                    i2 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack2 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack2;
                    trackOutputTrack2.format(mediaFormatCreateImageFormat);
                    return;
                case 3:
                    str2 = "audio/true-hd";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack3 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack3;
                    trackOutputTrack3.format(mediaFormatCreateImageFormat);
                    return;
                case 4:
                    str2 = "audio/vorbis";
                    vorbisCodecPrivate = parseVorbisCodecPrivate(this.codecPrivate);
                    i2 = 8192;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack4 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack4;
                    trackOutputTrack4.format(mediaFormatCreateImageFormat);
                    return;
                case 5:
                    str2 = "audio/mpeg";
                    vorbisCodecPrivate = null;
                    i2 = 4096;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack5 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack5;
                    trackOutputTrack5.format(mediaFormatCreateImageFormat);
                    return;
                case 6:
                    listSingletonList = parseFourCcVc1Private(new ParsableByteArray(this.codecPrivate));
                    str2 = "video/wvc1";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack6 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack6;
                    trackOutputTrack6.format(mediaFormatCreateImageFormat);
                    return;
                case 8:
                    Pair<List<byte[]>, Integer> avcCodecPrivate = parseAvcCodecPrivate(new ParsableByteArray(this.codecPrivate));
                    listSingletonList = (List) avcCodecPrivate.first;
                    this.nalUnitLengthFieldLength = ((Integer) avcCodecPrivate.second).intValue();
                    str2 = "video/avc";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack7 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack7;
                    trackOutputTrack7.format(mediaFormatCreateImageFormat);
                    return;
                case 9:
                    listSingletonList = Collections.singletonList(this.codecPrivate);
                    str2 = "application/vobsub";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack8 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack8;
                    trackOutputTrack8.format(mediaFormatCreateImageFormat);
                    return;
                case 10:
                    str2 = "audio/vnd.dts.hd";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack9 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack9;
                    trackOutputTrack9.format(mediaFormatCreateImageFormat);
                    return;
                case 11:
                    listSingletonList = Collections.singletonList(this.codecPrivate);
                    str2 = "audio/mp4a-latm";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack10 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack10;
                    trackOutputTrack10.format(mediaFormatCreateImageFormat);
                    return;
                case 12:
                    str2 = "audio/ac3";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack11 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack11;
                    trackOutputTrack11.format(mediaFormatCreateImageFormat);
                    return;
                case 13:
                case 17:
                    str2 = "audio/vnd.dts";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack12 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack12;
                    trackOutputTrack12.format(mediaFormatCreateImageFormat);
                    return;
                case 14:
                    str2 = "video/x-vnd.on2.vp8";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack13 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack13;
                    trackOutputTrack13.format(mediaFormatCreateImageFormat);
                    return;
                case 15:
                    str2 = "video/x-vnd.on2.vp9";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack14 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack14;
                    trackOutputTrack14.format(mediaFormatCreateImageFormat);
                    return;
                case 16:
                    str2 = "application/pgs";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack15 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack15;
                    trackOutputTrack15.format(mediaFormatCreateImageFormat);
                    return;
                case 18:
                    pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                    if (pcmEncoding == 0) {
                        throw new ParserException("Unsupported PCM bit depth: " + this.audioBitDepth);
                    }
                    i3 = pcmEncoding;
                    vorbisCodecPrivate = null;
                    i2 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack16 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack16;
                    trackOutputTrack16.format(mediaFormatCreateImageFormat);
                    return;
                case 19:
                    Pair<List<byte[]>, Integer> hevcCodecPrivate = parseHevcCodecPrivate(new ParsableByteArray(this.codecPrivate));
                    listSingletonList = (List) hevcCodecPrivate.first;
                    this.nalUnitLengthFieldLength = ((Integer) hevcCodecPrivate.second).intValue();
                    str2 = "video/hevc";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack17 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack17;
                    trackOutputTrack17.format(mediaFormatCreateImageFormat);
                    return;
                case 20:
                    str2 = "application/x-subrip";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack18 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack18;
                    trackOutputTrack18.format(mediaFormatCreateImageFormat);
                    return;
                case 21:
                    str2 = "video/mpeg2";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack19 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack19;
                    trackOutputTrack19.format(mediaFormatCreateImageFormat);
                    return;
                case 22:
                    str2 = "audio/eac3";
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack110 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack110;
                    trackOutputTrack110.format(mediaFormatCreateImageFormat);
                    return;
                case 23:
                    listSingletonList = Collections.singletonList(this.codecPrivate);
                    str2 = MimeTypes.AUDIO_FLAC;
                    vorbisCodecPrivate = listSingletonList;
                    i2 = -1;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack111 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack111;
                    trackOutputTrack111.format(mediaFormatCreateImageFormat);
                    return;
                case 24:
                    ArrayList arrayList = new ArrayList(3);
                    arrayList.add(this.codecPrivate);
                    arrayList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.codecDelayNs).array());
                    arrayList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.seekPreRollNs).array());
                    str2 = "audio/opus";
                    vorbisCodecPrivate = arrayList;
                    i2 = WebmExtractor.OPUS_MAX_INPUT_SIZE;
                    i3 = -1;
                    if (MimeTypes.isAudio(str2)) {
                        mediaFormatCreateImageFormat = MediaFormat.createAudioFormat(Integer.toString(i), str2, -1, i2, j, this.channelCount, this.sampleRate, vorbisCodecPrivate, this.language, i3);
                    } else {
                        if (MimeTypes.isVideo(str2)) {
                            if (this.displayUnit == 0) {
                                i6 = this.displayWidth;
                                if (i6 == -1) {
                                    i6 = this.width;
                                }
                                this.displayWidth = i6;
                                i7 = this.displayHeight;
                                if (i7 == -1) {
                                    i7 = this.height;
                                }
                                this.displayHeight = i7;
                            }
                            i4 = this.displayWidth;
                            if (i4 != -1) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createVideoFormat(Integer.toString(i), str2, -1, i2, j, this.width, this.height, vorbisCodecPrivate, -1, f);
                        } else if ("application/x-subrip".equals(str2)) {
                            mediaFormatCreateImageFormat = MediaFormat.createTextFormat(Integer.toString(i), str2, -1, j, this.language);
                        } else {
                            if (!"application/vobsub".equals(str2)) {
                            }
                            mediaFormatCreateImageFormat = MediaFormat.createImageFormat(Integer.toString(i), str2, -1, j, vorbisCodecPrivate, this.language);
                        }
                        break;
                    }
                    TrackOutput trackOutputTrack112 = extractorOutput.track(this.number);
                    this.output = trackOutputTrack112;
                    trackOutputTrack112.format(mediaFormatCreateImageFormat);
                    return;
                default:
                    throw new ParserException("Unrecognized codec identifier.");
            }
        }

        private static List<byte[]> parseFourCcVc1Private(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long littleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (littleEndianUnsignedInt != 826496599) {
                    throw new ParserException("Unsupported FourCC compression type: " + littleEndianUnsignedInt);
                }
                byte[] bArr = parsableByteArray.data;
                for (int position = parsableByteArray.getPosition() + 20; position < bArr.length - 4; position++) {
                    if (bArr[position] == 0 && bArr[position + 1] == 0 && bArr[position + 2] == 1 && bArr[position + 3] == 15) {
                        return Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length));
                    }
                }
                throw new ParserException("Failed to find FourCC VC1 initialization data");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing FourCC VC1 codec private");
            }
        }

        private static Pair<List<byte[]>, Integer> parseAvcCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.setPosition(4);
                int unsignedByte = (parsableByteArray.readUnsignedByte() & 3) + 1;
                if (unsignedByte == 3) {
                    throw new ParserException();
                }
                ArrayList arrayList = new ArrayList();
                int unsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
                for (int i = 0; i < unsignedByte2; i++) {
                    arrayList.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
                }
                int unsignedByte3 = parsableByteArray.readUnsignedByte();
                for (int i2 = 0; i2 < unsignedByte3; i2++) {
                    arrayList.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
                }
                return Pair.create(arrayList, Integer.valueOf(unsignedByte));
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing AVC codec private");
            }
        }

        private static Pair<List<byte[]>, Integer> parseHevcCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.setPosition(21);
                int unsignedByte = parsableByteArray.readUnsignedByte() & 3;
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                int position = parsableByteArray.getPosition();
                int i = 0;
                for (int i2 = 0; i2 < unsignedByte2; i2++) {
                    parsableByteArray.skipBytes(1);
                    int unsignedShort = parsableByteArray.readUnsignedShort();
                    for (int i3 = 0; i3 < unsignedShort; i3++) {
                        int unsignedShort2 = parsableByteArray.readUnsignedShort();
                        i += unsignedShort2 + 4;
                        parsableByteArray.skipBytes(unsignedShort2);
                    }
                }
                parsableByteArray.setPosition(position);
                byte[] bArr = new byte[i];
                int i4 = 0;
                for (int i5 = 0; i5 < unsignedByte2; i5++) {
                    parsableByteArray.skipBytes(1);
                    int unsignedShort3 = parsableByteArray.readUnsignedShort();
                    for (int i6 = 0; i6 < unsignedShort3; i6++) {
                        int unsignedShort4 = parsableByteArray.readUnsignedShort();
                        System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, bArr, i4, NalUnitUtil.NAL_START_CODE.length);
                        int length = i4 + NalUnitUtil.NAL_START_CODE.length;
                        System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), bArr, length, unsignedShort4);
                        i4 = length + unsignedShort4;
                        parsableByteArray.skipBytes(unsignedShort4);
                    }
                }
                return Pair.create(i == 0 ? null : Collections.singletonList(bArr), Integer.valueOf(unsignedByte + 1));
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing HEVC codec private");
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            int i;
            int i2;
            try {
                if (bArr[0] != 2) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int i3 = 1;
                int i4 = 0;
                while (true) {
                    i = bArr[i3];
                    if (i != -1) {
                        break;
                    }
                    i4 += 255;
                    i3++;
                }
                int i5 = i3 + 1;
                int i6 = i4 + i;
                int i7 = 0;
                while (true) {
                    i2 = bArr[i5];
                    if (i2 != -1) {
                        break;
                    }
                    i7 += 255;
                    i5++;
                }
                int i8 = i5 + 1;
                int i9 = i7 + i2;
                if (bArr[i8] != 1) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] bArr2 = new byte[i6];
                System.arraycopy(bArr, i8, bArr2, 0, i6);
                int i10 = i8 + i6;
                if (bArr[i10] != 3) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int i11 = i10 + i9;
                if (bArr[i11] != 5) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] bArr3 = new byte[bArr.length - i11];
                System.arraycopy(bArr, i11, bArr3, 0, bArr.length - i11);
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(bArr2);
                arrayList.add(bArr3);
                return arrayList;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int littleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (littleEndianUnsignedShort == 1) {
                    return true;
                }
                if (littleEndianUnsignedShort != 65534) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                return parsableByteArray.readLong() == WebmExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == WebmExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits();
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }
    }
}

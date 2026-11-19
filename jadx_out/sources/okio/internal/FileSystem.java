package okio.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.ts.TsExtractor;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.service.FileDownloadService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import okio.BufferedSink;
import okio.FileMetadata;
import okio.Okio;
import okio.Path;
import okio.Source;

/* JADX INFO: renamed from: okio.internal.-FileSystem, reason: invalid class name */
/* JADX INFO: compiled from: FileSystem.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006H\u0000\u001a\u001c\u0010\u000b\u001a\u00020\b*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0000\u001a\u001c\u0010\u000e\u001a\u00020\b*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0006H\u0000\u001a\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0006H\u0000\u001aF\u0010\u0014\u001a\u00020\b*\b\u0012\u0004\u0012\u00020\u00040\u00152\u0006\u0010\u0016\u001a\u00020\u00022\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00182\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0080@¢\u0006\u0002\u0010\u001a\u001a\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u001c"}, d2 = {"commonMetadata", "Lokio/FileMetadata;", "Lokio/FileSystem;", FileDownloadService.PARAMS_KEY_PATH, "Lokio/Path;", "commonExists", "", "commonCreateDirectories", "", "dir", "mustCreate", "commonCopy", FirebaseAnalytics.Param.SOURCE, TypedValues.AttributesType.S_TARGET, "commonDeleteRecursively", "fileOrDirectory", "mustExist", "commonListRecursively", "Lkotlin/sequences/Sequence;", "followSymlinks", "collectRecursively", "Lkotlin/sequences/SequenceScope;", "fileSystem", "stack", "Lkotlin/collections/ArrayDeque;", "postorder", "(Lkotlin/sequences/SequenceScope;Lokio/FileSystem;Lkotlin/collections/ArrayDeque;Lokio/Path;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "symlinkTarget", "okio"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class FileSystem {

    /* JADX INFO: renamed from: okio.internal.-FileSystem$collectRecursively$1, reason: invalid class name */
    /* JADX INFO: compiled from: FileSystem.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "okio.internal.-FileSystem", f = "FileSystem.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2}, l = {AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, TsExtractor.TS_STREAM_TYPE_E_AC3, 145}, m = "collectRecursively", n = {"$this$collectRecursively", "fileSystem", "stack", FileDownloadService.PARAMS_KEY_PATH, "followSymlinks", "postorder", "$this$collectRecursively", "fileSystem", "stack", FileDownloadService.PARAMS_KEY_PATH, "children", "symlinkPath", "child", "followSymlinks", "postorder", "symlinkCount", "$this$collectRecursively", "fileSystem", "stack", FileDownloadService.PARAMS_KEY_PATH, "children", "followSymlinks", "postorder"}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "Z$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "Z$0", "Z$1", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "Z$1"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FileSystem.collectRecursively(null, null, null, null, false, false, this);
        }
    }

    public static final FileMetadata commonMetadata(okio.FileSystem fileSystem, Path path) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        FileMetadata fileMetadataMetadataOrNull = fileSystem.metadataOrNull(path);
        if (fileMetadataMetadataOrNull != null) {
            return fileMetadataMetadataOrNull;
        }
        throw new FileNotFoundException("no such file: " + path);
    }

    public static final boolean commonExists(okio.FileSystem fileSystem, Path path) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        return fileSystem.metadataOrNull(path) != null;
    }

    public static final void commonCreateDirectories(okio.FileSystem fileSystem, Path dir, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(dir, "dir");
        ArrayDeque arrayDeque = new ArrayDeque();
        for (Path pathParent = dir; pathParent != null && !fileSystem.exists(pathParent); pathParent = pathParent.parent()) {
            arrayDeque.addFirst(pathParent);
        }
        if (z && arrayDeque.isEmpty()) {
            throw new IOException(dir + " already exists.");
        }
        Iterator it = arrayDeque.iterator();
        while (it.hasNext()) {
            okio.FileSystem.createDirectory$default(fileSystem, (Path) it.next(), false, 2, null);
        }
    }

    public static final void commonDeleteRecursively(okio.FileSystem fileSystem, Path fileOrDirectory, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(fileOrDirectory, "fileOrDirectory");
        Iterator it = SequencesKt.sequence(new FileSystem$commonDeleteRecursively$sequence$1(fileSystem, fileOrDirectory, null)).iterator();
        while (it.hasNext()) {
            fileSystem.delete((Path) it.next(), z && !it.hasNext());
        }
    }

    /* JADX INFO: renamed from: okio.internal.-FileSystem$commonListRecursively$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FileSystem.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lokio/Path;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "okio.internal.-FileSystem$commonListRecursively$1", f = "FileSystem.kt", i = {0, 0, 0}, l = {Opcodes.IADD}, m = "invokeSuspend", n = {"$this$sequence", "stack", "child"}, s = {"L$0", "L$1", "L$3"}, v = 1)
    static final class C02961 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Path $dir;
        final /* synthetic */ boolean $followSymlinks;
        final /* synthetic */ okio.FileSystem $this_commonListRecursively;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02961(Path path, okio.FileSystem fileSystem, boolean z, Continuation<? super C02961> continuation) {
            super(2, continuation);
            this.$dir = path;
            this.$this_commonListRecursively = fileSystem;
            this.$followSymlinks = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02961 c02961 = new C02961(this.$dir, this.$this_commonListRecursively, this.$followSymlinks, continuation);
            c02961.L$0 = obj;
            return c02961;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
            return ((C02961) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            ArrayDeque arrayDeque;
            Iterator<Path> it;
            SequenceScope sequenceScope = (SequenceScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ArrayDeque arrayDeque2 = new ArrayDeque();
                arrayDeque2.addLast(this.$dir);
                arrayDeque = arrayDeque2;
                it = this.$this_commonListRecursively.list(this.$dir).iterator();
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Iterator<Path> it2 = (Iterator) this.L$2;
                ArrayDeque arrayDeque3 = (ArrayDeque) this.L$1;
                ResultKt.throwOnFailure(obj);
                it = it2;
                arrayDeque = arrayDeque3;
            }
            while (it.hasNext()) {
                Path next = it.next();
                this.L$0 = sequenceScope;
                this.L$1 = arrayDeque;
                this.L$2 = it;
                this.L$3 = SpillingKt.nullOutSpilledVariable(next);
                this.label = 1;
                if (FileSystem.collectRecursively(sequenceScope, this.$this_commonListRecursively, arrayDeque, next, this.$followSymlinks, false, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final Sequence<Path> commonListRecursively(okio.FileSystem fileSystem, Path dir, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(dir, "dir");
        return SequencesKt.sequence(new C02961(dir, fileSystem, z, null));
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0124 A[Catch: all -> 0x0085, TRY_LEAVE, TryCatch #0 {all -> 0x0085, blocks: (B:17:0x0080, B:48:0x011e, B:50:0x0124), top: B:67:0x0080 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x017b  */
    /* JADX WARN: Code duplicated, block: B:62:0x01ac A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:65:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:72:0x0162 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:? A[LOOP:0: B:48:0x011e->B:74:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.lang.Object, kotlin.sequences.SequenceScope] */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r17v0, types: [java.lang.Object, kotlin.sequences.SequenceScope, kotlin.sequences.SequenceScope<? super okio.Path>] */
    /* JADX WARN: Type inference failed for: r17v1, types: [kotlin.sequences.SequenceScope] */
    public static final Object collectRecursively(SequenceScope<? super Path> sequenceScope, okio.FileSystem fileSystem, ArrayDeque<Path> arrayDeque, Path path, boolean z, boolean z2, Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        okio.FileSystem fileSystem2;
        ArrayDeque<Path> arrayDeque2;
        boolean z3;
        okio.FileSystem fileSystem3;
        ?? r11;
        boolean z4;
        List<Path> listListOrNull;
        ArrayDeque<Path> arrayDeque3;
        ?? r14;
        Path path2;
        boolean z5;
        boolean z6;
        int i;
        Path path3;
        Iterator<Path> it;
        okio.FileSystem fileSystem4;
        List<Path> list;
        Path next;
        Path path4 = path;
        boolean z7 = z2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        int i3 = 0;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            if (z7) {
                fileSystem2 = fileSystem;
                arrayDeque2 = arrayDeque;
                z3 = z;
            } else {
                anonymousClass1.L$0 = sequenceScope;
                fileSystem2 = fileSystem;
                anonymousClass1.L$1 = fileSystem2;
                arrayDeque2 = arrayDeque;
                anonymousClass1.L$2 = arrayDeque2;
                anonymousClass1.L$3 = path4;
                z3 = z;
                anonymousClass1.Z$0 = z3;
                anonymousClass1.Z$1 = z7;
                anonymousClass1.label = 1;
                if (sequenceScope.yield(path4, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            fileSystem3 = fileSystem2;
            boolean z8 = z3;
            r11 = sequenceScope;
            z4 = z8;
        } else {
            if (i2 != 1) {
                if (i2 == 2) {
                    i = anonymousClass1.I$0;
                    z5 = anonymousClass1.Z$1;
                    z6 = anonymousClass1.Z$0;
                    it = (Iterator) anonymousClass1.L$6;
                    path3 = (Path) anonymousClass1.L$5;
                    list = (List) anonymousClass1.L$4;
                    path2 = (Path) anonymousClass1.L$3;
                    arrayDeque3 = (ArrayDeque) anonymousClass1.L$2;
                    fileSystem4 = (okio.FileSystem) anonymousClass1.L$1;
                    SequenceScope sequenceScope2 = (SequenceScope) anonymousClass1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        r14 = sequenceScope2;
                        while (it.hasNext()) {
                            next = it.next();
                            anonymousClass1.L$0 = r14;
                            anonymousClass1.L$1 = fileSystem4;
                            anonymousClass1.L$2 = arrayDeque3;
                            anonymousClass1.L$3 = path2;
                            anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(list);
                            anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(path3);
                            anonymousClass1.L$6 = it;
                            anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(next);
                            anonymousClass1.Z$0 = z6;
                            anonymousClass1.Z$1 = z5;
                            anonymousClass1.I$0 = i;
                            anonymousClass1.label = 2;
                            if (collectRecursively(r14, fileSystem4, arrayDeque3, next, z6, z5, anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        arrayDeque3.removeLast();
                        z4 = z6;
                        listListOrNull = list;
                        arrayDeque2 = arrayDeque3;
                        fileSystem3 = fileSystem4;
                        z7 = z5;
                        path4 = path2;
                        r11 = r14;
                        if (z7) {
                            return Unit.INSTANCE;
                        }
                        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(r11);
                        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(fileSystem3);
                        anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(arrayDeque2);
                        anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(path4);
                        anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(listListOrNull);
                        anonymousClass1.L$5 = null;
                        anonymousClass1.L$6 = null;
                        anonymousClass1.L$7 = null;
                        anonymousClass1.Z$0 = z4;
                        anonymousClass1.Z$1 = z7;
                        anonymousClass1.label = 3;
                        if (r11.yield(path4, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } catch (Throwable th) {
                        th = th;
                        arrayDeque3.removeLast();
                        throw th;
                    }
                } else {
                    if (i2 != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    boolean z9 = anonymousClass1.Z$1;
                    boolean z10 = anonymousClass1.Z$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
            boolean z11 = anonymousClass1.Z$1;
            boolean z12 = anonymousClass1.Z$0;
            Path path5 = (Path) anonymousClass1.L$3;
            arrayDeque2 = (ArrayDeque) anonymousClass1.L$2;
            fileSystem3 = (okio.FileSystem) anonymousClass1.L$1;
            SequenceScope sequenceScope3 = (SequenceScope) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            z7 = z11;
            z4 = z12;
            path4 = path5;
            r11 = sequenceScope3;
        }
        listListOrNull = fileSystem3.listOrNull(path4);
        if (listListOrNull == null) {
            listListOrNull = CollectionsKt.emptyList();
        }
        if (!listListOrNull.isEmpty()) {
            Path path6 = path4;
            while (true) {
                if (z4 && arrayDeque2.contains(path6)) {
                    throw new IOException("symlink cycle at " + path4);
                }
                Path pathSymlinkTarget = symlinkTarget(fileSystem3, path6);
                if (pathSymlinkTarget != null) {
                    i3++;
                    path6 = pathSymlinkTarget;
                } else if (z4 || i3 == 0) {
                    arrayDeque2.addLast(path6);
                    try {
                        r14 = r11;
                        path2 = path4;
                        z5 = z7;
                        z6 = z4;
                        i = i3;
                        path3 = path6;
                        arrayDeque3 = arrayDeque2;
                        it = listListOrNull.iterator();
                        fileSystem4 = fileSystem3;
                        list = listListOrNull;
                        while (it.hasNext()) {
                            next = it.next();
                            anonymousClass1.L$0 = r14;
                            anonymousClass1.L$1 = fileSystem4;
                            anonymousClass1.L$2 = arrayDeque3;
                            anonymousClass1.L$3 = path2;
                            anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(list);
                            anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(path3);
                            anonymousClass1.L$6 = it;
                            anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(next);
                            anonymousClass1.Z$0 = z6;
                            anonymousClass1.Z$1 = z5;
                            anonymousClass1.I$0 = i;
                            anonymousClass1.label = 2;
                            if (collectRecursively(r14, fileSystem4, arrayDeque3, next, z6, z5, anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        arrayDeque3.removeLast();
                        z4 = z6;
                        listListOrNull = list;
                        arrayDeque2 = arrayDeque3;
                        fileSystem3 = fileSystem4;
                        z7 = z5;
                        path4 = path2;
                        r11 = r14;
                    } catch (Throwable th2) {
                        th = th2;
                        arrayDeque3 = arrayDeque2;
                        arrayDeque3.removeLast();
                        throw th;
                    }
                }
            }
        }
        if (z7) {
            return Unit.INSTANCE;
        }
        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(r11);
        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(fileSystem3);
        anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(arrayDeque2);
        anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(path4);
        anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(listListOrNull);
        anonymousClass1.L$5 = null;
        anonymousClass1.L$6 = null;
        anonymousClass1.L$7 = null;
        anonymousClass1.Z$0 = z4;
        anonymousClass1.Z$1 = z7;
        anonymousClass1.label = 3;
        if (r11.yield(path4, anonymousClass1) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    public static final Path symlinkTarget(okio.FileSystem fileSystem, Path path) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        Path symlinkTarget = fileSystem.metadata(path).getSymlinkTarget();
        if (symlinkTarget == null) {
            return null;
        }
        Path pathParent = path.parent();
        Intrinsics.checkNotNull(pathParent);
        return pathParent.resolve(symlinkTarget);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Throwable] */
    public static final void commonCopy(okio.FileSystem fileSystem, Path source, Path target) throws IOException {
        Throwable th;
        Long lValueOf;
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        Source source2 = fileSystem.source(source);
        Long th2 = null;
        try {
            Source source3 = source2;
            BufferedSink bufferedSinkBuffer = Okio.buffer(okio.FileSystem.sink$default(fileSystem, target, false, 2, null));
            try {
                lValueOf = Long.valueOf(bufferedSinkBuffer.writeAll(source3));
                if (bufferedSinkBuffer != null) {
                    try {
                        bufferedSinkBuffer.close();
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                th = null;
            } catch (Throwable th4) {
                if (bufferedSinkBuffer != null) {
                    try {
                        bufferedSinkBuffer.close();
                    } catch (Throwable th5) {
                        ExceptionsKt.addSuppressed(th4, th5);
                    }
                }
                th = th4;
                lValueOf = null;
            }
            if (th == null) {
                Long lValueOf2 = Long.valueOf(lValueOf.longValue());
                if (source2 != null) {
                    try {
                        source2.close();
                    } catch (Throwable th6) {
                        th2 = th6;
                    }
                }
                Long l = th2;
                th2 = lValueOf2;
                th = l;
                if (th != 0) {
                    throw th;
                }
                return;
            }
            throw th;
        } catch (Throwable th7) {
            th = th7;
            if (source2 != null) {
                try {
                    source2.close();
                } catch (Throwable th8) {
                    ExceptionsKt.addSuppressed(th, th8);
                }
            }
        }
    }
}

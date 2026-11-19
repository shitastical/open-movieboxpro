package kotlin.jvm.internal;

import com.jeffmony.downloader.model.Video;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: ClassReference.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 P2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001PB\u0013\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0017J\b\u0010I\u001a\u00020JH\u0002J\u0013\u0010K\u001a\u00020#2\b\u0010L\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010M\u001a\u00020NH\u0016J\b\u0010O\u001a\u00020\u000bH\u0016R\u0018\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u001e\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R \u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00160\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0014R\u001e\u0010\u0018\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0014R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R \u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u001b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b'\u0010(\u001a\u0004\b)\u0010\u001eR \u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u001b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b,\u0010(\u001a\u0004\b-\u0010\u001eR(\u0010.\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00010\u001b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b/\u0010(\u001a\u0004\b0\u0010\u001eR\u001c\u00101\u001a\u0004\u0018\u0001028VX\u0097\u0004¢\u0006\f\u0012\u0004\b3\u0010(\u001a\u0004\b4\u00105R\u001a\u00106\u001a\u00020#8VX\u0097\u0004¢\u0006\f\u0012\u0004\b7\u0010(\u001a\u0004\b6\u00108R\u001a\u00109\u001a\u00020#8VX\u0097\u0004¢\u0006\f\u0012\u0004\b:\u0010(\u001a\u0004\b9\u00108R\u001a\u0010;\u001a\u00020#8VX\u0097\u0004¢\u0006\f\u0012\u0004\b<\u0010(\u001a\u0004\b;\u00108R\u001a\u0010=\u001a\u00020#8VX\u0097\u0004¢\u0006\f\u0012\u0004\b>\u0010(\u001a\u0004\b=\u00108R\u001a\u0010?\u001a\u00020#8VX\u0097\u0004¢\u0006\f\u0012\u0004\b@\u0010(\u001a\u0004\b?\u00108R\u001a\u0010A\u001a\u00020#8VX\u0097\u0004¢\u0006\f\u0012\u0004\bB\u0010(\u001a\u0004\bA\u00108R\u001a\u0010C\u001a\u00020#8VX\u0097\u0004¢\u0006\f\u0012\u0004\bD\u0010(\u001a\u0004\bC\u00108R\u001a\u0010E\u001a\u00020#8VX\u0097\u0004¢\u0006\f\u0012\u0004\bF\u0010(\u001a\u0004\bE\u00108R\u001a\u0010G\u001a\u00020#8VX\u0097\u0004¢\u0006\f\u0012\u0004\bH\u0010(\u001a\u0004\bG\u00108¨\u0006Q"}, d2 = {"Lkotlin/jvm/internal/ClassReference;", "Lkotlin/reflect/KClass;", "", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "jClass", "Ljava/lang/Class;", "<init>", "(Ljava/lang/Class;)V", "getJClass", "()Ljava/lang/Class;", "simpleName", "", "getSimpleName", "()Ljava/lang/String;", "qualifiedName", "getQualifiedName", "members", "", "Lkotlin/reflect/KCallable;", "getMembers", "()Ljava/util/Collection;", "constructors", "Lkotlin/reflect/KFunction;", "getConstructors", "nestedClasses", "getNestedClasses", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "isInstance", "", "value", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters$annotations", "()V", "getTypeParameters", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes$annotations", "getSupertypes", "sealedSubclasses", "getSealedSubclasses$annotations", "getSealedSubclasses", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "isFinal", "isFinal$annotations", "()Z", "isOpen", "isOpen$annotations", "isAbstract", "isAbstract$annotations", "isSealed", "isSealed$annotations", "isData", "isData$annotations", "isInner", "isInner$annotations", "isCompanion", "isCompanion$annotations", "isFun", "isFun$annotations", "isValue", "isValue$annotations", "error", "", "equals", Video.TypeInfo.OTHER, "hashCode", "", "toString", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ClassReference implements KClass<Object>, ClassBasedDeclarationContainer {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Map<Class<? extends Function<?>>, Integer> FUNCTION_CLASSES;
    private final Class<?> jClass;

    public static /* synthetic */ void getSealedSubclasses$annotations() {
    }

    public static /* synthetic */ void getSupertypes$annotations() {
    }

    public static /* synthetic */ void getTypeParameters$annotations() {
    }

    public static /* synthetic */ void getVisibility$annotations() {
    }

    public static /* synthetic */ void isAbstract$annotations() {
    }

    public static /* synthetic */ void isCompanion$annotations() {
    }

    public static /* synthetic */ void isData$annotations() {
    }

    public static /* synthetic */ void isFinal$annotations() {
    }

    public static /* synthetic */ void isFun$annotations() {
    }

    public static /* synthetic */ void isInner$annotations() {
    }

    public static /* synthetic */ void isOpen$annotations() {
    }

    public static /* synthetic */ void isSealed$annotations() {
    }

    public static /* synthetic */ void isValue$annotations() {
    }

    public ClassReference(Class<?> jClass) {
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        this.jClass = jClass;
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class<?> getJClass() {
        return this.jClass;
    }

    @Override // kotlin.reflect.KClass
    public String getSimpleName() {
        return INSTANCE.getClassSimpleName(getJClass());
    }

    @Override // kotlin.reflect.KClass
    public String getQualifiedName() {
        return INSTANCE.getClassQualifiedName(getJClass());
    }

    @Override // kotlin.reflect.KClass, kotlin.reflect.KDeclarationContainer
    public Collection<KCallable<?>> getMembers() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public Collection<KFunction<Object>> getConstructors() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public Collection<KClass<?>> getNestedClasses() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public Object getObjectInstance() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isInstance(Object value) {
        return INSTANCE.isInstance(value, getJClass());
    }

    @Override // kotlin.reflect.KClass
    public List<KTypeParameter> getTypeParameters() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public List<KType> getSupertypes() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public List<KClass<? extends Object>> getSealedSubclasses() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public KVisibility getVisibility() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isFinal() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isOpen() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isAbstract() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isSealed() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isData() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isInner() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isCompanion() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isFun() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isValue() {
        error();
        throw new KotlinNothingValueException();
    }

    private final Void error() {
        throw new KotlinReflectionNotSupportedError();
    }

    @Override // kotlin.reflect.KClass
    public boolean equals(Object other) {
        return (other instanceof ClassReference) && Intrinsics.areEqual(JvmClassMappingKt.getJavaObjectType(this), JvmClassMappingKt.getJavaObjectType((KClass) other));
    }

    @Override // kotlin.reflect.KClass
    public int hashCode() {
        return JvmClassMappingKt.getJavaObjectType(this).hashCode();
    }

    public String toString() {
        return getJClass().toString() + " (Kotlin reflection is not available)";
    }

    /* JADX INFO: compiled from: ClassReference.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0014\u0010\r\u001a\u0004\u0018\u00010\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0006J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0006J\u001c\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00012\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0006R&\u0010\u0004\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlin/jvm/internal/ClassReference$Companion;", "", "<init>", "()V", "FUNCTION_CLASSES", "", "Ljava/lang/Class;", "Lkotlin/Function;", "", "classFqNameOf", "", "type", "simpleNameOf", "getClassSimpleName", "jClass", "getClassQualifiedName", "isInstance", "", "value", "kotlin-stdlib"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:261:0x037f A[RETURN, SYNTHETIC] */
        /* JADX WARN: Code restructure failed: missing block: B:129:0x01cc, code lost:
        
            if (r10.equals("java.lang.Double") == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:148:0x020e, code lost:
        
            if (r10.equals("java.lang.Long") == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:155:0x0226, code lost:
        
            if (r10.equals("java.lang.Byte") == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:165:0x0248, code lost:
        
            if (r10.equals("java.lang.Character") == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:168:0x0252, code lost:
        
            if (r10.equals("short") == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:171:0x025c, code lost:
        
            if (r10.equals(androidx.constraintlayout.core.motion.utils.TypedValues.Custom.S_FLOAT) == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:185:0x028c, code lost:
        
            if (r10.equals("long") == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:189:0x0299, code lost:
        
            if (r10.equals("char") == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:193:0x02a6, code lost:
        
            if (r10.equals("byte") == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:197:0x02b3, code lost:
        
            if (r10.equals(com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler.TYPE_INT) == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:212:0x02e7, code lost:
        
            if (r10.equals("java.lang.Short") == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:216:0x02f4, code lost:
        
            if (r10.equals("java.lang.Float") == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:232:0x032b, code lost:
        
            if (r10.equals(com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler.TYPE_DOUBLE) == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:256:0x0372, code lost:
        
            if (r10.equals("java.lang.Integer") == false) goto L261;
         */
        /* JADX WARN: Code restructure failed: missing block: B:304:?, code lost:
        
            return "kotlin.Long";
         */
        /* JADX WARN: Code restructure failed: missing block: B:305:?, code lost:
        
            return "kotlin.Char";
         */
        /* JADX WARN: Code restructure failed: missing block: B:306:?, code lost:
        
            return "kotlin.Byte";
         */
        /* JADX WARN: Code restructure failed: missing block: B:310:?, code lost:
        
            return "kotlin.Short";
         */
        /* JADX WARN: Code restructure failed: missing block: B:311:?, code lost:
        
            return "kotlin.Float";
         */
        /* JADX WARN: Code restructure failed: missing block: B:315:?, code lost:
        
            return "kotlin.Double";
         */
        /* JADX WARN: Code restructure failed: missing block: B:321:?, code lost:
        
            return "kotlin.Int";
         */
        /* JADX WARN: Failed to clean up code after switch over string restore
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 int, still in use, count: 3, list:
          (r0v0 int) from 0x0014: SWITCH (r0v0 int)
         case -2061550653: goto B:259:0x0377
         case -2056817302: goto B:255:0x036c
         case -2034166429: goto B:251:0x0360
         case -1979556166: goto B:247:0x0354
         case -1571515090: goto B:243:0x0348
         case -1383349348: goto B:239:0x033c
         case -1383343454: goto B:235:0x0330
         case -1325958191: goto B:231:0x0325
         case -1182275604: goto B:227:0x0317
         case -1062240117: goto B:223:0x0309
         case -688322466: goto B:219:0x02fb
         case -527879800: goto B:215:0x02ee
         case -515992664: goto B:211:0x02e1
         case -246476834: goto B:207:0x02d3
         case -207262728: goto B:203:0x02c5
         case -165139126: goto B:199:0x02b7
         case 104431: goto B:196:0x02ad
         case 3039496: goto B:192:0x02a0
         case 3052374: goto B:188:0x0293
         case 3327612: goto B:184:0x0286
         case 64711720: goto B:181:0x027c
         case 65821278: goto B:177:0x026e
         case 77230534: goto B:173:0x0260
         case 97526364: goto B:170:0x0256
         case 109413500: goto B:167:0x024c
         case 155276373: goto B:164:0x0242
         case 226173651: goto B:160:0x0234
         case 344809556: goto B:157:0x022a
         case 398507100: goto B:154:0x0220
         case 398585941: goto B:150:0x0212
         case 398795216: goto B:147:0x0208
         case 482629606: goto B:143:0x01fa
         case 499831342: goto B:139:0x01ec
         case 577341676: goto B:135:0x01de
         case 599019395: goto B:131:0x01d0
         case 761287205: goto B:128:0x01c6
         case 1052881309: goto B:124:0x01b8
         case 1063877011: goto B:120:0x01aa
         case 1195259493: goto B:116:0x019c
         case 1275614662: goto B:112:0x018e
         case 1383693018: goto B:108:0x0180
         case 1630335596: goto B:104:0x0172
         case 1877171123: goto B:100:0x0164
         default: goto B:4:0x0017 A[RegionRef:SW:3]
          (r0v0 int) from 0x001a: SWITCH (r0v0 int)
         case -1811142685: goto B:56:0x00ca
         case -1811142684: goto B:52:0x00bc
         case -1811142683: goto B:48:0x00ae
         default: goto B:6:0x001d A[RegionRef:SW:5]
          (r0v0 int) from 0x001d: SWITCH (r0v0 int)
         case 80123371: goto B:44:0x00a0
         case 80123372: goto B:40:0x0092
         case 80123373: goto B:36:0x0084
         case 80123374: goto B:32:0x0076
         case 80123375: goto B:28:0x0068
         case 80123376: goto B:24:0x005a
         case 80123377: goto B:20:0x004c
         case 80123378: goto B:16:0x003e
         case 80123379: goto B:12:0x0030
         case 80123380: goto B:8:0x0022
         default: goto B:261:0x037f A[RegionRef:SW:6]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
        	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:226)
        	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:215)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.replaceWithMergedSwitch(SwitchOverStringVisitor.java:355)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:111)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:72)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:140)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:47)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:66)
         */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final java.lang.String classFqNameOf(java.lang.String r10) {
            /*
                Method dump skipped, instruction units count: 1132
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.internal.ClassReference.Companion.classFqNameOf(java.lang.String):java.lang.String");
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:252:0x035d A[RETURN, SYNTHETIC] */
        /* JADX WARN: Code restructure failed: missing block: B:101:0x016c, code lost:
        
            if (r11.equals("kotlin.jvm.internal.IntCompanionObject") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:108:0x0184, code lost:
        
            if (r11.equals("kotlin.jvm.internal.BooleanCompanionObject") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:127:0x01c6, code lost:
        
            if (r11.equals("java.lang.Double") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:130:0x01d0, code lost:
        
            if (r11.equals("kotlin.jvm.internal.StringCompanionObject") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:141:0x01f6, code lost:
        
            if (r11.equals("kotlin.jvm.internal.FloatCompanionObject") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:144:0x0200, code lost:
        
            if (r11.equals("java.lang.Long") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:151:0x0218, code lost:
        
            if (r11.equals("java.lang.Byte") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:157:0x022c, code lost:
        
            if (r11.equals("kotlin.jvm.internal.EnumCompanionObject") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:160:0x0236, code lost:
        
            if (r11.equals("java.lang.Character") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:163:0x0240, code lost:
        
            if (r11.equals("short") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:166:0x024a, code lost:
        
            if (r11.equals(androidx.constraintlayout.core.motion.utils.TypedValues.Custom.S_FLOAT) == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:169:0x0254, code lost:
        
            if (r11.equals("kotlin.jvm.internal.ShortCompanionObject") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:179:0x0276, code lost:
        
            if (r11.equals("long") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:183:0x0283, code lost:
        
            if (r11.equals("char") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:187:0x0290, code lost:
        
            if (r11.equals("byte") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:191:0x029d, code lost:
        
            if (r11.equals(com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler.TYPE_INT) == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:198:0x02b5, code lost:
        
            if (r11.equals("kotlin.jvm.internal.LongCompanionObject") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:201:0x02bf, code lost:
        
            if (r11.equals("kotlin.jvm.internal.CharCompanionObject") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:204:0x02c9, code lost:
        
            if (r11.equals("java.lang.Short") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:208:0x02d6, code lost:
        
            if (r11.equals("java.lang.Float") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:220:0x02ff, code lost:
        
            if (r11.equals("kotlin.jvm.internal.ByteCompanionObject") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:223:0x0309, code lost:
        
            if (r11.equals(com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler.TYPE_DOUBLE) == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:247:0x0350, code lost:
        
            if (r11.equals("java.lang.Integer") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:251:0x035b, code lost:
        
            if (r11.equals("kotlin.jvm.internal.DoubleCompanionObject") == false) goto L252;
         */
        /* JADX WARN: Code restructure failed: missing block: B:254:0x0360, code lost:
        
            return "Companion";
         */
        /* JADX WARN: Code restructure failed: missing block: B:289:?, code lost:
        
            return "Long";
         */
        /* JADX WARN: Code restructure failed: missing block: B:290:?, code lost:
        
            return "Char";
         */
        /* JADX WARN: Code restructure failed: missing block: B:291:?, code lost:
        
            return "Byte";
         */
        /* JADX WARN: Code restructure failed: missing block: B:293:?, code lost:
        
            return "Short";
         */
        /* JADX WARN: Code restructure failed: missing block: B:294:?, code lost:
        
            return "Float";
         */
        /* JADX WARN: Code restructure failed: missing block: B:297:?, code lost:
        
            return "Double";
         */
        /* JADX WARN: Code restructure failed: missing block: B:303:?, code lost:
        
            return "Int";
         */
        /* JADX WARN: Failed to clean up code after switch over string restore
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 int, still in use, count: 3, list:
          (r0v0 int) from 0x0016: SWITCH (r0v0 int)
         case -2061550653: goto B:250:0x0355
         case -2056817302: goto B:246:0x034a
         case -2034166429: goto B:242:0x033e
         case -1979556166: goto B:238:0x0332
         case -1571515090: goto B:234:0x0326
         case -1383349348: goto B:230:0x031a
         case -1383343454: goto B:226:0x030e
         case -1325958191: goto B:222:0x0303
         case -1182275604: goto B:219:0x02f9
         case -1062240117: goto B:215:0x02eb
         case -688322466: goto B:211:0x02dd
         case -527879800: goto B:207:0x02d0
         case -515992664: goto B:203:0x02c3
         case -246476834: goto B:200:0x02b9
         case -207262728: goto B:197:0x02af
         case -165139126: goto B:193:0x02a1
         case 104431: goto B:190:0x0297
         case 3039496: goto B:186:0x028a
         case 3052374: goto B:182:0x027d
         case 3327612: goto B:178:0x0270
         case 64711720: goto B:175:0x0266
         case 65821278: goto B:171:0x0258
         case 77230534: goto B:168:0x024e
         case 97526364: goto B:165:0x0244
         case 109413500: goto B:162:0x023a
         case 155276373: goto B:159:0x0230
         case 226173651: goto B:156:0x0226
         case 344809556: goto B:153:0x021c
         case 398507100: goto B:150:0x0212
         case 398585941: goto B:146:0x0204
         case 398795216: goto B:143:0x01fa
         case 482629606: goto B:140:0x01f0
         case 499831342: goto B:136:0x01e2
         case 577341676: goto B:132:0x01d4
         case 599019395: goto B:129:0x01ca
         case 761287205: goto B:126:0x01c0
         case 1052881309: goto B:122:0x01b2
         case 1063877011: goto B:118:0x01a4
         case 1195259493: goto B:114:0x0196
         case 1275614662: goto B:110:0x0188
         case 1383693018: goto B:107:0x017e
         case 1630335596: goto B:103:0x0170
         case 1877171123: goto B:100:0x0166
         default: goto B:4:0x0019 A[RegionRef:SW:3]
          (r0v0 int) from 0x001c: SWITCH (r0v0 int)
         case -1811142685: goto B:56:0x00cc
         case -1811142684: goto B:52:0x00be
         case -1811142683: goto B:48:0x00b0
         default: goto B:6:0x001f A[RegionRef:SW:5]
          (r0v0 int) from 0x001f: SWITCH (r0v0 int)
         case 80123371: goto B:44:0x00a2
         case 80123372: goto B:40:0x0094
         case 80123373: goto B:36:0x0086
         case 80123374: goto B:32:0x0078
         case 80123375: goto B:28:0x006a
         case 80123376: goto B:24:0x005c
         case 80123377: goto B:20:0x004e
         case 80123378: goto B:16:0x0040
         case 80123379: goto B:12:0x0032
         case 80123380: goto B:8:0x0024
         default: goto B:252:0x035d A[RegionRef:SW:6]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
        	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:226)
        	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:215)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.replaceWithMergedSwitch(SwitchOverStringVisitor.java:355)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:111)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:72)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:140)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:47)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:66)
         */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final java.lang.String simpleNameOf(java.lang.String r11) {
            /*
                Method dump skipped, instruction units count: 1098
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.internal.ClassReference.Companion.simpleNameOf(java.lang.String):java.lang.String");
        }

        public final String getClassSimpleName(Class<?> jClass) {
            Intrinsics.checkNotNullParameter(jClass, "jClass");
            String str = null;
            if (jClass.isAnonymousClass()) {
                return null;
            }
            if (jClass.isLocalClass()) {
                String simpleName = jClass.getSimpleName();
                Method enclosingMethod = jClass.getEnclosingMethod();
                if (enclosingMethod != null) {
                    Intrinsics.checkNotNull(simpleName);
                    String strSubstringAfter$default = StringsKt.substringAfter$default(simpleName, enclosingMethod.getName() + Typography.dollar, (String) null, 2, (Object) null);
                    if (strSubstringAfter$default != null) {
                        return strSubstringAfter$default;
                    }
                }
                Constructor<?> enclosingConstructor = jClass.getEnclosingConstructor();
                if (enclosingConstructor == null) {
                    Intrinsics.checkNotNull(simpleName);
                    return StringsKt.substringAfter$default(simpleName, Typography.dollar, (String) null, 2, (Object) null);
                }
                Intrinsics.checkNotNull(simpleName);
                return StringsKt.substringAfter$default(simpleName, enclosingConstructor.getName() + Typography.dollar, (String) null, 2, (Object) null);
            }
            if (jClass.isArray()) {
                Class<?> componentType = jClass.getComponentType();
                if (componentType.isPrimitive()) {
                    String name = componentType.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    String strSimpleNameOf = simpleNameOf(name);
                    if (strSimpleNameOf != null) {
                        str = strSimpleNameOf + "Array";
                    }
                }
                return str == null ? "Array" : str;
            }
            String name2 = jClass.getName();
            Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
            String strSimpleNameOf2 = simpleNameOf(name2);
            return strSimpleNameOf2 == null ? jClass.getSimpleName() : strSimpleNameOf2;
        }

        public final String getClassQualifiedName(Class<?> jClass) {
            Intrinsics.checkNotNullParameter(jClass, "jClass");
            String str = null;
            if (jClass.isAnonymousClass() || jClass.isLocalClass()) {
                return null;
            }
            if (jClass.isArray()) {
                Class<?> componentType = jClass.getComponentType();
                if (componentType.isPrimitive()) {
                    String name = componentType.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    String strClassFqNameOf = classFqNameOf(name);
                    if (strClassFqNameOf != null) {
                        str = strClassFqNameOf + "Array";
                    }
                }
                return str == null ? "kotlin.Array" : str;
            }
            String name2 = jClass.getName();
            Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
            String strClassFqNameOf2 = classFqNameOf(name2);
            return strClassFqNameOf2 == null ? jClass.getCanonicalName() : strClassFqNameOf2;
        }

        public final boolean isInstance(Object value, Class<?> jClass) {
            Intrinsics.checkNotNullParameter(jClass, "jClass");
            Map map = ClassReference.FUNCTION_CLASSES;
            Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
            Integer num = (Integer) map.get(jClass);
            if (num != null) {
                return TypeIntrinsics.isFunctionOfArity(value, num.intValue());
            }
            if (jClass.isPrimitive()) {
                jClass = JvmClassMappingKt.getJavaObjectType(JvmClassMappingKt.getKotlinClass(jClass));
            }
            return jClass.isInstance(value);
        }
    }

    static {
        int i = 0;
        List listListOf = CollectionsKt.listOf((Object[]) new Class[]{Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        for (Object obj : listListOf) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(TuplesKt.to((Class) obj, Integer.valueOf(i)));
            i = i2;
        }
        FUNCTION_CLASSES = MapsKt.toMap(arrayList);
    }
}

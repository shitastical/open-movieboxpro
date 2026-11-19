package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes4.dex */
public class JavaBeanInfo {
    public final Method buildMethod;
    public final Class<?> builderClass;
    public final Class<?> clazz;
    public final Constructor<?> creatorConstructor;
    public Type[] creatorConstructorParameterTypes;
    public String[] creatorConstructorParameters;
    public final Constructor<?> defaultConstructor;
    public final int defaultConstructorParameterSize;
    public final Method factoryMethod;
    public final FieldInfo[] fields;
    public final JSONType jsonType;
    public String[] orders;
    public final int parserFeatures;
    public final FieldInfo[] sortedFields;
    public final String typeKey;
    public final String typeName;

    public JavaBeanInfo(Class<?> cls, Class<?> cls2, Constructor<?> constructor, Constructor<?> constructor2, Method method, Method method2, JSONType jSONType, List<FieldInfo> list) {
        JSONField jSONField;
        this.clazz = cls;
        this.builderClass = cls2;
        this.defaultConstructor = constructor;
        this.creatorConstructor = constructor2;
        this.factoryMethod = method;
        this.parserFeatures = TypeUtils.getParserFeatures(cls);
        this.buildMethod = method2;
        this.jsonType = jSONType;
        if (jSONType != null) {
            String strTypeName = jSONType.typeName();
            String strTypeKey = jSONType.typeKey();
            this.typeKey = strTypeKey.length() <= 0 ? null : strTypeKey;
            if (strTypeName.length() != 0) {
                this.typeName = strTypeName;
            } else {
                this.typeName = cls.getName();
            }
            String[] strArrOrders = jSONType.orders();
            this.orders = strArrOrders.length == 0 ? null : strArrOrders;
        } else {
            this.typeName = cls.getName();
            this.typeKey = null;
            this.orders = null;
        }
        FieldInfo[] fieldInfoArr = new FieldInfo[list.size()];
        this.fields = fieldInfoArr;
        list.toArray(fieldInfoArr);
        FieldInfo[] fieldInfoArr2 = new FieldInfo[fieldInfoArr.length];
        if (this.orders != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
            for (FieldInfo fieldInfo : fieldInfoArr) {
                linkedHashMap.put(fieldInfo.name, fieldInfo);
            }
            int i = 0;
            for (String str : this.orders) {
                FieldInfo fieldInfo2 = (FieldInfo) linkedHashMap.get(str);
                if (fieldInfo2 != null) {
                    fieldInfoArr2[i] = fieldInfo2;
                    linkedHashMap.remove(str);
                    i++;
                }
            }
            Iterator it = linkedHashMap.values().iterator();
            while (it.hasNext()) {
                fieldInfoArr2[i] = (FieldInfo) it.next();
                i++;
            }
        } else {
            System.arraycopy(fieldInfoArr, 0, fieldInfoArr2, 0, fieldInfoArr.length);
            Arrays.sort(fieldInfoArr2);
        }
        this.sortedFields = Arrays.equals(this.fields, fieldInfoArr2) ? this.fields : fieldInfoArr2;
        if (constructor != null) {
            this.defaultConstructorParameterSize = constructor.getParameterTypes().length;
        } else if (method != null) {
            this.defaultConstructorParameterSize = method.getParameterTypes().length;
        } else {
            this.defaultConstructorParameterSize = 0;
        }
        if (constructor2 != null) {
            Class<?>[] parameterTypes = constructor2.getParameterTypes();
            this.creatorConstructorParameterTypes = parameterTypes;
            if (parameterTypes.length == this.fields.length) {
                int i2 = 0;
                while (true) {
                    Type[] typeArr = this.creatorConstructorParameterTypes;
                    if (i2 >= typeArr.length) {
                        return;
                    }
                    if (typeArr[i2] != this.fields[i2].fieldClass) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            if (TypeUtils.isKotlin(cls)) {
                this.creatorConstructorParameters = TypeUtils.getKoltinConstructorParameters(cls);
                Annotation[][] parameterAnnotations = constructor2.getParameterAnnotations();
                for (int i3 = 0; i3 < this.creatorConstructorParameters.length && i3 < parameterAnnotations.length; i3++) {
                    Annotation[] annotationArr = parameterAnnotations[i3];
                    int length = annotationArr.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length) {
                            jSONField = null;
                            break;
                        }
                        Annotation annotation = annotationArr[i4];
                        if (annotation instanceof JSONField) {
                            jSONField = (JSONField) annotation;
                            break;
                        }
                        i4++;
                    }
                    if (jSONField != null) {
                        String strName = jSONField.name();
                        if (strName.length() > 0) {
                            this.creatorConstructorParameters[i3] = strName;
                        }
                    }
                }
                return;
            }
            this.creatorConstructorParameters = ASMUtils.lookupParameterNames(constructor2);
        }
    }

    private static FieldInfo getField(List<FieldInfo> list, String str) {
        for (FieldInfo fieldInfo : list) {
            if (fieldInfo.name.equals(str)) {
                return fieldInfo;
            }
            Field field = fieldInfo.field;
            if (field != null && fieldInfo.getAnnotation() != null && field.getName().equals(str)) {
                return fieldInfo;
            }
        }
        return null;
    }

    static boolean add(List<FieldInfo> list, FieldInfo fieldInfo) {
        int size = list.size() - 1;
        while (size >= 0) {
            FieldInfo fieldInfo2 = list.get(size);
            if (fieldInfo2.name.equals(fieldInfo.name) && (!fieldInfo2.getOnly || fieldInfo.getOnly)) {
                if (fieldInfo2.fieldClass.isAssignableFrom(fieldInfo.fieldClass) || fieldInfo2.compareTo(fieldInfo) < 0) {
                    list.remove(size);
                    break;
                }
                return false;
            }
            size--;
        }
        list.add(fieldInfo);
        return true;
    }

    public static JavaBeanInfo build(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy) {
        return build(cls, type, propertyNamingStrategy, false, TypeUtils.compatibleWithJavaBean);
    }

    /* JADX WARN: Code duplicated, block: B:142:0x028f  */
    /* JADX WARN: Code duplicated, block: B:143:0x0295  */
    /* JADX WARN: Code duplicated, block: B:198:0x03ba  */
    /* JADX WARN: Code duplicated, block: B:224:0x0495 A[PHI: r10
      0x0495: PHI (r10v20 java.lang.String) = (r10v19 java.lang.String), (r10v19 java.lang.String), (r10v21 java.lang.String) binds: [B:223:0x0493, B:226:0x04a0, B:230:0x04b9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:261:0x0547  */
    /* JADX WARN: Code duplicated, block: B:263:0x0557  */
    /* JADX WARN: Code duplicated, block: B:265:0x056d  */
    /* JADX WARN: Code duplicated, block: B:275:0x0594  */
    /* JADX WARN: Code duplicated, block: B:357:0x07d7  */
    /* JADX WARN: Code duplicated, block: B:359:0x07e3  */
    /* JADX WARN: Code duplicated, block: B:391:0x0870  */
    /* JADX WARN: Code duplicated, block: B:393:0x0894  */
    /* JADX WARN: Code duplicated, block: B:398:0x08a5 A[DONT_INVERT, PHI: r0 r6
      0x08a5: PHI (r0v8 java.lang.String) = (r0v7 java.lang.String), (r0v7 java.lang.String), (r0v7 java.lang.String), (r0v14 java.lang.String) binds: [B:392:0x0892, B:394:0x089c, B:396:0x08a2, B:390:0x0868] A[DONT_GENERATE, DONT_INLINE]
      0x08a5: PHI (r6v4 java.lang.reflect.Field[]) = 
      (r6v2 java.lang.reflect.Field[])
      (r6v2 java.lang.reflect.Field[])
      (r6v2 java.lang.reflect.Field[])
      (r6v6 java.lang.reflect.Field[])
     binds: [B:392:0x0892, B:394:0x089c, B:396:0x08a2, B:390:0x0868] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:399:0x08a7  */
    /* JADX WARN: Code duplicated, block: B:403:0x08bc  */
    /* JADX WARN: Code duplicated, block: B:420:0x07c0 A[EDGE_INSN: B:420:0x07c0->B:355:0x07c0 BREAK  A[LOOP:4: B:259:0x0544->B:354:0x07b0], SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:391:0x0870, please report this as an issue */
    public static JavaBeanInfo build(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy, boolean z, boolean z2) {
        Constructor<?> defaultConstructor;
        Constructor<?> constructor;
        ArrayList arrayList;
        Method[] methodArr;
        PropertyNamingStrategy propertyNamingStrategy2;
        Constructor<?> creatorConstructor;
        Field[] fieldArr;
        JSONType jSONType;
        Method[] methodArr2;
        Class<?> cls2;
        JSONField jSONField;
        Method[] methodArr3;
        int length;
        int i;
        int i2;
        PropertyNamingStrategy propertyNamingStrategy3;
        Method[] methods;
        int length2;
        int i3;
        Method method;
        String name;
        JSONField jSONField2;
        String strTranslate;
        Field[] fieldArr2;
        Field field;
        JSONField jSONField3;
        Field[] fieldArr3;
        String str;
        Method method2;
        Class<?> returnType;
        Class<?>[] parameterTypes;
        int i4;
        int i5;
        Method[] methodArr4;
        JSONField jSONField4;
        String strDecapitalize;
        int iOrdinal;
        int iOf;
        int i6;
        JSONField jSONField5;
        int i7;
        int i8;
        int i9;
        String str2;
        StringBuilder sb;
        String str3;
        String[] strArr;
        String[] strArrLookupParameterNames;
        String[] strArrLookupParameterNames2;
        Class<?>[] parameterTypes2;
        JSONField jSONField6;
        int iOf2;
        int i10;
        int i11;
        JSONField jSONField7;
        JSONField jSONField8;
        Field field2;
        String strName;
        int iOrdinal2;
        int iOf3;
        int iOf4;
        PropertyNamingStrategy propertyNamingStrategyNaming;
        JSONType jSONType2 = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
        PropertyNamingStrategy propertyNamingStrategy4 = (jSONType2 == null || (propertyNamingStrategyNaming = jSONType2.naming()) == null || propertyNamingStrategyNaming == PropertyNamingStrategy.CamelCase) ? propertyNamingStrategy : propertyNamingStrategyNaming;
        Class<?> builderClass = getBuilderClass(cls, jSONType2);
        Field[] declaredFields = cls.getDeclaredFields();
        Method[] methods2 = cls.getMethods();
        boolean zIsKotlin = TypeUtils.isKotlin(cls);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        if (!zIsKotlin || declaredConstructors.length == 1) {
            if (builderClass == null) {
                defaultConstructor = getDefaultConstructor(cls, declaredConstructors);
            } else {
                defaultConstructor = getDefaultConstructor(builderClass, builderClass.getDeclaredConstructors());
            }
            constructor = defaultConstructor;
        } else {
            constructor = null;
        }
        ArrayList arrayList2 = new ArrayList();
        Method method3 = null;
        Method factoryMethod = null;
        if (z) {
            for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
                computeFields(cls, type, propertyNamingStrategy4, arrayList2, superclass.getDeclaredFields());
            }
            return new JavaBeanInfo(cls, builderClass, constructor, null, null, null, jSONType2, arrayList2);
        }
        boolean z3 = cls.isInterface() || Modifier.isAbstract(cls.getModifiers());
        if ((constructor == null && builderClass == null) || z3) {
            creatorConstructor = getCreatorConstructor(declaredConstructors);
            if (creatorConstructor != null && !z3) {
                TypeUtils.setAccessible(creatorConstructor);
                Class<?>[] parameterTypes3 = creatorConstructor.getParameterTypes();
                if (parameterTypes3.length > 0) {
                    Annotation[][] parameterAnnotations = creatorConstructor.getParameterAnnotations();
                    String[] strArrLookupParameterNames3 = null;
                    int i12 = 0;
                    while (i12 < parameterTypes3.length) {
                        Annotation[] annotationArr = parameterAnnotations[i12];
                        int length3 = annotationArr.length;
                        int i13 = 0;
                        while (true) {
                            if (i13 >= length3) {
                                jSONField8 = null;
                                break;
                            }
                            Annotation annotation = annotationArr[i13];
                            if (annotation instanceof JSONField) {
                                jSONField8 = (JSONField) annotation;
                                break;
                            }
                            i13++;
                        }
                        Class<?> cls3 = parameterTypes3[i12];
                        Type type2 = creatorConstructor.getGenericParameterTypes()[i12];
                        if (jSONField8 != null) {
                            field2 = TypeUtils.getField(cls, jSONField8.name(), declaredFields);
                            iOrdinal2 = jSONField8.ordinal();
                            iOf3 = SerializerFeature.of(jSONField8.serialzeFeatures());
                            iOf4 = Feature.of(jSONField8.parseFeatures());
                            strName = jSONField8.name();
                        } else {
                            field2 = null;
                            strName = null;
                            iOrdinal2 = 0;
                            iOf3 = 0;
                            iOf4 = 0;
                        }
                        if (strName == null || strName.length() == 0) {
                            if (strArrLookupParameterNames3 == null) {
                                strArrLookupParameterNames3 = ASMUtils.lookupParameterNames(creatorConstructor);
                            }
                            strName = strArrLookupParameterNames3[i12];
                        }
                        String[] strArr2 = strArrLookupParameterNames3;
                        ArrayList arrayList3 = arrayList2;
                        add(arrayList3, new FieldInfo(strName, cls, cls3, type2, field2, iOrdinal2, iOf3, iOf4));
                        i12++;
                        arrayList2 = arrayList3;
                        propertyNamingStrategy4 = propertyNamingStrategy4;
                        strArrLookupParameterNames3 = strArr2;
                        parameterTypes3 = parameterTypes3;
                    }
                }
                arrayList = arrayList2;
                propertyNamingStrategy2 = propertyNamingStrategy4;
            } else {
                arrayList = arrayList2;
                propertyNamingStrategy2 = propertyNamingStrategy4;
                factoryMethod = getFactoryMethod(cls, methods2);
                if (factoryMethod != null) {
                    TypeUtils.setAccessible(factoryMethod);
                    Class<?>[] parameterTypes4 = factoryMethod.getParameterTypes();
                    if (parameterTypes4.length > 0) {
                        Annotation[][] parameterAnnotations2 = factoryMethod.getParameterAnnotations();
                        int i14 = 0;
                        while (i14 < parameterTypes4.length) {
                            Annotation[] annotationArr2 = parameterAnnotations2[i14];
                            int length4 = annotationArr2.length;
                            int i15 = 0;
                            while (true) {
                                if (i15 >= length4) {
                                    jSONField7 = null;
                                    break;
                                }
                                Annotation annotation2 = annotationArr2[i15];
                                if (annotation2 instanceof JSONField) {
                                    jSONField7 = (JSONField) annotation2;
                                    break;
                                }
                                i15++;
                            }
                            if (jSONField7 == null) {
                                throw new JSONException("illegal json creator");
                            }
                            add(arrayList, new FieldInfo(jSONField7.name(), cls, parameterTypes4[i14], factoryMethod.getGenericParameterTypes()[i14], TypeUtils.getField(cls, jSONField7.name(), declaredFields), jSONField7.ordinal(), SerializerFeature.of(jSONField7.serialzeFeatures()), Feature.of(jSONField7.parseFeatures())));
                            i14++;
                            parameterAnnotations2 = parameterAnnotations2;
                            parameterTypes4 = parameterTypes4;
                        }
                        return new JavaBeanInfo(cls, builderClass, null, null, factoryMethod, null, jSONType2, arrayList);
                    }
                } else if (!z3) {
                    String name2 = cls.getName();
                    if (zIsKotlin && declaredConstructors.length > 0) {
                        String[] koltinConstructorParameters = TypeUtils.getKoltinConstructorParameters(cls);
                        Constructor<?> koltinConstructor = TypeUtils.getKoltinConstructor(declaredConstructors);
                        TypeUtils.setAccessible(koltinConstructor);
                        creatorConstructor = koltinConstructor;
                        strArr = koltinConstructorParameters;
                    } else {
                        int length5 = declaredConstructors.length;
                        String[] strArr3 = null;
                        int i16 = 0;
                        while (true) {
                            if (i16 < length5) {
                                Constructor<?> constructor2 = declaredConstructors[i16];
                                Class<?>[] parameterTypes5 = constructor2.getParameterTypes();
                                if (name2.equals("org.springframework.security.web.authentication.WebAuthenticationDetails") && parameterTypes5.length == 2 && parameterTypes5[0] == String.class && parameterTypes5[1] == String.class) {
                                    constructor2.setAccessible(true);
                                    strArrLookupParameterNames2 = ASMUtils.lookupParameterNames(constructor2);
                                } else {
                                    if (name2.equals("org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken")) {
                                        if (parameterTypes5.length == 3) {
                                            if (parameterTypes5[0] == Object.class && parameterTypes5[1] == Object.class && parameterTypes5[2] == Collection.class) {
                                                constructor2.setAccessible(true);
                                                strArrLookupParameterNames2 = new String[]{"principal", "credentials", "authorities"};
                                            }
                                        }
                                    }
                                    if (name2.equals("org.springframework.security.core.authority.SimpleGrantedAuthority") && parameterTypes5.length == 1) {
                                        if (parameterTypes5[0] == String.class) {
                                            strArr = new String[]{"authority"};
                                            creatorConstructor = constructor2;
                                        }
                                    }
                                    if ((constructor2.getModifiers() & 1) != 0 && (strArrLookupParameterNames = ASMUtils.lookupParameterNames(constructor2)) != null && strArrLookupParameterNames.length != 0 && (creatorConstructor == null || strArr3 == null || strArrLookupParameterNames.length > strArr3.length)) {
                                        creatorConstructor = constructor2;
                                        strArr3 = strArrLookupParameterNames;
                                    }
                                    i16++;
                                }
                                strArr = strArrLookupParameterNames2;
                                creatorConstructor = constructor2;
                            } else {
                                strArr = strArr3;
                            }
                            if (strArr != null) {
                                parameterTypes2 = creatorConstructor.getParameterTypes();
                            } else {
                                parameterTypes2 = null;
                            }
                            if (strArr == null && parameterTypes2.length == strArr.length) {
                                Annotation[][] parameterAnnotations3 = creatorConstructor.getParameterAnnotations();
                                int i17 = 0;
                                while (i17 < parameterTypes2.length) {
                                    Annotation[] annotationArr3 = parameterAnnotations3[i17];
                                    String str4 = strArr[i17];
                                    int length6 = annotationArr3.length;
                                    int i18 = 0;
                                    while (true) {
                                        if (i18 >= length6) {
                                            jSONField6 = null;
                                            break;
                                        }
                                        Annotation annotation3 = annotationArr3[i18];
                                        if (annotation3 instanceof JSONField) {
                                            jSONField6 = (JSONField) annotation3;
                                            break;
                                        }
                                        i18++;
                                    }
                                    Class<?> cls4 = parameterTypes2[i17];
                                    Type type3 = creatorConstructor.getGenericParameterTypes()[i17];
                                    Field field3 = TypeUtils.getField(cls, str4, declaredFields);
                                    if (field3 != null && jSONField6 == null) {
                                        jSONField6 = (JSONField) field3.getAnnotation(JSONField.class);
                                    }
                                    if (jSONField6 == null) {
                                        if ("org.springframework.security.core.userdetails.User".equals(name2) && "password".equals(str4)) {
                                            iOf2 = Feature.InitStringFieldAsEmpty.mask;
                                            i11 = 0;
                                            i10 = 0;
                                        } else {
                                            i11 = 0;
                                            i10 = 0;
                                            iOf2 = 0;
                                        }
                                    } else {
                                        String strName2 = jSONField6.name();
                                        if (strName2.length() != 0) {
                                            str4 = strName2;
                                        }
                                        int iOrdinal3 = jSONField6.ordinal();
                                        int iOf5 = SerializerFeature.of(jSONField6.serialzeFeatures());
                                        iOf2 = Feature.of(jSONField6.parseFeatures());
                                        i10 = iOf5;
                                        i11 = iOrdinal3;
                                    }
                                    add(arrayList, new FieldInfo(str4, cls, cls4, type3, field3, i11, i10, iOf2));
                                    i17++;
                                    parameterTypes2 = parameterTypes2;
                                    strArr = strArr;
                                    name2 = name2;
                                    methods2 = methods2;
                                }
                                methodArr = methods2;
                                if (!zIsKotlin && !cls.getName().equals("javax.servlet.http.Cookie")) {
                                    return new JavaBeanInfo(cls, builderClass, null, creatorConstructor, null, null, jSONType2, arrayList);
                                }
                            } else {
                                throw new JSONException("default constructor not found. " + cls);
                            }
                        }
                    }
                    if (strArr != null) {
                        parameterTypes2 = creatorConstructor.getParameterTypes();
                    } else {
                        parameterTypes2 = null;
                    }
                    if (strArr == null) {
                    }
                    throw new JSONException("default constructor not found. " + cls);
                }
            }
            methodArr = methods2;
        } else {
            arrayList = arrayList2;
            methodArr = methods2;
            propertyNamingStrategy2 = propertyNamingStrategy4;
            creatorConstructor = null;
        }
        if (constructor != null) {
            TypeUtils.setAccessible(constructor);
        }
        String str5 = "set";
        if (builderClass != null) {
            JSONPOJOBuilder jSONPOJOBuilder = (JSONPOJOBuilder) builderClass.getAnnotation(JSONPOJOBuilder.class);
            String strWithPrefix = jSONPOJOBuilder != null ? jSONPOJOBuilder.withPrefix() : null;
            if (strWithPrefix == null || strWithPrefix.length() == 0) {
                strWithPrefix = "with";
            }
            String str6 = strWithPrefix;
            Method[] methods3 = builderClass.getMethods();
            int length7 = methods3.length;
            int i19 = 0;
            while (i19 < length7) {
                Method method4 = methods3[i19];
                if (!Modifier.isStatic(method4.getModifiers()) && method4.getReturnType().equals(builderClass)) {
                    JSONField superMethodAnnotation = (JSONField) method4.getAnnotation(JSONField.class);
                    if (superMethodAnnotation == null) {
                        superMethodAnnotation = TypeUtils.getSuperMethodAnnotation(cls, method4);
                    }
                    JSONField jSONField9 = superMethodAnnotation;
                    if (jSONField9 == null) {
                        i7 = 0;
                        i8 = 0;
                        i9 = 0;
                    } else if (jSONField9.deserialize()) {
                        int iOrdinal4 = jSONField9.ordinal();
                        int iOf6 = SerializerFeature.of(jSONField9.serialzeFeatures());
                        int iOf7 = Feature.of(jSONField9.parseFeatures());
                        if (jSONField9.name().length() != 0) {
                            i19 = i19;
                            length7 = length7;
                            methods3 = methods3;
                            methodArr = methodArr;
                            declaredFields = declaredFields;
                            jSONType2 = jSONType2;
                            builderClass = builderClass;
                            add(arrayList, new FieldInfo(jSONField9.name(), method4, null, cls, type, iOrdinal4, iOf6, iOf7, jSONField9, null, null));
                            str3 = str6;
                        } else {
                            i7 = iOrdinal4;
                            i8 = iOf6;
                            i9 = iOf7;
                        }
                    } else {
                        i19 = i19;
                        length7 = length7;
                        methods3 = methods3;
                        str3 = str6;
                        declaredFields = declaredFields;
                        jSONType2 = jSONType2;
                        methodArr = methodArr;
                        builderClass = builderClass;
                    }
                    String name3 = method4.getName();
                    if (name3.startsWith("set") && name3.length() > 3) {
                        sb = new StringBuilder(name3.substring(3));
                        str2 = str6;
                    } else {
                        str2 = str6;
                        if (name3.startsWith(str2) && name3.length() > str2.length()) {
                            sb = new StringBuilder(name3.substring(str2.length()));
                        } else {
                            str3 = str2;
                        }
                    }
                    char cCharAt = sb.charAt(0);
                    if (Character.isUpperCase(cCharAt)) {
                        sb.setCharAt(0, Character.toLowerCase(cCharAt));
                        str3 = str2;
                        add(arrayList, new FieldInfo(sb.toString(), method4, null, cls, type, i7, i8, i9, jSONField9, null, null));
                    } else {
                        str3 = str2;
                    }
                } else {
                    i19 = i19;
                    length7 = length7;
                    methods3 = methods3;
                    str3 = str6;
                    declaredFields = declaredFields;
                    jSONType2 = jSONType2;
                    methodArr = methodArr;
                    builderClass = builderClass;
                }
                i19++;
                builderClass = builderClass;
                str6 = str3;
                jSONType2 = jSONType2;
                length7 = length7;
                methods3 = methods3;
                methodArr = methodArr;
                declaredFields = declaredFields;
            }
            fieldArr = declaredFields;
            jSONType = jSONType2;
            methodArr2 = methodArr;
            cls2 = builderClass;
            if (cls2 != null) {
                JSONPOJOBuilder jSONPOJOBuilder2 = (JSONPOJOBuilder) cls2.getAnnotation(JSONPOJOBuilder.class);
                String strBuildMethod = jSONPOJOBuilder2 != null ? jSONPOJOBuilder2.buildMethod() : null;
                if (strBuildMethod == null || strBuildMethod.length() == 0) {
                    strBuildMethod = "build";
                }
                jSONField = null;
                try {
                    method3 = cls2.getMethod(strBuildMethod, null);
                } catch (NoSuchMethodException | SecurityException unused) {
                }
                if (method3 == null) {
                    try {
                        method3 = cls2.getMethod("create", null);
                    } catch (NoSuchMethodException | SecurityException unused2) {
                    }
                }
                if (method3 == null) {
                    throw new JSONException("buildMethod not found.");
                }
                TypeUtils.setAccessible(method3);
            }
            methodArr3 = methodArr2;
            length = methodArr3.length;
            i = 0;
            while (true) {
                i2 = 4;
                if (i < length) {
                    break;
                }
                method2 = methodArr3[i];
                String name4 = method2.getName();
                if (Modifier.isStatic(method2.getModifiers())) {
                    i4 = i;
                    i5 = length;
                    methodArr4 = methodArr3;
                    jSONField4 = jSONField;
                    cls2 = cls2;
                } else {
                    returnType = method2.getReturnType();
                    if ((!returnType.equals(Void.TYPE) || returnType.equals(method2.getDeclaringClass())) && method2.getDeclaringClass() != Object.class) {
                        parameterTypes = method2.getParameterTypes();
                        if (parameterTypes.length != 0 || parameterTypes.length > 2) {
                            i4 = i;
                            i5 = length;
                            methodArr4 = methodArr3;
                            jSONField4 = jSONField;
                            cls2 = cls2;
                        } else {
                            JSONField jSONField10 = (JSONField) method2.getAnnotation(JSONField.class);
                            int i20 = 0;
                            int i21 = 0;
                            int i22 = 0;
                            if (jSONField10 != null && parameterTypes.length == 2 && parameterTypes[0] == String.class && parameterTypes[1] == Object.class) {
                                i4 = i;
                                i5 = length;
                                methodArr4 = methodArr3;
                                jSONField4 = jSONField;
                                add(arrayList, new FieldInfo("", method2, null, cls, type, 0, 0, 0, jSONField10, null, null));
                            } else {
                                i4 = i;
                                i5 = length;
                                methodArr4 = methodArr3;
                                jSONField4 = jSONField;
                                if (parameterTypes.length == 1) {
                                    JSONField superMethodAnnotation2 = jSONField10 == null ? TypeUtils.getSuperMethodAnnotation(cls, method2) : jSONField10;
                                    if (superMethodAnnotation2 != null || name4.length() >= 4) {
                                        if (superMethodAnnotation2 != null) {
                                            if (superMethodAnnotation2.deserialize()) {
                                                int iOrdinal5 = superMethodAnnotation2.ordinal();
                                                int iOf8 = SerializerFeature.of(superMethodAnnotation2.serialzeFeatures());
                                                int iOf9 = Feature.of(superMethodAnnotation2.parseFeatures());
                                                if (superMethodAnnotation2.name().length() != 0) {
                                                    cls2 = cls2;
                                                    add(arrayList, new FieldInfo(superMethodAnnotation2.name(), method2, null, cls, type, iOrdinal5, iOf8, iOf9, superMethodAnnotation2, null, null));
                                                } else {
                                                    i20 = iOrdinal5;
                                                    i21 = iOf8;
                                                    i22 = iOf9;
                                                }
                                            }
                                        }
                                        if (superMethodAnnotation2 != null || name4.startsWith(str5)) {
                                            char cCharAt2 = name4.charAt(3);
                                            if (Character.isUpperCase(cCharAt2) || cCharAt2 > 512) {
                                                strDecapitalize = TypeUtils.compatibleWithJavaBean ? TypeUtils.decapitalize(name4.substring(3)) : Character.toLowerCase(name4.charAt(3)) + name4.substring(4);
                                            } else if (cCharAt2 == '_') {
                                                strDecapitalize = name4.substring(4);
                                            } else if (cCharAt2 == 'f') {
                                                strDecapitalize = name4.substring(3);
                                            } else if (name4.length() >= 5 && Character.isUpperCase(name4.charAt(4))) {
                                                strDecapitalize = TypeUtils.decapitalize(name4.substring(3));
                                            }
                                            Field[] fieldArr4 = fieldArr;
                                            Field field4 = TypeUtils.getField(cls, strDecapitalize, fieldArr4);
                                            if (field4 == null && parameterTypes[0] == Boolean.TYPE) {
                                                field4 = TypeUtils.getField(cls, "is" + Character.toUpperCase(strDecapitalize.charAt(0)) + strDecapitalize.substring(1), fieldArr4);
                                            }
                                            if (field4 != null) {
                                                JSONField jSONField11 = (JSONField) field4.getAnnotation(JSONField.class);
                                                if (jSONField11 == null) {
                                                    jSONField5 = jSONField11;
                                                    iOrdinal = i20;
                                                    iOf = i21;
                                                    i6 = i22;
                                                } else if (jSONField11.deserialize()) {
                                                    iOrdinal = jSONField11.ordinal();
                                                    iOf = SerializerFeature.of(jSONField11.serialzeFeatures());
                                                    int iOf10 = Feature.of(jSONField11.parseFeatures());
                                                    if (jSONField11.name().length() != 0) {
                                                        str5 = str5;
                                                        add(arrayList, new FieldInfo(jSONField11.name(), method2, field4, cls, type, iOrdinal, iOf, iOf10, superMethodAnnotation2, jSONField11, null));
                                                        fieldArr = fieldArr4;
                                                        propertyNamingStrategy2 = propertyNamingStrategy2;
                                                    } else {
                                                        jSONField5 = jSONField11;
                                                        i6 = iOf10;
                                                    }
                                                } else {
                                                    fieldArr = fieldArr4;
                                                }
                                            } else {
                                                iOrdinal = i20;
                                                iOf = i21;
                                                i6 = i22;
                                                jSONField5 = jSONField4;
                                            }
                                            propertyNamingStrategy2 = propertyNamingStrategy2;
                                            if (propertyNamingStrategy2 != null) {
                                                strDecapitalize = propertyNamingStrategy2.translate(strDecapitalize);
                                            }
                                            fieldArr = fieldArr4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field4, cls, type, iOrdinal, iOf, i6, superMethodAnnotation2, jSONField5, null));
                                        }
                                    }
                                }
                            }
                            cls2 = cls2;
                        }
                    } else {
                        i4 = i;
                        i5 = length;
                        methodArr4 = methodArr3;
                        jSONField4 = jSONField;
                        cls2 = cls2;
                    }
                }
                i = i4 + 1;
                str5 = str5;
                propertyNamingStrategy2 = propertyNamingStrategy2;
                jSONField = jSONField4;
                length = i5;
                methodArr3 = methodArr4;
                cls2 = cls2;
            }
            Class<?> cls5 = cls2;
            propertyNamingStrategy3 = propertyNamingStrategy2;
            computeFields(cls, type, propertyNamingStrategy3, arrayList, cls.getFields());
            methods = cls.getMethods();
            length2 = methods.length;
            i3 = 0;
            while (i3 < length2) {
                method = methods[i3];
                name = method.getName();
                if (name.length() < i2 && !Modifier.isStatic(method.getModifiers()) && cls5 == null && name.startsWith("get") && Character.isUpperCase(name.charAt(3)) && method.getParameterTypes().length == 0 && ((Collection.class.isAssignableFrom(method.getReturnType()) || Map.class.isAssignableFrom(method.getReturnType()) || AtomicBoolean.class == method.getReturnType() || AtomicInteger.class == method.getReturnType() || AtomicLong.class == method.getReturnType()) && ((jSONField2 = (JSONField) method.getAnnotation(JSONField.class)) == null || !jSONField2.deserialize()))) {
                    if (jSONField2 == null && jSONField2.name().length() > 0) {
                        strTranslate = jSONField2.name();
                        fieldArr2 = fieldArr;
                    } else {
                        strTranslate = Character.toLowerCase(name.charAt(3)) + name.substring(i2);
                        fieldArr2 = fieldArr;
                        field = TypeUtils.getField(cls, strTranslate, fieldArr2);
                        if (field != null || (jSONField3 = (JSONField) field.getAnnotation(JSONField.class)) == null || jSONField3.deserialize()) {
                        }
                        fieldArr3 = fieldArr2;
                    }
                    if (propertyNamingStrategy3 != null) {
                        strTranslate = propertyNamingStrategy3.translate(strTranslate);
                    }
                    str = strTranslate;
                    if (getField(arrayList, str) != null) {
                        fieldArr3 = fieldArr2;
                    } else {
                        fieldArr3 = fieldArr2;
                        i3 = i3;
                        length2 = length2;
                        methods = methods;
                        add(arrayList, new FieldInfo(str, method, null, cls, type, 0, 0, 0, jSONField2, null, null));
                    }
                    i3++;
                    length2 = length2;
                    methods = methods;
                    fieldArr = fieldArr3;
                    i2 = 4;
                } else {
                    fieldArr3 = fieldArr;
                }
                i3++;
                length2 = length2;
                methods = methods;
                fieldArr = fieldArr3;
                i2 = 4;
            }
            return new JavaBeanInfo(cls, cls5, constructor, creatorConstructor, factoryMethod, method3, jSONType, arrayList);
        }
        fieldArr = declaredFields;
        jSONType = jSONType2;
        methodArr2 = methodArr;
        cls2 = builderClass;
        jSONField = null;
        methodArr3 = methodArr2;
        length = methodArr3.length;
        i = 0;
        while (true) {
            i2 = 4;
            if (i < length) {
                break;
                break;
            }
            method2 = methodArr3[i];
            String name5 = method2.getName();
            if (Modifier.isStatic(method2.getModifiers())) {
                i4 = i;
                i5 = length;
                methodArr4 = methodArr3;
                jSONField4 = jSONField;
                cls2 = cls2;
            } else {
                returnType = method2.getReturnType();
                if (returnType.equals(Void.TYPE)) {
                    parameterTypes = method2.getParameterTypes();
                    if (parameterTypes.length != 0) {
                        i4 = i;
                        i5 = length;
                        methodArr4 = methodArr3;
                        jSONField4 = jSONField;
                        cls2 = cls2;
                    } else {
                        i4 = i;
                        i5 = length;
                        methodArr4 = methodArr3;
                        jSONField4 = jSONField;
                        cls2 = cls2;
                    }
                } else {
                    parameterTypes = method2.getParameterTypes();
                    if (parameterTypes.length != 0) {
                        i4 = i;
                        i5 = length;
                        methodArr4 = methodArr3;
                        jSONField4 = jSONField;
                        cls2 = cls2;
                    } else {
                        i4 = i;
                        i5 = length;
                        methodArr4 = methodArr3;
                        jSONField4 = jSONField;
                        cls2 = cls2;
                    }
                }
            }
            i = i4 + 1;
            str5 = str5;
            propertyNamingStrategy2 = propertyNamingStrategy2;
            jSONField = jSONField4;
            length = i5;
            methodArr3 = methodArr4;
            cls2 = cls2;
        }
        Class<?> cls6 = cls2;
        propertyNamingStrategy3 = propertyNamingStrategy2;
        computeFields(cls, type, propertyNamingStrategy3, arrayList, cls.getFields());
        methods = cls.getMethods();
        length2 = methods.length;
        i3 = 0;
        while (i3 < length2) {
            method = methods[i3];
            name = method.getName();
            if (name.length() < i2) {
                fieldArr3 = fieldArr;
            } else {
                if (jSONField2 == null) {
                    strTranslate = Character.toLowerCase(name.charAt(3)) + name.substring(i2);
                    fieldArr2 = fieldArr;
                    field = TypeUtils.getField(cls, strTranslate, fieldArr2);
                    if (field != null) {
                        if (propertyNamingStrategy3 != null) {
                            strTranslate = propertyNamingStrategy3.translate(strTranslate);
                        }
                        str = strTranslate;
                        if (getField(arrayList, str) != null) {
                            fieldArr3 = fieldArr2;
                            i3 = i3;
                            length2 = length2;
                            methods = methods;
                            add(arrayList, new FieldInfo(str, method, null, cls, type, 0, 0, 0, jSONField2, null, null));
                        }
                    } else {
                        if (propertyNamingStrategy3 != null) {
                            strTranslate = propertyNamingStrategy3.translate(strTranslate);
                        }
                        str = strTranslate;
                        if (getField(arrayList, str) != null) {
                            fieldArr3 = fieldArr2;
                            i3 = i3;
                            length2 = length2;
                            methods = methods;
                            add(arrayList, new FieldInfo(str, method, null, cls, type, 0, 0, 0, jSONField2, null, null));
                        }
                    }
                } else {
                    strTranslate = Character.toLowerCase(name.charAt(3)) + name.substring(i2);
                    fieldArr2 = fieldArr;
                    field = TypeUtils.getField(cls, strTranslate, fieldArr2);
                    if (field != null) {
                        if (propertyNamingStrategy3 != null) {
                            strTranslate = propertyNamingStrategy3.translate(strTranslate);
                        }
                        str = strTranslate;
                        if (getField(arrayList, str) != null) {
                            fieldArr3 = fieldArr2;
                            i3 = i3;
                            length2 = length2;
                            methods = methods;
                            add(arrayList, new FieldInfo(str, method, null, cls, type, 0, 0, 0, jSONField2, null, null));
                        }
                    } else {
                        if (propertyNamingStrategy3 != null) {
                            strTranslate = propertyNamingStrategy3.translate(strTranslate);
                        }
                        str = strTranslate;
                        if (getField(arrayList, str) != null) {
                            fieldArr3 = fieldArr2;
                            i3 = i3;
                            length2 = length2;
                            methods = methods;
                            add(arrayList, new FieldInfo(str, method, null, cls, type, 0, 0, 0, jSONField2, null, null));
                        }
                    }
                }
                fieldArr3 = fieldArr2;
            }
            i3++;
            length2 = length2;
            methods = methods;
            fieldArr = fieldArr3;
            i2 = 4;
        }
        return new JavaBeanInfo(cls, cls6, constructor, creatorConstructor, factoryMethod, method3, jSONType, arrayList);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0046  */
    /* JADX WARN: Code duplicated, block: B:22:0x0050  */
    /* JADX WARN: Code duplicated, block: B:27:0x0072  */
    /* JADX WARN: Code duplicated, block: B:30:0x0079  */
    /* JADX WARN: Code duplicated, block: B:32:0x0097  */
    /* JADX WARN: Code duplicated, block: B:34:0x009f  */
    /* JADX WARN: Code duplicated, block: B:36:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:43:0x0063 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:44:0x0012 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:45:? A[LOOP:1: B:20:0x004a->B:45:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:6:0x0012  */
    private static void computeFields(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy, List<FieldInfo> list, Field[] fieldArr) {
        Iterator<FieldInfo> it;
        String name;
        JSONField jSONField;
        int i;
        int i2;
        int i3;
        for (Field field : fieldArr) {
            int modifiers = field.getModifiers();
            if ((modifiers & 8) != 0) {
                break;
                break;
            }
            if ((modifiers & 16) != 0) {
                Class<?> type2 = field.getType();
                if (!Map.class.isAssignableFrom(type2) && !Collection.class.isAssignableFrom(type2) && !AtomicLong.class.equals(type2) && !AtomicInteger.class.equals(type2) && !AtomicBoolean.class.equals(type2)) {
                    break;
                    break;
                }
                it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().name.equals(field.getName())) {
                        }
                    } else {
                        name = field.getName();
                        jSONField = (JSONField) field.getAnnotation(JSONField.class);
                        if (jSONField != null) {
                            i = 0;
                            i2 = 0;
                            i3 = 0;
                        } else if (!jSONField.deserialize()) {
                            int iOrdinal = jSONField.ordinal();
                            int iOf = SerializerFeature.of(jSONField.serialzeFeatures());
                            int iOf2 = Feature.of(jSONField.parseFeatures());
                            if (jSONField.name().length() != 0) {
                                name = jSONField.name();
                            }
                            i = iOrdinal;
                            i2 = iOf;
                            i3 = iOf2;
                        }
                        if (propertyNamingStrategy != null) {
                            name = propertyNamingStrategy.translate(name);
                        }
                        add(list, new FieldInfo(name, null, field, cls, type, i, i2, i3, null, jSONField, null));
                        break;
                    }
                    break;
                }
            }
            it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().name.equals(field.getName())) {
                    }
                } else {
                    name = field.getName();
                    jSONField = (JSONField) field.getAnnotation(JSONField.class);
                    if (jSONField != null) {
                        i = 0;
                        i2 = 0;
                        i3 = 0;
                    } else if (!jSONField.deserialize()) {
                        int iOrdinal2 = jSONField.ordinal();
                        int iOf3 = SerializerFeature.of(jSONField.serialzeFeatures());
                        int iOf4 = Feature.of(jSONField.parseFeatures());
                        if (jSONField.name().length() != 0) {
                            name = jSONField.name();
                        }
                        i = iOrdinal2;
                        i2 = iOf3;
                        i3 = iOf4;
                    }
                    if (propertyNamingStrategy != null) {
                        name = propertyNamingStrategy.translate(name);
                    }
                    add(list, new FieldInfo(name, null, field, cls, type, i, i2, i3, null, jSONField, null));
                    break;
                    break;
                }
                break;
                break;
            }
        }
    }

    static Constructor<?> getDefaultConstructor(Class<?> cls, Constructor<?>[] constructorArr) {
        Constructor<?> constructor = null;
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        for (Constructor<?> constructor2 : constructorArr) {
            if (constructor2.getParameterTypes().length == 0) {
                constructor = constructor2;
                break;
            }
        }
        if (constructor != null || !cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
            return constructor;
        }
        for (Constructor<?> constructor3 : constructorArr) {
            Class<?>[] parameterTypes = constructor3.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(cls.getDeclaringClass())) {
                return constructor3;
            }
        }
        return constructor;
    }

    public static Constructor<?> getCreatorConstructor(Constructor[] constructorArr) {
        Constructor constructor = null;
        for (Constructor constructor2 : constructorArr) {
            if (((JSONCreator) constructor2.getAnnotation(JSONCreator.class)) != null) {
                if (constructor != null) {
                    throw new JSONException("multi-JSONCreator");
                }
                constructor = constructor2;
            }
        }
        if (constructor != null) {
            return constructor;
        }
        for (Constructor constructor3 : constructorArr) {
            Annotation[][] parameterAnnotations = constructor3.getParameterAnnotations();
            if (parameterAnnotations.length != 0) {
                int length = parameterAnnotations.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        if (constructor != null) {
                            throw new JSONException("multi-JSONCreator");
                        }
                        constructor = constructor3;
                        break;
                    }
                    Annotation[] annotationArr = parameterAnnotations[i];
                    int length2 = annotationArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            break;
                        }
                        if (annotationArr[i2] instanceof JSONField) {
                            break;
                        }
                        i2++;
                    }
                    i++;
                }
            }
        }
        return constructor;
    }

    private static Method getFactoryMethod(Class<?> cls, Method[] methodArr) {
        Method method = null;
        for (Method method2 : methodArr) {
            if (Modifier.isStatic(method2.getModifiers()) && cls.isAssignableFrom(method2.getReturnType()) && ((JSONCreator) method2.getAnnotation(JSONCreator.class)) != null) {
                if (method != null) {
                    throw new JSONException("multi-JSONCreator");
                }
                method = method2;
            }
        }
        return method;
    }

    public static Class<?> getBuilderClass(JSONType jSONType) {
        return getBuilderClass(null, jSONType);
    }

    public static Class<?> getBuilderClass(Class<?> cls, JSONType jSONType) {
        Class<?> clsBuilder;
        if (cls != null && cls.getName().equals("org.springframework.security.web.savedrequest.DefaultSavedRequest")) {
            return TypeUtils.loadClass("org.springframework.security.web.savedrequest.DefaultSavedRequest$Builder");
        }
        if (jSONType == null || (clsBuilder = jSONType.builder()) == Void.class) {
            return null;
        }
        return clsBuilder;
    }
}

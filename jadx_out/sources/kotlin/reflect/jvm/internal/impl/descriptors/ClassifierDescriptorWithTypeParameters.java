package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public interface ClassifierDescriptorWithTypeParameters extends ClassifierDescriptor, MemberDescriptor, Substitutable<ClassifierDescriptorWithTypeParameters> {
    List<TypeParameterDescriptor> getDeclaredTypeParameters();

    boolean isInner();
}

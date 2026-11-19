package com.weiphone.reader.cache;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/* JADX INFO: loaded from: classes5.dex */
public class MyObjectInputStream extends ObjectInputStream {
    public MyObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override // java.io.ObjectInputStream
    protected ObjectStreamClass readClassDescriptor() throws ClassNotFoundException, IOException {
        ObjectStreamClass classDescriptor = super.readClassDescriptor();
        if (classDescriptor.getName().contains("model.UserModel")) {
            return ObjectStreamClass.lookup(Class.forName(classDescriptor.getName().replace("model.UserModel", "model.user.OldUserModel")));
        }
        return classDescriptor.getName().contains("model.user.UserModel") ? ObjectStreamClass.lookup(Class.forName(classDescriptor.getName().replace("model.user.UserModel", "model.user.OldUserModel"))) : classDescriptor;
    }
}

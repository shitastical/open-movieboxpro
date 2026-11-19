package androidx.leanback.app;

import android.app.Notification;
import android.media.session.MediaSessionManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputContentInfo;
import java.util.Map;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class FragmentUtil$$ExternalSyntheticApiModelOutline0 {
    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ Notification.DecoratedMediaCustomViewStyle m186m() {
        return new Notification.DecoratedMediaCustomViewStyle();
    }

    public static /* synthetic */ MediaSessionManager.RemoteUserInfo m(String str, int i, int i2) {
        return new MediaSessionManager.RemoteUserInfo(str, i, i2);
    }

    public static /* synthetic */ AccessibilityNodeInfo.TouchDelegateInfo m(Map map) {
        return new AccessibilityNodeInfo.TouchDelegateInfo(map);
    }

    public static /* bridge */ /* synthetic */ InputContentInfo m(Object obj) {
        return (InputContentInfo) obj;
    }
}

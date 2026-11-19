package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannelGroup;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Icon;
import android.telephony.SubscriptionManager;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class NotificationCompat$$ExternalSyntheticApiModelOutline9 {
    public static /* synthetic */ Notification.Action.Builder m(Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
        return new Notification.Action.Builder(icon, charSequence, pendingIntent);
    }

    public static /* synthetic */ Notification.Builder m(Context context, String str) {
        return new Notification.Builder(context, str);
    }

    public static /* synthetic */ Notification.DecoratedCustomViewStyle m() {
        return new Notification.DecoratedCustomViewStyle();
    }

    public static /* synthetic */ Notification.MessagingStyle.Message m(CharSequence charSequence, long j, android.app.Person person) {
        return new Notification.MessagingStyle.Message(charSequence, j, person);
    }

    public static /* synthetic */ Notification.MessagingStyle.Message m(CharSequence charSequence, long j, CharSequence charSequence2) {
        return new Notification.MessagingStyle.Message(charSequence, j, charSequence2);
    }

    public static /* synthetic */ Notification.MessagingStyle m(android.app.Person person) {
        return new Notification.MessagingStyle(person);
    }

    public static /* synthetic */ Notification.MessagingStyle m(CharSequence charSequence) {
        return new Notification.MessagingStyle(charSequence);
    }

    public static /* bridge */ /* synthetic */ NotificationChannelGroup m(Object obj) {
        return (NotificationChannelGroup) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ ShortcutInfo.Builder m52m(Context context, String str) {
        return new ShortcutInfo.Builder(context, str);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ShortcutInfo m53m(Object obj) {
        return (ShortcutInfo) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Icon m54m(Object obj) {
        return (Icon) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m57m() {
        return Notification.MessagingStyle.class;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m62m() {
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m66m(Object obj) {
        return obj instanceof Icon;
    }

    public static /* bridge */ /* synthetic */ Class m$1() {
        return Notification.DecoratedCustomViewStyle.class;
    }

    /* JADX INFO: renamed from: m$1, reason: collision with other method in class */
    public static /* synthetic */ void m68m$1() {
    }

    public static /* bridge */ /* synthetic */ Class m$2() {
        return SubscriptionManager.class;
    }

    /* JADX INFO: renamed from: m$2, reason: collision with other method in class */
    public static /* synthetic */ void m70m$2() {
    }

    public static /* synthetic */ void m$3() {
    }

    public static /* synthetic */ void m$4() {
    }
}

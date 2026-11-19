package ly.count.android.sdk.messaging;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import ly.count.android.sdk.Countly;
import ly.count.android.sdk.ModuleLog;

/* JADX INFO: loaded from: classes6.dex */
public class CountlyPushActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        performPushAction(getIntent());
        finish();
    }

    /* JADX WARN: Code duplicated, block: B:26:0x007f A[PHI: r7
      0x007f: PHI (r7v12 boolean) = (r7v10 boolean), (r7v10 boolean), (r7v13 boolean) binds: [B:20:0x0073, B:22:0x0079, B:24:0x007c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:86:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:0x0088 A[EDGE_INSN: B:87:0x0088->B:30:0x0088 BREAK  A[LOOP:0: B:17:0x0066->B:88:0x0066], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x0066 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x0066 A[SYNTHETIC] */
    private void performPushAction(Intent intent) {
        boolean z;
        Countly.sharedInstance().L.d("[CountlyPush, CountlyPushActivity] Push activity receiver receiving message");
        intent.setExtrasClassLoader(CountlyPush.class.getClassLoader());
        Intent intent2 = (Intent) intent.getParcelableExtra(CountlyPush.EXTRA_INTENT);
        if (intent2 == null) {
            Countly.sharedInstance().L.e("[CountlyPush, CountlyPushActivity] Received a null Intent, stopping execution");
            return;
        }
        int flags = intent2.getFlags();
        if ((flags & 1) != 0 || (flags & 2) != 0) {
            Countly.sharedInstance().L.w("[CountlyPush, CountlyPushActivity] Attempt to get URI permissions");
            return;
        }
        if (CountlyPush.useAdditionalIntentRedirectionChecks) {
            ComponentName component = intent2.getComponent();
            String packageName = component.getPackageName();
            String className = component.getClassName();
            String packageName2 = getPackageName();
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(CountlyPush.ALLOWED_CLASS_NAMES);
            ArrayList<String> stringArrayListExtra2 = intent.getStringArrayListExtra(CountlyPush.ALLOWED_PACKAGE_NAMES);
            if (packageName != null) {
                stringArrayListExtra2.add(packageName2);
            }
            Iterator<String> it = stringArrayListExtra2.iterator();
            boolean z2 = false;
            boolean z3 = false;
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (packageName != null && packageName.startsWith(next)) {
                    z3 = true;
                    if (z2) {
                        break;
                    }
                    if (className.startsWith(next)) {
                        z2 = true;
                        if (z3) {
                            break;
                            break;
                        }
                    }
                } else if (className.startsWith(next)) {
                    z2 = true;
                    if (z3) {
                        break;
                    }
                }
            }
            if (!z3) {
                Countly.sharedInstance().L.w("[CountlyPush, CountlyPushActivity] Untrusted intent package");
                return;
            }
            if (!z2) {
                Iterator<String> it2 = stringArrayListExtra.iterator();
                do {
                    if (!it2.hasNext()) {
                        z = z2;
                        break;
                    }
                } while (!className.endsWith(it2.next()));
                if (!z) {
                    Countly.sharedInstance().L.w("[CountlyPush, CountlyPushActivity] Untrusted intent class");
                    return;
                }
            }
        }
        Countly.sharedInstance().L.d("[CountlyPush, CountlyPushActivity] Push activity, after filtering");
        intent2.setExtrasClassLoader(CountlyPush.class.getClassLoader());
        int intExtra = intent2.getIntExtra(CountlyPush.EXTRA_ACTION_INDEX, 0);
        Bundle bundle = (Bundle) intent2.getParcelableExtra(CountlyPush.EXTRA_MESSAGE);
        if (bundle == null) {
            Countly.sharedInstance().L.e("[CountlyPush, CountlyPushActivity] Received a null Intent bundle, stopping execution");
            return;
        }
        CountlyPush.Message message = (CountlyPush.Message) bundle.getParcelable(CountlyPush.EXTRA_MESSAGE);
        if (message == null) {
            Countly.sharedInstance().L.e("[CountlyPush, CountlyPushActivity] Received a null Intent bundle message, stopping execution");
            return;
        }
        message.recordAction(this, intExtra);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancel(message.hashCode());
        }
        try {
            if (Build.VERSION.SDK_INT < 31) {
                sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
            }
        } catch (Exception e) {
            Countly.sharedInstance().L.e("[CountlyPush, CountlyPushActivity] Encountered issue while trying to send the on click broadcast. [" + e.toString() + "]");
        }
        if (intExtra == 0) {
            try {
                if (message.link() != null) {
                    Countly.sharedInstance().L.d("[CountlyPush, CountlyPushActivity] Starting activity with given link. Push body. [" + message.link() + "]");
                    Intent intent3 = new Intent("android.intent.action.VIEW", message.link());
                    intent3.setFlags(1476919296);
                    intent3.putExtra(CountlyPush.EXTRA_MESSAGE, bundle);
                    intent3.putExtra(CountlyPush.EXTRA_ACTION_INDEX, intExtra);
                    startActivity(intent3);
                } else {
                    Countly.sharedInstance().L.d("[CountlyPush, CountlyPushActivity] Starting activity without a link. Push body");
                    intent2.setFlags(268435456);
                    startActivity(intent2);
                }
                return;
            } catch (Exception e2) {
                Countly.sharedInstance().L.e("[CountlyPush, displayDialog] Encountered issue while clicking on notification body [" + e2.toString() + "]");
                return;
            }
        }
        try {
            ModuleLog moduleLog = Countly.sharedInstance().L;
            StringBuilder sb = new StringBuilder();
            sb.append("[CountlyPush, CountlyPushActivity] Starting activity with given button link. [");
            int i = intExtra - 1;
            sb.append(i);
            sb.append("] [");
            sb.append(message.buttons().get(i).link());
            sb.append("]");
            moduleLog.d(sb.toString());
            Intent intent4 = new Intent("android.intent.action.VIEW", message.buttons().get(i).link());
            intent4.setFlags(268435456);
            intent4.putExtra(CountlyPush.EXTRA_MESSAGE, bundle);
            intent4.putExtra(CountlyPush.EXTRA_ACTION_INDEX, intExtra);
            startActivity(intent4);
        } catch (Exception e3) {
            Countly.sharedInstance().L.e("[CountlyPush, displayDialog] Encountered issue while clicking on notification button [" + e3.toString() + "]");
        }
    }
}

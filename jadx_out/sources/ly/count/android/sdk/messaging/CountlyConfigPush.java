package ly.count.android.sdk.messaging;

import android.app.Application;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ly.count.android.sdk.Countly;

/* JADX INFO: loaded from: classes6.dex */
public class CountlyConfigPush {
    Set<String> allowedIntentClassNames = new HashSet();
    Set<String> allowedIntentPackageNames = new HashSet();
    Application application;
    Countly.CountlyMessagingProvider provider;

    public CountlyConfigPush(Application application, Countly.CountlyMessagingMode countlyMessagingMode) {
        this.application = application;
    }

    public CountlyConfigPush(Application application) {
        this.application = application;
    }

    public synchronized CountlyConfigPush setProvider(Countly.CountlyMessagingProvider countlyMessagingProvider) {
        this.provider = countlyMessagingProvider;
        return this;
    }

    public synchronized CountlyConfigPush setAllowedIntentClassNames(List<String> list) {
        this.allowedIntentClassNames = new HashSet(list);
        return this;
    }

    public synchronized CountlyConfigPush setAllowedIntentPackageNames(List<String> list) {
        this.allowedIntentPackageNames = new HashSet(list);
        return this;
    }
}

package ly.count.android.sdk.messaging;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat$$ExternalSyntheticApiModelOutline9;
import androidx.media3.common.C;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ly.count.android.sdk.Countly;
import ly.count.android.sdk.CountlyStore;
import ly.count.android.sdk.ModuleLog;
import ly.count.android.sdk.Utils;

/* JADX INFO: loaded from: classes6.dex */
public class CountlyPush {
    public static final String ALLOWED_CLASS_NAMES = "allowed_class_names";
    public static final String ALLOWED_PACKAGE_NAMES = "allowed_package_names";
    public static final String CHANNEL_ID = "ly.count.android.sdk.CountlyPush.CHANNEL_ID";
    public static final String COUNTLY_BROADCAST_PERMISSION_POSTFIX = ".CountlyPush.BROADCAST_PERMISSION";
    public static final String EXTRA_ACTION_INDEX = "ly.count.android.sdk.CountlyPush.Action";
    public static final String EXTRA_INTENT = "ly.count.android.sdk.CountlyPush.intent";
    public static final String EXTRA_MESSAGE = "ly.count.android.sdk.CountlyPush.message";
    static final String FIREBASE_INSTANCEID_CLASS = "com.google.firebase.iid.FirebaseInstanceId";
    static final String FIREBASE_MESSAGING_CLASS = "com.google.firebase.messaging.FirebaseMessaging";
    static final String HUAWEI_CONFIG_CLASS = "com.huawei.agconnect.config.AGConnectServicesConfig";
    static final String HUAWEI_INSTANCEID_CLASS = "com.huawei.hms.aaid.HmsInstanceId";
    static final String HUAWEI_MESSAGING_CLASS = "com.huawei.hms.push.HmsMessageService";
    static int MEDIA_DOWNLOAD_ATTEMPTS = 3;
    static int MEDIA_DOWNLOAD_TIMEOUT = 15000;
    public static final String SECURE_NOTIFICATION_BROADCAST = "ly.count.android.sdk.CountlyPush.SECURE_NOTIFICATION_BROADCAST";
    private static Activity activity = null;
    private static Application.ActivityLifecycleCallbacks callbacks = null;
    private static CountlyConfigPush countlyConfigPush = null;
    static boolean initFinished = false;
    static Integer notificationAccentColor = null;
    public static boolean useAdditionalIntentRedirectionChecks = false;

    private interface BitmapCallback {
        void call(Bitmap bitmap);
    }

    public interface Button {
        int icon();

        int index();

        Uri link();

        void recordAction(Context context);

        String title();
    }

    public interface Message extends Parcelable {
        Integer badge();

        List<Button> buttons();

        String data(String str);

        Set<String> dataKeys();

        boolean has(String str);

        String id();

        Uri link();

        URL media();

        String message();

        void recordAction(Context context);

        void recordAction(Context context, int i);

        String sound();

        String title();
    }

    public static int getLastMessagingMethod(Context context) {
        return 0;
    }

    public static class ConsentBroadcastReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String token;
            if (CountlyPush.countlyConfigPush.provider == null || !CountlyPush.getPushConsent(context) || (token = CountlyPush.getToken(context, CountlyPush.countlyConfigPush.provider, Countly.sharedInstance().L)) == null || "".equals(token)) {
                return;
            }
            CountlyPush.onTokenRefresh(token, CountlyPush.countlyConfigPush.provider);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getToken(Context context, Countly.CountlyMessagingProvider countlyMessagingProvider, ModuleLog moduleLog) {
        if (countlyMessagingProvider == Countly.CountlyMessagingProvider.FCM) {
            try {
                return (String) UtilsMessaging.reflectiveCall(FIREBASE_INSTANCEID_CLASS, UtilsMessaging.reflectiveCall(FIREBASE_INSTANCEID_CLASS, null, "getInstance", moduleLog, new Object[0]), "getToken", moduleLog, new Object[0]);
            } catch (Throwable th) {
                Countly.sharedInstance().L.e("[CountlyPush, getToken] Couldn't get token for Countly FCM", th);
                return null;
            }
        }
        if (countlyMessagingProvider == Countly.CountlyMessagingProvider.HMS) {
            try {
                Object objReflectiveCallStrict = UtilsMessaging.reflectiveCallStrict(HUAWEI_CONFIG_CLASS, null, "fromContext", moduleLog, context, Context.class);
                if (objReflectiveCallStrict == null) {
                    Countly.sharedInstance().L.e("No Huawei Config");
                    return null;
                }
                Object objReflectiveCall = UtilsMessaging.reflectiveCall(HUAWEI_CONFIG_CLASS, objReflectiveCallStrict, "getString", moduleLog, "client/app_id");
                if (objReflectiveCall != null && !"".equals(objReflectiveCall)) {
                    Object objReflectiveCallStrict2 = UtilsMessaging.reflectiveCallStrict(HUAWEI_INSTANCEID_CLASS, null, "getInstance", moduleLog, context, Context.class);
                    if (objReflectiveCallStrict2 == null) {
                        Countly.sharedInstance().L.e("No Huawei instance id class");
                        return null;
                    }
                    return (String) UtilsMessaging.reflectiveCall(HUAWEI_INSTANCEID_CLASS, objReflectiveCallStrict2, "getToken", moduleLog, objReflectiveCall, "HCM");
                }
                Countly.sharedInstance().L.e("No Huawei app id in config");
                return null;
            } catch (Throwable th2) {
                Countly.sharedInstance().L.e("[CountlyPush, getToken] Couldn't get token for Countly huawei push kit", th2);
                return null;
            }
        }
        Countly.sharedInstance().L.e("[CountlyPush, getToken] Message provider is neither FCM or HMS, aborting");
        return null;
    }

    public static Boolean displayMessage(Context context, Map<String, String> map, int i, Intent intent) {
        return displayMessage(context, decodeMessage(map), i, intent);
    }

    public static Boolean displayMessage(Context context, Message message, int i, Intent intent) {
        Countly.sharedInstance().L.d("[CountlyPush, displayMessage] Displaying push message");
        if (context == null) {
            Countly.sharedInstance().L.e("[CountlyPush, displayMessage] Provided context was null, execution will stop");
            return false;
        }
        if (!initFinished) {
            Countly.sharedInstance().L.w("[CountlyPush, displayDialog] Push init has not been completed. Some things might not function.");
        }
        if (message == null || message.message() == null) {
            return null;
        }
        if (isAppRunningInForeground(context)) {
            Activity activity2 = activity;
            if (activity2 != null) {
                return displayDialog(activity2, message);
            }
            return displayNotification(context, message, i, intent);
        }
        return displayNotification(context, message, i, intent);
    }

    public static Boolean displayNotification(Context context, final Message message, int i, Intent intent) {
        Set hashSet;
        Set hashSet2;
        Notification.Builder builder;
        Integer num;
        if (context == null) {
            Countly.sharedInstance().L.e("[CountlyPush, displayNotification] Provided context was null, execution will stop");
            return false;
        }
        if (!getPushConsent(context)) {
            return null;
        }
        if (message == null) {
            Countly.sharedInstance().L.w("[CountlyPush, displayNotification] Message is 'null', can't display a notification");
            return null;
        }
        if (message.title() == null && message.message() == null) {
            Countly.sharedInstance().L.w("[CountlyPush, displayNotification] Message title and message body is 'null', can't display a notification");
            return null;
        }
        ModuleLog moduleLog = Countly.sharedInstance().L;
        StringBuilder sb = new StringBuilder("[CountlyPush, displayNotification] Displaying push notification, additional intent provided:[");
        sb.append(intent != null);
        sb.append("]");
        moduleLog.d(sb.toString());
        if (!initFinished) {
            Countly.sharedInstance().L.w("[CountlyPush, displayDialog] Push init has not been completed. Some things might not function.");
            hashSet = new HashSet();
            hashSet2 = new HashSet();
        } else {
            hashSet = countlyConfigPush.allowedIntentClassNames;
            hashSet2 = countlyConfigPush.allowedIntentPackageNames;
        }
        Set set = hashSet;
        Set set2 = hashSet2;
        final NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager == null) {
            Countly.sharedInstance().L.w("[CountlyPush, displayNotification] Retrieved notification manager is 'null', can't display notification");
            return Boolean.FALSE;
        }
        Intent intentCreatePushActivityIntent = createPushActivityIntent(context, message, intent, 0, set, set2);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationCompat$$ExternalSyntheticApiModelOutline9.m$3();
            builder = NotificationCompat$$ExternalSyntheticApiModelOutline9.m(context.getApplicationContext(), CHANNEL_ID);
        } else {
            builder = new Notification.Builder(context.getApplicationContext());
        }
        final Notification.Builder contentText = builder.setAutoCancel(true).setSmallIcon(i).setTicker(message.message()).setContentTitle(message.title()).setContentText(message.message());
        if (message.badge() != null) {
            contentText.setNumber(message.badge().intValue());
        }
        if (Build.VERSION.SDK_INT > 21 && (num = notificationAccentColor) != null) {
            contentText.setColor(num.intValue());
        }
        contentText.setAutoCancel(true).setContentIntent(PendingIntent.getActivity(context, message.hashCode(), intentCreatePushActivityIntent, Build.VERSION.SDK_INT >= 23 ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 0));
        contentText.setStyle(new Notification.BigTextStyle().bigText(message.message()).setBigContentTitle(message.title()));
        int i2 = 0;
        while (i2 < message.buttons().size()) {
            Button button = message.buttons().get(i2);
            int i3 = i2 + 1;
            contentText.addAction(button.icon(), button.title(), PendingIntent.getActivity(context, message.hashCode() + i2 + 1, createPushActivityIntent(context, message, intent, i3, set, set2), Build.VERSION.SDK_INT >= 23 ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 0));
            i2 = i3;
        }
        if (message.sound() != null) {
            if (message.sound().equals("default")) {
                contentText.setDefaults(1);
            } else {
                contentText.setSound(Uri.parse(message.sound()));
            }
        }
        if (message.media() != null) {
            loadImage(context, message, new BitmapCallback() { // from class: ly.count.android.sdk.messaging.CountlyPush.1
                @Override // ly.count.android.sdk.messaging.CountlyPush.BitmapCallback
                public void call(Bitmap bitmap) {
                    if (bitmap != null) {
                        contentText.setStyle(new Notification.BigPictureStyle().bigPicture(bitmap).setBigContentTitle(message.title()).setSummaryText(message.message()));
                    }
                    notificationManager.notify(message.hashCode(), contentText.build());
                }
            }, 1);
        } else {
            notificationManager.notify(message.hashCode(), contentText.build());
        }
        return Boolean.TRUE;
    }

    private static Intent createPushActivityIntent(Context context, Message message, Intent intent, int i, Set<String> set, Set<String> set2) {
        Intent intentAddFlags = new Intent(context.getApplicationContext(), (Class<?>) CountlyPushActivity.class).addFlags(268435456);
        intentAddFlags.setPackage(context.getApplicationContext().getPackageName());
        intentAddFlags.putExtra(EXTRA_INTENT, actionIntent(context, intent, message, i));
        intentAddFlags.putStringArrayListExtra(ALLOWED_CLASS_NAMES, new ArrayList<>(set));
        intentAddFlags.putStringArrayListExtra(ALLOWED_PACKAGE_NAMES, new ArrayList<>(set2));
        return intentAddFlags;
    }

    private static Intent actionIntent(Context context, Intent intent, Message message, int i) {
        Intent launchIntentForPackage;
        if (intent == null) {
            launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        } else {
            launchIntentForPackage = (Intent) intent.clone();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_MESSAGE, message);
        launchIntentForPackage.putExtra(EXTRA_MESSAGE, bundle);
        launchIntentForPackage.putExtra(EXTRA_ACTION_INDEX, i);
        return launchIntentForPackage;
    }

    public static Boolean displayDialog(final Activity activity2, final Message message) {
        if (activity2 == null) {
            Countly.sharedInstance().L.e("[CountlyPush, displayDialog] Provided Activity was null, execution will stop");
            return false;
        }
        if (!getPushConsent(activity2)) {
            return null;
        }
        Countly.sharedInstance().L.d("[CountlyPush, displayDialog] Displaying push dialog");
        if (!initFinished) {
            Countly.sharedInstance().L.w("[CountlyPush, displayDialog] Push init has not been completed. Some things might not function.");
        }
        loadImage(activity2, message, new BitmapCallback() { // from class: ly.count.android.sdk.messaging.CountlyPush.2
            @Override // ly.count.android.sdk.messaging.CountlyPush.BitmapCallback
            public void call(Bitmap bitmap) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity2);
                Message message2 = message;
                if (message2 != null && message2.media() != null) {
                    CountlyPush.addButtons(activity2, builder, message);
                    LinearLayout linearLayout = new LinearLayout(activity2);
                    linearLayout.setBackgroundColor(0);
                    linearLayout.setOrientation(1);
                    int i = (int) ((activity2.getResources().getDisplayMetrics().density * 10.0f) + 0.5f);
                    if (message.title() != null) {
                        TextView textView = new TextView(activity2);
                        textView.setText(message.title());
                        textView.setPadding(i, i, i, i);
                        textView.setTypeface(null, 1);
                        textView.setGravity(17);
                        linearLayout.addView(textView);
                    }
                    if (bitmap != null) {
                        ImageView imageView = new ImageView(activity2);
                        imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 48));
                        if (message.media() != null) {
                            imageView.setImageBitmap(bitmap);
                        }
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imageView.setAdjustViewBounds(true);
                        imageView.setPadding(i, i, i, i);
                        linearLayout.addView(imageView);
                    }
                    if (message.message() != null) {
                        TextView textView2 = new TextView(activity2);
                        textView2.setText(message.message());
                        textView2.setPadding(i, i, i, i);
                        linearLayout.addView(textView2);
                    }
                    builder.setView(linearLayout);
                } else {
                    Message message3 = message;
                    if (message3 != null && message3.link() != null) {
                        if (message.title() != null) {
                            builder.setTitle(message.title());
                        }
                        if (message.message() != null) {
                            builder.setMessage(message.message());
                        }
                        if (message.buttons().size() > 0) {
                            CountlyPush.addButtons(activity2, builder, message);
                        } else {
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: ly.count.android.sdk.messaging.CountlyPush.2.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int i2) {
                                    message.recordAction(activity2, 0);
                                    dialogInterface.dismiss();
                                    try {
                                        Intent intent = new Intent("android.intent.action.VIEW", message.link());
                                        intent.setFlags(268435456);
                                        intent.putExtra(CountlyPush.EXTRA_ACTION_INDEX, 0);
                                        activity2.startActivity(intent);
                                    } catch (Exception e) {
                                        Countly.sharedInstance().L.e("[CountlyPush, displayDialog] Encountered issue while clicking 'ok' button in dialog [" + e.toString() + "]");
                                    }
                                }
                            });
                        }
                    } else if (message.message() != null) {
                        if (message.buttons().size() > 0) {
                            CountlyPush.addButtons(activity2, builder, message);
                        } else {
                            message.recordAction(activity2);
                        }
                        builder.setTitle(message.title());
                        builder.setMessage(message.message());
                        builder.setCancelable(true);
                        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: ly.count.android.sdk.messaging.CountlyPush.2.2
                            @Override // android.content.DialogInterface.OnCancelListener
                            public void onCancel(DialogInterface dialogInterface) {
                                dialogInterface.dismiss();
                            }
                        });
                    } else {
                        throw new IllegalStateException("Countly Message with UNKNOWN type in ProxyActivity");
                    }
                }
                builder.create().show();
            }
        }, 1);
        return Boolean.TRUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addButtons(final Context context, AlertDialog.Builder builder, final Message message) {
        if (message.buttons().size() > 0) {
            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: ly.count.android.sdk.messaging.CountlyPush.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        int i2 = 2;
                        message.recordAction(context, i == -1 ? 2 : 1);
                        Intent intent = new Intent("android.intent.action.VIEW", message.buttons().get(i == -1 ? 1 : 0).link());
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(CountlyPush.EXTRA_MESSAGE, message);
                        intent.putExtra(CountlyPush.EXTRA_MESSAGE, bundle);
                        if (i != -1) {
                            i2 = 1;
                        }
                        intent.putExtra(CountlyPush.EXTRA_ACTION_INDEX, i2);
                        context.startActivity(intent);
                    } catch (Exception e) {
                        Countly.sharedInstance().L.e("[CountlyPush, dialog button onClick] Encountered issue while clicking on button #[" + i + "] [" + e.toString() + "]");
                    }
                    dialogInterface.dismiss();
                }
            };
            builder.setNeutralButton(message.buttons().get(0).title(), onClickListener);
            if (message.buttons().size() > 1) {
                builder.setPositiveButton(message.buttons().get(1).title(), onClickListener);
            }
        }
    }

    public static Message decodeMessage(Map<String, String> map) {
        ModulePush.MessageImpl messageImpl = new ModulePush.MessageImpl(map);
        if (messageImpl.id == null) {
            return null;
        }
        return messageImpl;
    }

    public static void onTokenRefresh(String str) {
        onTokenRefresh(str, Countly.CountlyMessagingProvider.FCM);
    }

    public static void onTokenRefresh(String str, Countly.CountlyMessagingProvider countlyMessagingProvider) {
        if (!initFinished) {
            Countly.sharedInstance().L.i("[CountlyPush, onTokenRefresh] CountlyPush is not initialized, ignoring call");
            return;
        }
        if (!Countly.sharedInstance().isInitialized()) {
            Countly.sharedInstance().L.i("[CountlyPush, onTokenRefresh] SDK is not initialized, ignoring call");
            return;
        }
        if (!getPushConsent(null)) {
            Countly.sharedInstance().L.i("[CountlyPush, onTokenRefresh] Consent not given, ignoring call");
            return;
        }
        Countly.sharedInstance().L.i("[CountlyPush, onTokenRefresh] Refreshing FCM push token, with for [" + countlyMessagingProvider + "]");
        Countly.sharedInstance().onRegistrationId(str, countlyMessagingProvider);
    }

    public static void init(Application application, Countly.CountlyMessagingMode countlyMessagingMode) throws IllegalStateException {
        init(application, countlyMessagingMode, null);
    }

    public static void init(Application application, Countly.CountlyMessagingMode countlyMessagingMode, Countly.CountlyMessagingProvider countlyMessagingProvider) throws IllegalStateException {
        init(new CountlyConfigPush(application).setProvider(countlyMessagingProvider));
    }

    public static void init(final CountlyConfigPush countlyConfigPush2) throws IllegalStateException {
        if (countlyConfigPush2 == null) {
            Countly.sharedInstance().L.e("[CountlyPush, init] Can't initialize Countly Push, provided 'CountlyConfigPush' was null");
            return;
        }
        ModuleLog moduleLog = Countly.sharedInstance().L;
        StringBuilder sb = new StringBuilder("[CountlyPush, init] Initializing Countly Push, App:[");
        sb.append(countlyConfigPush2.application != null);
        sb.append("], provider:[");
        sb.append(countlyConfigPush2.provider);
        sb.append("]");
        moduleLog.i(sb.toString());
        if (countlyConfigPush2.application == null) {
            throw new IllegalStateException("Non 'null' application must be provided!");
        }
        countlyConfigPush = countlyConfigPush2;
        if (countlyConfigPush2.provider == null) {
            if (UtilsMessaging.reflectiveClassExists(FIREBASE_MESSAGING_CLASS, Countly.sharedInstance().L)) {
                countlyConfigPush2.setProvider(Countly.CountlyMessagingProvider.FCM);
            } else if (UtilsMessaging.reflectiveClassExists(HUAWEI_MESSAGING_CLASS, Countly.sharedInstance().L)) {
                countlyConfigPush2.setProvider(Countly.CountlyMessagingProvider.HMS);
            }
        } else if (countlyConfigPush2.provider == Countly.CountlyMessagingProvider.FCM && !UtilsMessaging.reflectiveClassExists(FIREBASE_MESSAGING_CLASS, Countly.sharedInstance().L)) {
            countlyConfigPush2.setProvider(Countly.CountlyMessagingProvider.HMS);
        } else if (countlyConfigPush2.provider == Countly.CountlyMessagingProvider.HMS && !UtilsMessaging.reflectiveClassExists(HUAWEI_MESSAGING_CLASS, Countly.sharedInstance().L)) {
            countlyConfigPush2.setProvider(Countly.CountlyMessagingProvider.FCM);
        }
        if (countlyConfigPush2.provider == Countly.CountlyMessagingProvider.FCM && !UtilsMessaging.reflectiveClassExists(FIREBASE_MESSAGING_CLASS, Countly.sharedInstance().L)) {
            Countly.sharedInstance().L.e("Countly push didn't initialize. No FirebaseMessaging class in the class path. Please either add it to your gradle config or don't use CountlyPush.");
            return;
        }
        if (countlyConfigPush2.provider == Countly.CountlyMessagingProvider.HMS && !UtilsMessaging.reflectiveClassExists(HUAWEI_MESSAGING_CLASS, Countly.sharedInstance().L)) {
            Countly.sharedInstance().L.e("Countly push didn't initialize. No HmsMessageService class in the class path. Please either add it to your gradle config or don't use CountlyPush.");
            return;
        }
        if (countlyConfigPush2.provider == null) {
            Countly.sharedInstance().L.e("Countly push didn't initialize. Neither FirebaseMessaging, nor HmsMessageService class in the class path. Please either add Firebase / Huawei dependencies or don't use CountlyPush.");
            return;
        }
        CountlyStore.storeMessagingProvider(countlyConfigPush2.provider == Countly.CountlyMessagingProvider.FCM ? 1 : 2, countlyConfigPush2.application);
        if (callbacks == null) {
            callbacks = new Application.ActivityLifecycleCallbacks() { // from class: ly.count.android.sdk.messaging.CountlyPush.4
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity2, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity2) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity2) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity2) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity2, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity2) {
                    Activity unused = CountlyPush.activity = activity2;
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity2) {
                    if (activity2.equals(CountlyPush.activity)) {
                        Activity unused = CountlyPush.activity = null;
                    }
                }
            };
            countlyConfigPush2.application.registerActivityLifecycleCallbacks(callbacks);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Countly.CONSENT_BROADCAST);
            ConsentBroadcastReceiver consentBroadcastReceiver = new ConsentBroadcastReceiver();
            countlyConfigPush2.application.registerReceiver(consentBroadcastReceiver, intentFilter, countlyConfigPush2.application.getPackageName() + COUNTLY_BROADCAST_PERMISSION_POSTFIX, null);
        }
        if (countlyConfigPush2.provider == Countly.CountlyMessagingProvider.HMS && getPushConsent(countlyConfigPush2.application) && getEMUIVersion().startsWith("10")) {
            new Thread(new Runnable() { // from class: ly.count.android.sdk.messaging.CountlyPush.5
                @Override // java.lang.Runnable
                public void run() {
                    String token = CountlyPush.getToken(countlyConfigPush2.application, Countly.CountlyMessagingProvider.HMS, Countly.sharedInstance().L);
                    if (token == null || "".equals(token)) {
                        return;
                    }
                    CountlyPush.onTokenRefresh(token, Countly.CountlyMessagingProvider.HMS);
                }
            }).start();
        }
        initFinished = true;
    }

    static boolean getPushConsent(Context context) {
        if (Countly.sharedInstance().isInitialized() || context == null) {
            return Countly.sharedInstance().consent().getConsent(Countly.CountlyFeatureNames.push);
        }
        return CountlyStore.getConsentPushNoInit(context).booleanValue();
    }

    private static String getEMUIVersion() {
        try {
            String str = Build.DISPLAY;
            int iIndexOf = str.indexOf(" ");
            int iIndexOf2 = str.indexOf("(");
            if (iIndexOf2 != -1) {
                return str.substring(iIndexOf, iIndexOf2).trim();
            }
            return str.substring(iIndexOf).trim();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void setNotificationAccentColor(int i, int i2, int i3, int i4) {
        int iMin = Math.min(255, Math.max(0, i));
        int iMin2 = Math.min(255, Math.max(0, i2));
        int iMin3 = Math.min(255, Math.max(0, i3));
        int iMin4 = Math.min(255, Math.max(0, i4));
        Countly.sharedInstance().L.d("[CountlyPush] Calling [setNotificationAccentColor], [" + iMin + "][" + iMin2 + "][" + iMin3 + "][" + iMin4 + "]");
        notificationAccentColor = Integer.valueOf(Color.argb(iMin, iMin2, iMin3, iMin4));
    }

    private static boolean isAppRunningInForeground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            Countly.sharedInstance().L.d("[CountlyPush] Checking if app in foreground, NO");
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                Countly.sharedInstance().L.d("[CountlyPush] Checking if app in foreground, YES");
                return true;
            }
        }
        Countly.sharedInstance().L.d("[CountlyPush] Checking if app in foreground, NO");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loadImage(final Context context, final Message message, final BitmapCallback bitmapCallback, final int i) {
        Utils.runInBackground(new Runnable() { // from class: ly.count.android.sdk.messaging.CountlyPush.6
            /* JADX WARN: Code duplicated, block: B:60:0x00a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:62:0x005a A[EXC_TOP_SPLITTER, PHI: r4
              0x005a: PHI (r4v7 java.net.HttpURLConnection) = (r4v6 java.net.HttpURLConnection), (r4v11 java.net.HttpURLConnection) binds: [B:37:0x0096, B:14:0x0058] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:65:0x009c A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:74:? A[SYNTHETIC] */
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                HttpURLConnection httpURLConnection;
                InputStream inputStream = null;
                final Bitmap[] bitmapArr = {null};
                if (message.media() != null) {
                    try {
                        httpURLConnection = (HttpURLConnection) message.media().openConnection();
                        try {
                            try {
                                httpURLConnection.setDoInput(true);
                                httpURLConnection.setConnectTimeout(CountlyPush.MEDIA_DOWNLOAD_TIMEOUT);
                                httpURLConnection.setReadTimeout(CountlyPush.MEDIA_DOWNLOAD_TIMEOUT);
                                httpURLConnection.connect();
                                inputStream = httpURLConnection.getInputStream();
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                byte[] bArr = new byte[16384];
                                while (true) {
                                    int i2 = inputStream.read(bArr, 0, 16384);
                                    if (i2 == -1) {
                                        break;
                                    } else {
                                        byteArrayOutputStream.write(bArr, 0, i2);
                                    }
                                }
                                byteArrayOutputStream.flush();
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                bitmapArr[0] = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException unused) {
                                    }
                                }
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Throwable unused2) {
                                    }
                                }
                            } catch (Exception e) {
                                e = e;
                                Countly.sharedInstance().L.e("[CountlyPush] loadImage, Cannot download message media ", e);
                                if (i < CountlyPush.MEDIA_DOWNLOAD_ATTEMPTS) {
                                    CountlyPush.loadImage(context, message, bitmapCallback, i + 1);
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException unused3) {
                                        }
                                    }
                                    if (httpURLConnection != null) {
                                        try {
                                            httpURLConnection.disconnect();
                                            return;
                                        } catch (Throwable unused4) {
                                            return;
                                        }
                                    }
                                    return;
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException unused5) {
                                    }
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException unused6) {
                                }
                            }
                            if (httpURLConnection != null) {
                                try {
                                    httpURLConnection.disconnect();
                                    throw th;
                                } catch (Throwable unused7) {
                                    throw th;
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        httpURLConnection = null;
                    } catch (Throwable th2) {
                        th = th2;
                        httpURLConnection = null;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                            throw th;
                        }
                        throw th;
                    }
                }
                new Handler(context.getMainLooper()).post(new Runnable() { // from class: ly.count.android.sdk.messaging.CountlyPush.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        bitmapCallback.call(bitmapArr[0]);
                    }
                });
            }
        });
    }
}

package com.movieboxpro.android.app;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.LocaleList;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;
import androidx.room.Room;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.hjq.toast.Toaster;
import com.jeffmony.downloader.VideoDownloadManager;
import com.kingja.loadsir.core.LoadSir;
import com.movieboxpro.android.cache.ACache;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.config.OnlineConfigAgent;
import com.movieboxpro.android.db.Migrations;
import com.movieboxpro.android.db.ReaderDB;
import com.movieboxpro.android.event.ConfigReceiveEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.CipherKeys;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.v2.ApiV2EndpointConfig;
import com.movieboxpro.android.http.v2.session.AccountIdentityWrite;
import com.movieboxpro.android.http.v2.session.AccountSessionLock;
import com.movieboxpro.android.http.v2.session.ApiSessionManager;
import com.movieboxpro.android.http.v2.session.LoginCommitResult;
import com.movieboxpro.android.model.user.OldUserModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CipherUtils;
import com.movieboxpro.android.utils.FileUtil;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideMemCacheClearUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.Utils;
import com.movieboxpro.android.view.widget.loadcallback.EmptyCallbackView;
import com.movieboxpro.android.view.widget.loadcallback.ErrorCallbackView;
import com.movieboxpro.android.view.widget.loadcallback.LoadingCallbackView;
import com.movieboxpro.androidtv.BuildConfig;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Observer;
import ly.count.android.sdk.Countly;
import ly.count.android.sdk.CountlyConfig;
import org.greenrobot.eventbus.EventBus;
import org.videolan.libvlc.FactoryManager;
import org.videolan.libvlc.LibVLCFactory;
import org.videolan.libvlc.interfaces.ILibVLCFactory;

/* JADX INFO: loaded from: classes5.dex */
public class App extends MultiDexApplication implements Application.ActivityLifecycleCallbacks, OnlineConfigAgent.OnlineConfigListener, ComponentCallbacks2 {
    public static int DEBUG_LEVEL = 0;
    private static final String TAG = "App";
    private static int activityCount = 0;
    public static String channel = null;
    private static ReaderDB db = null;
    public static String deviceLang = null;
    public static String deviceToken = "";
    private static boolean hasExitCall = false;
    private static App instance = null;
    public static boolean isDebug = false;
    private static boolean isLogin = false;
    private static ACache mCache = null;
    private static AppObservable observable = null;
    public static String osType = "android";
    public static String packageName = null;
    public static boolean tempCL = false;
    private static UserModel user = null;
    public static int versionCode = 1;
    public static String versionName;

    private void configSpash() {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    private static class MkDirTask extends AsyncTask<String, Integer, Boolean> {
        private MkDirTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(String... strings) {
            if (strings != null && strings.length > 0) {
                for (String str : strings) {
                    try {
                        FileUtils.forceMkdir(new File(str));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        File file;
        super.onCreate();
        tempCL = true;
        Toaster.init(this);
        try {
            ProviderInstaller.installIfNeeded(this);
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e2) {
            e2.printStackTrace();
        }
        configLog();
        MLog.d(TAG, "onCreate");
        registerActivityLifecycleCallbacks(this);
        instance = this;
        mCache = ACache.get(this);
        osType = SystemUtils.getOSType();
        String processName = SystemUtils.getProcessName(this);
        packageName = getPackageName();
        MLog.d(TAG, "package:" + packageName + ",process:" + processName);
        if (packageName.equals(processName)) {
            getVersions();
            mkDirs();
            initPrefs();
            autoLogin();
            Http.refreshApiSessionIfExpiringAsync();
            initDB();
            configJarvis();
            configTokne();
            configSpash();
            GlideMemCacheClearUtils.startMonitorLowMemory();
            FactoryManager.registerFactory(ILibVLCFactory.factoryId, new LibVLCFactory());
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.DARK_MODE, false)) {
                AppCompatDelegate.setDefaultNightMode(2);
            } else {
                AppCompatDelegate.setDefaultNightMode(1);
            }
        }
        initLoadSir();
        Utils.init((Application) this);
        CountlyConfig countlyConfig = new CountlyConfig((Application) this, "35ccd9f4ab7fa68137943698dfa28bd930450bbe", "https://app.febapi.com");
        countlyConfig.setLoggingEnabled(false);
        countlyConfig.enableCrashReporting();
        countlyConfig.enableAutomaticViewTracking();
        Countly.sharedInstance().init(countlyConfig);
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, "");
        if (TextUtils.isEmpty(string)) {
            file = new File(Constant.DIR, Constant.Prefs.HLS_CACHE_DIR);
        } else {
            file = new File(string, Constant.Prefs.HLS_CACHE_DIR);
        }
        if (!file.exists()) {
            file.mkdir();
        }
        VideoDownloadManager.getInstance().initConfig(new VideoDownloadManager.Build(this).setCacheRoot(file.getPath()).setTimeOut(120000, 120000).setConcurrentCount(3).setIgnoreCertErrors(true).setShouldM3U8Merged(false).buildConfig());
    }

    private void initLoadSir() {
        LoadSir.beginBuilder().addCallback(new ErrorCallbackView()).addCallback(new EmptyCallbackView()).addCallback(new LoadingCallbackView()).setDefaultCallback(LoadingCallbackView.class).commit();
    }

    private void configLog() {
        MLog.config(isDebug);
    }

    private void getVersions() {
        channel = BuildConfig.CHANNEL;
        versionName = SystemUtils.getVersionName(this);
        versionCode = SystemUtils.getVersionCode(this);
    }

    private void mkDirs() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            new MkDirTask().execute(Constant.DIRS);
        }
    }

    private void autoLogin() {
        MLog.d(TAG, "autoLogin");
        ACache old = ACache.getOld(this);
        OldUserModel oldUserModel = (OldUserModel) old.getAsObject("user");
        if (oldUserModel != null) {
            MLog.d(TAG, "autoLogin: old cache");
            if (login((UserModel) JSONObject.parseObject(JSONObject.toJSONString(oldUserModel)).toJavaObject(UserModel.class))) {
                old.remove("user");
                ACache.clearOldCache(this);
                return;
            }
            return;
        }
        ACache.clearOldCache(this);
        File userFile = getUserFile();
        UserModel userModel = (userFile.exists() && userFile.isFile()) ? (UserModel) JSONObject.toJavaObject(JSONObject.parseObject(CipherUtils.decrypt(FileUtil.getFileOutputString(userFile.getAbsolutePath()), CipherKeys.getDefaultKeys())), UserModel.class) : null;
        if (userModel != null && userModel.member != null) {
            restoreLogin(userModel);
        } else {
            logout();
        }
    }

    private void initPrefs() {
        PrefsUtils.init(getContext(), Constant.Prefs.SETTING, 4);
        if (!PrefsUtils.getInstance().getBoolean("app_48", false)) {
            PrefsUtils.getInstance().putBoolean("app_48", true);
            PrefsUtils.getInstance().remove(Constant.Prefs.SHOW_READ_TIPS);
        }
        String string = PrefsUtils.getInstance().getString("language", "");
        if (!TextUtils.isEmpty(string)) {
            deviceLang = string;
        } else {
            initLanguage();
        }
    }

    private void initLanguage() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= 24) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        if (locale != null) {
            String language = locale.getLanguage();
            String country = locale.getCountry();
            if ("zh".equalsIgnoreCase(language)) {
                if ("CN".equalsIgnoreCase(country)) {
                    deviceLang = "zh-CN";
                    return;
                } else {
                    deviceLang = "zh-TW";
                    return;
                }
            }
            deviceLang = language;
            return;
        }
        deviceLang = "en";
    }

    private static void initDB() {
        db = (ReaderDB) Room.databaseBuilder(getContext(), ReaderDB.class, "movie_box.db").addMigrations(Migrations.migration1_2, Migrations.migration4_5, Migrations.migration5_6, Migrations.migration6_8, Migrations.migration8_9, Migrations.migration9_10).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    private void configJarvis() {
        MLog.d(TAG, "configJarvis");
    }

    private void configTokne() {
        MLog.d(TAG, "configTokne" + (Settings.Secure.getString(getContentResolver(), "android_id") + Build.SERIAL));
    }

    public static App getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    public static ReaderDB getDB() {
        ReaderDB readerDB = db;
        if (readerDB == null || !readerDB.isOpen()) {
            initDB();
        }
        return db;
    }

    public static ACache getCache() {
        if (mCache == null) {
            mCache = ACache.get(getContext());
        }
        return mCache;
    }

    public static boolean isLogin() {
        UserModel userModel;
        return (!isLogin || (userModel = user) == null || userModel.member == null) ? false : true;
    }

    public static UserModel.UserData getUserData() {
        if (isLogin()) {
            return user.member;
        }
        return new UserModel.UserData();
    }

    public static boolean login(UserModel model) {
        return loginResult(model) instanceof LoginCommitResult.Success;
    }

    public static LoginCommitResult loginResult(final UserModel model) {
        LoginCommitResult loginCommitResultCommitLogin;
        ApiSessionManager apiSessionManager = Http.getApiSessionManager();
        synchronized (AccountSessionLock.monitor) {
            final UserModel userModel = user;
            loginCommitResultCommitLogin = apiSessionManager.commitLogin(model.member, new AccountIdentityWrite() { // from class: com.movieboxpro.android.app.App$$ExternalSyntheticLambda3
                @Override // com.movieboxpro.android.http.v2.session.AccountIdentityWrite
                public final boolean run() {
                    return App.saveUser(model);
                }
            }, new AccountIdentityWrite() { // from class: com.movieboxpro.android.app.App$$ExternalSyntheticLambda4
                @Override // com.movieboxpro.android.http.v2.session.AccountIdentityWrite
                public final boolean run() {
                    return App.restoreUserFile(userModel);
                }
            }, new Runnable() { // from class: com.movieboxpro.android.app.App$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    App.lambda$loginResult$2(model);
                }
            });
        }
        if (loginCommitResultCommitLogin instanceof LoginCommitResult.Success) {
            onLoginCommitted(model);
            return loginCommitResultCommitLogin;
        }
        if (loginCommitResultCommitLogin instanceof LoginCommitResult.Failure) {
            MLog.e(TAG, "login failed: " + ((LoginCommitResult.Failure) loginCommitResultCommitLogin).getReason());
        }
        return loginCommitResultCommitLogin;
    }

    static /* synthetic */ void lambda$loginResult$2(UserModel userModel) {
        user = userModel;
        isLogin = true;
    }

    private static void restoreLogin(final UserModel model) {
        if (!Http.getApiSessionManager().hasPersistedSession()) {
            MLog.d(TAG, "restoreLogin: upgrade_no_session");
            logout();
        } else {
            Http.getApiSessionManager().restoreLogin(new Runnable() { // from class: com.movieboxpro.android.app.App$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    App.lambda$restoreLogin$3(model);
                }
            });
            onLoginCommitted(model);
        }
    }

    static /* synthetic */ void lambda$restoreLogin$3(UserModel userModel) {
        user = userModel;
        isLogin = true;
    }

    private static void onLoginCommitted(UserModel model) {
        Http.getLoginInvalidHandler().reset();
        FirebaseCrashlytics.getInstance().setUserId(model.member.getUid_v2());
        FirebaseCrashlytics.getInstance().setCustomKey("VERSION_NAME", BuildConfig.VERSION_NAME);
        FirebaseCrashlytics.getInstance().setCustomKey("VERSION_CODE", 126);
        if (observable == null) {
            observable = new AppObservable();
        }
    }

    private static void saveUser() throws Throwable {
        saveUser(user);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean saveUser(UserModel model) throws Throwable {
        try {
            String strEncrypt = CipherUtils.encrypt(JSONObject.toJSONString(model), CipherKeys.getDefaultKeys());
            File userFile = getUserFile();
            FileUtils.write(userFile, strEncrypt, false);
            return userFile.exists() && userFile.isFile() && userFile.length() > 0;
        } catch (RuntimeException unused) {
            MLog.e(TAG, "Failed to persist user identity");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean restoreUserFile(UserModel previousUser) {
        if (previousUser != null) {
            return saveUser(previousUser);
        }
        File userFile = getUserFile();
        return !userFile.exists() || userFile.delete();
    }

    private static File getUserFile() {
        return new File(getContext().getFilesDir(), "user.json");
    }

    public void addObserver(Observer observer) {
        MLog.d(TAG, "addObserver");
        AppObservable appObservable = observable;
        if (appObservable == null || observer == null) {
            return;
        }
        appObservable.addObserver(observer);
    }

    public void delObserver(Observer observer) {
        MLog.d(TAG, "delObserver");
        AppObservable appObservable = observable;
        if (appObservable == null || observer == null) {
            return;
        }
        appObservable.deleteObserver(observer);
    }

    public static void updateUser(UserModel.UserData data) throws Throwable {
        MLog.d(TAG, "updateUser");
        if (isLogin()) {
            data.uid_v2 = user.member.uid_v2;
            if (!TextUtils.isEmpty(user.member.getUid_token()) && TextUtils.isEmpty(data.getUid_token())) {
                data.setUid_token(user.member.getUid_token());
            }
            user.member = data;
            saveUser();
            if (observable != null) {
                MLog.d(TAG, "updateUser: notifyObservers");
                observable.notifyObservers();
                return;
            }
            return;
        }
        logout();
    }

    public static boolean updateUidV2(String value) {
        UserModel userModel;
        synchronized (AccountSessionLock.monitor) {
            if (isLogin && (userModel = user) != null && userModel.member != null) {
                String str = user.member.uid_v2;
                user.member.uid_v2 = value;
                if (saveUser(user)) {
                    return true;
                }
                user.member.uid_v2 = str;
                saveUser(user);
                return false;
            }
            return false;
        }
    }

    public static void updateUser(String avatar) throws Throwable {
        MLog.d(TAG, "updateUser");
        if (isLogin()) {
            user.member.avatar = avatar;
            saveUser();
            if (observable != null) {
                MLog.d(TAG, "updateUser: notifyObservers");
                observable.notifyObservers();
                return;
            }
            return;
        }
        logout();
    }

    public static void updateUser(String email, int i) throws Throwable {
        MLog.d(TAG, "updateUser");
        if (isLogin()) {
            user.member.email = email;
            saveUser();
            if (observable != null) {
                MLog.d(TAG, "updateUser: notifyObservers");
                observable.notifyObservers();
                return;
            }
            return;
        }
        logout();
    }

    public static void logout() {
        synchronized (AccountSessionLock.monitor) {
            Http.getApiSessionManager().clear();
            delUser();
            isLogin = false;
        }
        clearDatas();
        EventBus.getDefault().post(new LogoutEvent());
    }

    private static void delUser() {
        user = null;
        FileUtils.deleteFile(getUserFile());
    }

    private static void clearDatas() {
        AsyncTask.execute(new Runnable() { // from class: com.movieboxpro.android.app.App.1
            @Override // java.lang.Runnable
            public void run() {
                App.getDB().clearPlayRecord();
                GlideUtils.clearImageAllCache(App.getContext());
                for (String str : Constant.DIRS_CLEAR) {
                    FileUtils.deleteFile(new File(str));
                }
                for (String str2 : Constant.DIRS_CLEAR) {
                    try {
                        FileUtils.forceMkdir(new File(str2));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void clearCache() {
        AsyncTask.execute(new Runnable() { // from class: com.movieboxpro.android.app.App.2
            @Override // java.lang.Runnable
            public void run() {
                PrefsUtils.getInstance().removeAll();
                GlideUtils.clearImageAllCache(App.getContext());
            }
        });
    }

    public static void clearMemory() {
        AsyncTask.execute(new Runnable() { // from class: com.movieboxpro.android.app.App.3
            @Override // java.lang.Runnable
            public void run() {
                GlideUtils.clearImageAllCache(App.getContext());
            }
        });
    }

    public void updateOnline() {
        MLog.d(TAG, "updateOnline");
        OnlineConfigAgent.getInstance().setOnlineConfigListener(this);
        OnlineConfigAgent.getInstance().updateOnlineConfig();
    }

    @Override // com.movieboxpro.android.config.OnlineConfigAgent.OnlineConfigListener
    public void onDataReceived(String data) {
        MLog.d(TAG, "onDataReceived: " + data);
        if (!TextUtils.isEmpty(data)) {
            readOnline();
        }
        EventBus.getDefault().post(new ConfigReceiveEvent());
    }

    public void readOnline() {
        MLog.d(TAG, "readOnline");
        if (ApiV2EndpointConfig.updateFromBaseUrl(ConfigUtils.readStringConfig(ConfigKey.API_URL))) {
            MLog.d(TAG, "readOnline: base url changed");
            Http.baseUrlChanged();
        }
    }

    public static void exit() {
        MLog.d(TAG, "exit");
        hasExitCall = true;
        AppManager.finishAll();
        CallManager.cancelAll();
        RxManager.removeAll();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(getContext()).clearMemory();
        MLog.d(TAG, "onLowMemory");
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        MLog.d(TAG, "onTerminate");
        ReaderDB readerDB = db;
        if (readerDB != null) {
            readerDB.close();
            db = null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        activityCount++;
        MLog.d(TAG, "onActivityStarted: " + activityCount + ", " + activity.getClass().getSimpleName());
        if (activityCount <= 1) {
            MLog.d(TAG, "onActivityStarted: app enter from background: " + TimeUtils.getFormatedTime(TimeUtils.DETAULT_PATTERN));
        } else {
            MLog.d(TAG, "onActivityStarted: app jump internal");
            PrefsUtils.getInstance().putLong(Constant.Prefs.ENTER_BACK_TIME, TimeUtils.getCurrentTime());
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        activityCount--;
        MLog.d(TAG, "onActivityStopped: " + activityCount + ", " + activity.getClass().getSimpleName());
        if (activityCount == 0) {
            if (hasExitCall) {
                MLog.d(TAG, "onActivityStopped: app going to finish");
                PrefsUtils.getInstance().putLong(Constant.Prefs.ENTER_BACK_TIME, 0L);
            } else {
                PrefsUtils.getInstance().putLong(Constant.Prefs.ENTER_BACK_TIME, TimeUtils.getCurrentTime());
                MLog.d(TAG, "onActivityStopped: app enter background: " + TimeUtils.getFormatedTime(TimeUtils.DETAULT_PATTERN));
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        if (activityCount == 0) {
            MLog.d(TAG, "onActivityDestroyed: app has finished");
        }
        hasExitCall = false;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == 20) {
            Glide.get(getContext()).clearMemory();
        }
        Glide.get(getContext()).trimMemory(level);
    }
}

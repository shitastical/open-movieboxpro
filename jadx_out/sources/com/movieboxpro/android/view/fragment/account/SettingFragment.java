package com.movieboxpro.android.view.fragment.account;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.location.LocationRequestCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.DialogFragment;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.adorkable.iosdialog.R;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.CommMultiBaseAdapter;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.OpenChildModeEvent;
import com.movieboxpro.android.event.OpenHideMovieListEvent;
import com.movieboxpro.android.livedata.DownloadLanguageChangedLiveData;
import com.movieboxpro.android.livedata.HideDownloadLiveData;
import com.movieboxpro.android.livedata.PrivateModeChangedLiveData;
import com.movieboxpro.android.livedata.SmartDownloadChangedLiveData;
import com.movieboxpro.android.model.ImageHost;
import com.movieboxpro.android.model.SettingModel;
import com.movieboxpro.android.tv.LoginActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.AudioDelaySettingFragment;
import com.movieboxpro.android.view.dialog.ChooseDownloadLanguageDialog;
import com.movieboxpro.android.view.dialog.SmartDownloadSettingDialog;
import com.movieboxpro.android.view.dialog.SwitchLanguageDialog;
import com.movieboxpro.android.view.dialog.TestNetSpeedFragment;
import com.movieboxpro.android.view.widget.UpdateChecker;
import com.movieboxpro.androidtv.databinding.FragmentSettingBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: SettingFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J&\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001a\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00112\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\b\u0010\u001d\u001a\u00020\fH\u0002J!\u0010\u001e\u001a\u00020\f2\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110 \"\u00020\u0011H\u0002¢\u0006\u0002\u0010!J\u0018\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020\fH\u0002J\b\u0010'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/SettingFragment;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "adapter", "Lcom/movieboxpro/android/base/CommMultiBaseAdapter;", "Lcom/movieboxpro/android/model/SettingModel;", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentSettingBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "onLogin", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/LoginEvent;", "lastClickTime", "", "initListener", "setViewFocusListener", "args", "", "([Landroid/view/View;)V", "focusScale", "v", "scale", "", "initData", "getDownloadAudioRule", "", "onDestroy", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SettingFragment extends DialogFragment {
    private CommMultiBaseAdapter<SettingModel> adapter;
    private FragmentSettingBinding binding;
    private long lastClickTime;
    private FocusBorder mFocusBorder;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.DialogStyle);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.7f);
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentSettingBinding fragmentSettingBindingInflate = FragmentSettingBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentSettingBindingInflate, "inflate(...)");
        this.binding = fragmentSettingBindingInflate;
        if (fragmentSettingBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSettingBindingInflate = null;
        }
        return fragmentSettingBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLogin(LoginEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.CHILD_MODE, false);
        FragmentSettingBinding fragmentSettingBinding = null;
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE)) {
            FragmentSettingBinding fragmentSettingBinding2 = this.binding;
            if (fragmentSettingBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentSettingBinding = fragmentSettingBinding2;
            }
            fragmentSettingBinding.tvChildMode.setText("ON");
            return;
        }
        FragmentSettingBinding fragmentSettingBinding3 = this.binding;
        if (fragmentSettingBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentSettingBinding = fragmentSettingBinding3;
        }
        fragmentSettingBinding.tvChildMode.setText("");
    }

    private final void initListener() {
        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter = this.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.account.SettingFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SettingFragment.initListener$lambda$26(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:171:0x0366  */
    public static final void initListener$lambda$26(final SettingFragment settingFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        String str;
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter = settingFragment.adapter;
        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        String str2 = "OFF";
        int i2 = 0;
        switch (commMultiBaseAdapter.getItem(i).getType()) {
            case 1:
                new TestNetSpeedFragment().show(settingFragment.getChildFragmentManager(), "TestNetSpeedFragment");
                break;
            case 2:
                SwitchLanguageDialog switchLanguageDialog = new SwitchLanguageDialog();
                switchLanguageDialog.setListener(new SwitchLanguageDialog.OnLanguageListener() { // from class: com.movieboxpro.android.view.fragment.account.SettingFragment$initListener$1$1
                    @Override // com.movieboxpro.android.view.dialog.SwitchLanguageDialog.OnLanguageListener
                    public void onLanguageSelected() {
                        SettingFragment settingFragment2 = this.this$0;
                        CommMultiBaseAdapter commMultiBaseAdapter3 = settingFragment2.adapter;
                        CommMultiBaseAdapter commMultiBaseAdapter4 = null;
                        if (commMultiBaseAdapter3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            commMultiBaseAdapter3 = null;
                        }
                        int i3 = 0;
                        for (Object obj : commMultiBaseAdapter3.getData()) {
                            int i4 = i3 + 1;
                            if (i3 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            SettingModel settingModel = (SettingModel) obj;
                            if (settingModel.getType() == 2) {
                                settingModel.setStatus(App.deviceLang);
                                CommMultiBaseAdapter commMultiBaseAdapter5 = settingFragment2.adapter;
                                if (commMultiBaseAdapter5 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                                } else {
                                    commMultiBaseAdapter4 = commMultiBaseAdapter5;
                                }
                                commMultiBaseAdapter4.notifyItemChanged(i3);
                                return;
                            }
                            i3 = i4;
                        }
                    }
                });
                switchLanguageDialog.show(settingFragment.getChildFragmentManager(), "SwitchLanguageDialog");
                break;
            case 3:
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE)) {
                    LoginActivity.Companion.start$default(LoginActivity.INSTANCE, settingFragment.getContext(), false, 2, null);
                    settingFragment.dismiss();
                } else if (System.currentTimeMillis() - settingFragment.lastClickTime > ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS) {
                    ToastUtils.showShort("Press again to enter child mode!", new Object[0]);
                    settingFragment.lastClickTime = System.currentTimeMillis();
                } else {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.CHILD_MODE, true);
                    CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter3 = settingFragment.adapter;
                    if (commMultiBaseAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        commMultiBaseAdapter3 = null;
                    }
                    for (Object obj : commMultiBaseAdapter3.getData()) {
                        int i3 = i2 + 1;
                        if (i2 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        SettingModel settingModel = (SettingModel) obj;
                        if (settingModel.getType() == 3) {
                            settingModel.setStatus("ON");
                            CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter4 = settingFragment.adapter;
                            if (commMultiBaseAdapter4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            } else {
                                commMultiBaseAdapter2 = commMultiBaseAdapter4;
                            }
                            commMultiBaseAdapter2.notifyItemChanged(i2);
                            Unit unit = Unit.INSTANCE;
                            EventBus.getDefault().post(new OpenChildModeEvent());
                        } else {
                            i2 = i3;
                        }
                        break;
                    }
                    Unit unit2 = Unit.INSTANCE;
                    EventBus.getDefault().post(new OpenChildModeEvent());
                }
                break;
            case 4:
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_SELECTED_ORG, false)) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.REMEMBER_SELECTED_ORG, false);
                } else {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.REMEMBER_SELECTED_ORG, true);
                    str2 = "ON";
                }
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter5 = settingFragment.adapter;
                if (commMultiBaseAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter5 = null;
                }
                for (Object obj2 : commMultiBaseAdapter5.getData()) {
                    int i4 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SettingModel settingModel2 = (SettingModel) obj2;
                    if (settingModel2.getType() == 4) {
                        settingModel2.setStatus(str2);
                        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter6 = settingFragment.adapter;
                        if (commMultiBaseAdapter6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            commMultiBaseAdapter2 = commMultiBaseAdapter6;
                        }
                        commMultiBaseAdapter2.notifyItemChanged(i2);
                        Unit unit3 = Unit.INSTANCE;
                    } else {
                        i2 = i4;
                    }
                    break;
                }
                Unit unit4 = Unit.INSTANCE;
                break;
            case 5:
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIE_LIST, false)) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.HIDE_MOVIE_LIST, false);
                    EventBus.getDefault().post(new OpenHideMovieListEvent(false));
                } else {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.HIDE_MOVIE_LIST, true);
                    EventBus.getDefault().post(new OpenHideMovieListEvent(true));
                    str2 = "ON";
                }
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter7 = settingFragment.adapter;
                if (commMultiBaseAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter7 = null;
                }
                for (Object obj3 : commMultiBaseAdapter7.getData()) {
                    int i5 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SettingModel settingModel3 = (SettingModel) obj3;
                    if (settingModel3.getType() == 5) {
                        settingModel3.setStatus(str2);
                        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter8 = settingFragment.adapter;
                        if (commMultiBaseAdapter8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            commMultiBaseAdapter2 = commMultiBaseAdapter8;
                        }
                        commMultiBaseAdapter2.notifyItemChanged(i2);
                        Unit unit5 = Unit.INSTANCE;
                    } else {
                        i2 = i5;
                    }
                    break;
                }
                Unit unit6 = Unit.INSTANCE;
                break;
            case 7:
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_PLAY_NEXT, false)) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.AUTO_PLAY_NEXT, false);
                } else {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.AUTO_PLAY_NEXT, true);
                    str2 = "ON";
                }
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter9 = settingFragment.adapter;
                if (commMultiBaseAdapter9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter9 = null;
                }
                for (Object obj4 : commMultiBaseAdapter9.getData()) {
                    int i6 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SettingModel settingModel4 = (SettingModel) obj4;
                    if (settingModel4.getType() == 7) {
                        settingModel4.setStatus(str2);
                        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter10 = settingFragment.adapter;
                        if (commMultiBaseAdapter10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            commMultiBaseAdapter2 = commMultiBaseAdapter10;
                        }
                        commMultiBaseAdapter2.notifyItemChanged(i2);
                        Unit unit7 = Unit.INSTANCE;
                    } else {
                        i2 = i6;
                    }
                    break;
                }
                Unit unit8 = Unit.INSTANCE;
                break;
            case 8:
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, false)) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, false);
                } else {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, true);
                    str2 = "ON";
                }
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter11 = settingFragment.adapter;
                if (commMultiBaseAdapter11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter11 = null;
                }
                for (Object obj5 : commMultiBaseAdapter11.getData()) {
                    int i7 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SettingModel settingModel5 = (SettingModel) obj5;
                    if (settingModel5.getType() == 8) {
                        settingModel5.setStatus(str2);
                        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter12 = settingFragment.adapter;
                        if (commMultiBaseAdapter12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            commMultiBaseAdapter2 = commMultiBaseAdapter12;
                        }
                        commMultiBaseAdapter2.notifyItemChanged(i2);
                        Unit unit9 = Unit.INSTANCE;
                    } else {
                        i2 = i7;
                    }
                    break;
                }
                Unit unit10 = Unit.INSTANCE;
                break;
            case 9:
                String str3 = "auto";
                String string = PrefsUtils.getInstance().getString(Constant.Prefs.PLAYER_ENGINE, "auto");
                if (string != null) {
                    int iHashCode = string.hashCode();
                    if (iHashCode != 100892) {
                        if (iHashCode != 116845) {
                            if (iHashCode == 3005871 && string.equals("auto")) {
                                str = "exo(Support HDR)";
                                str3 = "exo";
                            } else {
                                str = "Auto";
                            }
                        } else if (string.equals("vlc")) {
                            str3 = "ijk";
                            str = "ijk(Better Performance)";
                        } else {
                            str = "Auto";
                        }
                    } else if (string.equals("exo")) {
                        str = "vlc(Support HDR)";
                        str3 = "vlc";
                    } else {
                        str = "Auto";
                    }
                } else {
                    str = "Auto";
                }
                PrefsUtils.getInstance().putString(Constant.Prefs.PLAYER_ENGINE, str3);
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter13 = settingFragment.adapter;
                if (commMultiBaseAdapter13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter13 = null;
                }
                for (Object obj6 : commMultiBaseAdapter13.getData()) {
                    int i8 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SettingModel settingModel6 = (SettingModel) obj6;
                    if (settingModel6.getType() == 9) {
                        settingModel6.setStatus(str);
                        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter14 = settingFragment.adapter;
                        if (commMultiBaseAdapter14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            commMultiBaseAdapter2 = commMultiBaseAdapter14;
                        }
                        commMultiBaseAdapter2.notifyItemChanged(i2);
                        Unit unit11 = Unit.INSTANCE;
                    } else {
                        i2 = i8;
                    }
                    break;
                }
                Unit unit12 = Unit.INSTANCE;
                break;
            case 10:
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_SELECT_SUBTITLE, false)) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.AUTO_SELECT_SUBTITLE, false);
                } else {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.AUTO_SELECT_SUBTITLE, true);
                    str2 = "ON";
                }
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter15 = settingFragment.adapter;
                if (commMultiBaseAdapter15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter15 = null;
                }
                for (Object obj7 : commMultiBaseAdapter15.getData()) {
                    int i9 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SettingModel settingModel7 = (SettingModel) obj7;
                    if (settingModel7.getType() == 10) {
                        settingModel7.setStatus(str2);
                        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter16 = settingFragment.adapter;
                        if (commMultiBaseAdapter16 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            commMultiBaseAdapter2 = commMultiBaseAdapter16;
                        }
                        commMultiBaseAdapter2.notifyItemChanged(i2);
                        Unit unit13 = Unit.INSTANCE;
                    } else {
                        i2 = i9;
                    }
                    break;
                }
                Unit unit14 = Unit.INSTANCE;
                break;
            case 11:
                AudioDelaySettingFragment.INSTANCE.newInstance().show(settingFragment.getChildFragmentManager(), "AudioDelaySettingFragment");
                break;
            case 12:
                new AccountManagerFragment().show(settingFragment.getChildFragmentManager(), "AccountManagerFragment");
                break;
            case 13:
                if (126 < ConfigUtils.readIntegerConfig(ConfigKey.WEB_APK_LATEST_VERSION, App.versionCode)) {
                    Context context = settingFragment.getContext();
                    if (context != null) {
                        UpdateChecker.INSTANCE.checkWebUpdate(context);
                        Unit unit15 = Unit.INSTANCE;
                        Unit unit16 = Unit.INSTANCE;
                    }
                } else {
                    ToastUtils.showShort("MovieBoxPro is up to date.", new Object[0]);
                    Unit unit17 = Unit.INSTANCE;
                }
                break;
            case 14:
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true)) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.SKIP_OPENING_ENDING, false);
                } else {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true);
                    str2 = "ON";
                }
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter17 = settingFragment.adapter;
                if (commMultiBaseAdapter17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter17 = null;
                }
                for (Object obj8 : commMultiBaseAdapter17.getData()) {
                    int i10 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SettingModel settingModel8 = (SettingModel) obj8;
                    if (settingModel8.getType() == 14) {
                        settingModel8.setStatus(str2);
                        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter18 = settingFragment.adapter;
                        if (commMultiBaseAdapter18 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            commMultiBaseAdapter2 = commMultiBaseAdapter18;
                        }
                        commMultiBaseAdapter2.notifyItemChanged(i2);
                        Unit unit18 = Unit.INSTANCE;
                    } else {
                        i2 = i10;
                    }
                    break;
                }
                Unit unit19 = Unit.INSTANCE;
                break;
            case 15:
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.DARK_MODE, false)) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.DARK_MODE, false);
                    AppCompatDelegate.setDefaultNightMode(1);
                } else {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.DARK_MODE, true);
                    AppCompatDelegate.setDefaultNightMode(2);
                    str2 = "ON";
                }
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter19 = settingFragment.adapter;
                if (commMultiBaseAdapter19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter19 = null;
                }
                for (Object obj9 : commMultiBaseAdapter19.getData()) {
                    int i11 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SettingModel settingModel9 = (SettingModel) obj9;
                    if (settingModel9.getType() == 15) {
                        settingModel9.setStatus(str2);
                        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter20 = settingFragment.adapter;
                        if (commMultiBaseAdapter20 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            commMultiBaseAdapter2 = commMultiBaseAdapter20;
                        }
                        commMultiBaseAdapter2.notifyItemChanged(i2);
                        Unit unit20 = Unit.INSTANCE;
                    } else {
                        i2 = i11;
                    }
                    break;
                }
                Unit unit21 = Unit.INSTANCE;
                break;
            case 16:
                ((DialogFragment) SmartDownloadSettingDialog.class.newInstance()).show(settingFragment.getChildFragmentManager(), Reflection.getOrCreateKotlinClass(SmartDownloadSettingDialog.class).getSimpleName());
                break;
            case 17:
                boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_DOWNLOAD, false);
                if (z) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.HIDE_DOWNLOAD, false);
                } else {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.HIDE_DOWNLOAD, true);
                    str2 = "ON";
                }
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter21 = settingFragment.adapter;
                if (commMultiBaseAdapter21 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter21 = null;
                }
                for (Object obj10 : commMultiBaseAdapter21.getData()) {
                    int i12 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SettingModel settingModel10 = (SettingModel) obj10;
                    if (settingModel10.getType() == 17) {
                        settingModel10.setStatus(str2);
                        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter22 = settingFragment.adapter;
                        if (commMultiBaseAdapter22 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            commMultiBaseAdapter2 = commMultiBaseAdapter22;
                        }
                        commMultiBaseAdapter2.notifyItemChanged(i2);
                        Unit unit22 = Unit.INSTANCE;
                        HideDownloadLiveData.INSTANCE.get().setValue(Boolean.valueOf(!z));
                    } else {
                        i2 = i12;
                    }
                    break;
                }
                Unit unit23 = Unit.INSTANCE;
                HideDownloadLiveData.INSTANCE.get().setValue(Boolean.valueOf(!z));
                break;
            case 18:
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false)) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.PRIVATE_MODE, false);
                } else {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.PRIVATE_MODE, true);
                    str2 = "ON";
                }
                PrivateModeChangedLiveData.INSTANCE.get().setValue(true);
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter23 = settingFragment.adapter;
                if (commMultiBaseAdapter23 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter23 = null;
                }
                for (Object obj11 : commMultiBaseAdapter23.getData()) {
                    int i13 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SettingModel settingModel11 = (SettingModel) obj11;
                    if (settingModel11.getType() == 18) {
                        settingModel11.setStatus(str2);
                        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter24 = settingFragment.adapter;
                        if (commMultiBaseAdapter24 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            commMultiBaseAdapter2 = commMultiBaseAdapter24;
                        }
                        commMultiBaseAdapter2.notifyItemChanged(i2);
                        Unit unit24 = Unit.INSTANCE;
                    } else {
                        i2 = i13;
                    }
                    break;
                }
                Unit unit25 = Unit.INSTANCE;
                break;
            case 19:
                ChooseImageHostDialogFragment chooseImageHostDialogFragment = new ChooseImageHostDialogFragment();
                chooseImageHostDialogFragment.setListener(new SettingFragment$initListener$1$13(settingFragment));
                chooseImageHostDialogFragment.show(settingFragment.getChildFragmentManager(), "ChooseImageHostDialogFragment");
                break;
            case 20:
                ((DialogFragment) ChooseDownloadLanguageDialog.class.newInstance()).show(settingFragment.getChildFragmentManager(), Reflection.getOrCreateKotlinClass(ChooseDownloadLanguageDialog.class).getSimpleName());
                break;
            case 21:
                boolean zIsHwDecoder = SettingManager.INSTANCE.isHwDecoder();
                SettingManager.INSTANCE.setHwDecoder(!zIsHwDecoder);
                String str4 = !zIsHwDecoder ? "Hardware" : ExifInterface.TAG_SOFTWARE;
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter25 = settingFragment.adapter;
                if (commMultiBaseAdapter25 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter25 = null;
                }
                for (Object obj12 : commMultiBaseAdapter25.getData()) {
                    int i14 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    SettingModel settingModel12 = (SettingModel) obj12;
                    if (settingModel12.getType() == 21) {
                        settingModel12.setStatus(str4);
                        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter26 = settingFragment.adapter;
                        if (commMultiBaseAdapter26 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            commMultiBaseAdapter2 = commMultiBaseAdapter26;
                        }
                        commMultiBaseAdapter2.notifyItemChanged(i2);
                        Unit unit26 = Unit.INSTANCE;
                    } else {
                        i2 = i14;
                    }
                    break;
                }
                Unit unit27 = Unit.INSTANCE;
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setViewFocusListener$lambda$28$lambda$27(SettingFragment settingFragment, View view, boolean z) {
        if (z) {
            Intrinsics.checkNotNull(view);
            settingFragment.focusScale(view, 1.1f);
        }
        FocusBorder focusBorder = settingFragment.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(z);
    }

    private final void focusScale(View v, float scale) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(scale, scale, 0.0f));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void initData() {
        SettingFragment settingFragment = this;
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(com.movieboxpro.androidtv.R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(com.movieboxpro.androidtv.R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(settingFragment);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingModel("Server Speed Test", null, null, com.movieboxpro.androidtv.R.mipmap.ic_test_speed, 1, 0, 0, 102, null));
        arrayList.add(new SettingModel("Manage Accounts", null, null, com.movieboxpro.androidtv.R.mipmap.ic_account, 12, 0, 0, 102, null));
        arrayList.add(new SettingModel("Language", null, App.deviceLang, com.movieboxpro.androidtv.R.mipmap.ic_switch_language, 2, 0, 0, 98, null));
        arrayList.add(new SettingModel("Audio Download Language Priority", getDownloadAudioRule(), null, 0, 20, 0, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, null));
        arrayList.add(new SettingModel("Child Mode", null, PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE) ? "ON" : "", com.movieboxpro.androidtv.R.mipmap.ic_child_mode, 3, 0, 0, 98, null));
        arrayList.add(new SettingModel("Skip Opening and Ending", null, PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true) ? "ON" : "OFF", com.movieboxpro.androidtv.R.mipmap.ic_skip_open_end, 14, 0, 0, 98, null));
        arrayList.add(new SettingModel("Remember selected ORG quality", "ORG quality may play stuck,no image", PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_SELECTED_ORG, false) ? "ON" : "OFF", 0, 4, 0, 0, LocationRequestCompat.QUALITY_LOW_POWER, null));
        arrayList.add(new SettingModel("Private Mode", "Hide watch and search history", PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false) ? "ON" : "OFF", 0, 18, 0, 0, LocationRequestCompat.QUALITY_LOW_POWER, null));
        arrayList.add(new SettingModel("Hide MovieLists", null, PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIE_LIST, false) ? "ON" : "OFF", 0, 5, 0, 0, 106, null));
        arrayList.add(new SettingModel("Hide Download Feature", null, PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_DOWNLOAD, false) ? "ON" : "OFF", 0, 17, 0, 0, 106, null));
        arrayList.add(new SettingModel("Smart Download", null, SettingManager.INSTANCE.getSmartDownload() ? "ON" : "OFF", 0, 16, 0, 0, 106, null));
        arrayList.add(new SettingModel("Solid Black Background", null, PrefsUtils.getInstance().getBoolean(Constant.Prefs.DARK_MODE, false) ? "ON" : "OFF", 0, 15, 0, 0, 106, null));
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.PLAYER_ENGINE, "auto");
        if (string != null) {
            switch (string.hashCode()) {
                case 100892:
                    if (string.equals("exo")) {
                        string = "exo(Support HDR and dolby audio)";
                    }
                    break;
                case 104298:
                    if (string.equals("ijk")) {
                        string = "ijk(Better Performance)";
                    }
                    break;
                case 116845:
                    if (string.equals("vlc")) {
                        string = "vlc(Support HDR)";
                    }
                    break;
                case 3005871:
                    if (string.equals("auto")) {
                        string = "Auto";
                    }
                    break;
            }
        }
        arrayList.add(new SettingModel("Player Engine", null, string, 0, 9, 0, 0, 106, null));
        arrayList.add(new SettingModel("Video Decoder", null, SettingManager.INSTANCE.isHwDecoder() ? "Hardware" : ExifInterface.TAG_SOFTWARE, 0, 21, 0, 0, 106, null));
        String stringConfig = ConfigUtils.readStringConfig("image_host");
        Intrinsics.checkNotNull(stringConfig);
        if (!StringsKt.isBlank(stringConfig)) {
            String string2 = PrefsUtils.getInstance().getString("image_host", "");
            Intrinsics.checkNotNull(string2);
            if (StringsKt.isBlank(string2)) {
                arrayList.add(new SettingModel("Image Host", null, "Default", 0, 19, 0, 0, 106, null));
            } else {
                arrayList.add(new SettingModel("Image Host", null, ((ImageHost) JSONObject.parseObject(string2, ImageHost.class)).getTitle(), 0, 19, 0, 0, 106, null));
            }
        }
        arrayList.add(new SettingModel("Play Video With Other Players", null, PrefsUtils.getInstance().getBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, false) ? "ON" : "OFF", 0, 8, 0, 0, 106, null));
        arrayList.add(new SettingModel("Auto Play Next Episode", null, PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_PLAY_NEXT, false) ? "ON" : "OFF", 0, 7, 0, 0, 106, null));
        arrayList.add(new SettingModel("Select Subtitle Automatically", null, PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_SELECT_SUBTITLE, false) ? "ON" : "OFF", 0, 10, 0, 0, 106, null));
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%.1f s", Arrays.copyOf(new Object[]{Double.valueOf((-PrefsUtils.getInstance().getLong(Constant.Prefs.AUDIO_DELAY)) / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        arrayList.add(new SettingModel("Audio Delay(only for vlc)", null, str, 0, 11, 0, 0, 106, null));
        arrayList.add(new SettingModel("Check For Updates", null, null, 0, 13, 0, 0, IjkMediaMeta.FF_PROFILE_H264_HIGH_10, null));
        this.adapter = new CommMultiBaseAdapter<>(new Function2() { // from class: com.movieboxpro.android.view.fragment.account.SettingFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return SettingFragment.initData$lambda$29(this.f$0, (BaseViewHolder) obj, (SettingModel) obj2);
            }
        }, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.SettingFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(SettingFragment.initData$lambda$30((SettingModel) obj));
            }
        }, CollectionsKt.arrayListOf(new Pair(1, Integer.valueOf(com.movieboxpro.androidtv.R.layout.adapter_setting_item))));
        FragmentSettingBinding fragmentSettingBinding = this.binding;
        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter = null;
        if (fragmentSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSettingBinding = null;
        }
        TvRecyclerView tvRecyclerView = fragmentSettingBinding.recyclerView;
        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter2 = this.adapter;
        if (commMultiBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter2 = null;
        }
        tvRecyclerView.setAdapter(commMultiBaseAdapter2);
        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter3 = this.adapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commMultiBaseAdapter = commMultiBaseAdapter3;
        }
        commMultiBaseAdapter.setList(arrayList);
        DownloadLanguageChangedLiveData.INSTANCE.get().observe(this, new SettingFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.account.SettingFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingFragment.initData$lambda$32(this.f$0, (Boolean) obj);
            }
        }));
        SmartDownloadChangedLiveData.INSTANCE.get().observeInFragment(settingFragment, new SettingFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.account.SettingFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingFragment.initData$lambda$35(this.f$0, (Boolean) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$29(SettingFragment settingFragment, BaseViewHolder helper, SettingModel item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        helper.setText(com.movieboxpro.androidtv.R.id.tvName, item.getTitle());
        TextView textView = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.tvDesc);
        TextView textView2 = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.tvStatus);
        ImageView imageView = (ImageView) helper.getView(com.movieboxpro.androidtv.R.id.ivIcon);
        String desc = item.getDesc();
        if (desc == null || StringsKt.isBlank(desc)) {
            CommonExtKt.gone(textView);
        } else {
            CommonExtKt.visible(textView);
            textView.setText(item.getDesc());
        }
        String status = item.getStatus();
        if (status == null || StringsKt.isBlank(status)) {
            CommonExtKt.gone(textView2);
        } else {
            CommonExtKt.visible(textView2);
            textView2.setText(item.getStatus());
        }
        if (item.getResId() == 0) {
            CommonExtKt.gone(imageView);
        } else {
            CommonExtKt.visible(imageView);
            imageView.setImageResource(item.getResId());
        }
        ImageView imageView2 = (ImageView) helper.getView(com.movieboxpro.androidtv.R.id.imageView);
        if (item.getIcon() == 0) {
            CommonExtKt.gone(imageView2);
        } else {
            CommonExtKt.visible(imageView2);
            GlideUtils.load(settingFragment.getContext(), item.getIcon(), imageView2);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int initData$lambda$30(SettingModel t) {
        Intrinsics.checkNotNullParameter(t, "t");
        return t.getViewType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$32(SettingFragment settingFragment, Boolean bool) {
        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter = settingFragment.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        int i = 0;
        for (Object obj : commMultiBaseAdapter.getData()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            SettingModel settingModel = (SettingModel) obj;
            if (settingModel.getType() == 20) {
                settingModel.setDesc(settingFragment.getDownloadAudioRule());
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter2 = settingFragment.adapter;
                if (commMultiBaseAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter2 = null;
                }
                commMultiBaseAdapter2.notifyItemChanged(i);
            }
            i = i2;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$35(SettingFragment settingFragment, Boolean bool) {
        String str;
        if (SettingManager.INSTANCE.getSmartDownload()) {
            str = "ON";
        } else {
            str = "OFF";
        }
        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter = settingFragment.adapter;
        CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        int i = 0;
        for (Object obj : commMultiBaseAdapter.getData()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            SettingModel settingModel = (SettingModel) obj;
            if (settingModel.getType() == 16) {
                settingModel.setStatus(str);
                CommMultiBaseAdapter<SettingModel> commMultiBaseAdapter3 = settingFragment.adapter;
                if (commMultiBaseAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    commMultiBaseAdapter2 = commMultiBaseAdapter3;
                }
                commMultiBaseAdapter2.notifyItemChanged(i);
                break;
            }
            i = i2;
        }
        return Unit.INSTANCE;
    }

    private final String getDownloadAudioRule() {
        SettingManager settingManager = SettingManager.INSTANCE;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        List<String> downloadAudioLanguage = settingManager.getDownloadAudioLanguage(contextRequireContext);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(downloadAudioLanguage, 10));
        Iterator<T> it = downloadAudioLanguage.iterator();
        while (it.hasNext()) {
            arrayList.add((String) StringsKt.split$default((CharSequence) it.next(), new String[]{"->"}, false, 0, 6, (Object) null).get(1));
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object obj : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            sb.append((String) obj);
            sb.append(">");
            i = i2;
        }
        sb.deleteCharAt(sb.length() - 1);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    private final void setViewFocusListener(View... args) {
        for (View view : args) {
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.fragment.account.SettingFragment$$ExternalSyntheticLambda5
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    SettingFragment.setViewFocusListener$lambda$28$lambda$27(this.f$0, view2, z);
                }
            });
        }
    }
}

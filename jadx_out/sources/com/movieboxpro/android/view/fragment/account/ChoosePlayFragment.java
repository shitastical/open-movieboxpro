package com.movieboxpro.android.view.fragment.account;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.adorkable.iosdialog.R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.androidtv.databinding.FragmentChoosePlayBinding;
import com.owen.focus.FocusBorder;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: ChoosePlayFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u0000 72\u00020\u0001:\u000278B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u000e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\tJ\b\u0010\u001f\u001a\u00020\u001bH\u0016J\u0018\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$J&\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001a\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020&2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010-\u001a\u00020\u001bH\u0002J!\u0010.\u001a\u00020\u001b2\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020&00\"\u00020&H\u0002¢\u0006\u0002\u00101J\u0018\u00102\u001a\u00020\u001b2\u0006\u00103\u001a\u00020&2\u0006\u00104\u001a\u000205H\u0002J\b\u00106\u001a\u00020\u001bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R+\u0010\u0014\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u00069"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/ChoosePlayFragment;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentChoosePlayBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/fragment/account/ChoosePlayFragment$ChooseListener;", "<set-?>", "", "boxType", "getBoxType", "()I", "setBoxType", "(I)V", "boxType$delegate", "Lkotlin/properties/ReadWriteProperty;", "", "needDetail", "getNeedDetail", "()Z", "setNeedDetail", "(Z)V", "needDetail$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setChooseListener", "onStart", "showAllowingStateLoss", "manager", "Landroidx/fragment/app/FragmentManager;", "tag", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "initListener", "setViewFocusListener", "args", "", "([Landroid/view/View;)V", "focusScale", "v", "scale", "", "initData", "Companion", "ChooseListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChoosePlayFragment extends DialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(ChoosePlayFragment.class, "boxType", "getBoxType()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ChoosePlayFragment.class, "needDetail", "getNeedDetail()Z", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentChoosePlayBinding binding;

    /* JADX INFO: renamed from: boxType$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty boxType;
    private ChooseListener listener;
    private FocusBorder mFocusBorder;

    /* JADX INFO: renamed from: needDetail$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty needDetail;

    /* JADX INFO: compiled from: ChoosePlayFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/ChoosePlayFragment$ChooseListener;", "", "chooseResume", "", "chooseRestart", "goDetail", "delete", "markWatched", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ChooseListener {
        void chooseRestart();

        void chooseResume();

        void delete();

        void goDetail();

        void markWatched();
    }

    public ChoosePlayFragment() {
        ChoosePlayFragment choosePlayFragment = this;
        this.boxType = FragmentArgsKt.arg(choosePlayFragment);
        this.needDetail = FragmentArgsKt.argOrDefault(choosePlayFragment, true);
    }

    /* JADX INFO: compiled from: ChoosePlayFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/ChoosePlayFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/account/ChoosePlayFragment;", "boxType", "", "needDetail", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ ChoosePlayFragment newInstance$default(Companion companion, int i, boolean z, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                z = true;
            }
            return companion.newInstance(i, z);
        }

        public final ChoosePlayFragment newInstance(int boxType, boolean needDetail) {
            ChoosePlayFragment choosePlayFragment = new ChoosePlayFragment();
            choosePlayFragment.setBoxType(boxType);
            choosePlayFragment.setNeedDetail(needDetail);
            return choosePlayFragment;
        }
    }

    private final int getBoxType() {
        return ((Number) this.boxType.getValue(this, $$delegatedProperties[0])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBoxType(int i) {
        this.boxType.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    private final boolean getNeedDetail() {
        return ((Boolean) this.needDetail.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNeedDetail(boolean z) {
        this.needDetail.setValue(this, $$delegatedProperties[1], Boolean.valueOf(z));
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.DialogStyle);
    }

    public final void setChooseListener(ChooseListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            getResources().getDisplayMetrics();
            attributes.width = -2;
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
    }

    public final void showAllowingStateLoss(FragmentManager manager, String tag) {
        Intrinsics.checkNotNullParameter(manager, "manager");
        try {
            Field declaredField = DialogFragment.class.getDeclaredField("mDismissed");
            Intrinsics.checkNotNullExpressionValue(declaredField, "getDeclaredField(...)");
            declaredField.setAccessible(true);
            declaredField.set(this, false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
        try {
            Field declaredField2 = DialogFragment.class.getDeclaredField("mShownByMe");
            Intrinsics.checkNotNullExpressionValue(declaredField2, "getDeclaredField(...)");
            declaredField2.setAccessible(true);
            declaredField2.set(this, true);
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (NoSuchFieldException e4) {
            e4.printStackTrace();
        }
        FragmentTransaction fragmentTransactionBeginTransaction = manager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
        fragmentTransactionBeginTransaction.add(this, tag);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentChoosePlayBinding fragmentChoosePlayBindingInflate = FragmentChoosePlayBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentChoosePlayBindingInflate, "inflate(...)");
        this.binding = fragmentChoosePlayBindingInflate;
        if (fragmentChoosePlayBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChoosePlayBindingInflate = null;
        }
        return fragmentChoosePlayBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    private final void initListener() {
        FragmentChoosePlayBinding fragmentChoosePlayBinding = this.binding;
        if (fragmentChoosePlayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChoosePlayBinding = null;
        }
        LinearLayout llResume = fragmentChoosePlayBinding.llResume;
        Intrinsics.checkNotNullExpressionValue(llResume, "llResume");
        LinearLayout llRestart = fragmentChoosePlayBinding.llRestart;
        Intrinsics.checkNotNullExpressionValue(llRestart, "llRestart");
        LinearLayout llDetail = fragmentChoosePlayBinding.llDetail;
        Intrinsics.checkNotNullExpressionValue(llDetail, "llDetail");
        LinearLayout llDelete = fragmentChoosePlayBinding.llDelete;
        Intrinsics.checkNotNullExpressionValue(llDelete, "llDelete");
        LinearLayout llMark = fragmentChoosePlayBinding.llMark;
        Intrinsics.checkNotNullExpressionValue(llMark, "llMark");
        setViewFocusListener(llResume, llRestart, llDetail, llDelete, llMark);
        fragmentChoosePlayBinding.llResume.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.ChoosePlayFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlayFragment.initListener$lambda$6$lambda$1(this.f$0, view);
            }
        });
        fragmentChoosePlayBinding.llRestart.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.ChoosePlayFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlayFragment.initListener$lambda$6$lambda$2(this.f$0, view);
            }
        });
        fragmentChoosePlayBinding.llDetail.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.ChoosePlayFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlayFragment.initListener$lambda$6$lambda$3(this.f$0, view);
            }
        });
        fragmentChoosePlayBinding.llDelete.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.ChoosePlayFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlayFragment.initListener$lambda$6$lambda$4(this.f$0, view);
            }
        });
        fragmentChoosePlayBinding.llMark.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.ChoosePlayFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChoosePlayFragment.initListener$lambda$6$lambda$5(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6$lambda$1(ChoosePlayFragment choosePlayFragment, View view) {
        ChooseListener chooseListener = choosePlayFragment.listener;
        if (chooseListener != null) {
            chooseListener.chooseResume();
        }
        choosePlayFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6$lambda$2(ChoosePlayFragment choosePlayFragment, View view) {
        ChooseListener chooseListener = choosePlayFragment.listener;
        if (chooseListener != null) {
            chooseListener.chooseRestart();
        }
        choosePlayFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6$lambda$3(ChoosePlayFragment choosePlayFragment, View view) {
        ChooseListener chooseListener = choosePlayFragment.listener;
        if (chooseListener != null) {
            chooseListener.goDetail();
        }
        choosePlayFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6$lambda$4(ChoosePlayFragment choosePlayFragment, View view) {
        ChooseListener chooseListener = choosePlayFragment.listener;
        if (chooseListener != null) {
            chooseListener.delete();
        }
        choosePlayFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6$lambda$5(ChoosePlayFragment choosePlayFragment, View view) {
        ChooseListener chooseListener = choosePlayFragment.listener;
        if (chooseListener != null) {
            chooseListener.markWatched();
        }
        choosePlayFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setViewFocusListener$lambda$8$lambda$7(ChoosePlayFragment choosePlayFragment, View view, boolean z) {
        if (z) {
            Intrinsics.checkNotNull(view);
            choosePlayFragment.focusScale(view, 1.1f);
        }
        FocusBorder focusBorder = choosePlayFragment.mFocusBorder;
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

    private final void initData() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(com.movieboxpro.androidtv.R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(com.movieboxpro.androidtv.R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(this);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
        FragmentChoosePlayBinding fragmentChoosePlayBinding = null;
        if (!getNeedDetail()) {
            FragmentChoosePlayBinding fragmentChoosePlayBinding2 = this.binding;
            if (fragmentChoosePlayBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentChoosePlayBinding2 = null;
            }
            LinearLayout llDetail = fragmentChoosePlayBinding2.llDetail;
            Intrinsics.checkNotNullExpressionValue(llDetail, "llDetail");
            CommonExtKt.gone(llDetail);
            FragmentChoosePlayBinding fragmentChoosePlayBinding3 = this.binding;
            if (fragmentChoosePlayBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentChoosePlayBinding3 = null;
            }
            LinearLayout llDelete = fragmentChoosePlayBinding3.llDelete;
            Intrinsics.checkNotNullExpressionValue(llDelete, "llDelete");
            CommonExtKt.gone(llDelete);
            FragmentChoosePlayBinding fragmentChoosePlayBinding4 = this.binding;
            if (fragmentChoosePlayBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentChoosePlayBinding4 = null;
            }
            LinearLayout llMark = fragmentChoosePlayBinding4.llMark;
            Intrinsics.checkNotNullExpressionValue(llMark, "llMark");
            CommonExtKt.gone(llMark);
        }
        if (getBoxType() == 1) {
            FragmentChoosePlayBinding fragmentChoosePlayBinding5 = this.binding;
            if (fragmentChoosePlayBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentChoosePlayBinding = fragmentChoosePlayBinding5;
            }
            fragmentChoosePlayBinding.tvDetail.setText("MOVIE DETAIL");
        } else {
            FragmentChoosePlayBinding fragmentChoosePlayBinding6 = this.binding;
            if (fragmentChoosePlayBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentChoosePlayBinding = fragmentChoosePlayBinding6;
            }
            fragmentChoosePlayBinding.tvDetail.setText("TV DETAIL");
        }
        Object objAs = Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.ChoosePlayFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ChoosePlayFragment.initData$lambda$9(this.f$0, (Long) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$9(ChoosePlayFragment choosePlayFragment, Long l) {
        FragmentChoosePlayBinding fragmentChoosePlayBinding = choosePlayFragment.binding;
        if (fragmentChoosePlayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChoosePlayBinding = null;
        }
        fragmentChoosePlayBinding.llResume.requestFocus();
        return Unit.INSTANCE;
    }

    private final void setViewFocusListener(View... args) {
        for (View view : args) {
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.fragment.account.ChoosePlayFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    ChoosePlayFragment.setViewFocusListener$lambda$8$lambda$7(this.f$0, view2, z);
                }
            });
        }
    }
}

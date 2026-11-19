package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.listener.AddWatchPlanListener;
import com.movieboxpro.android.livedata.RefreshWatchedLiveData;
import com.movieboxpro.android.livedata.RefreshWatchingLiveData;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogAddWatchPlanBinding;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: AddWatchPlanDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 (2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001(B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u001aJ\u001a\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0010\u0010#\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010$\u001a\u00020\u001cH\u0002J\b\u0010%\u001a\u00020\u001cH\u0002J\u0010\u0010&\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010'\u001a\u00020\u001cH\u0002R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR/\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0005\u001a\u0004\u0018\u00010\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\r\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AddWatchPlanDialog;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/DialogAddWatchPlanBinding;", "<init>", "()V", "<set-?>", "", "watched", "getWatched", "()I", "setWatched", "(I)V", "watched$delegate", "Lkotlin/properties/ReadWriteProperty;", "boxType", "getBoxType", "setBoxType", "boxType$delegate", "", "id", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "id$delegate", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/AddWatchPlanListener;", "onStart", "", "setListener", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "changeWatched", "initListener", "cancelWatch", "markWatched", "initWatchedStatus", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AddWatchPlanDialog extends BaseTransparentDialogFragment<DialogAddWatchPlanBinding> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddWatchPlanDialog.class, "watched", "getWatched()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddWatchPlanDialog.class, "boxType", "getBoxType()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddWatchPlanDialog.class, "id", "getId()Ljava/lang/String;", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: boxType$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty boxType;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty id;
    private AddWatchPlanListener listener;

    /* JADX INFO: renamed from: watched$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty watched;

    public AddWatchPlanDialog() {
        super(R.layout.dialog_add_watch_plan);
        AddWatchPlanDialog addWatchPlanDialog = this;
        this.watched = FragmentArgsKt.arg(addWatchPlanDialog);
        this.boxType = FragmentArgsKt.arg(addWatchPlanDialog);
        this.id = FragmentArgsKt.argOrNull(addWatchPlanDialog);
    }

    private final int getWatched() {
        return ((Number) this.watched.getValue(this, $$delegatedProperties[0])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setWatched(int i) {
        this.watched.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    private final int getBoxType() {
        return ((Number) this.boxType.getValue(this, $$delegatedProperties[1])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBoxType(int i) {
        this.boxType.setValue(this, $$delegatedProperties[1], Integer.valueOf(i));
    }

    private final String getId() {
        return (String) this.id.getValue(this, $$delegatedProperties[2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setId(String str) {
        this.id.setValue(this, $$delegatedProperties[2], str);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            getResources().getDisplayMetrics();
            attributes.width = -1;
            attributes.height = -1;
            attributes.gravity = 17;
            attributes.dimAmount = 0.95f;
            window.setAttributes(attributes);
        }
    }

    public final void setListener(AddWatchPlanListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    /* JADX INFO: compiled from: AddWatchPlanDialog.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AddWatchPlanDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/AddWatchPlanDialog;", "boxType", "", "watched", "id", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AddWatchPlanDialog newInstance(int boxType, int watched, String id) {
            AddWatchPlanDialog addWatchPlanDialog = new AddWatchPlanDialog();
            addWatchPlanDialog.setBoxType(boxType);
            addWatchPlanDialog.setWatched(watched);
            addWatchPlanDialog.setId(id);
            return addWatchPlanDialog;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initWatchedStatus();
        initListener();
    }

    private final void changeWatched(final int watched) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_mark").param("box_type", Integer.valueOf(getBoxType())).param("mid", getId()).param("watched", Integer.valueOf(watched)).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.dialog.AddWatchPlanDialog$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddWatchPlanDialog.changeWatched$lambda$1(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddWatchPlanDialog$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddWatchPlanDialog.changeWatched$lambda$2(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddWatchPlanDialog$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddWatchPlanDialog.changeWatched$lambda$3(this.f$0, watched, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit changeWatched$lambda$2(AddWatchPlanDialog addWatchPlanDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addWatchPlanDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit changeWatched$lambda$3(AddWatchPlanDialog addWatchPlanDialog, int i, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addWatchPlanDialog.hideLoadingView();
        addWatchPlanDialog.setWatched(i);
        AddWatchPlanListener addWatchPlanListener = addWatchPlanDialog.listener;
        if (addWatchPlanListener != null) {
            addWatchPlanListener.onWatchedChange(i);
        }
        addWatchPlanDialog.initWatchedStatus();
        RefreshWatchedLiveData.INSTANCE.get().setValue(true);
        RefreshWatchingLiveData.INSTANCE.get().setValue(true);
        addWatchPlanDialog.dismissAllowingStateLoss();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit changeWatched$lambda$1(AddWatchPlanDialog addWatchPlanDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addWatchPlanDialog.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void initListener() {
        getBinding().llWatched.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.AddWatchPlanDialog$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddWatchPlanDialog.initListener$lambda$4(this.f$0, view);
            }
        });
        getBinding().llWaiting.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.AddWatchPlanDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddWatchPlanDialog.initListener$lambda$5(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4(AddWatchPlanDialog addWatchPlanDialog, View view) {
        if (addWatchPlanDialog.getWatched() == 1) {
            addWatchPlanDialog.cancelWatch();
        } else if (addWatchPlanDialog.getWatched() == 0) {
            addWatchPlanDialog.changeWatched(1);
        } else {
            addWatchPlanDialog.markWatched(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5(AddWatchPlanDialog addWatchPlanDialog, View view) {
        if (addWatchPlanDialog.getWatched() == 0) {
            addWatchPlanDialog.cancelWatch();
        } else if (addWatchPlanDialog.getWatched() == 1) {
            addWatchPlanDialog.changeWatched(0);
        } else {
            addWatchPlanDialog.markWatched(0);
        }
    }

    private final void cancelWatch() {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_del").param("mid", getId()).param("box_type", Integer.valueOf(getBoxType())).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.dialog.AddWatchPlanDialog$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddWatchPlanDialog.cancelWatch$lambda$6(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddWatchPlanDialog$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddWatchPlanDialog.cancelWatch$lambda$7(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddWatchPlanDialog$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddWatchPlanDialog.cancelWatch$lambda$8(this.f$0, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cancelWatch$lambda$7(AddWatchPlanDialog addWatchPlanDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addWatchPlanDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cancelWatch$lambda$8(AddWatchPlanDialog addWatchPlanDialog, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addWatchPlanDialog.hideLoadingView();
        addWatchPlanDialog.setWatched(-1);
        AddWatchPlanListener addWatchPlanListener = addWatchPlanDialog.listener;
        if (addWatchPlanListener != null) {
            addWatchPlanListener.onWatchedChange(addWatchPlanDialog.getWatched());
        }
        addWatchPlanDialog.initWatchedStatus();
        RefreshWatchedLiveData.INSTANCE.get().setValue(true);
        RefreshWatchingLiveData.INSTANCE.get().setValue(true);
        addWatchPlanDialog.dismissAllowingStateLoss();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cancelWatch$lambda$6(AddWatchPlanDialog addWatchPlanDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addWatchPlanDialog.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void markWatched(final int watched) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_add").param("box_type", Integer.valueOf(getBoxType())).param("mid", getId()).param("watched", Integer.valueOf(watched)).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.dialog.AddWatchPlanDialog$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddWatchPlanDialog.markWatched$lambda$9(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddWatchPlanDialog$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddWatchPlanDialog.markWatched$lambda$10(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddWatchPlanDialog$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddWatchPlanDialog.markWatched$lambda$11(this.f$0, watched, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markWatched$lambda$10(AddWatchPlanDialog addWatchPlanDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addWatchPlanDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markWatched$lambda$11(AddWatchPlanDialog addWatchPlanDialog, int i, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addWatchPlanDialog.hideLoadingView();
        addWatchPlanDialog.setWatched(i);
        AddWatchPlanListener addWatchPlanListener = addWatchPlanDialog.listener;
        if (addWatchPlanListener != null) {
            addWatchPlanListener.onWatchedChange(i);
        }
        addWatchPlanDialog.initWatchedStatus();
        RefreshWatchedLiveData.INSTANCE.get().setValue(true);
        RefreshWatchingLiveData.INSTANCE.get().setValue(true);
        addWatchPlanDialog.dismissAllowingStateLoss();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markWatched$lambda$9(AddWatchPlanDialog addWatchPlanDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addWatchPlanDialog.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void initWatchedStatus() {
        DialogAddWatchPlanBinding binding = getBinding();
        if (getWatched() == -1) {
            binding.ivWaiting.setImageResource(R.mipmap.ic_waiting);
            binding.ivWatched.setImageResource(R.mipmap.ic_watched);
        } else if (getWatched() == 0) {
            binding.ivWaiting.setImageResource(R.mipmap.ic_waiting_added);
            binding.ivWatched.setImageResource(R.mipmap.ic_watched);
        } else if (getWatched() == 1) {
            binding.ivWaiting.setImageResource(R.mipmap.ic_waiting);
            binding.ivWatched.setImageResource(R.mipmap.ic_watched_added);
        }
    }
}

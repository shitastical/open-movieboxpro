package com.movieboxpro.android.view.fragment.account;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentChooseLoginWayBinding;
import com.owen.focus.FocusBorder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: ChooseLoginWayFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\tJ\u001a\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J!\u0010\u0016\u001a\u00020\u00132\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0018\"\u00020\u000bH\u0002¢\u0006\u0002\u0010\u0019J\u0018\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/ChooseLoginWayFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentChooseLoginWayBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/fragment/account/ChooseLoginWayFragment$ChooseLoginListener;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "", "onViewCreated", "view", "setViewFocusListener", "args", "", "([Landroid/view/View;)V", "focusScale", "v", "scale", "", "ChooseLoginListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseLoginWayFragment extends Fragment {
    private FragmentChooseLoginWayBinding binding;
    private ChooseLoginListener listener;
    private FocusBorder mFocusBorder;

    /* JADX INFO: compiled from: ChooseLoginWayFragment.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/ChooseLoginWayFragment$ChooseLoginListener;", "", "chooseLogin", "", "type", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ChooseLoginListener {
        void chooseLogin(int type);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentChooseLoginWayBinding fragmentChooseLoginWayBindingInflate = FragmentChooseLoginWayBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentChooseLoginWayBindingInflate, "inflate(...)");
        this.binding = fragmentChooseLoginWayBindingInflate;
        if (fragmentChooseLoginWayBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChooseLoginWayBindingInflate = null;
        }
        View root = fragmentChooseLoginWayBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public final void setListener(ChooseLoginListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(this);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
        FragmentChooseLoginWayBinding fragmentChooseLoginWayBinding = this.binding;
        FragmentChooseLoginWayBinding fragmentChooseLoginWayBinding2 = null;
        if (fragmentChooseLoginWayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChooseLoginWayBinding = null;
        }
        fragmentChooseLoginWayBinding.tvVersion.setText(BuildConfig.VERSION_NAME);
        View[] viewArr = new View[2];
        FragmentChooseLoginWayBinding fragmentChooseLoginWayBinding3 = this.binding;
        if (fragmentChooseLoginWayBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChooseLoginWayBinding3 = null;
        }
        LinearLayout llQr = fragmentChooseLoginWayBinding3.llQr;
        Intrinsics.checkNotNullExpressionValue(llQr, "llQr");
        viewArr[0] = llQr;
        FragmentChooseLoginWayBinding fragmentChooseLoginWayBinding4 = this.binding;
        if (fragmentChooseLoginWayBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChooseLoginWayBinding4 = null;
        }
        LinearLayout llCode = fragmentChooseLoginWayBinding4.llCode;
        Intrinsics.checkNotNullExpressionValue(llCode, "llCode");
        viewArr[1] = llCode;
        setViewFocusListener(viewArr);
        FragmentChooseLoginWayBinding fragmentChooseLoginWayBinding5 = this.binding;
        if (fragmentChooseLoginWayBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChooseLoginWayBinding5 = null;
        }
        fragmentChooseLoginWayBinding5.llQr.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.account.ChooseLoginWayFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view2, int i, KeyEvent keyEvent) {
                return ChooseLoginWayFragment.onViewCreated$lambda$0(view2, i, keyEvent);
            }
        });
        FragmentChooseLoginWayBinding fragmentChooseLoginWayBinding6 = this.binding;
        if (fragmentChooseLoginWayBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChooseLoginWayBinding6 = null;
        }
        fragmentChooseLoginWayBinding6.llQr.requestFocus();
        FragmentChooseLoginWayBinding fragmentChooseLoginWayBinding7 = this.binding;
        if (fragmentChooseLoginWayBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChooseLoginWayBinding7 = null;
        }
        fragmentChooseLoginWayBinding7.llQr.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.ChooseLoginWayFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ChooseLoginWayFragment.onViewCreated$lambda$1(this.f$0, view2);
            }
        });
        FragmentChooseLoginWayBinding fragmentChooseLoginWayBinding8 = this.binding;
        if (fragmentChooseLoginWayBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentChooseLoginWayBinding2 = fragmentChooseLoginWayBinding8;
        }
        fragmentChooseLoginWayBinding2.llCode.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.ChooseLoginWayFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ChooseLoginWayFragment.onViewCreated$lambda$2(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$0(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 || i != 19) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(ChooseLoginWayFragment chooseLoginWayFragment, View view) {
        ChooseLoginListener chooseLoginListener = chooseLoginWayFragment.listener;
        if (chooseLoginListener != null) {
            chooseLoginListener.chooseLogin(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(ChooseLoginWayFragment chooseLoginWayFragment, View view) {
        ChooseLoginListener chooseLoginListener = chooseLoginWayFragment.listener;
        if (chooseLoginListener != null) {
            chooseLoginListener.chooseLogin(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setViewFocusListener$lambda$4$lambda$3(ChooseLoginWayFragment chooseLoginWayFragment, View view, boolean z) {
        if (z) {
            Intrinsics.checkNotNull(view);
            chooseLoginWayFragment.focusScale(view, 1.1f);
        }
        FocusBorder focusBorder = chooseLoginWayFragment.mFocusBorder;
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

    private final void setViewFocusListener(View... args) {
        for (View view : args) {
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.fragment.account.ChooseLoginWayFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    ChooseLoginWayFragment.setViewFocusListener$lambda$4$lambda$3(this.f$0, view2, z);
                }
            });
        }
    }
}

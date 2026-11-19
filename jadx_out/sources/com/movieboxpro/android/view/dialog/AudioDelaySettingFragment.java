package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBottomFullScreenDialogFragment;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentAudioDelayBinding;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: AudioDelaySettingFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\bH\u0002J\b\u0010\u000e\u001a\u00020\bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AudioDelaySettingFragment;", "Lcom/movieboxpro/android/base/BaseBottomFullScreenDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentAudioDelayBinding;", "<init>", "()V", "currDelay", "", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initListener", "initView", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AudioDelaySettingFragment extends BaseBottomFullScreenDialogFragment<FragmentAudioDelayBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private long currDelay;

    public AudioDelaySettingFragment() {
        super(R.layout.fragment_audio_delay);
    }

    @Override // com.movieboxpro.android.base.BaseBottomFullScreenDialogFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
    }

    private final void initListener() {
        getBinding().flDecrease.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.AudioDelaySettingFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioDelaySettingFragment.initListener$lambda$0(this.f$0, view);
            }
        });
        getBinding().flIncrease.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.AudioDelaySettingFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioDelaySettingFragment.initListener$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(AudioDelaySettingFragment audioDelaySettingFragment, View view) {
        audioDelaySettingFragment.currDelay += (long) 100;
        TextView textView = audioDelaySettingFragment.getBinding().tvDelay;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%.1f s", Arrays.copyOf(new Object[]{Double.valueOf((-audioDelaySettingFragment.currDelay) / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(str);
        PrefsUtils.getInstance().putLong(Constant.Prefs.AUDIO_DELAY, audioDelaySettingFragment.currDelay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(AudioDelaySettingFragment audioDelaySettingFragment, View view) {
        audioDelaySettingFragment.currDelay -= (long) 100;
        TextView textView = audioDelaySettingFragment.getBinding().tvDelay;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%.1f s", Arrays.copyOf(new Object[]{Double.valueOf((-audioDelaySettingFragment.currDelay) / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(str);
        PrefsUtils.getInstance().putLong(Constant.Prefs.AUDIO_DELAY, audioDelaySettingFragment.currDelay);
    }

    private final void initView() {
        long j = PrefsUtils.getInstance().getLong(Constant.Prefs.AUDIO_DELAY, 0L);
        this.currDelay = j;
        TextView textView = getBinding().tvDelay;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%.1f s", Arrays.copyOf(new Object[]{Double.valueOf((-j) / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(str);
    }

    /* JADX INFO: compiled from: AudioDelaySettingFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AudioDelaySettingFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/AudioDelaySettingFragment;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AudioDelaySettingFragment newInstance() {
            return new AudioDelaySettingFragment();
        }
    }
}

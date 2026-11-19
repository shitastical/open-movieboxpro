package com.movieboxpro.android.tv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.view.fragment.account.ChooseLoginWayFragment;
import com.movieboxpro.android.view.fragment.account.CodeLoginFragmentFragment;
import com.movieboxpro.android.view.fragment.account.LoginFragment;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: LoginActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014J\b\u0010\b\u001a\u00020\u0005H\u0002J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0007J\b\u0010\f\u001a\u00020\u0005H\u0014J\b\u0010\r\u001a\u00020\u0005H\u0014¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/tv/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "initData", "onLogin", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/LoginEvent;", "onStart", "onDestroy", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoginActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initData();
    }

    private final void initData() {
        ChooseLoginWayFragment chooseLoginWayFragment = new ChooseLoginWayFragment();
        chooseLoginWayFragment.setListener(new ChooseLoginWayFragment.ChooseLoginListener() { // from class: com.movieboxpro.android.tv.LoginActivity.initData.1
            @Override // com.movieboxpro.android.view.fragment.account.ChooseLoginWayFragment.ChooseLoginListener
            public void chooseLogin(int type) {
                if (type == 1) {
                    FragmentUtils.replace(LoginActivity.this.getSupportFragmentManager(), LoginFragment.INSTANCE.newInstance(LoginActivity.this.getIntent().getBooleanExtra("addAccount", false)), R.id.flContainer);
                } else {
                    if (type != 2) {
                        return;
                    }
                    FragmentUtils.replace(LoginActivity.this.getSupportFragmentManager(), CodeLoginFragmentFragment.INSTANCE.newInstance(LoginActivity.this.getIntent().getBooleanExtra("addAccount", false)), R.id.flContainer);
                }
            }
        });
        FragmentUtils.add(getSupportFragmentManager(), chooseLoginWayFragment, R.id.flContainer);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLogin(LoginEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        super.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    /* JADX INFO: compiled from: LoginActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/tv/LoginActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "addAccount", "", "startAfterSignOut", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void start(Context context) {
            start$default(this, context, false, 2, null);
        }

        private Companion() {
        }

        public static /* synthetic */ void start$default(Companion companion, Context context, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            companion.start(context, z);
        }

        public final void start(Context context, boolean addAccount) {
            Intent intent = new Intent(context, (Class<?>) LoginActivity.class);
            intent.putExtra("addAccount", addAccount);
            if (context != null) {
                context.startActivity(intent);
            }
        }

        public final void startAfterSignOut(Context context) {
            if (context == null) {
                return;
            }
            Intent intent = new Intent(context.getApplicationContext(), (Class<?>) LoginActivity.class);
            intent.putExtra("addAccount", false);
            intent.addFlags(268468224);
            context.getApplicationContext().startActivity(intent);
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        }
    }
}

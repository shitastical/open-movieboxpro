package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.adorkable.iosdialog.R;
import kotlin.Metadata;

/* JADX INFO: compiled from: VideoFilterDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VideoFilterDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VideoFilterDialogFragment extends DialogFragment {
    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.DialogTopShowStyle);
    }
}

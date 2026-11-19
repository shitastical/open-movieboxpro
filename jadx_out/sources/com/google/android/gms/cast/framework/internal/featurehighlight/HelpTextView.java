package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.internal.cast.zzfo;

/* JADX INFO: loaded from: classes4.dex */
public class HelpTextView extends LinearLayout implements zzi {
    private TextView zzkq;
    private TextView zzkr;

    public HelpTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.google.android.gms.cast.framework.internal.featurehighlight.zzi
    public View asView() {
        return this;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.zzkq = (TextView) zzfo.checkNotNull((TextView) findViewById(R.id.cast_featurehighlight_help_text_header_view));
        this.zzkr = (TextView) zzfo.checkNotNull((TextView) findViewById(R.id.cast_featurehighlight_help_text_body_view));
    }

    @Override // com.google.android.gms.cast.framework.internal.featurehighlight.zzi
    public void setText(CharSequence charSequence, CharSequence charSequence2) {
        zza(this.zzkq, charSequence);
        zza(this.zzkr, charSequence2);
    }

    private static void zza(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        textView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }
}

package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.internal.cast.zzfo;

/* JADX INFO: loaded from: classes4.dex */
final class zzj {
    private final Rect zzky = new Rect();
    private final int zzkz;
    private final int zzla;
    private final int zzlb;
    private final int zzlc;
    private final zza zzld;

    zzj(zza zzaVar) {
        this.zzld = (zza) zzfo.checkNotNull(zzaVar);
        Resources resources = zzaVar.getResources();
        this.zzkz = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_inner_radius);
        this.zzla = resources.getDimensionPixelOffset(R.dimen.cast_libraries_material_featurehighlight_inner_margin);
        this.zzlb = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_text_max_width);
        this.zzlc = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_text_horizontal_offset);
    }

    final void zza(Rect rect, Rect rect2) {
        View viewZzbc = this.zzld.zzbc();
        if (rect.isEmpty() || rect2.isEmpty()) {
            viewZzbc.layout(0, 0, 0, 0);
        } else {
            int iCenterY = rect.centerY();
            int iCenterX = rect.centerX();
            boolean z = iCenterY < rect2.centerY();
            int iMax = Math.max(this.zzkz * 2, rect.height()) / 2;
            int i = this.zzla;
            int i2 = iCenterY + iMax + i;
            if (z) {
                zza(viewZzbc, rect2.width(), rect2.bottom - i2);
                int iZza = zza(viewZzbc, rect2.left, rect2.right, viewZzbc.getMeasuredWidth(), iCenterX);
                viewZzbc.layout(iZza, i2, viewZzbc.getMeasuredWidth() + iZza, viewZzbc.getMeasuredHeight() + i2);
            } else {
                int i3 = (iCenterY - iMax) - i;
                zza(viewZzbc, rect2.width(), i3 - rect2.top);
                int iZza2 = zza(viewZzbc, rect2.left, rect2.right, viewZzbc.getMeasuredWidth(), iCenterX);
                viewZzbc.layout(iZza2, i3 - viewZzbc.getMeasuredHeight(), viewZzbc.getMeasuredWidth() + iZza2, i3);
            }
        }
        this.zzky.set(viewZzbc.getLeft(), viewZzbc.getTop(), viewZzbc.getRight(), viewZzbc.getBottom());
        this.zzld.zzbd().zzb(rect, this.zzky);
        this.zzld.zzbe().zza(rect);
    }

    private final int zza(View view, int i, int i2, int i3, int i4) {
        int i5;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i6 = i3 / 2;
        if (i4 - i <= i2 - i4) {
            i5 = (i4 - i6) + this.zzlc;
        } else {
            i5 = (i4 - i6) - this.zzlc;
        }
        if (i5 - marginLayoutParams.leftMargin < i) {
            return i + marginLayoutParams.leftMargin;
        }
        return (i5 + i3) + marginLayoutParams.rightMargin > i2 ? (i2 - i3) - marginLayoutParams.rightMargin : i5;
    }

    private final void zza(View view, int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(View.MeasureSpec.makeMeasureSpec(Math.min((i - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin, this.zzlb), 1073741824), View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE));
    }
}

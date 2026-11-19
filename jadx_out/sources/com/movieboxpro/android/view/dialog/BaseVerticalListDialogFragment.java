package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.exifinterface.media.ExifInterface;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.VerticalListDialogBinding;
import com.owen.focus.FocusBorder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseVerticalListDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001dH&J\u001d\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00028\u0000H&¢\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u001dH&J\b\u0010$\u001a\u00020\u0012H&J\u0016\u0010%\u001a\u00020\u00122\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R&\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\tX\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/movieboxpro/android/view/dialog/BaseVerticalListDialogFragment;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/VerticalListDialogBinding;", "<init>", "()V", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "getAdapter", "()Lcom/chad/library/adapter/base/BaseQuickAdapter;", "setAdapter", "(Lcom/chad/library/adapter/base/BaseQuickAdapter;)V", "list", "", "onStart", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "focusScale", "v", "scale", "", "setItemLayout", "", "initItemView", "helper", "item", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;)V", "onItemClicked", "position", "initData", "setData", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class BaseVerticalListDialogFragment<T> extends BaseTransparentDialogFragment<VerticalListDialogBinding> {
    protected BaseQuickAdapter<T, BaseViewHolder> adapter;
    private List<? extends T> list;
    private FocusBorder mFocusBorder;

    public abstract void initData();

    public abstract void initItemView(BaseViewHolder helper, T item);

    public abstract void onItemClicked(int position);

    public abstract int setItemLayout();

    public BaseVerticalListDialogFragment() {
        super(R.layout.vertical_list_dialog);
        this.list = new ArrayList();
    }

    protected final BaseQuickAdapter<T, BaseViewHolder> getAdapter() {
        BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter != null) {
            return baseQuickAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    protected final void setAdapter(BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<set-?>");
        this.adapter = baseQuickAdapter;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
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
            attributes.dimAmount = 0.95f;
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        setAdapter(new BaseQuickAdapter<T, BaseViewHolder>(this, setItemLayout(), new ArrayList(this.list)) { // from class: com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment.onViewCreated.1
            final /* synthetic */ BaseVerticalListDialogFragment<T> this$0;

            {
                this.this$0 = this;
                ArrayList<T> arrayList = arrayList;
            }

            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            protected void convert(BaseViewHolder helper, T item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                this.this$0.initItemView(helper, item);
            }
        });
        getBinding().recyclerView.setSelectedItemAtCentered(true);
        getBinding().recyclerView.setAdapter(getAdapter());
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                BaseVerticalListDialogFragment.onViewCreated$lambda$1(this.f$0, baseQuickAdapter, view2, i);
            }
        });
        initData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(BaseVerticalListDialogFragment baseVerticalListDialogFragment, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        baseVerticalListDialogFragment.onItemClicked(i);
        baseVerticalListDialogFragment.dismiss();
    }

    private final void focusScale(View v, float scale) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(scale, scale, 0.0f));
    }

    public void setData(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.list = list;
    }
}

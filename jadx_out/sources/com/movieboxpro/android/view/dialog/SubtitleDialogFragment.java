package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dl7.player.model.SRTModel;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentSubtitleBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SubtitleDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001#B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0010J6\u0010\u0019\u001a\u00020\u00172\u001a\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\r\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0007J\u001a\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0017H\u0002J\b\u0010 \u001a\u00020\u0017H\u0002J\u0010\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0007H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\r\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentSubtitleBinding;", "<init>", "()V", "languageAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "subtitleAdapter", "Lcom/dl7/player/model/SRTModel$subTitles;", "subtitles", "Ljava/util/HashMap;", "", "selectSubtitles", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment$OnSubtitleSelectListener;", "lastSelectPos", "", "lastSelectLanguage", BreakpointSQLiteKey.FILENAME, "filenameNoExt", "onStart", "", "setListener", "setSubtitles", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initListener", "initData", "getRealName", "name", "OnSubtitleSelectListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SubtitleDialogFragment extends BaseTransparentDialogFragment<FragmentSubtitleBinding> {
    private String filename;
    private String filenameNoExt;
    private BaseQuickAdapter<String, BaseViewHolder> languageAdapter;
    private String lastSelectLanguage;
    private int lastSelectPos;
    private OnSubtitleSelectListener listener;
    private SRTModel.subTitles selectSubtitles;
    private BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder> subtitleAdapter;
    private HashMap<String, List<SRTModel.subTitles>> subtitles;

    /* JADX INFO: compiled from: SubtitleDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment$OnSubtitleSelectListener;", "", "onSubtitleSelect", "", "subtitle", "Lcom/dl7/player/model/SRTModel$subTitles;", "onOpenSubtitle", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnSubtitleSelectListener {
        void onOpenSubtitle();

        void onSubtitleSelect(SRTModel.subTitles subtitle);
    }

    public SubtitleDialogFragment() {
        super(R.layout.fragment_subtitle);
        this.lastSelectPos = -1;
        this.filename = "";
        this.filenameNoExt = "";
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (((double) getResources().getDisplayMetrics().widthPixels) * 0.7d);
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.3f;
            window.setAttributes(attributes);
        }
    }

    public final void setListener(OnSubtitleSelectListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    public final void setSubtitles(HashMap<String, List<SRTModel.subTitles>> subtitles, SRTModel.subTitles selectSubtitles, String filename) {
        this.subtitles = subtitles;
        if (subtitles != null) {
            Intrinsics.checkNotNull(subtitles);
            Iterator<Map.Entry<String, List<SRTModel.subTitles>>> it = subtitles.entrySet().iterator();
            while (it.hasNext()) {
                Iterator<T> it2 = it.next().getValue().iterator();
                while (it2.hasNext()) {
                    ((SRTModel.subTitles) it2.next()).setSelect(false);
                }
            }
        }
        this.selectSubtitles = selectSubtitles;
        if (filename == null) {
            filename = "";
        }
        this.filename = filename;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.dialog.SubtitleDialogFragment$initListener$1, reason: invalid class name */
    /* JADX INFO: compiled from: SubtitleDialogFragment.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J$\u0010\n\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J$\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"com/movieboxpro/android/view/dialog/SubtitleDialogFragment$initListener$1", "Lcom/owen/tvrecyclerview/widget/TvRecyclerView$OnItemListener;", "onItemSelected", "", "parent", "Lcom/owen/tvrecyclerview/widget/TvRecyclerView;", "itemView", "Landroid/view/View;", "position", "", "onItemPreSelected", "onItemClick", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AnonymousClass1 implements TvRecyclerView.OnItemListener {
        @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
        public void onItemClick(TvRecyclerView parent, View itemView, int position) {
        }

        @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
        public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
        }

        AnonymousClass1() {
        }

        @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
        public void onItemSelected(TvRecyclerView parent, View itemView, final int position) {
            TvRecyclerView tvRecyclerView = SubtitleDialogFragment.this.getBinding().rvSubtitle;
            final SubtitleDialogFragment subtitleDialogFragment = SubtitleDialogFragment.this;
            tvRecyclerView.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.SubtitleDialogFragment$initListener$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SubtitleDialogFragment.AnonymousClass1.onItemSelected$lambda$0(subtitleDialogFragment, position);
                }
            }, 100L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onItemSelected$lambda$0(SubtitleDialogFragment subtitleDialogFragment, int i) {
            BaseQuickAdapter baseQuickAdapter = subtitleDialogFragment.subtitleAdapter;
            List list = null;
            BaseQuickAdapter baseQuickAdapter2 = null;
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter = null;
            }
            HashMap map = subtitleDialogFragment.subtitles;
            if (map != null) {
                BaseQuickAdapter baseQuickAdapter3 = subtitleDialogFragment.languageAdapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
                } else {
                    baseQuickAdapter2 = baseQuickAdapter3;
                }
                list = (List) map.get(baseQuickAdapter2.getItem(i));
            }
            baseQuickAdapter.setList(list);
        }
    }

    private final void initListener() {
        getBinding().rvLanguage.setOnItemListener(new AnonymousClass1());
        BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.SubtitleDialogFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                SubtitleDialogFragment.initListener$lambda$3(this.f$0, baseQuickAdapter2, view, i);
            }
        });
        getBinding().tvOpenSubtitle.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.SubtitleDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitleDialogFragment.initListener$lambda$4(this.f$0, view);
            }
        });
        getBinding().tvOpenSubtitle.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.dialog.SubtitleDialogFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return SubtitleDialogFragment.initListener$lambda$5(this.f$0, view, i, keyEvent);
            }
        });
        getBinding().tvOpenSubtitle.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.SubtitleDialogFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                SubtitleDialogFragment.initListener$lambda$7(this.f$0, view, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(SubtitleDialogFragment subtitleDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder> baseQuickAdapter2 = subtitleDialogFragment.subtitleAdapter;
        BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder> baseQuickAdapter3 = null;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter2 = null;
        }
        SRTModel.subTitles item = baseQuickAdapter2.getItem(i);
        if (Intrinsics.areEqual(item.language, subtitleDialogFragment.lastSelectLanguage)) {
            BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder> baseQuickAdapter4 = subtitleDialogFragment.subtitleAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter4 = null;
            }
            SRTModel.subTitles itemOrNull = baseQuickAdapter4.getItemOrNull(subtitleDialogFragment.lastSelectPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder> baseQuickAdapter5 = subtitleDialogFragment.subtitleAdapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter5 = null;
            }
            baseQuickAdapter5.notifyItemChanged(subtitleDialogFragment.lastSelectPos);
        }
        BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder> baseQuickAdapter6 = subtitleDialogFragment.subtitleAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter6 = null;
        }
        baseQuickAdapter6.getItem(i).setSelect(true);
        BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder> baseQuickAdapter7 = subtitleDialogFragment.subtitleAdapter;
        if (baseQuickAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
        } else {
            baseQuickAdapter3 = baseQuickAdapter7;
        }
        baseQuickAdapter3.notifyItemChanged(i);
        OnSubtitleSelectListener onSubtitleSelectListener = subtitleDialogFragment.listener;
        if (onSubtitleSelectListener != null) {
            onSubtitleSelectListener.onSubtitleSelect(item);
        }
        subtitleDialogFragment.lastSelectLanguage = item.language;
        subtitleDialogFragment.lastSelectPos = i;
        subtitleDialogFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4(SubtitleDialogFragment subtitleDialogFragment, View view) {
        OnSubtitleSelectListener onSubtitleSelectListener = subtitleDialogFragment.listener;
        if (onSubtitleSelectListener != null) {
            onSubtitleSelectListener.onOpenSubtitle();
        }
        subtitleDialogFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$5(SubtitleDialogFragment subtitleDialogFragment, View view, int i, KeyEvent keyEvent) {
        if (i != 22 || keyEvent.getAction() != 0) {
            return false;
        }
        OnSubtitleSelectListener onSubtitleSelectListener = subtitleDialogFragment.listener;
        if (onSubtitleSelectListener != null) {
            onSubtitleSelectListener.onOpenSubtitle();
        }
        subtitleDialogFragment.dismiss();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7(final SubtitleDialogFragment subtitleDialogFragment, View view, boolean z) {
        if (z) {
            subtitleDialogFragment.getBinding().tvOpenSubtitle.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.SubtitleDialogFragment$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    SubtitleDialogFragment.initListener$lambda$7$lambda$6(this.f$0);
                }
            }, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7$lambda$6(SubtitleDialogFragment subtitleDialogFragment) {
        BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder> baseQuickAdapter = subtitleDialogFragment.subtitleAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setList(new ArrayList());
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r6v19, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r6v25, types: [T, java.util.ArrayList] */
    private final void initData() {
        ArrayList<String> arrayList = new ArrayList();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        final Ref.IntRef intRef = new Ref.IntRef();
        HashMap<String, List<SRTModel.subTitles>> map = this.subtitles;
        if (map != null) {
            Iterator<Map.Entry<String, List<SRTModel.subTitles>>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getKey());
            }
            SRTModel.subTitles subtitles = this.selectSubtitles;
            if (subtitles == null) {
                if (!arrayList.isEmpty()) {
                    List<SRTModel.subTitles> arrayList2 = map.get(arrayList.get(0));
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList<>();
                    }
                    objectRef.element = new ArrayList(arrayList2);
                    if (!((Collection) objectRef.element).isEmpty()) {
                        this.lastSelectLanguage = (String) arrayList.get(0);
                        this.lastSelectPos = -1;
                    }
                }
            } else {
                Intrinsics.checkNotNull(subtitles);
                ArrayList arrayList3 = map.get(subtitles.language);
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList();
                }
                for (SRTModel.subTitles subtitles2 : arrayList3) {
                    String str = subtitles2.sid;
                    SRTModel.subTitles subtitles3 = this.selectSubtitles;
                    Intrinsics.checkNotNull(subtitles3);
                    if (Intrinsics.areEqual(str, subtitles3.sid)) {
                        subtitles2.setSelect(true);
                        break;
                    }
                }
                int i = 0;
                for (String str2 : arrayList) {
                    int i2 = i + 1;
                    SRTModel.subTitles subtitles4 = this.selectSubtitles;
                    Intrinsics.checkNotNull(subtitles4);
                    if (Intrinsics.areEqual(str2, subtitles4.language)) {
                        List<SRTModel.subTitles> arrayList4 = map.get(str2);
                        if (arrayList4 == null) {
                            arrayList4 = new ArrayList<>();
                        }
                        objectRef.element = new ArrayList(arrayList4);
                        intRef.element = i;
                        this.lastSelectPos = i;
                        this.lastSelectLanguage = str2;
                        break;
                    }
                    i = i2;
                }
            }
        }
        this.languageAdapter = new BaseQuickAdapter<String, BaseViewHolder>(arrayList, R.layout.adapter_subtitle_language_item) { // from class: com.movieboxpro.android.view.dialog.SubtitleDialogFragment.initData.2
            {
                super(i, arrayList);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, String item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                helper.setText(R.id.textView, item);
            }
        };
        TvRecyclerView rvLanguage = getBinding().rvLanguage;
        Intrinsics.checkNotNullExpressionValue(rvLanguage, "rvLanguage");
        CommonExtKt.disableRefreshAnimation(rvLanguage);
        TvRecyclerView tvRecyclerView = getBinding().rvLanguage;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = this.languageAdapter;
        BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
        getBinding().rvLanguage.setSelectedItemAtCentered(true);
        if (intRef.element != 0) {
            getBinding().rvLanguage.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.SubtitleDialogFragment$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    SubtitleDialogFragment.initData$lambda$10(this.f$0, intRef);
                }
            }, 500L);
        }
        this.subtitleAdapter = new BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder>(objectRef, this, R.layout.adapter_choose_subtitle_item) { // from class: com.movieboxpro.android.view.dialog.SubtitleDialogFragment.initData.4
            final /* synthetic */ SubtitleDialogFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(i, objectRef.element);
                this.this$0 = this;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, SRTModel.subTitles item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                SubtitleDialogFragment subtitleDialogFragment = this.this$0;
                TextView textView = (TextView) helper.getView(R.id.tvName);
                String file_path = item.getFile_path();
                Intrinsics.checkNotNullExpressionValue(file_path, "getFile_path(...)");
                String file_path2 = item.getFile_path();
                Intrinsics.checkNotNullExpressionValue(file_path2, "getFile_path(...)");
                String strSubstring = file_path.substring(StringsKt.lastIndexOf$default((CharSequence) file_path2, "/", 0, false, 6, (Object) null) + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                textView.setText(strSubstring);
                helper.setText(R.id.tvSelectedNum, item.count + " selected");
                ImageView imageView = (ImageView) helper.getView(R.id.ivSelected);
                String fileNameNoExtension = FileUtils.getFileNameNoExtension(strSubstring);
                if (fileNameNoExtension == null) {
                    fileNameNoExtension = "";
                }
                Locale ENGLISH = Locale.ENGLISH;
                Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
                String lowerCase = fileNameNoExtension.toLowerCase(ENGLISH);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String str3 = subtitleDialogFragment.filenameNoExt;
                Locale ENGLISH2 = Locale.ENGLISH;
                Intrinsics.checkNotNullExpressionValue(ENGLISH2, "ENGLISH");
                String lowerCase2 = str3.toLowerCase(ENGLISH2);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                if (StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null)) {
                    textView.setTextColor(CommonExtKt.colorInt(R.color.color_main_yellow));
                } else {
                    textView.setTextColor(CommonExtKt.colorInt(R.color.white_87alpha));
                }
                if (item.isSelect()) {
                    CommonExtKt.visible(imageView);
                } else {
                    CommonExtKt.invisible(imageView);
                }
                TextView textView2 = (TextView) helper.getView(R.id.tvTime);
                if (item.getAdd_time() == 0) {
                    CommonExtKt.gone(textView2);
                } else {
                    CommonExtKt.visible(textView2);
                    textView2.setText(TimeUtils.formatTime4(item.getAdd_time() * ((long) 1000)));
                }
                LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.llOriginLanguage);
                if (item.getOrg_subtitle() == null) {
                    CommonExtKt.gone(linearLayout);
                } else {
                    CommonExtKt.visible(linearLayout);
                    helper.setText(R.id.tvOriginLanguage, item.getOrg_subtitle().language);
                }
            }
        };
        TvRecyclerView rvSubtitle = getBinding().rvSubtitle;
        Intrinsics.checkNotNullExpressionValue(rvSubtitle, "rvSubtitle");
        CommonExtKt.disableRefreshAnimation(rvSubtitle);
        TvRecyclerView tvRecyclerView2 = getBinding().rvSubtitle;
        BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder> baseQuickAdapter3 = this.subtitleAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        tvRecyclerView2.setAdapter(baseQuickAdapter2);
        getBinding().rvSubtitle.setSelectedItemAtCentered(true);
        getBinding().rvSubtitle.setMemoryFocus(false);
        getBinding().tvOpenSubtitle.setFocusable(false);
        getBinding().tvOpenSubtitle.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.SubtitleDialogFragment$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                SubtitleDialogFragment.initData$lambda$11(this.f$0);
            }
        }, 500L);
        getBinding().tvFilename.setText(this.filename);
        String fileNameNoExtension = FileUtils.getFileNameNoExtension(this.filename);
        Intrinsics.checkNotNullExpressionValue(fileNameNoExtension, "getFileNameNoExtension(...)");
        this.filenameNoExt = getRealName(fileNameNoExtension);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$10(SubtitleDialogFragment subtitleDialogFragment, Ref.IntRef intRef) {
        subtitleDialogFragment.getBinding().rvLanguage.setSelection(intRef.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$11(SubtitleDialogFragment subtitleDialogFragment) {
        TextView textView = subtitleDialogFragment.getBinding().tvOpenSubtitle;
        if (textView != null) {
            textView.setFocusable(true);
        }
    }

    private final String getRealName(String name) {
        String str = name;
        int length = str.length() - 1;
        if (length < 0) {
            length = -1;
            break;
        }
        while (true) {
            int i = length - 1;
            if (str.charAt(length) == '-') {
                break;
            }
            if (i < 0) {
                length = -1;
                break;
            }
            length = i;
        }
        if (length != -1) {
            String strSubstring = name.substring(0, length);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return strSubstring;
        }
        return "";
    }
}

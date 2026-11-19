package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.adorkable.iosdialog.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.utils.DensityUtils;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListDialog extends AppCompatDialog {
    private Context mContext;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public ListDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
    }

    private void initDialog() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (((double) this.mContext.getResources().getDisplayMetrics().widthPixels) * 0.4d);
        attributes.height = -2;
        window.setAttributes(attributes);
    }

    public static class Builder {
        private Context context;
        private RecyclerView.Adapter mAdapter;
        private OnItemClickListener mOnPositiveListener;
        private List<String> strings;
        private String title = "";
        private boolean cancelable = true;
        private boolean canceledOnTouchOutside = true;
        private boolean isHideTitle = true;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setItems(List<String> strings) {
            this.strings = strings;
            return this;
        }

        public Builder setItems(String[] strings) {
            this.strings = Arrays.asList(strings);
            return this;
        }

        public Builder setTitleVisible(boolean visible) {
            this.isHideTitle = visible;
            return this;
        }

        public Builder setAdapter(RecyclerView.Adapter adapter) {
            this.mAdapter = adapter;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder addAction(OnItemClickListener listener) {
            this.mOnPositiveListener = listener;
            return this;
        }

        public ListDialog create() {
            final ListDialog listDialog = new ListDialog(this.context, R.style.DialogStyle);
            View viewInflate = LayoutInflater.from(this.context).inflate(com.movieboxpro.androidtv.R.layout.list_dialog_layout, (ViewGroup) null);
            listDialog.addContentView(viewInflate, new ViewGroup.LayoutParams(-1, -2));
            View viewFindViewById = viewInflate.findViewById(com.movieboxpro.androidtv.R.id.view_line);
            TextView textView = (TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_title);
            if (this.isHideTitle) {
                textView.setVisibility(8);
                viewFindViewById.setVisibility(8);
            } else {
                textView.setText(this.title);
            }
            TvRecyclerView tvRecyclerView = (TvRecyclerView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.recyclerView);
            RecyclerView.Adapter adapter = this.mAdapter;
            if (adapter != null) {
                tvRecyclerView.setAdapter(adapter);
            } else {
                CommonBaseAdapter<String> commonBaseAdapter = new CommonBaseAdapter<String>(this, com.movieboxpro.androidtv.R.layout.adapter_text_item, this.strings) { // from class: com.movieboxpro.android.view.dialog.ListDialog.Builder.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.chad.library.adapter.base.BaseQuickAdapter
                    public void convert(BaseViewHolder helper, String item) {
                        helper.setText(com.movieboxpro.androidtv.R.id.textView, item);
                    }
                };
                commonBaseAdapter.setOnItemClickListener(new com.chad.library.adapter.base.listener.OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.ListDialog.Builder.2
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public void onItemClick(BaseQuickAdapter<?, ?> adapter2, View view, int position) {
                        listDialog.dismiss();
                        if (Builder.this.mOnPositiveListener != null) {
                            Builder.this.mOnPositiveListener.onClick(view, position);
                        }
                    }
                });
                tvRecyclerView.setSpacingWithMargins(DensityUtils.dp2px(this.context, 10.0f), DensityUtils.dp2px(this.context, 10.0f));
                tvRecyclerView.setAdapter(commonBaseAdapter);
            }
            listDialog.setCancelable(this.cancelable);
            listDialog.setCanceledOnTouchOutside(this.canceledOnTouchOutside);
            return listDialog;
        }
    }
}

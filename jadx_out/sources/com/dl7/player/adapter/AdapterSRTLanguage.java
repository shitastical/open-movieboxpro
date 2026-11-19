package com.dl7.player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dl7.player.R;
import com.dl7.player.media.BaseListAdapter;
import com.dl7.player.model.languageModel;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AdapterSRTLanguage extends BaseListAdapter<languageModel> {
    public AdapterSRTLanguage(Context context) {
        super(context);
    }

    public AdapterSRTLanguage(Context context, List<languageModel> list) {
        super(context, list);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_media_quality, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_media_quality);
        textView.setText(((languageModel) this.mDatas.get(i)).getLanguage());
        textView.setSelected(((languageModel) this.mDatas.get(i)).isSelected());
        return view;
    }

    private void _cleanSelected() {
        for (T t : this.mDatas) {
            if (t.isSelected()) {
                t.setSelected(false);
            }
        }
    }

    public void setMediaLanguage(int i) {
        for (int i2 = 0; i2 < this.mDatas.size(); i2++) {
            if (i2 == i) {
                if (((languageModel) this.mDatas.get(i2)).isSelected()) {
                    return;
                }
                _cleanSelected();
                ((languageModel) this.mDatas.get(i2)).setSelected(true);
                notifyDataSetChanged();
                return;
            }
        }
    }
}

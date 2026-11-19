package com.movieboxpro.android.player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dl7.player.R;
import com.dl7.player.media.BaseListAdapter;
import com.dl7.player.model.languageModel;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AdapterSRTLanguage extends BaseListAdapter<languageModel> {
    public AdapterSRTLanguage(Context context) {
        super(context);
    }

    public AdapterSRTLanguage(Context context, List<languageModel> datas) {
        super(context, datas);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_media_quality, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tv_media_quality);
        textView.setText(((languageModel) this.mDatas.get(position)).getLanguage());
        textView.setSelected(((languageModel) this.mDatas.get(position)).isSelected());
        return convertView;
    }

    private void _cleanSelected() {
        for (T t : this.mDatas) {
            if (t.isSelected()) {
                t.setSelected(false);
            }
        }
    }

    public void setMediaLanguage(int quality) {
        for (int i = 0; i < this.mDatas.size(); i++) {
            if (i == quality) {
                if (((languageModel) this.mDatas.get(i)).isSelected()) {
                    return;
                }
                _cleanSelected();
                ((languageModel) this.mDatas.get(i)).setSelected(true);
                notifyDataSetChanged();
                return;
            }
        }
    }
}

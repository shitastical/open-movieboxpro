package com.movieboxpro.android.player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dl7.player.media.BaseListAdapter;
import com.dl7.player.model.SRTModel;
import com.movieboxpro.androidtv.R;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AdapterMediaQuality extends BaseListAdapter<SRTModel.subTitles> {
    public AdapterMediaQuality(Context context, List<SRTModel.subTitles> datas) {
        super(context, datas);
    }

    public AdapterMediaQuality(Context context) {
        super(context);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_media_srt2, parent, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.adapter_srt_size);
        TextView textView2 = (TextView) view.findViewById(R.id.adapter_srt_selected);
        ImageView imageView = (ImageView) view.findViewById(R.id.adapter_srt_choose);
        textView.setText(((SRTModel.subTitles) this.mDatas.get(i)).getFile_path().substring(((SRTModel.subTitles) this.mDatas.get(i)).getFile_path().lastIndexOf("/") + 1));
        textView2.setVisibility(((SRTModel.subTitles) this.mDatas.get(i)).getCount() != 0 ? 0 : 8);
        textView2.setText(((SRTModel.subTitles) this.mDatas.get(i)).getCount() + "  Selected");
        imageView.setVisibility(((SRTModel.subTitles) this.mDatas.get(i)).isSelect() ? 0 : 8);
        return view;
    }

    public void _cleanSelected() {
        for (T t : this.mDatas) {
            if (t.isSelect()) {
                t.setSelect(false);
            }
        }
    }

    public void setMediaQuality(int quality) {
        for (int i = 0; i < this.mDatas.size(); i++) {
            if (i == quality) {
                if (((SRTModel.subTitles) this.mDatas.get(i)).isSelect()) {
                    return;
                }
                _cleanSelected();
                ((SRTModel.subTitles) this.mDatas.get(i)).setSelect(true);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public float getDely(int quality) {
        for (int i = 0; i < this.mDatas.size(); i++) {
            if (i == quality) {
                return ((SRTModel.subTitles) this.mDatas.get(i)).getDelay();
            }
        }
        return 0.0f;
    }
}

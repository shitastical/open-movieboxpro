package com.dl7.player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.dl7.player.R;
import com.dl7.player.media.BaseListAdapter;
import com.dl7.player.model.SRTModel;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AdapterMediaQuality extends BaseListAdapter<SRTModel.subTitles> {
    public AdapterMediaQuality(Context context, List<SRTModel.subTitles> list) {
        super(context, list);
    }

    public AdapterMediaQuality(Context context) {
        super(context);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_media_srt, viewGroup, false);
        }
        ((ImageView) view.findViewById(R.id.adapter_srt_choose)).setVisibility(((SRTModel.subTitles) this.mDatas.get(i)).isSelect() ? 0 : 8);
        return view;
    }

    public void _cleanSelected() {
        for (T t : this.mDatas) {
            if (t.isSelect()) {
                t.setSelect(false);
            }
        }
    }

    public void setMediaQuality(int i) {
        for (int i2 = 0; i2 < this.mDatas.size(); i2++) {
            if (i2 == i) {
                if (((SRTModel.subTitles) this.mDatas.get(i2)).isSelect()) {
                    return;
                }
                _cleanSelected();
                ((SRTModel.subTitles) this.mDatas.get(i2)).setSelect(true);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public float getDely(int i) {
        for (int i2 = 0; i2 < this.mDatas.size(); i2++) {
            if (i2 == i) {
                return ((SRTModel.subTitles) this.mDatas.get(i2)).getDelay();
            }
        }
        return 0.0f;
    }
}

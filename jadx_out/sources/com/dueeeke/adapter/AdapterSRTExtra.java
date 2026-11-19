package com.dueeeke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dueeeke.model.ExtrModel;
import com.dueeeke.videocontroller.R;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AdapterSRTExtra extends BaseListAdapter<ExtrModel> {
    public AdapterSRTExtra(Context context) {
        super(context);
    }

    public AdapterSRTExtra(Context context, List<ExtrModel> list) {
        super(context, list);
    }

    public AdapterSRTExtra(Context context, ExtrModel[] extrModelArr) {
        super(context, extrModelArr);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_srt_extra, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.srt_extr_name);
        ImageView imageView = (ImageView) view.findViewById(R.id.srt_extr_choose);
        TextView textView2 = (TextView) view.findViewById(R.id.srt_extr_srtname);
        TextView textView3 = (TextView) view.findViewById(R.id.srt_extr_languagename);
        TextView textView4 = (TextView) view.findViewById(R.id.srt_extr_updatename);
        textView.setText(((ExtrModel) this.mDatas.get(i)).getMovieName());
        textView2.setText(((ExtrModel) this.mDatas.get(i)).getSubFileName());
        textView3.setText(((ExtrModel) this.mDatas.get(i)).getLanguageName());
        textView4.setText(((ExtrModel) this.mDatas.get(i)).getSubAddDate());
        imageView.setVisibility(((ExtrModel) this.mDatas.get(i)).isSelect() ? 0 : 8);
        return view;
    }

    public void _cleanSelected() {
        for (T t : this.mDatas) {
            if (t.isSelect()) {
                t.setSelect(false);
            }
        }
    }

    public void setExtrSrt(int i) {
        for (int i2 = 0; i2 < this.mDatas.size(); i2++) {
            if (i2 == i) {
                if (((ExtrModel) this.mDatas.get(i2)).isSelect()) {
                    return;
                }
                _cleanSelected();
                ((ExtrModel) this.mDatas.get(i2)).setSelect(true);
                notifyDataSetChanged();
                return;
            }
        }
    }
}

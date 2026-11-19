package com.dueeeke.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dueeeke.model.SrtPraseModel;
import com.dueeeke.utils.TimeUtils;
import com.dueeeke.videocontroller.R;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AdapterSrtlist extends BaseListAdapter<SrtPraseModel> {
    public AdapterSrtlist(Context context) {
        super(context);
    }

    public AdapterSrtlist(Context context, List<SrtPraseModel> list) {
        super(context, list);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_media_srtlist, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.tv_srt_time_list)).setText(Html.fromHtml(((SrtPraseModel) this.mDatas.get(i)).getSrtBody()));
        TextView textView = (TextView) view.findViewById(R.id.tv_srt_time_start);
        StringBuilder sb = new StringBuilder();
        sb.append(TimeUtils.getTime(((SrtPraseModel) this.mDatas.get(i)).getBeginTime() / 1000));
        textView.setText(sb);
        ((RelativeLayout) view.findViewById(R.id.tv_srt_time_view)).setSelected(((SrtPraseModel) this.mDatas.get(i)).isSelected());
        return view;
    }

    private void _cleanSelected() {
        for (T t : this.mDatas) {
            if (t.isSelected()) {
                t.setSelected(false);
            }
        }
    }

    public void setMediaSrt(int i) {
        for (int i2 = 0; i2 < this.mDatas.size(); i2++) {
            if (i2 == i) {
                if (((SrtPraseModel) this.mDatas.get(i2)).isSelected()) {
                    return;
                }
                _cleanSelected();
                ((SrtPraseModel) this.mDatas.get(i2)).setSelected(true);
                notifyDataSetChanged();
                return;
            }
        }
    }
}

package com.movieboxpro.android.player;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dl7.player.R;
import com.dl7.player.media.BaseListAdapter;
import com.dl7.player.utils.SRT;
import com.dueeeke.utils.TimeUtils;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AdapterSrtlist extends BaseListAdapter<SRT> {
    public AdapterSrtlist(Context context) {
        super(context);
    }

    public AdapterSrtlist(Context context, List<SRT> datas) {
        super(context, datas);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_media_srtlist, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.tv_srt_time_list)).setText(Html.fromHtml(((SRT) this.mDatas.get(position)).getSrtBody()));
        TextView textView = (TextView) convertView.findViewById(R.id.tv_srt_time_start);
        StringBuilder sb = new StringBuilder();
        sb.append(TimeUtils.getTime(((SRT) this.mDatas.get(position)).getBeginTime() / 1000));
        textView.setText(sb);
        ((RelativeLayout) convertView.findViewById(R.id.tv_srt_time_view)).setSelected(((SRT) this.mDatas.get(position)).isSelected());
        return convertView;
    }

    private void _cleanSelected() {
        for (T t : this.mDatas) {
            if (t.isSelected()) {
                t.setSelected(false);
            }
        }
    }

    public void setMediaSrt(int srtSelect) {
        for (int i = 0; i < this.mDatas.size(); i++) {
            if (i == srtSelect) {
                if (((SRT) this.mDatas.get(i)).isSelected()) {
                    return;
                }
                _cleanSelected();
                ((SRT) this.mDatas.get(i)).setSelected(true);
                notifyDataSetChanged();
                return;
            }
        }
    }
}

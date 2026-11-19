package com.dl7.player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dl7.player.R;
import com.dl7.player.media.BaseListAdapter;
import com.dl7.player.model.TrackModel;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AdapterMediaTrack extends BaseListAdapter<TrackModel> {
    public AdapterMediaTrack(Context context) {
        super(context);
    }

    public AdapterMediaTrack(Context context, List<TrackModel> list) {
        super(context, list);
    }

    public AdapterMediaTrack(Context context, TrackModel[] trackModelArr) {
        super(context, trackModelArr);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_track_info, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.srt_track_language);
        TextView textView2 = (TextView) view.findViewById(R.id.srt_track_type);
        TextView textView3 = (TextView) view.findViewById(R.id.srt_track_info);
        ImageView imageView = (ImageView) view.findViewById(R.id.srt_track_choose);
        textView.setText(translate(((TrackModel) this.mDatas.get(i)).getTrackName()));
        textView2.setText(((TrackModel) this.mDatas.get(i)).getTrackType());
        textView3.setText(((TrackModel) this.mDatas.get(i)).getTrackInfo());
        imageView.setVisibility(((TrackModel) this.mDatas.get(i)).isSelect() ? 0 : 4);
        return view;
    }

    private void _cleanSelected() {
        for (T t : this.mDatas) {
            if (t.isSelect()) {
                t.setSelect(false);
            }
        }
    }

    public void setExtrSrt(int i) {
        for (int i2 = 0; i2 < this.mDatas.size(); i2++) {
            if (i2 == i) {
                if (((TrackModel) this.mDatas.get(i2)).isSelect()) {
                    return;
                }
                _cleanSelected();
                ((TrackModel) this.mDatas.get(i2)).setSelect(true);
                notifyDataSetChanged();
                return;
            }
        }
    }

    private String translate(String str) {
        str.hashCode();
        switch (str) {
            case "eng":
                return "English";
            case "ita":
                return "Italy";
            case "spa":
                return "Spain";
            case "und":
                return "unknown";
            default:
                return str;
        }
    }
}

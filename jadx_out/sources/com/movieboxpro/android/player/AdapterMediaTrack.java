package com.movieboxpro.android.player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dl7.player.media.BaseListAdapter;
import com.dl7.player.model.TrackModel;
import com.movieboxpro.androidtv.R;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AdapterMediaTrack extends BaseListAdapter<TrackModel> {
    public AdapterMediaTrack(Context context) {
        super(context);
    }

    public AdapterMediaTrack(Context context, List<TrackModel> datas) {
        super(context, datas);
    }

    public AdapterMediaTrack(Context context, TrackModel[] datas) {
        super(context, datas);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_track_info, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.srt_track_language);
        TextView textView2 = (TextView) view.findViewById(R.id.srt_track_type);
        TextView textView3 = (TextView) view.findViewById(R.id.srt_track_info);
        ImageView imageView = (ImageView) view.findViewById(R.id.srt_track_choose);
        textView.setText(translate(((TrackModel) this.mDatas.get(position)).getTrackName()));
        textView2.setText(((TrackModel) this.mDatas.get(position)).getTrackType());
        textView3.setText(((TrackModel) this.mDatas.get(position)).getTrackInfo());
        imageView.setVisibility(((TrackModel) this.mDatas.get(position)).isSelect() ? 0 : 4);
        return view;
    }

    private void _cleanSelected() {
        for (T t : this.mDatas) {
            if (t.isSelect()) {
                t.setSelect(false);
            }
        }
    }

    public void setExtrSrt(int quality) {
        for (int i = 0; i < this.mDatas.size(); i++) {
            if (i == quality) {
                if (((TrackModel) this.mDatas.get(i)).isSelect()) {
                    return;
                }
                _cleanSelected();
                ((TrackModel) this.mDatas.get(i)).setSelect(true);
                notifyDataSetChanged();
                return;
            }
        }
        notifyDataSetChanged();
    }

    private String translate(String country) {
        country.hashCode();
        switch (country) {
            case "eng":
                return "English";
            case "ita":
                return "Italy";
            case "spa":
                return "Spain";
            case "und":
                return "unknown";
            default:
                return country;
        }
    }
}

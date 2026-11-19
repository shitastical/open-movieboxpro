package com.dl7.player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.dl7.player.R;
import com.dl7.player.media.BaseListAdapter;
import com.dl7.player.model.MediaQualityInfo;

/* JADX INFO: loaded from: classes4.dex */
public class AdapterMediaQualitys extends BaseListAdapter<MediaQualityInfo> {
    public AdapterMediaQualitys(Context context) {
        super(context);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:14:0x00d8  */
    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_media_qualitys, viewGroup, false);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.adapter_quality_quality);
        TextView textView = (TextView) view.findViewById(R.id.adapter_quality_size);
        TextView textView2 = (TextView) view.findViewById(R.id.adapter_quality_time);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.adapter_quality_choose);
        TextView textView3 = (TextView) view.findViewById(R.id.adapter_quality_selected);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.adapter_quality_vip);
        TextView textView4 = (TextView) view.findViewById(R.id.adapter_quality_name);
        textView.setText(((MediaQualityInfo) this.mDatas.get(i)).getSize());
        textView2.setText(((MediaQualityInfo) this.mDatas.get(i)).getTime());
        textView4.setText(((MediaQualityInfo) this.mDatas.get(i)).getTitle());
        imageView3.setVisibility(((MediaQualityInfo) this.mDatas.get(i)).getIsVip().equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE) ? 0 : 4);
        imageView2.setVisibility(((MediaQualityInfo) this.mDatas.get(i)).isSelect() ? 0 : 4);
        textView3.setText(((MediaQualityInfo) this.mDatas.get(i)).getSelected() + "Selected");
        String quality = ((MediaQualityInfo) this.mDatas.get(i)).getQuality();
        quality.hashCode();
        switch (quality) {
            case "360p":
                imageView.setImageResource(R.mipmap.ic_choose_sd);
                return view;
            case "720p":
                imageView.setImageResource(R.mipmap.ic_choose_hd);
                return view;
            case "1080p":
                imageView.setImageResource(R.mipmap.ic_choose_fullhd);
                return view;
            default:
                return view;
        }
    }

    private void _cleanSelected() {
        for (T t : this.mDatas) {
            if (t.isSelect()) {
                t.setSelect(false);
            }
        }
    }

    public void setMediaQuality(int i) {
        for (T t : this.mDatas) {
            if (t.getIndex() == i) {
                if (t.isSelect()) {
                    return;
                }
                _cleanSelected();
                t.setSelect(true);
                notifyDataSetChanged();
                return;
            }
        }
    }
}

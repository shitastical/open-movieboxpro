package com.movieboxpro.android.player;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.dl7.player.R;
import com.dl7.player.media.BaseListAdapter;

/* JADX INFO: loaded from: classes5.dex */
public class AdapterMediaQualitys extends BaseListAdapter<MediaQualityInfo> {
    public AdapterMediaQualitys(Context context) {
        super(context);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:19:0x00c1  */
    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_media_qualitys2, parent, false);
        }
        MediaQualityInfo mediaQualityInfo = (MediaQualityInfo) this.mDatas.get(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.adapter_quality_quality);
        TextView textView = (TextView) convertView.findViewById(R.id.adapter_quality_size);
        TextView textView2 = (TextView) convertView.findViewById(R.id.adapter_quality_time);
        ImageView imageView2 = (ImageView) convertView.findViewById(R.id.adapter_quality_choose);
        TextView textView3 = (TextView) convertView.findViewById(R.id.adapter_quality_selected);
        ImageView imageView3 = (ImageView) convertView.findViewById(R.id.adapter_quality_vip);
        TextView textView4 = (TextView) convertView.findViewById(R.id.adapter_quality_name);
        ImageView imageView4 = (ImageView) convertView.findViewById(R.id.adapter_quality_fromat);
        textView.setText(mediaQualityInfo.getSize());
        textView2.setText(mediaQualityInfo.getTime());
        textView4.setText(mediaQualityInfo.getTitle());
        imageView3.setVisibility(mediaQualityInfo.getIsVip().equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE) ? 0 : 4);
        imageView2.setVisibility(mediaQualityInfo.isSelect() ? 0 : 4);
        textView3.setText(mediaQualityInfo.getSelected() + "Selected");
        imageView4.setImageResource(mediaQualityInfo.getH265() == 1 ? R.mipmap.ic_choose_fromat : R.mipmap.ic_choose_fromat2);
        String real_quality = mediaQualityInfo.getReal_quality();
        real_quality.hashCode();
        switch (real_quality) {
            case "4K":
                imageView.setImageResource(com.dueeeke.videocontroller.R.mipmap.ic_choose_4k);
                break;
            case "8K":
                imageView.setImageResource(com.movieboxpro.androidtv.R.mipmap.ic_choose_8k);
                break;
            case "org":
                imageView.setImageResource(com.movieboxpro.androidtv.R.mipmap.ic_origin_rate);
                break;
            case "360p":
                imageView.setImageResource(R.mipmap.ic_choose_sd);
                break;
            case "720p":
                imageView.setImageResource(R.mipmap.ic_choose_hd);
                break;
            case "1080p":
                imageView.setImageResource(R.mipmap.ic_choose_fullhd);
                break;
        }
        TextView textView5 = (TextView) convertView.findViewById(R.id.tv_rate);
        if (TextUtils.isEmpty(mediaQualityInfo.getBitstream())) {
            textView5.setVisibility(8);
        } else {
            textView5.setVisibility(0);
            textView5.setText(mediaQualityInfo.getBitstream());
        }
        return convertView;
    }

    private void _cleanSelected() {
        for (T t : this.mDatas) {
            if (t.isSelect()) {
                t.setSelect(false);
            }
        }
    }

    public void setMediaQuality(int quality) {
        for (T t : this.mDatas) {
            if (t.getIndex() == quality) {
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

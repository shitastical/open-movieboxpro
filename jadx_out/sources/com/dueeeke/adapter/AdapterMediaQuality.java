package com.dueeeke.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dueeeke.model.SRTModel;
import com.dueeeke.videocontroller.R;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AdapterMediaQuality extends BaseListAdapter<SRTModel.SubTitles> {
    private OnViewSubtitleClickListener listener;

    public interface OnViewSubtitleClickListener {
        void onViewSubtitleClick(int i);
    }

    public AdapterMediaQuality(Context context, List<SRTModel.SubTitles> list) {
        super(context, list);
    }

    public AdapterMediaQuality(Context context) {
        super(context);
    }

    public void setListener(OnViewSubtitleClickListener onViewSubtitleClickListener) {
        this.listener = onViewSubtitleClickListener;
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_media_srt, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_name);
        ImageView imageView = (ImageView) view.findViewById(R.id.adapter_srt_choose);
        ((ImageView) view.findViewById(R.id.iv_view_subtitle)).setOnClickListener(new View.OnClickListener() { // from class: com.dueeeke.adapter.AdapterMediaQuality.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (AdapterMediaQuality.this.listener != null) {
                    AdapterMediaQuality.this.listener.onViewSubtitleClick(i);
                }
            }
        });
        Html.ImageGetter imageGetter = new Html.ImageGetter() { // from class: com.dueeeke.adapter.AdapterMediaQuality.2
            @Override // android.text.Html.ImageGetter
            public Drawable getDrawable(String str) {
                Drawable drawable = AdapterMediaQuality.this.mContext.getResources().getDrawable(Integer.parseInt(str));
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }
        };
        if (textView.getText() != null && ((SRTModel.SubTitles) this.mDatas.get(i)).getAdmin_order() > 0) {
            textView.setText(Html.fromHtml("<img src=\"" + R.drawable.ic_player_priorit + "\">  " + ((SRTModel.SubTitles) this.mDatas.get(i)).getFile_path().substring(((SRTModel.SubTitles) this.mDatas.get(i)).getFile_path().lastIndexOf("/") + 1), imageGetter, null));
        } else {
            textView.setText(((SRTModel.SubTitles) this.mDatas.get(i)).getFile_path().substring(((SRTModel.SubTitles) this.mDatas.get(i)).getFile_path().lastIndexOf("/") + 1));
        }
        imageView.setVisibility(((SRTModel.SubTitles) this.mDatas.get(i)).isSelect() ? 0 : 8);
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
                if (((SRTModel.SubTitles) this.mDatas.get(i2)).isSelect()) {
                    return;
                }
                _cleanSelected();
                ((SRTModel.SubTitles) this.mDatas.get(i2)).setSelect(true);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public String getDely(int i) {
        for (int i2 = 0; i2 < this.mDatas.size(); i2++) {
            if (i2 == i) {
                return ((SRTModel.SubTitles) this.mDatas.get(i2)).getDelay();
            }
        }
        return "0";
    }
}

package com.google.android.gms.cast.framework.media;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbf extends ArrayAdapter<MediaTrack> implements View.OnClickListener {
    private final Context zzix;
    private int zzqp;

    public zzbf(Context context, List<MediaTrack> list, int i) {
        super(context, R.layout.cast_tracks_chooser_dialog_row_layout, list == null ? new ArrayList<>() : list);
        this.zzix = context;
        this.zzqp = i;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        zzbh zzbhVar;
        String string;
        if (view == null) {
            view = ((LayoutInflater) this.zzix.getSystemService("layout_inflater")).inflate(R.layout.cast_tracks_chooser_dialog_row_layout, viewGroup, false);
            zzbhVar = new zzbh(this, (TextView) view.findViewById(R.id.text), (RadioButton) view.findViewById(R.id.radio));
            view.setTag(zzbhVar);
        } else {
            zzbhVar = (zzbh) view.getTag();
        }
        if (zzbhVar == null) {
            return null;
        }
        zzbhVar.zzqr.setTag(Integer.valueOf(i));
        zzbhVar.zzqr.setChecked(this.zzqp == i);
        view.setOnClickListener(this);
        MediaTrack item = getItem(i);
        String name = item.getName();
        if (!TextUtils.isEmpty(name)) {
            string = name;
        } else if (item.getSubtype() == 2) {
            string = this.zzix.getString(R.string.cast_tracks_chooser_dialog_closed_captions);
        } else {
            if (!TextUtils.isEmpty(item.getLanguage())) {
                String displayLanguage = MediaUtils.getTrackLanguage(item).getDisplayLanguage();
                if (!TextUtils.isEmpty(displayLanguage)) {
                    string = displayLanguage;
                }
            }
            name = this.zzix.getString(R.string.cast_tracks_chooser_dialog_default_track_name, Integer.valueOf(i + 1));
            string = name;
        }
        zzbhVar.zzqq.setText(string);
        return view;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zzqp = ((Integer) ((zzbh) view.getTag()).zzqr.getTag()).intValue();
        notifyDataSetChanged();
    }

    public final MediaTrack zzcy() {
        int i = this.zzqp;
        if (i < 0 || i >= getCount()) {
            return null;
        }
        return getItem(this.zzqp);
    }
}

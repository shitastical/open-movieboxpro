package com.google.android.gms.cast.framework.media;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import androidx.fragment.app.DialogFragment;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class TracksChooserDialogFragment extends DialogFragment {
    private long[] zzds;
    private RemoteMediaClient zzid;
    private boolean zzqg;
    private List<MediaTrack> zzqh;
    private List<MediaTrack> zzqi;
    private Dialog zzqj;
    private MediaInfo zzqk;
    private long[] zzql;

    @Deprecated
    public TracksChooserDialogFragment() {
    }

    private TracksChooserDialogFragment(MediaInfo mediaInfo, long[] jArr) {
        this.zzqk = mediaInfo;
        this.zzql = jArr;
    }

    @Deprecated
    public static TracksChooserDialogFragment newInstance(MediaInfo mediaInfo, long[] jArr) {
        return new TracksChooserDialogFragment(mediaInfo, jArr);
    }

    public static TracksChooserDialogFragment newInstance() {
        return new TracksChooserDialogFragment();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzqg = true;
        this.zzqi = new ArrayList();
        this.zzqh = new ArrayList();
        this.zzds = new long[0];
        CastSession currentCastSession = CastContext.getSharedInstance(getContext()).getSessionManager().getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            this.zzqg = false;
            return;
        }
        RemoteMediaClient remoteMediaClient = currentCastSession.getRemoteMediaClient();
        this.zzid = remoteMediaClient;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || this.zzid.getMediaInfo() == null) {
            this.zzqg = false;
            return;
        }
        long[] jArr = this.zzql;
        if (jArr != null) {
            this.zzds = jArr;
        } else {
            MediaStatus mediaStatus = this.zzid.getMediaStatus();
            if (mediaStatus != null) {
                this.zzds = mediaStatus.getActiveTrackIds();
            }
        }
        MediaInfo mediaInfo = this.zzqk;
        if (mediaInfo == null) {
            mediaInfo = this.zzid.getMediaInfo();
        }
        if (mediaInfo == null) {
            this.zzqg = false;
            return;
        }
        List<MediaTrack> mediaTracks = mediaInfo.getMediaTracks();
        if (mediaTracks == null) {
            this.zzqg = false;
            return;
        }
        this.zzqi = zza(mediaTracks, 2);
        ArrayList<MediaTrack> arrayListZza = zza(mediaTracks, 1);
        this.zzqh = arrayListZza;
        if (arrayListZza.isEmpty()) {
            return;
        }
        this.zzqh.add(0, new MediaTrack.Builder(-1L, 1).setName(getActivity().getString(R.string.cast_tracks_chooser_dialog_none)).setSubtype(2).setContentId("").build());
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        int iZza = zza(this.zzqh, this.zzds, 0);
        int iZza2 = zza(this.zzqi, this.zzds, -1);
        zzbf zzbfVar = new zzbf(getActivity(), this.zzqh, iZza);
        zzbf zzbfVar2 = new zzbf(getActivity(), this.zzqi, iZza2);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View viewInflate = getActivity().getLayoutInflater().inflate(R.layout.cast_tracks_chooser_dialog_layout, (ViewGroup) null);
        ListView listView = (ListView) viewInflate.findViewById(R.id.text_list_view);
        ListView listView2 = (ListView) viewInflate.findViewById(R.id.audio_list_view);
        TabHost tabHost = (TabHost) viewInflate.findViewById(R.id.tab_host);
        tabHost.setup();
        if (zzbfVar.getCount() == 0) {
            listView.setVisibility(4);
        } else {
            listView.setAdapter((ListAdapter) zzbfVar);
            TabHost.TabSpec tabSpecNewTabSpec = tabHost.newTabSpec("textTab");
            tabSpecNewTabSpec.setContent(R.id.text_list_view);
            tabSpecNewTabSpec.setIndicator(getActivity().getString(R.string.cast_tracks_chooser_dialog_subtitles));
            tabHost.addTab(tabSpecNewTabSpec);
        }
        if (zzbfVar2.getCount() <= 1) {
            listView2.setVisibility(4);
        } else {
            listView2.setAdapter((ListAdapter) zzbfVar2);
            TabHost.TabSpec tabSpecNewTabSpec2 = tabHost.newTabSpec("audioTab");
            tabSpecNewTabSpec2.setContent(R.id.audio_list_view);
            tabSpecNewTabSpec2.setIndicator(getActivity().getString(R.string.cast_tracks_chooser_dialog_audio));
            tabHost.addTab(tabSpecNewTabSpec2);
        }
        builder.setView(viewInflate).setPositiveButton(getActivity().getString(R.string.cast_tracks_chooser_dialog_ok), new zzbe(this, zzbfVar, zzbfVar2)).setNegativeButton(R.string.cast_tracks_chooser_dialog_cancel, new zzbd(this));
        Dialog dialog = this.zzqj;
        if (dialog != null) {
            dialog.cancel();
            this.zzqj = null;
        }
        AlertDialog alertDialogCreate = builder.create();
        this.zzqj = alertDialogCreate;
        return alertDialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzbf zzbfVar, zzbf zzbfVar2) {
        if (!this.zzqg || !this.zzid.hasMediaSession()) {
            zzcx();
            return;
        }
        ArrayList arrayList = new ArrayList();
        MediaTrack mediaTrackZzcy = zzbfVar.zzcy();
        if (mediaTrackZzcy != null && mediaTrackZzcy.getId() != -1) {
            arrayList.add(Long.valueOf(mediaTrackZzcy.getId()));
        }
        MediaTrack mediaTrackZzcy2 = zzbfVar2.zzcy();
        if (mediaTrackZzcy2 != null) {
            arrayList.add(Long.valueOf(mediaTrackZzcy2.getId()));
        }
        long[] jArr = this.zzds;
        if (jArr != null && jArr.length > 0) {
            HashSet hashSet = new HashSet();
            Iterator<MediaTrack> it = this.zzqi.iterator();
            while (it.hasNext()) {
                hashSet.add(Long.valueOf(it.next().getId()));
            }
            Iterator<MediaTrack> it2 = this.zzqh.iterator();
            while (it2.hasNext()) {
                hashSet.add(Long.valueOf(it2.next().getId()));
            }
            for (long j : this.zzds) {
                if (!hashSet.contains(Long.valueOf(j))) {
                    arrayList.add(Long.valueOf(j));
                }
            }
        }
        long[] jArr2 = new long[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            jArr2[i] = ((Long) arrayList.get(i)).longValue();
        }
        Arrays.sort(jArr2);
        this.zzid.setActiveMediaTracks(jArr2);
        zzcx();
    }

    private final void zzcx() {
        Dialog dialog = this.zzqj;
        if (dialog != null) {
            dialog.cancel();
            this.zzqj = null;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    private static ArrayList<MediaTrack> zza(List<MediaTrack> list, int i) {
        ArrayList<MediaTrack> arrayList = new ArrayList<>();
        if (list != null) {
            for (MediaTrack mediaTrack : list) {
                if (mediaTrack.getType() == i) {
                    arrayList.add(mediaTrack);
                }
            }
        }
        return arrayList;
    }

    private static int zza(List<MediaTrack> list, long[] jArr, int i) {
        if (jArr != null && list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                for (long j : jArr) {
                    if (j == list.get(i2).getId()) {
                        return i2;
                    }
                }
            }
        }
        return i;
    }

    static /* synthetic */ Dialog zza(TracksChooserDialogFragment tracksChooserDialogFragment, Dialog dialog) {
        tracksChooserDialogFragment.zzqj = null;
        return null;
    }
}

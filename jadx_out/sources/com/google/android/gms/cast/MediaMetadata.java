package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzeg;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class MediaMetadata extends AbstractSafeParcelable {
    public static final int MEDIA_TYPE_GENERIC = 0;
    public static final int MEDIA_TYPE_MOVIE = 1;
    public static final int MEDIA_TYPE_MUSIC_TRACK = 3;
    public static final int MEDIA_TYPE_PHOTO = 4;
    public static final int MEDIA_TYPE_TV_SHOW = 2;
    public static final int MEDIA_TYPE_USER = 100;
    private final List<WebImage> zzdx;
    private final Bundle zzdy;
    private int zzdz;
    private static final String[] zzdv = {null, "String", SerializerHandler.TYPE_INT, SerializerHandler.TYPE_DOUBLE, "ISO-8601 date String"};
    public static final Parcelable.Creator<MediaMetadata> CREATOR = new zzal();
    public static final String KEY_CREATION_DATE = "com.google.android.gms.cast.metadata.CREATION_DATE";
    public static final String KEY_RELEASE_DATE = "com.google.android.gms.cast.metadata.RELEASE_DATE";
    public static final String KEY_BROADCAST_DATE = "com.google.android.gms.cast.metadata.BROADCAST_DATE";
    public static final String KEY_TITLE = "com.google.android.gms.cast.metadata.TITLE";
    public static final String KEY_SUBTITLE = "com.google.android.gms.cast.metadata.SUBTITLE";
    public static final String KEY_ARTIST = "com.google.android.gms.cast.metadata.ARTIST";
    public static final String KEY_ALBUM_ARTIST = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
    public static final String KEY_ALBUM_TITLE = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
    public static final String KEY_COMPOSER = "com.google.android.gms.cast.metadata.COMPOSER";
    public static final String KEY_DISC_NUMBER = "com.google.android.gms.cast.metadata.DISC_NUMBER";
    public static final String KEY_TRACK_NUMBER = "com.google.android.gms.cast.metadata.TRACK_NUMBER";
    public static final String KEY_SEASON_NUMBER = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
    public static final String KEY_EPISODE_NUMBER = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
    public static final String KEY_SERIES_TITLE = "com.google.android.gms.cast.metadata.SERIES_TITLE";
    public static final String KEY_STUDIO = "com.google.android.gms.cast.metadata.STUDIO";
    public static final String KEY_WIDTH = "com.google.android.gms.cast.metadata.WIDTH";
    public static final String KEY_HEIGHT = "com.google.android.gms.cast.metadata.HEIGHT";
    public static final String KEY_LOCATION_NAME = "com.google.android.gms.cast.metadata.LOCATION_NAME";
    public static final String KEY_LOCATION_LATITUDE = "com.google.android.gms.cast.metadata.LOCATION_LATITUDE";
    public static final String KEY_LOCATION_LONGITUDE = "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE";
    private static final zza zzdw = new zza().zza(KEY_CREATION_DATE, "creationDateTime", 4).zza(KEY_RELEASE_DATE, "releaseDate", 4).zza(KEY_BROADCAST_DATE, "originalAirdate", 4).zza(KEY_TITLE, TvContractCompat.ProgramColumns.COLUMN_TITLE, 1).zza(KEY_SUBTITLE, "subtitle", 1).zza(KEY_ARTIST, "artist", 1).zza(KEY_ALBUM_ARTIST, "albumArtist", 1).zza(KEY_ALBUM_TITLE, "albumName", 1).zza(KEY_COMPOSER, "composer", 1).zza(KEY_DISC_NUMBER, "discNumber", 2).zza(KEY_TRACK_NUMBER, "trackNumber", 2).zza(KEY_SEASON_NUMBER, DealWatchNextService.SEASON, 2).zza(KEY_EPISODE_NUMBER, DealWatchNextService.EPISODE, 2).zza(KEY_SERIES_TITLE, "seriesTitle", 1).zza(KEY_STUDIO, "studio", 1).zza(KEY_WIDTH, "width", 2).zza(KEY_HEIGHT, "height", 2).zza(KEY_LOCATION_NAME, "location", 1).zza(KEY_LOCATION_LATITUDE, "latitude", 3).zza(KEY_LOCATION_LONGITUDE, "longitude", 3).zza("com.google.android.gms.cast.metadata.SECTION_DURATION", "sectionDuration", 5).zza("com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA", "sectionStartTimeInMedia", 5).zza("com.google.android.gms.cast.metadata.SECTION_START_ABSOLUTE_TIME", "sectionStartAbsoluteTime", 5).zza("com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_CONTAINER", "sectionStartTimeInContainer", 5).zza("com.google.android.gms.cast.metadata.QUEUE_ITEM_ID", "queueItemId", 2);

    MediaMetadata(List<WebImage> list, Bundle bundle, int i) {
        this.zzdx = list;
        this.zzdy = bundle;
        this.zzdz = i;
    }

    private static class zza {
        private final Map<String, String> zzea = new HashMap();
        private final Map<String, String> zzeb = new HashMap();
        private final Map<String, Integer> zzec = new HashMap();

        public final zza zza(String str, String str2, int i) {
            this.zzea.put(str, str2);
            this.zzeb.put(str2, str);
            this.zzec.put(str, Integer.valueOf(i));
            return this;
        }

        public final String zzf(String str) {
            return this.zzea.get(str);
        }

        public final String zzg(String str) {
            return this.zzeb.get(str);
        }

        public final int zzh(String str) {
            Integer num = this.zzec.get(str);
            if (num != null) {
                return num.intValue();
            }
            return 0;
        }
    }

    public MediaMetadata() {
        this(0);
    }

    public MediaMetadata(int i) {
        this(new ArrayList(), new Bundle(), i);
    }

    public int getMediaType() {
        return this.zzdz;
    }

    public void clear() {
        this.zzdy.clear();
        this.zzdx.clear();
    }

    public boolean containsKey(String str) {
        return this.zzdy.containsKey(str);
    }

    public Set<String> keySet() {
        return this.zzdy.keySet();
    }

    public void putString(String str, String str2) {
        zza(str, 1);
        this.zzdy.putString(str, str2);
    }

    public String getString(String str) {
        zza(str, 1);
        return this.zzdy.getString(str);
    }

    public void putInt(String str, int i) {
        zza(str, 2);
        this.zzdy.putInt(str, i);
    }

    public int getInt(String str) {
        zza(str, 2);
        return this.zzdy.getInt(str);
    }

    public void putDouble(String str, double d) {
        zza(str, 3);
        this.zzdy.putDouble(str, d);
    }

    public double getDouble(String str) {
        zza(str, 3);
        return this.zzdy.getDouble(str);
    }

    public void putDate(String str, Calendar calendar) {
        zza(str, 4);
        this.zzdy.putString(str, zzeg.zza(calendar));
    }

    public Calendar getDate(String str) {
        zza(str, 4);
        String string = this.zzdy.getString(str);
        if (string != null) {
            return zzeg.zzv(string);
        }
        return null;
    }

    public String getDateAsString(String str) {
        zza(str, 4);
        return this.zzdy.getString(str);
    }

    public final long zze(String str) {
        zza(str, 5);
        return this.zzdy.getLong(str);
    }

    private static void zza(String str, int i) throws IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("null and empty keys are not allowed");
        }
        int iZzh = zzdw.zzh(str);
        if (iZzh == i || iZzh == 0) {
            return;
        }
        String str2 = zzdv[i];
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length());
        sb.append("Value for ");
        sb.append(str);
        sb.append(" must be a ");
        sb.append(str2);
        throw new IllegalArgumentException(sb.toString());
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("metadataType", this.zzdz);
        } catch (JSONException unused) {
        }
        JSONArray jSONArrayZze = zzeg.zze(this.zzdx);
        if (jSONArrayZze != null && jSONArrayZze.length() != 0) {
            try {
                jSONObject.put("images", jSONArrayZze);
            } catch (JSONException unused2) {
            }
        }
        ArrayList arrayList = new ArrayList();
        int i = this.zzdz;
        if (i == 0) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i == 1) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i == 2) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE);
        } else if (i == 3) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_ARTIST, KEY_ALBUM_TITLE, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE);
        } else if (i == 4) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE);
        }
        Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.SECTION_DURATION", "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA", "com.google.android.gms.cast.metadata.SECTION_START_ABSOLUTE_TIME", "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_CONTAINER", "com.google.android.gms.cast.metadata.QUEUE_ITEM_ID");
        try {
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                String str = (String) obj;
                if (this.zzdy.containsKey(str)) {
                    zza zzaVar = zzdw;
                    int iZzh = zzaVar.zzh(str);
                    if (iZzh != 1) {
                        if (iZzh == 2) {
                            jSONObject.put(zzaVar.zzf(str), this.zzdy.getInt(str));
                        } else if (iZzh == 3) {
                            jSONObject.put(zzaVar.zzf(str), this.zzdy.getDouble(str));
                        } else if (iZzh != 4) {
                            if (iZzh == 5) {
                                jSONObject.put(zzaVar.zzf(str), this.zzdy.getLong(str) / 1000.0d);
                            }
                        }
                    }
                    jSONObject.put(zzaVar.zzf(str), this.zzdy.getString(str));
                }
            }
            for (String str2 : this.zzdy.keySet()) {
                if (!str2.startsWith("com.google.")) {
                    Object obj2 = this.zzdy.get(str2);
                    if (obj2 instanceof String) {
                        jSONObject.put(str2, obj2);
                    } else if (obj2 instanceof Integer) {
                        jSONObject.put(str2, obj2);
                    } else if (obj2 instanceof Double) {
                        jSONObject.put(str2, obj2);
                    }
                }
            }
        } catch (JSONException unused3) {
        }
        return jSONObject;
    }

    public final void zze(JSONObject jSONObject) {
        clear();
        this.zzdz = 0;
        try {
            this.zzdz = jSONObject.getInt("metadataType");
        } catch (JSONException unused) {
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("images");
        if (jSONArrayOptJSONArray != null) {
            zzeg.zza(this.zzdx, jSONArrayOptJSONArray);
        }
        ArrayList arrayList = new ArrayList();
        int i = this.zzdz;
        if (i == 0) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i == 1) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i == 2) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE);
        } else if (i == 3) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_ALBUM_TITLE, KEY_ARTIST, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE);
        } else if (i == 4) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE);
        }
        Collections.addAll(arrayList, "com.google.android.gms.cast.metadata.QUEUE_ITEM_ID");
        HashSet hashSet = new HashSet(arrayList);
        try {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (!"metadataType".equals(next)) {
                    zza zzaVar = zzdw;
                    String strZzg = zzaVar.zzg(next);
                    if (strZzg != null) {
                        if (hashSet.contains(strZzg)) {
                            try {
                                Object obj = jSONObject.get(next);
                                if (obj != null) {
                                    int iZzh = zzaVar.zzh(strZzg);
                                    if (iZzh != 1) {
                                        if (iZzh != 2) {
                                            if (iZzh == 3) {
                                                double dOptDouble = jSONObject.optDouble(next);
                                                if (!Double.isNaN(dOptDouble)) {
                                                    this.zzdy.putDouble(strZzg, dOptDouble);
                                                }
                                            } else if (iZzh != 4) {
                                                if (iZzh == 5) {
                                                    this.zzdy.putLong(strZzg, (long) (jSONObject.optLong(next) * 1000.0d));
                                                }
                                            } else if ((obj instanceof String) && zzeg.zzv((String) obj) != null) {
                                                this.zzdy.putString(strZzg, (String) obj);
                                            }
                                        } else if (obj instanceof Integer) {
                                            this.zzdy.putInt(strZzg, ((Integer) obj).intValue());
                                        }
                                    } else if (obj instanceof String) {
                                        this.zzdy.putString(strZzg, (String) obj);
                                    }
                                }
                            } catch (JSONException unused2) {
                            }
                        }
                    } else {
                        Object obj2 = jSONObject.get(next);
                        if (obj2 instanceof String) {
                            this.zzdy.putString(next, (String) obj2);
                        } else if (obj2 instanceof Integer) {
                            this.zzdy.putInt(next, ((Integer) obj2).intValue());
                        } else if (obj2 instanceof Double) {
                            this.zzdy.putDouble(next, ((Double) obj2).doubleValue());
                        }
                    }
                }
            }
        } catch (JSONException unused3) {
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaMetadata)) {
            return false;
        }
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        return zza(this.zzdy, mediaMetadata.zzdy) && this.zzdx.equals(mediaMetadata.zzdx);
    }

    public int hashCode() {
        Iterator<String> it = this.zzdy.keySet().iterator();
        int iHashCode = 17;
        while (it.hasNext()) {
            iHashCode = (iHashCode * 31) + this.zzdy.get(it.next()).hashCode();
        }
        return (iHashCode * 31) + this.zzdx.hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getImages(), false);
        SafeParcelWriter.writeBundle(parcel, 3, this.zzdy, false);
        SafeParcelWriter.writeInt(parcel, 4, getMediaType());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public List<WebImage> getImages() {
        return this.zzdx;
    }

    public boolean hasImages() {
        List<WebImage> list = this.zzdx;
        return (list == null || list.isEmpty()) ? false : true;
    }

    public void clearImages() {
        this.zzdx.clear();
    }

    public void addImage(WebImage webImage) {
        this.zzdx.add(webImage);
    }

    private final boolean zza(Bundle bundle, Bundle bundle2) {
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if ((obj instanceof Bundle) && (obj2 instanceof Bundle) && !zza((Bundle) obj, (Bundle) obj2)) {
                return false;
            }
            if (obj == null) {
                if (obj2 != null || !bundle2.containsKey(str)) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }
}

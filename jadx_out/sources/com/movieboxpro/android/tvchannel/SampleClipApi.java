package com.movieboxpro.android.tvchannel;

import android.os.AsyncTask;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class SampleClipApi {
    private static final String VIDEO_DESCRIPTION = "Lorem ipsum dolor sit amet.";
    private static final int YOUTUBE_PLAYLIST_START_INDEX = 6;
    private static final int YOUTUBE_VIDEO_START_INDEX = 5;
    private static List<Playlist> mPlaylists;
    private static final String[] PLAY_LIST_NAMES = {"Dog videos", "Cat videos", "Beaches and rivers", "Nature videos", "Birds of prey", "Chickens in trees", "Youtube videos"};
    private static final String[] VIDEO_TITLES = {"Zeitgeist 2010 - Year in Review", "Google Demo Slam - 20ft Search", "Introducing Gmail Blue", "Introducing Google Fiber to the Pole", "Introducing Google Nose", "YouTube Developers Live: Embedded Web Player Customization", "Homer goes to College", "How Beauty and the Beast Should Have Ended", "Motivation Archive"};
    private static final String[] VIDEO_URLS = {"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue.mp4", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole.mp4", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose.mp4", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue.mp4", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole.mp4", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose.mp4", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4"};
    private static final String[] PREVIEW_VIDEO_URLS = {"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue.mp4", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole.mp4", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose.mp4", "https://www.youtube.com/watch?v=M7lc1UVf-VE", "https://www.youtube.com/watch?v=k-LCw4CGIV8", "https://www.youtube.com/watch?v=8hm9ezomDhQ", "https://www.youtube.com/watch?v=QygpaIJclm4"};
    private static final String[] BG_IMAGE_URLS = {"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/bg.jpg", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/bg.jpg", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/bg.jpg", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/bg.jpg", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/bg.jpg", "https://img.youtube.com/vi/M7lc1UVf-VE/maxresdefault.jpg", "https://img.youtube.com/vi/k-LCw4CGIV8/maxresdefault.jpg", "https://img.youtube.com/vi/8hm9ezomDhQ/maxresdefault.jpg", "https://img.youtube.com/vi/QygpaIJclm4/maxresdefault.jpg"};
    private static final String[] CARD_IMAGE_URLS = {"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/card.jpg", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/card.jpg", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/card.jpg", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/card.jpg", "https://img.youtube.com/vi/M7lc1UVf-VE/hqdefault.jpg", "https://img.youtube.com/vi/k-LCw4CGIV8/hqdefault.jpg", "https://img.youtube.com/vi/8hm9ezomDhQ/hqdefault.jpg", "https://img.youtube.com/vi/QygpaIJclm4/hqdefault.jpg"};
    private static int mNextAspectRatio = 0;
    private static int mSeedZ = 11;
    private static int mSeedW = 15;

    interface GetClipByIdListener {
        void onGetClipById(Clip clip);
    }

    interface GetPlaylistsListener {
        void onGetPlaylists(List<Playlist> playlists);
    }

    static void cancelGetClipById(GetClipByIdListener getClipByIdListener) {
    }

    static void cancelGetPlaylists(GetPlaylistsListener getPlaylistsListener) {
    }

    private SampleClipApi() {
    }

    private static int getNextAspectRatio() {
        int i = mNextAspectRatio;
        if (i == 0) {
            mNextAspectRatio = 1;
        } else if (i == 1) {
            mNextAspectRatio = 3;
        } else if (i == 3) {
            mNextAspectRatio = 2;
        } else if (i == 2) {
            mNextAspectRatio = 0;
        }
        return mNextAspectRatio;
    }

    private static int getNumber() {
        int i = mSeedZ;
        int i2 = ((i & 65535) * 36969) + (i >> 16);
        mSeedZ = i2;
        int i3 = mSeedW;
        int i4 = ((65535 & i3) * 18000) + (i3 >> 16);
        mSeedW = i4;
        int i5 = (i2 << 16) + i4;
        return i5 < 0 ? -i5 : i5;
    }

    private static void populatePlaylists() {
        int length;
        if (mPlaylists == null) {
            mPlaylists = new ArrayList();
            int length2 = PLAY_LIST_NAMES.length;
            int i = 0;
            int i2 = 0;
            int i3 = 1;
            int i4 = 1;
            while (i < length2) {
                int number = (getNumber() % 5) + 2;
                ArrayList arrayList = new ArrayList();
                int i5 = 0;
                while (i5 < number) {
                    if (i4 <= 6) {
                        length = i2 % 5;
                    } else {
                        length = (i2 % (VIDEO_TITLES.length - 5)) + 5;
                    }
                    arrayList.add(new Clip(VIDEO_TITLES[length], VIDEO_DESCRIPTION, BG_IMAGE_URLS[length], CARD_IMAGE_URLS[length], VIDEO_URLS[length], PREVIEW_VIDEO_URLS[length], i3 % 2 == 0, "category", Integer.toString(i3), Integer.toString(length), getNextAspectRatio(), 1));
                    i5++;
                    i2++;
                    i3++;
                }
                Collections.shuffle(arrayList);
                mPlaylists.add(new Playlist(PLAY_LIST_NAMES[i], arrayList, Integer.toString(i4)));
                i++;
                i4++;
            }
        }
    }

    static void getPlaylists(GetPlaylistsListener getPlaylistsListener) {
        populatePlaylists();
        new SimulateGetPlaylistsTask(getPlaylistsListener, mPlaylists).execute(new Void[0]);
    }

    static List<Playlist> getPlaylistBlocking() {
        populatePlaylists();
        return mPlaylists;
    }

    static void getClipById(String clipId, GetClipByIdListener getClipByIdListener) {
        populatePlaylists();
        Clip clip = null;
        for (int i = 0; i < mPlaylists.size() && clip == null; i++) {
            for (Clip clip2 : mPlaylists.get(i).getClips()) {
                if (TextUtils.equals(clip2.getClipId(), clipId)) {
                    clip = clip2;
                    break;
                }
            }
        }
        new SimulateGetClipByIdTask(getClipByIdListener, clip).execute(new Void[0]);
    }

    static Clip getClipByIdBlocking(String clipId) {
        populatePlaylists();
        Iterator<Playlist> it = mPlaylists.iterator();
        while (it.hasNext()) {
            for (Clip clip : it.next().getClips()) {
                if (TextUtils.equals(clip.getClipId(), clipId)) {
                    return clip;
                }
            }
        }
        return null;
    }

    static List<Clip> getSearchResults(String query) {
        int iRandom = (int) ((Math.random() * 4.0d) + 2.0d);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 1;
        int length = 0;
        while (i < iRandom) {
            boolean z = i2 % 2 == 0;
            String[] strArr = VIDEO_TITLES;
            arrayList.add(new Clip(strArr[length], VIDEO_DESCRIPTION, BG_IMAGE_URLS[length], CARD_IMAGE_URLS[length], VIDEO_URLS[length], PREVIEW_VIDEO_URLS[length], z, "category", Integer.toString(i2), Integer.toString(length), getNextAspectRatio(), 1));
            i++;
            length = (length + 1) % strArr.length;
            i2++;
        }
        return arrayList;
    }

    static List<Playlist> getDesiredPublishedChannelSet() {
        populatePlaylists();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 3 && i < mPlaylists.size(); i++) {
            arrayList.add(mPlaylists.get(i));
        }
        return arrayList;
    }

    static Playlist getPlaylistById(String playlistId) {
        for (Playlist playlist : mPlaylists) {
            if (TextUtils.equals(playlist.getPlaylistId(), playlistId)) {
                return playlist;
            }
        }
        return null;
    }

    private static class SimulateGetClipByIdTask extends AsyncTask<Void, Void, Void> {
        private Clip mClip;
        private GetClipByIdListener mGetClipByIdListener;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            return null;
        }

        SimulateGetClipByIdTask(GetClipByIdListener getClipByIdListener, Clip clip) {
            this.mGetClipByIdListener = getClipByIdListener;
            this.mClip = clip;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void result) {
            this.mGetClipByIdListener.onGetClipById(this.mClip);
        }
    }

    private static class SimulateGetPlaylistsTask extends AsyncTask<Void, Void, Void> {
        private GetPlaylistsListener mGetPlaylistsListener;
        private List<Playlist> mPlaylists;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            return null;
        }

        SimulateGetPlaylistsTask(GetPlaylistsListener getPlaylistsListener, List<Playlist> playlists) {
            this.mGetPlaylistsListener = getPlaylistsListener;
            this.mPlaylists = playlists;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void result) {
            this.mGetPlaylistsListener.onGetPlaylists(this.mPlaylists);
        }
    }
}

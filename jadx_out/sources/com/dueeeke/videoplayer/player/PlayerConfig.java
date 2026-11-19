package com.dueeeke.videoplayer.player;

/* JADX INFO: loaded from: classes4.dex */
public class PlayerConfig {
    public boolean addToPlayerManager;
    public boolean disableAudioFocus;
    public boolean enableMediaCodec;
    public boolean isLooping;
    public AbstractPlayer mAbstractPlayer;
    public boolean mAutoRotate;
    public boolean savingProgress;
    public boolean usingSurfaceView;

    private PlayerConfig(PlayerConfig playerConfig) {
        this.enableMediaCodec = true;
        this.savingProgress = true;
        this.mAbstractPlayer = null;
        this.isLooping = playerConfig.isLooping;
        this.mAutoRotate = playerConfig.mAutoRotate;
        this.addToPlayerManager = playerConfig.addToPlayerManager;
        this.usingSurfaceView = playerConfig.usingSurfaceView;
        this.enableMediaCodec = playerConfig.enableMediaCodec;
        this.mAbstractPlayer = playerConfig.mAbstractPlayer;
        this.savingProgress = playerConfig.savingProgress;
        this.disableAudioFocus = playerConfig.disableAudioFocus;
    }

    private PlayerConfig() {
        this.enableMediaCodec = true;
        this.savingProgress = true;
        this.mAbstractPlayer = null;
    }

    public static class Builder {
        private PlayerConfig target = new PlayerConfig();

        public Builder addToPlayerManager() {
            this.target.addToPlayerManager = true;
            return this;
        }

        public Builder usingSurfaceView() {
            this.target.usingSurfaceView = true;
            return this;
        }

        public Builder autoRotate() {
            this.target.mAutoRotate = true;
            return this;
        }

        public Builder setLooping() {
            this.target.isLooping = true;
            return this;
        }

        public Builder enableMediaCodec(boolean z) {
            this.target.enableMediaCodec = z;
            return this;
        }

        public Builder setCustomMediaPlayer(AbstractPlayer abstractPlayer) {
            this.target.mAbstractPlayer = abstractPlayer;
            return this;
        }

        public Builder savingProgress() {
            this.target.savingProgress = true;
            return this;
        }

        public Builder disableAudioFocus() {
            this.target.disableAudioFocus = true;
            return this;
        }

        public PlayerConfig build() {
            return new PlayerConfig();
        }
    }
}

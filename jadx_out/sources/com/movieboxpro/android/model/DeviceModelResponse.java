package com.movieboxpro.android.model;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DeviceModelResponse {
    private List<DeviceModel> device_list;

    public List<DeviceModel> getDevice_list() {
        return this.device_list;
    }

    public void setDevice_list(List<DeviceModel> device_list) {
        this.device_list = device_list;
    }

    public static class DeviceModel implements Serializable {
        private int box_type;
        private String device_model;
        private String device_name;
        private int disable;
        private int episode;
        private String id;
        private long last_time;
        private String master_uid;
        private String mid;
        private String open_udid;
        private String platform;
        private int screenNum;
        private int season;
        private String uid;
        private String username;

        public int getScreenNum() {
            return this.screenNum;
        }

        public void setScreenNum(int screenNum) {
            this.screenNum = screenNum;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMaster_uid() {
            return this.master_uid;
        }

        public void setMaster_uid(String master_uid) {
            this.master_uid = master_uid;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getOpen_udid() {
            return this.open_udid;
        }

        public void setOpen_udid(String open_udid) {
            this.open_udid = open_udid;
        }

        public int getBox_type() {
            return this.box_type;
        }

        public void setBox_type(int box_type) {
            this.box_type = box_type;
        }

        public int getSeason() {
            return this.season;
        }

        public void setSeason(int season) {
            this.season = season;
        }

        public int getEpisode() {
            return this.episode;
        }

        public void setEpisode(int episode) {
            this.episode = episode;
        }

        public int getDisable() {
            return this.disable;
        }

        public void setDisable(int disable) {
            this.disable = disable;
        }

        public String getMid() {
            return this.mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getPlatform() {
            return this.platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getDevice_name() {
            return this.device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_model() {
            return this.device_model;
        }

        public void setDevice_model(String device_model) {
            this.device_model = device_model;
        }

        public long getLast_time() {
            return this.last_time;
        }

        public void setLast_time(long last_time) {
            this.last_time = last_time;
        }
    }
}

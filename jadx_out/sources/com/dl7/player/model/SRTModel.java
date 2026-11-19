package com.dl7.player.model;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SRTModel {
    public String language;
    public List<subTitles> subtitles;

    public static class subTitles {
        private long add_time;
        public int count;
        public float delay;
        public String file_path;
        private boolean isSelect = false;
        public String lang;
        public String language;
        public String mid;
        public String myselect;
        public String order;
        private subTitles org_subtitle;
        public String point;
        public String sid;

        public subTitles getOrg_subtitle() {
            return this.org_subtitle;
        }

        public void setOrg_subtitle(subTitles subtitles) {
            this.org_subtitle = subtitles;
        }

        public long getAdd_time() {
            return this.add_time;
        }

        public void setAdd_time(long j) {
            this.add_time = j;
        }

        public String getSid() {
            return this.sid;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public String getMid() {
            return this.mid;
        }

        public void setMid(String str) {
            this.mid = str;
        }

        public String getFile_path() {
            return this.file_path;
        }

        public void setFile_path(String str) {
            this.file_path = str;
        }

        public String getLang() {
            return this.lang;
        }

        public void setLang(String str) {
            this.lang = str;
        }

        public float getDelay() {
            return this.delay;
        }

        public void setDelay(float f) {
            this.delay = f;
        }

        public String getPoint() {
            return this.point;
        }

        public void setPoint(String str) {
            this.point = str;
        }

        public String getOrder() {
            return this.order;
        }

        public void setOrder(String str) {
            this.order = str;
        }

        public String getMyselect() {
            return this.myselect;
        }

        public void setMyselect(String str) {
            this.myselect = str;
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int i) {
            this.count = i;
        }

        public boolean isSelect() {
            return this.isSelect;
        }

        public void setSelect(boolean z) {
            this.isSelect = z;
        }
    }
}

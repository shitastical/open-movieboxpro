package com.dueeeke.model;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SRTModel {
    public String language;
    public List<SubTitles> subtitles;

    public static class SubTitles {
        public int admin_order;
        public int count;
        public String delay;
        public String file_path;
        private boolean isSelect = false;
        public String lang;
        public String language;
        public String mid;
        public String myselect;
        public String order;
        public String point;
        public String sid;

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

        public String getDelay() {
            return this.delay;
        }

        public void setDelay(String str) {
            this.delay = str;
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

        public String getLanguage() {
            return this.language;
        }

        public void setLanguage(String str) {
            this.language = str;
        }

        public int getAdmin_order() {
            return this.admin_order;
        }

        public void setAdmin_order(int i) {
            this.admin_order = i;
        }
    }
}

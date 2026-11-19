package com.movieboxpro.android.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UserModel implements Serializable {
    public UserData member;

    public static class UserData implements Serializable {
        public long add_time;
        public String appid;
        public String avatar;
        public long dead_time;
        public String email;
        private int family;
        private int family_member_count;
        public String firebase_token;
        public String id;
        public int isvip;
        public long last_time;
        private String master_uid;
        private String master_username;
        public String name;
        public String nickname;
        public String password;
        public String phone;
        public transient String session_key;
        public transient String sk_ver;
        private String uid_token;
        public transient long uid_v2_exp;
        public String username;
        public String uid_v2 = "";
        public String uid = "";
        public int invite_count = 0;
        public int invite_code_count = 0;
        public List<Bind> bind = new ArrayList();

        public String getUid_token() {
            return this.uid_token;
        }

        public void setUid_token(String uid_token) {
            this.uid_token = uid_token;
        }

        public String getMaster_uid() {
            return this.master_uid;
        }

        public void setMaster_uid(String master_uid) {
            this.master_uid = master_uid;
        }

        public int getFamily() {
            return this.family;
        }

        public void setFamily(int family) {
            this.family = family;
        }

        public String getMaster_username() {
            return this.master_username;
        }

        public void setMaster_username(String master_username) {
            this.master_username = master_username;
        }

        public int getFamily_member_count() {
            return this.family_member_count;
        }

        public void setFamily_member_count(int family_member_count) {
            this.family_member_count = family_member_count;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUid_v2() {
            return this.uid_v2;
        }

        public void setUid_v2(String uid_v2) {
            this.uid_v2 = uid_v2;
        }

        public String getAppid() {
            return this.appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getAvatar() {
            return this.avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public long getAdd_time() {
            return this.add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public long getLast_time() {
            return this.last_time;
        }

        public void setLast_time(long last_time) {
            this.last_time = last_time;
        }

        public long getDead_time() {
            return this.dead_time;
        }

        public void setDead_time(long dead_time) {
            this.dead_time = dead_time;
        }

        public int getIsvip() {
            return this.isvip;
        }

        public void setIsvip(int isvip) {
            this.isvip = isvip;
        }

        public int getInvite_count() {
            return this.invite_count;
        }

        public void setInvite_count(int invite_count) {
            this.invite_count = invite_count;
        }

        public int getInvite_code_count() {
            return this.invite_code_count;
        }

        public void setInvite_code_count(int invite_code_count) {
            this.invite_code_count = invite_code_count;
        }

        public List<Bind> getBind() {
            return this.bind;
        }

        public void setBind(List<Bind> bind) {
            this.bind = bind;
        }

        public String getFirebase_token() {
            return this.firebase_token;
        }

        public void setFirebase_token(String firebase_token) {
            this.firebase_token = firebase_token;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class Bind implements Serializable {
        public String id;
        public String lock;
        public String name;
        public String nick;
        public String openid;
        public String token;
        public String type;
        public String uid;
        public String validtime;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOpenid() {
            return this.openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getToken() {
            return this.token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getValidtime() {
            return this.validtime;
        }

        public void setValidtime(String validtime) {
            this.validtime = validtime;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNick() {
            return this.nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getLock() {
            return this.lock;
        }

        public void setLock(String lock) {
            this.lock = lock;
        }
    }
}

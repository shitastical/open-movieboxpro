package com.movieboxpro.android.utils;

import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class ParamsUtils {
    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Bundle params = new Bundle();

        Builder() {
        }

        public Builder addParam(String key, ArrayList<? extends Parcelable> list) {
            this.params.putParcelableArrayList(key, list);
            return this;
        }

        public Builder addParam(String key, String value) {
            this.params.putString(key, value);
            return this;
        }

        public Builder addParam(String key, int value) {
            this.params.putInt(key, value);
            return this;
        }

        public Builder addParam(String key, boolean value) {
            this.params.putBoolean(key, value);
            return this;
        }

        public Builder addParam(String key, double value) {
            this.params.putDouble(key, value);
            return this;
        }

        public Builder addParam(String key, Serializable value) {
            this.params.putSerializable(key, value);
            return this;
        }

        public Builder addParam(String key, Parcelable value) {
            this.params.putParcelable(key, value);
            return this;
        }

        public Bundle build() {
            return this.params;
        }
    }
}

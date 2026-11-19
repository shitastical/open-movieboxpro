package com.kunminx.architecture.ui.callback;

/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public class UnPeekLiveDataV3<T> extends ProtectedUnPeekLiveDataV3<T> {
    @Override // com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveDataV3, androidx.lifecycle.LiveData
    public void setValue(T t) {
        super.setValue(t);
    }

    @Override // androidx.lifecycle.LiveData
    public void postValue(T t) {
        super.postValue(t);
    }

    public static class Builder<T> {
        private boolean isAllowNullValue;
        private int eventSurvivalTime = 1000;
        private boolean isAllowToClear = true;

        public Builder<T> setEventSurvivalTime(int i) {
            this.eventSurvivalTime = i;
            return this;
        }

        public Builder<T> setAllowNullValue(boolean z) {
            this.isAllowNullValue = z;
            return this;
        }

        public Builder<T> setAllowToClear(boolean z) {
            this.isAllowToClear = z;
            return this;
        }

        public UnPeekLiveDataV3<T> create() {
            UnPeekLiveDataV3<T> unPeekLiveDataV3 = new UnPeekLiveDataV3<>();
            unPeekLiveDataV3.DELAY_TO_CLEAR_EVENT = this.eventSurvivalTime;
            unPeekLiveDataV3.isAllowNullValue = this.isAllowNullValue;
            unPeekLiveDataV3.isAllowToClear = this.isAllowToClear;
            return unPeekLiveDataV3;
        }
    }
}

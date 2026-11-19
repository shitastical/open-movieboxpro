package com.kunminx.architecture.ui.callback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class ProtectedUnPeekLiveData<T> extends LiveData<T> {
    protected boolean isAllowNullValue;
    private final HashMap<Integer, Boolean> observers = new HashMap<>();

    public void observeInActivity(AppCompatActivity appCompatActivity, Observer<? super T> observer) {
        observe(Integer.valueOf(System.identityHashCode(appCompatActivity.getViewModelStore())), appCompatActivity, observer);
    }

    public void observeInFragment(Fragment fragment, Observer<? super T> observer) {
        observe(Integer.valueOf(System.identityHashCode(fragment.getViewModelStore())), fragment.getViewLifecycleOwner(), observer);
    }

    public void observeUnPeek(LifecycleOwner lifecycleOwner, ViewModelStore viewModelStore, Observer<? super T> observer) {
        observe(Integer.valueOf(System.identityHashCode(viewModelStore)), lifecycleOwner, observer);
    }

    private void observe(final Integer num, LifecycleOwner lifecycleOwner, final Observer<? super T> observer) {
        if (this.observers.get(num) == null) {
            this.observers.put(num, true);
        }
        super.observe(lifecycleOwner, new Observer() { // from class: com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveData$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m710xd8930437(num, observer, obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$observe$0$com-kunminx-architecture-ui-callback-ProtectedUnPeekLiveData, reason: not valid java name */
    /* synthetic */ void m710xd8930437(Integer num, Observer observer, Object obj) {
        if (this.observers.get(num).booleanValue()) {
            return;
        }
        this.observers.put(num, true);
        if (obj != null || this.isAllowNullValue) {
            observer.onChanged(obj);
        }
    }

    @Override // androidx.lifecycle.LiveData
    protected void setValue(T t) {
        if (t != null || this.isAllowNullValue) {
            Iterator<Map.Entry<Integer, Boolean>> it = this.observers.entrySet().iterator();
            while (it.hasNext()) {
                it.next().setValue(false);
            }
            super.setValue(t);
        }
    }

    protected void clear() {
        super.setValue(null);
    }
}

package com.movieboxpro.android.utils.click;

import android.view.View;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/* JADX INFO: loaded from: classes5.dex */
public class ClickObservable implements ObservableOnSubscribe<View> {
    private ObservableEmitter mEmitter;

    public ClickObservable(View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.utils.click.ClickObservable.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ClickObservable.this.mEmitter != null) {
                    ClickObservable.this.mEmitter.onNext(view2);
                }
            }
        });
    }

    @Override // io.reactivex.ObservableOnSubscribe
    public void subscribe(ObservableEmitter<View> e) throws Exception {
        this.mEmitter = e;
    }
}

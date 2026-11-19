package com.google.android.material.chip;

import android.content.res.loader.ResourcesLoader;
import android.graphics.Bitmap;
import android.graphics.Gainmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.media.PlaybackParams;
import android.view.autofill.AutofillManager;
import dalvik.system.DelegateLastClassLoader;
import java.util.concurrent.atomic.DoubleAdder;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class Chip$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ ResourcesLoader m() {
        return new ResourcesLoader();
    }

    public static /* bridge */ /* synthetic */ ImageDecoder.Source m(Object obj) {
        return (ImageDecoder.Source) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ AnimatedImageDrawable m539m(Object obj) {
        return (AnimatedImageDrawable) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ PlaybackParams m541m(Object obj) {
        return (PlaybackParams) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ AutofillManager m543m(Object obj) {
        return (AutofillManager) obj;
    }

    public static /* synthetic */ DelegateLastClassLoader m(String str, ClassLoader classLoader) {
        return new DelegateLastClassLoader(str, classLoader);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m544m() {
        return AutofillManager.class;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ DoubleAdder m546m(Object obj) {
        return (DoubleAdder) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m547m() {
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m550m(Object obj) {
        return obj instanceof DoubleAdder;
    }

    public static /* synthetic */ Gainmap m$1(Bitmap bitmap) {
        return new Gainmap(bitmap);
    }

    /* JADX INFO: renamed from: m$1, reason: collision with other method in class */
    public static /* synthetic */ void m552m$1() {
    }

    public static /* bridge */ /* synthetic */ boolean m$1(Object obj) {
        return obj instanceof AnimatedImageDrawable;
    }
}

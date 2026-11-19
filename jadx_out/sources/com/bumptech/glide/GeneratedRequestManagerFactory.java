package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.movieboxpro.android.app.GlideRequests;

/* JADX INFO: loaded from: classes4.dex */
final class GeneratedRequestManagerFactory implements RequestManagerRetriever.RequestManagerFactory {
    GeneratedRequestManagerFactory() {
    }

    @Override // com.bumptech.glide.manager.RequestManagerRetriever.RequestManagerFactory
    public RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode treeNode, Context context) {
        return new GlideRequests(glide, lifecycle, treeNode, context);
    }
}

package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import com.movieboxpro.android.service.FileDownloadService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DirectoryModel.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J;\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0010\"\u0004\b\u0016\u0010\u0012¨\u0006\""}, d2 = {"Lcom/movieboxpro/android/model/DirectoryModel;", "", "name", "", "checked", "", FileDownloadService.PARAMS_KEY_PATH, "isDir", "isEdit", "<init>", "(Ljava/lang/String;ZLjava/lang/String;ZZ)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getChecked", "()Z", "setChecked", "(Z)V", "getPath", "setPath", "setDir", "setEdit", "component1", "component2", "component3", "component4", "component5", "copy", "equals", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DirectoryModel {
    private boolean checked;
    private boolean isDir;
    private boolean isEdit;
    private String name;
    private String path;

    public static /* synthetic */ DirectoryModel copy$default(DirectoryModel directoryModel, String str, boolean z, String str2, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = directoryModel.name;
        }
        if ((i & 2) != 0) {
            z = directoryModel.checked;
        }
        boolean z4 = z;
        if ((i & 4) != 0) {
            str2 = directoryModel.path;
        }
        String str3 = str2;
        if ((i & 8) != 0) {
            z2 = directoryModel.isDir;
        }
        boolean z5 = z2;
        if ((i & 16) != 0) {
            z3 = directoryModel.isEdit;
        }
        return directoryModel.copy(str, z4, str3, z5, z3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getChecked() {
        return this.checked;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsDir() {
        return this.isDir;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsEdit() {
        return this.isEdit;
    }

    public final DirectoryModel copy(String name, boolean checked, String path, boolean isDir, boolean isEdit) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(path, "path");
        return new DirectoryModel(name, checked, path, isDir, isEdit);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DirectoryModel)) {
            return false;
        }
        DirectoryModel directoryModel = (DirectoryModel) other;
        return Intrinsics.areEqual(this.name, directoryModel.name) && this.checked == directoryModel.checked && Intrinsics.areEqual(this.path, directoryModel.path) && this.isDir == directoryModel.isDir && this.isEdit == directoryModel.isEdit;
    }

    public int hashCode() {
        return (((((((this.name.hashCode() * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.checked)) * 31) + this.path.hashCode()) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.isDir)) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.isEdit);
    }

    public String toString() {
        return "DirectoryModel(name=" + this.name + ", checked=" + this.checked + ", path=" + this.path + ", isDir=" + this.isDir + ", isEdit=" + this.isEdit + ")";
    }

    public DirectoryModel(String name, boolean z, String path, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(path, "path");
        this.name = name;
        this.checked = z;
        this.path = path;
        this.isDir = z2;
        this.isEdit = z3;
    }

    public /* synthetic */ DirectoryModel(String str, boolean z, String str2, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z, str2, (i & 8) != 0 ? true : z2, (i & 16) != 0 ? false : z3);
    }

    public final boolean getChecked() {
        return this.checked;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPath() {
        return this.path;
    }

    public final boolean isDir() {
        return this.isDir;
    }

    public final boolean isEdit() {
        return this.isEdit;
    }

    public final void setChecked(boolean z) {
        this.checked = z;
    }

    public final void setDir(boolean z) {
        this.isDir = z;
    }

    public final void setEdit(boolean z) {
        this.isEdit = z;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.path = str;
    }
}

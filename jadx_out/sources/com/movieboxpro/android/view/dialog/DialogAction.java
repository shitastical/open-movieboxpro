package com.movieboxpro.android.view.dialog;

/* JADX INFO: loaded from: classes5.dex */
public class DialogAction {
    private ActionListener actionListener;

    public interface ActionListener {
        void onClick();
    }

    public interface EditActionListener {
        void onClick(String text);
    }

    public void setActionListener(ActionListener listener) {
        this.actionListener = listener;
    }
}

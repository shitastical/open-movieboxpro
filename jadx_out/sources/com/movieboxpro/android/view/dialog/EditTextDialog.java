package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import com.adorkable.iosdialog.R;

/* JADX INFO: loaded from: classes5.dex */
public class EditTextDialog extends AppCompatDialog {
    private Context mContext;

    public EditTextDialog(Context context) {
        super(context, R.style.DialogStyle);
        this.mContext = context;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
    }

    private void initDialog() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (((double) this.mContext.getResources().getDisplayMetrics().widthPixels) * 0.8d);
        attributes.height = -2;
        window.setAttributes(attributes);
    }

    public static class Builder {
        private DialogAction.EditActionListener actionListener;
        private Context context;
        private EditText editText;
        private String hint;
        private String negativeText = "Cancel";
        private String positiveText = "OK";
        private String secondContent;
        private String title;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setHint(String hint) {
            this.hint = hint;
            return this;
        }

        public Builder setContent(String content) {
            this.secondContent = content;
            return this;
        }

        public Builder setNegativeText(String text) {
            this.negativeText = text;
            return this;
        }

        public Builder setPositiveText(String text) {
            this.positiveText = text;
            return this;
        }

        public Builder setActionListener(DialogAction.EditActionListener actionListener) {
            this.actionListener = actionListener;
            return this;
        }

        public EditTextDialog create() {
            final EditTextDialog editTextDialog = new EditTextDialog(this.context);
            View viewInflate = LayoutInflater.from(this.context).inflate(com.movieboxpro.androidtv.R.layout.edit_text_dialog_layout, (ViewGroup) null);
            editTextDialog.addContentView(viewInflate, new ViewGroup.LayoutParams(-1, -2));
            ((TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_title)).setText(this.title);
            ((TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_content)).setText(this.secondContent);
            EditText editText = (EditText) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.editText);
            this.editText = editText;
            editText.setHint(this.hint);
            TextView textView = (TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_negative);
            textView.setText(this.negativeText);
            final TextView textView2 = (TextView) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.tv_positive);
            textView2.setText(this.positiveText);
            textView2.setEnabled(false);
            this.editText.addTextChangedListener(new TextWatcher(this) { // from class: com.movieboxpro.android.view.dialog.EditTextDialog.Builder.1
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                    if (!TextUtils.isEmpty(s.toString())) {
                        textView2.setEnabled(true);
                    } else {
                        textView2.setEnabled(false);
                    }
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.EditTextDialog.Builder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (Builder.this.actionListener != null) {
                        Builder.this.actionListener.onClick(Builder.this.editText.getText().toString());
                    }
                    editTextDialog.dismiss();
                }
            });
            textView.setOnClickListener(new View.OnClickListener(this) { // from class: com.movieboxpro.android.view.dialog.EditTextDialog.Builder.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    editTextDialog.dismiss();
                }
            });
            final InputMethodManager inputMethodManager = (InputMethodManager) editTextDialog.getContext().getSystemService("input_method");
            editTextDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.movieboxpro.android.view.dialog.EditTextDialog.Builder.4
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialog) {
                    InputMethodManager inputMethodManager2 = inputMethodManager;
                    if (inputMethodManager2 != null) {
                        inputMethodManager2.hideSoftInputFromWindow(Builder.this.editText.getWindowToken(), 0);
                    }
                }
            });
            this.editText.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.EditTextDialog.Builder.5
                @Override // java.lang.Runnable
                public void run() {
                    Builder.this.editText.requestFocus();
                    InputMethodManager inputMethodManager2 = inputMethodManager;
                    if (inputMethodManager2 != null) {
                        inputMethodManager2.showSoftInput(Builder.this.editText, 0);
                    }
                }
            }, 300L);
            return editTextDialog;
        }
    }
}

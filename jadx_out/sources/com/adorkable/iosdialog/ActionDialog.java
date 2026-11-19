package com.adorkable.iosdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ActionDialog {
    private LinearLayout content;
    private Context context;
    private Dialog dialog;
    private Display display;
    private ScrollView scroller;
    private List<SheetItem> sheetItemList;
    private boolean showTitle = false;
    private TextView tvTitle;

    public interface OnSheetItemClickListener {
        void onClick(int i);
    }

    public ActionDialog(Context context) {
        this.context = context;
        this.display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    public ActionDialog builder() {
        Dialog dialog = new Dialog(this.context, R.style.DialogStyle);
        this.dialog = dialog;
        dialog.setContentView(R.layout.view_actiondialog);
        this.scroller = (ScrollView) this.dialog.findViewById(R.id.dialog_action_scroller);
        this.content = (LinearLayout) this.dialog.findViewById(R.id.dialog_action_content);
        this.tvTitle = (TextView) this.dialog.findViewById(R.id.dialog_action_title);
        ((TextView) this.dialog.findViewById(R.id.dialog_action_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.adorkable.iosdialog.ActionDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ActionDialog.this.dialog.dismiss();
            }
        });
        this.dialog.setCanceledOnTouchOutside(false);
        return this;
    }

    public ActionDialog setTitle(String str) {
        this.showTitle = true;
        this.tvTitle.setVisibility(0);
        this.tvTitle.setText(str);
        return this;
    }

    public ActionDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public ActionDialog addSheetItem(String str, SheetItemColor sheetItemColor, OnSheetItemClickListener onSheetItemClickListener) {
        if (this.sheetItemList == null) {
            this.sheetItemList = new ArrayList();
        }
        this.sheetItemList.add(new SheetItem(str, sheetItemColor, onSheetItemClickListener));
        return this;
    }

    private void setSheetItems() {
        List<SheetItem> list = this.sheetItemList;
        if (list == null || list.size() <= 0) {
            return;
        }
        int size = this.sheetItemList.size();
        if (size >= 7) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.scroller.getLayoutParams();
            layoutParams.height = this.display.getHeight() / 2;
            this.scroller.setLayoutParams(layoutParams);
        }
        for (final int i = 1; i <= size; i++) {
            SheetItem sheetItem = this.sheetItemList.get(i - 1);
            String str = sheetItem.name;
            SheetItemColor sheetItemColor = sheetItem.color;
            final OnSheetItemClickListener onSheetItemClickListener = sheetItem.itemClickListener;
            TextView textView = new TextView(this.context);
            textView.setText(str);
            textView.setTextSize(2, 14.0f);
            textView.setGravity(17);
            if (size == 1) {
                if (this.showTitle) {
                    textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                } else {
                    textView.setBackgroundResource(R.drawable.actionsheet_single_selector);
                }
            } else if (this.showTitle) {
                if (i >= 1 && i < size) {
                    textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                } else {
                    textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                }
            } else if (i == 1) {
                textView.setBackgroundResource(R.drawable.actionsheet_top_selector);
            } else if (i < size) {
                textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
            } else {
                textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
            }
            if (sheetItemColor == null) {
                textView.setTextColor(Color.parseColor(SheetItemColor.Blue.getName()));
            } else {
                textView.setTextColor(Color.parseColor(sheetItemColor.getName()));
            }
            textView.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) ((this.context.getResources().getDisplayMetrics().density * 45.0f) + 0.5f)));
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.adorkable.iosdialog.ActionDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onSheetItemClickListener.onClick(i);
                    ActionDialog.this.dialog.dismiss();
                }
            });
            this.content.addView(textView);
        }
    }

    public void show() {
        setSheetItems();
        this.dialog.show();
    }

    public class SheetItem {
        SheetItemColor color;
        OnSheetItemClickListener itemClickListener;
        String name;

        public SheetItem(String str, SheetItemColor sheetItemColor, OnSheetItemClickListener onSheetItemClickListener) {
            this.name = str;
            this.color = sheetItemColor;
            this.itemClickListener = onSheetItemClickListener;
        }
    }

    public enum SheetItemColor {
        Blue("#037BFF"),
        Red("#FD4A2E");

        private String name;

        SheetItemColor(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }
    }
}

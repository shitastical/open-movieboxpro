package com.gavin.com.library.util;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ViewUtil {
    public static List<View> getChildViewWithId(View view) {
        ArrayList arrayList = new ArrayList();
        if (view != null && (view instanceof ViewGroup)) {
            LinkedList linkedList = new LinkedList();
            linkedList.add((ViewGroup) view);
            while (!linkedList.isEmpty()) {
                ViewGroup viewGroup = (ViewGroup) linkedList.removeFirst();
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt instanceof ViewGroup) {
                        linkedList.addLast((ViewGroup) viewGroup.getChildAt(i));
                    }
                    if (childAt.getId() != -1) {
                        arrayList.add(childAt);
                    }
                }
            }
        }
        return arrayList;
    }
}

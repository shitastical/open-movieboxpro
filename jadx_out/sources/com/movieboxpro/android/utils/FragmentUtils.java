package com.movieboxpro.android.utils;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class FragmentUtils {
    private static final String ARGS_ID = "args_id";
    private static final String ARGS_IS_ADD_STACK = "args_is_add_stack";
    private static final String ARGS_IS_HIDE = "args_is_hide";
    private static final String ARGS_TAG = "args_tag";
    private static final int TYPE_ADD_FRAGMENT = 1;
    private static final int TYPE_HIDE_FRAGMENT = 4;
    private static final int TYPE_REMOVE_FRAGMENT = 32;
    private static final int TYPE_REMOVE_TO_FRAGMENT = 64;
    private static final int TYPE_REPLACE_FRAGMENT = 16;
    private static final int TYPE_SHOW_FRAGMENT = 2;
    private static final int TYPE_SHOW_HIDE_FRAGMENT = 8;

    public interface OnBackClickListener {
        boolean onBackClick();
    }

    private FragmentUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId) {
        add(fm, add, containerId, (String) null, false, false);
    }

    public static void add(final FragmentManager fm, final Fragment add, String tag, final int containerId) {
        add(fm, add, containerId, tag, false, false);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final boolean isHide) {
        add(fm, add, containerId, (String) null, isHide, false);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final boolean isHide, final boolean isAddStack) {
        add(fm, add, containerId, (String) null, isHide, isAddStack);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final int enterAnim, final int exitAnim) {
        add(fm, add, containerId, null, false, enterAnim, exitAnim, 0, 0);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final boolean isAddStack, final int enterAnim, final int exitAnim) {
        add(fm, add, containerId, null, isAddStack, enterAnim, exitAnim, 0, 0);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {
        add(fm, add, containerId, null, false, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final boolean isAddStack, final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {
        add(fm, add, containerId, null, isAddStack, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final View... sharedElements) {
        add(fm, add, containerId, (String) null, false, sharedElements);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final boolean isAddStack, final View... sharedElements) {
        add(fm, add, containerId, (String) null, isAddStack, sharedElements);
    }

    public static void add(final FragmentManager fm, final List<Fragment> adds, final int containerId, final int showIndex) {
        add(fm, (Fragment[]) adds.toArray(new Fragment[adds.size()]), containerId, (String[]) null, showIndex);
    }

    public static void add(final FragmentManager fm, final Fragment[] adds, final int containerId, final int showIndex) {
        add(fm, adds, containerId, (String[]) null, showIndex);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final String tag) {
        add(fm, add, containerId, tag, false, false);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final String tag, final boolean isHide) {
        add(fm, add, containerId, tag, isHide, false);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final String tag, final boolean isHide, final boolean isAddStack) {
        putArgs(add, new Args(containerId, tag, isHide, isAddStack));
        operateNoAnim(fm, 1, null, add);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final String tag, final int enterAnim, final int exitAnim) {
        add(fm, add, containerId, tag, false, enterAnim, exitAnim, 0, 0);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final String tag, final boolean isAddStack, final int enterAnim, final int exitAnim) {
        add(fm, add, containerId, tag, isAddStack, enterAnim, exitAnim, 0, 0);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final String tag, final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {
        add(fm, add, containerId, tag, false, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final String tag, final boolean isAddStack, final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {
        FragmentTransaction fragmentTransactionBeginTransaction = fm.beginTransaction();
        putArgs(add, new Args(containerId, tag, false, isAddStack));
        addAnim(fragmentTransactionBeginTransaction, enterAnim, exitAnim, popEnterAnim, popExitAnim);
        operate(1, fm, fragmentTransactionBeginTransaction, null, add);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final String tag, final View... sharedElements) {
        add(fm, add, containerId, tag, false, sharedElements);
    }

    public static void add(final FragmentManager fm, final Fragment add, final int containerId, final String tag, final boolean isAddStack, final View... sharedElements) {
        FragmentTransaction fragmentTransactionBeginTransaction = fm.beginTransaction();
        putArgs(add, new Args(containerId, tag, false, isAddStack));
        addSharedElement(fragmentTransactionBeginTransaction, sharedElements);
        operate(1, fm, fragmentTransactionBeginTransaction, null, add);
    }

    public static void add(final FragmentManager fm, final List<Fragment> adds, final int containerId, final String[] tags, final int showIndex) {
        add(fm, (Fragment[]) adds.toArray(new Fragment[adds.size()]), containerId, tags, showIndex);
    }

    public static void add(final FragmentManager fm, final Fragment[] adds, final int containerId, final String[] tags, final int showIndex) {
        if (tags == null) {
            int length = adds.length;
            int i = 0;
            while (i < length) {
                putArgs(adds[i], new Args(containerId, null, showIndex != i, false));
                i++;
            }
        } else {
            int length2 = adds.length;
            int i2 = 0;
            while (i2 < length2) {
                putArgs(adds[i2], new Args(containerId, tags[i2], showIndex != i2, false));
                i2++;
            }
        }
        operateNoAnim(fm, 1, null, adds);
    }

    public static void show(final Fragment show) {
        putArgs(show, false);
        operateNoAnim(show.getFragmentManager(), 2, null, show);
    }

    public static void show(final Fragment show, int enterAnim) {
        putArgs(show, false);
        operateWithAnim(show.getFragmentManager(), 2, null, enterAnim, 0, show);
    }

    public static void show(final FragmentManager fm) {
        List<Fragment> fragments = getFragments(fm);
        Iterator<Fragment> it = fragments.iterator();
        while (it.hasNext()) {
            putArgs(it.next(), false);
        }
        operateNoAnim(fm, 2, null, (Fragment[]) fragments.toArray(new Fragment[fragments.size()]));
    }

    public static void hide(final Fragment hide) {
        putArgs(hide, true);
        operateNoAnim(hide.getFragmentManager(), 4, null, hide);
    }

    public static void hide2(final Fragment hide) {
        operateNoAnim(hide.getFragmentManager(), 4, null, hide);
    }

    public static void hide(final Fragment hide, int outAnim) {
        putArgs(hide, true);
        operateWithAnim(hide.getFragmentManager(), 4, null, 0, outAnim, hide);
    }

    public static void hide(final FragmentManager fm) {
        List<Fragment> fragments = getFragments(fm);
        Iterator<Fragment> it = fragments.iterator();
        while (it.hasNext()) {
            putArgs(it.next(), true);
        }
        operateNoAnim(fm, 4, null, (Fragment[]) fragments.toArray(new Fragment[fragments.size()]));
    }

    public static void showHide(final int showIndex, final List<Fragment> fragments) {
        showHide(fragments.get(showIndex), fragments);
    }

    public static void showHide(final Fragment show, final List<Fragment> hide) {
        Iterator<Fragment> it = hide.iterator();
        while (it.hasNext()) {
            Fragment next = it.next();
            putArgs(next, next != show);
        }
        operateNoAnim(show.getFragmentManager(), 8, show, (Fragment[]) hide.toArray(new Fragment[hide.size()]));
    }

    public static void showHide(final int showIndex, final Fragment... fragments) {
        showHide(fragments[showIndex], fragments);
    }

    public static void showHide(final Fragment show, final Fragment... hide) {
        int length = hide.length;
        for (int i = 0; i < length; i++) {
            Fragment fragment = hide[i];
            putArgs(fragment, fragment != show);
        }
        operateNoAnim(show.getFragmentManager(), 8, show, hide);
    }

    public static void showHide(final Fragment show, final Fragment hide) {
        putArgs(show, false);
        putArgs(hide, true);
        operateNoAnim(show.getFragmentManager(), 8, show, hide);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment) {
        replace(srcFragment, destFragment, (String) null, false);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final boolean isAddStack) {
        replace(srcFragment, destFragment, (String) null, isAddStack);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final int enterAnim, final int exitAnim) {
        replace(srcFragment, destFragment, (String) null, false, enterAnim, exitAnim, 0, 0);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final boolean isAddStack, final int enterAnim, final int exitAnim) {
        replace(srcFragment, destFragment, (String) null, isAddStack, enterAnim, exitAnim, 0, 0);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {
        replace(srcFragment, destFragment, (String) null, false, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final boolean isAddStack, final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {
        replace(srcFragment, destFragment, (String) null, isAddStack, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final View... sharedElements) {
        replace(srcFragment, destFragment, (String) null, false, sharedElements);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final boolean isAddStack, final View... sharedElements) {
        replace(srcFragment, destFragment, (String) null, isAddStack, sharedElements);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId) {
        replace(fm, fragment, containerId, (String) null, false);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final boolean isAddStack) {
        replace(fm, fragment, containerId, (String) null, isAddStack);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final int enterAnim, final int exitAnim) {
        replace(fm, fragment, containerId, null, false, enterAnim, exitAnim, 0, 0);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final boolean isAddStack, final int enterAnim, final int exitAnim) {
        replace(fm, fragment, containerId, null, isAddStack, enterAnim, exitAnim, 0, 0);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {
        replace(fm, fragment, containerId, null, false, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final boolean isAddStack, final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {
        replace(fm, fragment, containerId, null, isAddStack, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final View... sharedElements) {
        replace(fm, fragment, containerId, (String) null, false, sharedElements);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final boolean isAddStack, final View... sharedElements) {
        replace(fm, fragment, containerId, (String) null, isAddStack, sharedElements);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final String destTag) {
        replace(srcFragment, destFragment, destTag, false);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final String destTag, final boolean isAddStack) {
        FragmentManager fragmentManager = srcFragment.getFragmentManager();
        if (fragmentManager == null) {
            return;
        }
        replace(fragmentManager, destFragment, getArgs(srcFragment).id, destTag, isAddStack);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final String destTag, final int enterAnim, final int exitAnim) {
        replace(srcFragment, destFragment, destTag, false, enterAnim, exitAnim, 0, 0);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final String destTag, final boolean isAddStack, final int enterAnim, final int exitAnim) {
        replace(srcFragment, destFragment, destTag, isAddStack, enterAnim, exitAnim, 0, 0);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final String destTag, final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {
        replace(srcFragment, destFragment, destTag, false, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final String destTag, final boolean isAddStack, final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {
        FragmentManager fragmentManager = srcFragment.getFragmentManager();
        if (fragmentManager == null) {
            return;
        }
        replace(fragmentManager, destFragment, getArgs(srcFragment).id, destTag, isAddStack, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final String destTag, final View... sharedElements) {
        replace(srcFragment, destFragment, destTag, false, sharedElements);
    }

    public static void replace(final Fragment srcFragment, final Fragment destFragment, final String destTag, final boolean isAddStack, final View... sharedElements) {
        FragmentManager fragmentManager = srcFragment.getFragmentManager();
        if (fragmentManager == null) {
            return;
        }
        replace(fragmentManager, destFragment, getArgs(srcFragment).id, destTag, isAddStack, sharedElements);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final String destTag) {
        replace(fm, fragment, containerId, destTag, false);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final String destTag, final boolean isAddStack) {
        FragmentTransaction fragmentTransactionBeginTransaction = fm.beginTransaction();
        putArgs(fragment, new Args(containerId, destTag, false, isAddStack));
        operate(16, fm, fragmentTransactionBeginTransaction, null, fragment);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final String destTag, final int enterAnim, final int exitAnim) {
        replace(fm, fragment, containerId, destTag, false, enterAnim, exitAnim, 0, 0);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final String destTag, final boolean isAddStack, final int enterAnim, final int exitAnim) {
        replace(fm, fragment, containerId, destTag, isAddStack, enterAnim, exitAnim, 0, 0);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final String destTag, final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {
        replace(fm, fragment, containerId, destTag, false, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final String destTag, final boolean isAddStack, final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {
        FragmentTransaction fragmentTransactionBeginTransaction = fm.beginTransaction();
        putArgs(fragment, new Args(containerId, destTag, false, isAddStack));
        addAnim(fragmentTransactionBeginTransaction, enterAnim, exitAnim, popEnterAnim, popExitAnim);
        operate(16, fm, fragmentTransactionBeginTransaction, null, fragment);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final String destTag, final View... sharedElements) {
        replace(fm, fragment, containerId, destTag, false, sharedElements);
    }

    public static void replace(final FragmentManager fm, final Fragment fragment, final int containerId, final String destTag, final boolean isAddStack, final View... sharedElements) {
        FragmentTransaction fragmentTransactionBeginTransaction = fm.beginTransaction();
        putArgs(fragment, new Args(containerId, destTag, false, isAddStack));
        addSharedElement(fragmentTransactionBeginTransaction, sharedElements);
        operate(16, fm, fragmentTransactionBeginTransaction, null, fragment);
    }

    public static void pop(final FragmentManager fm) {
        pop(fm, true);
    }

    public static void pop(final FragmentManager fm, final boolean isImmediate) {
        if (isImmediate) {
            fm.popBackStackImmediate();
        } else {
            fm.popBackStack();
        }
    }

    public static void popTo(final FragmentManager fm, final Class<? extends Fragment> popClz, final boolean isIncludeSelf) {
        popTo(fm, popClz, isIncludeSelf, true);
    }

    public static void popTo(FragmentManager fragmentManager, Class<? extends Fragment> cls, boolean z, boolean z2) {
        if (z2) {
            fragmentManager.popBackStackImmediate(cls.getName(), z ? 1 : 0);
        } else {
            fragmentManager.popBackStack(cls.getName(), z ? 1 : 0);
        }
    }

    public static void popAll(final FragmentManager fm) {
        popAll(fm, true);
    }

    public static void popAll(final FragmentManager fm, final boolean isImmediate) {
        while (fm.getBackStackEntryCount() > 0) {
            if (isImmediate) {
                fm.popBackStackImmediate();
            } else {
                fm.popBackStack();
            }
        }
    }

    public static void remove(final Fragment remove) {
        operateNoAnim(remove.getFragmentManager(), 32, null, remove);
    }

    public static void removeTo(final Fragment removeTo, final boolean isIncludeSelf) {
        operateNoAnim(removeTo.getFragmentManager(), 64, isIncludeSelf ? removeTo : null, removeTo);
    }

    public static void removeAll(final FragmentManager fm) {
        List<Fragment> fragments = getFragments(fm);
        operateNoAnim(fm, 32, null, (Fragment[]) fragments.toArray(new Fragment[fragments.size()]));
    }

    private static void putArgs(final Fragment fragment, final Args args) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        arguments.putInt(ARGS_ID, args.id);
        arguments.putBoolean(ARGS_IS_HIDE, args.isHide);
        arguments.putBoolean(ARGS_IS_ADD_STACK, args.isAddStack);
        arguments.putString(ARGS_TAG, args.tag);
    }

    private static void putArgs(final Fragment fragment, final boolean isHide) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        arguments.putBoolean(ARGS_IS_HIDE, isHide);
    }

    private static Args getArgs(final Fragment fragment) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = Bundle.EMPTY;
        }
        return new Args(arguments.getInt(ARGS_ID, fragment.getId()), arguments.getBoolean(ARGS_IS_HIDE), arguments.getBoolean(ARGS_IS_ADD_STACK));
    }

    private static void operateNoAnim(final FragmentManager fm, final int type, final Fragment src, Fragment... dest) {
        if (fm == null) {
            return;
        }
        operate(type, fm, fm.beginTransaction(), src, dest);
    }

    private static void operateWithAnim(final FragmentManager fm, final int type, final Fragment src, int enterAnim, int outAnim, Fragment... dest) {
        if (fm == null) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = fm.beginTransaction();
        fragmentTransactionBeginTransaction.setCustomAnimations(enterAnim, outAnim);
        operate(type, fm, fragmentTransactionBeginTransaction, src, dest);
    }

    private static void operate(final int type, final FragmentManager fm, final FragmentTransaction ft, final Fragment src, final Fragment... dest) {
        if (src != null && src.isRemoving()) {
            Log.e("FragmentUtils", src.getClass().getName() + " is isRemoving");
            return;
        }
        int i = 0;
        if (type == 1) {
            int length = dest.length;
            while (i < length) {
                Fragment fragment = dest[i];
                Bundle arguments = fragment.getArguments();
                if (arguments == null) {
                    return;
                }
                String string = arguments.getString(ARGS_TAG, fragment.getClass().getName());
                Fragment fragmentFindFragmentByTag = fm.findFragmentByTag(string);
                if (fragmentFindFragmentByTag != null && fragmentFindFragmentByTag.isAdded()) {
                    ft.remove(fragmentFindFragmentByTag);
                }
                ft.add(arguments.getInt(ARGS_ID), fragment, string);
                if (arguments.getBoolean(ARGS_IS_HIDE)) {
                    ft.hide(fragment);
                }
                if (arguments.getBoolean(ARGS_IS_ADD_STACK)) {
                    ft.addToBackStack(string);
                }
                i++;
            }
        } else if (type == 2) {
            int length2 = dest.length;
            while (i < length2) {
                ft.show(dest[i]);
                i++;
            }
        } else if (type == 4) {
            int length3 = dest.length;
            while (i < length3) {
                ft.hide(dest[i]);
                i++;
            }
        } else if (type == 8) {
            ft.show(src);
            int length4 = dest.length;
            while (i < length4) {
                Fragment fragment2 = dest[i];
                if (fragment2 != src) {
                    ft.hide(fragment2);
                }
                i++;
            }
        } else if (type == 16) {
            Bundle arguments2 = dest[0].getArguments();
            if (arguments2 == null) {
                return;
            }
            String string2 = arguments2.getString(ARGS_TAG, dest[0].getClass().getName());
            ft.replace(arguments2.getInt(ARGS_ID), dest[0], string2);
            if (arguments2.getBoolean(ARGS_IS_ADD_STACK)) {
                ft.addToBackStack(string2);
            }
        } else if (type == 32) {
            int length5 = dest.length;
            while (i < length5) {
                Fragment fragment3 = dest[i];
                if (fragment3 != src) {
                    ft.remove(fragment3);
                }
                i++;
            }
        } else if (type == 64) {
            for (int length6 = dest.length - 1; length6 >= 0; length6--) {
                Fragment fragment4 = dest[length6];
                if (fragment4 == dest[0]) {
                    if (src == null) {
                        break;
                    }
                    ft.remove(fragment4);
                    break;
                }
                ft.remove(fragment4);
            }
        }
        ft.commitAllowingStateLoss();
    }

    private static void addAnim(final FragmentTransaction ft, final int enter, final int exit, final int popEnter, final int popExit) {
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
    }

    private static void addSharedElement(final FragmentTransaction ft, final View... views) {
        for (View view : views) {
            ft.addSharedElement(view, view.getTransitionName());
        }
    }

    public static Fragment getTop(final FragmentManager fm) {
        return getTopIsInStack(fm, false);
    }

    public static Fragment getTopInStack(final FragmentManager fm) {
        return getTopIsInStack(fm, true);
    }

    private static Fragment getTopIsInStack(final FragmentManager fm, final boolean isInStack) {
        Bundle arguments;
        List<Fragment> fragments = getFragments(fm);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = fragments.get(size);
            if (fragment != null && (!isInStack || ((arguments = fragment.getArguments()) != null && arguments.getBoolean(ARGS_IS_ADD_STACK)))) {
                return fragment;
            }
        }
        return null;
    }

    public static Fragment getTopShow(final FragmentManager fm) {
        return getTopShowIsInStack(fm, false);
    }

    public static Fragment getTopShowInStack(final FragmentManager fm) {
        return getTopShowIsInStack(fm, true);
    }

    private static Fragment getTopShowIsInStack(final FragmentManager fm, final boolean isInStack) {
        Bundle arguments;
        List<Fragment> fragments = getFragments(fm);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = fragments.get(size);
            if (fragment != null && fragment.isResumed() && fragment.isVisible() && fragment.getUserVisibleHint() && (!isInStack || ((arguments = fragment.getArguments()) != null && arguments.getBoolean(ARGS_IS_ADD_STACK)))) {
                return fragment;
            }
        }
        return null;
    }

    public static List<Fragment> getFragments(final FragmentManager fm) {
        List<Fragment> fragments = fm.getFragments();
        return (fragments == null || fragments.isEmpty()) ? Collections.emptyList() : fragments;
    }

    public static List<Fragment> getFragmentsInStack(final FragmentManager fm) {
        Bundle arguments;
        List<Fragment> fragments = getFragments(fm);
        ArrayList arrayList = new ArrayList();
        for (Fragment fragment : fragments) {
            if (fragment != null && (arguments = fragment.getArguments()) != null && arguments.getBoolean(ARGS_IS_ADD_STACK)) {
                arrayList.add(fragment);
            }
        }
        return arrayList;
    }

    public static List<FragmentNode> getAllFragments(final FragmentManager fm) {
        return getAllFragments(fm, new ArrayList());
    }

    private static List<FragmentNode> getAllFragments(final FragmentManager fm, final List<FragmentNode> result) {
        List<Fragment> fragments = getFragments(fm);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = fragments.get(size);
            if (fragment != null) {
                result.add(new FragmentNode(fragment, getAllFragments(fragment.getChildFragmentManager(), new ArrayList())));
            }
        }
        return result;
    }

    public static List<FragmentNode> getAllFragmentsInStack(final FragmentManager fm) {
        return getAllFragmentsInStack(fm, new ArrayList());
    }

    private static List<FragmentNode> getAllFragmentsInStack(final FragmentManager fm, final List<FragmentNode> result) {
        Bundle arguments;
        List<Fragment> fragments = getFragments(fm);
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = fragments.get(size);
            if (fragment != null && (arguments = fragment.getArguments()) != null && arguments.getBoolean(ARGS_IS_ADD_STACK)) {
                result.add(new FragmentNode(fragment, getAllFragmentsInStack(fragment.getChildFragmentManager(), new ArrayList())));
            }
        }
        return result;
    }

    public static Fragment findFragment(final FragmentManager fm, final Class<? extends Fragment> findClz) {
        return fm.findFragmentByTag(findClz.getName());
    }

    public static Fragment findFragment(final FragmentManager fm, final String tag) {
        return fm.findFragmentByTag(tag);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchBackPress(final Fragment fragment) {
        return fragment.isResumed() && fragment.isVisible() && fragment.getUserVisibleHint() && (fragment instanceof OnBackClickListener) && ((OnBackClickListener) fragment).onBackClick();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchBackPress(final FragmentManager fm) {
        List<Fragment> fragments = getFragments(fm);
        if (fragments != null && !fragments.isEmpty()) {
            for (int size = fragments.size() - 1; size >= 0; size--) {
                Fragment fragment = fragments.get(size);
                if (fragment != 0 && fragment.isResumed() && fragment.isVisible() && fragment.getUserVisibleHint() && (fragment instanceof OnBackClickListener) && ((OnBackClickListener) fragment).onBackClick()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void setBackgroundColor(final Fragment fragment, final int color) {
        View view = fragment.getView();
        if (view != null) {
            view.setBackgroundColor(color);
        }
    }

    public static void setBackgroundResource(final Fragment fragment, final int resId) {
        View view = fragment.getView();
        if (view != null) {
            view.setBackgroundResource(resId);
        }
    }

    public static void setBackground(final Fragment fragment, final Drawable background) {
        View view = fragment.getView();
        if (view == null) {
            return;
        }
        view.setBackground(background);
    }

    public static String getSimpleName(final Fragment fragment) {
        return fragment == null ? "null" : fragment.getClass().getSimpleName();
    }

    private static class Args {
        final int id;
        final boolean isAddStack;
        final boolean isHide;
        final String tag;

        Args(final int id, final boolean isHide, final boolean isAddStack) {
            this(id, null, isHide, isAddStack);
        }

        Args(final int id, final String tag, final boolean isHide, final boolean isAddStack) {
            this.id = id;
            this.tag = tag;
            this.isHide = isHide;
            this.isAddStack = isAddStack;
        }
    }

    public static class FragmentNode {
        final Fragment fragment;
        final List<FragmentNode> next;

        public FragmentNode(final Fragment fragment, final List<FragmentNode> next) {
            this.fragment = fragment;
            this.next = next;
        }

        public Fragment getFragment() {
            return this.fragment;
        }

        public List<FragmentNode> getNext() {
            return this.next;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.fragment.getClass().getSimpleName());
            sb.append("->");
            List<FragmentNode> list = this.next;
            sb.append((list == null || list.isEmpty()) ? "no child" : this.next.toString());
            return sb.toString();
        }
    }
}

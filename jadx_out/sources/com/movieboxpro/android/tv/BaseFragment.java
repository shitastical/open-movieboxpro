package com.movieboxpro.android.tv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseFragment extends Fragment {
    protected Activity activity;
    protected Context context;
    private TextView mCountText;
    protected FocusBorder mFocusBorder;
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() { // from class: com.movieboxpro.android.tv.BaseFragment.1
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
            BaseFragment.this.updateState(scrollState);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView rv, int i, int i2) {
            BaseFragment.this.updatePosition((TvRecyclerView) rv);
        }
    };
    private TextView mPositionText;
    private RecyclerView mRecyclerView;
    private TextView mStateText;
    private Unbinder unbinder;

    public interface FocusBorderHelper {
        /* JADX INFO: renamed from: getFocusBorder */
        FocusBorder getMFocusBorder();
    }

    public abstract int getLayoutId();

    public void showToast(int resId) {
    }

    public void showToast(String str) {
    }

    protected void onMoveFocusBorder(View focusedView, float scale) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder != null) {
            focusBorder.onFocus(focusedView, FocusBorder.OptionsFactory.get(scale, scale));
        }
    }

    protected void onMoveFocusBorder(View focusedView, float scale, float roundRadius) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder != null) {
            focusBorder.onFocus(focusedView, FocusBorder.OptionsFactory.get(scale, scale, roundRadius));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = getActivity();
        if (getActivity() instanceof FocusBorderHelper) {
            this.mFocusBorder = ((FocusBorderHelper) getActivity()).getMFocusBorder();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(getLayoutId(), container, false);
        this.unbinder = ButterKnife.bind(this, viewInflate);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.unbinder.unbind();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateState(0);
    }

    protected void setScrollListener(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                recyclerView2.removeOnScrollListener(this.mOnScrollListener);
            }
            recyclerView.addOnScrollListener(this.mOnScrollListener);
            this.mRecyclerView = recyclerView;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePosition(TvRecyclerView rv) {
        if (this.mPositionText == null || this.mCountText == null) {
            return;
        }
        int childCount = rv.getChildCount();
        int firstVisiblePosition = rv.getFirstVisiblePosition();
        this.mPositionText.setText("First: " + firstVisiblePosition);
        this.mCountText.setText("Count: " + childCount);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateState(int scrollState) {
        String str;
        TextView textView = this.mStateText;
        if (textView != null) {
            if (scrollState == 0) {
                str = "Idle";
            } else if (scrollState == 1) {
                str = "Dragging";
            } else if (scrollState != 2) {
                str = "Undefined";
            } else {
                str = "Flinging";
            }
            textView.setText(str);
        }
    }

    protected void route(Class<? extends Activity> claz) {
        route(claz, (Bundle) null);
    }

    protected void route(Class<? extends Activity> claz, Bundle data) {
        Intent intent = new Intent(getContext(), claz);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivity(intent);
    }

    protected void route(Class<? extends Activity> claz, int requestCode) {
        route(claz, null, requestCode);
    }

    protected void route(Class<? extends Activity> claz, Bundle data, int requestCode) {
        Intent intent = new Intent(getContext(), claz);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void route(Class<? extends Activity> claz, Bundle data, View v, String transtion) {
        Intent intent = new Intent(getContext(), claz);
        if (data != null) {
            intent.putExtras(data);
        }
        if (Build.VERSION.SDK_INT > 21) {
            ActivityCompat.startActivity(getActivity(), intent, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), Pair.create(v, transtion)).toBundle());
        } else {
            startActivity(intent);
        }
    }
}

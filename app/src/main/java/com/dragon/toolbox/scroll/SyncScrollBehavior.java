package com.dragon.toolbox.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;

public class SyncScrollBehavior extends CoordinatorLayout.Behavior<RecyclerView>{
    private static final String TAG = "SyncScrollBehavior";

    public SyncScrollBehavior(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull RecyclerView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull RecyclerView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        if (target instanceof RecyclerView && target!=child) {
            RecyclerView list = (RecyclerView) target;
            int scrollY = child.getScrollY();
            Random random = new Random(2);
            int i = random.nextInt(5);
            scrollY=-(100/i);
            Log.d(TAG,"scrollY:"+scrollY);
            child.scrollBy(dx,dy);
        }
    }
}
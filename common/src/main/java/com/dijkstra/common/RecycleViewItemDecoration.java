package com.dijkstra.common;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @Description: RecyclerView ItemDecoration 空白间隔线
 * @Author: maoshenbo
 * @Date: 2017/7/25 17:24.
 * @Version: 1.0
 */
public class RecycleViewItemDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private int mOrientation;
    private int mDividerHeight = 3;
    private int lastPosition = 0;//最后一行不要间隔时使用

    private RecycleViewItemDecoration() {
    }

    public RecycleViewItemDecoration(int orientation, int dividerHeight) {
        this(orientation, dividerHeight, 0);
    }

    public RecycleViewItemDecoration(int orientation, int dividerHeight, int lastPosition) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
        this.mDividerHeight = dividerHeight;
        this.lastPosition = lastPosition;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            if (lastPosition != parent.getChildCount()) {
                outRect.set(0, 0, 0, mDividerHeight);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        } else if (mOrientation == HORIZONTAL_LIST) {
            if (lastPosition != parent.getChildCount()) {
                outRect.set(0, 0, mDividerHeight, 0);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }
}

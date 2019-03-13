package com.dijkstra.common.grid;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @Description: RecyclerView Grid 空白间隔线
 * @Author: maoshenbo
 * @Date: 2018/9/17 11:21
 * @Version: 1.0
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpanCount;//横条目数量
    private int mHoSpacing, mVeSpacing;//空隙
    private boolean mIncludeEdge;//是否包含边界

    public GridSpaceItemDecoration(int spanCount, int hoSpacing, int veSpacing, boolean includeEdge) {
        this.mSpanCount = spanCount;
        this.mHoSpacing = hoSpacing;
        this.mVeSpacing = veSpacing;
        this.mIncludeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % mSpanCount; // item column
        if (mIncludeEdge) {
            outRect.left = mHoSpacing - column * mHoSpacing / mSpanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * mHoSpacing / mSpanCount; // (column + 1) * ((1f / spanCount) * spacing)
            if (position < mSpanCount) { // top edge
                outRect.top = mVeSpacing;
            }
            outRect.bottom = mHoSpacing; // item bottom
        } else {
            outRect.left = column * mHoSpacing / mSpanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = mHoSpacing - (column + 1) * mHoSpacing / mSpanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= mSpanCount) {
                outRect.top = mVeSpacing; // item top
            }
        }
    }
}

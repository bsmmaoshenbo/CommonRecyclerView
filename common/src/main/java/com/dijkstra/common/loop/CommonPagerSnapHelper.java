package com.dijkstra.common.loop;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Description: ViewPager的对齐方式控制类
 * @Author: maoshenbo
 * @Date: 2018/11/7 14:20
 * @Version: 1.0
 */
public class CommonPagerSnapHelper extends PagerSnapHelper {

    private RecyclerView mRecyclerView;
    protected OnPageListener mOnPageListener;
    private int mCurrentPosition = -1;
    /**
     * 默认是横向Pager
     */
    private int mOrientation = LinearLayoutManager.HORIZONTAL;

    public CommonPagerSnapHelper() {
    }

    public CommonPagerSnapHelper(int mCurrentPosition) {
        this.mCurrentPosition = mCurrentPosition;
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public CommonPagerSnapHelper setCurrentPosition(int currentPosition) {
        mCurrentPosition = currentPosition;
        return this;
    }

    public OnPageListener getOnPageListener() {
        return mOnPageListener;
    }

    /**
     * 页面选择回调监听
     */
    public CommonPagerSnapHelper setOnPageListener(OnPageListener onPageListener) {
        mOnPageListener = onPageListener;
        return this;
    }

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        //SnapHelper附着到RecyclerView上，从而实现辅助RecyclerView滚动对齐操作
        super.attachToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;

        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(mScrollListener);
            recyclerView.addOnScrollListener(mScrollListener);

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                mOrientation = ((LinearLayoutManager) layoutManager).getOrientation();
            }
        }
    }

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        // 计算SnapView当前位置 与 目标位置的距离
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) targetView.getLayoutParams();
        int position = params.getViewAdapterPosition();
        int left = targetView.getLeft();
        int right = targetView.getRight();
        int top = targetView.getTop();
        int bottom = targetView.getBottom();
        ViewGroup viewGroup = (ViewGroup) targetView.getParent();

        int[] out = new int[]{0, 0};
        boolean isLastItem;
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            isLastItem = position == layoutManager.getItemCount() - 1/*最后一个*/ && right == viewGroup.getMeasuredWidth();
            out[0] = left;
            out[1] = 0;
        } else {
            isLastItem = position == layoutManager.getItemCount() - 1/*最后一个*/ && bottom == viewGroup.getMeasuredHeight();
            out[0] = 0;
            out[1] = top;
        }

        if (mOnPageListener != null && mCurrentPosition != position) {
            int currentPosition = mCurrentPosition;
            boolean listener = false;
            if (mOrientation == LinearLayoutManager.HORIZONTAL && (out[0] == 0 || isLastItem)) {
                listener = true;
            } else if (mOrientation == LinearLayoutManager.VERTICAL && (out[1] == 0 || isLastItem)) {
                listener = true;
            }

            if (listener) {
                mCurrentPosition = position;
                mOnPageListener.onPageChangedListener(currentPosition, mCurrentPosition);
            }
        }
        return out;
    }

    /**
     * 滚动监听
     */
    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            switch (newState) {
                case RecyclerView.SCROLL_STATE_DRAGGING://开始滚动
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING://滑行中
                    break;
                case RecyclerView.SCROLL_STATE_IDLE://结束滚动
                    break;
            }
        }
    };

    public CommonPagerSnapHelper setOrientation(int orientation) {
        mOrientation = orientation;
        return this;
    }
}

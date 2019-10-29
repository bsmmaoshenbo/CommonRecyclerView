package com.dijkstra.common.loop;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.dijkstra.common.CommonRecyclerView;

/**
 * @Description: 自滚动recyclerView
 * @Author: maoshenbo
 * @Date: 2018/12/13 下午6:45
 * @Version: 1.0
 */
public class LoopRecyclerView extends CommonRecyclerView {

    private int mCurScrollPosition;
    private boolean mEnableScroll = true;
    private long mAutoScrollTimeInterval = 3000;

    private Runnable mAutoScrollRunnable;
    private OnPageListener mOnPageListener;
    private LoopLayoutManager mLoopLayoutManager;

    public LoopRecyclerView(Context context) {
        this(context, null);
    }

    public LoopRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoopRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    @Nullable
    @Override
    public CommonLoopAdapter getAdapter() {
        return (CommonLoopAdapter) super.getAdapter();
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        if (!(adapter instanceof CommonLoopAdapter)) {
            throw new IllegalArgumentException("adapter must  instanceof CommonLoopAdapter!");
        }
        super.setAdapter(adapter);
        if (getAdapter() != null) {
            scrollToPosition(getAdapter().getDataItemCount() * 1000);
        }

        if (mEnableScroll) {
            postDelayed(mAutoScrollRunnable, mAutoScrollTimeInterval);
        }
    }

    private void initView() {
        mAutoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                mCurScrollPosition = getCurrentPosition();

                scrollToNext();

                if (mEnableScroll) {
                    postDelayed(mAutoScrollRunnable, mAutoScrollTimeInterval);
                }
            }
        };


        LoopSnapHelper helper = new LoopSnapHelper(this, getLoopOrientation(), mCurScrollPosition);
        helper.setOnPageListener(new OnPageListener() {
            @Override
            public void onPageChangedListener(int fromPosition, int toPosition) {
                if (mOnPageListener != null) {
                    mOnPageListener.onPageChangedListener(fromPosition, toPosition);
                }
            }
        }).attachToRecyclerView(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mLoopLayoutManager != null) {
            mLoopLayoutManager.setItemWidthHeight(w, h);
        }
    }

    @Override
    public LoopRecyclerView setListType(int listType) {
        mLoopLayoutManager = new LoopLayoutManager(getContext(), getLoopOrientation(), false);
        setInfinite(true);
        setLayoutManager(mLoopLayoutManager);
        return this;
    }

    /**
     * 设置自滚动时间间隔
     *
     * @param autoScrollTimeInterval 时间间隔
     * @return 设置时间间隔
     */
    public LoopRecyclerView setAutoScrollTimeInterval(long autoScrollTimeInterval) {
        mAutoScrollTimeInterval = autoScrollTimeInterval;
        return this;
    }

    public LoopRecyclerView setEnableScroll(boolean enableScroll) {
        mEnableScroll = enableScroll;
        return this;
    }

    /**
     * 打开无限循环
     */
    public void setInfinite(boolean enable) {
        if (mLoopLayoutManager != null) {
            mLoopLayoutManager.setInfinite(enable);
        }
    }

    private int getLoopOrientation() {
        String tag = (String) this.getTag();
        if (!TextUtils.isEmpty(tag) && "V".equalsIgnoreCase(tag)) {
            return ViewPagerLayoutManager.VERTICAL;
        }
        return ViewPagerLayoutManager.HORIZONTAL;
    }

    /**
     * 获取当前位置
     *
     * @return 当前位置
     */
    public int getCurrentPosition() {
        if (getLayoutManager() instanceof LoopLayoutManager) {
            return ((LoopLayoutManager) getLayoutManager()).getCurrentPosition();
        } else {
            return RecyclerView.NO_POSITION;
        }
    }


    /**
     * 滚动到下一个
     */
    public void scrollToNext() {
        scrollTo(true);
    }

    /**
     * 滚动到上一个
     */
    public void scrollToPrev() {
        scrollTo(false);
    }

    /**
     * 滚动到莫哥位置
     *
     * @param forwardDirection 到达位置
     */
    public void scrollTo(boolean forwardDirection) {
        if (getLayoutManager() instanceof LoopLayoutManager && getAdapter() != null) {
            final int offsetPosition;
            if (forwardDirection) {
                offsetPosition = 1;
            } else {
                offsetPosition = -1;
            }

            final int currentPosition = getCurrentPosition();

            int position = ((LoopLayoutManager) getLayoutManager()).getReverseLayout() ?
                    currentPosition - offsetPosition : currentPosition + offsetPosition;
            smoothScrollToPosition(position);
        }
    }


    public void setOnPagerListener(OnPageListener listener) {
        mOnPageListener = listener;
    }

}

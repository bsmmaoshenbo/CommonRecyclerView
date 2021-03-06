package com.dijkstra.common;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @Description: RecyclerView
 * @Author: maoshenbo
 * @Date: 2018/10/17 11:58
 * @Version: 1.0
 */
public class CommonRecyclerView extends RecyclerView {

    public static final int LIST_TYPE_NORMAL = 0;
    public static final int LIST_TYPE_VIEW_PAGER = 1;
    public static final int LIST_TYPE_GRID_VIEW = 2;

    public static final int HORIZONTAL = RecyclerView.HORIZONTAL;
    public static final int VERTICAL = RecyclerView.VERTICAL;

    private int mOrientation;//延伸方向

    public CommonRecyclerView(Context context) {
        super(context);
    }

    public CommonRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    //------------------------- 数据设置 -------------------------------------
    //---------------------滑动设置-----------start-------------

    /**
     * 是否可滑动（一般用在NestScrollView嵌套时使用，防止滑动冲突）
     *
     * @param enabled 是否可滑动
     * @return 是否可滑动
     */
    public CommonRecyclerView setOnNestedScrollingEnabled(boolean enabled) {
        setNestedScrollingEnabled(enabled);
        return this;
    }

    public CommonRecyclerView noSpring() {
        setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        return this;
    }

    //---------------------滑动设置----------end--------------
    //---------------------延伸方向设置----------start--------------

    /**
     * 设置布局方向
     *
     * @param orientation 方向
     * @return 方向
     */
    public CommonRecyclerView setOrientation(int orientation) {
        mOrientation = orientation;
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null) {
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                gridLayoutManager.setOrientation(orientation);
            } else {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                linearLayoutManager.setOrientation(orientation);
            }
            setLayoutManager(layoutManager);
        }
        return this;
    }

    //---------------------延伸方向设置----------end--------------
    //---------------------list类型设置----------start--------------

    /**
     * 设置list类型
     *
     * @param listType LIST_TYPE_NORMAL, LIST_TYPE_VIEW_PAGER, LIST_TYPE_GRID_VIEW
     * @return 设置list类型
     */
    public CommonRecyclerView setListType(int listType) {
        if (listType != LIST_TYPE_GRID_VIEW) {
            setListType(listType, 0);
        }
        return this;
    }

    /**
     * 设置list类型
     *
     * @param listType  LIST_TYPE_NORMAL, LIST_TYPE_VIEW_PAGER, LIST_TYPE_GRID_VIEW
     * @param spanCount GridView 数量
     * @return 设置list类型
     */
    public CommonRecyclerView setListType(int listType, int spanCount) {
        RecyclerView.LayoutManager layoutManager = null;
        switch (listType) {
            case LIST_TYPE_NORMAL://普通横竖展示
                layoutManager = new LinearLayoutManager(getContext());
                break;
            case LIST_TYPE_VIEW_PAGER://viewPager
                layoutManager = new LinearLayoutManager(getContext());
                PagerSnapHelper snapHelper = new PagerSnapHelper();
                snapHelper.attachToRecyclerView(this);
                break;
            case LIST_TYPE_GRID_VIEW://gridView
                layoutManager = new GridLayoutManager(getContext(), spanCount);
                break;
        }
        if (layoutManager != null) {
            setLayoutManager(layoutManager);
            setOrientation(mOrientation);
        }
        return this;
    }

    //---------------------list类型设置---------end---------------
}

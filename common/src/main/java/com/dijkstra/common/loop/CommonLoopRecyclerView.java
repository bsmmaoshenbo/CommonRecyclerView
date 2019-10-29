package com.dijkstra.common.loop;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

import com.dijkstra.common.CommonRecyclerView;

/**
 * @Description: 无限循环的RecyclerView
 * @Author: maoshenbo
 * @Date: 2018/11/7 13:19
 * @Version: 1.0
 */
public class CommonLoopRecyclerView extends CommonRecyclerView {

    public CommonLoopRecyclerView(Context context) {
        super(context);
    }

    public CommonLoopRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonLoopRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
    }
}

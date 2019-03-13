package com.dijkstra.common.loop;

import com.dijkstra.common.BaseRecyclerViewAdapter;
import com.dijkstra.common.BaseRecyclerViewHolder;

import java.util.List;

/**
 * @Description: 循环adapter
 * @Author: maoshenbo
 * @Date: 2018/12/3 18:47
 * @Version: 1.0
 */
public abstract class CommonLoopAdapter<T> extends BaseRecyclerViewAdapter<T> {

    public CommonLoopAdapter(int... layoutId) {
        super(layoutId);
    }

    @Override
    protected int initType(List<T> dataList, int[] layoutIds, int position) {
        return initViewType(dataList.get(position % getDataItemCount()), layoutIds, position);
    }

    @Override
    public int getItemCount() {
        int count = getDataItemCount();
        if (isLoop()) {
            if (count > 0) {
                return Integer.MAX_VALUE;
            }
        } else {
            return count;
        }
        return 0;
    }

    /**
     * 是否无限循环
     */
    protected abstract boolean isLoop();

    @Override
    public void bindDataList(BaseRecyclerViewHolder holder, int position, List<T> dataList) {
        bindData(holder, position, dataList.get(isLoop() ? position % getDataItemCount() : position));
    }

    @Override
    protected void bindData(BaseRecyclerViewHolder holder, int position, T data) {
    }
}

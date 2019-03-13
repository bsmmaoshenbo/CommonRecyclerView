package com.dijkstra.common;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * @Description: viewHolder
 * @Author: maoshenbo
 * @Date: 2018/10/18 18:07
 * @Version: 1.0
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> viewArray;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        viewArray = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = viewArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewArray.put(viewId, view);
        }
        return (T) view;
    }

    public View getItemView() {
        return itemView;
    }
}

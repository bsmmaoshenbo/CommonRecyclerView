package com.dijkstra.commonrecyclerview.grid;

import android.widget.TextView;

import com.dijkstra.common.BaseRecyclerViewAdapter;
import com.dijkstra.common.BaseRecyclerViewHolder;
import com.dijkstra.commonrecyclerview.R;
import com.dijkstra.commonrecyclerview.SensorInfo;

/**
 * @Description: GridViewAdapter
 * @Author: maoshenbo
 * @Date: 2018/12/4 16:21
 * @Version: 1.0
 */
public class GridViewAdapter extends BaseRecyclerViewAdapter<SensorInfo> {


    @Override
    public int[] initLayouts() {
        return new int[]{R.layout.recycler_view_grid};
    }

    @Override
    protected void bindData(BaseRecyclerViewHolder holder, int position, SensorInfo data) {
        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(data.name);
    }
}

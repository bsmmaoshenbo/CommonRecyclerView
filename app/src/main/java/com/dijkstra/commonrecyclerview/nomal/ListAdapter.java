package com.dijkstra.commonrecyclerview.nomal;

import android.view.View;
import android.widget.TextView;

import com.dijkstra.common.BaseRecyclerViewAdapter;
import com.dijkstra.common.BaseRecyclerViewHolder;
import com.dijkstra.commonrecyclerview.R;
import com.dijkstra.commonrecyclerview.SensorInfo;

/**
 * @Description: adapter
 * @Author: maoshenbo
 * @Date: 2018/11/6 11:58
 * @Version: 1.0
 */
public class ListAdapter extends BaseRecyclerViewAdapter<SensorInfo> {

    public ListAdapter(int... layoutIds) {
        super(layoutIds);
    }

    @Override
    protected int initViewType(SensorInfo data, int[] layoutIds, int position) {
        int viewType;
        if (position == 0) {
            viewType = layoutIds[0];
        } else {
            viewType = layoutIds[1];
        }
        return viewType;
    }

    @Override
    protected void bindData(BaseRecyclerViewHolder holder, int position, SensorInfo data) {
        int type = holder.getItemViewType();

        View view = holder.getItemView();
        view.setEnabled(true);

        switch (type) {
            case R.layout.recycler_view_vel:
                TextView tvName = holder.getView(R.id.tv_name);
                tvName.setText(data.name);
                break;
            case R.layout.recycler_view_vel_2:
//                tvName.setText(String.format("%s我是第二个", data.name));
                break;
        }
    }
}

package com.dijkstra.commonrecyclerview.viewpager;

import android.widget.TextView;

import com.dijkstra.common.BaseRecyclerViewHolder;
import com.dijkstra.common.loop.CommonLoopAdapter;
import com.dijkstra.commonrecyclerview.R;
import com.dijkstra.commonrecyclerview.SensorInfo;

/**
 * @Description: ViewPagerAdapter
 * @Author: maoshenbo
 * @Date: 2018/11/7 11:05
 * @Version: 1.0
 */
public class ViewPagerAdapter extends CommonLoopAdapter<SensorInfo> {


    public ViewPagerAdapter(int layoutId) {
        super(layoutId);
    }

    @Override
    protected boolean isLoop() {
        return true;
    }

    @Override
    protected void bindData(BaseRecyclerViewHolder holder, int position, SensorInfo data) {
        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(data.name);
    }
}

package com.dijkstra.commonrecyclerview.viewpager;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dijkstra.common.BaseRecyclerViewAdapter;
import com.dijkstra.common.RecycleViewItemDecoration;
import com.dijkstra.common.loop.CommonLoopRecyclerView;
import com.dijkstra.commonrecyclerview.R;
import com.dijkstra.commonrecyclerview.RViewModel;
import com.dijkstra.commonrecyclerview.SensorInfo;

import java.util.List;

/**
 * @Description: ViewPager
 * @Author: maoshenbo
 * @Date: 2018/11/7 10:58
 * @Version: 1.0
 */
public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_pager);

        final CommonLoopRecyclerView recyclerViewCenter = findViewById(R.id.recycler_view_center);

        final ViewPagerAdapter adapter = new ViewPagerAdapter();

        recyclerViewCenter.setListType(CommonLoopRecyclerView.LIST_TYPE_VIEW_PAGER)
                .setOrientation(CommonLoopRecyclerView.HORIZONTAL)
                .addItemDecoration(new RecycleViewItemDecoration(RecycleViewItemDecoration.VERTICAL_LIST, 5));

        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Toast.makeText(ViewPagerActivity.this, "我点击了页面，你信吗", Toast.LENGTH_SHORT).show();
            }
        });

        RViewModel viewModel = ViewModelProviders.of(this).get(RViewModel.class);
        viewModel.getCenterLiveData.observe(this, new Observer<List<SensorInfo>>() {
            @Override
            public void onChanged(@Nullable List<SensorInfo> sensorInfos) {
                adapter.setData(sensorInfos);
                recyclerViewCenter.setAdapter(adapter);
            }
        });
        viewModel.getLiveData().setValue(true);
    }
}

package com.dijkstra.commonrecyclerview;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dijkstra.common.AllRecyclerView;
import com.dijkstra.common.CommonRecyclerView;
import com.dijkstra.commonrecyclerview.viewpager.ViewPagerAdapter;

import java.util.List;

/**
 * @Description:
 * @Author: maoshenbo
 * @Date: 2018/12/14 下午6:43
 * @Version: 1.0
 */
public class AllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        final AllRecyclerView recyclerViewAll = findViewById(R.id.recycler_view_all);
        recyclerViewAll
                .noSpring()
                .setListType(CommonRecyclerView.LIST_TYPE_VIEW_PAGER)
                .setOrientation(CommonRecyclerView.VERTICAL);


//        final ListAdapter adapter = new ListAdapter(R.layout.recycler_view_vel, R.layout.recycler_view_vel_2);
//        final GridViewAdapter gridViewAdapter = new GridViewAdapter(R.layout.recycler_view_grid);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(R.layout.recycler_view_pager);


        RViewModel viewModel = ViewModelProviders.of(this).get(RViewModel.class);
        viewModel.getCenterLiveData.observe(this, new Observer<List<SensorInfo>>() {
            @Override
            public void onChanged(@Nullable List<SensorInfo> sensorInfos) {
                adapter.setData(sensorInfos);
                recyclerViewAll.setAdapter(adapter);
            }
        });
        viewModel.getLiveData().setValue(true);
    }
}

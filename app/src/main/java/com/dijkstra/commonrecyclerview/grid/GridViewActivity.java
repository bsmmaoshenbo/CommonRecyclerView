package com.dijkstra.commonrecyclerview.grid;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dijkstra.common.CommonRecyclerView;
import com.dijkstra.common.grid.GridSpaceItemDecoration;
import com.dijkstra.commonrecyclerview.R;
import com.dijkstra.commonrecyclerview.RViewModel;
import com.dijkstra.commonrecyclerview.SensorInfo;

import java.util.List;

/**
 * @Description: GridViewActivity
 * @Author: maoshenbo
 * @Date: 2018/12/4 16:17
 * @Version: 1.0
 */
public class GridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grid_view);

        CommonRecyclerView recyclerGridView = findViewById(R.id.recycler_grid_view);

        final GridViewAdapter adapter = new GridViewAdapter(R.layout.recycler_view_grid);

        recyclerGridView
                .noSpring()
                .setListType(CommonRecyclerView.LIST_TYPE_GRID_VIEW, 4)
                .setOnNestedScrollingEnabled(false)
                .setOrientation(CommonRecyclerView.VERTICAL)
                .setAdapter(adapter);

        recyclerGridView.addItemDecoration(new GridSpaceItemDecoration(4, 5, 5, false));


        RViewModel viewModel = ViewModelProviders.of(this).get(RViewModel.class);
        viewModel.getCenterLiveData.observe(this, new Observer<List<SensorInfo>>() {
            @Override
            public void onChanged(@Nullable List<SensorInfo> sensorInfos) {
                adapter.setData(sensorInfos);
            }
        });
        viewModel.getLiveData().setValue(true);
    }
}

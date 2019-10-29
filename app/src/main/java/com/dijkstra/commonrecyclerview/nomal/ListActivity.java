package com.dijkstra.commonrecyclerview.nomal;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import com.dijkstra.common.BaseRecyclerViewAdapter;
import com.dijkstra.common.CommonRecyclerView;
import com.dijkstra.common.RecycleViewItemDecoration;
import com.dijkstra.commonrecyclerview.R;
import com.dijkstra.commonrecyclerview.RViewModel;
import com.dijkstra.commonrecyclerview.SensorInfo;

import java.util.List;

/**
 * @Description: 普通list
 * @Author: maoshenbo
 * @Date: 2018/11/6 16:01
 * @Version: 1.0
 */
public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_list);


        final CommonRecyclerView recyclerViewCenter = findViewById(R.id.recycler_view_center);

        recyclerViewCenter.setOnNestedScrollingEnabled(false)
                .noSpring()
                .setListType(CommonRecyclerView.LIST_TYPE_NORMAL)
                .setOrientation(CommonRecyclerView.VERTICAL)
                .addItemDecoration(new RecycleViewItemDecoration(RecycleViewItemDecoration.VERTICAL_LIST, dp2px(this, 5)));

        final ListAdapter adapter = new ListAdapter();
        recyclerViewCenter.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                SensorInfo sensorInfo = adapter.getData().get(position);
                sensorInfo.name = "小马";
            }
        });
        adapter.setOnItemLongClickListener(new BaseRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClickListener(View v, int position) {

            }
        });

        Button headerView = new Button(this);
        headerView.setText("点我加一个条目");
        adapter.addHeaderView(headerView);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.insertItem(new SensorInfo("gyroscope", "陀螺仪", R.mipmap.ic_launcher_round), 1);
            }
        });


        Button footerView = new Button(this);
        footerView.setText("点我减一个条目");
        adapter.addFooterView(footerView);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeItem(3);
            }
        });

        RViewModel viewModel = ViewModelProviders.of(this).get(RViewModel.class);
        viewModel.getCenterLiveData.observe(this, new Observer<List<SensorInfo>>() {
            @Override
            public void onChanged(@Nullable List<SensorInfo> sensorInfos) {
                adapter.setData(sensorInfos);
                adapter.refreshData(sensorInfos, false);
            }
        });
        viewModel.getLiveData().setValue(true);
    }

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}

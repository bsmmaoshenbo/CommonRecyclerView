package com.dijkstra.commonrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dijkstra.commonrecyclerview.grid.GridViewActivity;
import com.dijkstra.commonrecyclerview.nomal.ListActivity;
import com.dijkstra.commonrecyclerview.viewpager.LoopViewPagerActivity;
import com.dijkstra.commonrecyclerview.viewpager.ViewPagerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tvList = findViewById(R.id.tv_list);
        Button tvGridView = findViewById(R.id.tv_grid_view);
        Button tvViewPager = findViewById(R.id.tv_view_pager);
        Button tvLoopViewPager = findViewById(R.id.tv_loop_view_pager);
        Button tvAll = findViewById(R.id.tv_all);


        tvList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        tvGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
                startActivity(intent);
            }
        });
        tvViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
            }
        });
        tvLoopViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoopViewPagerActivity.class);
                startActivity(intent);
            }
        });
        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllActivity.class);
                startActivity(intent);
            }
        });
    }
}

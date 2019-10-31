# CommonRecyclerView
通用的RecyclerView，实现了RecyclerView、GridView、ViewPager功能

How to
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.bsmmaoshenbo:CommonRecyclerView:1.1.0'
	}

使用：
1. xml：直接xml引用


	    <?xml version="1.0" encoding="utf-8"?>
	    <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
    
            <com.dijkstra.common.CommonRecyclerView
                android:id="@+id/recycler_view_center"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:layout_constraintLeft_toLeftOf="parent"
                app:layout_constraintRight_toRightOf="parent"
                app:layout_constraintTop_toTopOf="parent" />
	    </android.support.constraint.ConstraintLayout>
    
    对于androidx版本：
        
        
        <?xml version="1.0" encoding="utf-8"?>
        <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
        
            <com.dijkstra.common.CommonRecyclerView
                android:id="@+id/recycler_view_center"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:layout_constraintLeft_toLeftOf="parent"
                app:layout_constraintRight_toRightOf="parent"
                app:layout_constraintTop_toTopOf="parent" />
        </androidx.constraintlayout.widget.ConstraintLayout>
2. 代码调用：以垂直list为例
    
    
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
                recyclerViewCenter.setAdapter(adapter);//设置adapter
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
                    adapter.insertItem(new SensorInfo("gyroscope", "陀螺仪", R.mipmap.ic_launcher_round), 1);//增加条目
                }
                });
    
    
                Button footerView = new Button(this);
                footerView.setText("点我减一个条目");
                adapter.addFooterView(footerView);
                footerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.removeItem(3);//删除条目
                }
                });
    
                RViewModel viewModel = ViewModelProviders.of(this).get(RViewModel.class);
                viewModel.getCenterLiveData.observe(this, new Observer<List<SensorInfo>>() {
                @Override
                public void onChanged(@Nullable List<SensorInfo> sensorInfos) {
                    adapter.setData(sensorInfos);//填充数据
                    adapter.refreshData(sensorInfos, false);
                }
                });
                viewModel.getLiveData().setValue(true);
            }
    
            public static int dp2px(Context context, int dp) {
                return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
            }
	    }

3. adapter:重写三个方法


        /**
         * @Description: adapter
         * @Author: maoshenbo
         * @Date: 2018/11/6 11:58
         * @Version: 1.0
         */
        public class ListAdapter extends BaseRecyclerViewAdapter<SensorInfo> {
        
            @Override
            public int[] initLayouts() {
                return new int[]{R.layout.recycler_view_vel, R.layout.recycler_view_vel_2};
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

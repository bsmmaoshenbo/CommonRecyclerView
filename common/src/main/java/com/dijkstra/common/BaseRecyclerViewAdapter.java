package com.dijkstra.common;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: adapter
 * @Author: maoshenbo
 * @Date: 2018/10/18 17:10
 * @Version: 1.0
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private final int TYPE_HEADER = 1 << 4;
    private final int TYPE_FOOTER = TYPE_HEADER + 1;
    private int[] mLayoutIds;

    private View VIEW_FOOTER;
    private View VIEW_HEADER;

    private List<T> mDataList;

    private OnItemClickListener mOnItemClickListener;//单击事件
    private OnItemLongClickListener mOnItemLongClickListener;//长按单击事件

    public BaseRecyclerViewAdapter(int... layoutIds) {
        this.mLayoutIds = layoutIds;
    }

    public void setData(List<T> dataList) {
        this.mDataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (isHeaderView(position)) {
            viewType = TYPE_HEADER;
        } else if (isFooterView(position)) {
            viewType = TYPE_FOOTER;
        } else {
            if (haveHeaderView()) {
                position--;
            }
            viewType = initType(mDataList, mLayoutIds, position);
        }
        return viewType;
    }

    protected int initType(List<T> dataList, int[] layoutIds, int position) {
        return initViewType(dataList.get(position), layoutIds, position);
    }

    protected int initViewType(T data, int[] mLayoutIds, int position) {
        return mLayoutIds[0];
    }

    public int getDataItemCount() {
        return mDataList != null && !mDataList.isEmpty() ? mDataList.size() : 0;
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = VIEW_HEADER;
        } else if (viewType == TYPE_FOOTER) {
            view = VIEW_FOOTER;
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        }
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseRecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (isHeaderView(position) || isFooterView(position)) return;
        if (haveHeaderView()) {
            position--;
        }
        bindDataList(holder, position, mDataList);
        final int finalPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClickListener(holder.getItemView(), finalPosition);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onItemLongClickListener(holder.getItemView(), finalPosition);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = getDataItemCount();
        if (VIEW_HEADER != null) {
            count++;
        }
        if (VIEW_FOOTER != null) {
            count++;
        }
        return count;
    }

    /**
     * 带全部数据
     *
     * @param holder   holder
     * @param position 位置
     * @param dataList 数据
     */
    public void bindDataList(BaseRecyclerViewHolder holder, int position, List<T> dataList) {
        bindData(holder, position, dataList.get(position));
    }

    /**
     * 带单个数据
     *
     * @param holder   holder
     * @param position 位置
     * @param data     数据
     */
    protected abstract void bindData(BaseRecyclerViewHolder holder, int position, T data);

    //---------------处理头尾------start-------------
    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("headerView has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
//            ifGridLayoutManager();
            notifyItemInserted(0);
        }
    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
//            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }
    //--------------处理头尾---------end----------------
    //---------------------item增删减操作-------------start-------------

    public void insertItem(T data, int position) {
        if (mDataList != null) {
            mDataList.add(position, data);
            notifyItemInserted(position);
        }
    }

    public void removeItem(int position) {
        if (mDataList != null) {
            mDataList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateItem(int position) {
        notifyItemChanged(position);
    }

    public void refreshData(List<T> dateList, boolean isLoadMore) {
        if (mDataList != null) {
            List<T> list = new ArrayList<>(dateList);
            if (!isLoadMore) {
                mDataList.clear();
            }
            mDataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    //---------------------item增删减操作------------end-------------

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View v, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClickListener(View v, int position);
    }
}

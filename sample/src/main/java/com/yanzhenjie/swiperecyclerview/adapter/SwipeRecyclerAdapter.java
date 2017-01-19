package com.yanzhenjie.swiperecyclerview.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.BaseSwipeViewHolder;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 结合SwipeRecyclerView的SwipeMenuAdapter和EasyRecyclerView中的BaseViewHolder实现的持有者和适配器
 * 注：和BaseSwipeViewHolder
 *
 * @param <T>
 */
public abstract class SwipeRecyclerAdapter<T> extends SwipeMenuAdapter<BaseSwipeViewHolder> {
    private List<T> datas;

    public SwipeRecyclerAdapter(List<T> datas) {
        super();
        this.datas = datas;
        if (this.datas == null)
            this.datas = new ArrayList<>();
    }

    public SwipeRecyclerAdapter() {
        super();
        this.datas = new ArrayList<>();
    }

    @Override
    public final void onBindViewHolder(BaseSwipeViewHolder holder, int position) {
        holder.setData(datas.get(position));
    }

    abstract public BaseSwipeViewHolder onCompatCreateViewHolder(ViewGroup realParent, int viewType);

    @Override
    public final BaseSwipeViewHolder onCompatCreateViewHolder(View realContentView, int viewType){
        return onCompatCreateViewHolder((ViewGroup) realContentView, viewType);
    }

    public T getData(int position) {
        if (position < datas.size())
            return datas.get(position);
        else
            return null;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setDatas(List<T> mDatas) {
        this.datas = mDatas;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void updateAdapter(List<T> mDatas) {
        this.datas = mDatas;
        notifyDataSetChanged();
    }

}

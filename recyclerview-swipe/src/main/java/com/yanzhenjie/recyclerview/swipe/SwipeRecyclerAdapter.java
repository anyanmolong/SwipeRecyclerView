package com.yanzhenjie.recyclerview.swipe;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 结合SwipeRecyclerView的SwipeMenuAdapter和EasyRecyclerView中的BaseViewHolder实现的持有者和适配器
 * 注：和BaseSwipeViewHolder
 *
 * Created by YXC on 2017/1/19.
 *
 * @param <T>
 */
public abstract class SwipeRecyclerAdapter<T> extends SwipeMenuAdapter<SwipeViewHolder> {
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
    public final void onBindViewHolder(SwipeViewHolder holder, int position) {
        holder.setData(datas.get(position));
    }

    abstract public SwipeViewHolder onCompatCreateViewHolder(ViewGroup realParent, int viewType);

    @Override
    public final SwipeViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
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

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void updateAdapter(List<T> datas) {
        setDatas(datas);
        notifyDataSetChanged();
    }

}

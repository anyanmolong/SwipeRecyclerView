package com.yanzhenjie.swiperecyclerview.viewholder;

import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.SwipeViewHolder;
import com.yanzhenjie.swiperecyclerview.R;

/**
 * NoLoadUrlImageViewHolder
 * 该在构造函数中没有实现父类带LoadImg接口的构造方法：
 * "super(ViewGroup parent, @LayoutRes int res, LoadUrlImageSupport loadUrlImageSupport)"
 * Created by ayxc on 2017/2/24.
 */
public class NoLoadUrlImageViewHolder extends SwipeViewHolder<String> {

    public NoLoadUrlImageViewHolder(ViewGroup parent) {
        super(parent, R.layout.item);
    }

    @Override
    public void setData(final String data) {
        setText(R.id.tv_title, NoLoadUrlImageViewHolder.class.getSimpleName() + data);
    }


}
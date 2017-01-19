package com.yanzhenjie.swiperecyclerview.base.recyclerview;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.SwipeViewHolder;

/**
 *
 * Created by Yxc on 2017/1/19.
 */
public class BaseViewHolder<M> extends SwipeViewHolder<M> {

    public BaseViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res, new GlideLoadUrlImageSupport(parent.getContext()));
    }

}

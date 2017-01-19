package com.yanzhenjie.swiperecyclerview.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.yanzhenjie.swiperecyclerview.R;
import com.yanzhenjie.swiperecyclerview.base.recyclerview.BaseViewHolder;

/**
 * Created by Mr.Jude on 2016/1/6.
 */
public class AdViewHolder extends BaseViewHolder<String>{

    public AdViewHolder(ViewGroup parent) {
        super(parent, R.layout.item);
    }

    @Override
    public void setData(final String data) {
//        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
//        this.tvTitle.setText(AdViewHolder.class.getSimpleName() + data);
        setText(R.id.tv_title, AdViewHolder.class.getSimpleName() + data);
    }

    TextView tvTitle;

}
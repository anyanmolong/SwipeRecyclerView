package com.yanzhenjie.swiperecyclerview.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanzhenjie.recyclerview.swipe.BaseSwipeViewHolder;
import com.yanzhenjie.swiperecyclerview.R;
import com.yanzhenjie.swiperecyclerview.listener.OnItemClickListener;

/**
 * Created by Mr.Jude on 2016/1/6.
 */
public class AdViewHolder extends BaseSwipeViewHolder<String> implements View.OnClickListener {


    public AdViewHolder(ViewGroup parent) {
        super(parent, R.layout.item);
//        ImageView imageView = (ImageView) itemView;
//        imageView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)Utils.convertDpToPixel(156,getContext())));
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public void setData(final String data) {
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        this.tvTitle.setText(data);
    }

    TextView tvTitle;
    OnItemClickListener mOnItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }
}
package com.yanzhenjie.swiperecyclerview.adapter;

import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.SwipeViewHolder;
import com.yanzhenjie.recyclerview.swipe.SwipeRecyclerAdapter;
import com.yanzhenjie.swiperecyclerview.R;
import com.yanzhenjie.swiperecyclerview.viewholder.AdViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */
//public class TestMenu2Adapter extends SwipeRecyclerAdapter{
//
//    @Override
//    public RecyclerView.ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//    }
//}

public class TestMenu2Adapter extends SwipeRecyclerAdapter<String> {

    public TestMenu2Adapter(List<String> mStrings) {
        super(mStrings);
    }

    @Override
    public SwipeViewHolder onCompatCreateViewHolder(ViewGroup realParent, int viewType) {
        switch (viewType) {
            case 1:
                return new AdViewHolder(realParent);
            default:
                return new SwipeViewHolder<String>(realParent, R.layout.item) {
                    @Override
                    public void setData(String data) {
                        setText(R.id.tv_title, SwipeViewHolder.class.getName() + data);
                    }
                };
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }
}

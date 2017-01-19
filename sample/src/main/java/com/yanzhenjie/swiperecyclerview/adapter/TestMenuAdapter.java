/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.swiperecyclerview.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.yanzhenjie.swiperecyclerview.listener.OnItemClickListener;
import com.yanzhenjie.swiperecyclerview.viewholder2.AdViewHolder;

import java.util.List;

/**
 * Created by YOLANDA on 2016/7/22.
 */
public class TestMenuAdapter extends SwipeMenuAdapter<AdViewHolder> {

    private List<String> titles;

    private OnItemClickListener mOnItemClickListener;

    public TestMenuAdapter(List<String> titles) {
        this.titles = titles;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public AdViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new AdViewHolder((ViewGroup) realContentView);
    }

    @Override
    public void onBindViewHolder(AdViewHolder holder, int position) {
        holder.setData(titles.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }


}

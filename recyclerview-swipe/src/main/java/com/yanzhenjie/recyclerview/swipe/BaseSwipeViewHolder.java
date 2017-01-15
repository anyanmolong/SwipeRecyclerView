package com.yanzhenjie.recyclerview.swipe;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * M为这个itemView对应的model。
 * 使用RecyclerArrayAdapter就一定要用这个ViewHolder。
 * 这个ViewHolder将ItemView与Adapter解耦。
 * 推荐子类继承第二个构造函数。并将子类的构造函数设为一个ViewGroup parent。
 * 然后这个ViewHolder就完全独立。adapter在new的时候只需将parentView传进来。View的生成与管理由ViewHolder执行。
 * 实现setData来实现UI修改。Adapter会在onCreateViewHolder里自动调用。
 * <p>
 * 在一些特殊情况下，只能在setData里设置监听。
 *
 * @param <M>
 */
abstract public class BaseSwipeViewHolder<M> extends BaseViewHolder<M> {

    private SparseArray<View> mViews;

    /**
     * 对SwipeMenuAdapter原先的写法做兼容， 考虑是否要删掉
     * @param itemView
     */
    @Deprecated
    public BaseSwipeViewHolder(View itemView) {
        super(itemView);
    }

    public BaseSwipeViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(compatCreateView(parent, res));
        this.mViews = new SparseArray<>();
    }

    private final static View compatCreateView(ViewGroup parent, @LayoutRes int res) {
        if (parent instanceof SwipeMenuLayout) {
            ((ViewGroup) parent.findViewById(R.id.swipe_content)).addView(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
        } else {
            return LayoutInflater.from(parent.getContext()).inflate(res, parent, false);
        }
        return parent;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T1 extends View> T1 findView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = $(viewId);
            mViews.put(viewId, view);
        }
        return (T1) view;
    }

    /**
     * 通过控件的Id返回子控件TextView的字符串
     *
     * @param viewId
     * @return
     */
    public CharSequence getText(int viewId) {
        TextView view = findView(viewId);
        return view.getText();
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public void setText(int viewId, CharSequence text) {
        TextView view = findView(viewId);
        view.setText(TextUtils.isEmpty(text) ? "" : text);

    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param resId
     * @return
     */
    public void setText(int viewId, @StringRes int resId) {
        TextView view = findView(viewId);
        view.setText(resId);
    }

    /**
     * @param viewId
     * @param visibility
     * @return
     */
    public void setVisibility(int viewId, int visibility) {
        findView(viewId).setVisibility(visibility);

    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @return
     */
    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        findView(viewId).setOnClickListener(listener);

    }

    /**
     * 设置字体颜色
     */
    public void setTextColor(int viewId, int color) {
        TextView view = findView(viewId);
        view.setTextColor(color);

    }

    /**
     * 设置激活
     */
    public void setEnabled(int viewId, boolean isEnabled) {
        View view = findView(viewId);
        view.setEnabled(isEnabled);

    }

    /**
     *
     */
    public void setChecked(int viewId, boolean isChecked) {
        CheckBox view = findView(viewId);
        view.setChecked(isChecked);

    }

    /**
     * 为TextView设置字符串
     */
    public void setHint(int viewId, String text) {
        TextView view = findView(viewId);
        view.setHint(text);

    }

    /**
     * 为ImageView设置图片
     */
    public void setBackgroundResource(int viewId, @DrawableRes int drawableId) {
        View view = findView(viewId);
        view.setBackgroundResource(drawableId);

    }

    /**
     * 为ImageView设置图片
     */
    public void setImageResource(int viewId, @DrawableRes int drawableId) {
        ImageView view = findView(viewId);
        view.setImageResource(drawableId);

    }

    /**
     * 为ImageView设置图片
     */
    public void setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = findView(viewId);
        view.setImageBitmap(bm);

    }

    /**
     * 为ImageView设置图片
     */
    public void setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = findView(viewId);
        view.setImageDrawable(drawable);

    }

//    /**
//     * 为ImageView设置图片
//     */
//    public void setImageByUrl(int viewId, String url) {
//        url = getImageUrl(url);
//        ImageUtil.setImageByUrl(findView(viewId), url);
//    }
//
//    /**
//     * 为ImageView设置图片
//     *
//     * @param viewId
//     * @return
//     */
//    public void setImageByUrl(int viewId, int imageRid, String url) {
//        url = getImageUrl(url);
//        ImageUtil.setImageByUrl(findView(viewId), url);
//
//    }
//
//    /**
//     * 为ImageView设置图片
//     *
//     * @param viewId
//     * @return
//     */
//    public void setImageByUrl(int viewId, String url, int imageRid) {
//        url = getImageUrl(url);
//        ImageUtil.setImageByUrl(findView(viewId), url);
//
//    }
//
//    /**
//     * 为ImageView设置圆角图片
//     *
//     * @param viewId
//     * @return
//     */
//    public void setImageByUrlAndRoundCorner(int viewId, String url) {
//        if (url == null)
//            url = getImageUrl(url);
//        ImageUtil.setImageByUrlByResource(findView(viewId), url);
//
//    }
//
//    /**
//     * 为ImageView设置圆角图片
//     *
//     * @param viewId
//     * @return
//     */
//    public void setImageByUrlAndRoundCorner(int viewId, String url, int defaultImgId) {
//        if (url == null)
//            url = getImageUrl(url);
//        ImageUtil.setImageByUrlByResource(findView(viewId), url);
//
//    }
//
//    public String getImageUrl(String url) {
//        return (url.indexOf("/") == 0) ? SystemConst.BASE_URL + url : url;
//    }

}
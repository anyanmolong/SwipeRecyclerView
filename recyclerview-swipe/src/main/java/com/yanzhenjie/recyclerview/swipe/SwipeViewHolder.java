package com.yanzhenjie.recyclerview.swipe;


import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * M为这个itemView对应的model。
 * 使用SwipeRecyclerAdapter就一定要用这个ViewHolder。
 * 这个ViewHolder将ItemView与Adapter解耦。
 * <p>
 * 与BaseViewHolder不同，只有this(ViewGroup parent, @LayoutRes int res)一个构造函数。
 * 然后这个ViewHolder就完全独立。adapter在new的时候只需将realParentView传进来。View的生成与管理由ViewHolder执行。
 * 实现setData来实现UI修改。Adapter会在onCreateViewHolder里自动调用。
 * <p>
 * (目前只能在setData里设置监听。
 * <p>
 * * 继承于BaseViewHolder带来了三个受保护的方法
 * protected Context getContext()
 * protected <T extends RecyclerView.Adapter> T getOwnerAdapter()
 * protected RecyclerView getOwnerRecyclerView()
 * <p>
 * ViewHolder中参考了一些base-adapter-helper的辅助方法写法
 *
 * Created by YXC on 2017/1/19.
 *
 * @param <M>
 */
abstract public class SwipeViewHolder<M> extends BaseViewHolder<M> {

    static final String TAG = SwipeViewHolder.class.getSimpleName();

    private SparseArray<View> mViews;
    private LoadUrlImageSupport loadUrlImageSupport;

    public SwipeViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(compatCreateView(parent, res));
        this.mViews = new SparseArray<>();
    }

    public SwipeViewHolder(ViewGroup parent, @LayoutRes int res, LoadUrlImageSupport loadUrlImageSupport) {
        this(parent, res);
        this.loadUrlImageSupport = loadUrlImageSupport;
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
    public <T1 extends View> T1 getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = $(viewId);
            mViews.put(viewId, view);
        }
        return (T1) view;
    }

    /* view */

    /**
     * @param viewId
     * @param visibility
     * @return
     */
    public void setVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
    }

    public void setBackgroundColor(int viewId, int color) {
        getView(viewId).setBackgroundColor(color);
    }

    public void setBackgroundRes(int viewId, int backgroundRes) {
        getView(viewId).setBackgroundResource(backgroundRes);
    }

    public void setEnabled(int viewId, boolean isEnabled) {
        getView(viewId).setEnabled(isEnabled);
    }

    public void setTag(int viewId, Object tag) {
        getView(viewId).setTag(tag);
    }

    public void setTag(int viewId, int key, Object tag) {
        getView(viewId).setTag(key, tag);
    }

    /**
     * Add an action to set the alpha of a view. Can be called multiple times.
     * Alpha between 0-1.
     */
    public void setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        getView(viewId).setOnClickListener(listener);
    }

    public void setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        getView(viewId).setOnLongClickListener(listener);
    }

    /* TextView */

    /**
     * 通过控件的Id返回子控件TextView的字符串
     *
     * @param viewId
     * @return
     */
    public CharSequence getText(@IdRes int viewId) {
        TextView view = getView(viewId);
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
        TextView view = getView(viewId);
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
        TextView view = getView(viewId);
        view.setText(resId);
    }

    /**
     * 设置字体颜色
     */
    public void setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
    }

    /**
     * 设置字体颜色
     */
    public void setTextColorRes(int viewId, @ColorRes int colorRes) {
        if (Build.VERSION.SDK_INT >= 23) {
            setTextColor(viewId, ContextCompat.getColor(getContext(), colorRes));
        } else {
            setTextColor(viewId, getContext().getResources().getColor(colorRes));
        }
    }

    /**
     * 为TextView设置字符串
     */
    public void setHint(int viewId, String text) {
        TextView view = getView(viewId);
        view.setHint(text);
    }

    /**
     * 添加链接到一个TextView
     **/
    public void linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
    }

    /**
     * 给指定viewId的view设置字体, 并启用亚像素(subpixel)渲染
     */
    public void setTypeface(int viewId, Typeface typeface) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    public void setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            setTypeface(typeface, viewId);
        }
    }

    /**
     * ImageView
     **/

    public void setImageResource(int viewId, @DrawableRes int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
    }

    public void setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
    }

    public void setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
    }

    /**
     * 为ImageView设置url图片
     */
    public void setImageUrl(int viewId, String url) {
        if (loadUrlImageSupport != null) {
            loadUrlImageSupport.loadImageUrl(((ImageView) getView(viewId)), loadUrlImageSupport.getImageUrl(url));
        } else {
            Log.e(TAG, "警告！LoadUrlImageSupport不能为空！ " + this.getClass().getName() + ".setImageUrl() LoadUrlImageSupport:" + loadUrlImageSupport);
        }
    }

    /**
     * 扩展，如需要对图片加载做针对性特殊处理
     *
     * @param viewId
     * @return
     */
    public void setImageBuilder(int viewId, String url, Object object) {
        if (loadUrlImageSupport != null) {
            loadUrlImageSupport.loadImageBuilder(((ImageView) getView(viewId)), loadUrlImageSupport.getImageUrl(url), object);
        } else {
            Log.e(TAG, "警告！LoadUrlImageSupport不能为空！ " + this.getClass().getName() + ".setImageBuilder() LoadUrlImageSupport:" + loadUrlImageSupport);
        }
    }

    public LoadUrlImageSupport getLoadUrlImageSupport() {
        return loadUrlImageSupport;
    }

    public void setLoadUrlImageSupport(LoadUrlImageSupport loadUrlImageSupport) {
        this.loadUrlImageSupport = loadUrlImageSupport;
    }

    /** ProgressBar **/

    /**
     * Sets the progress of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     * @return The BaseAdapterHelper for chaining.
     */
    public void setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
    }

    /**
     * Sets the progress and max of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     * @param max      The max value of a ProgressBar.
     * @return The BaseAdapterHelper for chaining.
     */
    public void setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
    }

    /**
     * Sets the range of a ProgressBar to 0...max.
     *
     * @param viewId The view id.
     * @param max    The max value of a ProgressBar.
     * @return The BaseAdapterHelper for chaining.
     */
    public void setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
    }

    /** RatingBar **/

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @return The BaseAdapterHelper for chaining.
     */
    public void setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
    }

    /**
     * Sets the rating (the number of stars filled) and max of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @param max    The range of the RatingBar to 0...max.
     * @return The BaseAdapterHelper for chaining.
     */
    public void setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
    }

    /** AdapterView **/

    /**
     * Sets the listview or gridview's item click listener of the view
     *
     * @param viewId   The view id.
     * @param listener The item on click listener;
     * @return The BaseAdapterHelper for chaining.
     */
    public void setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemClickListener(listener);
    }

    /**
     * Sets the listview or gridview's item long click listener of the view
     *
     * @param viewId   The view id.
     * @param listener The item long click listener;
     * @return The BaseAdapterHelper for chaining.
     */
    public void setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemLongClickListener(listener);
    }

    /**
     * Sets the listview or gridview's item selected click listener of the view
     *
     * @param viewId   The view id.
     * @param listener The item selected click listener;
     * @return The BaseAdapterHelper for chaining.
     */
    public void setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemSelectedListener(listener);
    }

    /**
     * Sets the adapter of a adapter view.
     *
     * @param viewId  The view id.
     * @param adapter The adapter;
     * @return The BaseAdapterHelper for chaining.
     */
    public void setAdapter(int viewId, Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter(adapter);
    }

    /** 其他View **/

    /**
     * Sets the checked status of a checkable.
     *
     * @param viewId  The view id.
     * @param checked The checked status;
     * @return The BaseAdapterHelper for chaining.
     */
    public void setChecked(int viewId, boolean checked) {
        View view = getView(viewId);
        // View unable cast to Checkable
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        }
    }


}
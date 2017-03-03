package com.yanzhenjie.swiperecyclerview.base.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.yanzhenjie.recyclerview.swipe.LoadUrlImageSupport;

/**
 * 实现Glide图片加载框架的接口
 */
public class GlideLoadUrlImageSupport extends LoadUrlImageSupport {

    public GlideLoadUrlImageSupport(Context context) {
        super(context);
    }

    @Override
    public void loadImageUrl(ImageView view, String url) {
        // 要根据view针对性加载不同大小图片就在这里处理
//        Glide.with(getContext())
//                .load(url)
//                .into(view);
        load(Glide.with(getContext()), view, url);
    }

    @Override
    public void loadImageBuilder(ImageView view, String url, Object object) {

        if (object instanceof Activity) {
            load(Glide.with((Activity) object), view, url);
            return;
        }
        if (object instanceof FragmentActivity) {
            load(Glide.with((FragmentActivity) object), view, url);
            return;
        }
        if (object instanceof Fragment) {
            load(Glide.with((Fragment) object), view, url);
            return;
        }
        if (object instanceof android.app.Fragment) {
            load(Glide.with((android.app.Fragment) object), view, url);
            return;
        }

        loadImageUrl(view, url);
    }

    private void load(RequestManager requestManager, ImageView view, String url) {
        requestManager.load(url)
                .into(view);
    }

    /**
     * 钩子方法(可以不重写)
     * <p>
     * 演示：
     * 对出现半截的url时进行url补全处理
     *
     * @param url
     * @return
     */
    @Override
    public String getImageUrl(String url) {
        return (url.indexOf("/") == 0) ? "http:ww.baidu.com/" + url : url;
    }

}

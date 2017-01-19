package com.yanzhenjie.recyclerview.swipe;

import android.content.Context;
import android.widget.ImageView;

/**
 * 加载网络图片方法的抽象，
 * 用于需要替换图片加载框架等情况
 */
public abstract class LoadUrlImageSupport {
    private Context context;

    public LoadUrlImageSupport(Context context) {
        this.context = context;
    }

    abstract public void loadImageUrl(ImageView view, String url);

    abstract public void loadImageBuilder(ImageView view, String url, Object object);

    /**
     * 钩子方法<p>
     * 可以在这里对url做处理，例如对半截的url添加主机头等
     *
     * @param url
     * @return
     */
    public String getImageUrl(String url) {
//        return (url.indexOf("/") == 0) ? SystemConst.BASE_URL + url : url;
        return url;
    }

    public Context getContext() {
        return context;
    }
}
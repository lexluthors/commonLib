package com.mycp.mylibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;


public class AppUtil {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private AppUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        AppUtil.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    //token过期
    public static final String TOKEN_OUT_TIME = "token_out_time";
}

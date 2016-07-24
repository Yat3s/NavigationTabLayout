package com.yat3s.navigationtab;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Yat3s on 11/15/15.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class OPDisplayUtil {

    /**
     * 获取手机屏幕高度,以px为单位
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * 获取手机屏幕宽度，以px为单位
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 返回程序window宽度
     *
     * @return
     */
    public static int getWindowWidth(Activity activity) {
        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getWidth();
    }

    /**
     * 返回程序window高度，不包括通知栏和标题栏
     *
     * @return
     */
    public static int getWindowContentHeight(Activity activity) {
        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getHeight();
    }

    /**
     * 返回程序window高度，不包括通知栏
     *
     * @return
     */
    public static int getWindowHeight(Activity activity) {
        return getScreenHeight(activity) - getStatusBarHeight(activity);
    }

    /**
     * 返回屏幕像素密度
     *
     * @param context
     * @return
     */
    public static float getPixelDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 返回状态栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect outRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        return outRect.top;
    }

    public static int getTitleBarHeight(Activity activity) {
        return getScreenHeight(activity) - getWindowContentHeight(activity) - getStatusBarHeight(activity);
    }

    /**
     * 单位转换，将dip转换为px
     *
     * @param dp
     * @return
     */
    public static int dip2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    public static int getWidthWithPercentInScreen(Context context, int percent) {
        return (getScreenWidth(context) * percent) / 100;
    }

    public static int getHeightWithPercentInScreen(Context context, int percent) {
        return (getScreenHeight(context) * percent) / 100;
    }
}

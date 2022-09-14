package com.dragon.toolbox.hs;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by longlongren on 2018/8/14.
 * <p>
 * introduce:像素单位转换工具
 */

public class DensityUtils {

    /**
     * dp值转化成px
     *
     * @param px
     * @return
     */
    public static int px2dp(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }

    /**
     * dp值转化成px
     *
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    /**
     * sp值转化成px
     *
     * @param sp
     * @return
     */
    public static int sp2px(Context context, int sp) {
        float density = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * density + 0.5f);
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return width;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenheight(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int height = dm.heightPixels;
        return height;
    }

}

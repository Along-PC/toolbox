package com.dragon.toolbox.chartlet.utils;

import android.graphics.Paint;
import android.graphics.Rect;

public class CanvasUtils {

    private static Rect measureRect= new Rect();

    public static int getTextLength(Paint paint, String content){
        int start=0;
        int end=content.length();
        paint.getTextBounds(content,start,end, measureRect);
        return measureRect.width();
    }

    public static int getTextYOffset(Paint paint){
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float textHeight = fontMetrics.bottom - fontMetrics.top;
        int yOffset = (int) (fontMetrics.bottom - textHeight / 2);
        return -yOffset;
    }

}

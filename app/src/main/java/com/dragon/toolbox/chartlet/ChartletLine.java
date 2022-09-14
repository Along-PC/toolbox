package com.dragon.toolbox.chartlet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.dragon.toolbox.R;
import com.dragon.toolbox.chartlet.utils.CanvasUtils;

import java.util.ArrayList;
import java.util.List;

public class ChartletLine {

    private Context mContext;
    private List<ChartletItem> chartletItems=new ArrayList<>();
    private RectF indexRectF;
    private RectF backRectF;

    private int rawIndex;
    //背景画笔
    private Paint mLineBackPaint;
    //行索引背景画笔
    private Paint mRawRectPaint;
    //行索引画笔
    private Paint mRawNumberPaint;

    //是否处于拖拽模式
    private boolean isDragStatus;

    //贴图矩形圆角半径
    private float cChartletRound = 10;

    {
        mLineBackPaint = new Paint();
        mLineBackPaint.setAntiAlias(true);
        mLineBackPaint.setStyle(Paint.Style.FILL);
        mLineBackPaint.setColor(0xFFF0F0F0);

        mRawRectPaint = new Paint();
        mRawRectPaint.setAntiAlias(true);
        mRawRectPaint.setStyle(Paint.Style.STROKE);
//        mRawRectPaint.setColor(getResources().getColor(R.color.colorPrimary));

        mRawNumberPaint = new Paint();
        mRawNumberPaint.setAntiAlias(true);
//        mRawNumberPaint.setTextSize(DensityUtils.sp2px(12));
//        mRawNumberPaint.setColor(getResources().getColor(R.color.colorPrimary));
    }

    public void onDraw(Canvas canvas){
        //绘制背景
        canvas.drawRoundRect(backRectF, cChartletRound, cChartletRound, mLineBackPaint);
        drawLineIndex(canvas);
    }

    /**
     * 绘制行号
     *
     * @param canvas
     */
    private void drawLineIndex(Canvas canvas) {
        String rawIndexStr=rawIndex+"";
        float radius = indexRectF.width() / 2;
        canvas.drawRoundRect(indexRectF, radius, radius, mRawRectPaint);
        int startX = (int) (indexRectF.centerX() - CanvasUtils.getTextLength(mRawNumberPaint, rawIndexStr) / 2);
        int startY = (int) (indexRectF.centerY() + CanvasUtils.getTextYOffset(mRawNumberPaint));
        canvas.drawText(rawIndexStr, startX, startY, mRawNumberPaint);
    }

}

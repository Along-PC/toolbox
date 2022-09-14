package com.dragon.toolbox.chartlet.bean;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

public class ChartletBean {

    //贴图背景色
    private int backColor;

    //小图标
    private Bitmap bitmap;

    //小图标路径
    private String iconPath;

    //文字
    private String text;

    //所在位置
    private RectF backRectF;

    //是否显示拉伸框
    private boolean canStretch;

    //是否显示长按, 长按后隐藏并创建一个临时的
    private boolean canLongClick;

    private RectF mConvertRect = new RectF();
    //缩放检测用
    private RectF detectRect = new RectF();
    //缩放倍数
    private float scale = 1f;

    //贴图数据载体
//    private EditorItem editorItem;
}

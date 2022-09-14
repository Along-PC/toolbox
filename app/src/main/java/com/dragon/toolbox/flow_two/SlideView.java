package com.dragon.toolbox.flow_two;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

public class SlideView extends View implements NodeOperate{

    private NodeChain nodeChain;

    public SlideView(Context context) {
        super(context);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setInterval(long start,long end) {
        post(new Runnable() {
            @Override
            public void run() {
                SlideOptionBean slideOptionBean = new SlideOptionBean(getMeasuredWidth(), getMeasuredHeight());
                slideOptionBean.setPaddingLeft(getPaddingLeft());
                slideOptionBean.setPaddingTop(getPaddingTop());
                slideOptionBean.setPaddingRight(getPaddingRight());
                slideOptionBean.setPaddingBottom(getPaddingBottom());
                nodeChain = new NodeChain(start, end,slideOptionBean);
                invalidate();
            }
        });
    }

    public void updateInterval(long start,long end){
        if (nodeChain==null) {
            return;
        }
        nodeChain.setStartPosition(start);
        nodeChain.setEndPosition(end);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean addNode(Node node) {
        boolean addNode = nodeChain.addNode(node);
        invalidate();
        return addNode;
    }

    @Override
    public boolean removeNode(Node node) {
        boolean removeNode = nodeChain.removeNode(node);
        invalidate();
        return removeNode;
    }
}

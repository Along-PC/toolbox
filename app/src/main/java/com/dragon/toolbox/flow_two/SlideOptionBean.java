package com.dragon.toolbox.flow_two;

public class SlideOptionBean {

    //x轴缩放比列
    private float scaleX=1;
    //节点高度
    private float nodeHeight=0;
    //行间距
    private float linePadding=0;

    private float parentWidth;
    private float parentHeight;
    private float paddingLeft;
    private float paddingTop;
    private float paddingRight;
    private float paddingBottom;

    public SlideOptionBean(float parentWidth, float parentHeight) {
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getNodeHeight() {
        return nodeHeight;
    }

    public void setNodeHeight(float nodeHeight) {
        this.nodeHeight = nodeHeight;
    }

    public float getLinePadding() {
        return linePadding;
    }

    public void setLinePadding(float linePadding) {
        this.linePadding = linePadding;
    }

    public float getParentWidth() {
        return parentWidth;
    }

    public void setParentWidth(float parentWidth) {
        this.parentWidth = parentWidth;
    }

    public float getParentHeight() {
        return parentHeight;
    }

    public void setParentHeight(float parentHeight) {
        this.parentHeight = parentHeight;
    }

    public float getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(float paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public float getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(float paddingTop) {
        this.paddingTop = paddingTop;
    }

    public float getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(float paddingRight) {
        this.paddingRight = paddingRight;
    }

    public float getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(float paddingBottom) {
        this.paddingBottom = paddingBottom;
    }
}

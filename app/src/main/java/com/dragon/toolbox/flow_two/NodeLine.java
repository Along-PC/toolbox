package com.dragon.toolbox.flow_two;

import java.util.ArrayList;
import java.util.List;

public class NodeLine implements Interval, NodeOperate,Position {

    private long start;
    private long end;
    private float left;
    private float top;
    private float right;
    private float bottom;
    private List<Node> nodes = new ArrayList<>();

    public NodeLine(long start, long end, SlideOptionBean slideOptionBean,int lineIndex) {
        this.start = start;
        this.end = end;
        float linePadding = slideOptionBean.getLinePadding();
        float paddingLeft = slideOptionBean.getPaddingLeft();
        float paddingRight = slideOptionBean.getPaddingRight();

    }

    @Override
    public long getStartPosition() {
        return start;
    }

    @Override
    public void setStartPosition(long start) {
        this.start = start;
    }

    @Override
    public long getEndPosition() {
        return end;
    }

    @Override
    public void setEndPosition(long end) {
        this.end = end;
    }

    @Override
    public boolean addNode(Node node) {
        if (!checkValidInterval(node)) {
            return false;
        }
        boolean flag = checkCoincidence(node);
        if (flag) {
            return false;
        } else {
            nodes.add(node);
            return true;
        }
    }

    /**
     * 验证节点区间是否有效
     * @param node
     */
    private boolean checkValidInterval(Node node) {
        if (node.getStartPosition()<getStartPosition()) {
            return false;
        }
        if(node.getEndPosition()>getEndPosition()){
            return false;
        }
        return true;
    }

    /**
     * 验证是否有重合的节点
     * @param node
     * @return
     */
    private boolean checkCoincidence(Node node) {
        boolean flag = false;
        for (Node temp : nodes) {
            if (temp.coincidence(node)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public boolean removeNode(Node node) {
        if (nodes.contains(node)) {
            nodes.remove(node);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public float getLeft() {
        return left;
    }

    @Override
    public void setLeft(float left) {
        this.left = left;
    }

    @Override
    public float getTop() {
        return top;
    }

    @Override
    public void setTop(float top) {
        this.top = top;
    }

    @Override
    public float getRight() {
        return right;
    }

    @Override
    public void setRight(float right) {
        this.right = right;
    }

    @Override
    public float getBottom() {
        return bottom;
    }

    @Override
    public void setBottom(float bottom) {
        this.bottom = bottom;
    }
}

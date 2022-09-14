package com.dragon.toolbox.flow;

public class NodeImpl implements Node {

    private long start;
    private long end;
    private boolean selected;

    @Override
    public void adjustStart(long start) {
        this.start = start;
    }

    @Override
    public void adjustEnd(long end) {
        this.end = end;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public long getStartPosition() {
        return start;
    }

    @Override
    public long getEndPosition() {
        return end;
    }

    @Override
    public boolean coincidence(Node node) {
        if (node.getStartPosition() > this.end || node.getEndPosition() < this.start) {
            return false;
        } else {
            return true;
        }
    }
}

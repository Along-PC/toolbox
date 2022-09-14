package com.dragon.toolbox.flow_two;

public class NodeImpl implements Node {

    private long start;
    private long end;
    private boolean selected;

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
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
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

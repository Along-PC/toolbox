package com.dragon.toolbox.flow_two;

import java.util.ArrayList;
import java.util.List;

public class NodeChain implements Interval, NodeOperate {

    private long start;
    private long end;
    private SlideOptionBean slideOptionBean;
    private List<NodeLine> nodeLines = new ArrayList<>();

    private int lineIndex=0;

    public NodeChain(long start, long end, SlideOptionBean slideOptionBean) {
        this.start = start;
        this.end = end;
        this.slideOptionBean = slideOptionBean;
    }

    @Override
    public boolean addNode(Node node) {
        boolean flag = false;
        for (NodeLine nodeLine : nodeLines) {
            if (nodeLine.addNode(node)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            long startPosition = getStartPosition();
            long endPosition = getEndPosition();
//            NodeLine nodeLine = new NodeLine(getStartPosition(), getEndPosition());
//            flag = nodeLine.addNode(node);
        }
        return flag;
    }

    @Override
    public boolean removeNode(Node node) {
        boolean flag = false;
        for (NodeLine nodeLine : nodeLines) {
            if (nodeLine.removeNode(node)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public long getStartPosition() {
        return start;
    }

    @Override
    public void setStartPosition(long start) {
        for (NodeLine nodeLine : nodeLines) {
            nodeLine.setStartPosition(start);
        }
        this.start = start;
    }

    @Override
    public long getEndPosition() {
        return end;
    }

    @Override
    public void setEndPosition(long end) {
        for (NodeLine nodeLine : nodeLines) {
            nodeLine.setEndPosition(end);
        }
        this.end = end;
    }
}

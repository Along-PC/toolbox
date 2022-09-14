package com.dragon.toolbox.flow;

import java.util.ArrayList;
import java.util.List;

public class NodeLine {

    private List<Node> mNodes = new ArrayList<>();

    public boolean addNode(Node node) {
        boolean flag = false;
        for (Node temp : mNodes) {
            if (!temp.coincidence(node)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            mNodes.add(node);
            return true;
        } else {
            return false;
        }
    }

    public boolean delteNode(Node node) {
        if (mNodes.contains(node)) {
            mNodes.remove(node);
            return true;
        } else {
            return false;
        }
    }
}

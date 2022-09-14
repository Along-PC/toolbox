package com.dragon.toolbox.flow;

import java.util.ArrayList;
import java.util.List;

public class NodeChain implements NodeOperate{

    private List<NodeLine> mNodeLines=new ArrayList<>();
    private NodeRender mNodeRender;

    public void attachNodeRender(NodeRender nodeRender){
        mNodeRender=nodeRender;
    }

    @Override
    public boolean addNode(Node node) {
        boolean flag=false;
        for (NodeLine nodeLine : mNodeLines) {
            if (nodeLine.addNode(node)) {
                flag=true;
            }
        }
        return flag;
    }

    @Override
    public boolean deleteNode(Node node) {
        boolean flag=false;
        for (NodeLine nodeLine : mNodeLines) {
            if (nodeLine.delteNode(node)) {
                flag=true;
            }
        }
        return flag;
    }

}

package com.dragon.toolbox.flow_two;

public interface Node extends Interval {

    /**
     * 当前节点是否选中
     * @return
     */
    boolean isSelected();

    /**
     * 设置是否选中
     * @param selected
     */
    void setSelected(boolean selected);

    /**
     * 判断两个节点是否有重合部分
     * @param node
     * @return
     */
    boolean coincidence(Node node);

}

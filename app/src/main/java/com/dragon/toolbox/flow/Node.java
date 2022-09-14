package com.dragon.toolbox.flow;

public interface Node {

    /**
     * 调节开始位置
     * @param start
     */
    void adjustStart(long start);

    /**
     * 调节结束位置
     * @param end
     */
    void adjustEnd(long end);

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
     * 获取节点开始位置
     * @return
     */
    long getStartPosition();

    /**
     * 获取节点结束位置
     * @return
     */
    long getEndPosition();

    /**
     * 判断两个节点是否有重合部分
     * @param node
     * @return
     */
    boolean coincidence(Node node);
}

package com.dragon.toolbox.flow_two;

public interface Interval {

    /**
     * 获取开始位置
     * @return
     */
    long getStartPosition();

    /**
     * 调节开始位置
     * @param start
     */
    void setStartPosition(long start);

    /**
     * 获取结束位置
     * @return
     */
    long getEndPosition();

    /**
     * 调节结束位置
     * @param end
     */
    void setEndPosition(long end);

}

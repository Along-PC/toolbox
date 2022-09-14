package com.dragon.toolbox.hs;

public class HsBean {

    private int color;
    private String content;
    private int size;

    public HsBean(int color, String content, int size) {
        this.color = color;
        this.content = content;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

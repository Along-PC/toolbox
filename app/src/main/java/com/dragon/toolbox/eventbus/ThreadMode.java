package com.dragon.toolbox.eventbus;

public enum ThreadMode {
    Main,//主线程执行订阅方法
    POST,//默认
    BACKGROUND//后台线程订阅执行
}
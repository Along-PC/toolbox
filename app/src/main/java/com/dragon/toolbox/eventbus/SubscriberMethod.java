package com.dragon.toolbox.eventbus;

import java.lang.reflect.Method;

/**
 * 方法包装类
 */
class SubscriberMethod {
    final Method method;//方法
    final ThreadMode threadMode;//线程模式
    final Class<?> eventType;//方法的入参参数类型
 
    public SubscriberMethod(Method method, ThreadMode threadMode, Class<?> eventType) {
        this.method = method;
        this.threadMode = threadMode;
        this.eventType = eventType;
    }
}
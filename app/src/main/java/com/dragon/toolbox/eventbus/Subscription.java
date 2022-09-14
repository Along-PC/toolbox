package com.dragon.toolbox.eventbus;

/**
 * 对象 方法包装类
 */
class Subscription {
    // 注册对象
    final Object subscriber;
    // 方法包装类
    final SubscriberMethod subscriberMethod;
 
    public Subscription(Object subscriber, SubscriberMethod subscriberMethod) {
        this.subscriber = subscriber;
        this.subscriberMethod = subscriberMethod;
    }
}
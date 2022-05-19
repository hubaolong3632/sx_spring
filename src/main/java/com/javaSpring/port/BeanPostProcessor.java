package com.javaSpring.port;

import java.util.Map;

public interface BeanPostProcessor {  //初始化前后的操作
    /**初始化前*/
    Object  InitializeTheFormer(Object bean,String beanName);
    /**初始化后*/
    Object  AfterTheInitialization(Object bean,String beanName);
}

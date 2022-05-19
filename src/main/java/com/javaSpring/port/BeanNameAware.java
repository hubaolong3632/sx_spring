package com.javaSpring.port;

public interface BeanNameAware {  //回调方法用来获取名称
    void setBeanName(String name);
}

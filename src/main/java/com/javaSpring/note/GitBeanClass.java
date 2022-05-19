package com.javaSpring.note;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //保存的时间
@Target(ElementType.TYPE)   //只可以写在类上面
public @interface GitBeanClass {  //获取扫描的地址
    String value() default "";
}

package com.javaSpring.note;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //保存的时间
@Target({ElementType.METHOD,ElementType.FIELD})   //只可以写在方法和属性上面
 public @interface Autowired{ //依赖注入
}

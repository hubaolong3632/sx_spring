package com.wenjin.Service;

import com.javaSpring.note.GitBean;
import com.javaSpring.note.Scope;
import com.javaSpring.port.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@GitBean("beforeandafterinitialization")
@Scope("beforeandafterinitialization")
public class BeforeAndAfterInitialization implements BeanPostProcessor {   //用于写每一个数的初始化前后操作

    @Override
    public Object InitializeTheFormer(Object bean, String beanName) {
        if(beanName.equals("tow")){ //判断如果是为one才输出初始化前后
            System.out.println("one初始化前:");

        }
        return bean;
    }

    @Override
    public Object AfterTheInitialization(Object bean, String beanName) {//初始化前后

        if(beanName.equals("tow")){ //判断如果是为one才输出初始化前后
            System.out.println("tow初始化后:");
           //生成代理对象
            Object proxyInstance = Proxy.newProxyInstance(BeforeAndAfterInitialization.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {//需要代理的类  被代理的类
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {  //代理函数
                    System.out.println("代理逻辑:");
                    method.invoke(bean,args); // 当前类,类里面实现接口的方法 执行代理的方法 (就是接口里面定义的方法 scout())
                    return null;
                }
            });


        return proxyInstance; //返回代理对象

        }
        return bean;
    }
}

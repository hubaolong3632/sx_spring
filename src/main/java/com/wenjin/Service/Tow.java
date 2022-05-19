package com.wenjin.Service;

import com.javaSpring.note.Autowired;
import com.javaSpring.note.GitBean;
import com.javaSpring.note.Scope;
import com.javaSpring.port.BeanNameAware;
import com.javaSpring.port.InitialSizingBean;

@GitBean("tow") //标识需要被扫描
@Scope("tow") // 标识原型类(可以被new多次相同)
public class Tow implements BeanNameAware, InitialSizingBean,UserService {

    @Autowired   //属性注入
    private One one;

    private String beanName;


    @Override
    public void setBeanName(String name) { //回调方法用来给beanNAme注入
    beanName=name;

    }

    @Override
    public void aftertiesSet() throws Exception {  //初始化
        System.out.println("\n");
        System.out.println("初始化操作");
    }

    @Override
    public void scout() { //代理输出
        System.out.println("当前注入的类:"+one);
        System.out.println("当前接口名称:"+beanName);
    }
}

package com.wenjin.View;

import com.javaSpring.class1.CaozuoContext;
import com.javaSpring.note.GitBeanClass;
import com.wenjin.Service.BeforeAndAfterInitialization;
import com.wenjin.Service.Tow;
import com.wenjin.Service.UserService;

//用来获取路径
@GitBeanClass("com/wenjin/Service")
public class Main {

    public static void main(String[] args) {
        CaozuoContext cao=new CaozuoContext(Main.class); //配置
//        Object one = cao.getBen("one"); //获取当前类

        System.out.println();

        UserService tow = (UserService) cao.getBen("tow");
        tow.scout(); //输出

        
//        测试初始化前后
        BeforeAndAfterInitialization three = (BeforeAndAfterInitialization)cao.getBen("beforeandafterinitialization");


    }

}

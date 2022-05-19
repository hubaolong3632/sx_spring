package com.javaSpring.class1;

import com.javaSpring.note.Autowired;
import com.javaSpring.note.GitBean;
import com.javaSpring.note.GitBeanClass;
import com.javaSpring.note.Scope;
import com.javaSpring.port.BeanNameAware;
import com.javaSpring.port.BeanPostProcessor;
import com.javaSpring.port.InitialSizingBean;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaozuoContext {

    Class cs;
    /**用来放注释名称,创建好的对象*/
    private HashMap<String,Object> singletonObject=new HashMap<>();
    /**用来放 注释名称,beandefinition*/
   private  HashMap<String, BeanDefinition> beanDefinitionHashMap=new HashMap<>();

   /**存储实现的BeanPostProcessor初始化前后的对象*/
   private List<BeanPostProcessor> beanPostProcessors=new ArrayList<>();

   /**初始化,并且扫描所有注解*/
    public CaozuoContext(Class cs) {
        this.cs = cs;

        scan(cs);

        for(Map.Entry<String,BeanDefinition> entry:beanDefinitionHashMap.entrySet()){

            String  beanName = entry.getKey();
//            System.out.println("对象名称:"+beanName);
            BeanDefinition beanDefinition = entry.getValue();
            if(beanDefinition.getScope().equals("singleton")){ //判断是否为单例类
                Object bean = createBean(beanName,beanDefinition); //获取到当前解析完成之后的类(不带注解的)
                singletonObject.put(beanName,bean);// 键,解析的类


            }

        }


        /*
       for(BeanDefinition beanDefinition:beanDefinitionHashMap.values()){
           if(beanDefinition.getScope().equals("singleton")){ //判断是否为单例类

           }

       }
       */


    }
        /**创建类*/
    public Object createBean(String beanName,BeanDefinition beanDefinition){
        Class clazz = beanDefinition.getClazz();  //获取类
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();//创建一个类

            //依赖注入带@Autowired 方法的
            for (Field field : clazz.getDeclaredFields()) { //获取此类所有属性
                if(field.isAnnotationPresent(Autowired.class)){ //判断当前属性是否有Autowired注解

                    Object ben = getBen(field.getName()); //通过getBen方法查找有没有此类直接注入
                    field.setAccessible(true); //设置不检查访问权限
                    field.set(instance,ben); //给属性赋值(根据属性类)

                }

            }



            //判断是否实现接口( 回调)
            if( instance instanceof BeanNameAware){
                ((BeanNameAware) instance).setBeanName(beanName); //给当前接口进行注入

            }

            //初始化前的操作
            for (BeanPostProcessor bpp : beanPostProcessors) {
                instance = bpp.InitializeTheFormer(instance, beanName); //放入东西(被创建的类,名字)
            }


            //初始化
            if(instance instanceof InitialSizingBean){
                try {
                    ((InitialSizingBean) instance).aftertiesSet(); //该方法表示里面写了什么就实现什么
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            //初始化后的操作
            for (BeanPostProcessor bpp : beanPostProcessors) {
                instance = bpp.AfterTheInitialization(instance, beanName); //放入东西 ,返回的就


            }



                return  instance;//创建类返回类
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        return  null;

    }

   /**用来扫描然后放入集合*/
    private void scan(Class cs) {
        GitBeanClass gitBeanclass = (GitBeanClass) cs.getDeclaredAnnotation(GitBeanClass.class); //获取文件
        String value = gitBeanclass.value();   //wenjin/Service
        System.out.println(value); //获取注解上面的值

        value=value.replace(".","/");  //把点换成斜杠
        ClassLoader aClass = CaozuoContext.class.getClassLoader(); //获取 app
        System.out.println(value);

        URL resource = aClass.getResource(value);//查找当前指定路径
        System.out.println(resource);
        File file=new File(resource.getFile()); //转换成文件类型


        if(file.isDirectory()){ //判断是否为空

            File[] files = file.listFiles(); //获取文件

            for (File f1:files){
                System.out.println(f1); //输出当前地址

                String className= f1.getAbsolutePath(); //获取路径
                if(className.endsWith(".class")){ //判断是不是class文件

                    String srcName=className.substring(className.indexOf("com"),className.indexOf(".class"));
                    srcName=srcName.replace("\\","."); //把斜杠编程点
                    System.out.println(srcName); //转换成com.wenjin.Service.One 格式


                    try {
                        Class<?> aClass1 = aClass.loadClass(srcName); //获取当前文件的class
                        if(aClass1.isAnnotationPresent(GitBean.class)){  //扫描当前所有的类有木有文件带GitBean注解的


                             //第九集  BeanPostProcessor 初始化前后
                               if(BeanPostProcessor.class.isAssignableFrom(aClass1)){ //判断当前对象是否实现了BeanPostProcessor接口
                                   try {
                                       BeanPostProcessor instance = (BeanPostProcessor)aClass1.getDeclaredConstructor().newInstance(); //如果实现就直接实例化
                                       beanPostProcessors.add(instance); //存储初始化前的类对象
                                   } catch (InstantiationException e) {
                                       e.printStackTrace();
                                   } catch (IllegalAccessException e) {
                                       e.printStackTrace();
                                   } catch (InvocationTargetException e) {
                                       e.printStackTrace();
                                   } catch (NoSuchMethodException e) {
                                       e.printStackTrace();
                                   }

                               }
                               //_______________________________________

                            GitBean declaredAnnotation = aClass1.getDeclaredAnnotation(GitBean.class);//注解里面的内容
                            String value1 = declaredAnnotation.value(); //获取类名称

                            BeanDefinition beanDefinition=new BeanDefinition();
                            beanDefinition.setClazz(aClass1); //放入集合的class类名称
                           if(aClass1.isAnnotationPresent(Scope.class)) //判断有没有gitbean注解   如果有此注解就不是单例ben
                           {
                               Scope gitBean=aClass1.getDeclaredAnnotation(Scope.class); //获取注解名称
                               beanDefinition.setScope(gitBean.value()); //注解的名称放入类中
                           }else{
                               beanDefinition.setScope("singleton"); //标识头上不带的类

                           }

                            beanDefinitionHashMap.put(value1,beanDefinition);//放入类名和类对象


                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }



        }
    }

/**获取类对象,初始化原型(singleton)和非原型@Scope*/
    public Object getBen(String ben){  //

     if(beanDefinitionHashMap.containsKey(ben)){ //判断当前里面是否存在对应的ben
        BeanDefinition beanDefinition=beanDefinitionHashMap.get(ben); //获取此类
                if(beanDefinition.getScope().equals("singleton")){ //判断是否是单例类

                    Object bean = singletonObject.get(ben); //获取当前对应的原形方法
                    return bean;

                }else{
                        Object bean=createBean(ben,beanDefinition); //创建一个类,ben可能实现一个接口回调就放进去

                        return bean; //直接返回回去

                }

     }else{
         throw  new NullPointerException();   //如果查找不到这个类
     }





    }

}

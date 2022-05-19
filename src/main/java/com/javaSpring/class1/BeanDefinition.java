package com.javaSpring.class1;
/**类对象*/
public class BeanDefinition {
    /**ben的类型*/
    private Class clazz;
    /**类型*/
    private String scope;





    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}

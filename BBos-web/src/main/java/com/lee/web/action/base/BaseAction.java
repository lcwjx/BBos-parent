package com.lee.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    public static final String HOME = "home";

    protected T model;

    @Override
    public T getModel() {
        return model;
    }

    public BaseAction() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取BaseAction<T>声明的泛型数组
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();

        Class<T> entityCalss = (Class<T>) actualTypeArguments[0];
        //通过反射获取对象
        try {
            model = entityCalss.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

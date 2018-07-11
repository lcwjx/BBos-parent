package com.lee.web.action.base;

import com.alibaba.fastjson.JSONObject;
import com.lee.domain.Staff;
import com.lee.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    protected PageBean pageBean = new PageBean();
    public static final String HOME = "home";
    public static final String LIST = "list";


    public void setPage(int page) {
        pageBean.setCurrentPage(page);
    }

    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }

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
        //        提交离线查询对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityCalss);
        pageBean.setDetachedCriteria(detachedCriteria);
        try {
            model = entityCalss.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void objectConvertJson(Object object) {
        String json = JSONObject.toJSONString(object);
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

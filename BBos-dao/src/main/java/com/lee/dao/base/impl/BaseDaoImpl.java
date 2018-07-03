package com.lee.dao.base.impl;

import com.lee.dao.base.IBaseDao;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
    public Class<T> entityClass;

    @Resource//根据类型注入spring工厂中的会话工厂对象sessionFactory
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    //在父类（BaseDaoImpl）的构造方法中动态获得entityClass
    public BaseDaoImpl() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取泛型数组
        Type[] types = superclass.getActualTypeArguments();

        entityClass = (Class<T>) types[0];

    }

    @Override
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        String hql = "FROM " + entityClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }
}

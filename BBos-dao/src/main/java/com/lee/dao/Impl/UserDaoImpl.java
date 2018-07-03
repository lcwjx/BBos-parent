package com.lee.dao.Impl;

import com.lee.dao.IUserDao;
import com.lee.dao.base.impl.BaseDaoImpl;
import com.lee.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
    /**
     * 根据用户名和密码查询用户
     */
    public User findUserByUsernameAndPassword(String username, String password) {
        String hql = "FROM User u WHERE u.username = ?0 AND u.password = ?1";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}

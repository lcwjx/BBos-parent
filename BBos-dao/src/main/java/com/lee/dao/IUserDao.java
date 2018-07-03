package com.lee.dao;

import com.lee.dao.base.IBaseDao;
import com.lee.domain.User;

public interface IUserDao extends IBaseDao<User> {
    User findUserByUsernameAndPassword(String username, String password);
}

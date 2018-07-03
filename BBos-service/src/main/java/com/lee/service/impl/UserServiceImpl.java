package com.lee.service.impl;

import com.lee.dao.IUserDao;
import com.lee.domain.User;
import com.lee.service.IUserService;
import com.lee.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public User login(User model) {
        String password = MD5Utils.md5(model.getPassword());
        return userDao.findUserByUsernameAndPassword(model.getUsername(),password);
    }
}

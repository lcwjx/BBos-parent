package com.lee.service.impl;

import com.lee.dao.ISubareaDao;
import com.lee.domain.Subarea;
import com.lee.service.ISubareaService;
import com.lee.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService{
    @Autowired
    private ISubareaDao subareaDao;
    @Override
    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    @Override
    public void save(Subarea model) {
        subareaDao.save(model);
    }
}

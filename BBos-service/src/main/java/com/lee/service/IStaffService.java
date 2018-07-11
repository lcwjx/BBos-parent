package com.lee.service;

import com.lee.domain.Staff;
import com.lee.utils.PageBean;

public interface IStaffService {
    void save(Staff model);

    void pageQuery(PageBean pageBean);

    void deleteBatch(String ids);

    Staff findById(String id);

    void update(Staff staff);
}

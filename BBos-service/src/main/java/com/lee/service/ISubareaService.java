package com.lee.service;

import com.lee.domain.Subarea;
import com.lee.utils.PageBean;

public interface ISubareaService {
    void pageQuery(PageBean pageBean);

    void save(Subarea model);
}

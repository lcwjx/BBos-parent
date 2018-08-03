package com.lee.service;

import com.lee.domain.Subarea;
import com.lee.utils.PageBean;

import java.util.List;

public interface ISubareaService {
    void pageQuery(PageBean pageBean);

    void save(Subarea model);

    List<Subarea> findAll();

    List<Subarea> findListNotAssociation();
}

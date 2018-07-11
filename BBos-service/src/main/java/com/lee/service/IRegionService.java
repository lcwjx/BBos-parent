package com.lee.service;

import com.lee.domain.Region;
import com.lee.utils.PageBean;

import java.util.List;

public interface IRegionService {
    void saveBatch(List<Region> regionList);

    void pageQuery(PageBean pageBean);

    List<Region> findAll();

    List<Region> findListByQ(String q);
}

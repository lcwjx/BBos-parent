package com.lee.dao;

import com.lee.dao.base.IBaseDao;
import com.lee.domain.Region;

import java.util.List;

public interface IRegionDao extends IBaseDao<Region>{

    List<Region> findListByQ(String q);
}

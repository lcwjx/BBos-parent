package com.lee.service.impl;

import com.lee.dao.IRegionDao;
import com.lee.domain.Region;
import com.lee.service.IRegionService;
import com.lee.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private IRegionDao regionDao;

    /**
     * 区域数据批量保存
     *
     * @param regionList
     */
    @Override
    public void saveBatch(List<Region> regionList) {
        for (Region region : regionList) {
            regionDao.saveOrUpdate(region);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    @Override
    public List<Region> findAll() {
        return regionDao.findAll();
    }

    @Override
    public List<Region> findListByQ(String q) {
        return regionDao.findListByQ(q);
    }
}

package com.lee.dao.Impl;

import com.lee.dao.IRegionDao;
import com.lee.dao.base.impl.BaseDaoImpl;
import com.lee.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

    @Override
    public List<Region> findListByQ(String q) {
        String hql = "FROM Region r WHERE r.shortcode LIKE ?0 "
                + "	OR r.citycode LIKE ?1 OR r.province LIKE ?2 "
                + "OR r.city LIKE ?3 OR r.district LIKE ?4";
        List<Region> list = (List<Region>) this.getHibernateTemplate().
                find(hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
        return list;
    }
}

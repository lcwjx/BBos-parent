package com.lee.service.impl;

import com.lee.dao.IDecidedzoneDao;
import com.lee.dao.ISubareaDao;
import com.lee.domain.Decidedzone;
import com.lee.domain.Subarea;
import com.lee.service.IDecidedzoneService;
import com.lee.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {
    @Autowired
    private IDecidedzoneDao decidedzoneDao;

    @Autowired
    private ISubareaDao subareaDao;

    @Override
    public void pageQuery(PageBean pageBean) {
        decidedzoneDao.pageQuery(pageBean);
    }

    @Override
    public void save(Decidedzone model, String[] subareaid) {
        decidedzoneDao.save(model);
        for (String id : subareaid) {
            Subarea subarea = subareaDao.findById(id);
            //model.getSubareas().add(subarea);一方（定区）已经放弃维护外键权利，只能由多方（分区）负责维护
            subarea.setDecidedzone(model);//分区关联定区
        }
    }
}

package com.lee.service;

import com.lee.domain.Decidedzone;
import com.lee.utils.PageBean;

public interface IDecidedzoneService {
    void pageQuery(PageBean pageBean);

    void save(Decidedzone model, String[] subareaid);
}


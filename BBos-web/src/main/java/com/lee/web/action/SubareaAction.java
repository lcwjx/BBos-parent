package com.lee.web.action;

import com.lee.domain.Subarea;
import com.lee.service.ISubareaService;
import com.lee.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
    @Autowired
    private ISubareaService subareaService;

    /**
     * 分页查询
     *
     * @return
     * @throws Exception
     */
    public String pageQuery() throws Exception {
        subareaService.pageQuery(pageBean);
        this.objectConvertJson(pageBean);
        return NONE;
    }

    public String add() throws Exception {
        subareaService.save(model);
        return LIST;
    }
}

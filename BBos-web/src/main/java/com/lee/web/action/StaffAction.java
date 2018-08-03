package com.lee.web.action;


import com.lee.domain.Staff;
import com.lee.service.IStaffService;
import com.lee.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;


@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    @Autowired
    private IStaffService staffService;

    /**
     * 保存
     *
     * @return
     */
    public String add() {
        staffService.save(model);
        return LIST;
    }

    /**
     * 分页查询
     *
     * @return
     * @throws IOException
     */
    public String pageQuery() throws IOException {
        staffService.pageQuery(pageBean);
        this.objectConvertJson(pageBean, new String[]{"decidedzones","currentPage","detachedCriteria","pageSize"});
        return NONE;
    }


    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    /**
     * 批量删除
     *
     * @return
     * @throws Exception
     */
    public String deleteBatch() throws Exception {
        staffService.deleteBatch(ids);
        return LIST;
    }

    /**
     * 修改数据
     *
     * @return
     * @throws Exception
     */
    public String edit() throws Exception {
//        查询数据库获取要修改用户的所有信息
        Staff staff = staffService.findById(model.getId());
        staff.setName(model.getName());
        staff.setDeltag(model.getDeltag());
        staff.setStation(model.getStation());
        staff.setTelephone(model.getTelephone());
        staff.setHaspda(model.getHaspda());
        staff.setStandard(model.getStandard());
        staffService.update(staff);
        return LIST;
    }

    /**
     * 查询所有未删除的取派员
     *
     * @return
     */
    public String listAjax() {
        List<Staff> list = staffService.findListNotDelete();
        this.objectConvertJson(list, new String[]{"decidedzones"});
        return NONE;
    }
}

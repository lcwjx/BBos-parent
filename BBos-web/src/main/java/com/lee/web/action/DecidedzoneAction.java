package com.lee.web.action;

import com.lee.domain.Customer;
import com.lee.domain.Decidedzone;
import com.lee.service.ICustomerService;
import com.lee.service.IDecidedzoneService;
import com.lee.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
    @Autowired
    private IDecidedzoneService decidedzoneService;

    @Autowired
    private ICustomerService customerService;

    //属性驱动，接收多个分区id
    private String[] subareaid;

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    /**
     * 添加定区
     */
    public String add() {
        decidedzoneService.save(model, subareaid);
        return LIST;
    }

    public String pageQuery() {
        decidedzoneService.pageQuery(pageBean);
        this.objectConvertJson(pageBean, new String[]{"currentPage", "detachedCriteria",
                "pageSize", "subareas", "decidedzones"});
        return NONE;
    }

    public String findListNotAssociation() throws Exception {
        List<Customer> listNotAssociation = customerService.findListNotAssociation();
        this.objectConvertJson(listNotAssociation, new String[]{});
        return NONE;
    }

    public String findListHasAssociation() throws Exception {
        String id = model.getId();
        List<Customer> listNotAssociation = customerService.findListHasAssociation(id);
        this.objectConvertJson(listNotAssociation, new String[]{});
        return NONE;
    }

    //属性驱动，接收页面提交的多个客户id
    private List<Integer> customerIds;

    /**
     * 远程调用crm服务，将客户关联到定区
     */
    public String assignCustomersToDecidedzone() {
        customerService.assignCustomersToDecidedzone(model.getId(), customerIds);
        return LIST;
    }

    public List<Integer> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }
}

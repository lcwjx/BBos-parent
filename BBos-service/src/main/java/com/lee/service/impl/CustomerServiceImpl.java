package com.lee.service.impl;

import com.lee.dao.ICustomerDao;
import com.lee.domain.Customer;
import com.lee.service.ICustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerDao customerDao;


    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    /**
     * 查询用户没有关联定区的
     *
     * @return
     */
    @Override
    public List<Customer> findListNotAssociation() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        detachedCriteria.add(Restrictions.isNull("decidedzone_id"));
        return customerDao.findByCriteria(detachedCriteria);
    }

    /**
     * 查询已经关联到指定定区的客户
     *
     * @param decidedzoneId
     * @return
     */
    @Override
    public List<Customer> findListHasAssociation(String decidedzoneId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        detachedCriteria.add(Restrictions.eq("decidedzone_id", decidedzoneId));
        return customerDao.findByCriteria(detachedCriteria);
    }

    /**
     * //定区关联客户
     *  @param decidedzoneId
     * @param customerIds
     */
    @Override
    public void assignCustomersToDecidedzone(String decidedzoneId, List<Integer> customerIds) {
        customerDao.executeUpdate("customer.decidedzoneId", decidedzoneId);
        for (Integer id : customerIds) {
            customerDao.executeUpdate("customer.decidedzoneIdAndId", decidedzoneId, id);
        }
    }
}

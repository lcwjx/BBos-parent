package com.lee.service;

import com.lee.domain.Customer;

import java.util.List;


public interface ICustomerService {
    List<Customer> findAll();

    //查询未关联到定区的客户
    List<Customer> findListNotAssociation();

    //查询已经关联到指定定区的客户
    List<Customer> findListHasAssociation(String decidedzoneId);

    //定区关联客户
    void assignCustomersToDecidedzone(String decidedzoneId, List<Integer> customerIds);
}

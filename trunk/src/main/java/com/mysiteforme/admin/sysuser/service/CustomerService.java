package com.mysiteforme.admin.sysuser.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.sysuser.entity.Customer;
import java.util.List;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/6/11
 */

public interface CustomerService extends IService<Customer> {

    Customer saveCustomer(Customer sysCustomer);

    Customer findCustomerById(Long id);

    List<Customer> selectAll();

    List<Customer> selectCustomerByUserId(Long userId);

    int cusCount(String phone);

    Customer updateCustomer(Customer sysCustomer);
}

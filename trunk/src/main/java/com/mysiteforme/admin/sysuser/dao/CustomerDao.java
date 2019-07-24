package com.mysiteforme.admin.sysuser.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.sysuser.entity.Customer;


import java.util.List;
import java.util.Map;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/6/11
 */
public interface CustomerDao extends BaseMapper<Customer> {

    List<Customer> selectCustomerByMap(Map<String, Object> map);
}

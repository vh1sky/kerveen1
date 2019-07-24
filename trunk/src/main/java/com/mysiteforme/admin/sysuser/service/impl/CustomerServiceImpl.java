package com.mysiteforme.admin.sysuser.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.sysuser.dao.CustomerDao;
import com.mysiteforme.admin.sysuser.entity.Customer;
import com.mysiteforme.admin.sysuser.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/6/11
 */
@Service("customerService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, Customer> implements CustomerService {


    @Override
    public Page<Customer> selectPage(Page page, Wrapper wrapper) {
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(this.baseMapper.selectPage(page,wrapper));
        return page;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Customer saveCustomer(Customer Customer) {
        baseMapper.insert(Customer);
        return findCustomerById(Customer.getId());
    }

    @Override
    public Customer findCustomerById(Long id) {
        return findCustomerById(id);
    }



    @Override
    public List<Customer> selectAll() {
        EntityWrapper<Customer> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        List<Customer> cusList = baseMapper.selectList(wrapper);
        return cusList;
    }

    @Override
    public List<Customer> selectCustomerByUserId(Long userId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        List<Customer> customers = baseMapper.selectCustomerByMap(map);

        return customers;
    }

    @Override
    public int cusCount(String phone) {
        return 0;
    }

    @Override
    public Customer updateCustomer(Customer Customer) {
        return null;
    }
}

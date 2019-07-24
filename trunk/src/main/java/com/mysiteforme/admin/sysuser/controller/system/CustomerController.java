package com.mysiteforme.admin.sysuser.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.sysuser.entity.Customer;
import com.mysiteforme.admin.util.DateUtils;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/6/11
 */
@Controller
@RequestMapping("admin/system/customer")
public class CustomerController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("list")
    @SysLog("跳转用户列表页面")
    public String list(){
        return "admin/system/customer/list";
    }

    @RequiresPermissions("sys:user:list")//权限判定
    @PostMapping("list")
    @ResponseBody
    public LayerData<Customer> selectCustomer(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                              @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                              ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Customer> sysLayerData = new LayerData<>();
        EntityWrapper<Customer> customerEntityWrapper = new EntityWrapper<>();
        if(!map.isEmpty()){
            String keys = (String) map.get("key");
            if(StringUtils.isNotBlank(keys)) {
                customerEntityWrapper.like("city", keys)
                        .or().like("province",keys)
                        .or().like("address",keys)
                        .or().like("industryCode",keys)
                        .or().like("typeCode",keys)
                        .or().like("salePerson",keys)
                        .or().like("status",keys)
                        .or().like("installDate",keys)
                        .or().like("servicePerson",keys);
            }
        }

        Page<Customer> CustomerPage = customerService.selectPage(new Page<>(page,limit),customerEntityWrapper);
       /* Date date = CustomerPage.getRecords().get(0).getCreateDate();
        Date installDate = CustomerPage.getRecords().get(0).getInstallDate();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sd.format(date);
        String installDateStr = sd.format(installDate);*/
        sysLayerData.setCount(CustomerPage.getTotal());
        sysLayerData.setData(CustomerPage.getRecords());
        return sysLayerData;
    }

    @GetMapping("add")
    public String add(Model model){
        List<Customer> customerList = customerService.selectAll();
        model.addAttribute("customerlist",customerList);
        return "admin/system/customer/add";
    }

   /* @RequiresPermissions("sys:user:add")
    @PostMapping("add")
    @ResponseBody
    @SysLog("新增客户信息")
    public RestResponse add(@RequestBody CustomerVO customervo){
    if(StringUtils.isBlank(customervo.getName())){
        return RestResponse.failure("客户名不能为空");
    }
    if(userService.userCount(customervo.getLoginName())>0){
        return RestResponse.failure("登录名已存在");
    }
    if(StringUtils.isBlank(customervo.getPassWord())){
        return RestResponse.failure("密码不能为空");
    }
    if(customervo.getPassWord() != customervo.getPassWords() ){
        return RestResponse.failure("密码不一致");
    }

        return RestResponse.success();
    }*/

    @GetMapping("edit")
    public String edit(Long id, Model model){
        Customer customer = customerService.findCustomerById(id);
        model.addAttribute("localcustomer",customer);
        return "edit";
    }

    @RequiresPermissions("sys:customer:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog
    public RestResponse edit(@RequestBody Customer Customer){
        if (StringUtils.isBlank(Customer.getName())){
            return RestResponse.failure("客户名不能为空");
        }
        if (StringUtils.isBlank(Customer.getAddress())){
            return RestResponse.failure("地址不能为空");
        }
        if (Customer.getInstallDate() == null){
            return RestResponse.failure("安装时间不能为空");
        }
        com.mysiteforme.admin.sysuser.entity.Customer oldCus = customerService.findCustomerById(Customer.getId());
        if(StringUtils.isNotBlank(oldCus.getPhone())){
            if(!Customer.getPhone().equals(oldCus.getPhone())){
                if(customerService.cusCount(Customer.getPhone())>0){
                    return RestResponse.failure("该电话已注册");
                }
            }
        }
        customerService.updateCustomer(Customer);
        return RestResponse.success();
    }
}

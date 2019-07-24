package com.mysiteforme.admin.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.monitor.entity.CupStudent;
import com.mysiteforme.admin.monitor.entity.Host;
import com.mysiteforme.admin.sysuser.entity.Customer;
import com.mysiteforme.admin.sysuser.entity.Dict;
import com.mysiteforme.admin.sysuser.entity.User;
import com.mysiteforme.admin.util.RestResponse;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.*;

/**
 * @author Iwen
 * @date 2019/6/15 16:03
 * @Version 1.0
 */
@Controller
@RequestMapping("admin/monitor/host")
public class HostController extends BaseController {

    private static final Log log = LogFactory.get();

    /**
     * 获取查询条件：学校联动下拉框
     */
    @PostMapping("customerList")
    @ResponseBody
    public List<Customer> getCustomers() {
        User user = getCurrentUser();
        List<Customer> customers = customerService.selectCustomerByUserId(user.getId());
        return customers;
    }

    /**
     * 从字典表查询主机机型
     */
    @PostMapping("comboList")
    @ResponseBody
    public Map<String, List> getHostType() {
        Map<String, List> result = new HashMap<>();
        List<Dict> hostTypes = dictService.getDictByType("host_type");
        List<Dict> networkStates = dictService.getDictByType("network_state");
        result.put("hostTypes", hostTypes);
        result.put("networkState", networkStates);
        return result;
    }

    @RequiresPermissions("monitor:host:list")
    @PostMapping("list")
    @ResponseBody
    public RestResponse list(ServletRequest request) {

        Map map = WebUtils.getParametersStartingWith(request, "s_");

        List<Host> hosts = hostService.selectHost((String) map.get("hostDeviceId"), (String) map.get("customerName"),
                (String) map.get("model"), (String) map.get("status"), (String) map.get("address"));


        if (StringUtils.isBlank((String) map.get("customerName"))) {
            List<Customer> customers = getCustomers();
            List<Long> customerIds = new ArrayList<>();
            for (Customer customer : customers) {
                customerIds.add(customer.getId());
            }
            Iterator<Host> it = hosts.iterator();
            while (it.hasNext()) {
                Host x = it.next();
                if (!customerIds.contains(x.getCustomerId())) {
                    it.remove();
                }
            }
        }
        log.info(JSONObject.toJSONString(hosts));

        return RestResponse.success().setData(hosts);
    }

    @GetMapping("add")
    public String add() {
        return "admin/monitor/host/add";
    }

    @RequiresPermissions("monitor:host:add")
    @PostMapping("add")
    @SysLog("新增主机信息")
    @ResponseBody
    public RestResponse add(Host host) {
        if (host.getId() == null || host.getId() == 0) {
            return RestResponse.failure("ID不能为空");
        }
        if (StringUtils.isBlank(host.getHostDeviceId())) {
            return RestResponse.failure("主机ID不能为空");
        }
        if (StringUtils.isBlank(host.getMainboardId())) {
            return RestResponse.failure("主板ID不能为空");
        }
        if (StringUtils.isBlank(host.getModel())) {
            return RestResponse.failure("主机机型不能为空");
        }
        EntityWrapper<CupStudent> wrapper = new EntityWrapper<>();
        wrapper.eq("hostDeviceId", host.getHostDeviceId());
        if (cupStudentService.selectCount(wrapper) > 0) {
            return RestResponse.failure("已经存在主机ID为【" + host.getHostDeviceId() + "】的主机，请联系相关工作人员");
        }
        hostService.saveOrUpdateHost(host);
        return RestResponse.success();
    }

    @GetMapping("edit")
    public String edit(Long id, Model model) {
        Host host = hostService.selectById(id);
        model.addAttribute("host", host);
        return "admin/monitor/host/edit";
    }

    @RequiresPermissions("monitor:host:edit")
    @PostMapping("edit")
    @SysLog("编辑主机信息")
    @ResponseBody
    public RestResponse edit(Host host) {
        if (host.getId() == null || host.getId() == 0) {
            return RestResponse.failure("ID不能为空");
        }
        if (StringUtils.isBlank(host.getHostDeviceId())) {
            return RestResponse.failure("主机id不能为空");
        }
        if (StringUtils.isBlank(host.getMainboardId())) {
            return RestResponse.failure("主板id不能为空");
        }
        hostService.saveOrUpdateHost(host);
        return RestResponse.success();
    }

    @RequiresPermissions("monitor:host:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除主机数据(单个)")
    public RestResponse delete(@RequestParam(value = "id", required = false) Long id) {
        if (id == null || id == 0) {
            return RestResponse.failure("参数错误");
        }
        Host host = hostService.selectById(id);
        if (host == null) {
            return RestResponse.failure("主机信息不存在");
        }
        hostService.deleteHost(host);
        return RestResponse.success();
    }

    @RequiresPermissions("monitor:host:delete")
    @PostMapping("deleteSome")
    @ResponseBody
    @SysLog("删除主机数据(多个)")
    public RestResponse deleteSome(@RequestBody List<Host> hosts) {
        if (hosts == null || hosts.size() == 0) {
            return RestResponse.failure("请选择需要删除的主机");
        }
        for (Host host : hosts) {
            hostService.deleteHost(host);
        }
        return RestResponse.success();
    }
}

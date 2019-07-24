package com.mysiteforme.admin.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.monitor.entity.CupStudent;
import com.mysiteforme.admin.monitor.entity.PipelineMachine;
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
 * @date 2019/6/15 16:04
 * @Version 1.0
 */
@Controller
@RequestMapping("admin/monitor/pipelineMachine")
public class PipelineMachineController extends BaseController {

    private static final Log log = LogFactory.get();

    /**
     * 获取查询条件：学校联动下拉框
     *
     */
    @PostMapping("customerList")
    @ResponseBody
    public List<Customer> getCustomers(){
        User user = getCurrentUser();
        List<Customer> customers = customerService.selectCustomerByUserId(user.getId());
        return customers;
    }

    /**
     * 从字典表查询主机机型
     *
     */
    @PostMapping("comboList")
    @ResponseBody
    public Map<String, List> getPipelineMachineType() {
        Map<String, List> result = new HashMap<>();
        List<Dict> pepelineMachineTypes = dictService.getDictByType("pepeline_machine_type");
        List<Dict> networkStates = dictService.getDictByType("network_state");
        result.put("pepelineMachineTypes", pepelineMachineTypes);
        result.put("networkState", networkStates);
        return result;
    }

    @RequiresPermissions("monitor:pepelineMachine:list")
    @PostMapping("list")
    @ResponseBody
    public RestResponse list(ServletRequest request) {

        Map map = WebUtils.getParametersStartingWith(request, "s_");

        List<PipelineMachine> pipelineMachines = pipelineMachineService.selectPipelineMachine((String) map.get("pipelineMachineId"), (String) map.get("customerName"),
                (String) map.get("model"), (String) map.get("status"),(String) map.get("address"), getCurrentUser().getId());
        if (StringUtils.isBlank((String) map.get("customerName"))){
                List<Customer> customers = getCustomers();
                List<Long> customerIds = new ArrayList<>();
                for (Customer customer : customers) {
                    customerIds.add(customer.getId());
                }
                Iterator<PipelineMachine> it = pipelineMachines.iterator();
                while (it.hasNext()) {
                    PipelineMachine x = it.next();
                    if (!customerIds.contains(x.getCustomerId())) {
                        it.remove();
                    }
                }
        }
        log.info(JSONObject.toJSONString(pipelineMachines));

        return RestResponse.success().setData(pipelineMachines);
    }

    @GetMapping("add")
    public String add() {
        return "admin/monitor/pipelineMachine/add";
    }

    @RequiresPermissions("monitor:pipelineMachine:add")
    @PostMapping("add")
    @SysLog("新增管线机信息")
    @ResponseBody
    public RestResponse add(PipelineMachine pipelineMachine) {
        if (pipelineMachine.getId() == null || pipelineMachine.getId() == 0) {
            return RestResponse.failure("ID不能为空");
        }
        if (StringUtils.isBlank(pipelineMachine.getPipelineMachineId())) {
            return RestResponse.failure("管线机ID不能为空");
        }
        if (StringUtils.isBlank(pipelineMachine.getMainboardId())) {
            return RestResponse.failure("主板ID不能为空");
        }
        if (StringUtils.isBlank(pipelineMachine.getModel())) {
            return RestResponse.failure("主机机型不能为空");
        }
        EntityWrapper<CupStudent> wrapper = new EntityWrapper<>();
        wrapper.eq("pipelineMachineId", pipelineMachine.getPipelineMachineId());
        if (cupStudentService.selectCount(wrapper) > 0) {
            return RestResponse.failure("已经存在管线机ID为【" + pipelineMachine.getPipelineMachineId() + "】的管线机，请联系相关工作人员");
        }
        pipelineMachineService.saveOrUpdatePipelineMachine(pipelineMachine);
        return RestResponse.success();
    }

    @GetMapping("edit")
    public String edit(Long id, Model model) {
        PipelineMachine pipelineMachine = pipelineMachineService.selectById(id);
        model.addAttribute("pipelineMachine", pipelineMachine);
        return "admin/monitor/pipelineMachine/edit";
    }

    @RequiresPermissions("monitor:pipelineMachine:edit")
    @PostMapping("edit")
    @SysLog("编辑管线机信息")
    @ResponseBody
    public RestResponse edit(PipelineMachine pipelineMachine) {
        if (pipelineMachine.getId() == null || pipelineMachine.getId() == 0) {
            return RestResponse.failure("ID不能为空");
        }
        if (StringUtils.isBlank(pipelineMachine.getPipelineMachineId())) {
            return RestResponse.failure("管线机id不能为空");
        }
        if (StringUtils.isBlank(pipelineMachine.getMainboardId())) {
            return RestResponse.failure("主板id不能为空");
        }
        pipelineMachineService.saveOrUpdatePipelineMachine(pipelineMachine);
        return RestResponse.success();
    }

    @RequiresPermissions("monitor:pipelineMachine:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除管线机数据(单个)")
    public RestResponse delete(@RequestParam(value = "id", required = false) Long id) {
        if (id == null || id == 0 ) {
            return RestResponse.failure("参数错误");
        }
        PipelineMachine pipelineMachine = pipelineMachineService.selectById(id);
        if (pipelineMachine == null) {
            return RestResponse.failure("管线机信息不存在");
        }
        pipelineMachineService.deletePipelineMachine(pipelineMachine);
        return RestResponse.success();
    }

    @RequiresPermissions("monitor:pipelineMachine:delete")
    @PostMapping("deleteSome")
    @ResponseBody
    @SysLog("删除管线机数据(多个)")
    public RestResponse deleteSome(@RequestBody List<PipelineMachine> pipelineMachines) {
        if (pipelineMachines == null || pipelineMachines.size() == 0) {
            return RestResponse.failure("请选择需要删除的管线机");
        }
        for (PipelineMachine pipelineMachine : pipelineMachines) {
            pipelineMachineService.deletePipelineMachine(pipelineMachine);
        }
        return RestResponse.success();
    }
}

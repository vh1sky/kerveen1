package com.mysiteforme.admin.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.monitor.entity.CupStudent;
import com.mysiteforme.admin.sysuser.entity.Customer;
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
 * @date 2019/6/14 10:08
 * @Version 1.0
 */
@Controller
@RequestMapping("admin/monitor/cupStudent")
public class CupStudentController extends BaseController {

    private static final Log log = LogFactory.get();

    /**
     * 获取查询条件：学校和班级联动下拉框
     * String类型的key为学校名称
     * set类型为年级的集合
     */
    @PostMapping("customerList")
    @ResponseBody
    public Map<String, Set<String>> getCustomers() {
        Map<String, Set<String>> combo = new HashMap<>();
        User user = getCurrentUser();
        List<Customer> customers = customerService.selectCustomerByUserId(user.getId());
        for (Customer customer : customers) {
            if (StringUtils.isNotBlank(customer.getName())) {
                List<String> grades = cupStudentService.selectGradeByCustomer(customer.getId());
                combo.put(customer.getName(), new HashSet(grades));
            }
        }
        return combo;
    }

    @RequiresPermissions("monitor:cupStudent:list")
    @PostMapping("list")
    @ResponseBody
    public RestResponse list(ServletRequest request) {

        Map map = WebUtils.getParametersStartingWith(request, "s_");
        Map parameter = new HashMap();
        if (StringUtils.isNotBlank((String) map.get("name")))
            parameter.put("name", map.get("name"));
        if (StringUtils.isNotBlank((String) map.get("guarder")))
            parameter.put("guarder", map.get("guarder"));

        if (StringUtils.isNotBlank((String) map.get("grade")))
            parameter.put("grade", map.get("grade"));


        List<CupStudent> cupStudents = cupStudentService.selectByMap(parameter);

        User user = getCurrentUser();
        List<Customer> customers = customerService.selectCustomerByUserId(user.getId());

        Long customerId = 0L;
        if (StringUtils.isNotBlank((String) map.get("customerName"))) {
            for (Customer customer : customers) {
                if (map.get("customerName").equals(customer.getName())) {
                    customerId = customer.getId();
                }
            }
        }
        if (customerId != 0L) {
            Iterator<CupStudent> it = cupStudents.iterator();
            while (it.hasNext()) {
                CupStudent x = it.next();
                if (customerId != x.getCustomerId()) {
                    it.remove();
                }
            }
        } else {
            List<Long> customerIds = new ArrayList<>();
            for (Customer customer : customers) {
                customerIds.add(customer.getId());
            }
            Iterator<CupStudent> it = cupStudents.iterator();
            while (it.hasNext()) {
                CupStudent x = it.next();
                if (!customerIds.contains(x.getCustomerId())) {
                    it.remove();
                }
            }
        }

        log.info(JSONObject.toJSONString(cupStudents));

        return RestResponse.success().setData(cupStudents);
    }

    @GetMapping("add")
    public String add() {
        return "admin/monitor/cupStudent/add";
    }

    @RequiresPermissions("monitor:cupStudent:add")
    @PostMapping("add")
    @SysLog("新增水杯学生信息")
    @ResponseBody
    public RestResponse add(CupStudent cupStudent) {
        if (StringUtils.isBlank(cupStudent.getCupCode())) {
            return RestResponse.failure("杯子识别码不能为空");
        }
        EntityWrapper<CupStudent> wrapper = new EntityWrapper<>();
        wrapper.eq("cupCode", cupStudent.getCupCode());
        if (cupStudentService.selectCount(wrapper) > 0) {
            return RestResponse.failure("已经存在杯子识别码为【" + cupStudent.getCupCode() + "】的杯子，请联系售后人员");
        }
        cupStudentService.saveOrUpdateCupStudent(cupStudent);
        return RestResponse.success();
    }

    @GetMapping("edit")
    public String edit(Long id, Model model) {
        CupStudent cupStudent = cupStudentService.selectById(id);
        model.addAttribute("cupStudent", cupStudent);
        return "admin/monitor/cupStudent/edit";
    }

    @RequiresPermissions("monitor:cupStudent:edit")
    @PostMapping("edit")
    @SysLog("编辑杯子学生信息")
    @ResponseBody
    public RestResponse edit(CupStudent cupStudent) {
        if (cupStudent.getId() == null || cupStudent.getId() == 0) {
            return RestResponse.failure("杯子ID不能为空");
        }
        if (StringUtils.isBlank(cupStudent.getCupCode())) {
            return RestResponse.failure("杯子识别码不能为空");
        }
        cupStudentService.saveOrUpdateCupStudent(cupStudent);
        return RestResponse.success();
    }

    @RequiresPermissions("monitor:cupStudent:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除杯子数据(单个)")
    public RestResponse delete(@RequestParam(value = "id", required = false) Long id) {
        if (id == null || id == 0) {
            return RestResponse.failure("参数错误");
        }
        CupStudent cupStudent = cupStudentService.selectById(id);
        if (cupStudent == null) {
            return RestResponse.failure("杯子信息不存在");
        }
        cupStudentService.deleteCupStudent(cupStudent);
        return RestResponse.success();
    }

    @RequiresPermissions("monitor:cupStudent:delete")
    @PostMapping("deleteSome")
    @ResponseBody
    @SysLog("删除杯子数据(多个)")
    public RestResponse deleteSome(@RequestBody List<CupStudent> cupStudents) {
        if (cupStudents == null || cupStudents.size() == 0) {
            return RestResponse.failure("请选择需要删除的杯子");
        }
        for (CupStudent cupStudent : cupStudents) {
            cupStudentService.deleteCupStudent(cupStudent);
        }
        return RestResponse.success();
    }
}

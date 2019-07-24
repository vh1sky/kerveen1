package com.mysiteforme.admin.sysuser.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.sysuser.entity.DictType;
import com.mysiteforme.admin.sysuser.entity.VO.TreeDict;
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
import java.util.List;
import java.util.Map;

/**
 * @author Iwen
 * @date 2019/6/13 16:02
 * @Version 1.0
 */
@Controller
@RequestMapping("admin/system/dictType")
public class DictTypeController extends BaseController {

    private static final Log log = LogFactory.get();

    @RequiresPermissions("sys:dict:list")
    @PostMapping("list")
    @ResponseBody
    public RestResponse list(ServletRequest request) {

        Map map = WebUtils.getParametersStartingWith(request, "s_");
        List<TreeDict> treeDicts = dictTypeService.showTreeDicts((String) map.get("type"), (String) map.get("name"));
        log.info(JSONObject.toJSONString(treeDicts));

        return RestResponse.success().setData(treeDicts);
    }

    @GetMapping("add")
    public String add(@RequestParam(value = "type", required = false) String type, Model model) {
        if (StringUtils.isNotBlank(type)) {
            model.addAttribute("type", type);
        }
        return "admin/system/dictType/add";
    }

    @RequiresPermissions("sys:dict:add")
    @PostMapping("add")
    @SysLog("新增系统字典类型")
    @ResponseBody
    public RestResponse add(DictType dictType) {
        if (StringUtils.isBlank(dictType.getType())) {
            return RestResponse.failure("字典类型不能为空");
        }
        if (StringUtils.isBlank(dictType.getName())) {
            return RestResponse.failure("字典类型名称不能为空");
        }
        if (dictTypeService.getCountByType(dictType.getType()) > 0) {
            return RestResponse.failure("已经存在该字典类型【" + dictType.getType() + "】");
        }
        if (dictTypeService.getCountByName(dictType.getName()) > 0) {
            return RestResponse.failure("已经存在字典名称为【" + dictType.getName() + "】的字典类型");
        }
        dictTypeService.saveOrUpdateDict(dictType);
        return RestResponse.success();
    }

    @GetMapping("edit")
    public String edit(Long id, Model model) {
        DictType dictType = dictTypeService.selectById(id);
        model.addAttribute("dictType", dictType);
        return "admin/system/dictType/edit";
    }

    @RequiresPermissions("sys:dict:edit")
    @PostMapping("edit")
    @SysLog("编辑系统字典")
    @ResponseBody
    public RestResponse edit(DictType dictType) {
        if (dictType.getId() == null || dictType.getId() == 0) {
            return RestResponse.failure("字典ID不能为空");
        }
        if (StringUtils.isBlank(dictType.getType())) {
            return RestResponse.failure("字典类型不能为空");
        }
        if (StringUtils.isBlank(dictType.getName())) {
            return RestResponse.failure("字典类型名称不能为空");
        }
        DictType oldDictType = dictTypeService.selectById(dictType.getId());
        /*if (!oldDictType.getType().equals(dictType.getType())) {
            return RestResponse.failure("字典类型不能修改");
        }*/
        if ((!oldDictType.getType().equals(dictType.getType())) && dictTypeService.getCountByType(dictType.getType()) > 0) {
            return RestResponse.failure("已经存在该字典类型【" + dictType.getType() + "】");
        }
        if ((!oldDictType.getName().equals(oldDictType.getName())) && dictTypeService.getCountByName(oldDictType.getName()) > 0) {
            return RestResponse.failure("已经存在名称为【" + dictType.getName() + "】的字典类型");
        }
        dictTypeService.saveOrUpdateDict(dictType);
        return RestResponse.success();
    }

}

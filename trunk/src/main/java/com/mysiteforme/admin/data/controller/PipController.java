package com.mysiteforme.admin.data.controller;

import com.google.common.collect.Maps;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.onenet.OnenetConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/5
 */
@Controller
@RequestMapping("admin/data/pip")
public class PipController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PipController.class);

    @GetMapping("list")
    @SysLog("跳转管线机列表")
    public String list(){return "admin/data/pip/list";}

    @PostMapping("list")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public List<Map<String, Object>> selectData(String onenetId){
        Map resultMap = Maps.newHashMap();
        resultMap.put("onenetId", onenetId);
        for (Map<String,String> tmpMap : OnenetConstant.PPM_DATA_STATE){
            if (onenetId.equals(tmpMap.get("onenetId"))){
                resultMap.put("mainboardId", tmpMap.get("pipelineId"));
                resultMap.put("pipeline_net",tmpMap.get("pipeline_net"));
                resultMap.put("pipelineVoltage", tmpMap.get("pipelineVoltage"));
                resultMap.put("pipelinePattern", tmpMap.get("pipelinePattern"));
            }
        }
        for (Map<String,String> tmpMap : OnenetConstant.PPM_DATA_PIPELINE_VALUE){
            if (onenetId.equals(tmpMap.get("onenetId"))){
                resultMap.put("pipelineValvePattern", tmpMap.get("pipelineValvePattern"));
                resultMap.put("pipelineValveAmpere", tmpMap.get("pipelineValveAmpere"));
            }
        }
        for (Map<String,String> tmpMap : OnenetConstant.PPM_DATA_PIPELINE_HUMIDITY){
            if (onenetId.equals(tmpMap.get("onenetId"))) {
                resultMap.put("pipelineHumidity",tmpMap.get("pipelineHumidity"));
            }
        }
        for (Map<String,String> tmpMap : OnenetConstant.PPM_DATA_PIPELINE_LEVEL){
            if (onenetId.equals(tmpMap.get("onenetId"))) {
                resultMap.put("pipelineLevelPattern",tmpMap.get("pipelineLevelPattern"));
            }
        }
        for (Map<String,String> tmpMap : OnenetConstant.PPM_DATA_PIPELINE_OUT){
            if (onenetId.equals(tmpMap.get("onenetId"))) {
                resultMap.put("pipelineOutPattern",tmpMap.get("pipelineOutPattern"));
            }
        }
        for (Map<String,String> tmpMap : OnenetConstant.PPM_DATA_PIPELINE_PUMP1){
            if (onenetId.equals(tmpMap.get("onenetId"))) {
                resultMap.put("pipelinePump1Pattern",tmpMap.get("pipelinePump1Pattern"));
                resultMap.put("pipelinePump1Ampere",tmpMap.get("pipelinePump1Ampere"));
                resultMap.put("pipelinePump1Time",tmpMap.get("pipelinePump1Time"));
            }
        }
        for (Map<String,String> tmpMap : OnenetConstant.PPM_DATA_PIPELINE_PUMP2){
            if (onenetId.equals(tmpMap.get("onenetId"))) {
                resultMap.put("pipelinePump2Pattern",tmpMap.get("pipelinePump2Pattern"));
                resultMap.put("pipelinePump2Time",tmpMap.get("pipelinePump2Time"));
                resultMap.put("pipelinePump2Ampere",tmpMap.get("pipelinePump2Ampere"));
            }
        }
        for (Map<String,String> tmpMap : OnenetConstant.PPM_DATA_PIPELINE_TEMPERATURE){
            if (onenetId.equals(tmpMap.get("onenetId"))) {
                resultMap.put("pipelineTemperature",tmpMap.get("pipelineTemperature"));
            }
        }
        for (Map<String,String> tmpMap : OnenetConstant.PPM_DATA_PIPELINE_TPROBE){
            if (onenetId.equals(tmpMap.get("onenetId"))) {
                resultMap.put("pipelineTprobePattern",tmpMap.get("pipelineTprobePattern"));
                resultMap.put("pipelineTprobeHot",tmpMap.get("pipelineTprobeHot"));
            }
        }
        for (Map<String,String> tmpMap : OnenetConstant.PPM_DATA_PIPELINE_UV){
            if (onenetId.equals(tmpMap.get("onenetId"))) {
                resultMap.put("pipelineUVPattern",tmpMap.get("pipelineUVPattern"));
                resultMap.put("pipelineUVAmpere",tmpMap.get("pipelineUVAmpere"));
            }
        }
        for (Map<String,String> tmpMap : OnenetConstant.PPM_DATA_PIPELINE_WARN){
            if (onenetId.equals(tmpMap.get("onenetId"))) {
                resultMap.put("pipelineWarnWater",tmpMap.get("pipelineWarnWater"));
                resultMap.put("pipelineWarnHot",tmpMap.get("pipelineWarnHot"));
                resultMap.put("pipelineWarnProbe",tmpMap.get("pipelineWarnProbe"));
            }
        }
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        list.add(resultMap);
        return list;
    }
}

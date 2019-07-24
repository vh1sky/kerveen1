package com.mysiteforme.admin.mqtt;

import cmcc.iot.onenet.javasdk.api.cmds.GetCmdsHistoryApi;
import cmcc.iot.onenet.javasdk.api.datastreams.FindDatastreamListApi;
import cmcc.iot.onenet.javasdk.api.datastreams.GetDatastreamApi;
import cmcc.iot.onenet.javasdk.api.device.*;
import cmcc.iot.onenet.javasdk.api.mqtt.GetUserTopics;
import cmcc.iot.onenet.javasdk.model.Location;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.cmds.CmdsList;
import cmcc.iot.onenet.javasdk.response.datastreams.DatastreamsResponse;
import cmcc.iot.onenet.javasdk.response.device.*;
import cmcc.iot.onenet.javasdk.utils.OrderConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Iwen
 * @date 2019/6/1 9:54
 * @Version 1.0
 */
public class OneNetUtil {

    @Autowired
    private OneNetConfig config;

    /****
     * 设备新增
     * 参数顺序与构造函数顺序一致
     * @param title： 设备名，String
     * @param protocol： 接入协议（可选，默认为HTTP）,一般选择DTU,String
     * @param desc： 设备描述（可选）,String
     * @param tags： 设备标签（可选，可为一个或多个）,List<String>
     * @param location： 设备位置{"纬度", "精度", "高度"}（可选）,Location类型
     * @param isPrivate： 设备私密性,Boolean类型（可选，默认为ture）
     * @param authInfo： 设备唯一编号 ,Object
     * @param other： 其他信息,Map<String, Object>（可选，可自定义）
     * @param interval： MODBUS设备 下发命令周期,Integer类型，秒（可选）
     * @return data: device_id,code,msg
     */
    public LinkedHashMap addDevices(String title, String desc, String protocol, Location location, List<String> tags,
                                    Boolean isPrivate, Object authInfo, Map<String, Object> other, Integer interval, String key) {
        if (StringUtils.isEmpty(key)){
            key = config.getMasterApiKey();
        }

        AddDevicesApi api = new AddDevicesApi(title, protocol, desc, tags, location, isPrivate, authInfo, other, interval, key);
        BasicResponse<NewDeviceResponse> response = api.executeApi();

        LinkedHashMap data = (LinkedHashMap)response.getDataInternal();
        data.put("code",response.errno);
        data.put("msg",response.error);

        return data;
    }

    /**
     * 设备注册
     * 参数顺序与构造函数顺序一致
     * @param mac：设备唯一mac标识，最长32字符(可为null)
     * @param sn：设备唯一标识String类型，最长512字符
     * @param title:设备名（可选） 最长32个字符
     * @return data: code,msg,device_id,key(具备仅限该设备访问权限的apikey)
     */
    public LinkedHashMap registerDeviceApi(String mac, String sn, String title,String key) {
        if (StringUtils.isEmpty(key)){
            key = config.getMasterApiKey();
        }

        RegisterDeviceApi api = new RegisterDeviceApi(config.getDeviceCode(), mac, sn, title, key);
        BasicResponse<RegDeviceResponse> response = api.executeApi();
        LinkedHashMap data = (LinkedHashMap)response.getDataInternal();
        data.put("code",response.errno);
        data.put("msg",response.error);

        return data;
    }

    /***
     * 设备更新
     * 参数顺序与构造函数顺序一致
     * @param id： 设备ID,String
     * @param title： 设备名，String
     * @param desc： 设备描述（可选），String
     * @param protocol： 接入协议（可选，默认为HTTP），String
     * @param location： 设备位置{"纬度", "精度", "高度"}（可选）,Location类型
     * @param tags： 设备标签（可选，可为一个或多个），List<String>
     * @param isPrivate： 设备私密性，Boolean类型
     * @param authInfo： 设备唯一编号 ，Object
     * @param other：  其他信息，Map<String, Object>
     * @param interval： MODBUS设备 下发命令周期,Integer类型
     * @param key ：masterkey 或者 设备apikey,String
     */
    public LinkedHashMap modifyDevices(String id, String title, String desc, String protocol, Location location, List<String> tags, Boolean isPrivate, Object authInfo, Map<String, Object> other, Integer interval, String key) {
        if (StringUtils.isEmpty(key)){
            key = config.getMasterApiKey();
        }

        ModifyDevicesApi api = new ModifyDevicesApi(id, title, protocol, desc, tags, location, isPrivate, authInfo, other, interval, key);
        BasicResponse<Void> response = api.executeApi();

        LinkedHashMap data = new LinkedHashMap();
        data.put("code",response.errno);
        data.put("msg",response.error);

       return data;
    }

    /**
     * 精确查询单个设备
     * 参数顺序与构造函数顺序一致
     * @param deviceId:设备id，String
     * @return key:masterkey 或者 设备apikey,String
     * (返回值参考：{"code":0,"msg":"succ",""private":true,"protocol":"DTU","create_time":"2019-06-01 12:21:56",
     * "keys":[{"title":"auto generated device key","key":"vW1Zb3StHBLZvJq=HHiAEktIBd4="}],"online":false,"id":"528391018",
     * "auth_info":"2017031401427","title":"myddddvvv2s"}）
     */
    public LinkedHashMap getDevice(String deviceId, String key) {
        if (StringUtils.isEmpty(key)){
            key = config.getMasterApiKey();
        }

        GetDeviceApi api = new GetDeviceApi(deviceId, key);
        BasicResponse<DeviceResponse> response = api.executeApi();
        LinkedHashMap data = (LinkedHashMap)response.getDataInternal();
        data.put("code",response.errno);
        data.put("msg",response.error);

        return data;
    }

    /**
     * 批量查询设备状态
     * 参数顺序与构造函数顺序一致
     * @param deviceIds:设备id用逗号隔开, 限制1000个设备
     */
    public LinkedHashMap getDevicesStatus(String deviceIds, String key) {
        if (StringUtils.isEmpty(key)){
            key = config.getMasterApiKey();
        }
        GetDevicesStatus api = new GetDevicesStatus(deviceIds,key);
        BasicResponse<DevicesStatusList> response = api.executeApi();
        LinkedHashMap data = (LinkedHashMap)response.getDataInternal();
        data.put("code",response.errno);
        data.put("msg",response.error);

        return data;
    }


    /**
     * 批量查询设备最新数据
     * 参数顺序与构造函数顺序一致
     * @param deviceIds :设备id用逗号隔开, 限制1000个设备,String
     */
    public LinkedHashMap getLatesDevicesData(String deviceIds, String key) {
        if (StringUtils.isEmpty(key)){
            key = config.getMasterApiKey();
        }

        GetLatesDeviceData api = new GetLatesDeviceData(deviceIds,key);
        BasicResponse<DeciceLatestDataPoint> response = api.executeApi();
        LinkedHashMap data = (LinkedHashMap)response.getDataInternal();
        data.put("code",response.errno);
        data.put("msg",response.error);

        return data;
    }

    /**
     * 查询单个数据流
     * @param deviceId:设备ID,String
     * @param dataStreamId:数据流名称 ,String
     * @param key:masterkey 或者 设备apikey
     */
    public LinkedHashMap getDataStream(String deviceId, String dataStreamId, String key) {
       if (StringUtils.isEmpty(key)){
           key = config.getMasterApiKey();
       }

        GetDatastreamApi api = new GetDatastreamApi(deviceId, dataStreamId, key);
        BasicResponse<DatastreamsResponse> response = api.executeApi();
        LinkedHashMap data = (LinkedHashMap)response.getDataInternal();
        data.put("code",response.errno);
        data.put("msg",response.error);

        return data;
    }

    /**
     * 查询多个数据流
     * @param dataStreamIds:数据流名称 ,String
     * @param deviceId:设备ID,String
     * @param key:masterkey 或者 设备apikey
     */
    public LinkedHashMap getDatastreamsList(String dataStreamIds, String deviceId, String key) {
        if (StringUtils.isEmpty(key)){
            key = config.getMasterApiKey();
        }

        FindDatastreamListApi api = new FindDatastreamListApi(dataStreamIds, deviceId, key);
        BasicResponse<List<DatastreamsResponse>> response = api.executeApi();
        LinkedHashMap data = (LinkedHashMap)response.getDataInternal();
        data.put("code",response.errno);
        data.put("msg",response.error);

        return data;
    }

    /**
     * 查询设备历史命令
     * @param deviceId：设备id
     * @param key：master-key
     * @param start：起始时间 2018-12-28T08:00:00
     * @param end：结束时间 2018-12-28T08:00:00
     * @param duration：时间间隔
     * @param limit：查询条数
     * @param sort：ASC/DESC
     * @param status：命令状态
     */
    /**
     * status说明
     * 1：命令已创建| Command Created
     * 2：命令已发往设备| Command Sent
     * 3：命令发往设备失败| Send Command Failed
     * 4：设备正常响应| Command Response Received
     * 5：命令执行超时| Command Response Timeout
     * 6：设备响应消息过长 | Command Response Too Large
     **/
    public LinkedHashMap getCmdsHistory(String deviceId, String start, String end, Integer duration, Integer limit, Integer status, String key){
        if (StringUtils.isEmpty(key)){
            key = config.getMasterApiKey();
        }
        if (limit == 0)
            limit = 10;
        if (status == 0)
            status = 1;

        BasicResponse<CmdsList>  response = new GetCmdsHistoryApi(deviceId,key, start,end,duration,limit,null, OrderConstant.ORDER_ASC,status).executeApi();
        LinkedHashMap data = (LinkedHashMap)response.getDataInternal();
        data.put("code",response.errno);
        data.put("msg",response.error);

        return data;
    }
}

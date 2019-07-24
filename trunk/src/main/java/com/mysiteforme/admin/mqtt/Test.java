package com.mysiteforme.admin.mqtt;


import cmcc.iot.onenet.javasdk.api.bindata.AddBindataApi;
import cmcc.iot.onenet.javasdk.api.bindata.GetBindataApi;
import cmcc.iot.onenet.javasdk.api.cmds.GetCmdsHistoryApi;
import cmcc.iot.onenet.javasdk.api.cmds.QueryCmdsRespApi;
import cmcc.iot.onenet.javasdk.api.cmds.QueryCmdsStatus;
import cmcc.iot.onenet.javasdk.api.cmds.SendCmdsApi;
import cmcc.iot.onenet.javasdk.api.datapoints.AddDatapointsApi;
import cmcc.iot.onenet.javasdk.api.datapoints.GetDatapointsListApi;
import cmcc.iot.onenet.javasdk.api.datastreams.*;
import cmcc.iot.onenet.javasdk.api.device.*;
import cmcc.iot.onenet.javasdk.api.dtu.AddDtuParser;
import cmcc.iot.onenet.javasdk.api.dtu.DeleteDtuParser;
import cmcc.iot.onenet.javasdk.api.dtu.FindDtuParserList;
import cmcc.iot.onenet.javasdk.api.dtu.ModifyDtuParser;
import cmcc.iot.onenet.javasdk.api.key.AddKeyApi;
import cmcc.iot.onenet.javasdk.api.key.DeleteKeyApi;
import cmcc.iot.onenet.javasdk.api.key.FindKeyList;
import cmcc.iot.onenet.javasdk.api.key.ModifyKeyApi;
import cmcc.iot.onenet.javasdk.api.mqtt.*;
import cmcc.iot.onenet.javasdk.api.triggers.*;
import cmcc.iot.onenet.javasdk.model.*;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.bindata.NewBindataResponse;
import cmcc.iot.onenet.javasdk.response.cmds.CmdsList;
import cmcc.iot.onenet.javasdk.response.cmds.CmdsResponse;
import cmcc.iot.onenet.javasdk.response.cmds.NewCmdsResponse;
import cmcc.iot.onenet.javasdk.response.datapoints.DatapointsList;
import cmcc.iot.onenet.javasdk.response.datastreams.DatastreamsResponse;
import cmcc.iot.onenet.javasdk.response.datastreams.NewdatastramsResponse;
import cmcc.iot.onenet.javasdk.response.device.*;
import cmcc.iot.onenet.javasdk.response.dtu.DtuParserList;
import cmcc.iot.onenet.javasdk.response.dtu.NewParserResponse;
import cmcc.iot.onenet.javasdk.response.key.KeyList;
import cmcc.iot.onenet.javasdk.response.key.NewKeyResponse;
import cmcc.iot.onenet.javasdk.response.mqtt.TopicDeviceList;
import cmcc.iot.onenet.javasdk.response.triggers.NewTriggersResponse;
import cmcc.iot.onenet.javasdk.response.triggers.TriggersList;
import cmcc.iot.onenet.javasdk.response.triggers.TriggersResponse;
import cmcc.iot.onenet.javasdk.utils.OrderConstant;
import com.xiaoleilu.hutool.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/**
 * @author Iwen
 * @date 2019/5/31 17:53
 * @Version 1.0
 */
public class Test {

    //当然括号里的参数名可以不一样
    public static void main(String[] args) {
        try {
            while (true) {
                Thread.sleep(1000);
                doPostTestFour();
            }
        } catch (InterruptedException e) {
        }

    }

    /**
     * POST---有参测试(普通参数)
     *
     * @date 2018年7月13日 下午4:18:50
     */
    public static void doPostTestFour() {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        StringBuffer params = new StringBuffer();
        try {
            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            params.append("name=" + URLEncoder.encode("王文", "utf-8"));
            params.append("&");
            params.append("mobile=17353251103");
            params.append("&");
            params.append("city=" + URLEncoder.encode("重庆", "utf-8"));
            params.append("&");
            params.append("userGuid=0e31293a-64e3-853d-6767-0c53fc8c2542");
            params.append("&");
            params.append("openid=oxJHPwRhtlrtpRom7HIgXvyqqbQ0");
            params.append("&");
            params.append("sopenid=ab4faee7ce2a571f8cdfae544eff97da");
            params.append("&");
            params.append("sopenid=ab4faee7ce2a571f8cdfae544eff97da");
            params.append("&");
            params.append("action=getcode");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // 创建Post请求
        HttpPost httpPost = new HttpPost("https://wx.fractalist.com.cn/buick/api/CommonApi.ashx" + "?" + "name=%E7%8E%8B%E6%96%87&mobile=17353251103&city=%E9%87%8D%E5%BA%86&userGuid=ae181d1f-53ef-c815-8b3c-fd24b1103e8d&openid=oxJHPwRhtlrtpRom7HIgXvyqqbQ0&sopenid=ab4faee7ce2a571f8cdfae544eff97da&action=getcode");

        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
//        httpPost.setHeader("Host", "wx.fractalist.com.cn");
//        httpPost.setHeader("Connection", "keep-alive");
//        httpPost.setHeader("Content-Length", "204");
        httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpPost.setHeader("Origin", "https://wx.wetianxia.cn");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 8.0.0; MI 6 Build/OPR1.170623.027; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/67.0.3396.87 XWEB/880 MMWEBSDK/190301 Mobile Safari/537.36 MMWEBID/332 MicroMessenger/7.0.4.1420(0x2700043C) Process/tools NetType/4G Language/zh_CN");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setHeader("Referer", "https://wx.wetianxia.cn/webapps/html/velite6/?openid=oxJHPwRhtlrtpRom7HIgXvyqqbQ0&sopenid=ab4faee7ce2a571f8cdfae544eff97da");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Accept-Language", "zh-CN,en-US;q=0.9");
        httpPost.setHeader("X-Requested-With", "com.tencent.mm");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                String content = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + content);

                String filePath = "D:\\link12345.txt";
                try {

                org.json.JSONObject jsonMsg = new org.json.JSONObject(content);
                if ((!"您已提交信息无需重复提交哦~".equals(jsonMsg.getString("msg"))) || (!"2".equals(jsonMsg.getString("errcode")))
                || StringUtils.isNotBlank(jsonMsg.getString("usecode"))) {
                    System.out.println(jsonMsg);
                    new Test().WriteStringToFile5(content, filePath);
                }
                }catch (JSONException e){}

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void WriteStringToFile(String filePath) {
        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println("http://www.docin.com/p-315288370.html");// 往文件里写入字符串
            ps.append("http://www.docin.com/p-315288370.html");// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void WriteStringToFile2(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append("在已有的基础上添加字符串");
            bw.write("abc\r\n ");// 往已有的文件上添加字符串
            bw.write("def\r\n ");
            bw.write("hijk ");
            bw.close();
            fw.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void WriteStringToFile3(String filePath) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.println("abc ");
            pw.println("def ");
            pw.println("hef ");
            pw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void WriteStringToFile4(String filePath) {
        try {
            RandomAccessFile rf = new RandomAccessFile(filePath, "rw");
            rf.writeBytes("op\r\n");
            rf.writeBytes("app\r\n");
            rf.writeBytes("hijklllll");
            rf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteStringToFile5(String content, String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath,true);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void testAdddevices() {
        String key = "prT9fgAxfN0gvImrl5SYp27h=WA=";
        String title = "devices_test";
        String desc = "devices_test";
        String protocol = "HTTP";
        Location location = new Location(106, 29, 370);//设备位置{"纬度", "经度", "高度"}（可选）
        List<String> tags = new ArrayList<String>();
        tags.add("china");
        tags.add("mobile");
        String auth_info = "201503041a5829151";
        /****
         * 设备新增
         * 参数顺序与构造函数顺序一致
         * @param title： 设备名，String
         * @param protocol： 接入协议（可选，默认为HTTP）,String
         * @param desc： 设备描述（可选）,String
         * @param tags： 设备标签（可选，可为一个或多个）,List<String>
         * @param location： 设备位置{"纬度", "精度", "高度"}（可选）,Location类型
         * @param isPrivate： 设备私密性,Boolean类型（可选，默认为ture）
         * @param authInfo： 设备唯一编号 ,Object
         * @param other： 其他信息,Map<String, Object>（可选，可自定义）
         * @param interval： MODBUS设备 下发命令周期,Integer类型，秒（可选）
         * @param key： masterkey,String
         */
        AddDevicesApi api = new AddDevicesApi(title, protocol, desc, tags, location, null, auth_info, null, null, key);
        BasicResponse<NewDeviceResponse> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
        System.out.println(response.getJson());

    }

    public void testModifydevices() {
        String id = "1674527";
        String key = "9ylHzkz25nre41i=SuJR=F=k5kU=";
        String title = "devices_test2";
        String desc = "devices_test2";
        String protocol = "HTTP";
        List<String> tags = new ArrayList<String>();
        tags.add("china");
        tags.add("mobile");
        String auth_info = "201503041a5829151";
        /***
         * 设备更新
         * 参数顺序与构造函数顺序一致
         * @param id： 设备ID,String
         * @param title： 设备名，String
         * @param protocol： 接入协议（可选，默认为HTTP），String
         * @param desc： 设备描述（可选），String
         * @param tags： 设备标签（可选，可为一个或多个），List<String>
         * @param location： 设备位置{"纬度", "精度", "高度"}（可选）,Location类型
         * @param isPrivate： 设备私密性，Boolean类型
         * @param authInfo： 设备唯一编号 ，Object
         * @param other：  其他信息，Map<String, Object>
         * @param interval： MODBUS设备 下发命令周期,Integer类型
         * @param key ：masterkey 或者 设备apikey,String
         */
        ModifyDevicesApi api = new ModifyDevicesApi(id, title, protocol, desc, tags, null, null, auth_info, null, null, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    public void testGetdevice() {
        String id = "1674527";
        String key = "9ylHzkz25nre41i=SuJR=F=k5kU=";
        /**
         * 精确查询单个设备
         * 参数顺序与构造函数顺序一致
         * @param devid:设备名，String
         * @param key:masterkey 或者 设备apikey,String
         */
        GetDeviceApi api = new GetDeviceApi(id, key);
        BasicResponse<DeviceResponse> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
        System.out.println(response.getJson());
    }

    public void testFinddevicesList() {
        String key = "9ylHzkz25nre41i=SuJR=F=k5kU=";

        /**
         * 模糊查询设备
         * 参数顺序与构造函数顺序一致
         * @param keywords:匹配关键字（可选，从id和title字段中左匹配）,String
         * @param authinfo:鉴权信息（可选，对应注册时的sn参数，唯一设备编号）,Object
         * @param devid: 指定设备ID，多个用逗号分隔，最多100个（可选）,String
         * @param begin:起始时间，包括当天（可选）,Date
         * @param end:结束时间，包括当天（可选）,Date
         * @param tags:设备标签（可选，可为一个或多个）,List<String>
         * @param isPrivate： 设备私密性，Boolean类型
         * @param page:指定页码，最大页数为10000（可选）,Integer
         * @param perpage:指定每页输出设备个数，默认30，最多100（可选）,Integer
         * @param isOnline:在线状态（可选）
         * @param key:masterkey
         */
        FindDevicesListApi api = new FindDevicesListApi(null, null, null, null, null, null, null, null, null, null,
                null, null, key);
        BasicResponse<DeviceList> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
        System.out.println(response.getJson());
    }

    public void testGetDevicesStatusApi() {
        String key = "9ylHzkz25nre41i=SuJR=F=k5kU=";
        String devIds = "2406529,2406530";
        /**
         * 批量查询设备状态
         * 参数顺序与构造函数顺序一致
         * @param devIds:设备id用逗号隔开, 限制1000个设备
         * @param key :masterkey 或者 设备apikey,String
         */
        GetDevicesStatus api = new GetDevicesStatus(devIds, key);
        BasicResponse<DevicesStatusList> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
        System.out.println(response.getJson());
    }

    public void testGetLatesDeviceDataApi() {
        String key = "0SpE8tn39qhzN4Kj1JWtgv0N1kM=";
        String devIds = "528924930";
        /**
         * 批量查询设备最新数据
         * 参数顺序与构造函数顺序一致
         * @param devIds :设备id用逗号隔开, 限制1000个设备,String
         * @param key:masterkey
         */
        GetLatesDeviceData api = new GetLatesDeviceData(devIds, key);
        BasicResponse<DeciceLatestDataPoint> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
        System.out.println(response.getJson());
    }

    public void testRemovedeviceApi() {
        String id = "1674526";
        String key = "9ylHzkz25nre41i=SuJR=F=k5kU=";
        /**
         * 设备删除
         * 参数顺序与构造函数顺序一致
         * @param devid: 设备ID,String
         * @param key: masterkey
         */
        DeleteDeviceApi api = new DeleteDeviceApi(id, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    public void testRegisterDeviceApi() {
        String key = "9ylHzkz25nre41i=SuJR=F=k5kU=";
        String sn = "2017031401421";
        String title = "myddddvvv2s";
        String code = "ZSAuX3f1QPNp2n1m";
        /**
         * 设备注册
         * 参数顺序与构造函数顺序一致
         * @param code：设备注册码（必填）,String
         * @param mac：设备唯一mac标识，最长32字符
         * @param sn：设备唯一标识String类型，最长512字符
         * @param title:设备名（可选） 最长32个字符
         * @param key:设备注册码（必填）
         */
        RegisterDeviceApi api = new RegisterDeviceApi(code, null, sn, title, key);
        BasicResponse<RegDeviceResponse> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
        System.out.println(response.getData().key);
    }

    public void testAddDatastreamsApi() {
        String key = "9ylHzkz25nre41i=SuJR=F=k5kU=";
        String id = "temperature2";
        String devId = "1674527";
        List<String> tags = new ArrayList<String>();
        tags.add("china");
        tags.add("mobile");
        String unit = "celsius";
        String unitsymbol = "C";
        String cmd = "0003000000184411";
        int interval = 60;
        String formula = "(A0+A1)*A2";
        /**
         * 数据流新增
         * @param id：数据流名称 ，String
         * @param devId:设备ID,String
         * @param tags:数据流标签（可选，可以为一个或多个）,List<Stirng>
         * @param unit:单位（可选）,String
         * @param unitSymbol:单位符号（可选）,String
         * @param cmd:MODBUS设备填写，周期下发命令，16进制字节字符串
         * @param interval:MODBUS设备填写，采集间隔，秒,Integer
         * @param formula:MODBUS设备填写，寄存器计算公式（可选）,String
         * @param key:masterkey 或者 设备apikey
         */
        AddDatastreamsApi api = new AddDatastreamsApi(id, devId, tags, unit, unitsymbol, cmd, interval, formula, key);
        BasicResponse<NewdatastramsResponse> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
        System.out.println(response.getData().id);
    }

    public void testModifyDatastreamsApi() {
        String dsid = "temperature2";
        String key = "9ylHzkz25nre41i=SuJR=F=k5kU=";
        String devId = "1674527";
        List<String> tags = new ArrayList<String>();
        tags.add("china");
        tags.add("mobile");
        String unit = "celsius";
        String unitsymbol = "C";
        String cmd = "0003000000184411";
        int interval = 80;
        String formula = "(A0+A1)*A2";
        /**
         * 数据流更新
         * @param dsid:数据流名称 ,String
         * @param devId:设备ID,String
         * @param tags:数据流标签（可选，可以为一个或多个）
         * @param unit:单位（可选）,String
         * @param unitSymbol:单位符号（可选）,String
         * @param cmd:MODBUS设备填写，周期下发命令，16进制字节字符串
         * @param interval:MODBUS设备填写，采集间隔，秒,Integer
         * @param formula:MODBUS设备填写，寄存器计算公式（可选）,String
         * @param key:masterkey 或者 设备apikey
         */
        ModifyDatastramsApi api = new ModifyDatastramsApi(dsid, devId, tags, unit, unitsymbol, cmd, interval, formula,
                key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);

    }

    public void testGetDatastream() {
        String devId = "212141";
        String id = "datastream_idxx";
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        /**
         * 查询单个数据流
         * @param devid:设备ID,String
         * @param datastreamid:数据流名称 ,String
         * @param key:masterkey 或者 设备apikey
         */
        GetDatastreamApi api = new GetDatastreamApi(devId, id, key);
        BasicResponse<DatastreamsResponse> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
        System.out.println(response.getData().getId());
    }

    public void testFindDatastreamsListApi() {
        String datastreamids = "datastream_idxx,datastream_idxy";
        String devid = "212141";
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        /**
         * 查询多个数据流
         * @param datastreamids:数据流名称 ,String
         * @param devid:设备ID,String
         * @param key:masterkey 或者 设备apikey
         */
        FindDatastreamListApi api = new FindDatastreamListApi(datastreamids, devid, key);
        BasicResponse<List<DatastreamsResponse>> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
        System.out.println(response.getJson());
    }

    public void testRemoveDatastreamApi() {
        String dsid = "temperature2";
        String key = "9ylHzkz25nre41i=SuJR=F=k5kU=";
        String devId = "1674527";
        /**
         * 数据流删除
         * @param devid:设备ID,String
         * @param datastreamid:数据流名称 ,String
         * @param key:masterkey 或者 设备apikey
         */
        DeleteDatastreamsApi api = new DeleteDatastreamsApi(devId, dsid, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    public void testAddDatapointsApi() {
        String devid = "212141";
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        List<Datapoints> list = new ArrayList<Datapoints>();
        List<Data> dl = new ArrayList<Data>();
        dl.add(new Data("2013-04-22T00:35:43", 11));
        dl.add(new Data("2013-04-22T00:36:43", 12));
        list.add(new Datapoints("datastream_idxx", dl));
        list.add(new Datapoints("datastream_idxy", dl));
        Map<String, List<Datapoints>> map = new HashMap<String, List<Datapoints>>();
        map.put("datastreams", list);
        /**
         * 数据点新增
         * @param map :数据点内容,Map<String,List<Datapoints>>
         * @param data:提供简写方式上传数据,String
         * 示例：
         * type=4
         * data="{\"temperature\":{\"2015-03-22T22:31:12\":22.5}}";
         * type=5
         * data=",;temperature,2015-03-22T22:31:12,22.5;pm2.5,89";
         * @param type:上传数据类型（可选，默认为完整JSON型，见HTTP内容示例）
         * @param devId:设备ID,String
         * @param key:masterkey 或者 设备apikey
         */
        AddDatapointsApi api = new AddDatapointsApi(map, null, null, devid, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    //type=4时数据点上传
    public void testAddDatapointsTypefourApi() {
        String devid = "212141";
        int type = 4;
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        String data = "{\"datastream_idxx\":{\"2015-03-22T22:31:12\":22.5}}";

        /**
         * 数据点新增
         * @param map :数据点内容,Map<String,List<Datapoints>>
         * @param data:提供简写方式上传数据,String
         * 示例：
         * type=4
         * data="{\"temperature\":{\"2015-03-22T22:31:12\":22.5}}";
         * type=5
         * data=",;temperature,2015-03-22T22:31:12,22.5;pm2.5,89";
         * @param type:上传数据类型（可选，默认为完整JSON型，见HTTP内容示例）
         * @param devId:设备ID,String
         * @param key:masterkey 或者 设备apikey
         */
        AddDatapointsApi api = new AddDatapointsApi(null, data, type, devid, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    public void testGetDatapointsApi() {
        String datastreamids = "datastream_idxx,datastream_idxy";
        String devid = "212141";
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        /**
         * 数据点查询
         * @param datastreamids:查询的数据流，多个数据流之间用逗号分隔（可选）,String
         * @param start:提取数据点的开始时间（可选）,String
         * @param end:提取数据点的结束时间（可选）,String
         * @param devid:设备ID,String
         *
         * @param duration:查询时间区间（可选，单位为秒）,Integer
         *  start+duration：按时间顺序返回从start开始一段时间内的数据点
         *  end+duration：按时间倒序返回从end回溯一段时间内的数据点
         *
         * @param limit:限定本次请求最多返回的数据点数，0<n<=6000（可选，默认1440）,Integer
         * @param cursor:指定本次请求继续从cursor位置开始提取数据（可选）,String
         * @param interval:通过采样方式返回数据点，interval值指定采样的时间间隔（可选）,Integer
         * @param metd:指定在返回数据点时，同时返回统计结果，可能的值为（可选）,String
         * @param first:返回结果中最值的时间点。1-最早时间，0-最近时间，默认为1（可选）,Integer
         * @param sort:值为DESC|ASC时间排序方式，DESC:倒序，ASC升序，默认升序,String
         * @param key:masterkey 或者 设备apikey
         */
        GetDatapointsListApi api = new GetDatapointsListApi(datastreamids, null, null, devid, null, null, null, null,
                null, null, null, key);
        BasicResponse<DatapointsList> response = api.executeApi();
        System.out.println(response.getJson());
    }

    public void testAddtriggersApi() {
        String dsid = "datastream_idxx";
        String url = "http://xx.bb.com";
        String type = "==";
        int threshold = 100;
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        /**
         * 触发器新增
         * @param title:名称（可选）,String
         * @param dsid:数据流名称（id）（可选）,String
         * @param devids:设备ID（可选）,List<String>
         * @param dsuuids:数据流uuid（可选）,List<String>
         * @param desturl:url,String
         * @param type:触发类型，String
         * @param threshold:阙值，根据type不同，见以下说明,Integer
         * @param key:masterkey 或者 设备apikey
         */
        AddTriggersApi api = new AddTriggersApi(null, dsid, null, null, url, type, threshold, key);
        BasicResponse<NewTriggersResponse> response = api.executeApi();
        System.out.println(response.getJson());
    }

    public void testModifyTriggersApi() {
        String url = "http://xx.bbc.com";
        String type = "==";
        int threshold = 100;
        List<String> dsuuids = new ArrayList<String>();
        dsuuids.add("28ccffa8-9eab-53d0-8365-84928950c473");
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        String tirggerid = "288";
        /**
         * 触发器更新
         * @param tirggerid:触发器ID,String
         * @param title:名称（可选）,String
         * @param dsid数据流名称（id）（可选）,String
         * @param devids:设备ID（可选）,List<String>
         * @param dsuuids:数据流uuid（可选）,List<String>
         * @param desturl:url,String
         * @param type:触发类型，String
         * @param threshold:阙值，根据type不同，见以下说明,Integer
         * @param key:masterkey 或者 设备apikey
         */
        ModifyTriggersApi api = new ModifyTriggersApi(tirggerid, null, null, null, dsuuids, url, type, threshold, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    public void testModifyKeyRelDeviceListApi() {
        String apiKey = "aZTcGDO6HEjluD85aIe=tgoaOV8=";
        String masterKey = "BRON6gVO7RPrDEngH8W32prf4lg=";
        String addDevId1 = "19751301";
        String addDevId2 = "19751420";
        String delDevId1 = "19751419";
        /**
         * 修改apiKey关联设备关系api
         * @param addDevIds:待关联设备列表，List<String>
         * @param delDevIds:已关联设备列表,List<String>
         * @param apiKey:设备apikey
         * @param masterkey:产品masterkey
         */
        BasicResponse<ModifyKeyRelDeviceResponse> re = new ModifyKeyRelDeviceListApi
                (Arrays.asList(addDevId1, addDevId2), Arrays.asList(delDevId1), apiKey, masterKey).executeApi();
        System.out.println(re.getJson());
    }

    public void testGetTriggersApi() {
        String tirggerid = "288";
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        /**
         * 查询单个触发器
         * @param tirggerid:触发器ID,String
         * @param key:masterkey 或者 设备apikey
         */
        GetTriggersApi api = new GetTriggersApi(tirggerid, key);
        BasicResponse<TriggersResponse> response = api.executeApi();
        System.out.println(response.getJson());
    }

    public void testFindTriggersListApi() {
        String title = "test";
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        /**
         * 模糊查询触发器
         * @param title:名称（可选）,String
         * @param page:指定页码，最大页数为10000（可选）,Integer
         * @param perpage:指定每页输出设备个数，默认30，最多100（可选）,Integer
         * @param key:masterkey 或者 设备apikey
         */
        FindTriggersListApi api = new FindTriggersListApi(title, null, null, key);
        BasicResponse<TriggersList> response = api.executeApi();
        System.out.println(response.getJson());
    }


    public void testFindKeyRelDeviceListApi() {
        String apiKey = "aZTcGDO6HEjluD85aIe=tgoaOV8=";
        String masterKey = "BRON6gVO7RPrDEngH8W32prf4lg=";
        boolean isRelated = true;
        /**
         * api-key关联/未关联的设备分页列表查询
         * @param page:指定页码，最大页数为10000（可选）,Integer
         * @param perPage:指定每页输出设备个数，默认30，最多100（可选）,Integer
         * @param deviceId:设备id,String
         * @param deviceTitle:设备名称,String
         * @param isRelated:关联/未关联,bool
         * @param apiKey:设备apiKey
         * @param masterKey:产品masterkey
         */
        BasicResponse<KeyRelDeviceList> list = new FindKeyRelDeviceListApi(1, 10, null, "", isRelated, apiKey, masterKey).executeApi();
        System.out.println(list.getJson());
    }


    public void testRemoveTriggersApi() {
        String tirggerid = "3228";
        String key = "9ylHzkz25nre41i=SuJR=F=k5kU=";
        /**
         * 触发器删除
         * @param tirggerid:触发器ID,String
         * @param key:masterkey 或者 设备apikey
         */
        DeleteTriggersApi api = new DeleteTriggersApi(tirggerid, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    public void testAddBindataApi() {
        String devId = "212141";
        String datastreamid = "datastream_idxy";
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        String filename = "a";
        String filepath = "E://data.txt";
        /**
         *
         * 二进制数据新增
         * @param devId:数据所属设备（必选）,String
         * @param datastreamid:该数据所属已经存在的数据流（必选）,String
         * @param key:masterkey 或者 该设备的设备key
         * @param filename:文件名,String
         * @param filepath：路径,String
         */
        AddBindataApi api = new AddBindataApi(devId, datastreamid, key, filename, filepath);
        BasicResponse<NewBindataResponse> response = api.executeApi();
        System.out.println(response.getJson());
    }

    public void testGetBindataApi() {
        String index = "212141_1490712458735_datastream_idxx";
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        /**
         * 二进制数据查询
         * @param index:二进制数据索引号,String
         * @param key:masterkey 或者 该设备的设备key
         */
        GetBindataApi api = new GetBindataApi(index, key);
        System.out.println(api.executeApi());
    }

    public void testAddKeyApi() {
        String title = "sharing key";
        List<Permissions> permissions = new ArrayList<Permissions>();
        List<Devices> resources = new ArrayList<Devices>();
        List<String> accessMethods = new ArrayList<String>();
        resources.add(new Devices("212141"));
        accessMethods.add("POST");
        accessMethods.add("GET");
        accessMethods.add("PUT");
        permissions.add(new Permissions(resources, accessMethods));
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        /**
         * API key新增
         * @param title:名称（可选）,String
         * @param permissions：apikey权限,List<Permissions>permissions
         * @param key:masterkey(注：只能为master-key)
         */
        AddKeyApi api = new AddKeyApi(title, permissions, key);
        BasicResponse<NewKeyResponse> response = api.executeApi();
        System.out.println(response.getData().getKey());
        System.out.println(response.getJson());
    }

    public void testModifyKeyApi() {
        String title = "sharing key";
        String apikey = "A1HzNFR344JgmZCZ3=O9FsQ9q=s=";
        List<Permissions> permissions = new ArrayList<Permissions>();
        List<Devices> resources = new ArrayList<Devices>();
        List<String> accessMethods = new ArrayList<String>();
        resources.add(new Devices("212141"));
        accessMethods.add("POST");
        accessMethods.add("GET");
        accessMethods.add("PUT");
        permissions.add(new Permissions(resources, accessMethods));
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        /**
         * API key更新
         * @param title:名称（可选）,String
         * @param apikey:需要修改的apikey,String
         * @param permissions：apikey权限,List<Permissions>permissions
         * @param key：masterkey(注：只能为master-key)
         */
        ModifyKeyApi api = new ModifyKeyApi(title, apikey, permissions, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    public void testFindKeyList() {
        String devId = "212141";
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        /**
         * API key查看
         * @param apikey：需要查找的apikey,String
         * @param page:指定页码，最大页数为10000（可选）,Integer
         * @param perpage:指定每页输出设备个数，默认30，最多100（可选）,Integer
         * @param devid：可选,只查看与该设备相关的非master-key,String
         * @param key：masterkey(注：只能为master-key)
         */
        FindKeyList api = new FindKeyList(null, null, null, null, devId, key);
        BasicResponse<KeyList> response = api.executeApi();
        System.out.println(response.getJson());
    }

    public void testRemoveKeyApi() {
        String keystr = "A1HzNFR344JgmZCZ3=O9FsQ9q=s=";
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        /**
         * API key删除
         * @param keystr：需要删除的apikey,String
         * @param key：masterkey(注：只能为master-key)
         */
        DeleteKeyApi api = new DeleteKeyApi(keystr, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    // 发送二进制数据命令
    public void testSendBytesCmdsApi() throws IOException {
        String devId = "9288";
        String key = "JKRfIzneAwXLdI6V0Yy83XOavb8=";
        byte[] buffer = new byte[2];
        buffer[0] = (byte) (0x61);
        buffer[1] = (byte) (0x62);
        /**
         * 发送命令
         * @param devId：接收该数据的设备ID（必选），String
         * @param qos:是否需要响应，默认为0,Integer
         * 0：不需要响应，即最多发送一次，不关心设备是否响应；
         * 1：需要响应，如果设备收到命令后没有响应，则会在下一次设备登陆时若命令在有效期内(有效期定义参见timeout参数）则会继续发送。
         * 对响应时间无限制，多次响应以最后一次为准。
         * 本参数仅当type=0时有效；
         * @param timeOut:命令有效时间，默认0,Integer
         * 0：在线命令，若设备在线,下发给设备，若设备离线，直接丢弃；
         *  >0： 离线命令，若设备在线，下发给设备，若设备离线，在当前时间加timeout时间内为有效期，有效期内，若设备上线，则下发给设备。单位：秒，有效围：0~2678400。
         *  本参数仅当type=0时有效；
         * @param type://默认0。0：发送CMD_REQ包，1：发送PUSH_DATA包
         * @param contents:用户自定义数据：json、string、二进制数据（小于64K）
         * @param key:masterkey或者设备apikey
         */
        SendCmdsApi api = new SendCmdsApi(devId, null, null, null, buffer, key);
        BasicResponse<NewCmdsResponse> response = api.executeApi();
        System.out.println(response.getJson());
    }

    // 发送String类型命令
    public void testSendStrCmdsApi() throws IOException {
        String devId = "9288";
        String key = "JKRfIzneAwXLdI6V0Yy83XOavb8=";
        String text = "xxxxxxxxxxxxxxxxx";
        /**
         * 发送命令
         * @param devId：接收该数据的设备ID（必选），String
         * @param qos:是否需要响应，默认为0,Integer
         * 0：不需要响应，即最多发送一次，不关心设备是否响应；
         * 1：需要响应，如果设备收到命令后没有响应，则会在下一次设备登陆时若命令在有效期内(有效期定义参见timeout参数）则会继续发送。
         * 对响应时间无限制，多次响应以最后一次为准。
         * 本参数仅当type=0时有效；
         * @param timeOut:命令有效时间，默认0,Integer
         * 0：在线命令，若设备在线,下发给设备，若设备离线，直接丢弃；
         *  >0： 离线命令，若设备在线，下发给设备，若设备离线，在当前时间加timeout时间内为有效期，有效期内，若设备上线，则下发给设备。单位：秒，有效围：0~2678400。
         *  本参数仅当type=0时有效；
         * @param type://默认0。0：发送CMD_REQ包，1：发送PUSH_DATA包
         * @param contents:用户自定义数据：json、string、二进制数据（小于64K）
         * @param key:masterkey或者设备apikey
         */
        SendCmdsApi api = new SendCmdsApi(devId, null, null, null, text, key);
        BasicResponse<NewCmdsResponse> response = api.executeApi();
        System.out.println(response.getJson());
    }

    // 发送json类型类型命令
    public void testSendJsonCmdsApi() throws IOException {
        String devId = "9288";
        String key = "JKRfIzneAwXLdI6V0Yy83XOavb8=";
        JSONObject json = new JSONObject();
        json.put("title", "xxxxxxxxxxx");
        /**
         * 发送命令
         * @param devId：接收该数据的设备ID（必选）,String
         * @param qos:是否需要响应，默认为0,Integer
         * 0：不需要响应，即最多发送一次，不关心设备是否响应；
         * 1：需要响应，如果设备收到命令后没有响应，则会在下一次设备登陆时若命令在有效期内(有效期定义参见timeout参数）则会继续发送。
         * 对响应时间无限制，多次响应以最后一次为准。
         * 本参数仅当type=0时有效；
         * @param timeOut:命令有效时间，默认0,Integer
         * 0：在线命令，若设备在线,下发给设备，若设备离线，直接丢弃；
         *  >0： 离线命令，若设备在线，下发给设备，若设备离线，在当前时间加timeout时间内为有效期，有效期内，若设备上线，则下发给设备。单位：秒，有效围：0~2678400。
         *  本参数仅当type=0时有效；
         * @param type://默认0。0：发送CMD_REQ包，1：发送PUSH_DATA包
         * @param contents:用户自定义数据：json、string、二进制数据（小于64K）
         * @param key:masterkey或者设备apikey
         */
        SendCmdsApi api = new SendCmdsApi(devId, null, null, null, json.toString(), key);
        BasicResponse<NewCmdsResponse> response = api.executeApi();
        System.out.println(response.getJson());
    }

    public void testQueryCmdsStatusApi() {
        String cmdUuid = "3a7b478e-f07d-56e6-b312-2362ac15f13f";
        String key = "JKRfIzneAwXLdI6V0Yy83XOavb8=";
        /**
         *查询命令状态
         * @param cmduuid:命令id,String
         * @param key:masterkey或者设备apikey
         */
        QueryCmdsStatus api = new QueryCmdsStatus(cmdUuid, key);
        BasicResponse<CmdsResponse> response = api.executeApi();
        System.out.println(response.getJson());
    }

    public void testQueryCmdsRespApi() {
        String cmdUuid = "3a7b478e-f07d-56e6-b312-2362ac15f13f";
        String key = "JKRfIzneAwXLdI6V0Yy83XOavb8=";
        /**
         * 查询命令响应
         * @param cmduuid:命令id,String
         * @param key:masterkey或者设备apikey
         */
        QueryCmdsRespApi api = new QueryCmdsRespApi(cmdUuid, key);
        String response = api.executeApi();
        System.out.println(response);
    }

    public void testCreateMqttTopicApi() {
        String name = "testtopic";
        String key = "JKRfIzneAwXLdI6V0Yy83XOavb8=";
        /**
         * 创建产品的Topic
         * @param name:设备订阅的主题（必选）,String
         * @param key：masterkey
         */
        CreateMqttTopicApi api = new CreateMqttTopicApi(name, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    public void testSendMqttsApi() {
        String topic = "testtopic";
        String key = "JKRfIzneAwXLdI6V0Yy83XOavb8=";
        JSONObject json = new JSONObject();
        json.put("title", "wangxiaojun is laosiji");
        /**
         *按Topic发送命令
         * @param topic：设备订阅的主题（必选）,String
         * @param contents:用户自定义数据：json、string、二进制数据（小于64K）
         * @param key：masterkey
         */
        SendMqttApi api = new SendMqttApi(topic, json, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    public void testFindDevicesListApi() {
        String topic = "testtopic";
        String key = "JKRfIzneAwXLdI6V0Yy83XOavb8=";
        int page = 1;
        int perPage = 1;
        /**
         * 查询订阅指定Topic设备的列表
         * @param page：当前页码. 必填,大于等于1,Integer
         * @param perPage：每页. 必填，范围1-1000,Integer
         * @param topic：设备订阅的主题（必选）,String
         * @param key：masterkey
         */
        FindTopicDevices api = new FindTopicDevices(page, perPage, topic, key);
        BasicResponse<TopicDeviceList> response = api.executeApi();
        System.out.println(response.getJson());
    }

    public void testGetTopicsApi() {
        String devId = "9288";
        String key = "JKRfIzneAwXLdI6V0Yy83XOavb8=";
        /**
         * 查询设备订阅的Topic列表
         * @param devId：设备ID（必选）,String
         * @param key：masterkey
         */
        GetDevicesTopicsApi api = new GetDevicesTopicsApi(devId, key);
        BasicResponse<List<String>> response = api.executeApi();
        System.out.println(response.getJson());
    }

    public void testGetUserTopicsApi() {
        String key = "JKRfIzneAwXLdI6V0Yy83XOavb8=";
        /**
         * 查询产品Topic
         * @param key：masterkey
         */
        GetUserTopics api = new GetUserTopics(key);
        BasicResponse<List<String>> response = api.executeApi();
        System.out.println(response.getJson());
    }

    public void testGetCmdsHistoryApi() {
        String key = "kRNzo7D0cXdf6kXXnFk=T2X2Nx4=";
        String start = "2018-12-28T08:00:00";
        String devId = "4944074";
        /**
         * 查询设备历史命令
         * @param devId：设备id
         * @param key：master-key
         * @param start：起始时间
         * @param start：结束时间
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
        BasicResponse<CmdsList> resp = new GetCmdsHistoryApi(devId, key, start, null, null, 10, null, OrderConstant.ORDER_ASC, 1).executeApi();
        System.out.println(resp.getJson());
    }

    public void testRemoveUserTopic() {
        String key = "JKRfIzneAwXLdI6V0Yy83XOavb8=";
        String name = "testtopic";
        /**
         * 删除产品的Topic
         * @param name：topic名（必选）
         * @param key：masterkey
         */
        DeleteUserTopic api = new DeleteUserTopic(name, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println(response.getJson());
    }


    public void testAddDtuParserApi() {
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        String name = "zjhtes";
        String filepath = "E:\\modbus.lua";
        /**
         * TCP透传新增
         * @param filename ： 名字,String
         * @param filepath:路径，String
         * @param key 必须为masterkey
         */
        AddDtuParser api = new AddDtuParser(name, filepath, key);
        BasicResponse<NewParserResponse> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
        System.out.println(response.getJson());
    }

    public void testModifyDtuParserApi() {
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        String name = "zjhtesb";
        String filepath = "E:\\modbus.lua";
        Integer id = 385;

        /**
         * TCP透传更新
         * @param id :parserId ,Integer
         * @param name:名字, String
         * @param filepath:路径，String
         * @param key:masterkey 或者 设备apikey
         */
        ModifyDtuParser api = new ModifyDtuParser(id, name, filepath, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }

    public void testFindDtuParserListApi() {
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        String name = "test";


        /**
         * TCP透传查询
         * @param name： 名字,精确匹配名字（可选）,String
         * @param key:masterkey 或者 该设备的设备apikey
         */
        FindDtuParserList api = new FindDtuParserList(name, key);
        BasicResponse<DtuParserList> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
        System.out.println(response.getJson());
    }

    public void testDeleteDtuParserApi() {
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        Integer id = 385;


        /**
         * TCP透传查询
         * @param name： 名字,精确匹配名字（可选）,String
         * @param key:masterkey 或者 该设备的设备apikey
         */
        DeleteDtuParser api = new DeleteDtuParser(id, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:" + response.errno + " error:" + response.error);
    }
}


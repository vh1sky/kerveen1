package com.mysiteforme.admin.onenet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Iwen
 * @date 2019/6/25 9:30
 * @Version 1.0
 */
public class OnenetConstant {
    /**
     * onenet平台返回数据（未处理） 时间：内容
     */
    public static final ConcurrentHashMap<String, String> CONTENT = new ConcurrentHashMap<>();

    /**
     * onenet平台返回数据（未处理）缓存待导出excel数据 时间：内容
     */
    public static final ConcurrentHashMap<String, String> EXCEL_CONTENT = new ConcurrentHashMap<>();

    /**
     * 设置数据解析线程最大为5
     */
    public static final ExecutorService EXECUTORSERVICE = Executors.newFixedThreadPool(5);

    /**
     * onenet平台返回管线机状态数据
     */
    public static final List<Map<String, String>> PPM_DATA_STATE = new ArrayList<>();

    /**
     * onenet平台返回管线机进水电磁阀状态数据
     */
    public static final List<Map<String, String>> PPM_DATA_PIPELINE_VALUE = new ArrayList<>();

    /**
     * onenet平台返回管线机循环泵数据
     */
    public static final List<Map<String, String>> PPM_DATA_PIPELINE_PUMP1 = new ArrayList<>();

    /**
     * onenet平台返回管线机抽水泵数据
     */
    public static final List<Map<String, String>> PPM_DATA_PIPELINE_PUMP2 = new ArrayList<>();

    /**
     * onenet平台返回管线机温度探头数据
     */
    public static final List<Map<String, String>> PPM_DATA_PIPELINE_TPROBE = new ArrayList<>();

    /**
     * onenet平台返回管线机UV杀菌灯数据
     */
    public static final List<Map<String, String>> PPM_DATA_PIPELINE_UV = new ArrayList<>();

    /**
     * onenet平台返回管线机水位开关数据
     */
    public static final List<Map<String, String>> PPM_DATA_PIPELINE_LEVEL = new ArrayList<>();

    /**
     * onenet平台返回管线机出水开关数据
     */
    public static final List<Map<String, String>> PPM_DATA_PIPELINE_OUT = new ArrayList<>();

    /**
     * onenet平台返回管线机环境温度数据
     */
    public static final List<Map<String, String>> PPM_DATA_PIPELINE_TEMPERATURE = new ArrayList<>();

    /**
     * onenet平台返回管线机环境湿度数据
     */
    public static final List<Map<String, String>> PPM_DATA_PIPELINE_HUMIDITY = new ArrayList<>();

    /**
     * onenet平台返回管线机告警数据
     */
    public static final List<Map<String, String>> PPM_DATA_PIPELINE_WARN = new ArrayList<>();

    /**
     * onenet平台返回水杯相关数据
     */
    public static final List<Map<String, String>> CUP_DATA_STATE = new ArrayList<>();

    /**
     * onenet平台返回主机相关数据
     */
    public static final List<Map<String, String>> HOST_DATA_STATE = new ArrayList<>();

    /**
     * onenet平台返回主机滤芯相关数据
     */
    public static final List<Map<String, String>> HOST_DATA_HOST_FILTER = new ArrayList<>();

    /**
     * onenet平台返回主机进水电磁阀数据
     */
    public static final List<Map<String, String>> HOST_DATA_HOST_VALUE = new ArrayList<>();

    /**
     * onenet平台返回主机原水tds数据
     */
    public static final List<Map<String, String>> HOST_DATA_HOST_TDSRAW = new ArrayList<>();

    /**
     * onenet平台返回主机纯水tds数据
     */
    public static final List<Map<String, String>> HOST_DATA_HOST_TDSPURE = new ArrayList<>();

    /**
     * onenet平台返回主机泵数据
     */
    public static final List<Map<String, String>> HOST_DATA_HOST_PUMP_STATE = new ArrayList<>();

    /**
     * onenet平台返回主机泵后压力探头数据
     */
    public static final List<Map<String, String>> HOST_DATA_HOST_PUMP_VOLT = new ArrayList<>();

    /**
     * onenet平台返回主机流量计数据
     */
    public static final List<Map<String, String>> HOST_DATA_HOST_FLOW = new ArrayList<>();

    /**
     * onenet平台返回主机废水电磁阀数据
     */
    public static final List<Map<String, String>> HOST_DATA_HOST_SCRAP_STATE = new ArrayList<>();

    /**
     * onenet平台返回主机原水压力数据
     */
    public static final List<Map<String, String>> HOST_DATA_HOST_RAW_VOLT = new ArrayList<>();

    /**
     * onenet平台返回主机UV杀菌灯数据
     */
    public static final List<Map<String, String>> HOST_DATA_HOST_UV = new ArrayList<>();

    /**
     * onenet平台返回主机纯水压力数据
     */
    public static final List<Map<String, String>> HOST_DATA_HOST_PURE_VOLT = new ArrayList<>();

    /**
     * onenet平台返回主机告警数据
     */
    public static final List<Map<String, String>> HOST_DATA_HOST_WARN = new ArrayList<>();

    /**
     * 数据解析，主机头
     */
    public static final String HOST = "FF";

    /**
     * 数据解析，管线机头
     */
    public static final String PIPELINE_MACHINE = "AA";

    /**
     * 数据解析，水杯头
     */
    public static final String CUP = "CC";

    /**
     * JSON解析错误
     */
    public static final int JSON_RESOLVE = 2;

    /**
     * 类型不匹配
     */
    public static final int TRANSTYPE_NO = 3;

    /**
     * Head - messageID未赋值
     */
    public static final int HEAD_messageID = 4;
}

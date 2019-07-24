package com.mysiteforme.admin.onenet;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.data.entity.*;
import com.mysiteforme.admin.data.service.PipHumidityService;
import com.mysiteforme.admin.data.service.PipWarnService;
import com.mysiteforme.admin.monitor.entity.PipelineMachine;
import com.mysiteforme.admin.monitor.service.CupStudentService;
import com.mysiteforme.admin.monitor.service.PipelineMachineService;
import com.mysiteforme.admin.util.CRC8;
import com.mysiteforme.admin.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Iwen
 * @date 2019/6/26 17:02
 * @Version 1.0
 */
public class HexAnalyse extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(HexAnalyse.class);

    @Autowired
    protected PipelineMachineService pipelineMachineService;

    @Autowired
    protected CupStudentService cupStudentService;

    @Autowired
    protected PipHumidityService pipHumidityService;

    @Autowired
    protected PipWarnService pipWarnService;

    public static void analyse(String onenetId, Date date, String hexString) {
        hexString = hexString.toUpperCase();
        if (CRC8.calcCrc8(hexString.substring(4))) {
            String data = hexString.substring(0, hexString.length() - 2);
            List<String> dataList = new ArrayList<>();
            int length = data.length();
            if (length % 2 == 1) {
                for (int i = 0; i < data.length() / 2; i++) {
                    dataList.add(data.substring(i * 2, (i * 2) + 2));
                }
                dataList.add(data.substring(length - 1));
            } else {
                for (int i = 0; i < data.length() / 2; i++) {
                    dataList.add(data.substring(i * 2, (i * 2) + 2));
                }
            }
        }
    }

    public void judge(String onenetId, Date date, List dataList) {
        if (OnenetConstant.HOST.equals(dataList.get(0)) && OnenetConstant.HOST.equals(dataList.get(1))) {
            //主机数据解析

        } else if (OnenetConstant.PIPELINE_MACHINE.equals(dataList.get(0)) && OnenetConstant.PIPELINE_MACHINE.equals(dataList.get(1))) {
            //管线机数据解析
            pipelineMachine(onenetId, date, dataList);
        } else if (OnenetConstant.CUP.equals(dataList.get(0)) && OnenetConstant.CUP.equals(dataList.get(1))) {
            //水杯数据解析
            cup(onenetId, date, dataList);
        } else {
            logger.info("此条数据有误！");
        }
    }

    /**
     * 管线机状态数据解析
     */
    public void pipelineMachine(String oneNetId, Date date, List<String> dataList) {
        int length = Integer.parseInt(dataList.get(3), 16);
        Map<String, String> status = Maps.newHashMap();
        status.put("onenetId", oneNetId);
        status.put("date", DateUtils.dateToString(date));

        /**
         *管线机状态数据
         */
        if ("01".equals(dataList.get(2))) {
            status.put("pipelinePattern", dataList.get(4));
            String hexId = dataList.get(5) + dataList.get(6) + dataList.get(7) + dataList.get(8) + dataList.get(9) + dataList.get(10);
            status.put("pipelineId", Long.toString(Long.parseLong(hexId, 16)));
            status.put("pipeline_net", dataList.get(11));
            status.put("pipelineVoltage", Long.toString(Long.parseLong(dataList.get(12), 16)));
            OnenetConstant.PPM_DATA_STATE.add(status);

            /**将onenetId存数据库*/
            EntityWrapper<PipelineMachine> wrapper = new EntityWrapper<>();
            wrapper.eq("onenetId", oneNetId);
            if (pipelineMachineService.selectCount(wrapper) <= 0) {
                Map param = Maps.newHashMap();
                param.put("mainboardId", Long.toString(Long.parseLong(hexId, 16)));
                List<PipelineMachine> pipelineMachines = pipelineMachineService.selectByMap(param);
                if (pipelineMachines != null && pipelineMachines.size() > 0){
                    for (PipelineMachine pipelineMachine : pipelineMachines){
                        pipelineMachine.setOnenetId(Long.parseLong(oneNetId));
                        pipelineMachineService.saveOrUpdatePipelineMachine(pipelineMachine);
                    }
                }else {
                    logger.info("找不到对应的管线机！");
                }
            }

        }

        /**
         *管线机进水电磁阀状态数据
         */
        if ("02".equals(dataList.get(2))) {
            status.put("pipelineValvePattern", dataList.get(4));
            status.put("pipelineValveAmpere", Integer.toString(Integer.parseInt(dataList.get(5), 16)));
            OnenetConstant.PPM_DATA_PIPELINE_VALUE.add(status);

            //遍历数据
            for (Map<String,String> temMap :OnenetConstant.PPM_DATA_PIPELINE_VALUE) {
                PipValve pipValve = new PipValve();
                pipValve.setOnenetid(Integer.valueOf(temMap.get("onenetId")));
                pipValve.setPipelineValveAmpere(Integer.valueOf(temMap.get("pipelineValveAmpere")));
                pipValve.setPipelineValvePattern(temMap.get("pipelineValvePattern"));
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pipValve.setRecordTime(format.toString());
                pipValveService.insert(pipValve);
            }
        }

        /**
         *管线机循环泵状态数据
         */
        if ("03".equals(dataList.get(2))) {
            status.put("pipelinePump1Pattern", dataList.get(4));

            status.put("pipelinePump1Ampere", Integer.toString(Integer.parseInt(dataList.get(5), 16)));
            String tmp = dataList.get(6) + dataList.get(7) + dataList.get(8);
            status.put("pipelinePump1Time", Long.toString(Long.parseLong(tmp, 16)));
            OnenetConstant.PPM_DATA_PIPELINE_PUMP1.add(status);

            //遍历数据
            for (Map<String,String> temMap : OnenetConstant.PPM_DATA_PIPELINE_PUMP1) {
                PipPump1 pipPump1 = new PipPump1();
                pipPump1.setOnenetid(Integer.valueOf(temMap.get("onenetId")));
                pipPump1.setPipelinePump1Pattern(temMap.get("pipelinePump1Pattern"));
                pipPump1.setPipelinePump1Ampere(Integer.valueOf(temMap.get("pipelinePump1Ampere")));
                pipPump1.setPipelinePump1Time(temMap.get("pipelinePump1Time"));
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pipPump1.setRecordTime(format.toString());
                pipPump1Service.insert(pipPump1);
            }
        }

        /**
         *管线机抽水泵数据
         */
        if ("04".equals(dataList.get(2))) {
            status.put("pipelinePump2Pattern", dataList.get(4));
            status.put("pipelinePump2Time", Integer.toString(Integer.parseInt(dataList.get(5), 16)));
            status.put("pipelinePump2Ampere", Integer.toString(Integer.parseInt(dataList.get(6), 16)));

            OnenetConstant.PPM_DATA_PIPELINE_PUMP2.add(status);

            //遍历数据
            for (Map<String,String> temMap : OnenetConstant.PPM_DATA_PIPELINE_PUMP2) {
                PipPump2 pipPump2 = new PipPump2();
                pipPump2.setOnenetid(Integer.valueOf(temMap.get("onenetId")));
                pipPump2.setPipelinePump2Pattern(temMap.get("pipelinePump2Pattern"));
                pipPump2.setPipelinePump2Time(temMap.get("pipelinePump2Time"));
                pipPump2.setPipelinePump2Ampere(Integer.valueOf(temMap.get("pipelinePump2Ampere")));
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pipPump2.setRecordTime(format.toString());
                pipPump2Service.insert(pipPump2);
            }
        }

        /**
         *管线机温控探头数据
         */
        if ("05".equals(dataList.get(2))) {
            status.put("pipelineTprobePattern", dataList.get(4));
            status.put("pipelineTprobeHot", Integer.toString(Integer.parseInt(dataList.get(5), 16)));

            OnenetConstant.PPM_DATA_PIPELINE_TPROBE.add(status);

            //遍历数据
            for (Map<String,String> temMap : OnenetConstant.PPM_DATA_PIPELINE_TPROBE) {
                PipTprobe pipTprobe = new PipTprobe();
                pipTprobe.setOnenetid(Integer.valueOf(temMap.get("onenetId")));
                pipTprobe.setPipelineTprobePattern(temMap.get("pipelineTprobePattern"));
                pipTprobe.setPipelineTprobeHot(Integer.valueOf(temMap.get("pipelineTprobeHot")));
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pipTprobe.setRecordTime(format.toString());
                pipTprobeService.insert(pipTprobe);
            }
        }

        /**
         *管线机UV杀菌灯数据
         */
        if ("06".equals(dataList.get(2))) {
            status.put("pipelineUVPattern", dataList.get(4));
            status.put("pipelineUVAmpere", Integer.toString(Integer.parseInt(dataList.get(5), 16)));

            OnenetConstant.PPM_DATA_PIPELINE_UV.add(status);

            //数据遍历
            for (Map<String,String> temMap : OnenetConstant.PPM_DATA_PIPELINE_UV) {
                PipeUv pipeUv = new PipeUv();
                pipeUv.setOnenetid(Integer.valueOf(temMap.get("onenetId")));
                pipeUv.setPipelineUvAmpere(Integer.valueOf(temMap.get("pipelineUVAmpere")));
                pipeUv.setPipelineUvPattern(temMap.get("pipelineUVPattern"));
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pipeUv.setRecordTime(format.toString());
                pipeUvService.insert(pipeUv);
            }
        }

        /**
         *管线机水位开关数据
         */
        if ("07".equals(dataList.get(2))) {
            status.put("pipelineLevelPattern", dataList.get(4));

            OnenetConstant.PPM_DATA_PIPELINE_LEVEL.add(status);

            //遍历数据
            for (Map<String,String> temMap : OnenetConstant.PPM_DATA_PIPELINE_LEVEL) {
                PipLevel pipLevel = new PipLevel();
                pipLevel.setOnenetid(Integer.valueOf(temMap.get("onenetId")));
                pipLevel.setPipelineLevelPattern(temMap.get("pipelineLevelPattern"));
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pipLevel.setRecordTime(format.toString());
                pipLevelService.insert(pipLevel);
            }
        }

        /**
         *管线机出水开关数据
         */
        if ("08".equals(dataList.get(2))) {
            status.put("pipelineOutPattern", dataList.get(4));

            OnenetConstant.PPM_DATA_PIPELINE_OUT.add(status);

            //遍历数据
            for (Map<String,String> temMap : OnenetConstant.PPM_DATA_PIPELINE_OUT) {
                PipOut pipOut = new PipOut();
                pipOut.setOnenetid(Integer.valueOf(temMap.get("onenetId")));
                pipOut.setPipelineOutPattern(temMap.get("pipelineOutPattern"));
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pipOut.setRecordTime(format.toString());
                pipOutService.insert(pipOut);
            }
        }

        /**
         *管线机环境温度数据
         */
        if ("09".equals(dataList.get(2))) {
            status.put("pipelineTemperature", dataList.get(4));

            OnenetConstant.PPM_DATA_PIPELINE_TEMPERATURE.add(status);

            //遍历数据
            for (Map<String,String> tepMap : OnenetConstant.PPM_DATA_PIPELINE_TEMPERATURE) {
                PipTemperature pipTemperature = new PipTemperature();
                pipTemperature.setOnenetid(Integer.valueOf(tepMap.get("onenetId")));
                pipTemperature.setPipelineTemperature(Integer.valueOf(tepMap.get("pipelineTemperature")));
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pipTemperature.setRecordTime(format.toString());
                pipTempeService.insert(pipTemperature);
            }
        }

        /**
         *管线机环境湿度数据
         */
        if ("0A".equals(dataList.get(2))) {
            status.put("pipelineHumidity", dataList.get(4));
            OnenetConstant.PPM_DATA_PIPELINE_HUMIDITY.add(status);

            //遍历数据
            for (Map<String,String> tmpMap : OnenetConstant.PPM_DATA_PIPELINE_HUMIDITY) {
                PipHumidity pipHumidity = new PipHumidity();
                //获取onenetid存入 humidity对象中
                pipHumidity.setOnenetid(Integer.valueOf(tmpMap.get("onenetId")));
                //获取湿度 存入该对象
                pipHumidity.setPipelineHumidity(Integer.valueOf(tmpMap.get("pipelineHumidity")));
                //获取当前时间
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pipHumidity.setRecordTime(format.toString());
                pipHumidityService.insert(pipHumidity);
            }
        }

        /**
         *管线机告警数据
         */
        if ("0A".equals(dataList.get(2))) {
            status.put("pipelineWarnWater", dataList.get(4));
            status.put("pipelineWarnHot", dataList.get(4));
            status.put("pipelineWarnProbe", dataList.get(4));
            OnenetConstant.PPM_DATA_PIPELINE_WARN.add(status);

            //管线机告警数据入库
            for (Map<String,String> temMap : OnenetConstant.PPM_DATA_PIPELINE_WARN) {
                PipWarn pipWarn = new PipWarn();
                //数据设置
                pipWarn.setOnenetid(Integer.valueOf(temMap.get("onenetId")));
                pipWarn.setPipelineWarnHot(temMap.get("pipelineWarnHot"));
                pipWarn.setPipelineWarnWater(temMap.get("pipelineWarnWater"));
                pipWarn.setPipelineWarnProbe(temMap.get("pipelineWarnProbe"));
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pipWarn.setRecordTime(format.toString());
                pipWarnService.insert(pipWarn);
            }
        }
    }

    /**
     * 水杯数据解析
     */
    public void cup(String oneNetId, Date date, List<String> dataList) {
        int length = Integer.parseInt(dataList.get(3), 16);
        Map<String, String> status = Maps.newHashMap();
        status.put("onenetId", oneNetId);
        status.put("date", DateUtils.dateToString(date));

        /**
         *水杯状态数据
         */
        if ("10".equals(dataList.get(2))) {
            String hexId = dataList.get(4) + dataList.get(5) + dataList.get(6) + dataList.get(7) + dataList.get(8) + dataList.get(9);
            status.put("cupId", hexId);
            String hexDate = dataList.get(10) + dataList.get(11) + dataList.get(12) + dataList.get(13) + dataList.get(14) + dataList.get(15);
            status.put("cupTime", Long.toString(Long.parseLong(hexDate, 16)));
            status.put("cupWater", Long.toString(Long.parseLong(dataList.get(16) + dataList.get(17), 16)));
            status.put("cupHight", Long.toString(Long.parseLong(dataList.get(18) + dataList.get(19), 16)));
            status.put("cupWeight", Long.toString(Long.parseLong(dataList.get(20) + dataList.get(21), 16)));
            OnenetConstant.CUP_DATA_STATE.add(status);

            /**将数据存数据库*/
            /*EntityWrapper<CupStudent> wrapper = new EntityWrapper<>();
            wrapper.eq("onenetId", oneNetId);
                Map param = Maps.newHashMap();
                param.put("cupId", Long.toString(Long.parseLong(hexId, 16)));
                List<PipelineMachine> pipelineMachines = pipelineMachineService.selectByMap(param);
                if (pipelineMachines != null && pipelineMachines.size() > 0){
                    for (PipelineMachine pipelineMachine : pipelineMachines){
                        pipelineMachine.setOnenetId(Long.parseLong(oneNetId));
                        pipelineMachineService.saveOrUpdatePipelineMachine(pipelineMachine);
                }
            }*/
        }
    }

    /**
     * 主机数据解析
     */
    public void host(String oneNetId, Date date, List<String> dataList) {
        int length = Integer.parseInt(dataList.get(3), 16);
        Map<String, String> status = Maps.newHashMap();
        status.put("onenetId", oneNetId);
        status.put("date", DateUtils.dateToString(date));

        /**
         *主机状态数据
         */
        if ("01".equals(dataList.get(2))) {
            String hexId = dataList.get(4) + dataList.get(5) + dataList.get(6) + dataList.get(7) + dataList.get(8) + dataList.get(9);
            status.put("hostId", hexId);
            status.put("hostPumpVolt", Long.toString(Long.parseLong(dataList.get(10) + dataList.get(11), 16)));
            status.put("hostRinse", dataList.get(12));
            status.put("hostMake", dataList.get(13));
            status.put("hostFull", dataList.get(14));
            status.put("hostQuantity", Integer.toString(Integer.parseInt(dataList.get(15) + dataList.get(16) + dataList.get(17), 16)));
            status.put("hostNet", dataList.get(18));
            OnenetConstant.HOST_DATA_STATE.add(status);
        }

        /**
         *主机滤芯寿命数据
         */
        if ("02".equals(dataList.get(2))) {
            status.put("hostFilterLevel", dataList.get(4));
            status.put("hostFilterDay", Integer.toString(Integer.parseInt(dataList.get(5) + dataList.get(6), 16)));
            OnenetConstant.HOST_DATA_HOST_FILTER.add(status);
        }

        /**
         *主机进水电磁阀数据
         */
        if ("03".equals(dataList.get(2))) {
            status.put("hostValvePattern", dataList.get(4));
            OnenetConstant.HOST_DATA_HOST_VALUE.add(status);
        }

        /**
         *主机原水TDS探头数据
         */
        if ("04".equals(dataList.get(2))) {
            status.put("hostTds1", dataList.get(4));
            status.put("hostTemperature", Integer.toString(Integer.parseInt(dataList.get(5),16)));
            OnenetConstant.HOST_DATA_HOST_TDSRAW.add(status);
        }

        /**
         *主机原水TDS探头数据
         */
        if ("05".equals(dataList.get(2))) {
            status.put("hostTds2", dataList.get(4));
            OnenetConstant.HOST_DATA_HOST_TDSPURE.add(status);
        }

        /**
         *主机泵数据
         */
        if ("06".equals(dataList.get(2))) {
            status.put("hostPump", dataList.get(4));
            OnenetConstant.HOST_DATA_HOST_PUMP_STATE.add(status);
        }

        /**
         *主机泵后压力探头数据
         */
        if ("07".equals(dataList.get(2))) {
            status.put("hostPumpVolt", Long.toString(Long.parseLong(dataList.get(4), 16)));
            OnenetConstant.HOST_DATA_HOST_PUMP_VOLT.add(status);
        }

        /**
         *主机流量计数据
         */
        if ("08".equals(dataList.get(2))) {
            status.put("hostFlowBlink", Long.toString(Long.parseLong(dataList.get(4), 16)));
            status.put("hostFlowQuantity", Long.toString(Long.parseLong(dataList.get(5)+dataList.get(6)+dataList.get(7), 16)));
            OnenetConstant.HOST_DATA_HOST_FLOW.add(status);
        }

        /**
         *主机废水电磁阀数据
         */
        if ("09".equals(dataList.get(2))) {
            status.put("hostScrapValve",dataList.get(4));
            OnenetConstant.HOST_DATA_HOST_SCRAP_STATE.add(status);
        }

        /**
         *主机原水压力数据
         */
        if ("0A".equals(dataList.get(2))) {
            status.put("hostRawValue",Long.toString(Long.parseLong(dataList.get(4)+dataList.get(5),16)));
            status.put("hostRawState",dataList.get(6));
            OnenetConstant.HOST_DATA_HOST_RAW_VOLT.add(status);
        }

        /**
         *主机UV杀菌灯状态数据
         */
        if ("0B".equals(dataList.get(2))) {
            status.put("hostUvState",dataList.get(4));
            OnenetConstant.HOST_DATA_HOST_UV.add(status);
        }

        /**
         *主机纯水压力值数据
         */
        if ("0C".equals(dataList.get(2))) {
            status.put("hostPureVolt",Integer.toString(Integer.parseInt(dataList.get(4)+dataList.get(5),16)));
            OnenetConstant.HOST_DATA_HOST_PURE_VOLT.add(status);
        }

        /**
         *主机告警数据
         */
        if ("0D".equals(dataList.get(2))) {
            status.put("hostTmpWarn",dataList.get(4));
            status.put("hostUvWarn",dataList.get(5));
            OnenetConstant.HOST_DATA_HOST_WARN.add(status);
        }
    }

}

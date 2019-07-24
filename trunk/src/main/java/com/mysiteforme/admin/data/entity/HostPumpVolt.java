package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 泵后 压力
 * @create 2019/7/8
 */
@TableName("host_pump_volt")
public class HostPumpVolt extends BaseEntity<HostPumpVolt> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 泵后压力值 单位 ：千帕
     */
    @TableField("host_pump_volt")
    private int hostPumpVolt;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public int getHostPumpVolt() { return hostPumpVolt; }

    public void setHostPumpVolt(int hostPumpVolt) { this.hostPumpVolt = hostPumpVolt; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }
}

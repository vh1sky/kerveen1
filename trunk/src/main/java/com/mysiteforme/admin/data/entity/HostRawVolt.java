package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/8
 */
@TableName("host_raw_volt")
public class HostRawVolt extends BaseEntity<HostRawVolt> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 原水压力值 单位：千帕
     */
    @TableField("host_raw_value")
    private int hostRawValue;
    /**
     * 缺水状态  00正常 01缺水
     */
    @TableField("host_raw_state")
    private String hostRawState;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public int getHostRawValue() { return hostRawValue; }

    public void setHostRawValue(int hostRawValue) { this.hostRawValue = hostRawValue; }

    public String getHostRawState() { return hostRawState; }

    public void setHostRawState(String hostRawState) { this.hostRawState = hostRawState; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }
}

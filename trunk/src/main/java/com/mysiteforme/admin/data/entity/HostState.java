package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/8
 */
@TableName("host_state")
public class HostState extends BaseEntity<HostState> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 主板id
     */
    private int hostid;
    /**
     * 泵工作压力值 单位：千帕
     */
    @TableField("host_pump_volt")
    private int hostPumpVolt;
    /**
     * 冲洗状态 00冲洗中 01冲洗结束
     */
    @TableField("host_rinse")
    private String hostRinse;
    /**
     * 制水状态00制水中01结束制水
     */
    @TableField("host_make")
    private String hostMake;
    /**
     * 水满状态00水满01水不满
     */
    @TableField("host_full")
    private String hostFull;
    /**
     * 制水量单位：ml
     */
    @TableField("host_quantity")
    private int hostQuantity;
    /**
     * 网络状态0x00表示网络正常0x01表示网络异常
     */
    @TableField("host_net")
    private String hostNet;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public int getHostid() { return hostid; }

    public void setHostid(int hostid) { this.hostid = hostid; }

    public int getHostPumpVolt() { return hostPumpVolt; }

    public void setHostPumpVolt(int hostPumpVolt) { this.hostPumpVolt = hostPumpVolt; }

    public int getHostQuantity() { return hostQuantity; }

    public void setHostQuantity(int hostQuantity) { this.hostQuantity = hostQuantity; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }

    public String getHostRinse() { return hostRinse; }

    public void setHostRinse(String hostRinse) { this.hostRinse = hostRinse; }

    public String getHostMake() { return hostMake; }

    public void setHostMake(String hostMake) { this.hostMake = hostMake; }

    public String getHostFull() { return hostFull; }

    public void setHostFull(String hostFull) { this.hostFull = hostFull; }

    public String getHostNet() { return hostNet; }

    public void setHostNet(String hostNet) { this.hostNet = hostNet; }
}

package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 纯水压力值
 * @create 2019/7/8
 */
@TableName("host_pure_volt")
public class HostPureVolt extends BaseEntity<HostPureVolt> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 纯水压力值
     */
    @TableField("host_pure_volt")
    private int hostPureVolt;
    /**
     * 记录时间
     */
    @TableField("record_time")

    private String recordTime;
    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public int getHostPureVolt() { return hostPureVolt; }

    public void setHostPureVolt(int hostPureVolt) { this.hostPureVolt = hostPureVolt; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }
}

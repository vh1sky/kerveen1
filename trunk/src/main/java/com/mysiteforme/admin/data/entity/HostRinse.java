package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/8
 */
@TableName("host_rinse_state")
public class HostRinse extends BaseEntity<HostRinse> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 复位状态响应 00收到指令 01开始复位 02复位成功 03复位失败
     */
    @TableField("host_reset")
    private String hostReset ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getHostReset() { return hostReset; }

    public void setHostReset(String hostReset) { this.hostReset = hostReset; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }
}

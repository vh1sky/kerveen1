package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 废水电磁阀
 * @create 2019/7/8
 */
@TableName("host_scrap_state")
public class HostScrap extends BaseEntity<HostScrap> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     *废水电磁阀状态 00关闭 01开启
     */
    @TableField("host_scrap_valve")
    private String hostScrapValve;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;
    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getHostScrapValve() { return hostScrapValve; }

    public void setHostScrapValve(String hostScrapValve) { this.hostScrapValve = hostScrapValve; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }
}

package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/8
 */
@TableName("host_flow")
public class HostFlow extends BaseEntity<HostFlow> {

    private static final long serialVersionUID = 1L;
    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 瞬时流量 单位 ml/s
     */
    @TableField("host_flow_blink")
    private String hostFlowBlink;
    /**
     * 累计流量 单位 ml
     */
    @TableField("host_flow_quantity")
    private int hostFlowQuantity;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getHostFlowBlink() { return hostFlowBlink; }

    public void setHostFlowBlink(String  hostFlowBlink) { this.hostFlowBlink = hostFlowBlink; }

    public int getHostFlowQuantity() { return hostFlowQuantity; }

    public void setHostFlowQuantity(int hostFlowQuantity) { this.hostFlowQuantity = hostFlowQuantity; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }
}

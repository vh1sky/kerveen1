package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 设备报警
 * @create 2019/6/27
 */
@TableName("pipeline_warn")
public class PipWarn extends BaseEntity<PipWarn> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 进水时间 00正常  01 异常
     */
    @TableField("pipeline_warn_water")
    private String pipelineWarnWater ;
    /**
     * 加热 00正常 01异常
     */
    @TableField("pipeline_warn_hot")
    private String pipelineWarnHot ;
    /**
     * 温控探头 00工作 01断开 02短路
     */
    @TableField("pipeline_warn_probe")
    private String pipelineWarnProbe ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelineWarnWater() { return pipelineWarnWater; }

    public void setPipelineWarnWater(String pipelineWarnWater) { this.pipelineWarnWater = pipelineWarnWater; }

    public String getPipelineWarnHot() { return pipelineWarnHot; }

    public void setPipelineWarnHot(String pipelineWarnHot) { this.pipelineWarnHot = pipelineWarnHot; }

    public String getPipelineWarnProbe() { return pipelineWarnProbe; }

    public void setPipelineWarnProbe(String pipelineWarnProbe) { this.pipelineWarnProbe = pipelineWarnProbe; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }
}

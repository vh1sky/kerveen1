package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 管线机状态
 * @create 2019/6/27
 */
public class PipState extends BaseEntity<PipState> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 00按键出水 01放杯子按键出水 02放杯子出水
     */
    @TableField("pipeline_pattern")
    private String pipelinePattern ;
    /**
     * 管线机主板id
     */
    @TableField("pipeline_id")
    private String pipelineId ;
    /**
     * 01网络好  00网络差
     */
    @TableField("pipeline_net")
    private String pipelineNet ;
    /**
     * 管线机电压值
     */
    @TableField("pipeline_voltage")
    private String pipelineVoltage;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelineId() { return pipelineId; }

    public void setPipelineId(String pipelineId) { this.pipelineId = pipelineId; }

    public String getPipelinePattern() { return pipelinePattern; }

    public void setPipelinePattern(String pipelinePattern) { this.pipelinePattern = pipelinePattern; }

    public String getPipelineNet() { return pipelineNet; }

    public void setPipelineNet(String pipelineNet) { this.pipelineNet = pipelineNet; }

    public String getPipelineVoltage() { return pipelineVoltage; }

    public void setPipelineVoltage(String pipelineVoltage) { this.pipelineVoltage = pipelineVoltage; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }

}

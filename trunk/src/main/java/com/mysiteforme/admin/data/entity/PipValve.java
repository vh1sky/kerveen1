package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 管线机电磁阀状态
 * @create 2019/6/27
 */
@TableName("pipeline_valve")
public class PipValve extends BaseEntity<PipValve> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 进水电磁阀  00开 01关
     */
    @TableField("pipeline_valve_pattern")
    private String pipelineValvePattern;
    /**
     * 进水电磁阀工作电流
     */
    @TableField("pipeline_valve_ampere")
    private int pipelineValveAmpere;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelineValvePattern() { return pipelineValvePattern; }

    public void setPipelineValvePattern(String pipelineValvePattern) { this.pipelineValvePattern = pipelineValvePattern; }

    public int getPipelineValveAmpere() { return pipelineValveAmpere; }

    public void setPipelineValveAmpere(int pipelineValveAmpere) { this.pipelineValveAmpere = pipelineValveAmpere; }

}

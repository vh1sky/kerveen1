package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION UV杀菌灯
 * @create 2019/6/27
 */
@TableName("pipeline_uv_zero")
public class PipUvZero extends BaseEntity<PipUvZero> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 进水电磁阀  00开 01关
     */
    @TableField("pipeline_valve_pattern")
    private String pipelineValvePattern ;
    /**
     * 进水电磁阀工作电流
     */
    @TableField("pipeline_valve_ampere")
    private int pipelineValveAmpere ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelineValvePattern() { return pipelineValvePattern; }

    public void setPipelineValvePattern(String pipelineValvePattern) { this.pipelineValvePattern = pipelineValvePattern; }

    public int getPipelineValveAmpere() { return pipelineValveAmpere; }

    public void setPipelineValveAmpere(int pipelineValveAmpere) { this.pipelineValveAmpere = pipelineValveAmpere; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }

}

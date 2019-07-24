package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 循环泵
 * @create 2019/6/27
 */
@TableName("pipeline_pump1")
public class PipPump1 extends BaseEntity<PipPump1> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 循环泵 01工作中  00结束
     */
    @TableField("pipeline_pump1_pattern")
    private String pipelinePump1Pattern ;
    /**
     * 循环泵工作电流
     */
    @TableField("pipeline_pump1_ampere")
    private int pipelinePump1Ampere ;
    /**
     * 循环泵工作时间
     */
    @TableField("pipeline_pump1_time")
    private String pipelinePump1Time ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelinePump1Pattern() { return pipelinePump1Pattern; }

    public void setPipelinePump1Pattern(String pipelinePump1Pattern) { this.pipelinePump1Pattern = pipelinePump1Pattern; }

    public int getPipelinePump1Ampere() { return pipelinePump1Ampere; }

    public void setPipelinePump1Ampere(int pipelinePump1Ampere) { this.pipelinePump1Ampere = pipelinePump1Ampere; }

    public String getPipelinePump1Time() { return pipelinePump1Time; }

    public void setPipelinePump1Time(String pipelinePump1Time) { this.pipelinePump1Time = pipelinePump1Time; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }
}

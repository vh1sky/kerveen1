package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 环境温度
 * @create 2019/6/27
 */
@TableName("pipeline_temperature")
public class PipTemperature extends BaseEntity<PipTemperature> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 环境温度值
     */
    @TableField("pipeline_temperature")
    private int pipelineTemperature ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public int getPipelineTemperature() { return pipelineTemperature; }

    public void setPipelineTemperature(int pipelineTemperature) { this.pipelineTemperature = pipelineTemperature; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }

}

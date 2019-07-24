package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION UV杀菌灯
 * @create 2019/6/27
 */
@TableName("pipeline_uv")
public class PipeUv extends BaseEntity<PipeUv> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * UV杀菌灯 01 开启  00 关闭
     */
    @TableField("pipeline_uv_pattern")
    private String pipelineUvPattern ;
    /**
     * UV杀菌灯工作电流
     */
    @TableField("pipeline_uv_ampere")
    private int pipelineUvAmpere ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelineUvPattern() { return pipelineUvPattern; }

    public void setPipelineUvPattern(String pipelineUvPattern) { this.pipelineUvPattern = pipelineUvPattern; }

    public int getPipelineUvAmpere() { return pipelineUvAmpere; }

    public void setPipelineUvAmpere(int pipelineUvAmpere) { this.pipelineUvAmpere = pipelineUvAmpere; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }

}

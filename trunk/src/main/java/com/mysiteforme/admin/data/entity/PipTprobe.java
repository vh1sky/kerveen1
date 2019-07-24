package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 温控探头
 * @create 2019/6/27
 */
@TableName("pipeline_tprobe")
public class PipTprobe extends BaseEntity<PipTprobe> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 温控探头 00 工作     01  异常
     */
    @TableField("pipeline_tprobe_pattern")
    private String pipelineTprobePattern ;
    /**
     * 温度探头 温度值
     */
    @TableField("pipeline_tprobe_hot")
    private int pipelineTprobeHot ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelineTprobePattern() { return pipelineTprobePattern; }

    public void setPipelineTprobePattern(String pipelineTprobePattern) { this.pipelineTprobePattern = pipelineTprobePattern; }

    public int getPipelineTprobeHot() { return pipelineTprobeHot; }

    public void setPipelineTprobeHot(int pipelineTprobeHot) { this.pipelineTprobeHot = pipelineTprobeHot; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }
}

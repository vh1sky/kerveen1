package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 出水泵
 * @create 2019/6/27
 */
@TableName("pipeline_pump2")
public class PipPump2 extends BaseEntity<PipPump2> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 抽水泵 00关 01开
     */
    @TableField("pipeline_pump2_pattern")
    private String pipelinePump2Pattern ;
    /**
     * 抽水泵出水时长
     */
    @TableField("pipeline_pump2_time")
    private String pipelinePump2Time ;
    /**
     * 抽水泵工作电流
     */
    @TableField("pipeline_pump2_ampere")
    private int pipelinePump2Ampere ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelinePump2Pattern() { return pipelinePump2Pattern; }

    public void setPipelinePump2Pattern(String pipelinePump2Pattern) { this.pipelinePump2Pattern = pipelinePump2Pattern; }

    public String getPipelinePump2Time() { return pipelinePump2Time; }

    public void setPipelinePump2Time(String pipelinePump2Time) { this.pipelinePump2Time = pipelinePump2Time; }

    public int getPipelinePump2Ampere() { return pipelinePump2Ampere; }

    public void setPipelinePump2Ampere(int pipelinePump2Ampere) { this.pipelinePump2Ampere = pipelinePump2Ampere; }
}

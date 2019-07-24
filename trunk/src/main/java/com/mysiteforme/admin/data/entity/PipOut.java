package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 出水开关
 * @create 2019/6/27
 */
@TableName("pipeline_out")
public class PipOut extends BaseEntity<PipOut> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 出水开关 01 开启  00 关闭
     */
    @TableField("pipeline_out_pattern")
    private String pipelineOutPattern ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelineOutPattern() { return pipelineOutPattern; }

    public void setPipelineOutPattern(String pipelineOutPattern) { this.pipelineOutPattern = pipelineOutPattern; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }

}

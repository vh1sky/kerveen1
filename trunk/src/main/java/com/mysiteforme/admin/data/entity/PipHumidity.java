package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 环境湿度
 * @create 2019/6/27
 */
@TableName("pipeline_humidity")
public class PipHumidity extends BaseEntity<PipHumidity> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 环境湿度值
     */
    @TableField("pipeline_humidity")
    private int pipelineHumidity ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public int getPipelineHumidity() { return pipelineHumidity; }

    public void setPipelineHumidity(int pipelineHumidity) { this.pipelineHumidity = pipelineHumidity; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }
}

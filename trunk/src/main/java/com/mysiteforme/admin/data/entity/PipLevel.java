package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 水位开关
 * @create 2019/6/27
 */
@TableName("pipeline_level")
public class PipLevel extends BaseEntity<PipLevel> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 水位开关 01 开启  00 关闭
     */
    @TableField("pipeline_level_pattern")
    private String pipelineLevelPattern ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelineLevelPattern() { return pipelineLevelPattern; }

    public void setPipelineLevelPattern(String pipelineLevelPattern) { this.pipelineLevelPattern = pipelineLevelPattern; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }
}

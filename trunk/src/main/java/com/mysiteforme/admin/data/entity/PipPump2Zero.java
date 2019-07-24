package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 出水泵工作时间归0
 * @create 2019/6/27
 */
@TableName("pipeline_pump2_zero")
public class PipPump2Zero extends BaseEntity<PipPump2Zero> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 抽水泵时长归零 00收到归零指令  01开始 02成功 03失败
     */
    @TableField("pipeline_pump2_zero")
    private String pipelinePump2Zero ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;


    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelinePump2Zero() { return pipelinePump2Zero; }

    public void setPipelinePump2Zero(String pipelinePump2Zero) { this.pipelinePump2Zero = pipelinePump2Zero; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }

}

package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 循环泵时长归0
 * @create 2019/6/27
 */
@TableName("pipeline_pump1_zero")
public class PipPump1Zero extends BaseEntity<PipPump1Zero> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 循环泵时长归零 00收到归零指令  01开始 02成功 03失败
     */
    @TableField("pipeline_pump1_zero")
    private String pipelinePump1Zero ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelinePump1Zero() { return pipelinePump1Zero; }

    public void setPipelinePump1Zero(String pipelinePump1Zero) { this.pipelinePump1Zero = pipelinePump1Zero; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }

}

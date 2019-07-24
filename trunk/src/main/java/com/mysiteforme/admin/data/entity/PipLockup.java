package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 设备锁死
 * @create 2019/6/27
 */
@TableName("pipeline_lockup")
public class PipLockup extends BaseEntity<PipLockup> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 设备锁死 00收到指令  01开始 02成功 03失败
     */
    @TableField("pipeline_lockup")
    private String pipelineLockup ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getPipelineLockup() { return pipelineLockup; }

    public void setPipelineLockup(String pipelineLockup) { this.pipelineLockup = pipelineLockup; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }

}

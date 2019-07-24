package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 滤芯状态
 * @create 2019/7/8
 */
@TableName("host_filter")
public class HostFilter extends BaseEntity<HostFilter> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 几级滤芯
     */
    @TableField("host_filter_level")
    private String hostFilterLevel;
    /**
     * 剩余天数
     */
    @TableField("host_filter_day")
    private int hostFilterDay;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getHostFilterLevel() { return hostFilterLevel; }

    public void setHostFilterLevel(String hostFilterLevel) { this.hostFilterLevel = hostFilterLevel; }

    public int getHostFilterDay() { return hostFilterDay; }

    public void setHostFilterDay(int hostFilterDay) { this.hostFilterDay = hostFilterDay; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }
}

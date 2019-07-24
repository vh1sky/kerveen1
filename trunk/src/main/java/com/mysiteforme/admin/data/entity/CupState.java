package com.mysiteforme.admin.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.BaseEntity;

/**
 * @auther lzh
 * @DESCRIPTION 杯子属性
 * @create 2019/6/27
 */
@TableName("cup_state")
public class CupState extends BaseEntity<CupState> {

    private static final long serialVersionUID = 1L;

    /**
     * OneNet设备id
     */
    private int onenetid;
    /**
     * 水杯id
     */
    @TableField("cup_id")
    private String cupId ;
    /**
     * 水杯 接水时间
     */
    @TableField("cup_time")
    private int cupTime ;
    /**
     * 身高
     */
    @TableField("cup_hight")
    private int cupHight ;
    /**
     * 体重
     */
    @TableField("cup_weight")
    private int cupWeight ;
    /**
     * 记录时间
     */
    @TableField("record_time")
    private String recordTime;
    /**
     * 水量
     */
    @TableField("cup_water")
    private int cupWater ;

    public int getCupWater() { return cupWater; }

    public void setCupWater(int cupWater) { this.cupWater = cupWater; }

    public int getOnenetid() { return onenetid; }

    public void setOnenetid(int onenetid) { this.onenetid = onenetid; }

    public String getCupId() { return cupId; }

    public String getRecordTime() { return recordTime; }

    public void setRecordTime(String recordTime) { this.recordTime = recordTime; }

    public void setCupId(String cupId) { this.cupId = cupId; }

    public int getCupTime() { return cupTime; }

    public void setCupTime(int cupTime) { this.cupTime = cupTime; }

    public int getCupHight() { return cupHight; }

    public void setCupHight(int cupHight) { this.cupHight = cupHight; }

    public int getCupWeight() { return cupWeight; }

    public void setCupWeight(int cupWeight) { this.cupWeight = cupWeight; }
}

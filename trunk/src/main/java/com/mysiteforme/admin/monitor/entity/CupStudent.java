package com.mysiteforme.admin.monitor.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.DataEntity;

import java.util.Date;

/**
 * DEV_CUP_STUDENT
 * 
 * @author Iwen
 * @version 1.0.0 2019-06-12
 */
@TableName("dev_cup_student")
public class CupStudent extends DataEntity<CupStudent> {
    /** 版本号 */
    private static final long serialVersionUID = 1L;

    /** 杯子唯一识别码 */
    private String cupCode;

    private Long userId;

    private Long customerId;

    /** 学生姓名 */
    private String name;

    /** 性别        0：女       1：男 */
    private Integer sex;

    /** 班级 */
    private String grade;

    /** 出生日期 */
    private Date birthday;

    /** 身高 */
    private Double height;

    /** 体重 */
    private Double weight;


    /**
     * 获取杯子唯一识别码
     * 
     * @return 杯子唯一识别码
     */
    public String getCupCode() {
        return this.cupCode;
    }

    /**
     * 设置杯子唯一识别码
     * 
     * @param cupCode
     *          杯子唯一识别码
     */
    public void setCupCode(String cupCode) {
        this.cupCode = cupCode;
    }

    /**
     * 获取学生姓名
     * 
     * @return 学生姓名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置学生姓名
     * 
     * @param name
     *          学生姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别        0：女       1：男
     * 
     * @return 性别        0
     */
    public Integer getSex() {
        return this.sex;
    }

    /**
     * 设置性别        0：女       1：男
     * 
     * @param sex
     *          性别        0
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取出生日期
     * 
     * @return 出生日期
     */
    public Date getBirthday() {
        return this.birthday;
    }

    /**
     * 设置出生日期
     * 
     * @param birthday
     *          出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取身高
     * 
     * @return 身高
     */
    public Double getHeight() {
        return this.height;
    }

    /**
     * 设置身高
     * 
     * @param height
     *          身高
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * 获取体重
     * 
     * @return 体重
     */
    public Double getWeight() {
        return this.weight;
    }

    /**
     * 设置体重
     * 
     * @param weight
     *          体重
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
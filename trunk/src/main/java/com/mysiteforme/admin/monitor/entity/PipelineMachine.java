package com.mysiteforme.admin.monitor.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.DataEntity;

import java.util.Date;

/**
 * DEV_PIPELINE_MACHINE
 *
 * @author Iwen
 * @version 1.0.0 2019-06-12
 */

@TableName("dev_pipeline_machine")
public class PipelineMachine extends DataEntity<PipelineMachine> {
    /** 版本号 */
    private static final long serialVersionUID = 1L;

    /** 管线机id */
    private String pipelineMachineId;

    /** 主板id */
    private String mainboardId;

    /** onenet设备id */
    private Long onenetId;

    /** 机型 */
    private String model;

    /** 生产日期 */
    private Date manufactureDate;

    /** 使用班级 */
    private String grade;

    /** 安装位置 */
    private String address;

    /** 主机表id */
    private Long hostId;

    /** 安装人id */
    private Long installBy;

    /** 安装日期 */
    private Date installDate;

    /** 状态    0：在线      1：离线 */
    private Integer status;

    /** 出水温度 */
    private Integer waterTemperature;

    /** 出水时间，决定单次出水量 */
    private Integer waterTime;

    @TableField(exist = false)
    private Long customerId;

    @TableField(exist = false)
    private String customerName;

    @TableField(exist = false)
    private String modelName;

    @TableField(exist = false)
    private String installName;

    @TableField(exist = false)
    private String statusName;


    /**
     * 获取管线机id
     * 
     * @return 管线机id
     */
    public String getPipelineMachineId() {
        return this.pipelineMachineId;
    }

    /**
     * 设置管线机id
     * 
     * @param pipelineMachineId
     *          管线机id
     */
    public void setPipelineMachineId(String pipelineMachineId) {
        this.pipelineMachineId = pipelineMachineId;
    }

    /**
     * 获取主板id
     * 
     * @return 主板id
     */
    public String getMainboardId() {
        return this.mainboardId;
    }

    /**
     * 设置主板id
     * 
     * @param mainboardId
     *          主板id
     */
    public void setMainboardId(String mainboardId) {
        this.mainboardId = mainboardId;
    }

    /**
     * 获取机型
     * 
     * @return 机型
     */
    public String getModel() {
        return this.model;
    }

    /**
     * 设置机型
     * 
     * @param model
     *          机型
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取生产日期
     * 
     * @return 生产日期
     */
    public Date getManufactureDate() {
        return this.manufactureDate;
    }

    /**
     * 设置生产日期
     * 
     * @param manufactureDate
     *          生产日期
     */
    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    /**
     * 获取使用班级
     * 
     * @return 使用班级
     */
    public String getGrade() {
        return this.grade;
    }

    /**
     * 设置使用班级
     * 
     * @param grade
     *          使用班级
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取安装位置
     * 
     * @return 安装位置
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 设置安装位置
     * 
     * @param address
     *          安装位置
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取主机表id
     * 
     * @return 主机表id
     */
    public Long getHostId() {
        return this.hostId;
    }

    /**
     * 设置主机表id
     * 
     * @param hostId
     *          主机表id
     */
    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    /**
     * 获取安装人id
     * 
     * @return 安装人id
     */
    public Long getInstallBy() {
        return this.installBy;
    }

    /**
     * 设置安装人id
     * 
     * @param installBy
     *          安装人id
     */
    public void setInstallBy(Long installBy) {
        this.installBy = installBy;
    }

    /**
     * 获取安装日期
     * 
     * @return 安装日期
     */
    public Date getInstallDate() {
        return this.installDate;
    }

    /**
     * 设置安装日期
     * 
     * @param installDate
     *          安装日期
     */
    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    /**
     * 获取状态    0：在线      1：离线
     * 
     * @return 状态    0
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态    0：在线      1：离线
     * 
     * @param status
     *          状态    0
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getInstallName() {
        return installName;
    }

    public void setInstallName(String installName) {
        this.installName = installName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getOnenetId() {
        return onenetId;
    }

    public void setOnenetId(Long onenetId) {
        this.onenetId = onenetId;
    }

    public Integer getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(Integer waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public Integer getWaterTime() {
        return waterTime;
    }

    public void setWaterTime(Integer waterTime) {
        this.waterTime = waterTime;
    }
}
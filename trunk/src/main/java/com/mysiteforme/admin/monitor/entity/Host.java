package com.mysiteforme.admin.monitor.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.DataEntity;

import java.util.Date;

/**
 * DEV_HOST
 * 
 * @author Iwen
 * @version 1.0.0 2019-06-12
 */
@TableName("dev_host")
public class Host extends DataEntity<Host> {
    /** 版本号 */
    private static final long serialVersionUID = 1L;

    /** 主机id */
    private String hostDeviceId;

    /** 主板id */
    private String mainboardId;

    /** 机型 */
    private String model;

    /** 生产日期 */
    private Date manufactureDate;

    /** 安装人id */
    private Long installBy;

    /** 安装地址 */
    private String address;

    /** 安装时间 */
    private Date installDate;

    /** 客户信息表id */
    private Long customerId;

    /** 状态      0：在线       1：离线 */
    private Integer status;

    @TableField(exist = false)
    private String modelName;

    @TableField(exist = false)
    private String installName;

    @TableField(exist = false)
    private String statusName;

    @TableField(exist = false)
    private String customerName;


    /**
     * 获取主机id
     * 
     * @return 主机id
     */
    public String getHostDeviceId() {
        return this.hostDeviceId;
    }

    /**
     * 设置主机id
     * 
     * @param hostDeviceId
     *          主机id
     */
    public void setHostDeviceId(String hostDeviceId) {
        this.hostDeviceId = hostDeviceId;
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
     * 获取安装地址
     * 
     * @return 安装地址
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 设置安装地址
     * 
     * @param address
     *          安装地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取安装时间
     * 
     * @return 安装时间
     */
    public Date getInstallDate() {
        return this.installDate;
    }

    /**
     * 设置安装时间
     * 
     * @param installDate
     *          安装时间
     */
    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    /**
     * 获取客户信息表id
     * 
     * @return 客户信息表id
     */
    public Long getCustomerId() {
        return this.customerId;
    }

    /**
     * 设置客户信息表id
     * 
     * @param customerId
     *          客户信息表id
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取状态      0：在线       1：离线
     * 
     * @return 状态      0
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态      0：在线       1：离线
     * 
     * @param status
     *          状态      0
     */
    public void setStatus(Integer status) {
        this.status = status;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
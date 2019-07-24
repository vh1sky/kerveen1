package com.mysiteforme.admin.sysuser.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.DataEntity;

import java.util.Date;

@TableName("sys_customer")
public class Customer extends DataEntity<Customer> {

    private static final long serialVersionUID = 1L;

    //客户名称
    private String name;
    //客户所属行业编码公立私立
    private String industryCode;

    @TableField(exist = false)
    private String industryName;
    //客户地址
    private String address;
    //客户电话
    private String phone;
    //安装时间
    private Date installDate;
    //销售方式编码
    private String typeCode;

    @TableField(exist = false)
    private String typeName;
    //租赁时长或售后时长，单位：月
    private int duration;
    //客户状态编码
    private String status;

    @TableField(exist = false)
    private String statusName;
    //销售人员姓名
    private String salePerson;
    //售后人员姓名
    private String servicePerson;
    //客户所在城市
    private String city;
    //省
    private String province;

    public String getProvince() { return province; }

    public void setProvince(String province) { this.province = province; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name == null ? null : name.trim(); }

    public String getIndustryCode() { return industryCode; }

    public void setIndustryCode(String industryCode) { this.industryCode = industryCode == null ? null : industryCode.trim(); }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address == null ? null : address.trim(); }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone == null ? null : phone.trim(); }

    public Date getInstallDate() { return installDate; }

    public void setInstallDate(Date installDate) { this.installDate = installDate; }

    public String getTypeCode() { return typeCode; }

    public void setTypeCode(String typeCode) { this.typeCode = typeCode == null ? null : typeCode.trim(); }

    public int getDuration() { return duration; }

    public void setDuration(int duration) { this.duration = duration; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status == null ? null : status.trim(); }

    public String getSalePerson() { return salePerson; }

    public void setSalePerson(String salePerson) { this.salePerson = salePerson == null ? null : salePerson.trim(); }

    public String getServicePerson() { return servicePerson; }

    public void setServicePerson(String servicePerson) { this.servicePerson = servicePerson == null ? null : servicePerson.trim(); }

    public String getIndustryName() { return industryName; }

    public void setIndustryName(String industryName) { this.industryName = industryName; }

    public String getTypeName() { return typeName; }

    public void setTypeName(String typeName) { this.typeName = typeName; }

    public String getStatusName() { return statusName; }

    public void setStatusName(String statusName) { this.statusName = statusName; }
}
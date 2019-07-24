package com.mysiteforme.admin.sysuser.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.google.common.collect.Sets;
import com.mysiteforme.admin.base.DataEntity;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Iwen
 * @date 2019/6/10 14:38
 * @Version 1.0 字典类型表
 */
@TableName("sys_dict_type")
public class DictType extends DataEntity<Dict> {

    private static final long serialVersionUID = 1L;


    /**
     * 类型名
     */
    private String name;
    /**
     * 类型code
     */
    private String type;

    @TableField(exist=false)
    private Set<Dict> dicts = Sets.newHashSet();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Dict> getDicts() {
        return dicts;
    }

    public void setMenus(Set<Dict> dicts) {
        this.dicts = dicts;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DictType{" +
                ", type=" + type +
                ", name=" + name +
                ", type=" + type +
                "}";
    }
}

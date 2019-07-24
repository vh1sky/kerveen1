package com.mysiteforme.admin.sysuser.entity.VO;

import com.google.common.collect.Lists;
import com.mysiteforme.admin.sysuser.entity.Dict;

import java.util.List;

/**
 * @author Iwen
 * @date 2019/6/13 16:37
 * @Version 1.0
 */
public class TreeDict {
    private Long id;
    private  Long pid;
    private String type;
    private String name;
    private Boolean spread;
    List<Dict> children = Lists.newArrayList();

    public TreeDict(Boolean spread) {
        this.spread = spread;
    }

    public TreeDict(Long id, Long pid, String type,String name) {
        this.id = id;
        this.pid = pid;
        this.type = type;
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public List<Dict> getChildren() {
        return children;
    }

    public void setChildren(List<Dict> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

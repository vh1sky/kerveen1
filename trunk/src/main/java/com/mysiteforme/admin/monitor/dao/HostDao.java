package com.mysiteforme.admin.monitor.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.monitor.entity.Host;

import java.util.List;
import java.util.Map;

/**
 * @author Iwen
 * @date 2019/6/15 10:30
 * @Version 1.0
 */
public interface HostDao extends BaseMapper<Host> {
    List<Host> selectHostByMap(Map<String, Object> map);
}

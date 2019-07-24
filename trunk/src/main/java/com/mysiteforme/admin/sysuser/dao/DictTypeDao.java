package com.mysiteforme.admin.sysuser.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.sysuser.entity.DictType;

import java.util.List;
import java.util.Map;

/**
 * @author Iwen
 * @date 2019/6/10 14:53
 * @Version 1.0
 */
public interface DictTypeDao extends BaseMapper<DictType> {
    List<DictType> selectListByMap(Map<String, Object> map);
}

package com.mysiteforme.admin.monitor.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.monitor.entity.CupStudent;

import java.util.List;
import java.util.Map;

/**
 * @author Iwen
 * @date 2019/6/13 14:47
 * @Version 1.0
 */
public interface CupStudentDao extends BaseMapper<CupStudent> {

    List<CupStudent> selectListByMap(Map<String, Object> map);

    List<CupStudent> selectCupStudent(Map<String, Object> map);

    List<CupStudent> selectCupStudent(Map<String, Object> map, Page<CupStudent> page);

    void saveCupStudent(Map<String, Object> map);

    void removeCupStudent(Long cupId);

}

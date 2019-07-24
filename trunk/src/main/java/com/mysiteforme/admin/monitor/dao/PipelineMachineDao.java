package com.mysiteforme.admin.monitor.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.monitor.entity.PipelineMachine;

import java.util.List;
import java.util.Map;

/**
 * @author Iwen
 * @date 2019/6/15 15:51
 * @Version 1.0
 */
public interface PipelineMachineDao extends BaseMapper<PipelineMachine> {
    List selectPipelineMachineByMap(Map<String, Object> map);
}

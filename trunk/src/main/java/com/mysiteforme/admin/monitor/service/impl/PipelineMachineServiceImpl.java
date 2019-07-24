package com.mysiteforme.admin.monitor.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.monitor.dao.PipelineMachineDao;
import com.mysiteforme.admin.monitor.entity.PipelineMachine;
import com.mysiteforme.admin.monitor.service.PipelineMachineService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Iwen
 * @date 2019/6/15 15:53
 * @Version 1.0
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PipelineMachineServiceImpl extends ServiceImpl<PipelineMachineDao, PipelineMachine> implements PipelineMachineService {

    @Override
    public List selectPipelineMachine(String pipelineMachineId, String customerName, String model, String status, String address, Long id) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("pipelineMachineId", pipelineMachineId);
        map.put("customerName", customerName);
        map.put("model", model);
        map.put("status", status);
        map.put("address", address);
        List<PipelineMachine> result = baseMapper.selectPipelineMachineByMap(map);

        return result;
    }

    @CacheEvict(value = "pipelineMachineCache",key = "#pipelineMachine.pipelineMachineId",condition = "#pipelineMachine.pipelineMachineId ne null ")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void saveOrUpdatePipelineMachine(PipelineMachine pipelineMachine) {
        insertOrUpdate(pipelineMachine);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    @Caching(evict = {
            @CacheEvict(value = "pipelineMachine", key = "'pipeline_machine_id_'+T(String).valueOf(#pipelineMachine.pipelineMachineId)",condition = "#pipelineMachine.pipelineMachineId != null and #pipelineMachine.pipelineMachineId != '' "),
            @CacheEvict(value = "pipelineMachine", key = "'mainboard_id_'+#pipelineMachine.mainboardId", condition = "#pipelineMachine.mainboardId !=null and #pipelineMachine.mainboardId != ''"),
    })
    public void deletePipelineMachine(PipelineMachine pipelineMachine) {
        pipelineMachine.setDelFlag(true);
        pipelineMachine.updateById();
    }
}

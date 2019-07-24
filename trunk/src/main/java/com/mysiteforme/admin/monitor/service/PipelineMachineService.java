package com.mysiteforme.admin.monitor.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.monitor.entity.PipelineMachine;

import java.util.List;

/**
 * @author Iwen
 * @date 2019/6/15 15:53
 * @Version 1.0
 */
public interface PipelineMachineService extends IService<PipelineMachine>  {
    List selectPipelineMachine(String pipelineMachineId, String customerName, String model, String status, String address, Long id);

    void saveOrUpdatePipelineMachine(PipelineMachine pipelineMachine);

    void deletePipelineMachine(PipelineMachine pipelineMachine);
}

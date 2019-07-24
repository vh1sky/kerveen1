package com.mysiteforme.admin.monitor.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.monitor.entity.Host;

import java.util.List;

/**
 * @author Iwen
 * @date 2019/6/15 10:31
 * @Version 1.0
 */
public interface HostService extends IService<Host> {

    List selectHost(String hostDeviceId, String customerName, String model, String status, String address);

    void saveOrUpdateHost(Host host);

    void deleteHost(Host host);
}

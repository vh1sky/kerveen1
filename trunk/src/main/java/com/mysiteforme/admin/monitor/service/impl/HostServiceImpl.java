package com.mysiteforme.admin.monitor.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.monitor.dao.HostDao;
import com.mysiteforme.admin.monitor.entity.Host;
import com.mysiteforme.admin.monitor.service.HostService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Iwen
 * @date 2019/6/15 10:32
 * @Version 1.0
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class HostServiceImpl extends ServiceImpl<HostDao, Host> implements HostService {
    @Override
    public List selectHost(String hostDeviceId, String customerName, String model, String status, String address) {

        Map<String,Object> map = Maps.newHashMap();
        map.put("hostDeviceId", hostDeviceId);
        map.put("customerName", customerName);
        map.put("model", model);
        map.put("status", status);
        map.put("address", address);
        List<Host> totalHosts = baseMapper.selectHostByMap(map);

        return totalHosts;
    }

    @CacheEvict(value = "hostCache",key = "#host.hostDeviceId",condition = "#host.hostDeviceId ne null ")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateHost(Host host) {
        insertOrUpdate(host);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    @Caching(evict = {
            @CacheEvict(value = "host", key = "'host_device_id_'+T(String).valueOf(#host.hostDeviceId)",condition = "#host.hostDeviceId != null and #host.hostDeviceId != '' "),
            @CacheEvict(value = "host", key = "'mainboard_id_'+#host.mainboardId", condition = "#host.mainboardId !=null and #host.mainboardId != ''"),
    })
    public void deleteHost(Host host) {
        host.setDelFlag(true);
        host.updateById();
    }
}

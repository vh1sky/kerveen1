package com.mysiteforme.admin.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.data.dao.PipHumidityDao;
import com.mysiteforme.admin.data.entity.PipHumidity;
import com.mysiteforme.admin.data.service.PipHumidityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/16
 */
@Service("pipHumidityService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PipHumidityServiceImpl extends ServiceImpl<PipHumidityDao, PipHumidity> implements PipHumidityService {

}

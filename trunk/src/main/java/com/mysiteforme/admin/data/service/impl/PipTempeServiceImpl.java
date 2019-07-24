package com.mysiteforme.admin.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.data.dao.PipTempeDao;
import com.mysiteforme.admin.data.entity.PipTemperature;
import com.mysiteforme.admin.data.service.PipTempeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/17
 */
@Service("pipTempeService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PipTempeServiceImpl extends ServiceImpl<PipTempeDao, PipTemperature> implements PipTempeService {
}

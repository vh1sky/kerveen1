package com.mysiteforme.admin.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.data.dao.PipValveDao;
import com.mysiteforme.admin.data.entity.PipValve;
import com.mysiteforme.admin.data.service.PipValveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/17
 */
@Service("pipValveService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PipValveServiceImpl extends ServiceImpl<PipValveDao, PipValve> implements PipValveService {
}

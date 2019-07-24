package com.mysiteforme.admin.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.data.dao.PipPump2Dao;
import com.mysiteforme.admin.data.entity.PipPump2;
import com.mysiteforme.admin.data.service.PipPump2Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/17
 */
@Service("pipPump2Service")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PipPump2ServiceImpl extends ServiceImpl<PipPump2Dao, PipPump2> implements PipPump2Service {
}

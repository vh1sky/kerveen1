package com.mysiteforme.admin.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.data.dao.PipPump1Dao;
import com.mysiteforme.admin.data.entity.PipPump1;
import com.mysiteforme.admin.data.service.PipPump1Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/17
 */
@Service("pipPump1Service")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PipPump1ServiceImpl extends ServiceImpl<PipPump1Dao,PipPump1> implements PipPump1Service {
}

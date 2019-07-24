package com.mysiteforme.admin.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.data.dao.PipOutDao;
import com.mysiteforme.admin.data.entity.PipOut;
import com.mysiteforme.admin.data.service.PipOutService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/17
 */
@Service("pipOutService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PipOutServiceImpl extends ServiceImpl<PipOutDao, PipOut> implements PipOutService {
}

package com.mysiteforme.admin.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.data.dao.PipWarnDao;
import com.mysiteforme.admin.data.entity.PipWarn;
import com.mysiteforme.admin.data.service.PipWarnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/17
 */
@Service("pipWarnService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PipWarnServiceImpl extends ServiceImpl<PipWarnDao, PipWarn> implements PipWarnService {
}

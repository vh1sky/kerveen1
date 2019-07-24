package com.mysiteforme.admin.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.data.dao.PiplevelDao;
import com.mysiteforme.admin.data.entity.PipLevel;
import com.mysiteforme.admin.data.service.PipLevelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/17
 */
@Service("pipLevelService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PipLevelServiceImpl extends ServiceImpl<PiplevelDao, PipLevel> implements PipLevelService {
}

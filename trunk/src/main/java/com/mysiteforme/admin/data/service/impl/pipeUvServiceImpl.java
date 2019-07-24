package com.mysiteforme.admin.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.data.dao.PipeUvDao;
import com.mysiteforme.admin.data.entity.PipeUv;
import com.mysiteforme.admin.data.service.PipeUvService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/17
 */
@Service("pipeUvServiceImpl")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class pipeUvServiceImpl extends ServiceImpl<PipeUvDao,PipeUv> implements PipeUvService {
}

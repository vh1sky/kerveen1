package com.mysiteforme.admin.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.data.dao.PipTprobeDao;
import com.mysiteforme.admin.data.entity.PipTprobe;
import com.mysiteforme.admin.data.service.PipTprobeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther lzh
 * @DESCRIPTION
 * @create 2019/7/17
 */
@Service("pipTprobeService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PipTprobeServiceImpl extends ServiceImpl<PipTprobeDao,PipTprobe> implements PipTprobeService {
}

package com.mysiteforme.admin.monitor.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.monitor.dao.CupStudentDao;
import com.mysiteforme.admin.monitor.entity.CupStudent;
import com.mysiteforme.admin.monitor.service.CupStudentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Iwen
 * @date 2019/6/14 17:04
 * @Version 1.0
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CupStudentServiceImpl extends ServiceImpl<CupStudentDao, CupStudent> implements CupStudentService {

    @CacheEvict(value = "cupStudentCache",key = "#cupStudent.cupCode",condition = "#cupStudent.cupCode ne null ")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateCupStudent(CupStudent cupStudent) {
        insertOrUpdate(cupStudent);
    }

    @Override
    public List selectGradeByCustomer(Long customerId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("customerId", customerId);
        List grades = baseMapper.selectListByMap(map);
        return grades;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    @Caching(evict = {
            @CacheEvict(value = "cupStudent", key = "'cup_code_'+T(String).valueOf(#cupStudent.cupCode)",condition = "#cupStudent.cupCode != null and #cupStudent.cupCode != '' "),
            @CacheEvict(value = "cupStudent", key = "'name_'+#cupStudent.name", condition = "#cupStudent.name !=null and #cupStudent.name != ''"),
    })
    public void deleteCupStudent(CupStudent cupStudent) {
        cupStudent.setDelFlag(true);
        cupStudent.updateById();
    }
}

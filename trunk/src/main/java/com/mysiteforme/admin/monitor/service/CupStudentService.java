package com.mysiteforme.admin.monitor.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.monitor.entity.CupStudent;

import java.util.List;

/**
 * @author Iwen
 * @date 2019/6/14 17:02
 * @Version 1.0
 */
public interface CupStudentService extends IService<CupStudent> {

    void saveOrUpdateCupStudent(CupStudent cupStudent);

    List selectGradeByCustomer(Long customerId);

    void deleteCupStudent(CupStudent cupStudent);
}

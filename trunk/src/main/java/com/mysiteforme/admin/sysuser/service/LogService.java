package com.mysiteforme.admin.sysuser.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.sysuser.entity.Log;

import java.util.List;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author wangl
 * @since 2018-01-13
 */
public interface LogService extends IService<Log> {

    public List<Integer> selectSelfMonthData();

}

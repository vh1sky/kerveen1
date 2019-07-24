package com.mysiteforme.admin.base;

import com.mysiteforme.admin.data.service.*;
import com.mysiteforme.admin.monitor.service.CupStudentService;
import com.mysiteforme.admin.monitor.service.HostService;
import com.mysiteforme.admin.monitor.service.PipelineMachineService;
import com.mysiteforme.admin.realm.AuthRealm.ShiroUser;
import com.mysiteforme.admin.sysuser.entity.User;
import com.mysiteforme.admin.sysuser.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	
	public User getCurrentUser() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(shiroUser == null) {
			return null;
		}
		User loginUser = userService.selectById(shiroUser.getId());
		return loginUser;
	}
	@Autowired
	protected PipHumidityService pipHumidityService;

	@Autowired
	protected UserService userService;

	@Autowired
	protected MenuService menuService;

	@Autowired
	protected RoleService roleService;

	@Autowired
	protected DictService dictService;

	@Autowired
	protected RescourceService rescourceService;

	@Autowired
	protected TableService tableService;

	@Autowired
	protected SiteService siteService;

	@Autowired
	protected LogService logService;

	@Autowired
	protected QuartzTaskService quartzTaskService;

	@Autowired
	protected QuartzTaskLogService quartzTaskLogService;

	@Autowired
	protected DictTypeService dictTypeService;

	@Autowired
	protected CustomerService customerService;

	@Autowired
	protected CupStudentService cupStudentService;

    @Autowired
    protected HostService hostService;

    @Autowired
    protected PipelineMachineService pipelineMachineService;

    @Autowired
	protected PipTempeService pipTempeService;

    @Autowired
	protected PipOutService pipOutService;

    @Autowired
	protected PipLevelService pipLevelService;

    @Autowired
	protected PipeUvService pipeUvService;

    @Autowired
	protected  PipTprobeService pipTprobeService;

    @Autowired
	protected PipPump2Service pipPump2Service;

    @Autowired
	protected PipPump1Service pipPump1Service;

    @Autowired
	protected PipValveService pipValveService;
}

package com.ldz.college.biz;

import com.ldz.college.entity.AdminInfo;

public interface IAdminInfoBiz {
	/**
	 * 管理员登录
	 * @return
	 */
	public AdminInfo login(AdminInfo af);
}

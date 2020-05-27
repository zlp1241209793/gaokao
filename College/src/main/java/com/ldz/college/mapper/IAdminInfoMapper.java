package com.ldz.college.mapper;

import com.ldz.college.entity.AdminInfo;

public interface IAdminInfoMapper {
	/**
	 * 管理员登录
	 * @return
	 */
	public AdminInfo login(AdminInfo af);
}

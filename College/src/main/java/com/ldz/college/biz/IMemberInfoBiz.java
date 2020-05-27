package com.ldz.college.biz;


import com.ldz.college.entity.MemberInfo;

public interface IMemberInfoBiz {
	/**
	 * 用户登录
	 * @param mInfo
	 * @return
	 */
	public MemberInfo login(MemberInfo mInfo);
	
	/**
	 * 用户注册
	 * @param mInfo
	 * @return
	 */
	public int register(MemberInfo mInfo);
	
	/**
	 * 用户修改个人资料
	 * @param mInfo
	 * @return
	 */
	public int updateInfo(MemberInfo mInfo);
	
	/**
	 * 通过用户编号查询用户所有信息
	 * @param mno
	 * @return
	 */
	public MemberInfo findAll(String mno);
}

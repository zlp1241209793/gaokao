package com.ldz.college.biz;


import java.util.List;
import java.util.Map;

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
	
	/**
	 * 根据用户邮箱查询用户信息
	 * @param email
	 * @return
	 */
	public MemberInfo findByEmail(String email);
	
	/**
	 * 查询所有专业
	 * @return
	 */
	public List<Map<String, Object>> findMajor();
}

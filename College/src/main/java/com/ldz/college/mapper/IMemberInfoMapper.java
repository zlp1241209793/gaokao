package com.ldz.college.mapper;

import java.util.List;
import java.util.Map;

import com.ldz.college.entity.MemberInfo;

public interface IMemberInfoMapper {
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
	
	/**
	 * 用户通过邮箱重置密码
	 * @param pwd
	 * @return
	 */
	public int resetPwdByEmail(MemberInfo memberInfo);
	
	/**
	 * 通过用户编号查询用户部分信息
	 * @param mno
	 * @return
	 */
	public MemberInfo find(MemberInfo memberInfo);
}

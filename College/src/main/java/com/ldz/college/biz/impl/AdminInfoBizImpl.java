package com.ldz.college.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldz.college.biz.IAdminInfoBiz;
import com.ldz.college.entity.AdminInfo;
import com.ldz.college.mapper.IAdminInfoMapper;

@Service
@Transactional
public class AdminInfoBizImpl implements IAdminInfoBiz {
	@Autowired
	private IAdminInfoMapper adminInfoMapper;
	
	@Override
	@Transactional
	public AdminInfo login(AdminInfo af) {
		return adminInfoMapper.login(af);
	}
}

package com.ldz.college.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldz.college.biz.IMemberInfoBiz;
import com.ldz.college.entity.MemberInfo;
import com.ldz.college.mapper.IMemberInfoMapper;
import com.ldz.college.util.StringUtil;

@Service
@Transactional
public class MemberInfoBizImpl implements IMemberInfoBiz {
	@Autowired
	private IMemberInfoMapper memberInfoMapper;
	
	@Override
	@Transactional
	public MemberInfo login(MemberInfo mInfo) {
		if (StringUtil.checkNull(mInfo.getNickName(), mInfo.getPwd())) {
			return null;
		}
		return memberInfoMapper.login(mInfo);
	}

	@Override
	@Transactional
	public int register(MemberInfo mInfo) {
		if (StringUtil.checkNull(mInfo.getNickName(), mInfo.getPwd())) {
			return 0;
		}
		// 查询该账号是否已经注册
		MemberInfo mf = memberInfoMapper.login(mInfo);
		if (mf != null) { // 说明该账号已经注册
			return -1;
		}
		return memberInfoMapper.register(mInfo);
	}

	@Override
	public int updateInfo(MemberInfo mInfo) {
		if (StringUtil.checkNull(mInfo.getNickName(), mInfo.getSex(), mInfo.getAcademic(), mInfo.getProvince(), mInfo.getScore(), mInfo.getRanking(), mInfo.getMno())) {
			return 0;
		}
		return memberInfoMapper.updateInfo(mInfo);
	}

	@Override
	public MemberInfo findAll(String mno) {
		if (StringUtil.checkNull(mno)) {
			return null;
		}
		return memberInfoMapper.findAll(mno);
	}

}

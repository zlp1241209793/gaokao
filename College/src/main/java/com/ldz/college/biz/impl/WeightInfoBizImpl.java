package com.ldz.college.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldz.college.biz.IWeightInfoBiz;
import com.ldz.college.entity.WeightInfo;
import com.ldz.college.mapper.IWeightInfoMapper;
import com.ldz.college.util.StringUtil;

@Service
@Transactional
public class WeightInfoBizImpl implements IWeightInfoBiz {
	@Autowired
	private IWeightInfoMapper weightMapper;

	@Transactional
	@Override
	public int setWeight(WeightInfo weight) {
		if (StringUtil.checkNull(weight.getMno(), weight.getLocation(), weight.getRanking(), weight.getMajor())) {
			return 0;
		}
		// 判断该用户是否设置过权重,若是则调用修改的方法
		WeightInfo wgt = weightMapper.findByMno(weight.getMno().toString());
		if (wgt != null) { // 说明该用户的权重信息已经存在
			return weightMapper.updateWeight(weight); // 调用修改权重的方法
		}
		return weightMapper.setWeight(weight); // 调用设置权重的方法
	}

	@Transactional
	@Override
	public int updateWeight(WeightInfo weight) {
		if (StringUtil.checkNull(weight.getMno(), weight.getLocation(), weight.getRanking(), weight.getMajor())) {
			return 0;
		}
		return weightMapper.updateWeight(weight);
	}

	@Transactional
	@Override
	public WeightInfo findByMno(String mno) {
		if (StringUtil.checkNull(mno)) {
			return null;
		}
		return weightMapper.findByMno(mno);
	}

}

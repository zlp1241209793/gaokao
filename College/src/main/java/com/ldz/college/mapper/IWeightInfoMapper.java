package com.ldz.college.mapper;

import com.ldz.college.entity.WeightInfo;

public interface IWeightInfoMapper {
	/**
	 * 用户设置权重
	 * @param mno
	 * @return
	 */
	public int setWeight(WeightInfo weight);
	
	/**
	 * 用户修改权重
	 * @param mno
	 * @return
	 */
	public int updateWeight(WeightInfo weight);
	
	/**
	 * 通过用户编号查询用户权重
	 * @param mno
	 * @return
	 */
	public WeightInfo findByMno(String mno);
}

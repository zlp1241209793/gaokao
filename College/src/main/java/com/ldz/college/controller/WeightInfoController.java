package com.ldz.college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldz.college.biz.IWeightInfoBiz;
import com.ldz.college.entity.WeightInfo;

@RestController
@RequestMapping("/weight")
public class WeightInfoController {
	@Autowired
	private IWeightInfoBiz weightBiz;
	
	@RequestMapping("/set")
	public int setWeight(WeightInfo weight) {
		int result = -1;
		result = weightBiz.setWeight(weight);
		return result;
	}
	
	@RequestMapping("/update")
	public int updateWeight(WeightInfo weight) {
		int result = -1;
		result = weightBiz.updateWeight(weight);
		return result;
	}
	
	@RequestMapping("/find")
	public WeightInfo findByMno(String mno) {
		return weightBiz.findByMno(mno);
	}
}

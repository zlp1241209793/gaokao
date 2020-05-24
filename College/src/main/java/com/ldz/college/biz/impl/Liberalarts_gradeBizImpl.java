package com.ldz.college.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldz.college.biz.ILiberalarts_gradeBiz;
import com.ldz.college.mapper.ILiberalarts_gradeMapper;

@Service
@Transactional
public class Liberalarts_gradeBizImpl implements ILiberalarts_gradeBiz {
	@Autowired
	private ILiberalarts_gradeMapper liberalarts_gradeMapper;

}

package com.ldz.college.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldz.college.biz.ILiberalarts_situationBiz;
import com.ldz.college.mapper.ILiberalarts_situationMapper;

@Service
@Transactional
public class Liberalarts_situationBizImpl implements ILiberalarts_situationBiz {
	@Autowired 
	private ILiberalarts_situationMapper liberalarts_situationMapper;

	@Transactional
	@Override
	public List<Map<String, Object>> locationRank(Map<String, Object> map) {
		return liberalarts_situationMapper.locationRank(map);
	}

	@Transactional
	@Override
	public List<Map<String, Object>> majorRank(Map<String, Object> map) {
		return liberalarts_situationMapper.majorRank(map);
	}

	@Transactional
	@Override
	public List<Map<String, Object>> schoolRank(Map<String, Object> map) {
		return liberalarts_situationMapper.schoolRank(map);
	}
}

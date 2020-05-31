package com.ldz.college.biz.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldz.college.biz.IUniversityInfoBiz;
import com.ldz.college.entity.UniversityInfo;
import com.ldz.college.mapper.IUniversityInfoMapper;
import com.ldz.college.util.Lucene;
import com.ldz.college.util.RequestParameterUtil;
import com.ldz.college.util.StringUtil;

@Service
@Transactional
public class UniversityInfoBizImpl implements IUniversityInfoBiz{
	@Autowired
	private IUniversityInfoMapper universityInfoMapper;
	
	@Transactional
	@Override
	public List<UniversityInfo> findByPage(Integer page, Integer rows) {
		if (StringUtil.checkNull(page, rows)) {
			return Collections.EMPTY_LIST;
		}
		Map<String, Object> map = RequestParameterUtil.pageParam(page, rows);
		return universityInfoMapper.findByPage(map);
	}

	@Transactional
	@Override
	public String getCount() {
		return universityInfoMapper.getCount();
	}

	@Override
	@PostConstruct // 项目启动时运行一次
	public void createIndex() {
		List<Map<String, Object>> list = universityInfoMapper.findAll();
		Lucene lucene = new Lucene();
		try {
			lucene.createIndex(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public Map<String, Object> luceneSearch(String text, Integer page, Integer rows) {
		// 处理page和rows
		Map<String, Object> myMap = RequestParameterUtil.lucenePageParam(page, rows);
		
		Lucene lucene = new Lucene();
		Map<String, Object> luceneMap = null;
		try {
			luceneMap = lucene.Search(text, Integer.parseInt(myMap.get("page").toString()), Integer.parseInt(myMap.get("rows").toString()));
		} catch (NumberFormatException | IOException | ParseException e) {
			e.printStackTrace();
		}

		return luceneMap;
	}
}

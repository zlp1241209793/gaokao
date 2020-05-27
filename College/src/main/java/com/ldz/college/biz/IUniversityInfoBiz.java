package com.ldz.college.biz;

import java.util.List;
import java.util.Map;

import com.ldz.college.entity.UniversityInfo;

public interface IUniversityInfoBiz {
	/**
	 * 分页查询学校数据
	 * @param map
	 * @return
	 */
	public List<UniversityInfo> findByPage(Integer page, Integer rows);
	
	/**
	 * 查询学校数量
	 * @return
	 */
	public String getCount();
	
	/**
	 * 为luncene创建索引
	 */
	public void createIndex();
	
	/**
	 * lucene 查询
	 * @param text 查询条件
	 * @param page 当前页数
	 * @param rows 每页行数
	 */
	public Map<String, Object> luceneSearch(String text, Integer page, Integer rows);
}

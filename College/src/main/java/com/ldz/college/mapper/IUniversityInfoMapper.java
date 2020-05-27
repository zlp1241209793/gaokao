package com.ldz.college.mapper;

import java.util.List;
import java.util.Map;

import com.ldz.college.entity.UniversityInfo;

public interface IUniversityInfoMapper {
	/**
	 * 分页查询学校数据
	 * @param map
	 * @return
	 */
	public List<UniversityInfo> findByPage(Map<String, Object> map);

	/**
	 * 查询所有学校数据
	 * @return
	 */
	public List<Map<String, Object>> findAll();

	/**
	 * 查询学校数量
	 * @return
	 */
	public String getCount();
}

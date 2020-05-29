package com.ldz.college.mapper;

import java.util.List;
import java.util.Map;


public interface ILiberalarts_situationMapper {
	/**
	 * 地域优先算法
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> locationRank(Map<String, Object> map);
	
	/**
	 * 专业优先算法
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> majorRank(Map<String, Object> map);
}

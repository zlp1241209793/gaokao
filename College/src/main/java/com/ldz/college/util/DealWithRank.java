package com.ldz.college.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 处理排名的各种方法
 * @author 1241209793
 *
 */
public class DealWithRank {
	/**
	 * 对特殊市的排名做处理(学校排名)
	 * @param list 需要处理的集合
	 * @param percent 需要加的百分比
	 * @return
	 */
	public List<Map<String, Object>> cityRank_Displacement(List<Map<String, Object>> list, Double percent, String text) {
		if (list.size() <= 0) { // 如果集合中没有数据
			return Collections.EMPTY_LIST;
		}
		for (Map<String, Object> map : list) {
			// 判断该城市是否为字符串中的城市
			if (text.contains(map.get("city").toString())) { // 若是，省会城市学校排名+（筛选结果总数*percent%）
				map.put("displacement", (int) (Double.parseDouble(map.get("displacement").toString()) - (list.size() * percent)));
			}
		}
		return list;
	}
	
	/**
	 * 对特殊省的排名做处理(学校排名)
	 * @param list 需要处理的集合
	 * @param percent 需要加的百分比
	 * @return
	 */
	public List<Map<String, Object>> provinceRank_Displacement(List<Map<String, Object>> list, Double percent, String text) {
		if (list.size() <= 0) { // 如果集合中没有数据
			return Collections.EMPTY_LIST;
		}
		for (Map<String, Object> map : list) {
			// 判断该省是否字符串中的省
			if (text.contains(map.get("province").toString())) { // 若是，省会城市学校排名+（筛选结果总数*percent%）
				map.put("displacement", (int) (Double.parseDouble(map.get("displacement").toString()) - (list.size() * percent)));
			}
		}
		return list;
	}
	
	/**
	 * 对学校排名重新进行升序排序
	 * @param list
	 * @return
	 */
	public List<Map<String, Object>> reSort_Displacement(List<Map<String, Object>> list) {
		if (list.size() <= 0) { // 如果集合中没有数据
			return Collections.EMPTY_LIST;
		}
		Collections.sort(list, new Comparator<Map<String, Object>>(){
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return (int)o1.get("displacement") - (int)o2.get("displacement");
			}  
		}); 
		return list;
	}
	
	
	
	
	/**
	 * 对特殊市的排名做处理(学校专业排名)
	 * @param list 需要处理的集合
	 * @param percent 需要加的百分比
	 * @return
	 */
	public List<Map<String, Object>> cityRank_Major(List<Map<String, Object>> list, Double percent, String text) {
		if (list.size() <= 0) { // 如果集合中没有数据
			return Collections.EMPTY_LIST;
		}
		for (Map<String, Object> map : list) {
			// 判断该城市是否为字符串中的城市
			if (text.contains(map.get("city").toString())) { // 若是，省会城市学校排名+（筛选结果总数*percent%）
				map.put("rank", (int) (Double.parseDouble(map.get("rank").toString()) - (list.size() * percent)));
			}
		}
		return list;
	}
	
	/**
	 * 对特殊省的排名做处理(学校专业排名)
	 * @param list 需要处理的集合
	 * @param percent 需要加的百分比
	 * @return
	 */
	public List<Map<String, Object>> provinceRank_Major(List<Map<String, Object>> list, Double percent, String text) {
		if (list.size() <= 0) { // 如果集合中没有数据
			return Collections.EMPTY_LIST;
		}
		for (Map<String, Object> map : list) {
			// 判断该省是否字符串中的省
			if (text.contains(map.get("province").toString())) { // 若是，省会城市学校排名+（筛选结果总数*percent%）
				map.put("rank", (int) (Double.parseDouble(map.get("rank").toString()) - (list.size() * percent)));
			}
		}
		return list;
	}
	
	/**
	 * 对学校专业排名重新进行升序排序
	 * @param list
	 * @return
	 */
	public List<Map<String, Object>> reSort_Major(List<Map<String, Object>> list) {
		if (list.size() <= 0) { // 如果集合中没有数据
			return Collections.EMPTY_LIST;
		}
		Collections.sort(list, new Comparator<Map<String, Object>>(){
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return (int)o1.get("rank") - (int)o2.get("rank");
			}  
		}); 
		return list;
	}
}

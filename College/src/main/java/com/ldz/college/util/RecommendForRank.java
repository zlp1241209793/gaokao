package com.ldz.college.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 为排名推荐学校
 * @author 1241209793
 *
 */
public class RecommendForRank {
	/**
	 * 处理排名的方法
	 * @param rank 当前用户排名
	 * @return
	 */
	public Map<String, Object> dealWithRank(Integer rank) {
		Integer maxRank = 0; // 排名向上浮动后的值
		Integer minRank = 0; // 排名向下浮动后的值

		if (rank <= 100) { // 如果用户的排名在100及以内,直接推荐前10的学校
			minRank = 66;
			maxRank = 2000;
		} else if (rank > 100 && rank <= 1000) { 
			//101~1000    取参考排名区间±15%的院校。
			//激进方案：参考排名=实际排名*85%
			minRank = (int) Math.round(rank * 0.85); // 四舍五入
			//普通方案：参考排名=实际排名
			//保守方案：参考排名=实际排名*115%
			maxRank = (int) Math.round(rank * 1.15); // 四舍五入
		} else if (rank > 1000 && rank <= 50000) {
			//1001~50000    取参考排名区间±10%的院校
			//激进方案：参考排名=实际排名*90%
			minRank = (int) Math.round(rank * 0.9); // 四舍五入
			//普通方案：参考排名=实际排名
			//保守方案：参考排名=实际排名*110%
			maxRank = (int) Math.round(rank * 1.1); // 四舍五入
		} else if (rank > 50000 && rank <= 100000) {
			//50001~100000    取参考排名区间±7.5%的院校
			//激进方案：参考排名=实际排名*92.5%
			minRank = (int) Math.round(rank * 0.925); // 四舍五入
			//普通方案：参考排名=实际排名
			//保守方案：参考排名=实际排名*107.5%
			maxRank = (int) Math.round(rank * 1.075); // 四舍五入
		} else if (rank > 100000 && rank <= 200000) {
			//100001~200000    取参考排名区间±5%的院校
			//激进方案：参考排名=实际排名*95%
			minRank = (int) Math.round(rank * 0.95); // 四舍五入
			//普通方案：参考排名=实际排名
			//保守方案：参考排名=实际排名*105%
			maxRank = (int) Math.round(rank * 1.05); // 四舍五入
		} else {
			//200000~+∞    取参考排名区间±2%的院校
			//激进方案：参考排名=实际排名*98%
			minRank = (int) Math.round(rank * 0.98); // 四舍五入
			//普通方案：参考排名=实际排名
			//保守方案：参考排名=实际排名*102%
			maxRank = (int) Math.round(rank * 1.02); // 四舍五入
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("minRank", minRank);
		map.put("maxRank", maxRank);
		map.put("rank", rank);
		
		return map;
	}
	
	/**
	 * 学校去重，优先保留排名高的
	 * @param list
	 * @return
	 */
	public List<Map<String, Object>> listDeduplication(List<Map<String, Object>> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).get("school_name").equals(list.get(i).get("school_name"))) {
					list.remove(j);
				}
			}
		}
		return list;
	}
	
	/**
	 * 最终推荐的学校列表
	 * @param list
	 * @return
	 */
	public List<Map<String, Object>> finalRecommend(List<Map<String, Object>> list) {
		if (list.size() <= 0) { // 如果list为空
			return Collections.EMPTY_LIST;
		}
		
		if (list.size() <= 10) { // 如果总学校数量小于10个，不做处理
			return list;
		} else { // 前3倒数3加中间4个
			List<Map<String, Object>> new_list = new ArrayList<Map<String,Object>>();
			Integer count = list.size();
			Integer avg = count / 2;
			
			Collections.addAll(new_list, list.get(0), list.get(1), list.get(2), list.get(avg-2), list.get(avg-1), list.get(avg), list.get(avg+1), list.get(count-3), list.get(count-2) ,list.get(count-1));
			return new_list;
		}
	}
}

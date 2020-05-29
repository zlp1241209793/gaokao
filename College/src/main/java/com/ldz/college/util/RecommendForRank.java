package com.ldz.college.util;

import java.util.HashMap;
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
			minRank = 0;
			maxRank = 10;
		} else if (rank > 100 && rank <= 1000) { 
			//101~1000    取参考排名区间±15%的院校。
			//激进方案：参考排名=实际排名*85%
			minRank = (int) Math.round(rank * 0.85); // 四舍五入
			//普通方案：参考排名=实际排名
			//保守方案：参考排名=实际排名*115%
			maxRank = (int) Math.round(rank * 1.15); // 四舍五入
		} else if (rank > 1000 && rank <= 40000) {
			//1001~40000    取参考排名区间±10%的院校
			//激进方案：参考排名=实际排名*90%
			minRank = (int) Math.round(rank * 0.9); // 四舍五入
			//普通方案：参考排名=实际排名
			//保守方案：参考排名=实际排名*110%
			maxRank = (int) Math.round(rank * 1.1); // 四舍五入
		} else if (rank > 40000 && rank <= 100000) {
			//40001~100000    取参考排名区间±5%的院校
			//激进方案：参考排名=实际排名*95%
			minRank = (int) Math.round(rank * 0.95); // 四舍五入
			//普通方案：参考排名=实际排名
			//保守方案：参考排名=实际排名*105%
			maxRank = (int) Math.round(rank * 1.05); // 四舍五入
		} else {
			//100000~+∞    取参考排名区间±3%的院校
			//激进方案：参考排名=实际排名*97%
			minRank = (int) Math.round(rank * 0.97); // 四舍五入
			//普通方案：参考排名=实际排名
			//保守方案：参考排名=实际排名*103%
			maxRank = (int) Math.round(rank * 1.03); // 四舍五入
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("minRank", minRank);
		map.put("maxRank", maxRank);
		
		return map;
	}
}

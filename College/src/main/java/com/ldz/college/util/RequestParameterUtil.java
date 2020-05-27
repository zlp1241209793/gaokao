package com.ldz.college.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestParameterUtil {
	/**
	 * 将请求中的参数封装成map对象
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getRequestParameter(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Enumeration<String> params = request.getParameterNames();
		String key = null;
		while (params.hasMoreElements()) {
			key = params.nextElement();
			map.put(key, request.getParameter(key));
		}
		return map;
	}
	
	public static Map<String, Object> pageParam(Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", (page - 1) * rows);
		map.put("rows", rows);
		return map;
	}
	
	public static Map<String, Object> pageParam(Map<String, Object> map) {
		int page = Integer.parseInt(String.valueOf(map.get("page")));
		int rows = Integer.parseInt(String.valueOf(map.get("rows")));
		map.put("page", (page - 1) * rows);
		map.put("rows", rows);
		return map;
	}
	
	public static Map<String, Object> lucenePageParam(Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page * rows);
		map.put("rows", rows);
		return map;
	}
}

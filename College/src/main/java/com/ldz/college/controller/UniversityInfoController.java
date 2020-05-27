package com.ldz.college.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ldz.college.biz.IUniversityInfoBiz;
import com.ldz.college.util.ResponseUtil;
import com.ldz.college.util.StringUtil;

@RestController
@RequestMapping("/uni")
public class UniversityInfoController {
	@Autowired 
	private IUniversityInfoBiz universityInfoBiz;

	@RequestMapping("/findByPage")
	public Map<String, Object> findByPage(Integer page, @RequestParam(value = "limit")Integer rows, String searchParams) {
		String text = "";
		if (!StringUtil.checkNull(searchParams)) { // 如果searchParams为空
			// 搜索框的内容
			// {"shortName":"特瑞特","uname":"特特","place":"特瑞特","tags":"特"}
			text = searchParams.replaceAll("\"[a-z]*[A-Z]*[a-z]*\":", "").replace("{\"", "").replace("\"}", "").replace("\",\"", ""); // {"特瑞特","特特","特瑞特","特"}  特瑞特特特特瑞特特
		}
		
		if (StringUtil.checkNull(text)) { // 如果搜索框的内容为空，则执行不带参数的分页查询
			return ResponseUtil.responseMap(0, null, Integer.parseInt(universityInfoBiz.getCount()), universityInfoBiz.findByPage(page, rows));
		} else { // 如果有参数，则执行lucene查询
			Map<String, Object> luceneMap = universityInfoBiz.luceneSearch(text, page, rows);
			return ResponseUtil.responseMap(0, null, Integer.parseInt(luceneMap.get("count").toString()), luceneMap.get("lucene"));
		}
	}
}

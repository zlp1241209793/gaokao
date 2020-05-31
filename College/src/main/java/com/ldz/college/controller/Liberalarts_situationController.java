package com.ldz.college.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldz.college.biz.ILiberalarts_situationBiz;
import com.ldz.college.biz.IMemberInfoBiz;
import com.ldz.college.biz.IWeightInfoBiz;
import com.ldz.college.entity.MemberInfo;
import com.ldz.college.entity.WeightInfo;
import com.ldz.college.util.DealWithRank;
import com.ldz.college.util.RecommendForRank;
import com.ldz.college.util.StringUtil;

@RestController
@RequestMapping("/liberalarts")
public class Liberalarts_situationController {
	@Autowired 
	private ILiberalarts_situationBiz liberalarts_situationBiz;
	
	@Autowired
	private IMemberInfoBiz memberInfoBiz;
	
	@Autowired
	private IWeightInfoBiz weightInfoBiz;
	
	@Autowired(required = false)
	private RecommendForRank recommendForRank;
	
	@Autowired (required = false)
	private DealWithRank dealWithRank;
	
	@RequestMapping("/recommendForRank")
	public List<Map<String, Object>> recommendForRank(String mno) {
		if (StringUtil.checkNull(mno)) { // 如果用户编号为空，直接return
			return Collections.EMPTY_LIST;
		}
		
		// 查询用户信息
		MemberInfo memberInfo = memberInfoBiz.findAll(mno);
		if (StringUtil.checkNull(memberInfo.getRanking(), memberInfo.getProvince(), memberInfo.getMajor())) { // 如果用户还未填写排名、地域、偏向专业,直接return
			return Collections.EMPTY_LIST;
		}
		
		// 通过用户的排名,处理后得到学校的排名范围
		Map<String, Object> map = recommendForRank.dealWithRank(memberInfo.getRanking()); // 处理后的数据用map存起来 maxRank: 排名向上浮动后的值   minRank: 排名向下浮动后的值
		
		// 查询用户的权重信息
		WeightInfo weightInfo = weightInfoBiz.findByMno(mno);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (StringUtil.checkNull(weightInfo.getLocation(), weightInfo.getMajor())) { // 如果用户还未设置权重,直接return
			return Collections.EMPTY_LIST;
		} else if (weightInfo.getLocation() > weightInfo.getMajor() && weightInfo.getLocation() > weightInfo.getRanking()) { // 如果用户的地域权重最大
			map.put("location", memberInfo.getProvince()); // 将用户的地域取出来存到map里
			// 采用地域优先算法
			list = liberalarts_situationBiz.locationRank(map); // 查出所有符合地域条件的学校
			// 所有的省会城市
			String city = "北京市天津市上海市重庆市石家庄市太原市呼和浩特市沈阳市长春市哈尔滨市南京市杭州市合肥市福州市南昌市济南市郑州市武汉市长沙市广州市南宁市海口市成都市贵阳市昆明市拉萨市西安市兰州市西宁市银川市乌鲁木齐市台北市";
			list = dealWithRank.cityRank_Displacement(list, 0.2, city); // 对省会城市的排名做处理 排名+20%
			
			list = dealWithRank.reSort_Displacement(list); // 对学校排名重新进行升序排序
			
			list = recommendForRank.listDeduplication(list); // 学校去重
			
			list = recommendForRank.finalRecommend(list); // 最终推荐的学校列表
		} else if (weightInfo.getMajor() > weightInfo.getLocation() && weightInfo.getMajor() > weightInfo.getRanking()) { // 如果用户的专业权重最大
			map.put("major", memberInfo.getMajor()); // 将用户的偏向专业取出来存到map里
			// 采用专业优先算法
			list = liberalarts_situationBiz.majorRank(map); // 查出所有符合专业条件的学校
			// 所有的省会城市
			String city = "北京市天津市上海市重庆市石家庄市太原市呼和浩特市沈阳市长春市哈尔滨市南京市杭州市合肥市福州市南昌市济南市郑州市武汉市长沙市广州市南宁市海口市成都市贵阳市昆明市拉萨市西安市兰州市西宁市银川市乌鲁木齐市台北市";
			list = dealWithRank.cityRank_Major(list, 0.1, city); // 对省会城市的排名做处理 排名+10%
			// 对特殊的省做处理
			city = "北京市上海市天津市河北省山西省内蒙古自治区湖北省湖南省河南省江西省广东省广西省海南省四川省";
			list = dealWithRank.provinceRank_Major(list, 0.1, city); // 北京、上海地区、华北、华中、华南、四川地区学校排名+（筛选结果总数*10%） 排名+10%
			city = "辽宁省吉林省黑龙江省贵州省云南省";
			list = dealWithRank.provinceRank_Major(list, 0.05, city); // 东北地区及云贵地区学校排名+（筛选结果总数*5%）
			
			list = dealWithRank.reSort_Major(list);// 对学校排名重新进行升序排序
			
			list = recommendForRank.listDeduplication(list); // 学校去重
			
			list = recommendForRank.finalRecommend(list); // 最终推荐的学校列表
		} else if (weightInfo.getRanking() > weightInfo.getMajor() && weightInfo.getRanking() > weightInfo.getLocation()) { // 如果用户的学校权重最大
			// 采用学校优先算法
			list = liberalarts_situationBiz.schoolRank(map); // 查出所有符合专业条件的学校
			// 所有的省会城市
			String city = "北京市天津市上海市重庆市石家庄市太原市呼和浩特市沈阳市长春市哈尔滨市南京市杭州市合肥市福州市南昌市济南市郑州市武汉市长沙市广州市南宁市海口市成都市贵阳市昆明市拉萨市西安市兰州市西宁市银川市乌鲁木齐市台北市";
			list = dealWithRank.cityRank_Displacement(list, 0.1, city); // 省会城市学校排名+（筛选结果总数*10%）
			// 对特殊的省做处理
			city = "北京市上海市天津市河北省山西省内蒙古自治区湖北省湖南省河南省江西省广东省广西省海南省四川省";
			list = dealWithRank.provinceRank_Displacement(list, 0.1, city); // 北京、上海地区、华北、华中、华南、四川地区学校排名+（筛选结果总数*10%） 排名+10%
			city = "辽宁省吉林省黑龙江省贵州省云南省";
			list = dealWithRank.provinceRank_Displacement(list, 0.05, city); // 东北地区及云贵地区学校排名+（筛选结果总数*5%）
			
			list = dealWithRank.reSort_Displacement(list);// 对学校排名重新进行升序排序
			
			list = recommendForRank.listDeduplication(list); // 学校去重
			
			list = recommendForRank.finalRecommend(list); // 最终推荐的学校列表
		}
		return list;
	}
}

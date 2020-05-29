package com.ldz.college.entity;

import java.io.Serializable;

public class Science_situation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer school_id;
	private Integer school_code; // 学校代码
	private String school_name; // 学校名
	private Integer plan_num; // 计划人数
	private Integer throw_num; // 投档人数
	private Integer score; // 分数
	private Integer displacement; // 最低排位
	private Integer year; // 年份
	private String region; // 地区
	private Integer batch; // 批次
	private String province; // 省份
	private String city; // 市区
	
	@Override
	public String toString() {
		return "Science_situation [school_id=" + school_id + ", school_code=" + school_code + ", school_name="
				+ school_name + ", plan_num=" + plan_num + ", throw_num=" + throw_num + ", score=" + score
				+ ", displacement=" + displacement + ", year=" + year + ", region=" + region + ", batch=" + batch
				+ ", province=" + province + ", city=" + city + "]";
	}

	public Integer getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}

	public Integer getSchool_code() {
		return school_code;
	}

	public void setSchool_code(Integer school_code) {
		this.school_code = school_code;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public Integer getPlan_num() {
		return plan_num;
	}

	public void setPlan_num(Integer plan_num) {
		this.plan_num = plan_num;
	}

	public Integer getThrow_num() {
		return throw_num;
	}

	public void setThrow_num(Integer throw_num) {
		this.throw_num = throw_num;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Integer displacement) {
		this.displacement = displacement;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getBatch() {
		return batch;
	}

	public void setBatch(Integer batch) {
		this.batch = batch;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((displacement == null) ? 0 : displacement.hashCode());
		result = prime * result + ((plan_num == null) ? 0 : plan_num.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((school_code == null) ? 0 : school_code.hashCode());
		result = prime * result + ((school_id == null) ? 0 : school_id.hashCode());
		result = prime * result + ((school_name == null) ? 0 : school_name.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + ((throw_num == null) ? 0 : throw_num.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Science_situation other = (Science_situation) obj;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (displacement == null) {
			if (other.displacement != null)
				return false;
		} else if (!displacement.equals(other.displacement))
			return false;
		if (plan_num == null) {
			if (other.plan_num != null)
				return false;
		} else if (!plan_num.equals(other.plan_num))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (school_code == null) {
			if (other.school_code != null)
				return false;
		} else if (!school_code.equals(other.school_code))
			return false;
		if (school_id == null) {
			if (other.school_id != null)
				return false;
		} else if (!school_id.equals(other.school_id))
			return false;
		if (school_name == null) {
			if (other.school_name != null)
				return false;
		} else if (!school_name.equals(other.school_name))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (throw_num == null) {
			if (other.throw_num != null)
				return false;
		} else if (!throw_num.equals(other.throw_num))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
}

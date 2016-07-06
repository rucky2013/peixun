package com.ync365.px.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.ync365.px.entity.ScoreSetting;

public class ScoreYearModel implements Comparable<ScoreYearModel> {
	
	String year;
	Long userid;
	String username;
	String departmentName;
	String parentDepartmentName;
	Float scoreCount = 0.0f;//积分汇总
	Float periodCount = 0.0f;//学时汇总
	Float scoreCountShould = 0.0f;//年度应得积分
	Float scoreCountTeacher=0.0f;//讲师所得积分
	Float periodCountDepartment = 0.0f;
	List<ScoreMonthModel> scoreMonthModels;
	ScoreSetting scoreSetting;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public List<ScoreMonthModel> getScoreMonthModels() {
		return scoreMonthModels;
	}

	public void setScoreMonthModels(List<ScoreMonthModel> scoreMonthModels) {
		this.scoreMonthModels = scoreMonthModels;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	

	public String getParentDepartmentName() {
		return parentDepartmentName;
	}

	public void setParentDepartmentName(String parentDepartmentName) {
		this.parentDepartmentName = parentDepartmentName;
	}
	

	public Float getScoreCount() {
		return scoreCount;
	}

	public void setScoreCount(Float scoreCount) {
		this.scoreCount = scoreCount;
	}

	public Float getPeriodCount() {
		return periodCount;
	}

	public void setPeriodCount(Float periodCount) {
		this.periodCount = periodCount;
	}

	public Float getPeriodCountDepartment() {
		return periodCountDepartment;
	}

	public void setPeriodCountDepartment(Float periodCountDepartment) {
		this.periodCountDepartment = periodCountDepartment;
	}
	
	public Float getScoreCountShould() {
		return scoreCountShould;
	}

	public void setScoreCountShould(Float scoreCountShould) {
		this.scoreCountShould = scoreCountShould;
	}

	
	public ScoreSetting getScoreSetting() {
		return scoreSetting;
	}

	public void setScoreSetting(ScoreSetting scoreSetting) {
		this.scoreSetting = scoreSetting;
	}
	

	public Float getScoreCountTeacher() {
		return scoreCountTeacher;
	}

	public void setScoreCountTeacher(Float scoreCountTeacher) {
		this.scoreCountTeacher = scoreCountTeacher;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);

	}

	@Override
	public int compareTo(ScoreYearModel o) {
		// TODO Auto-generated method stub
		if (parentDepartmentName.equals(o.getParentDepartmentName()) && departmentName.equals(o.getDepartmentName())) {
			return 0;
		} else {
			return -1;
		}
	}
	
	
	
}

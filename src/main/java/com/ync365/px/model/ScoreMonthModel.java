package com.ync365.px.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.ync365.px.entity.ScoreSetting;

public class ScoreMonthModel implements Comparable<ScoreMonthModel> {
	
	Date dateMonth;
	Integer month;
	Long userid;
	String username;
	String departmentName;
	String parentDepartmentName;
	Float scoreCount = 0.0f;
	Float scoreCountTeacher = 0.0f;
	Float scoreCountShould = 0.0f;
	Float periodCount = 0.0f;
	Float periodCountDepartment = 0.0f;
	Float periodCountTeacher = 0.0f;
	
	List<ScoreModel> scoreModel;
	ScoreSetting scoreSetting;
	
	
	public List<ScoreModel> getScoreModel() {
		return scoreModel;
	}
	public void setScoreModel(List<ScoreModel> scoreModel) {
		this.scoreModel = scoreModel;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Date getDateMonth() {
		return dateMonth;
	}
	public void setDateMonth(Date dateMonth) {
		this.dateMonth = dateMonth;
	}
	
	public Float getScoreCount() {
		return scoreCount;
	}
	public void setScoreCount(Float scoreCount) {
		this.scoreCount = scoreCount;
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
	public Float getScoreCountTeacher() {
		return scoreCountTeacher;
	}
	public void setScoreCountTeacher(Float scoreCountTeacher) {
		this.scoreCountTeacher = scoreCountTeacher;
	}
	public Float getPeriodCountTeacher() {
		return periodCountTeacher;
	}
	public void setPeriodCountTeacher(Float periodCountTeacher) {
		this.periodCountTeacher = periodCountTeacher;
	}
	
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
	@Override
	public int compareTo(ScoreMonthModel o) {
		if (this.month > o.getMonth()) {
			return 1;
		} else if (this.month < o.getMonth()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.month.equals(((ScoreMonthModel)obj).getMonth()) && (!((ScoreMonthModel)obj).getMonth().equals(-1));
	}
	
	
	
}

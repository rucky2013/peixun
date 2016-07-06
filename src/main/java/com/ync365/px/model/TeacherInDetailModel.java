package com.ync365.px.model;

import java.util.Date;

public class TeacherInDetailModel {

	private String name;
	private String teacherName;
	private Date beginTime;
	private Date endTime;
	private int teacherType;
	private double classHour; 
	private double teacherScore; 
	private double payClassScore;
	private String projectName;// 项目名称
	private Date projectStartTime;// 项目开始时间
	private Date projectEndTime;// 项目结束时间
	private int projectLevel;// 项目级别
	private String parentDepartment; // 一级部门
	private String department;// 二级部门

	public double getTeacherScore() {
		return teacherScore;
	}

	public void setTeacherScore(double teacherScore) {
		this.teacherScore = teacherScore;
	}

	 
	public double getPayClassScore() {
		return payClassScore;
	}

	public void setPayClassScore(double payClassScore) {
		this.payClassScore = payClassScore;
	}

	
	public String getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(String parentDepartment) {
		this.parentDepartment = parentDepartment;
	}
	public double getClassHour() {
		return classHour;
	}

	public void setClassHour(double classHour) {
		this.classHour = classHour;
	}
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getProjectStartTime() {
		return projectStartTime;
	}

	public void setProjectStartTime(Date projectStartTime) {
		this.projectStartTime = projectStartTime;
	}

	public Date getProjectEndTime() {
		return projectEndTime;
	}

	public void setProjectEndTime(Date projectEndTime) {
		this.projectEndTime = projectEndTime;
	}

	public int getProjectLevel() {
		return projectLevel;
	}

	public void setProjectLevel(int projectLevel) {
		this.projectLevel = projectLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	 

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(int teacherType) {
		this.teacherType = teacherType;
	}

	@Override
	public String toString() {
		return "TeacherInDetailModel [name=" + name + ", teacherName="
				+ teacherName + ", beginTime=" + beginTime + ", endTime="
				+ endTime + ", teacherType=" + teacherType + ", classHour="
				+ classHour + ", teacherScore=" + teacherScore
				+ ", payClassScore=" + payClassScore + ", projectName="
				+ projectName + ", projectStartTime=" + projectStartTime
				+ ", projectEndTime=" + projectEndTime + ", projectLevel="
				+ projectLevel + ", parentDepartment=" + parentDepartment
				+ ", department=" + department + "]";
	}

	 

	 

}

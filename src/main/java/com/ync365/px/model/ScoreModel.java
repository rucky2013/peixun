package com.ync365.px.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.ync365.px.entity.ScoreSetting;

public class ScoreModel {
	
	private Long id;
	private Float should = 0.0f;//应得积分
	private Float real = 0.0f;//实得积分
	private Float period = 0.0f;//学生课时
	private Float periodShould = 0.0f;//学生应上课时
	private Float periodTeacher = 0.0f;//老师课时
	private Float teacherScore = 0.0f;//老师积分
	private Long projectId;
	private String projectName;
	private Date projectStartTime;
	private Date projectEndTime;
	private Integer projectLevel;
	private Long userId;
	private String userName;
	private Long departmentId;
	private String departmentName;
	private Long parentDepartmentId;
	private String parentDepartmentName;
	private List<ClassModel> classes;
	private ScoreSetting scoreSetting;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getShould() {
		return should;
	}
	public void setShould(Float should) {
		this.should = should;
	}
	public Float getReal() {
		return real;
	}
	public void setReal(Float real) {
		this.real = real;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Long getParentDepartmentId() {
		return parentDepartmentId;
	}
	public void setParentDepartmentId(Long parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}
	public String getParentDepartmentName() {
		return parentDepartmentName;
	}
	public void setParentDepartmentName(String parentDepartmentName) {
		this.parentDepartmentName = parentDepartmentName;
	}
	
	public Date getProjectEndTime() {
		return projectEndTime;
	}
	public void setProjectEndTime(Date projectEndTime) {
		this.projectEndTime = projectEndTime;
	}
	
	public Float getPeriod() {
		return period;
	}
	public void setPeriod(Float period) {
		this.period = period;
	}
	
	public Integer getProjectLevel() {
		return projectLevel;
	}
	public void setProjectLevel(Integer projectLevel) {
		this.projectLevel = projectLevel;
	}
	
	public Float getTeacherScore() {
		return teacherScore;
	}
	public void setTeacherScore(Float teacherScore) {
		this.teacherScore = teacherScore;
	}
	public Float getPeriodTeacher() {
		return periodTeacher;
	}
	public void setPeriodTeacher(Float periodTeacher) {
		this.periodTeacher = periodTeacher;
	}
	
	public List<ClassModel> getClasses() {
		return classes;
	}
	public void setClasses(List<ClassModel> classes) {
		this.classes = classes;
	}
	public Date getProjectStartTime() {
		return projectStartTime;
	}
	public void setProjectStartTime(Date projectStartTime) {
		this.projectStartTime = projectStartTime;
	}
	
	public ScoreSetting getScoreSetting() {
		return scoreSetting;
	}
	public void setScoreSetting(ScoreSetting scoreSetting) {
		this.scoreSetting = scoreSetting;
	}
	
	public Float getPeriodShould() {
		return periodShould;
	}
	public void setPeriodShould(Float periodShould) {
		this.periodShould = periodShould;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

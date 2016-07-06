package com.ync365.px.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ClassModel {
	
	private Long id;
	private String name;
	private Long projectid;
	private String teacherName;
	private Long teacherid;
	private Integer teacherType;
	private Float teacherScore;
	private Float teacherScoreShould;
	private Float peroidTeacherShould = 0.0f;
	private Float peroidTeacher = 0.0f;
	private Date startTime;
	private Date endTime;
	private Float period = 0.0f;
	private Float should = 0.0f;
	private Float real = 0.0f;
	private Float realPeriod = 0.0f;
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Long getProjectid() {
		return projectid;
	}



	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}



	public String getTeacherName() {
		return teacherName;
	}



	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	


	public Long getTeacherid() {
		return teacherid;
	}



	public void setTeacherid(Long teacherid) {
		this.teacherid = teacherid;
	}



	public Integer getTeacherType() {
		return teacherType;
	}



	public void setTeacherType(Integer teacherType) {
		this.teacherType = teacherType;
	}



	public Float getTeacherScore() {
		return teacherScore;
	}



	public void setTeacherScore(Float teacherScore) {
		this.teacherScore = teacherScore;
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


	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



	public Date getEndTime() {
		return endTime;
	}



	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	


	public Float getPeriod() {
		return period;
	}



	public void setPeriod(Float period) {
		this.period = period;
	}



	public Float getRealPeriod() {
		return realPeriod;
	}



	public void setRealPeriod(Float realPeriod) {
		this.realPeriod = realPeriod;
	}

	

	public Float getPeroidTeacher() {
		return peroidTeacher;
	}


	public void setPeroidTeacher(Float peroidTeacher) {
		this.peroidTeacher = peroidTeacher;
	}
	
	

	public Float getPeroidTeacherShould() {
		return peroidTeacherShould;
	}


	public void setPeroidTeacherShould(Float peroidTeacherShould) {
		this.peroidTeacherShould = peroidTeacherShould;
	}
	
	

	public Float getTeacherScoreShould() {
		return teacherScoreShould;
	}


	public void setTeacherScoreShould(Float teacherScoreShould) {
		this.teacherScoreShould = teacherScoreShould;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

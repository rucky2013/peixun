/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ync365.px.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

//JPA标识
@Entity
@Table(name = "px_class")
public class PxClass extends IdEntity {

	//private String name;
	private String className;
	private Integer projectId;
	
	private Integer teacherId;
	
	private String teacherName;
	
	private Double teacherScore;
	
	private Date beginTime;
	
	private Date endTime;
	
	private Integer status;
	
	private Date createTime;

	private int teacherType;

	private float classHour;
	
	private double payClassScore;
	
	private float studentScore;
	
	private float sclassHour;
    
    private double steacherScore;
    
    private double degree;
	
    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public double getSteacherScore() {
        return steacherScore;
    }

    public void setSteacherScore(double steacherScore) {
        this.steacherScore = steacherScore;
    }

    public float getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(float studentScore) {
        this.studentScore = studentScore;
    }

    public double getPayClassScore() {
		return payClassScore;
	}

	public void setPayClassScore(double payClassScore) {
		this.payClassScore = payClassScore;
	}

	/*public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
*/
	
	
	public Integer getProjectId() {
		return projectId;
	}

	

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(name="name")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Double getTeacherScore() {
		return teacherScore;
	}

	public void setTeacherScore(Double teacherScore) {
		this.teacherScore = teacherScore;
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

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public int getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(int teacherType) {
		this.teacherType = teacherType;
	}

    public float getClassHour() {
        return classHour;
    }

    public void setClassHour(float classHour) {
        this.classHour = classHour;
    }

    public float getSclassHour() {
        return sclassHour;
    }

    public void setSclassHour(float sclassHour) {
        this.sclassHour = sclassHour;
    }

}

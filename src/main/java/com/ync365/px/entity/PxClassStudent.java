/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ync365.px.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

//JPA标识
@Entity
@Table(name = "px_class_student")
public class PxClassStudent extends IdEntity {

	private Integer classId;
	
	private Integer user_id;
	
	private String user_name;
	
    private Float should;
	
	private Float reals;
	
	private Float s_score;
	
	private Float score;
	
	private Integer come;
	
	private String mark;
	
	public String getUser_name() {
	    return user_name;
	}
	
	public void setUser_name(String user_name) {
	    this.user_name = user_name;
	}

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Float getShould() {
        return should;
    }

    public void setShould(Float should) {
        this.should = should;
    }

    public Float getReals() {
        return reals;
    }

    public void setReals(Float reals) {
        this.reals = reals;
    }

    public Float getS_score() {
        return s_score;
    }

    public void setS_score(Float s_score) {
        this.s_score = s_score;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getCome() {
        return come;
    }

    public void setCome(Integer come) {
        this.come = come;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

}

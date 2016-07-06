package com.ync365.px.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the px_score_setting database table.
 * 
 */
@Entity
@Table(name="px_score_setting")
@NamedQuery(name="ScoreSetting.findAll", query="SELECT s FROM ScoreSetting s")
public class ScoreSetting extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer year;
	private Float score_1;
	private Float score_2;
	private Float score_3;
	private Float score_4;
	private Float score_5;
	private Float score_6;
	private Float score_7;
	private Float score_8;
	private Float score_9;
	private Float score_10;
	private Float score_11;
	private Float score_12;
	private Long userid;
	private String username;
	private String departmentName;
	private String parentDepartmentName;
	public ScoreSetting() {
	}


	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	

	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public Float getScore_1() {
		return score_1;
	}


	public void setScore_1(Float score_1) {
		this.score_1 = score_1;
	}


	public Float getScore_2() {
		return score_2;
	}


	public void setScore_2(Float score_2) {
		this.score_2 = score_2;
	}


	public Float getScore_3() {
		return score_3;
	}


	public void setScore_3(Float score_3) {
		this.score_3 = score_3;
	}


	public Float getScore_4() {
		return score_4;
	}


	public void setScore_4(Float score_4) {
		this.score_4 = score_4;
	}


	public Float getScore_5() {
		return score_5;
	}


	public void setScore_5(Float score_5) {
		this.score_5 = score_5;
	}


	public Float getScore_6() {
		return score_6;
	}


	public void setScore_6(Float score_6) {
		this.score_6 = score_6;
	}


	public Float getScore_7() {
		return score_7;
	}


	public void setScore_7(Float score_7) {
		this.score_7 = score_7;
	}


	public Float getScore_8() {
		return score_8;
	}


	public void setScore_8(Float score_8) {
		this.score_8 = score_8;
	}


	public Float getScore_9() {
		return score_9;
	}


	public void setScore_9(Float score_9) {
		this.score_9 = score_9;
	}


	public Float getScore_10() {
		return score_10;
	}


	public void setScore_10(Float score_10) {
		this.score_10 = score_10;
	}


	public Float getScore_11() {
		return score_11;
	}


	public void setScore_11(Float score_11) {
		this.score_11 = score_11;
	}


	public Float getScore_12() {
		return score_12;
	}


	public void setScore_12(Float score_12) {
		this.score_12 = score_12;
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

	
	

}
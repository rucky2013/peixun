package com.ync365.px.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the px_score database table.
 * 
 */
@Entity
@Table(name="px_score")
@NamedQuery(name="Score.findAll", query="SELECT s FROM Score s")
public class Score extends com.ync365.px.entity.IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date lastUpdated;
	private String mark;
	private long projectStudentId;
	private float score;
	private String status;
	private int type;

	public Score() {
	}

	@Column(name="last_updated")
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}


	@Column(name="project_student_id")
	public long getProjectStudentId() {
		return this.projectStudentId;
	}

	public void setProjectStudentId(long projectStudentId) {
		this.projectStudentId = projectStudentId;
	}


	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}


	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
package com.ync365.px.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the px_train database table.
 * 
 */
@Entity
@Table(name = "px_train")
@NamedQuery(name = "Train.findAll", query = "SELECT t FROM Train t")
public class Train extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long fkSubjectId;
	private long fkUserId;
	private Date lastUpdated;
	private String mark;
	private float real;
	private float should;
	private int status;

	public Train() {
	}

	@Column(name = "fk_subject_id")
	public long getFkSubjectId() {
		return this.fkSubjectId;
	}

	public void setFkSubjectId(long fkSubjectId) {
		this.fkSubjectId = fkSubjectId;
	}

	@Column(name = "fk_user_id")
	public long getFkUserId() {
		return this.fkUserId;
	}

	public void setFkUserId(long fkUserId) {
		this.fkUserId = fkUserId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated")
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Lob
	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public float getReal() {
		return this.real;
	}

	public void setReal(float real) {
		this.real = real;
	}

	public float getShould() {
		return this.should;
	}

	public void setShould(float should) {
		this.should = should;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
package com.ync365.px.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "px_project_student")
public class PxProjectStudent  extends IdEntity{
	
	private Integer projectId;
	
	private Integer projectLevel;
	
	private Integer userId;
	
	private String userName;
	
	private Date createTime;

	public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectLevel() {
		return projectLevel;
	}

	public void setProjectLevel(Integer projectLevel) {
		this.projectLevel = projectLevel;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

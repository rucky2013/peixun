package com.ync365.px.model.bo;

import java.util.Date;

public class PxProjectBo {

    private Long id;
    
    private String name;

    private Integer tagId;

    private Integer projectLevel;

    private Date beginTime;

    private Date endTime;

    private Integer ticketType;
    
    private Double totalStudentScore;
    
    private Integer ticketNum;
    
    private Integer status;
    
	private Integer createUserId;
	
	private String content;

    private Date ticketTime;

    public Date getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(Date ticketTime) {
        this.ticketTime = ticketTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Double getTotalStudentScore() {
        return totalStudentScore;
    }

    public void setTotalStudentScore(Double totalStudentScore) {
        this.totalStudentScore = totalStudentScore;
    }

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

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(Integer projectLevel) {
        this.projectLevel = projectLevel;
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

    public Integer getTicketType() {
        return ticketType;
    }

    public void setTicketType(Integer ticketType) {
        this.ticketType = ticketType;
    }

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
    
    
}

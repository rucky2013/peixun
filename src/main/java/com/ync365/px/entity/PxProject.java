package com.ync365.px.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "px_project")
public class PxProject extends IdEntity{
	
	private String name;
	
	private Integer tagId;
	
	private Integer projectLevel;
	
	private Date beginTime;
	
	private Date endTime;
	
	private Integer ticketType;
	
	private Date createTime;
	
	private Integer status;
	
	private Integer ticketNum;
	
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
	
    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
     @Column(name="begin_time")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
    @Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
    @Column(name="ticket_type")
	public Integer getTicketType() {
		return ticketType;
	}

	public void setTicketType(Integer ticketType) {
		this.ticketType = ticketType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	@Column(name="cuser_id")
	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
		
}

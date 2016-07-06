package com.ync365.px.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "px_project_tag")
public class PxProjectTag  extends IdEntity{
	private String tagName;
	
	private Integer isUse;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	
	
	
}

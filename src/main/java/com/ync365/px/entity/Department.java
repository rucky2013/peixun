package com.ync365.px.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the px_department database table.
 * 
 */
@Entity
@Table(name="px_department")
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String oaid;
	private Long parent;
	private int status;
	private String parentName;
	private Integer leaf;
	private String parentOaId;
	private String subcompanyId;
	private Integer isCompany;

	public Department() {
	}


	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getOaid() {
		return this.oaid;
	}

	public void setOaid(String oaid) {
		this.oaid = oaid;
	}


	

	public Long getParent() {
		return parent;
	}


	public void setParent(Long parent) {
		this.parent = parent;
	}


	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	public Integer getLeaf() {
		return leaf;
	}


	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public String getParentOaId() {
		return parentOaId;
	}

	public void setParentOaId(String parentOaId) {
		this.parentOaId = parentOaId;
	}



	public String getSubcompanyId() {
		return subcompanyId;
	}



	public void setSubcompanyId(String subcompanyId) {
		this.subcompanyId = subcompanyId;
	}



	public Integer getIsCompany() {
		return isCompany;
	}



	public void setIsCompany(Integer isCompany) {
		this.isCompany = isCompany;
	}

	
}
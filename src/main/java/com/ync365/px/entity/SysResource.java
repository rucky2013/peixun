package com.ync365.px.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysResource implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private Integer type;

    private String url;

    private String permission;

    private Integer parentId;

    private Date createTime;

    private Short sort;
    
    private List<SysResource> children= new ArrayList<SysResource>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }
    
    public List<SysResource> getChildren() {
		return children;
	}
    public void setChildren(List<SysResource> children) {
		this.children = children;
	}
    public void addChildren(SysResource sysResource){
    	this.children.add(sysResource);
    }
}
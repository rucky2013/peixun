package com.ync365.px.model;

public class TeacherOuterModel {
	private Long id;
	private String company;// 公司(单位)
	private String teacherName;// 讲师姓名 
	
	private String introduce;// 讲师简介
	private String phone;// 联系方式
	private String subject;// 课程
	private String post;// 职称
	private String address;// 授课地点
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}*/
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	 
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	
}

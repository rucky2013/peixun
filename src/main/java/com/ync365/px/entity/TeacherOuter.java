package com.ync365.px.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

/*
 * 外部讲师实体
 */
@Entity
@Table(name = "px_teacher_outer")
public class TeacherOuter extends IdEntity {

	private String company;// 公司(单位)
	private String teacherName;// 讲师姓名 
	private String introduce;// 讲师简介
	private String phone;// 联系方式
	private String subject;// 课程
	private String post;// 职称
	private String address;// 授课地点

	public TeacherOuter() {

	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	// JSR303 BeanValidator的校验规则
	@NotBlank
	@Column(name="name")
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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

	@Override
	public String toString() {
		return "TeacherOuter [company=" + company + ", teacherName="
				+ teacherName + ", introduce=" + introduce + ", phone=" + phone
				+ ", subject=" + subject + ", post=" + post + ", address="
				+ address + "]";
	}

	 
}

package com.ync365.px.model;

//内部讲师积分汇总
public class TeacherInSumModel {

	 
	private String name; // 内部讲师姓名
	private String parentDepartment; // 一级部门
	private String department;// 二级部门
	private double classScoreYear; // 年度课时积分 
	private double payScoreYear;// 年度课酬积分 
	private double sumScoreYear; // 年度所得积分 
	
	
	public double getSumScoreYear() {
		return sumScoreYear;
	}
 

	public void setSumScoreYear(double sumScoreYear) {
		this.sumScoreYear = sumScoreYear;
	}

	
    
	public TeacherInSumModel() {
		super();

	}

	public double getPayScoreYear() {
		return payScoreYear;
	} 
	public void setPayScoreYear(double payScoreYear) {
		this.payScoreYear = payScoreYear;
	}


	public double getClassScoreYear() {
		return classScoreYear;
	}

	public void setClassScoreYear(double classScoreYear) {
		this.classScoreYear = classScoreYear;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(String parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	@Override
	public String toString() {
		return "TeacherInSumModel [name=" + name + ", parentDepartment="
				+ parentDepartment + ", department=" + department
				+ ", classScoreYear=" + classScoreYear + ", payScoreYear="
				+ payScoreYear + ", sumScoreYear=" + sumScoreYear + "]";
	}
 
	 
}

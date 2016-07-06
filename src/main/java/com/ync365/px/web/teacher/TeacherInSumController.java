package com.ync365.px.web.teacher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ync365.px.model.Paginator;
import com.ync365.px.model.TeacherInSumModel;
import com.ync365.px.service.classservice.PxClassService;

@Controller
public class TeacherInSumController {

	@Autowired
	private PxClassService pxClassService;

	@RequestMapping(value = "/listdata/teacherinsum/teacherinsumlist")
	public String myData(
			@Valid @ModelAttribute("Paginator") Paginator paginator,// 分页对象
			@RequestParam(value = "company",required=false) String company,
			@RequestParam(value = "name", required=false) String name,
			@RequestParam(value = "year", required=false) String year,
			Model model) {
		  
		List<TeacherInSumModel> teacherInSumModels=null;
		  if( null==year){
		   Date nowDate=new Date();
		     SimpleDateFormat sdf=new SimpleDateFormat("yyyy"); 
		      year = sdf.format(nowDate);
		  }
		  teacherInSumModels = pxClassService.getAllPaginaBySum(paginator,year);
		  
		  
		  if(StringUtils.isBlank(company) && StringUtils.isBlank(name) ){
				//如果公司和姓名为空则就显示所有的内部讲师汇总
			  teacherInSumModels = pxClassService.getAllPaginaBySum(paginator,year);
						 
	           
			}
		  if(StringUtils.isNotBlank(company) ){
				//根据单位查询出所有内部讲师汇总
			 
			  teacherInSumModels = pxClassService
						.getTeacherInSumByCompany(company, paginator,year);
			   
			}
		  
		  if(StringUtils.isNotBlank(name) ){
				//根据姓名查询出所有内部讲师汇总
			 
			  teacherInSumModels = pxClassService
						.getTeacherInSumByName(name, paginator,year);
			  
			}
	         
		  if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(company)){
			  teacherInSumModels = pxClassService
						 .getTeacherInSumByNameAndCompany(name, company, paginator, year);
			  
		  }
		model.addAttribute("teacherInSumModels", teacherInSumModels);
		model.addAttribute("year", year);
		model.addAttribute("paginator", paginator);
		return "/teacher/teacherinsumlist";

	}

	@RequestMapping(value = "/teacherinsum/teacherinsummy")
	public String my(Model model) {
		 Date nowDate=new Date();
	     SimpleDateFormat sdf=new SimpleDateFormat("yyyy"); 
	     String year = sdf.format(nowDate);
	     model.addAttribute("year", year);
		return "teacher/teacherinsummy";

	}

}

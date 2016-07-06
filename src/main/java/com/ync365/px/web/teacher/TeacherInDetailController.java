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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
 
import com.ync365.px.model.Paginator;
import com.ync365.px.model.TeacherInDetailModel;
/*import com.ync365.px.model.TeacherInDetailModel;*/
 
 
import com.ync365.px.service.classservice.PxClassService;

/*import com.ync365.px.service.teacher.TeacherInDetailService;*/
@Controller
public class TeacherInDetailController {

	@Autowired
	private PxClassService pxClassService;

	@RequestMapping(value = "/listdata/teacherindetail/teacherindetaillist")
	public String myData(
			@Valid @ModelAttribute("Paginator") Paginator paginator,// 分页对象
			@RequestParam(value = "company",required=false) String company,
			@RequestParam(value = "name", required=false) String name,
			/*@RequestParam(value = "nameorder", required=false) String nameorder,
			@RequestParam(value = "classorder", required=false) String classorder,*/
			@RequestParam(value = "year", required=false) String year,
			@RequestParam(value = "selectVal", required=false) String selectVal,
			@RequestParam(value = "selectNameVal", required=false) String selectNameVal,
			
			Model model) {
		// logger.debug(paginator + "");
		
		List<TeacherInDetailModel> teacherInDetailModels=null;
		  if( null==year){
		   Date nowDate=new Date();
		     SimpleDateFormat sdf=new SimpleDateFormat("yyyy"); 
		      year = sdf.format(nowDate);
		  }
		  
		  teacherInDetailModels = pxClassService.getTeacherInDetailInYear(paginator, year);
		  
		  if(StringUtils.isBlank(company) && StringUtils.isBlank(name) ){
				//如果公司和姓名为空则就显示所有的内部讲师明细
			  teacherInDetailModels = pxClassService.getTeacherInDetailInYear(paginator, year);
						 
	           
			}

		  
		  
		if(StringUtils.isNotBlank(company) ){
			//根据公司查询出所有内部讲师明细数据
			teacherInDetailModels = pxClassService
					.getTeacherInDetailByCompany(company, paginator,year);
           
		}

		if(StringUtils.isNotBlank(name) ){
			//根据姓名查询出所有的内部讲师明细的数据
			teacherInDetailModels = pxClassService
					  .getTeacherInDetailByName(name, paginator,year);
		}
		
		
		if("降序".equals(selectVal)){
			//按时间降序排序
			
			System.out.println("selectVal:"+selectVal);
			teacherInDetailModels = pxClassService
					.getTeacherInDetailByProjectBeginTimeOrder(paginator, year);
		}
		if("升序".equals(selectVal)){
			System.out.println("selectVal:"+selectVal);
			teacherInDetailModels = pxClassService
					.getTeacherInDetailByProjectBeginTimeOrderAsc(paginator, year);
		}
		if("升序".equals(selectNameVal)){
			//按人员升序排序
			 System.out.println("selectNameVal:"+selectNameVal);
			teacherInDetailModels = pxClassService
					  .getTeacherInDetailByNameOrderASC(paginator,year);
			  
		} 
		
		if("降序".equals(selectNameVal)){
			//按人员降序排序
			 System.out.println("selectNameVal:"+selectNameVal);
			teacherInDetailModels = pxClassService
					  .getTeacherInDetailByNameOrderDESC(paginator,year);
			 
			 
		} 
		/*if("1".equals(classorder)){
			//按课程时间排序
			teacherInDetailModels = pxClassService
					.getTeacherInDetailByProjectBeginTimeOrder(paginator, year);
				 
		}*/
		
		
		if(StringUtils.isNotBlank(company) && StringUtils.isNotBlank(name) ){
			//按单位和姓名排序
			 
			teacherInDetailModels = pxClassService
					  .getTeacherInDetailInByCompanyAndName(company,name,paginator,year);
			 
		}
		 model.addAttribute("selectVal", selectVal);
	     model.addAttribute("selectNameVal", selectNameVal);
		model.addAttribute("year", year);
		model.addAttribute("paginator", paginator);
		model.addAttribute("teacherInDetailModels", teacherInDetailModels);
		return "/teacher/teacherindetaillist";

	}

	 
	@RequestMapping(value = "/teacherindetail/teacherindetailmy")
	public String my(Model model) {
		 Date nowDate=new Date();
	     SimpleDateFormat sdf=new SimpleDateFormat("yyyy"); 
	     String year = sdf.format(nowDate);
	    /* String selectVal="降序";
	     String  selectNameVal="降序";*/
	     model.addAttribute("year", year);
	     
	     /*model.addAttribute("selectVal", selectVal);
	     model.addAttribute("selectNameVal", selectNameVal);*/
		return "teacher/teacherindetailmy";

	}

	 
	 
}

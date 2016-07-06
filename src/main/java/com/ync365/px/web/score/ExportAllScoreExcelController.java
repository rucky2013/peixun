package com.ync365.px.web.score;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
 
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
 
import org.springframework.web.bind.annotation.RequestParam;

 




import com.ync365.px.model.Paginator;
import com.ync365.px.repositoryimpl.ScoreDaoImpl;
import com.ync365.px.service.train.ExportAllScoreExcelService;
import com.ync365.px.utils.ExportAllScoreExcelUtil;

@Controller
public class ExportAllScoreExcelController {
     
	@Autowired
	 private  ExportAllScoreExcelService  exportAllScoreExcelService;
	@Autowired
	 private ScoreDaoImpl scoreDaoImpl;
	  
	 @RequestMapping(value="/exportallscore")
	 public void ExportAllScoreExcelAll(/*@Valid @ModelAttribute("Paginator") Paginator paginator,*/// 分页对象
			 HttpServletRequest request, ModelMap model,HttpServletResponse response,
			 @RequestParam(value = "year", defaultValue = "-1") String year
				 
				 ) {
		
		 if (year.equals("-1")) {
			 year = new SimpleDateFormat("yyyy").format(new Date());
			}
		  Paginator paginator=new Paginator();
	      Long countScoreYear = scoreDaoImpl.countScoreYear(null, null, null);
	      paginator.setLength(Integer.parseInt(Long.toString(countScoreYear)) );
	    /*  paginator.setLength(Integer.parseInt(Long.toString(3)));*/
	      
	 String fileName = exportAllScoreExcelService.exportAllScoreExcel(paginator,year);
	 	 
		 File file = new File(fileName);
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        ServletOutputStream out = null;
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
	       
	        try {
	        	
				response.setHeader("Content-Disposition", "attachment;filename="+ new String(("年度积分-" + year + ".xls").getBytes(), "iso-8859-1"));
				out = response.getOutputStream();
				bis = new BufferedInputStream(new FileInputStream(file));
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int byteRead = 0;
				while (-1 != (byteRead = bis.read(buff,0,buff.length))) {
					bos.write(buff,0,byteRead);
				}
				bos.flush();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	       
	/* return "";*/
	 }
}

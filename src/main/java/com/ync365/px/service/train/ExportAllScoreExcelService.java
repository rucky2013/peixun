package com.ync365.px.service.train;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

  
import com.ync365.px.model.ExportAllScoreModel;
import com.ync365.px.model.Paginator;
 
import com.ync365.px.model.ScoreYearModel;
import com.ync365.px.repositoryimpl.ScoreDaoImpl;
import com.ync365.px.utils.ExportAllScoreExcelUtil;
 

//Spring Bean的标识.
@Component
//类中所有public函数都纳入事务管理的标识.
public class ExportAllScoreExcelService {
    
	 public final Logger logger = LoggerFactory.getLogger(ScoreService.class);
	@PersistenceContext  
     private EntityManager em;  
	
	 @Autowired
	 private ScoreDaoImpl scoreDaoImpl;
	  
	 
	 public String exportAllScoreExcel(Paginator paginator,String year){  
	 
		 List<ScoreYearModel> scoreYearModels= scoreDaoImpl.getScoreYear(paginator, null, null, year);
		      
		 List<ExportAllScoreModel>  exportAllScoreModels=new ArrayList<ExportAllScoreModel>();
		
		 for (ScoreYearModel scoreYearModel : scoreYearModels) {
			 
			 ExportAllScoreModel exportAllScoreModel=new ExportAllScoreModel(); 
			 
			 exportAllScoreModel.setDepartmentName(scoreYearModel.getDepartmentName()); 
			 exportAllScoreModel.setParentDepartmentName(scoreYearModel.getParentDepartmentName());
			 exportAllScoreModel.setUsername(scoreYearModel.getUsername());
			 exportAllScoreModel.setScore_1(scoreYearModel.getScoreMonthModels().get(0).getScoreCount());
			 exportAllScoreModel.setScore_2(scoreYearModel.getScoreMonthModels().get(1).getScoreCount());
			 exportAllScoreModel.setScore_3(scoreYearModel.getScoreMonthModels().get(2).getScoreCount());
			 exportAllScoreModel.setScore_4(scoreYearModel.getScoreMonthModels().get(3).getScoreCount());
			 exportAllScoreModel.setScore_5(scoreYearModel.getScoreMonthModels().get(4).getScoreCount()); 
			 exportAllScoreModel.setScore_6(scoreYearModel.getScoreMonthModels().get(5).getScoreCount());
			 exportAllScoreModel.setScore_7(scoreYearModel.getScoreMonthModels().get(6).getScoreCount());
			 exportAllScoreModel.setScore_8(scoreYearModel.getScoreMonthModels().get(7).getScoreCount());
			 exportAllScoreModel.setScore_9(scoreYearModel.getScoreMonthModels().get(8).getScoreCount());
			 exportAllScoreModel.setScore_10(scoreYearModel.getScoreMonthModels().get(9).getScoreCount());
			 exportAllScoreModel.setScore_11(scoreYearModel.getScoreMonthModels().get(10).getScoreCount());
			 exportAllScoreModel.setScore_12(scoreYearModel.getScoreMonthModels().get(11).getScoreCount());
			 exportAllScoreModel.setScoreCountShould(scoreYearModel.getScoreCountShould());
			 exportAllScoreModel.setScoreCount(scoreYearModel.getScoreCount());
			 exportAllScoreModel.setPeriodCount(scoreYearModel.getPeriodCount());
		
			 exportAllScoreModels.add(exportAllScoreModel);
		 }
		 
		 Map<String, String> headlines = new LinkedHashMap<String, String>();
         headlines.put("0parentDepartmentName", "分部");//属性前边的数字代表字段的先后顺序。
         headlines.put("1departmentName", "部门");//最好将源码中判别顺序的格式改为"序号-字段"。
         headlines.put("2username", "姓名");
         headlines.put("3score_1", "1月");
         headlines.put("4score_2", "2月");
         headlines.put("5score_3", "3月");
         headlines.put("6score_4", "4月");
         headlines.put("7score_5", "5月");
         headlines.put("8score_6", "6月");
         headlines.put("9score_7", "7月");
         headlines.put("10score_8", "8月");
         headlines.put("11score_9","9月");
         headlines.put("12score_10", "10月"); 
         headlines.put("13score_11", "11月");
         headlines.put("14score_12", "12月"); 
         headlines.put("15scoreCountShould", "应得积分");
         headlines.put("16scoreCount", "积分汇总");
         headlines.put("17periodCount", "学时汇总"); 
         
         Map data = new HashMap();
         data.put("exportAllScoreModels", exportAllScoreModels);
         data.put("headlines", headlines);
          
	 return  ExportAllScoreExcelUtil.objListToExcel(data);
		      
	 }
}

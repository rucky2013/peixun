package com.ync365.px.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ync365.px.common.config.GeneralStaticConfig;
import com.ync365.px.entity.PxProject;
import com.ync365.px.entity.ScoreSetting;
import com.ync365.px.entity.User;
import com.ync365.px.model.ClassModel;
import com.ync365.px.model.ScoreModel;
import com.ync365.px.model.ScoreMonthModel;
import com.ync365.px.model.ScoreYearModel;
import com.ync365.px.model.bo.PxProjectBo;

public class PackModelUtil {

	/**
	 * 封装User model
	 * @param data
	 * @return
	 */
	public static List<User> packUserModelFormNativeSql(List data){
		List<User> result = new ArrayList<User>();
		for(Object object : data){
			Map map = (Map) object;
			User user = new User();
			user.setId(ResultSetUtils.null2Long(String.valueOf(map.get("u_id"))));
			user.setName((String)map.get("u_name"));
			user.setLoginName((String)map.get("u_loginname"));
			user.setDepartmentId(ResultSetUtils.null2Long(map.get("u_department_id")));
			user.setDepartmentName((String)map.get("dep_name"));
			user.setParentDepartmentName((String)map.get("dep_parent_name"));
			user.setStatus(ResultSetUtils.null2Integer(map.get("u_status")));
			result.add(user);
		}
		return result;
	}

	public static List<PxProject> packPxProjectModelFormNativeSql(List data){
		List<PxProject> result = new ArrayList<PxProject>();
		if(data.size()!=0){
		    for(Object object : data){
		        Map map = (Map) object;
		        PxProject pxProject = new PxProject();
		        pxProject.setId( ((Integer)map.get("id")).longValue());
		        pxProject.setName((String)map.get("name"));
		        pxProject.setBeginTime((Date) map.get("begin_time"));
		        pxProject.setEndTime((Date) map.get("end_time"));
		        pxProject.setProjectLevel((Integer) map.get("project_level"));
		        pxProject.setStatus((Integer)map.get("status"));
		        pxProject.setTicketType((Integer)map.get("ticket_type"));
		        result.add(pxProject);
		    }
		}
		return result;
	}

	/**
	 * 封装积分model
	 * @param data
	 * @return
	 */
	public static List<ScoreModel> packScoreModelFormNativeSql(List data){
		List<ScoreModel> result = new ArrayList<ScoreModel>();
		for(Object object : data){
			Map map = (Map) object;
			ScoreModel scoreModel = new ScoreModel();
			scoreModel.setId(ResultSetUtils.null2Long(String.valueOf(map.get("s_id"))));
			scoreModel.setUserId(ResultSetUtils.null2Long(String.valueOf(map.get("u_id"))));
			scoreModel.setUserName((String)map.get("u_name"));
			scoreModel.setDepartmentId(ResultSetUtils.null2Long(String.valueOf(map.get("dep_id"))));
			scoreModel.setDepartmentName((String)map.get("dep_name"));
			scoreModel.setParentDepartmentName((String)map.get("dep_parent_name"));
			result.add(scoreModel);
		}
		return result;
	}

	/**
	 * 封装首页项目列表
	 */
	public static List<PxProjectBo> packPxProjectBoFormNativeSql(List data) {
		List<PxProjectBo> lists = new ArrayList<PxProjectBo>();
		for(Object object : data) {
			Map map = (Map) object;
			PxProjectBo pxProjectBo = new PxProjectBo();
			pxProjectBo.setId(ResultSetUtils.null2Long(String.valueOf(map.get("id"))));
			pxProjectBo.setBeginTime((Date) map.get("begin_time"));
			pxProjectBo.setEndTime((Date) map.get("end_time"));
			pxProjectBo.setTicketTime((Date) map.get("ticket_time"));
			pxProjectBo.setStatus(ResultSetUtils.null2Integer(String.valueOf(map.get("status"))));
			pxProjectBo.setName((String)map.get("name"));
			lists.add(pxProjectBo);
		}
		return lists;
	}

	/**
	 * 封装积分年度model
	 * @param data
	 * @return
	 */
	public static List<ScoreYearModel> packScoreModelYearFormNativeSql(List data){
		List<ScoreYearModel> result = new ArrayList<ScoreYearModel>();
		Map<String, ScoreModel> mapScoreModel = new HashMap<String,ScoreModel>();


		Map<String, HashMap<String, ScoreMonthModel>> mapUserScoreYearModel = new HashMap<String, HashMap<String, ScoreMonthModel>>();
		
		for(Object object : data){
			Map map = (Map) object;
			String userid = "" + ResultSetUtils.null2Long(String.valueOf(map.get("u_id")));
			//先封装每个用户的一年12个月的月度汇总
			if (StringUtils.isNotEmpty(userid)) {
				if (!mapUserScoreYearModel.containsKey(userid)) {
					ScoreYearModel scoreYearModel = new ScoreYearModel();
					ScoreSetting scoreSetting = new ScoreSetting();
					scoreSetting.setScore_1(ResultSetUtils.null2Float(map.get("score_1")));
					scoreSetting.setScore_2(ResultSetUtils.null2Float(map.get("score_2")));
					scoreSetting.setScore_3(ResultSetUtils.null2Float(map.get("score_3")));
					scoreSetting.setScore_4(ResultSetUtils.null2Float(map.get("score_4")));
					scoreSetting.setScore_5(ResultSetUtils.null2Float(map.get("score_5")));
					scoreSetting.setScore_6(ResultSetUtils.null2Float(map.get("score_6")));
					scoreSetting.setScore_7(ResultSetUtils.null2Float(map.get("score_7")));
					scoreSetting.setScore_8(ResultSetUtils.null2Float(map.get("score_8")));
					scoreSetting.setScore_9(ResultSetUtils.null2Float(map.get("score_9")));
					scoreSetting.setScore_10(ResultSetUtils.null2Float(map.get("score_10")));
					scoreSetting.setScore_11(ResultSetUtils.null2Float(map.get("score_11")));
					scoreSetting.setScore_12(ResultSetUtils.null2Float(map.get("score_12")));
					
					scoreYearModel.setScoreMonthModels(new ArrayList<ScoreMonthModel>());
					HashMap<String, ScoreMonthModel> mapScoreMonth = new HashMap<String, ScoreMonthModel>();
					for(int i = 1; i <= 12; i++){
						ScoreMonthModel scoreMonthModel = new ScoreMonthModel();
						scoreMonthModel.setScoreSetting(scoreSetting);
						scoreMonthModel.setMonth(i);
						scoreMonthModel.setUserid(ResultSetUtils.null2Long(String.valueOf(map.get("u_id"))));
						scoreMonthModel.setUsername((String)map.get("u_name"));
						scoreMonthModel.setDepartmentName((String)map.get("dep_name"));
						scoreMonthModel.setParentDepartmentName((String)map.get("dep_parent_name"));
						mapScoreMonth.put(i + "", scoreMonthModel);
					}
					mapUserScoreYearModel.put(userid, mapScoreMonth);
				}
			}
			//如果没有项目记录 不封装class
			if (null == ResultSetUtils.null2Long(String.valueOf(map.get("p_id"))) || ResultSetUtils.null2Long(String.valueOf(map.get("p_id"))).equals(0l)) {
				continue;
			}
			
			String keyProjectUserid = "" + ResultSetUtils.null2Long(String.valueOf(map.get("p_id"))) + ResultSetUtils.null2Long(String.valueOf(map.get("u_id")));

			//开始封装class model
			if (!mapScoreModel.containsKey(keyProjectUserid)) {
				ClassModel classModel = new ClassModel();
				classModel.setId(ResultSetUtils.null2Long(String.valueOf(map.get("c_id"))));
				classModel.setName((String)map.get("c_name"));
				classModel.setProjectid(ResultSetUtils.null2Long(String.valueOf(map.get("p_id"))));
				classModel.setTeacherName((String)map.get("c_teacher_name"));
				classModel.setTeacherid(ResultSetUtils.null2Long(String.valueOf(map.get("c_teacher_id"))));
				classModel.setTeacherType(ResultSetUtils.null2Integer(String.valueOf(map.get("c_teacher_type"))));

				classModel.setStartTime(ResultSetUtils.null2Date(map.get("c_begin_time")));
				classModel.setEndTime(ResultSetUtils.null2Date(map.get("c_end_time")));
				classModel.setShould(ResultSetUtils.null2Float(map.get("cs_sscore")));//学生应得积分
				classModel.setReal(ResultSetUtils.null2Float(map.get("cs_score")));//学生实得积分
				classModel.setPeriod(ResultSetUtils.null2Float(map.get("cs_should")));//课程课时
				classModel.setRealPeriod(ResultSetUtils.null2Float(map.get("cs_reals")));//学生实际课时
				classModel.setPeroidTeacherShould(ResultSetUtils.null2Float(map.get("c_class_hour")));//教师应上课时
				classModel.setPeroidTeacher(ResultSetUtils.null2Float(map.get("c_sclass_hour")));//老师实际课时
				classModel.setTeacherScore(ResultSetUtils.null2Float(map.get("c_steacher_score")));//老师实得积分
				classModel.setTeacherScoreShould(ResultSetUtils.null2Float(map.get("c_teacher_score")));//老师应得积分

				//封装ScoreModel
				ScoreModel scoreModel = new ScoreModel();
				scoreModel.setUserId(ResultSetUtils.null2Long(String.valueOf(map.get("u_id"))));
				scoreModel.setUserName((String)map.get("u_name"));
				scoreModel.setDepartmentId(ResultSetUtils.null2Long(String.valueOf(map.get("dep_id"))));
				scoreModel.setDepartmentName((String)map.get("dep_name"));
				scoreModel.setProjectName((String)map.get("p_name"));
				scoreModel.setProjectId(ResultSetUtils.null2Long(String.valueOf(map.get("p_id"))));
				scoreModel.setParentDepartmentName((String)map.get("dep_parent_name"));
				scoreModel.setProjectStartTime(ResultSetUtils.null2Date(map.get("p_begin_time")));
				scoreModel.setProjectEndTime(ResultSetUtils.null2Date(map.get("p_end_time")));
				scoreModel.setProjectLevel(ResultSetUtils.null2Integer(map.get("p_project_level")));


				if (null == scoreModel.getClasses()) {
					scoreModel.setClasses(new ArrayList<ClassModel>());
					scoreModel.getClasses().add(classModel);
				} else {
					scoreModel.getClasses().add(classModel);
				}
				if (classModel.getTeacherType().equals(GeneralStaticConfig.TEACHER_TYPE_INNER) && classModel.getTeacherid().equals(scoreModel.getUserId())) {
					scoreModel.setReal(scoreModel.getReal() + classModel.getTeacherScore());
					scoreModel.setPeriodTeacher(scoreModel.getPeriodTeacher() + classModel.getPeroidTeacher());
					scoreModel.setTeacherScore(scoreModel.getTeacherScore() + classModel.getTeacherScore());
				}
				//汇总一次培训的积分和课时(公司级别)
				if (scoreModel.getProjectLevel().equals(GeneralStaticConfig.PROJECT_LEVEL_COMPANY)) {
					scoreModel.setReal(scoreModel.getReal() + classModel.getReal());
					scoreModel.setShould(scoreModel.getShould() + classModel.getShould());
					scoreModel.setPeriod(scoreModel.getPeriod() + classModel.getRealPeriod());
					scoreModel.setPeriodShould(scoreModel.getPeriodShould() + classModel.getPeriod());
					
				}
				//汇总一次培训的积分和课时(部门级别)
				if (scoreModel.getProjectLevel().equals(GeneralStaticConfig.PROJECT_LEVEL_DEPARTMENT)) {
					scoreModel.setPeriod(scoreModel.getPeriod() + classModel.getRealPeriod());
				}
				
				mapScoreModel.put(keyProjectUserid, scoreModel);

			} else {
				ClassModel classModel = new ClassModel();
				classModel.setId(ResultSetUtils.null2Long(String.valueOf(map.get("c_id"))));
				classModel.setName((String)map.get("c_name"));
				classModel.setProjectid(ResultSetUtils.null2Long(String.valueOf(map.get("p_id"))));
				classModel.setTeacherName((String)map.get("c_teacher_name"));
				classModel.setTeacherid(ResultSetUtils.null2Long(String.valueOf(map.get("c_teacher_id"))));
				classModel.setTeacherType(ResultSetUtils.null2Integer(String.valueOf(map.get("c_teacher_type"))));

				classModel.setStartTime(ResultSetUtils.null2Date(map.get("c_begin_time")));
				classModel.setEndTime(ResultSetUtils.null2Date(map.get("c_end_time")));
				classModel.setShould(ResultSetUtils.null2Float(map.get("cs_sscore")));//学生应得积分
				classModel.setReal(ResultSetUtils.null2Float(map.get("cs_score")));//学生实得积分
				classModel.setPeriod(ResultSetUtils.null2Float(map.get("cs_should")));//课程课时
				classModel.setRealPeriod(ResultSetUtils.null2Float(map.get("cs_reals")));//学生实际课时
				classModel.setPeroidTeacherShould(ResultSetUtils.null2Float(map.get("c_class_hour")));//教师应上课时
				classModel.setPeroidTeacher(ResultSetUtils.null2Float(map.get("c_sclass_hour")));//老师实际课时
				classModel.setTeacherScore(ResultSetUtils.null2Float(map.get("c_steacher_score")));//老师实得积分
				classModel.setTeacherScoreShould(ResultSetUtils.null2Float(map.get("c_teacher_score")));//老师应得积分
				ScoreModel scoreModel = mapScoreModel.get(keyProjectUserid);
				if (null == scoreModel.getClasses()) {
					scoreModel.setClasses(new ArrayList<ClassModel>());
					scoreModel.getClasses().add(classModel);
				} else {
					scoreModel.getClasses().add(classModel);
				}
				if (classModel.getTeacherType().equals(GeneralStaticConfig.TEACHER_TYPE_INNER) && classModel.getTeacherid().equals(scoreModel.getUserId())) {
					scoreModel.setReal(scoreModel.getReal() + classModel.getTeacherScore());
					scoreModel.setPeriodTeacher(scoreModel.getPeriodTeacher() + classModel.getPeroidTeacher());
					scoreModel.setTeacherScore(scoreModel.getTeacherScore() + classModel.getTeacherScore());
				}
				//汇总公司月度积分
				if (scoreModel.getProjectLevel().equals(GeneralStaticConfig.PROJECT_LEVEL_COMPANY)) {
					scoreModel.setReal(scoreModel.getReal() + classModel.getReal());
					scoreModel.setShould(scoreModel.getShould() + classModel.getShould());
					scoreModel.setPeriod(scoreModel.getPeriod() + classModel.getRealPeriod());
					scoreModel.setPeriodShould(scoreModel.getPeriodShould() + classModel.getPeriod());
					
				}
				//汇总部门积分月度积分
				if (scoreModel.getProjectLevel().equals(GeneralStaticConfig.PROJECT_LEVEL_DEPARTMENT)) {
					scoreModel.setPeriod(scoreModel.getPeriod() + classModel.getRealPeriod());
				}
			}

		}

		for(String key : mapScoreModel.keySet()){
			ScoreModel scoreModel = mapScoreModel.get(key);

			//如果项目结束时间为空,认为项目还没有结束,不计入积分汇总
			if (null == scoreModel.getProjectEndTime()) {
				continue;
			}




			ScoreMonthModel scoreMonthModel = mapUserScoreYearModel.get(scoreModel.getUserId() + "").get("" + Integer.parseInt(DateUtils.getDateMonthWithoutYearString(scoreModel.getProjectEndTime())));
			
			if (null == scoreMonthModel) {
				continue;
			}		
			
			if (null == scoreMonthModel.getScoreModel()) {
				scoreMonthModel.setScoreModel(new ArrayList<ScoreModel>());

			}
			scoreMonthModel.getScoreModel().add(scoreModel);
			//公司培训讲师所得积分
			scoreMonthModel.setScoreCountTeacher(scoreMonthModel.getScoreCountTeacher() + scoreModel.getTeacherScore());
			//公司培训讲师总学时
			scoreMonthModel.setPeriodCountTeacher(scoreMonthModel.getPeriodCountTeacher() + scoreModel.getPeriodTeacher());
			//部门培训总学时
			if (scoreModel.getProjectLevel().equals(GeneralStaticConfig.PROJECT_LEVEL_DEPARTMENT)) {
				scoreMonthModel.setPeriodCountDepartment((scoreMonthModel.getPeriodCountDepartment() + scoreModel.getPeriod()));
			}
			
			//月度总学时
			scoreMonthModel.setPeriodCount(scoreMonthModel.getPeriodCount() + scoreModel.getPeriod());
			//月度总积分
			scoreMonthModel.setScoreCount(scoreMonthModel.getScoreCount() + scoreModel.getReal());
			
		}

//		for(String key : mapMonthModel.keySet()){
//			String userid = key.split("_")[1];
//			ArrayList<ScoreModel> list = mapMonthModel.get(key);
//			ScoreMonthModel scoreMonthModel = new ScoreMonthModel();
//			scoreMonthModel.setUserid(list.get(0).getUserId());
//			scoreMonthModel.setDateMonth(list.get(0).getProjectEndTime());
//			scoreMonthModel.setMonth(null == scoreMonthModel.getDateMonth() ? -1 : Integer.valueOf(DateUtils.getDateMonthWithoutYearString(scoreMonthModel.getDateMonth())));
//			scoreMonthModel.setUsername(list.get(0).getUserName());
//			scoreMonthModel.setDepartmentName(list.get(0).getDepartmentName());
//			scoreMonthModel.setParentDepartmentName(list.get(0).getParentDepartmentName());
//			if (!mapYearModel.containsKey(userid)) {
//				if (list.size() <= 0) {
//					mapYearModel.put(userid, new ArrayList<ScoreMonthModel>());
//				} else {
//					for(ScoreModel scoreModel : list){
//						if (null == scoreMonthModel.getScoreModel()) {
//							scoreMonthModel.setScoreModel(new ArrayList<ScoreModel>());
//							scoreMonthModel.getScoreModel().add(scoreModel);
//							
//							//公司培训讲师所得积分
//							scoreMonthModel.setScoreCountTeacher(scoreMonthModel.getScoreCountTeacher() + scoreModel.getTeacherScore());
//							//公司培训讲师总学时
//							scoreMonthModel.setPeriodCountTeacher(scoreMonthModel.getPeriodCountTeacher() + scoreModel.getPeriodTeacher());
//							//部门培训总学时
//							if (scoreModel.getProjectLevel().equals(GeneralStaticConfig.PROJECT_LEVEL_DEPARTMENT)) {
//								scoreMonthModel.setPeriodCountDepartment((scoreMonthModel.getPeriodCountDepartment() + scoreModel.getPeriod()));
//							}
//							//月度总学时
//							scoreMonthModel.setPeriodCount(scoreMonthModel.getPeriodCount() + scoreModel.getPeriod());
//							//月度总积分
//							scoreMonthModel.setScoreCount(scoreMonthModel.getScoreCount() + scoreModel.getReal());
//							
//							
//						} else {
//							scoreMonthModel.getScoreModel().add(scoreModel);
//							//公司培训讲师所得积分
//							scoreMonthModel.setScoreCountTeacher(scoreMonthModel.getScoreCountTeacher() + scoreModel.getTeacherScore());
//							//公司培训讲师总学时
//							scoreMonthModel.setPeriodCountTeacher(scoreMonthModel.getPeriodCountTeacher() + scoreModel.getPeriodTeacher());
//							//部门培训总学时
//							if (scoreModel.getProjectLevel().equals(GeneralStaticConfig.PROJECT_LEVEL_DEPARTMENT)) {
//								scoreMonthModel.setPeriodCountDepartment((scoreMonthModel.getPeriodCountDepartment() + scoreModel.getPeriod()));
//							}
//							//月度总学时
//							scoreMonthModel.setPeriodCount(scoreMonthModel.getPeriodCount() + scoreModel.getPeriod());
//							//月度总积分
//							scoreMonthModel.setScoreCount(scoreMonthModel.getScoreCount() + scoreModel.getReal());
//						}
//					}
//					//设置月度公司培训所得积分
//					
//					for(ScoreModel scoreModel : scoreMonthModel.getScoreModel()){
//						if (!scoreModel.getProjectLevel().equals(GeneralStaticConfig.PROJECT_LEVEL_COMPANY)) {
//							continue;
//						}
//						scoreMonthModel.setScoreCount(scoreMonthModel.getScoreCount() + scoreModel.getReal());
//					}
//					
//					//月度总学时(公司培训学习学时+公司培训讲师学时+部门培训学时)
//					scoreMonthModel.setPeriodCount(scoreMonthModel.getPeriodCount() + scoreMonthModel.getPeriodCountTeacher());
//					//月度总积分(公司培训学习积分+公司培训讲师积分)
//					scoreMonthModel.setScoreCount(scoreMonthModel.getScoreCount() + scoreMonthModel.getScoreCountTeacher());
//					
//				}
//				mapYearModel.put(userid, new ArrayList<ScoreMonthModel>());
//				mapYearModel.get(userid).add(scoreMonthModel);
//			} else {
//				if (list.size() <= 0) {
//					mapYearModel.put(userid, new ArrayList<ScoreMonthModel>());
//				} else {	
//					for(ScoreModel scoreModel : list){
//						if (null == scoreMonthModel.getScoreModel()) {
//							scoreMonthModel.setScoreModel(new ArrayList<ScoreModel>());
//							scoreMonthModel.getScoreModel().add(scoreModel);
//							//公司培训讲师所得积分
//							scoreMonthModel.setScoreCountTeacher(scoreMonthModel.getScoreCountTeacher() + scoreModel.getTeacherScore());
//							//公司培训讲师总学时
//							scoreMonthModel.setPeriodCountTeacher(scoreMonthModel.getPeriodCountTeacher() + scoreModel.getPeriodTeacher());
//							//部门培训总学时
//							if (scoreModel.getProjectLevel().equals(GeneralStaticConfig.PROJECT_LEVEL_DEPARTMENT)) {
//								scoreMonthModel.setPeriodCountDepartment((scoreMonthModel.getPeriodCountDepartment() + scoreModel.getPeriod()));
//							}
//							//月度总学时
//							scoreMonthModel.setPeriodCount(scoreMonthModel.getPeriodCount() + scoreModel.getPeriod());
//							//月度总积分
//							scoreMonthModel.setScoreCount(scoreMonthModel.getScoreCount() + scoreModel.getReal());
//						} else {
//							scoreMonthModel.getScoreModel().add(scoreModel);
//							//公司培训讲师所得积分
//							scoreMonthModel.setScoreCountTeacher(scoreMonthModel.getScoreCountTeacher() + scoreModel.getTeacherScore());
//							//公司培训讲师总学时
//							scoreMonthModel.setPeriodCountTeacher(scoreMonthModel.getPeriodCountTeacher() + scoreModel.getPeriodTeacher());
//							//部门培训总学时
//							if (scoreModel.getProjectLevel().equals(GeneralStaticConfig.PROJECT_LEVEL_DEPARTMENT)) {
//								scoreMonthModel.setPeriodCountDepartment((scoreMonthModel.getPeriodCountDepartment() + scoreModel.getPeriod()));
//							}
//							//月度总学时
//							scoreMonthModel.setPeriodCount(scoreMonthModel.getPeriodCount() + scoreModel.getPeriod());
//							//月度总积分
//							scoreMonthModel.setScoreCount(scoreMonthModel.getScoreCount() + scoreModel.getReal());
//						}
//					}
//					//月度总学时(公司培训学习学时+公司培训讲师学时+部门培训学时)
//					scoreMonthModel.setPeriodCount(scoreMonthModel.getPeriodCount() + scoreMonthModel.getPeriodCountTeacher());
//					//月度总积分(公司培训学习积分+公司培训讲师积分)
//					scoreMonthModel.setScoreCount(scoreMonthModel.getScoreCount() + scoreMonthModel.getScoreCountTeacher());
//					
//				}
//				mapYearModel.get(userid).add(scoreMonthModel);
//			}
//		}
		
		for(String userid : mapUserScoreYearModel.keySet()){
			ScoreYearModel scoreYearModel = new ScoreYearModel();
			HashMap<String, ScoreMonthModel> map = mapUserScoreYearModel.get(userid + "");
			scoreYearModel.setUserid(Long.valueOf(userid));
			scoreYearModel.setDepartmentName(map.get("1").getDepartmentName());
			scoreYearModel.setParentDepartmentName(map.get("1").getParentDepartmentName());
			scoreYearModel.setUsername(map.get("1").getUsername());
			
//			ArrayList<ScoreMonthModel> list = mapYearModel.get(userid);
//			ScoreSetting scoreSetting = list.get(0).getScoreModel().get(0).getScoreSetting();
//			if (null != scoreSetting) {
//				scoreYearModel.setScoreCountShould(scoreSetting.getScore_1() + 
//						scoreSetting.getScore_2()+
//						scoreSetting.getScore_3()+
//						scoreSetting.getScore_4()+
//						scoreSetting.getScore_5()+
//						scoreSetting.getScore_6()+
//						scoreSetting.getScore_7()+
//						scoreSetting.getScore_8()+
//						scoreSetting.getScore_9()+
//						scoreSetting.getScore_10()+
//						scoreSetting.getScore_11()+
//						scoreSetting.getScore_12()
//						);
//			}
			for(ScoreMonthModel scoreMonthModel : map.values()){
				
				//月度总学时(公司培训学习学时+公司培训讲师学时)
				scoreMonthModel.setPeriodCount(scoreMonthModel.getPeriodCount() + scoreMonthModel.getPeriodCountTeacher());
				//月度总积分(公司培训学习积分+公司培训讲师积分)
				scoreMonthModel.setScoreCount(scoreMonthModel.getScoreCount() + scoreMonthModel.getScoreCountTeacher());
				scoreYearModel.setPeriodCount(scoreYearModel.getPeriodCount() + scoreMonthModel.getPeriodCount());
				scoreYearModel.setPeriodCountDepartment(scoreYearModel.getPeriodCountDepartment() + scoreMonthModel.getPeriodCountDepartment());
				scoreYearModel.setScoreCount(scoreYearModel.getScoreCount() + scoreMonthModel.getScoreCount());
				scoreYearModel.setScoreCountTeacher(scoreYearModel.getScoreCountTeacher() + scoreMonthModel.getScoreCountTeacher());
				ScoreSetting scoreSetting = scoreMonthModel.getScoreSetting();
				scoreYearModel.setScoreCountShould(scoreSetting.getScore_1() +
						scoreSetting.getScore_2()+
						scoreSetting.getScore_3()+
						scoreSetting.getScore_4()+
						scoreSetting.getScore_5()+
						scoreSetting.getScore_6()+
						scoreSetting.getScore_7()+
						scoreSetting.getScore_8()+
						scoreSetting.getScore_9()+
						scoreSetting.getScore_10()+
						scoreSetting.getScore_11()+
						scoreSetting.getScore_12());

				if (null == scoreYearModel.getScoreMonthModels()) {
					scoreYearModel.setScoreMonthModels((new ArrayList<ScoreMonthModel>()));
				}
				scoreYearModel.getScoreMonthModels().add(scoreMonthModel);

			}
//			Set<Integer> setMonth = new HashSet<Integer>();
//			//汇总年度
//			for(ScoreMonthModel scoreMonthModel : list){
//				if (!scoreMonthModel.getMonth().equals(-1)) {
//					setMonth.add(scoreMonthModel.getMonth());
//				}
//				scoreYearModel.setPeriodCount(scoreYearModel.getPeriodCount() + scoreMonthModel.getPeriodCount());
//				scoreYearModel.setPeriodCountDepartment(scoreYearModel.getPeriodCountDepartment() + scoreMonthModel.getPeriodCountDepartment());
//				scoreYearModel.setScoreCount(scoreYearModel.getScoreCount() + scoreMonthModel.getScoreCount());
//				
//			}
//			for(int i = 1; i<13; i++){
//				ScoreMonthModel scoreMonthModel = new ScoreMonthModel();
//				scoreMonthModel.setMonth(i);
//				if (null == scoreYearModel.getScoreMonthModels()) {
//					scoreYearModel.setScoreMonthModels(new ArrayList<ScoreMonthModel>());
//					scoreYearModel.getScoreMonthModels().add(scoreMonthModel);
//				}
//				if (!scoreYearModel.getScoreMonthModels().contains(scoreMonthModel)) {
//					scoreYearModel.getScoreMonthModels().add(scoreMonthModel);
//				}
//				
//			}
			Collections.sort(scoreYearModel.getScoreMonthModels());
			result.add(scoreYearModel);
		}
		Collections.sort(result);
		
//		System.out.println(result);
		return result;
	}

}

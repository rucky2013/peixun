/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ync365.px.web.score;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.px.entity.PxClass;
import com.ync365.px.entity.PxClassStudent;
import com.ync365.px.entity.ScoreSetting;
import com.ync365.px.model.Paginator;
import com.ync365.px.model.ScoreYearModel;
import com.ync365.px.service.account.AccountService;
import com.ync365.px.service.account.HrmService;
import com.ync365.px.service.account.ShiroDbRealm.ShiroUser;
import com.ync365.px.service.classservice.PxClassStudentService;
import com.ync365.px.service.classservice.PxClassTmpService;
import com.ync365.px.service.train.ScoreService;
import com.ync365.px.service.train.TrainService;
import com.ync365.px.shiro.util.ShiroUserUtils;
import com.ync365.px.task.UpdateHrmTask;
import com.ync365.px.utils.DateUtils;

/**
 * 积分管理
 *     
 * @Title：HomeController  
 * @Description: TODO   
 * @author: zhangdong      
 * @date: 2016年1月5日 下午6:13:13      
 * @version     
 *
 */
@Controller
public class ScoreController {
	
	public final Logger logger = LoggerFactory.getLogger(ScoreController.class);
	@Autowired
	private TrainService trainService;
	@Autowired
	private ScoreService scoreService;
	@Autowired 
	AccountService accountService;
	@Autowired
	HrmService HrmService;
	@Autowired
	PxClassStudentService pxClassStudentService;
	@Autowired
	PxClassTmpService pxClassTmpService;
	@Autowired
	UpdateHrmTask updateHrmTask;
	
    @RequestMapping(value="/listdata/score/my",method=RequestMethod.POST)
    public String myData(
    		@Valid @ModelAttribute("Paginator") Paginator paginator,//分页对象
    		@RequestParam(value="year",defaultValue="") String year,
    		Model model) {
    	
	    	if (StringUtils.isEmpty(year)) {
				year = new DateUtils().getDatYearString(new Date());
		}
	    	String userid = "";
	    	ShiroUser user = ShiroUserUtils.getCurrentShiroUser();
	    	userid = user.id + "";
	    	
		List<ScoreYearModel> scores = scoreService.getScoreAllByUser(paginator, year, userid);
		logger.debug("" + scores);
		model.addAttribute("score", scores.size() > 0 ? scores.get(0) : null);
		model.addAttribute("paginator", paginator);
		return "/listdata/score_my_data";
    }
    
    @RequestMapping(value="/score/my")
    public String my(Model model) {
//    		updateHrmTask.run();
    		return "/score/my";
    }
    
    
    @RequestMapping(value="/listdata/score/all",method=RequestMethod.POST)
    public String allData(
    		@Valid @ModelAttribute("Paginator") Paginator paginator,//分页对象
    		@RequestParam(value="year",defaultValue="") String year,
    		@RequestParam(value="name",defaultValue="") String name,Model model) {
    		if (StringUtils.isEmpty(year)) {
				year = new DateUtils().getDatYearString(new Date());
		}
    		name = name.trim();
    		List<ScoreYearModel> scores = scoreService.getScoreAllByYear(paginator,year,name);
    		
    		model.addAttribute("scores", scores);
    		model.addAttribute("paginator", paginator);
        return "/listdata/score_all_data";
    }
    
    @RequestMapping(value="/score/all")
    public String all(Model model) {
        return "score/all";
    }
    
    
    @RequestMapping(value="/listdata/score/detail",method=RequestMethod.GET)
    public String detailData(
    		@Valid @ModelAttribute("Paginator") Paginator paginator,//分页对象
    		@RequestParam(value="year",defaultValue="") String year, 
    		@RequestParam(value="userid",defaultValue="") String userid,Model model) {
	    	if (StringUtils.isEmpty(year)) {
				year = new DateUtils().getDatYearString(new Date());
		}
	    	
		List<ScoreYearModel> scores = scoreService.getScoreAllByUser(paginator, year, userid);
		logger.debug("" + scores);
		model.addAttribute("score", scores.size() > 0 ? scores.get(0) : null);
		model.addAttribute("paginator", paginator);
        return "/listdata/score_detail_data";
    }
    
    @RequestMapping(value="/score/detail")
    public String detail(Model model,
    		@RequestParam(value="year",defaultValue="") String year,
    		@RequestParam(value="userid",defaultValue="") String userid) {
    		model.addAttribute("year", year);
		model.addAttribute("userid", userid);
        return "score/detail";
    }
    
    
    @RequestMapping(value="/listdata/score/user",method=RequestMethod.GET)
    public String userData(
    		@Valid @ModelAttribute("Paginator") Paginator paginator,//分页对象
    		@RequestParam(value="year",defaultValue="") String year,
    		@RequestParam(value="userid",defaultValue="") String userid,
    		Model model) {
    		
    		if (StringUtils.isEmpty(userid)) {
    			return "/listdata/score_user_data";
		}
    	
	    	if (StringUtils.isEmpty(year)) {
				year = new DateUtils().getDatYearString(new Date());
		}
	    	
		List<ScoreYearModel> scores = scoreService.getScoreAllByUser(paginator, year, userid);
		model.addAttribute("score", scores.size() > 0 ? scores.get(0) : null);
		model.addAttribute("paginator", paginator);
		return "/listdata/score_user_data";
    }
    
    @RequestMapping(value="/score/user")
    public String user(@RequestParam(value="year",defaultValue="") String year,
    		@RequestParam(value="userid",defaultValue="") String userid,Model model) {
	    	if (StringUtils.isEmpty(year)) {
				year = new DateUtils().getDatYearString(new Date());
		}
    		model.addAttribute("year", year);
    		model.addAttribute("userid", userid);
        return "/score/user";
    }
    
    @RequestMapping(value="/listdata/score/setting",method=RequestMethod.POST)
    public String settingData(
    		@Valid @ModelAttribute("Paginator") Paginator paginator,//分页对象
    		@RequestParam(value="year",defaultValue="") String year,
    		@RequestParam(value="name",defaultValue="") String name,Model model) {
    		if (StringUtils.isEmpty(year)) {
				year = new DateUtils().getDatYearString(new Date());
		}
    		name = name.trim();
    		Map<String, Object> searchParams = new HashMap<String, Object>();
    		searchParams.put("EQ_year", year);
    		searchParams.put("LIKE_username", name);
    		List<ScoreSetting> scoreSettings = scoreService.getScoreSettings(searchParams, paginator);
    		logger.debug("" + scoreSettings);
    		model.addAttribute("settings", scoreSettings);
    		model.addAttribute("paginator", paginator);
        return "/listdata/score_setting_data";
    }
    
    @RequestMapping(value="/score/setting")
    public String setting(
    		@RequestParam(value="name",defaultValue="") String n,
    		@RequestParam(value="year",defaultValue="") String year,Model model) {
	    	if (StringUtils.isEmpty(year)) {
				year = new DateUtils().getDatYearString(new Date());
		}
	    	
	    	//插入积分
	    	scoreService.setScoreSettings(year);
	    	
	    	model.addAttribute("year", year);
        return "score/setting";
    }

    /**
     * 保存积分设定 s* 代表对应月份积分
     * @param year
     * @param scoreSettingId
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @param s5
     * @param s6
     * @param s7
     * @param s8
     * @param s9
     * @param s10
     * @param s11
     * @param s12
     * @param model
     * @return
     */
    @RequestMapping(value="/score/setting/update")
    @ResponseBody
    public Object settingUpdate(@RequestParam(value="year",defaultValue="") String year,
    		@RequestParam(value="score_setting_id",defaultValue="") Long scoreSettingId,
    		@RequestParam(value="s_1",defaultValue="") Float s1,
    		@RequestParam(value="s_2",defaultValue="") Float s2,
    		@RequestParam(value="s_3",defaultValue="") Float s3,
    		@RequestParam(value="s_4",defaultValue="") Float s4,
    		@RequestParam(value="s_5",defaultValue="") Float s5,
    		@RequestParam(value="s_6",defaultValue="") Float s6,
    		@RequestParam(value="s_7",defaultValue="") Float s7,
    		@RequestParam(value="s_8",defaultValue="") Float s8,
    		@RequestParam(value="s_9",defaultValue="") Float s9,
    		@RequestParam(value="s_10",defaultValue="") Float s10,
    		@RequestParam(value="s_11",defaultValue="") Float s11,
    		@RequestParam(value="s_12",defaultValue="") Float s12,
    		Model model) {
    		Map<String,String> result = new HashMap<String,String>();
    		try {
    		 	if (StringUtils.isEmpty(year)) {
    				year = new DateUtils().getDatYearString(new Date());
    		 	}
    	    	
	    	    	ScoreSetting scoreSetting = scoreService.findScoreSetting(scoreSettingId);
	    	    	if (null == scoreSetting) {
	    	    		result.put("result", "fail");
	    	    		return result;
			}
	    	    	scoreSetting.setId(scoreSettingId);
	    	    	scoreSetting.setScore_1(s1);
	    	    	scoreSetting.setScore_2(s2);
	    	    	scoreSetting.setScore_3(s3);
	    	    	scoreSetting.setScore_4(s4);
	    	    	scoreSetting.setScore_5(s5);
	    	    	scoreSetting.setScore_6(s6);
	    	    	scoreSetting.setScore_7(s7);
	    	    	scoreSetting.setScore_8(s8);
	    	    	scoreSetting.setScore_9(s9);
	    	    	scoreSetting.setScore_10(s10);
	    	    	scoreSetting.setScore_11(s11);
	    	    	scoreSetting.setScore_12(s12);
	    	    	//插入积分
	    	    	scoreService.saveScoreSetting(scoreSetting);
	    	    	result.put("result", "success");
		} catch (Exception e) {
			result.put("result", "fail");
		}
	   
        return result;
    }

    
    @RequestMapping(value = "/score/updateScoreinit/{id}/{projectId}")
    public String updateScoreListinit(Model model, @PathVariable Integer id, @PathVariable Integer projectId) {
        model.addAttribute("classId", id);
        model.addAttribute("projectId", projectId);
        return "/score/updateScore";
    }
    
    @RequestMapping(value="/score/updateScoreinit/grid",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateScoreinit(int classId,Paginator page,Model model) {
        PxClassStudent pxClassStudent = new PxClassStudent();
        pxClassStudent.setClassId(classId);
        Page<PxClassStudent> pxClassStudentlist =  pxClassStudentService.getCsinfoByClassIdinPage(pxClassStudent,page);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", pxClassStudentlist.getContent());
        map.put("iTotalRecords", pxClassStudentlist.getTotalElements());
        map.put("iTotalDisplayRecords", pxClassStudentlist.getTotalElements());
        return map;
    }
    
    @RequestMapping(value = "/score/classscoreedit/{id}")
    public String classscoreedit(@PathVariable Long id, Model model) {
        PxClassStudent pxClassStudent = pxClassStudentService.findById(id) ;
        model.addAttribute("pxClassStudent", pxClassStudent);
        model.addAttribute("projectId",pxClassTmpService.findById(pxClassStudent.getClassId().longValue()).getProjectId());
        return "/score/classscoreedit";
    }
    
    @RequestMapping(value = "/score/classscoreupdate")
    @ResponseBody
    public Map<String, String> classscoreupdate(@RequestParam(value="id",defaultValue="") Long id,
    		@RequestParam(value="reals",defaultValue="") Float reals,
    		@RequestParam(value="score",defaultValue="") Float score) {
    		Map<String, String> result = new HashMap<String,String>();
    		try {
    			PxClassStudent pxClassStudent = pxClassStudentService.findById(id);
    			if (null == pxClassStudent) {
				result.put("result", "fail");
				return result;
			}
    			pxClassStudent.setScore(score);
    			pxClassStudent.setReals(reals);
    			if (pxClassStudentService.updateClassScorce(pxClassStudent)) {
				result.put("result", "success");
				return result;
			} else {
				result.put("result","fail");
				return result;
			}
		} catch (Exception e) {
			result.put("result","fail");
			return result;
		}

    }
    @RequestMapping(value = "/score/updateteScoreinit/{id}/{projectId}")
    public String updateteScoreinit(Model model, @PathVariable Long id, @PathVariable Integer projectId) {
        PxClass pxClass = pxClassTmpService.findById(id);
        model.addAttribute("pxClass", pxClass);
        return "/score/updateteScore";
    }
    
    @RequestMapping(value = "/score/tescoreupdate")
    public String tescoreupdate(PxClass pxClass) {
        pxClassTmpService.updateteScore(pxClass);
        return "redirect:/classes/viewlist/admin/"+pxClass.getProjectId();
    }


}

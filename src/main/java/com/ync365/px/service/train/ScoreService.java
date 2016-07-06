/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ync365.px.service.train;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.ync365.px.common.config.GeneralStaticConfig;
import com.ync365.px.entity.Score;
import com.ync365.px.entity.ScoreSetting;
import com.ync365.px.entity.User;
import com.ync365.px.model.Paginator;
import com.ync365.px.model.ScoreYearModel;
import com.ync365.px.repository.ScoreDao;
import com.ync365.px.repository.ScoreSettingDao;
import com.ync365.px.repository.UserDao;
import com.ync365.px.repositoryimpl.ScoreDaoImpl;
import com.ync365.px.service.account.HrmService;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class ScoreService {
	public final Logger logger = LoggerFactory.getLogger(ScoreService.class);
	@PersistenceContext  
    private EntityManager em;  
	
	@Autowired
	private ScoreDaoImpl scoreDaoImpl;
	@Autowired
	private 	ScoreDao scoreDao;
	@Autowired
	private ScoreSettingDao scoreSettingDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private HrmService hrmService;

	public long count() {
		return scoreDao.count();
	}
	
	public Score getScore(Long id) {
		return scoreDao.findOne(id);
	}
	
	public List<ScoreYearModel> getScoreAllByYear(Paginator paginator,String year,String username) {
		long count = scoreDaoImpl.countScoreYear(null,username,year);
		paginator.setLength(new Long(count).intValue());//不分页，查所有
		paginator.setCount(count);
		return scoreDaoImpl.getScoreYear(paginator,null,username,year);
		
	}
	
	public List<ScoreYearModel> getScoreAllByUser(Paginator paginator,String year,String userid) {
		paginator.setCount(scoreDaoImpl.countScoreYear(userid,null,year));
		return scoreDaoImpl.getScoreYear(paginator,userid,null,year);
		
	}
	
	public List<ScoreSetting> getScoreSettings(Map<String, Object> searchParams,Paginator paginator){
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<ScoreSetting> spec = DynamicSpecifications.bySearchFilter(filters.values(), ScoreSetting.class);
		paginator.setCount(scoreSettingDao.count(spec));
		return scoreSettingDao.getScoreSettings(Integer.valueOf((String)searchParams.get("EQ_year")),(String)searchParams.get("LIKE_username"),paginator.getStart(), paginator.getLength());
	}
	
	public ScoreSetting findScoreSetting(Long id){
		return scoreSettingDao.findOne(id);
	}
	
	public void saveScoreSetting(ScoreSetting scoreSetting){
		scoreSettingDao.save(scoreSetting);
	}
	public void setScoreSettings(String year){
		List<User> users = hrmService.getAllUser();
		Set<Long> setUserId = new HashSet<Long>();
		List<ScoreSetting> scoreSettingsNew = new ArrayList<ScoreSetting>();
		Map<String, Object> searchParams = new HashMap<String,Object>();
		searchParams.put("EQ_year", year);
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<ScoreSetting> spec = DynamicSpecifications.bySearchFilter(filters.values(), ScoreSetting.class);
		List<ScoreSetting> scoreSettings = scoreSettingDao.findAll(spec);
		for(ScoreSetting scoreSetting : scoreSettings){
			setUserId.add(scoreSetting.getUserid());
		}
		
		for(User user : users){
			if (setUserId.contains(user.getId())) {
				continue;
			}
			ScoreSetting scoreSetting = new ScoreSetting();
			scoreSetting.setUserid(user.getId());
			scoreSetting.setUsername(user.getName());
			scoreSetting.setDepartmentName(user.getDepartmentName());
			scoreSetting.setParentDepartmentName(user.getParentDepartmentName());
			scoreSetting.setYear(Integer.valueOf(year));
			scoreSetting.setScore_1(GeneralStaticConfig.SCORE_MONTH);
			scoreSetting.setScore_2(GeneralStaticConfig.SCORE_MONTH);
			scoreSetting.setScore_3(GeneralStaticConfig.SCORE_MONTH);
			scoreSetting.setScore_4(GeneralStaticConfig.SCORE_MONTH);
			scoreSetting.setScore_5(GeneralStaticConfig.SCORE_MONTH);
			scoreSetting.setScore_6(GeneralStaticConfig.SCORE_MONTH);
			scoreSetting.setScore_7(GeneralStaticConfig.SCORE_MONTH);
			scoreSetting.setScore_8(GeneralStaticConfig.SCORE_MONTH);
			scoreSetting.setScore_9(GeneralStaticConfig.SCORE_MONTH);
			scoreSetting.setScore_10(GeneralStaticConfig.SCORE_MONTH);
			scoreSetting.setScore_11(GeneralStaticConfig.SCORE_MONTH);
			scoreSetting.setScore_12(GeneralStaticConfig.SCORE_MONTH);
			scoreSettingsNew.add(scoreSetting);
			
		}
		scoreSettingDao.save(scoreSettingsNew);
		
	}
	
	
//	public List<Score> getScoreAll(Paginator paginator) {
//		//查询之前先setCount
//		paginator.setCount(count());
//		
//		
//	}

	public long buildPageStart(int page,int pageSize){
		return (page - 1) * pageSize; 
	}
	
	public long getPgaeCount(long count ,int pageSize){
		if (count % pageSize == 0) {
			return count / pageSize;
		} else {
			return count / pageSize + 1;
		}
	}
}

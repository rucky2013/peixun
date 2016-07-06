/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ync365.px.web.home;

import com.ync365.px.model.Paginator;
import com.ync365.px.model.ScoreYearModel;
import com.ync365.px.service.account.ShiroDbRealm.ShiroUser;
import com.ync365.px.service.project.ProjectService;
import com.ync365.px.service.train.ScoreService;
import com.ync365.px.shiro.util.ShiroUserUtils;
import com.ync365.px.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @Title：HomeController
 * @Description: TODO
 * @author: zhangdong
 * @date: 2016年1月5日 下午6:13:13
 * @version
 *
 */
@Controller
public class HomeController {

    private static final Logger  logger  = LoggerFactory.getLogger(ShiroUserUtils.class);

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ScoreService scoreService;


    @RequestMapping(value="/tohomepage")
    public String tohomepage(Map<String, Object> model) {
        ShiroUser user = ShiroUserUtils.getCurrentUser();
        String year = new DateUtils().getDatYearString(new Date());

        List<ScoreYearModel> scores = scoreService.getScoreAllByUser(new Paginator(), year, user.id + "");
        model.put("score", scores.size() > 0 ? scores.get(0) : null);
        model.put("user", user);
        model.put("projectList", projectService.listTopFiveProjectByCreateTime(user.id));
        //logger.info(user.toString());
        return "home/homepage";
    }

}

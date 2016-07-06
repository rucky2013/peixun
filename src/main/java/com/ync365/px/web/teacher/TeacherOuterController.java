package com.ync365.px.web.teacher;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.px.entity.TeacherOuter;
import com.ync365.px.model.Paginator;
import com.ync365.px.model.TeacherOuterModel;
import com.ync365.px.service.account.ShiroDbRealm.ShiroUser;
import com.ync365.px.service.teacherout.TeacherOuterService;
import com.ync365.px.shiro.util.ShiroUserUtils;

@Controller
public class TeacherOuterController {

	public final Logger logger = LoggerFactory
			.getLogger(TeacherOuterController.class);
	@Autowired
	private TeacherOuterService teacherOuterService;

	@RequestMapping(value = "/teacherouter/teacherouterlist")
	public String projectList(Model model) {
		return "/teacher/teacherouterlist";
	}

	@RequestMapping(value = "/listdata/teacherouter/grid")
	@ResponseBody
	public Map<String, Object> teacherOuterGrid(Paginator page,
			TeacherOuter teacherOuter, Model model) {

		Page<TeacherOuter> teacherOuters = teacherOuterService
				.listTeacherOuterByPage(teacherOuter, page);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("aaData", teacherOuters.getContent());
		map.put("iTotalRecords", teacherOuters.getTotalElements());
		map.put("iTotalDisplayRecords", teacherOuters.getTotalElements());
		return map;
	}

	@RequestMapping(value = "/teacherouter/teacherouteredit/{id}")
	public String projectEdit(@PathVariable Long id, Model model) {

		model.addAttribute("teacherOuter",
				teacherOuterService.getTeacherOuter(id));

		return "/teacher/teacherouteredit";
	}

	@RequestMapping(value = "/teacherouter/teacherouterupdate")
	public String projectUpdate(TeacherOuterModel teacherOuterModel) {
		teacherOuterService.updateTeacherOuter(teacherOuterModel);
		return "/teacher/teacherouterlist";
	}

	@RequestMapping(value = "/teacherouter/teacheroutersave")
	
	public String projectSave(TeacherOuter teacherOuter) {
		teacherOuterService.insertTeacherOuter(teacherOuter);
		return "redirect:teacherouterlist";
	}

	@RequestMapping(value = "/teacherouter/teacherouterdelete")
	@ResponseBody
	public String projectDel(TeacherOuterModel teacherOuterModel) {
		teacherOuterService.deleteTeacherOuter(teacherOuterModel);
		return "map";
	}

}

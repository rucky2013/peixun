package com.ync365.px.web.classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ync365.px.entity.PxClass;
import com.ync365.px.entity.PxProject;
import com.ync365.px.entity.TeacherOuter;
import com.ync365.px.entity.User;
import com.ync365.px.model.Paginator;
import com.ync365.px.service.account.HrmService;
import com.ync365.px.service.classservice.PxClassTmpService;
import com.ync365.px.service.project.ProjectService;
import com.ync365.px.service.projectstudent.ProjectStudentService;
import com.ync365.px.service.teacherout.TeacherOuterService;

@Controller
public class ClassController {

    public final Logger logger = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    private PxClassTmpService pxClassTmpService;
    
    @Autowired
    private TeacherOuterService teacherOuterService;

    @Autowired
    private HrmService hrmService;
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private ProjectStudentService projectStudentService;
    
    @RequestMapping(value = "/classes/list/{projectId}")
    public String classesList(Model model, @PathVariable Integer projectId) {
        model.addAttribute("projectId", projectId);
        PxProject pxProject = projectService.findById(Long.valueOf(projectId));
        model.addAttribute("pxProject",pxProject);
        return "/classes/list";
    }
    
    @RequestMapping(value = "/classes/issetClass/{projectId}")
    @ResponseBody
    public int issetClass(Model model, @PathVariable Long projectId) {
        List<PxClass> pxClassList = pxClassTmpService.findClassByProjectId(projectId);
        return pxClassList.size();
    }
    
    @RequestMapping(value = "/classes/issetStu/{projectId}")
    @ResponseBody
    public int issetStu(Model model, @PathVariable Long projectId) {
        
        return projectStudentService.searchAllProjectStudent(projectId.intValue()).size();
    }
    
    @RequestMapping(value = "/classes/viewlist/{requrl}/{projectId}")
    public String viewclassesList(Model model, @PathVariable Integer projectId, @PathVariable String requrl) {
        model.addAttribute("projectId", projectId);
        PxProject pxProject = projectService.findById(Long.valueOf(projectId));
        model.addAttribute("projectStatus",pxProject.getStatus());
        model.addAttribute("requrl",requrl);
        return "/classes/viewlist";
    }

    @RequestMapping(value = "/listdata/classes/grid")
    @ResponseBody
    public Map<String, Object> classesGrid(Paginator page, PxClass pxClass, Model model) {
        Page<PxClass> projects = pxClassTmpService.listClassByPage(pxClass, page);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", projects.getContent());
        map.put("iTotalRecords", projects.getTotalElements());
        map.put("iTotalDisplayRecords", projects.getTotalElements());
        return map;
    }
    
    @RequestMapping(value = "/classes/edit/{id}")
    public String classesEdit(@PathVariable Long id, Model model) {
        model.addAttribute("classes", pxClassTmpService.findById(id));
        return "/classes/edit";
    }

    @RequestMapping(value = "/classes/update")
    public String classesUpdate(PxClass pxClass) {
        pxClassTmpService.updateClass(pxClass);
        return "redirect:/classes/list/"+pxClass.getProjectId();
    }

    @RequestMapping(value = "/classes/save")
   // @ResponseBody
    public String classesSave(PxClass pxClass) {
        pxClassTmpService.insertClass(pxClass);
       // return "map";
        return "redirect:/classes/list/"+pxClass.getProjectId();
    }

    @RequestMapping(value = "/classes/del")
    @ResponseBody
    public String classesDel(PxClass pxClass) {
        pxClassTmpService.delClass(pxClass);
        return "map";
    }
    @RequestMapping(value = "/classes/teaout")
    @ResponseBody
    public List<TeacherOuter> classesTeaOut() {
        return teacherOuterService.getTeacherOutAll();
    }
    @RequestMapping(value = "/classes/teain")
    @ResponseBody
    public List<User> classesTeaIn() {
        return hrmService.getAllUser();
    }
    /**时间自动格式化
     * @author xieang
     * 2015年9月15日
     * @param bin
     */
    @InitBinder
    public void InitBinder(ServletRequestDataBinder bin) {
        bin.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        bin.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}

package com.ync365.px.web.projectstudent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ync365.px.entity.PxProject;
import com.ync365.px.model.ProjectStudentBo;
import com.ync365.px.service.project.ProjectService;
import com.ync365.px.service.projectstudent.ProjectStudentService;

@Controller
public class ProjectStudentController {

    @Autowired
    private ProjectStudentService projectStudentService;
    
    @Autowired
    private ProjectService projectService;
    
    @RequestMapping(value = "/proStu/addstudents") 
    public void proStuAddStudents(HttpServletResponse response, ProjectStudentBo projectStudentBo,@RequestParam("prosturole") String prosturole ) throws IOException {
        
        	Map<String, Object> map = new HashMap<>();
        	projectStudentService.insertClassStudents(projectStudentBo);
        	map.put("success", "ok");
           if("admin".equals(prosturole)){
        	   //model.addAttribute("prosturole", "admin")  ; 
        	   map.put("prosturole", "admin");
           }else{
        	   map.put("prosturole", "user"); 
           }           
           ObjectMapper mapper = new ObjectMapper();
           response.setContentType("application/json");
           response.getWriter().write(mapper.writeValueAsString(map));
           response.flushBuffer();
        //return "ture";
    }
    @RequestMapping(value = "/proStu/list/{projectId}")
    public String classesTest(@PathVariable Integer projectId,Model model ) {
        PxProject pxProject = projectService.findById(Long.valueOf(projectId));
        model.addAttribute("projectId",projectId);
        model.addAttribute("ticketNum", pxProject.getTicketNum());
        model.addAttribute("students", projectStudentService.getStudents(projectId));
        model.addAttribute("studentList",  projectStudentService.searchAllProjectStudent(projectId)); 
        	  return "/proStu/list";  
    }
    
    @RequestMapping(value = "/proStu/liststudent/{projectId}")
    public String proStuList(@PathVariable Integer projectId,Model model) {
        PxProject pxProject = projectService.findById(Long.valueOf(projectId));
        model.addAttribute("projectId",projectId);
        model.addAttribute("ticketNum", pxProject.getTicketNum());
        model.addAttribute("students", projectStudentService.getStudents(projectId));
        model.addAttribute("studentList",  projectStudentService.searchAllProjectStudent(projectId));
        return "/proStu/liststudent";
    }
}

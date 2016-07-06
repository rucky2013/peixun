package com.ync365.px.web.project;

import com.ync365.px.entity.PxClass;
import com.ync365.px.entity.PxFile;
import com.ync365.px.entity.PxProject;
import com.ync365.px.model.Paginator;
import com.ync365.px.model.bo.FileUploadBo;
import com.ync365.px.model.bo.PxProjectBo;
import com.ync365.px.model.bo.SelectInfoBo;
import com.ync365.px.service.account.ShiroDbRealm.ShiroUser;
import com.ync365.px.service.booking.BookingService;
import com.ync365.px.service.classservice.PxClassTmpService;
import com.ync365.px.service.file.PxFileService;
import com.ync365.px.service.project.ProjectService;
import com.ync365.px.service.projectscope.ProjectScopeService;
import com.ync365.px.service.projectstudent.ProjectStudentService;
import com.ync365.px.shiro.util.ShiroUserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ProjectController {

    public final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PxFileService pxFileService;

    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private PxClassTmpService pxClassTmpService;
    
    @Autowired
    private ProjectStudentService projectStudentService;

    @Autowired
    private ProjectScopeService projectScopeService;


    /**
    /**
     * 
     * @Title: ProjectList
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2016年1月7日 下午2:55:32       
     * @version: 
     *
     * @param model
     * @return
     *
     */
    @RequestMapping(value = "/project/list")
    public String projectList(Model model) {
        List<SelectInfoBo> projectLevelBos = projectService.listProjectLevevl();
        List<SelectInfoBo> ticketTypeBos = projectService.listTicketType();
        List<SelectInfoBo> projectTagList = projectService.listProjectTag();
        model.addAttribute("projectLevels", projectLevelBos);
        model.addAttribute("ticketTypes", ticketTypeBos);
        model.addAttribute("projectTags", projectTagList);
        return "/project/list";
    }
    
    @RequestMapping(value = "/project/mylist")
    public String myprojectList(Model model) {
        List<SelectInfoBo> projectLevelBos = projectService.listProjectLevevl();
        List<SelectInfoBo> ticketTypeBos = projectService.listTicketType();
        List<SelectInfoBo> projectTagList = projectService.listProjectTag();
        model.addAttribute("projectLevels", projectLevelBos);
        model.addAttribute("ticketTypes", ticketTypeBos);
        model.addAttribute("projectTags", projectTagList);
        return "/project/mylist";
    }

    @RequestMapping(value = "/listdata/project/grid")
    @ResponseBody
    public Map<String, Object> projectGrid(Paginator page, PxProject project, Model model) {
        Page<PxProject> projects = projectService.listProjectByPage(project, page);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("aaData", projects.getContent());
        map.put("iTotalRecords", projects.getTotalElements());
        map.put("iTotalDisplayRecords", projects.getTotalElements());
        return map;
    }
    
    @RequestMapping(value = "/listdata/project/mygrid")
    @ResponseBody
    public Map<String, Object> myprojectGrid(Paginator page, PxProject project, Model model) {
        ShiroUser user = ShiroUserUtils.getCurrentUser();
        List<PxProject> projects = projectService.listMyProjectByPage(user.id.intValue(),project, page);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("aaData", projects);
        map.put("iTotalRecords", projects.size());
        map.put("iTotalDisplayRecords", projects.size());
        return map;
    }

    @RequestMapping(value = "/project/edit/{flag}/{id}")
    public String projectEdit(@PathVariable Long id, @PathVariable String flag,Model model) {
        List<SelectInfoBo> projectLevelBos = projectService.listProjectLevevl();
        List<SelectInfoBo> ticketTypeBos = projectService.listTicketType();
        List<SelectInfoBo> projectTagList = projectService.listProjectTag();
        model.addAttribute("project", projectService.findById(id));
        model.addAttribute("projectLevels", projectLevelBos);
        model.addAttribute("ticketTypes", ticketTypeBos);
        model.addAttribute("projectTags", projectTagList);
        //根据flag显示，可修改的属性
        model.addAttribute("flag", flag);
        return "/project/edit";
    }

    @RequestMapping(value = "/project/update")
    public String projectUpdate(PxProjectBo pxProjectBo) {
        projectService.updateProject(pxProjectBo);
        return "redirect:/project/list";
    }

    @RequestMapping(value = "/project/save")
    // @ResponseBody
     public String projectSave(PxProjectBo pxProjectBo) {
         projectService.insertProject(pxProjectBo);
         return "redirect:list";
         //return "map";
     }
    
    @RequestMapping(value = "/project/updatenum")
     public String projectupdatenum(Long id,int ticketNum) {
         projectService.updateTNum(id, ticketNum);
         return "redirect:/myapplyproject/list";
     }

    @RequestMapping(value = "/project/del")
    @ResponseBody
    public String projectDel(PxProjectBo pxProjectBo) {
        projectService.delProject(pxProjectBo);
        bookingService.syncProjectDel2redis(pxProjectBo.getId());
        projectScopeService.deleteByProjectId(pxProjectBo.getId());
        return "map";
    }

    @RequestMapping(value = "/project/fileList/{id}")
    public String projectfileList(@PathVariable Long id ,Model model) {
        model.addAttribute("projectId",id);
        return "/project/fileList";
    }
    
    @RequestMapping(value = "/project/changeStatus")
    @ResponseBody
    public String changeProjectStatus(Long id, Integer status) {
        if(status ==2){
            //如果是设置为抢票状态，检查是否已经设置课程
            List<PxClass> pxClassList = pxClassTmpService.findClassByProjectId(id);
            if(pxClassList.size()==0){
                return "pleaseSetClass";
            }
            //检查是否设置范围
            if(projectService.findScopeByProjectId(id).size()==0){
                return "pleaseSetScope";
            }
        }
        projectService.changeProjectStatus(id, status);
        PxProject pxProject = projectService.findById(id);
        bookingService.syncProjectinfo2redis(pxProject);
        return "";
    }
    
    /**
     * 
     * @Title: ProjectList
     * @Description: TODO    ：    
     * @author: ivan    
     * @date: 2016年1月7日 下午2:55:32       
     * @version: 
     *
     * @param model
     * @return
     *
     */
    @RequestMapping(value = "/project/file/{projectId}")
    public String fileList(@PathVariable Long projectId ,Model model) {
        model.addAttribute("projectId", projectId);
        return "/fileUpload/list";
    }

    /**
     *
     * @param file
     * @param request
     * @param projectId
     * @return
     * @throws Exception
     */
    @RequestMapping("/project/uploadFile/{projectId}")
    @ResponseBody
    public Map<String,String> uploadFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,
                                         @PathVariable Integer projectId) throws Exception {
        List<FileUploadBo> files = new ArrayList<FileUploadBo>();
        String  contextPath = request.getContextPath();
        FileUploadBo fileUploadBo = new FileUploadBo();
        PxFile pxFile =pxFileService.uploadFile(file, 0, projectId);;
        fileUploadBo.setDeleteType("DELETE");
        fileUploadBo.setDeleteUrl(contextPath+"/fileManager/deleteFile/"+pxFile.getId());
        fileUploadBo.setName(file.getOriginalFilename());
        fileUploadBo.setSize(String.valueOf(file.getSize()));
        fileUploadBo.setUrl(contextPath+"/fileManager/download/"+pxFile.getId());
        files.add(fileUploadBo);
        Map<String,String> map = new HashMap<>();
        map.put("tagetURL" ,"/project/file/"+projectId);
        return map;
    }

    @RequestMapping(value = "project/projectscope")
    @ResponseBody
    public String projectscope(String ids, Long id,@RequestParam("projectscopename") String projectscopename) {
        projectScopeService.insertScopes(id, ids);
          if("user".equals(projectscopename)){
        	  return "/myapplyproject/list" ; 
          }
        return "/project/list";
    }
    @RequestMapping("/project/listFile")
    @ResponseBody
    public Map<String, Object> listFile(HttpServletRequest request,
            Integer projectId) throws Exception {
        List<PxFile> pxFiles = pxFileService.getPxFileByTypeFK(0, projectId);
        List<FileUploadBo> files = new ArrayList<FileUploadBo>();
        String  contextPath = request.getContextPath();
        for(PxFile pxFile : pxFiles) {
            FileUploadBo fileUploadBo = new FileUploadBo();
            fileUploadBo.setId(pxFile.getId());
            fileUploadBo.setDeleteType("DELETE");
            fileUploadBo.setDeleteUrl(contextPath+"/fileManager/deleteFile/"+pxFile.getId());
            fileUploadBo.setName(pxFile.getName());
            fileUploadBo.setSize(String.valueOf(pxFile.getSize()));
            fileUploadBo.setUrl(contextPath+"/fileManager/download/"+pxFile.getId());
            files.add(fileUploadBo);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("aaData",files);
        map.put("iTotalRecords", files.size());
        map.put("iTotalDisplayRecords", files.size());
        return map;
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
        bin.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm"), true));
    }
    
    
    
    
    

    //--------以下为我的申请项目相关---------------------------------------
  
    
    
    
    
    /**
     * 根据创建人的createUserId,找出所有的项目,专项list页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/myapplyproject/list")
    public String myApplyProjectList(Model model) {
        List<SelectInfoBo> projectLevelBos = projectService.listProjectLevevl();
         for (SelectInfoBo projectLevelBo : projectLevelBos) {
			if("公司级别".equals(projectLevelBo.getValue()) ){
				projectLevelBos.remove(projectLevelBo);
			}
		}
        List<SelectInfoBo> ticketTypeBos = projectService.listTicketType();
        List<SelectInfoBo> projectTagList = projectService.listProjectTag();
        model.addAttribute("projectLevels", projectLevelBos);
        model.addAttribute("ticketTypes", ticketTypeBos);
        model.addAttribute("projectTags", projectTagList);
        return "/myapplyproject/list";
    }
    
       
    
    /**
     * 根据创建人的createUserId,找出所有的项目
     * @param page
     * @param project
     * @param model
     * @return
     */
   /* @RequestMapping(value = "/listdata/myapplyproject/grid")
    @ResponseBody
    public Map<String, Object> myApplyProject(Paginator page, PxProject project, Model model) {
        ShiroUser user = ShiroUserUtils.getCurrentUser();
        List<PxProject> projects = projectService.myApplyProjectByPage(user.id.intValue(), project, page);
        Map<String, Object> map = new HashMap<String, Object>();
        Long count = projectService.myApplyProjectCountByPage(user.id.intValue());
        map.put("aaData", projects);
        map.put("iTotalRecords", count);
        map.put("iTotalDisplayRecords", count);
        return map;
    }*/
     @RequestMapping(value = "/listdata/myapplyproject/grid")
    @ResponseBody
    public Map<String, Object> myApplyProject(Paginator page, PxProject project, Model model) {
    	 ShiroUser user = ShiroUserUtils.getCurrentUser();
    	long id= user.id;
    	 Page<PxProject> projects = projectService.myApplyProjectByPage(project, page,id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("aaData", projects.getContent());
        map.put("iTotalRecords", projects.getTotalElements());
        map.put("iTotalDisplayRecords", projects.getTotalElements());
        return map;
    }
    @RequestMapping(value = "/myapplyproject/edit/{id}")
    public String myApplyProjectEdit(@PathVariable Long id, Model model) {
        List<SelectInfoBo> projectLevelBos = projectService.listProjectLevevl();
        List<SelectInfoBo> ticketTypeBos = projectService.listTicketType();
        List<SelectInfoBo> projectTagList = projectService.listProjectTag();
        model.addAttribute("project", projectService.findById(id));
        model.addAttribute("projectLevels", projectLevelBos);
        model.addAttribute("ticketTypes", ticketTypeBos);
        model.addAttribute("projectTags", projectTagList);
        return "/myapplyproject/edit";
    }

    @RequestMapping(value = "/myapplyproject/update")
    public String myApplyProjectUpdate(PxProjectBo pxProjectBo) {
    	ShiroUser user = ShiroUserUtils.getCurrentUser();
    	int createUserId=user.id.intValue();
    	pxProjectBo.setCreateUserId(createUserId);
    	pxProjectBo.setProjectLevel(2);
        projectService.updateProject(pxProjectBo);
        return "redirect:/myapplyproject/list";
    }

    @RequestMapping(value = "/myapplyproject/save")
    // @ResponseBody
     public String myApplyProjectSave(PxProjectBo pxProjectBo) {
    	ShiroUser user = ShiroUserUtils.getCurrentUser();
    	int createUserId=user.id.intValue();
    	pxProjectBo.setCreateUserId(createUserId);
    	pxProjectBo.setProjectLevel(2);
         projectService.insertProject(pxProjectBo);
         return "redirect:list";
         //return "map";
     }
    
    @RequestMapping(value = "/myapplyproject/del")
    @ResponseBody
    public String myApplyProjectDel(PxProjectBo pxProjectBo) {
        projectService.delProject(pxProjectBo);
        bookingService.syncProjectDel2redis(pxProjectBo.getId());
        return "map";
    }
}

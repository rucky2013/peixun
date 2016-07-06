package com.ync365.px.service.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.px.common.utils.CloneUtils;
import com.ync365.px.common.utils.StringUtils;
import com.ync365.px.entity.ProjectScope;
import com.ync365.px.entity.PxClass;
import com.ync365.px.entity.PxProject;
import com.ync365.px.entity.PxProjectStudent;
import com.ync365.px.entity.PxProjectTag;
import com.ync365.px.entity.User;
import com.ync365.px.model.Paginator;
import com.ync365.px.model.bo.PxProjectBo;
import com.ync365.px.model.bo.SelectInfoBo;
import com.ync365.px.repository.PxClassDao;
import com.ync365.px.repository.PxClassStudentDao;
import com.ync365.px.repository.PxProjectDao;
import com.ync365.px.repository.PxProjectScopeDao;
import com.ync365.px.repository.PxProjectStudentDao;
import com.ync365.px.repository.PxProjectTagDao;
import com.ync365.px.repositoryimpl.PxProjectDaoImpl;
import com.ync365.px.repositoryimpl.PxProjectScopeDaoImpl;
import com.ync365.px.repositoryimpl.UserDaoImpl;
import com.ync365.px.service.booking.BookingService;

@Component
@Transactional
public class ProjectService {

    public final Logger logger = LoggerFactory.getLogger(ProjectService.class);
    @PersistenceContext  
    private EntityManager em;
    
    @Autowired
	private PxClassDao pxClassDao;
	
    @Autowired
	private PxProjectDao pxProjectDao;
	
    @Autowired
	private PxProjectStudentDao pxProjectStudentDao;
    
    @Autowired
    private BookingService bookingService;
	
    @Autowired
	private PxProjectTagDao pxProjectTagDao;
    
    @Autowired
    private PxClassStudentDao pxClassStudentDao;
    
    @Autowired
    private PxProjectDaoImpl projectDaoImpl;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private PxProjectScopeDaoImpl pxProjectScopeDaoImpl;
    
    @Autowired
    private PxProjectScopeDao pxProjectScopeDao;
	
	public PxProject addPxProject (final PxProject pxProject) {
			pxProjectDao.save(pxProject);
			return pxProject;
	}
	
	public PxProject getProject(Long id) {
		return pxProjectDao.findOne(id);
	}
	public List<PxProject> listAllProject() {
	    return (List<PxProject>) pxProjectDao.findAll();
	}
	
	public Long count() {
	    return pxProjectDao.count();
	}
	
	public void changeProjectStatus(Long id,Integer status) {
	    PxProject pxProject = pxProjectDao.findOne(id);
	    pxProject.setStatus(status);
	    pxProjectDao.save(pxProject);
	}
	
	
	public Page<PxProject> listProjectByPage(final PxProject projectQuery,final Paginator page){
        Specification<PxProject> sp = buildPxProject(projectQuery);
        PageRequest pageRequest = null;
        Sort sort = new Sort(Direction.DESC, "createTime");
        pageRequest = new PageRequest((int) page.getPageReOne(), (int)page.getLength(),sort);
        Page<PxProject> pages = null;
        pages = pxProjectDao.findAll(sp, pageRequest);
        return pages;
    }

    public List<PxProjectBo> listTopFiveProjectByCreateTime(Long userId) {
        User user = userDao.getUser(userId);
        List<PxProjectBo> projectList = new ArrayList<PxProjectBo>();
        projectList = pxProjectScopeDaoImpl.getPxProjectByScope(user.getDepartmentId());

        for(PxProjectBo pxProjectBo : projectList) {
            List<PxClass> classes = pxClassDao.findByProjectId(pxProjectBo.getId().intValue());
            Double sumStudentScore =0.0;
            for(PxClass pxClass : classes) {
                sumStudentScore = sumStudentScore + Double.valueOf(pxClass.getStudentScore());
            }
            pxProjectBo.setTotalStudentScore(sumStudentScore);
        }
        return projectList;
    }
	
	public PxProject findById(final Long id) {
	    return pxProjectDao.findOne(id);
	}
	
	public List<SelectInfoBo> listProjectLevevl() {
	    List <SelectInfoBo> projectLevelBos = new ArrayList<SelectInfoBo> ();
	    SelectInfoBo bo = new SelectInfoBo();
	    bo.setId(1l);
	    bo.setValue("公司级别");
	    SelectInfoBo bo1 = new SelectInfoBo();
	    bo1.setId(2l);
	    bo1.setValue("部门级别");
	    projectLevelBos.add(bo);
	    projectLevelBos.add(bo1);
	    return projectLevelBos;
	}
	public List<SelectInfoBo> listTicketType() {
	    List<SelectInfoBo> ticketTypeBos = new ArrayList<SelectInfoBo> ();
	    SelectInfoBo bo = new SelectInfoBo();
	    bo.setId(1l);
	    bo.setValue("指定人员+抢票");
	    SelectInfoBo bo1 = new SelectInfoBo();
	    bo1.setId(2l);
	    bo1.setValue("指定人员");
	    ticketTypeBos.add(bo);
	    ticketTypeBos.add(bo1);
	    return ticketTypeBos;
	}
	public List<SelectInfoBo> listProjectTag() {
	    List <PxProjectTag> tags = (List<PxProjectTag>) pxProjectTagDao.findByIsUse(1);
	    List<SelectInfoBo> projectTagList = new ArrayList<SelectInfoBo> ();
	    for (PxProjectTag tag : tags) {
	        SelectInfoBo bo = new SelectInfoBo();
	        bo.setId(tag.getId());
	        bo.setValue(tag.getTagName());
	        projectTagList.add(bo);
	    }
	    return projectTagList;
	}
	public PxProject insertProject(final PxProjectBo pxProjectBo) {
	    PxProject pxProject = new PxProject();
	    CloneUtils.cloneObject(pxProjectBo, pxProject);
	    pxProject.setStatus(1);
	    pxProject.setCreateTime(new Date());
	    pxProjectDao.save(pxProject);
	    //新建成功同步数据到redis
	    if(pxProject.getTicketType() == 1) {
	        bookingService.syncProjectinfo2redis(pxProject);
	    }
	    return pxProject;
	}
	public PxProject updateProject(final PxProjectBo pxProjectBo) {
	    PxProject pxProject = pxProjectDao.findOne(pxProjectBo.getId());
	    CloneUtils.cloneObject(pxProjectBo, pxProject);
	    pxProjectDao.save(pxProject);
	    return pxProject;
	}
	public boolean updateTNum(Long Id,int num) {
        pxProjectDao.updateTNumById(Id,num);
        return true;
    }
	public void delProject(final PxProjectBo pxProjectBo) {
        pxProjectDao.delete(pxProjectBo.getId());
        pxClassDao.deleteByProjectId(Integer.valueOf(pxProjectBo.getId().toString()));
        pxProjectStudentDao.deleteByProjectId(Integer.valueOf(pxProjectBo.getId().toString()));
        List<PxClass> pxClassList = pxClassDao.findByProjectId(Integer.valueOf(pxProjectBo.getId().toString()));
        for(PxClass pxClass : pxClassList){
            pxClassStudentDao.deleteByClassId(Integer.valueOf(pxClass.getId().toString()));
        }
    }
	
	private Specification<PxProject> buildPxProject(final PxProject q) {
        Specification<PxProject> sp = new Specification<PxProject>() {

            @Override
            public Predicate toPredicate(Root<PxProject> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (q.getId() != null) {
                    list.add(cb.equal(root.get("id").as(Long.class), q.getId()));
                }
                if (q.getStatus() != null) {
                    list.add(cb.equal(root.get("status").as(Integer.class), q.getStatus()));
                }
                if (!StringUtils.isBlank(q.getName())) {
                    list.add(cb.like (root.get("name").as(String.class), "%"+q.getName()+"%"));
                }
                if (q.getProjectLevel() != null) {
                    list.add(cb.equal(root.get("projectLevel").as(Integer.class), q.getProjectLevel()));
                }
                if (q.getTicketType() != null) {
                    list.add(cb.equal(root.get("ticketType").as(Integer.class), q.getTicketType()));
                }
               /* if (q.getBeginTime() != null) {
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    list.add(cb.equal(root.get("beginTime").as(String.class),
                            f.format(q.getBeginTime())));
                }
                if (q.getEndTime() != null) {
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    list.add(cb.equal(root.get("endTime").as(String.class),
                            f.format(q.getEndTime())));
                }*/
                //list.add(cb.between(root.get("beginTime").as(String.class), root.get("beginTime").as(String.class),root.get("endTime").as(String.class)));
                if(q.getBeginTime()!=null && q.getEndTime()!=null){
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					list.add(cb.and(cb.greaterThanOrEqualTo(cb.substring(root.get("beginTime").as(String.class),1,10), sdf.format(q.getBeginTime())),
							cb.lessThanOrEqualTo(cb.substring(root.get("endTime").as(String.class),1,10), sdf.format(q.getEndTime()))));
				}
                
                Predicate[] ps = new Predicate[list.size()];
                query.where(cb.and(list.toArray(ps)));
                return query.getGroupRestriction();
            }
        };
        return sp;
    }
	private Specification<PxProject> buildPxProjectStatus() {
        Specification<PxProject> sp = new Specification<PxProject>() {

            @Override
            public Predicate toPredicate(Root<PxProject> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                    list.add(cb.equal(root.get("status").as(Integer.class), 1));
                    list.add(cb.equal(root.get("status").as(Integer.class), 2));
                Predicate[] ps = new Predicate[list.size()];
                query.where(cb.or(list.toArray(ps)));
                return query.getGroupRestriction();
            }
        };
        return sp;
    }
	
    public List<PxProject> listMyProjectByPage(int userId,PxProject project, Paginator page) {
        page.setCount(projectDaoImpl.countPro(userId));
        
        return projectDaoImpl.getMyPro(page, userId);
    }
    
    
    public List<ProjectScope> findScopeByProjectId(Long projectId){
        return pxProjectScopeDao.findByProjectId(projectId);
    }
    
    
    
    
    /*  ----------------------------以下为项目申请代码----------------------------
     */
     
     
     

     /**
      * 根据当前创建人的createUserId;查询所有数据
      * @param q
      * @param id
      * @return
      */
     private Specification<PxProject> buildMyApplyPxProject(final PxProject q,final long id) {
         Specification<PxProject> sp = new Specification<PxProject>() {

             @Override
             public Predicate toPredicate(Root<PxProject> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                 List<Predicate> list = new ArrayList<>();
                 if (q.getId() != null) {
                     list.add(cb.equal(root.get("id").as(Long.class), q.getId()));
                 }
                 if (q.getStatus() != null) {
                     list.add(cb.equal(root.get("status").as(Integer.class), q.getStatus()));
                 }
                 if (!StringUtils.isBlank(q.getName())) {
                     list.add(cb.like(root.get("name").as(String.class),"%"+ q.getName()+"%"));
                 }
                 if (q.getProjectLevel() != null) {
                     list.add(cb.equal(root.get("projectLevel").as(Integer.class), q.getProjectLevel()));
                 }
                 if (q.getTicketType() != null) {
                     list.add(cb.equal(root.get("ticketType").as(Integer.class), q.getTicketType()));
                 }
                /* SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");
                 if (q.getBeginTime() != null) {
                      
                     list.add(cb.equal(root.get("beginTime").as(String.class),
                             sdf.format(q.getBeginTime())));
                 }
                 if (q.getEndTime() != null) {
                   
                     list.add(cb.equal(root.get("endTime").as(String.class),
                    		  sdf.format(q.getEndTime())));
                            
                 }
                    */
                 
                 if(q.getBeginTime()!=null && q.getEndTime()!=null){
                	 
 					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
 					list.add(cb.and(cb.greaterThanOrEqualTo(cb.substring(root.get("beginTime").as(String.class),1,10), sdf.format(q.getBeginTime())),
 							cb.lessThanOrEqualTo(cb.substring(root.get("endTime").as(String.class),1,10), sdf.format(q.getEndTime()))));
 				}
                     list.add(cb.equal(root.get("createUserId").as(Long.class), id));
                
                 Predicate[] ps = new Predicate[list.size()];
                 query.where(cb.and(list.toArray(ps)));
                 return query.getGroupRestriction();
                 
                 
             }
         };
         return sp;
     }
     
 	
      /*
 	 public List<PxProject> myApplyProjectByPage(int userId,PxProject project, Paginator page) {
 	        
 	        return projectDaoImpl.getMyApplyProjectList(page, userId);
 	    }
 	 public Long myApplyProjectCountByPage (int userId) {
 		 return projectDaoImpl.myApplyProjectCount(userId);
 	 }*/

 	 
 	 /**
 	  * 根据创建人的createUserId 查询出所有项目数据
 	  * @param project
 	  * @param page
 	  * @param id
 	  * @return
 	  */
 	public Page<PxProject> myApplyProjectByPage(final PxProject project,
 			final Paginator page ,final long id) {
 		 Specification<PxProject> sp = buildMyApplyPxProject(project ,id);
 	        PageRequest pageRequest = null;
 	        Sort sort = new Sort(Direction.DESC, "createTime");
 	        pageRequest = new PageRequest((int) page.getPageReOne(), (int)page.getLength(),sort);
 	        Page<PxProject> pages = null;
 	        pages = pxProjectDao.findAll(sp, pageRequest);
 	        return pages;
 	 
 	}
}

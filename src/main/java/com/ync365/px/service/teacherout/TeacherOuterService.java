package com.ync365.px.service.teacherout;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.px.common.utils.CloneUtils;
import com.ync365.px.common.utils.StringUtils;
import com.ync365.px.entity.TeacherOuter;
import com.ync365.px.model.Paginator;
import com.ync365.px.model.TeacherOuterModel;
import com.ync365.px.repository.TeacherOuterDao;
import com.ync365.px.repositoryimpl.TeacherOuterDaoImpl;

//Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class TeacherOuterService {

	@PersistenceContext
	private EntityManager em;

	private TeacherOuterDao teacherOuterDao;
	 @Autowired
	private TeacherOuterDaoImpl teacherOuterDaoImpl;

	public TeacherOuterDao getTeacherOuterDao() {
		return teacherOuterDao;
	}

	@Autowired
	public void setTeacherOuterDao(TeacherOuterDao teacherOuterDao) {
		this.teacherOuterDao = teacherOuterDao;
	}

	public TeacherOuter getTeacherOuter(Long id) {
		return teacherOuterDao.findOne(id);
	}

	public void saveTeacherOuter(TeacherOuter entity) {
		teacherOuterDao.save(entity);
	}
	
	
	public TeacherOuter insertTeacherOuter(final TeacherOuter  teacherOuter) { 
	   
	    teacherOuterDao.save(teacherOuter);
	    return teacherOuter;
	}
	/**
	 * 更新一个外部讲师
	 * @param teacherOuterModel 封装一个新的外部讲师对象
	 * @return
	 */
	public TeacherOuter updateTeacherOuter(final TeacherOuterModel teacherOuterModel) {
		    
		TeacherOuter teacherOuter = teacherOuterDao.findOne(teacherOuterModel.getId());
		  CloneUtils.cloneObject(teacherOuterModel, teacherOuter);
		teacherOuterDao.save(teacherOuter);
	    return teacherOuter;
	}
	/**
	 * 删除一个外部讲师
	 * @param teacherOuterModel
	 */
	public void deleteTeacherOuter(TeacherOuterModel teacherOuterModel) {
		 
		teacherOuterDao.delete(teacherOuterModel.getId());
	}
	 
	
	public void deleteTeacherOuter(Long id) {
		teacherOuterDao.delete(id);
	}

	public long count() {
		return teacherOuterDao.count();
	}
    
	/**
	 * 根据公司（单位）查询总记录数
	 * @param company
	 * @return
	 */
	public long countByCompany(String company){
		return teacherOuterDaoImpl.getCountByCompany(company);
	}
	
	/**
	 * 根据名字查询出总记录数
	 * @param name
	 * @return
	 */
	public long countByName(String name){
		return teacherOuterDaoImpl.getCountByName(name);
	}
	
	public List<TeacherOuter> getAllTeacherOuter() {

		return (List<TeacherOuter>) teacherOuterDao.findAll();
	}

	public List<TeacherOuter> getAllTeacherOuter(Paginator paginator) {
		// 查询之前先setCount
		paginator.setCount(count());
		/*
		 * logger.debug("page" + paginator.getPage()); logger.debug("start" +
		 * paginator.getStart()); logger.debug("pageSize" +
		 * paginator.getPageSize());
		 */
		return teacherOuterDao.getAllPagina(paginator.getStart(),
				paginator.getLength());

	}

	public long buildPageStart(int page, int pageSize) {
		return (page - 1) * pageSize;
	}

	public long getPgaeCount(long count, int pageSize) {
		if (count % pageSize == 0) {
			return count / pageSize;
		} else {
			return count / pageSize + 1;
		}
	}

	/**
	 * 根据人员名称获取所有的外部讲师
	 * 
	 * @param name
	 * @return
	 */
	public List<TeacherOuter> getTeacherOutByName(String name,Paginator paginator) {

		// 查询之前先setCount
		paginator.setCount(countByName(name));

		return teacherOuterDaoImpl.getTeacherOutByName(name,
				paginator.getStart(), paginator.getLength());
	}
	/**
     * 根据人员名称获取所有的外部讲师
     * 
     * @param name
     * @return
     */
    public List<TeacherOuter> getTeacherOutAll() {

        return teacherOuterDaoImpl.getTeacherOutAll();
    }

	/**
	 * 根据公司名称获取所有的外部讲师
	 * 
	 * @param department
	 * @return
	 */
	public List<TeacherOuter> getTeacherOutByCompany(String company,
			Paginator paginator) {

		// 查询之前先setCount
		paginator.setCount(countByCompany(company));

		return teacherOuterDaoImpl.findTeacherOuterByCompany(company,
				paginator.getStart(), paginator.getLength());

	}

	 /**
	  * 查询分页数据，并封装到Page对象中
	  * @param teacherOuter
	  * @param page
	  * @return
	  */
	public Page<TeacherOuter> listTeacherOuterByPage(final TeacherOuter teacherOuter,
			final Paginator page) {
		 /*System.out.println("name:"+teacherOuter.getTeacherName());*/
	        Specification<TeacherOuter> specification = buildTeacherOuter(teacherOuter);
	        PageRequest pageRequest = null;
	            pageRequest = new PageRequest((int) page.getPageReOne(), (int)page.getLength());
	        Page<TeacherOuter> pages = null;
	        pages = teacherOuterDao.findAll(specification, pageRequest);
	        List<TeacherOuter> content = pages.getContent();
	       System.out.println(content);
	        return pages;
	    
	}

	private Specification<TeacherOuter> buildTeacherOuter(
			final TeacherOuter teacherOuter) {
		 
		   Specification<TeacherOuter> specification = new Specification<TeacherOuter>() {
			  
	            @Override
	            public Predicate toPredicate(Root<TeacherOuter> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	                List<Predicate> list = new ArrayList<>();
	                if (teacherOuter.getId() != null) {
	                    list.add(cb.equal(root.get("id").as(Long.class), teacherOuter.getId()));
	                }
	                if (!StringUtils.isBlank(teacherOuter.getCompany())) {
	                	list.add(cb.like(root.get("company").as(String.class), "%"+teacherOuter.getCompany()+"%" ));
	                }
	                if (StringUtils.isNotBlank(teacherOuter.getTeacherName())) {
	                    list.add(cb.like(root.get("teacherName").as(String.class),"%"+ teacherOuter.getTeacherName()+"%"));
	                }
	                if (!StringUtils.isBlank(teacherOuter.getIntroduce())) {
	                    list.add(cb.equal(root.get("introduce").as(String.class),teacherOuter.getIntroduce() ));
	                }
	                
	                if (!StringUtils.isBlank(teacherOuter.getPhone())) {
	                    list.add(cb.equal(root.get("phone").as(String.class),teacherOuter.getPhone()));
	                }
	                if (!StringUtils.isBlank(teacherOuter.getSubject())) {
	                	list.add(cb.equal(root.get("subject").as(String.class),teacherOuter.getSubject()));
	                }
	                if (!StringUtils.isBlank(teacherOuter.getPost())) {
	                    list.add(cb.equal(root.get("post").as(String.class), teacherOuter.getPost()));
	                }
	                if (!StringUtils.isBlank(teacherOuter.getAddress())) {
	                    list.add(cb.equal(root.get("address").as(String.class),teacherOuter.getAddress()));
	                             
	                }
	                  

	                Predicate[] predicate = new Predicate[list.size()];
	                query.where(cb.and(list.toArray(predicate)));
	                return query.getGroupRestriction();
	            }
	        };
	        return specification;
	    }

	 

	

}

package com.ync365.px.service.classservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.px.common.utils.StringUtils;
import com.ync365.px.entity.PxClass;
import com.ync365.px.entity.TeacherOuter;
import com.ync365.px.entity.User;
import com.ync365.px.model.Paginator;
import com.ync365.px.repository.PxClassDao;
import com.ync365.px.repository.PxClassStudentDao;
import com.ync365.px.repository.PxProjectStudentDao;
import com.ync365.px.repository.PxProjectTagDao;
import com.ync365.px.repository.TeacherOuterDao;
import com.ync365.px.repositoryimpl.UserDaoImpl;

//Spring Bean的标识.
@Component
//类中所有public函数都纳入事务管理的标识.
@Transactional
public class PxClassTmpService {
    public final Logger logger = LoggerFactory.getLogger(PxClassTmpService.class);

    @Autowired
    private PxClassDao pxClassDao;


    @Autowired
    private PxProjectStudentDao pxProjectStudentDao;

    @Autowired
    private PxProjectTagDao pxProjectTagDao;
    
    @Autowired
    private TeacherOuterDao teacherOuterDao;
    
    @Autowired
    private UserDaoImpl userDaoImpl;
    
    @Autowired
    private PxClassStudentDao pxClassStudentDao;

    public Long count() {
        return pxClassDao.count();
    }

    public PxClass findById(final Long id) {
        return pxClassDao.findOne(id);
    }
    public PxClass insertClass(PxClass pxClass) {
        if(2 == pxClass.getTeacherType()) {
            TeacherOuter teacherOuter = teacherOuterDao.findOne(Long.valueOf(pxClass.getTeacherId()));
            pxClass.setTeacherName(teacherOuter.getTeacherName());
        }else if(1 == pxClass.getTeacherType()) {
            User user = userDaoImpl.getUser(Long.valueOf(pxClass.getTeacherId()));
            pxClass.setTeacherName(user.getName());
        }
        pxClass.setCreateTime(new Date());
        pxClass.setStatus(0);
        pxClass.setSclassHour(pxClass.getClassHour());
        pxClass.setSteacherScore(pxClass.getTeacherScore());
        pxClassDao.save(pxClass);
        return pxClass;
    }
    public PxClass updateClass(final PxClass pxClass) {
        if(2 == pxClass.getTeacherType()) {
            TeacherOuter teacherOuter = teacherOuterDao.findOne(Long.valueOf(pxClass.getTeacherId()));
            pxClass.setTeacherName(teacherOuter.getTeacherName());
        }else if(1 == pxClass.getTeacherType()) {
            User user = userDaoImpl.getUser(Long.valueOf(pxClass.getTeacherId()));
            pxClass.setTeacherName(user.getName());
        }
        pxClassDao.save(pxClass);
        return pxClass;
    }
    public void delClass(final PxClass pxClass) {
        pxClassDao.delete(pxClass.getId());
        pxClassStudentDao.deleteByClassId(Integer.valueOf(pxClass.getId().toString()));
    }
    public Page<PxClass> listClassByPage(final PxClass pxClassQuery, final Paginator page) {
        Specification<PxClass> sp = buildPxClass(pxClassQuery);
        PageRequest pageRequest = null;
        pageRequest = new PageRequest((int) page.getPageReOne(), (int) page.getLength());
        Page<PxClass> pages = null;
        pages = pxClassDao.findAll(sp, pageRequest);
        return pages;
    }

    private Specification<PxClass> buildPxClass(final PxClass q) {
        Specification<PxClass> sp = new Specification<PxClass>() {

            @Override
            public Predicate toPredicate(Root<PxClass> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (q.getId() != null) {
                    list.add(cb.equal(root.get("id").as(Long.class), q.getId()));
                }
                if (!StringUtils.isBlank(q.getClassName())) {
                    list.add(cb.equal(root.get("className").as(String.class), q.getClassName()));
                }
                if (q.getProjectId() != null) {
                    list.add(cb.equal(root.get("projectId").as(Long.class), q.getProjectId()));
                }
                Predicate[] ps = new Predicate[list.size()];
                query.where(cb.and(list.toArray(ps)));
                return query.getGroupRestriction();
            }
        };
        return sp;
    }
    public boolean updateteScore(PxClass pxClass) {
        pxClassDao.updateScore(pxClass.getPayClassScore(), pxClass.getSclassHour(), pxClass.getSteacherScore(), pxClass.getDegree(),pxClass.getId().intValue());
        return true;
    }

    public List<PxClass> findClassByProjectId(Long id) {
        return pxClassDao.findByProjectId(id.intValue());
    }
}
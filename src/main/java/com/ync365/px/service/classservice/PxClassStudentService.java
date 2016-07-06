package com.ync365.px.service.classservice;

import java.util.ArrayList;
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

import com.ync365.px.entity.PxClassStudent;
import com.ync365.px.model.Paginator;
import com.ync365.px.repository.PxClassStudentDao;

//Spring Bean的标识.
@Component
//类中所有public函数都纳入事务管理的标识.
@Transactional
public class PxClassStudentService {
    public final Logger logger = LoggerFactory.getLogger(PxClassStudentService.class);

    @Autowired
    private PxClassStudentDao pxClassStudentDao;

    public Page<PxClassStudent> getCsinfoByClassIdinPage(PxClassStudent pxClassStudent, Paginator page) {
        Specification<PxClassStudent> sp = buildPxClass(pxClassStudent);
        PageRequest pageRequest = null;
        pageRequest = new PageRequest((int) page.getStart(), (int) page.getLength());
        Page<PxClassStudent> pages = null;
        pages = pxClassStudentDao.findAll(sp, pageRequest);
        return pages;
    }
    private Specification<PxClassStudent> buildPxClass(final PxClassStudent q) {
        Specification<PxClassStudent> sp = new Specification<PxClassStudent>() {
            @Override
            public Predicate toPredicate(Root<PxClassStudent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (q.getClassId() != null) {
                    list.add(cb.equal(root.get("classId").as(Long.class), q.getClassId()));
                }
                Predicate[] ps = new Predicate[list.size()];
                query.where(cb.and(list.toArray(ps)));
                return query.getGroupRestriction();
            }
        };
        return sp;
    }
    
    public PxClassStudent findById(final Long id) {
        return pxClassStudentDao.findOne(id);
    }
    
    public boolean updateClassScorce(PxClassStudent pxClassStudent) {
        pxClassStudentDao.save(pxClassStudent);
        return true;
    }
}

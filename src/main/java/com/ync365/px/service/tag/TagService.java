package com.ync365.px.service.tag;

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

import com.ync365.px.entity.PxProjectTag;
import com.ync365.px.model.Paginator;
import com.ync365.px.repository.PxProjectTagDao;

//Spring Bean的标识.
@Component
//类中所有public函数都纳入事务管理的标识.
@Transactional
public class TagService {

    public final Logger logger = LoggerFactory.getLogger(TagService.class);

    @Autowired
    private PxProjectTagDao pxProjectTagDao;
    
    public Long count() {
        return pxProjectTagDao.count();
    }

    public PxProjectTag findById(final Long id) {
        return pxProjectTagDao.findOne(id);
    }
    public PxProjectTag insertTag(PxProjectTag pxProjectTag) {
        pxProjectTagDao.save(pxProjectTag);
        return pxProjectTag;
    }
    public PxProjectTag updateTag(final PxProjectTag pxProjectTag) {
        pxProjectTagDao.save(pxProjectTag);
        return pxProjectTag;
    }
    public void delTag(final PxProjectTag pxProjectTag) {
        pxProjectTagDao.delete(pxProjectTag.getId());
    }
    public Page<PxProjectTag> listTagByPage(final PxProjectTag pxProjectTag, final Paginator page) {
        Specification<PxProjectTag> sp = buildPxClass(pxProjectTag);
        PageRequest pageRequest = null;
        pageRequest = new PageRequest((int) page.getStart(), (int) page.getLength());
        Page<PxProjectTag> pages = null;
        pages = pxProjectTagDao.findAll(sp, pageRequest);
        return pages;
    }

    private Specification<PxProjectTag> buildPxClass(final PxProjectTag q) {
        Specification<PxProjectTag> sp = new Specification<PxProjectTag>() {

            @Override
            public Predicate toPredicate(Root<PxProjectTag> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (q.getId() != null) {
                    list.add(cb.equal(root.get("id").as(Long.class), q.getId()));
                }
                if (q.getIsUse() != null) {
                    list.add(cb.equal(root.get("isUse").as(Long.class), q.getIsUse()));
                }
                Predicate[] ps = new Predicate[list.size()];
                query.where(cb.and(list.toArray(ps)));
                return query.getGroupRestriction();
            }
        };
        return sp;
    }
}

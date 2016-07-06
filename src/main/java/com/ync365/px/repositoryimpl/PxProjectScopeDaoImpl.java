package com.ync365.px.repositoryimpl;

import com.ync365.px.model.bo.PxProjectBo;
import com.ync365.px.utils.PackModelUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ivan on 1/25/16.
 */
public class PxProjectScopeDaoImpl {
    @PersistenceContext
    private EntityManager em;

    public List<PxProjectBo> getPxProjectByScope(Long departmentId) {
        String sql = "select b.id as id," +
                " b.name as name," +
                "b.status as status," +
                "b.begin_time as begin_time," +
                "b.end_time as end_time ," +
                "b.ticket_time as ticket_time from" +
                "(select * from px_project_scope where department_id = "+departmentId+" or department_id = 1 ) a left join px_project b " +
                "on a.project_id = b.id where status = 1 or status = 2 order by b.create_time";
        Query q = em.createNativeQuery(sql);
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return PackModelUtil.packPxProjectBoFormNativeSql(q.getResultList());
    }
}

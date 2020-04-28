package com.cody.dynamicquery;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * ClassName: DynamicQueryImpl
 *
 * @author WQL
 * @Description:
 * @date: 2020/4/27 23:42
 * @since JDK 1.8
 */
@Service
public class DynamicQueryImpl implements DynamicQuery {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void save(Object entity) {
        em.persist(entity);
    }

    @Override
    public void update(Object entity) {
        em.merge(entity);
    }

    @Override
    public <T> void delete(Class<T> entityClass, Object entityId) {
        delete(entityClass, new Object[]{entityId});
    }

    @Override
    public <T> void delete(Class<T> entityClass, Object[] entityids) {
        for (Object id : entityids) {
            em.remove(em.getReference(entityClass, id));
        }
    }

    private Query createNativeQuery(String sql, Object... params) {
        Query q = em.createNativeQuery(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                q.setParameter(i + 1, params[i]);
            }
        }
        return q;
    }

    private <T> Query createNativeQuery(Class<T> resultClass, String sql, Object... params) {
        Query q = em.createNativeQuery(sql);
        q.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                q.setParameter(i + 1, params[i]);
            }
        }
        return q;
    }

    @Override
    public Long nativeQueryCount(String nativeSql, Object... params) {
        Object count = createNativeQuery(nativeSql, params).getSingleResult();
        return ((Number) count).longValue();
    }

    @Override
    public <T> List<T> nativeQueryPagingList(Class<T> resultClass, Pageable pageable, String naviteSql, Object... params) {
        Integer pageNumber = pageable.getPageNumber();
        Integer pageSize = pageable.getPageSize();
        Integer startPosition = pageNumber * pageSize;
        return createNativeQuery(resultClass, naviteSql, params).setFirstResult(startPosition).setMaxResults(pageSize)
                .getResultList();
    }
}

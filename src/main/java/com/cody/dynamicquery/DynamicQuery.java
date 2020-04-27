package com.cody.dynamicquery;


import org.springframework.data.domain.Pageable;

/**
 * ClassName: DynamicQuery
 *
 * @author WQL
 * @Description:
 * @date: 2020/4/27 23:36
 * @since JDK 1.8
 */
public interface DynamicQuery {

    void save(Object entity);

    void update(Object entity);

    <T> void delete(Class<T> entityClass, Object entityId);

    <T> void delete(Class<T> entityClass, Object[] entityids);

    /**
     * 执行nativeSql统计查询
     *
     * @param nativeSql
     * @param params
     * @return
     */
    Long nativeQieryCount(String nativeSql, Object... params);


    /**
     * 执行nativeSql分页查询
     *
     * @param resultClass
     * @param pageable
     * @param naviteSql
     * @param params
     * @param <T>
     * @return
     */
    <T> Long<T> naviveQueryPagingList(Class<T> resultClass, Pageable pageable, String naviteSql, Object... params);


}

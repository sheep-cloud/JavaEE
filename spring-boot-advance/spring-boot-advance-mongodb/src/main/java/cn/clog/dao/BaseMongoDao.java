package cn.clog.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

/**
 * 
 *
 * @author colg
 */
public interface BaseMongoDao<T> {

    /**
     * 通过条件查询实体（集合）
     *
     * @param query
     * @param entityClass
     * @param collectionName
     * @return
     * @author colg
     */
    public List<T> find(Query query, Class<T> entityClass, String collectionName);

}

package cn.clog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 *
 * @author colg
 */
@Repository
public class BaseMongoDaoImpl<T> implements BaseMongoDao<T> {

    /**
     * spring mongodb 集成操作类
     */
    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public List<T> find(Query query, Class<T> entityClass, String collectionName) {
        return mongoTemplate.find(query, entityClass, collectionName);
    }

}

package cn.clog.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import cn.clog.BaseTest;
import cn.clog.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试
 *
 * @author colg
 */
@Slf4j
public class BaseMongoDaoImplTest extends BaseTest {

    @Autowired
    private BaseMongoDao<User> baseMongoDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Test method for {@link cn.clog.dao.BaseMongoDaoImpl#find(org.springframework.data.mongodb.core.query.Query, java.lang.Class, java.lang.String)}.
     */
    @Test
    public final void testFind() {
        List<User> list = baseMongoDao.find(new Query(), User.class, "stus");
        log.info("BaseMongoDaoImplTest.testFind() >> list.size() : {}", list.size());
        log.info("BaseMongoDaoImplTest.testFind() >> list : {}", list);
    }

    @Test
    public void testName() throws Exception {
        List<User> list = mongoTemplate.find(new Query(), User.class, "stus");
        log.info("BaseMongoDaoImplTest.testName() >> list.size() : {}", list.size());
        log.info("BaseMongoDaoImplTest.testName() >> list : {}", list);
    }

}

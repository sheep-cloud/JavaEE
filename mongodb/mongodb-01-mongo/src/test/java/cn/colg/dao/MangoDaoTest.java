package cn.colg.dao;

import org.bson.Document;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;

/**
 * 使用 java 代码调用MongoDB 测试
 *
 * @author colg
 */
public class MangoDaoTest {

    private MongoClient client = new MongoClient("127.0.0.1", 27017);
    private MongoDatabase db = client.getDatabase("test");
    private MongoCollection<Document> collection = db.getCollection("stus");

    @Test
    public final void test() {
        Document document = new Document("User", "Jack");
        collection.insertOne(document);
    }

    @Test
    public void testName() throws Exception {
        Dict map = new Dict().set("User", "Rose").set("User", "Tom");
        Document document = new Document(map);
        collection.insertOne(document );
    }
    
    @Test
    public void testFind() throws Exception {
        FindIterable<Document> iterable = collection.find();
        for (Document document : iterable) {
            Console.log(JSON.toJSONString(document));
        }
    }
}

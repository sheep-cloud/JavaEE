package cn.colg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * MongoDB配置
 *
 * @author colg
 */
@Configuration
public class MongoConfig {

    /**
     * MongoDb保存实体类时，去掉 _class 字段
     *
     * @param mongoDbFactory
     * @param mappingContext
     * @return
     * @author colg
     */
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory mongoDbFactory, MongoMappingContext mappingContext) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingConverter;
    }
}

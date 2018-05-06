package cn.colg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JedicClusterCacheCondition implements Condition{

    @Value("${redis.model}")
    private String redisModel;
    
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        log.info("matches() >> 当前采用的redis模式 : {}", redisModel);
        if ("C".equalsIgnoreCase(redisModel)) {
            return true;
        }
        return false;
    }

}

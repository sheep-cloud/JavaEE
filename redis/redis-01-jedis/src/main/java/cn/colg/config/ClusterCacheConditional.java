package cn.colg.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 注入redis集群版 条件
 *
 * @author colg
 */
@Slf4j
public class ClusterCacheConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String model = context.getEnvironment().getProperty("redis.model");
        if ("C".equalsIgnoreCase(model)) {
            log.info("redis 集群启动... {}", DateUtil.now());
            return true;
        }
        return false;
    }

}

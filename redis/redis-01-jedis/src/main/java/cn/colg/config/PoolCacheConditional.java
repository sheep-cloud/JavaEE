package cn.colg.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 注入redis单机版 条件
 *
 * @author colg
 */
@Slf4j
public class PoolCacheConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String model = environment.getProperty("redis.model");
        if ("S".equalsIgnoreCase(model)) {
            log.info("redis 单机启动成功... {}", DateUtil.now());
            return true;
        }
        return false;
    }

}

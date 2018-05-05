package cn.colg.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import lombok.extern.slf4j.Slf4j;

/**
 * 判断是否是windows系统
 *
 * @author colg
 */
@Slf4j
public class WindowsConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty("os.name");
        String sys = "Windows";
        if (property.contains(sys)) {
            log.info("matches() >> 当前操作系统是 : {}", property);
            return true;
        }
        return false;
    }

}

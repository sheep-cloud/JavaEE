package cn.colg.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import lombok.extern.slf4j.Slf4j;

/**
 * 判断是否是linux系统
 *
 * @author colg
 */
@Slf4j
public class LinuxConditional implements Condition {

    /**
     * 确定条件是否匹配。
     *
     * @param context 上下文条件；判断条件，能使用的上下文（环境）
     * @param metadata 要检查的类或方法的元数据元数据。；注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        /// 是否linux系统
        // 获取环境信息
        Environment environment = context.getEnvironment();
        // 获取操作系统
        String property = environment.getProperty("os.name");
        
        /// 可以判断容器中bean注册情况，也可以给容器中注册bean
        
        // 获取bean定义的注册类
/*        BeanDefinitionRegistry registry = context.getRegistry();
        boolean containsBeanDefinition = registry.containsBeanDefinition("person");*/
        
        String sys = "Linux";
        if (property.contains(sys)) {
            log.info("matches() >> 当前操作系统是 : {}", property);
            return true;
        }
        return false;
    }

}

package cn.colg.plugin;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * MyBatis 自定义插件02
 *
 * @author colg
 */
@Intercepts(
    {@Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})}
)
@Slf4j
public class MySecondPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("MySecondPlugin.intercept() >> invocation.getMethod() : {}", invocation.getMethod());
        Object object = invocation.proceed();
        return object;
    }

    @Override
    public Object plugin(Object target) {
        log.info("MySecondPlugin.plugin() >> target.getClass() : {}", target.getClass());
        Object object = Plugin.wrap(target, this);
        return object;
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("enclosing_type.setProperties() >> 插件配置的信息 : {}", JSON.toJSONString(properties));
    }

}

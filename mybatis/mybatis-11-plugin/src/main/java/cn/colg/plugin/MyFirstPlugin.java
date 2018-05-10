package cn.colg.plugin;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * MyBatis 自定义插件01
 * 
 * <pre>
 * '@Intercepts'：   完成插件签名，告诉MyBatis当前插件用来拦截哪个对象的，哪个方法
 * </pre>
 *
 * @author colg
 */
@Intercepts(
        {@Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})}
    )
@Slf4j
public class MyFirstPlugin implements Interceptor {

    /**
     * intercept：拦截<br>
     * 
     * 拦截目标对象的目标方法的执行
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("MyFirstPlugin.intercept() >> invocation.getMethod() : {}", invocation.getMethod());
        
        // 动态的改变一下sql运行的参数，以前查询的是 id：1 员工，实际从数据库查询 id：23 员工
        log.info("MyFirstPlugin.intercept() >> 当前拦截到的对象 : {}", invocation.getTarget().getClass());
        // 拿到：StatementHandler==>ParameterHandler==>ParameterObject
        // 拿到target的元数据
        Object target = invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        log.info("MyFirstPlugin.intercept() >> sql语句用的参数是 : {}", value);
        // 修改sql语句要用的参数
        metaObject.setValue("parameterHandler.parameterObject", 23);
        // 执行目标方法
        Object object = invocation.proceed();
        // 返回执行后的返回值
        return object;
    }

    /**
     * plugin：包装目标对象；<br>
     * 
     * 包装：为目标对象创建一个代理对象
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        log.info("MyFirstPlugin.plugin() >> target.getClass() : {}", target.getClass());
        
        // 借助Plugin.wrap(target, interceptor); 方法包装目标对象
        Object object = Plugin.wrap(target, this);
        // 返回为当前target创建的动态代理对象
        return object;
    }

    /**
     * setProperties：将插件注册时的property属性设置进来
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        log.info("MyFirstPlugin.setProperties() >> 插件配置的信息 : {}", JSON.toJSONString(properties));
    }

}

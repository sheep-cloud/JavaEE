package cn.colg.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类；自动装配
 * 
 * <pre>
 * 自动装配：
 *  Spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值；
 *  
 *  1. '@Autowired'：    自动注入
 *      1. 默认有限按照类型去容器中找对应的组件：applicationContext.getBean(BookDao.class);
 *      2. 如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找
 *      3. '@Qualifier("bookDao")'：指定需要装配的组件的id，而不是使用属性名
 *      4. 自动装配默认一定要将属性赋值好，没有就会报错，可以使用'@Autowired(required = false)'
 *      5. '@Primary'：让Spring进行自动装配的时候，默认使用首选的Bean，也可以继续使用@Qualifier("bookDao")指定需要装配的组件的id，而不是使用属性名
 *      
 *  2. Spring还支持用'@Resource(JSR250)和Inject(JSR330)'[java规范的注解]
 *      '@Resource'：
 *          可以和'@Autowired'一样实现自动装配功能；默认是按照组件名称进行装配的；没有支持@Primary功能，没有'@Autowired(required = false)'功能
 *      '@Inject'：
 *          需要引入'javax.inject'依赖，和Autowired的功能一样，没有'@Autowired(required = false)'功能
 * </pre>
 *
 * @author colg
 */
@ComponentScan(basePackages = {"cn.colg.controller", "cn.colg.service", "cn.colg.dao"})
@Configuration
public class Config02OfAutowired {

}

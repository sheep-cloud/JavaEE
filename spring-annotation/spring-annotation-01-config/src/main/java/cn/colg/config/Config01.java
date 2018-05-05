package cn.colg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cn.colg.bean.Person;

/**
 * 配置类；'@ComponentScan'
 * 
 * <pre>
 * `@Configuration`：    告诉Spring这是一个配置类；指示一个类声明一个或多个@Bean方法，并且可以由Spring容器处理以在运行时为这些bean生成bean定义和服务请求
 * 
 * `@ComponentScan(basePackages = "cn.colg")`：  配置用于@Configuration类的组件扫描指令。提供与Spring XML的<context：component-scan>元素并行的支持。
 *  'Filter[] includeFilters() default {};'：    指定扫描的时候按照规则包含组件
 *  `useDefaultFilters = false`：                                        禁用扫描所有组件
 *  'Filter[] excludeFilters() default {};'：    指定扫描的时候按照规则排除组件
 *  '@Filter(type = '：
 *      FilterType.ANNOTATION：                         按照注解
 *      FilterType.ASSIGNABLE_TYPE：          按照给定的类型
 *      FilterType.ASPECTJ：                                  使用ASPECTJ表达式
 *      FilterType.REGEX：                                        使用正则指定
 *      FilterType.CUSTOM：                                     使用自定义规则
 *      
 * </pre>
 *
 * @author colg
 */
@Configuration
@ComponentScan(
    basePackages = "cn.colg"/*,
    includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Controller.class})},
    useDefaultFilters = false,
    excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})}*/
)
public class Config01 {

    /**
     * 给容器注册一个Bean；类型为返回值的类型，id默认是用方法名作为id
     *
     * @return
     */
    @Bean
    public Person person() {
        return new Person("Rose", 20);
    }
}

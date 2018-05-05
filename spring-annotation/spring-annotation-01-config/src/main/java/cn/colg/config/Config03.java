package cn.colg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import cn.colg.bean.Person;
import cn.colg.conditional.LinuxConditional;
import cn.colg.conditional.WindowsConditional;

/**
 * 配置类；'@Conditional'
 * 
 * <pre>
 * '@Conditional'：  按照一定的条件进行判断，满足条件给容器中注册bean
 * </pre>
 * 
 * @author colg
 */
@Configuration
public class Config03 {

    /*
     * colg  注册组件
     *  如果系统是windows，给容器中注册("bill")
     *  如果系统是linux，给容器中注册("linux")
     */

    /// ----------------------------------------------------------------------------------------------------

    @Conditional(value = {WindowsConditional.class})
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 62);
    }

    @Conditional(value = {LinuxConditional.class})
    @Bean("linus")
    public Person person02() {
        return new Person("linus", 48);
    }
}

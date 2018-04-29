package cn.colg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 启用
 *
 * @author colg
 */
@EnableSwagger2
@Configuration
public class Swagger2Bean {

    /**
     * 配置Swagger2
     *
     * @return
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                    .enable(true)
                    .groupName("02. spring-cloud-provider-dept-8002")
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("cn.colg.web"))
                    .paths(PathSelectors.any())
                    .build();
    }

    /**
     * 设置api基本信息
     * 
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                    .title("部门服务 API 提供")
                    .description("部门相关操作的接口")
                    .version("1.0.0")
                    .termsOfServiceUrl("https://github.com/colg-cloud/JavaEE")
                    .contact(new Contact("colg", "https://github.com/colg-cloud/JavaEE/tree/master/spring-cloud", "121529654@qq.com"))
                    .license("该文档仅限公司内部传阅")
                    .licenseUrl("https://springcloud.cc/")
                    .build();
    }
}

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
public class Swagger2 {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("cn.colg.web"))
                    .paths(PathSelectors.any())
                    .build();
    }

    /**
     * 设置api基本信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                    .title("spring-cloud-provider-dept-8001 API 提供者")
                    .termsOfServiceUrl("https://github.com/colg-cloud/JavaEE")
                    .contact(new Contact("colg", "https://github.com/colg-cloud/JavaEE/tree/master/spring-cloud", "121529654@qq.com"))
                    .version("1.0.0")
                    .build();
    }
}

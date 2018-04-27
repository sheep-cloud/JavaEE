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

    /**
     * 配置Swagger2
     * 
     * <pre>
     * enable：                                      是否启用swagger
     * groupName：                            api组的名字，会在swagger-ui的api下拉列表中显示；组名前的序号，多个组可以排序；最好不要写中文
     * apiInfo：                                  响应中包含的api元信息。
     * select：                                      api启动的构建器
     * apis：                                            扫描的包
     * </pre>
     *
     * @return
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                    .enable(true)
                    .groupName("01. spring-cloud-provider-dept-8001")
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("cn.colg.web"))
                    .paths(PathSelectors.any())
                    .build();
    }

    /**
     * 设置api基本信息
     * 
     * <pre>
     * title：                                           api组的标题，会在swagger-ui的标题处显示
     * description：                         pi组的描述，会在swagger-ui的描述中显示
     * version：                                     api版本
     * termsOfServiceUrl：       服务条款地址
     * contact：                                     文档联系人信息
     * license：                                     授权协议
     * licenseUrl：                           授权协议地址
     * </pre>
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

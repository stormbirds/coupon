package cn.stormbirds.coupon.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 *
 * <p> SwaggerConfig.java
 * </p>
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/29 17:50
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.stormbirds.coupon.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "电商优惠券活动实现 Swagger 实例文档",
                "我的博客网站：https://blog.stormbirds.cn，欢迎大家访问。",
                "API V1.0",
                null,
                new Contact("stormbirds", "https://blog.stormbirds.cn", "xbaojun@gmail.com"),
                "Apache", "https://opensource.org/licenses/Apache-2.0", Collections.emptyList());
    }
}
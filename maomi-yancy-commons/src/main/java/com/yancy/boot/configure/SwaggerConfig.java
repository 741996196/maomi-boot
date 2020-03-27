package com.yancy.boot.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//swagger的配置类
public class SwaggerConfig {

    @Value(value = "${spring.swagger2.enabled}")
    private Boolean swaggerEnabled;
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                .select()
                //一定要写上你的包名，代表需要生成接口文档的目录包
                .apis(RequestHandlerSelectors.basePackage("com.yancy.boot"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("spring boot swagger调用")
                //团队地址
                .termsOfServiceUrl("https://study.163.com/provider/106671292/index.htm")
                .version("1.0")
                .build();
    }
}

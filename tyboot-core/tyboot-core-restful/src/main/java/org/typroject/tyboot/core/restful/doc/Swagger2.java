package org.typroject.tyboot.core.restful.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by 子杨 on 2017/4/18.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())

                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(TycloudOperation.class))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("tycloud項目組")
                .description("更多tycloud相关文章请关注：http://tycloud.org/")
                .termsOfServiceUrl("http://tycloud.org")
                .contact("子杨")
                .version("0.1")
                .build();
    }
}

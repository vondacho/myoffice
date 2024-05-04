package edu.noia.myoffice.address.boot.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SpringfoxConfiguration {

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Address API")
                .apiInfo(apiInfo())
                .select()
                .paths(salePaths())
                .build()
                .enableUrlTemplating(true);
    }

    private Predicate<String> salePaths() {
        return s -> regex("/api/address.*").apply(s);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Address search API")
                .description("Endpoint for address and street search")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("1.0")
                .build();
    }
}

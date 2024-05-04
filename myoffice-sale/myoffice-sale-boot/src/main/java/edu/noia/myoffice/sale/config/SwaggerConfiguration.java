package edu.noia.myoffice.sale.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static java.util.Collections.singletonList;
import static springfox.documentation.builders.PathSelectors.regex;

@Import({BeanValidatorPluginsConfiguration.class})
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("myOffice API")
                .apiInfo(apiInfo())
                .select()
                .paths(salePaths())
                .build()
                .enableUrlTemplating(true);
        //.securitySchemes(singletonList(basicAuthScheme()))
        //.securityContexts(singletonList(securityContext()));
    }

    private Predicate<String> salePaths() {
        return s -> regex("/api/sale.*").apply(s);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("myOffice Sale API")
                .description("The Sale API is a part of the myOffice API and provides endpoints for Cart entity management, auditing and event streaming")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("1.0")
                .build();
    }

    private BasicAuth basicAuthScheme() {
        return new BasicAuth("myauth");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(salePaths())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(new SecurityReference("myauth", authorizationScopes));
    }

}

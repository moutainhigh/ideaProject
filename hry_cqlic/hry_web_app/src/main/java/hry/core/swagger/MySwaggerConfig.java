package hry.core.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.collect.Lists;
import hry.util.properties.PropertiesUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author denghuifan
 * @version V1.0.0
 * @description:
 * @create_time：2017年7月17日17:58:00
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@EnableSwaggerBootstrapUI
@ComponentScan(basePackages = {"hry"})
public class
MySwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    @Order(value = 1)
    public Docket appDocket() {

        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = Lists.newArrayList();
        parameterBuilder.name("token").description("需要登录的方法传token").modelRef(new ModelRef("String")).parameterType("header").required(true).build();
        parameters.add(parameterBuilder.build());
        //
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("base7.0Api")
                .enable("true".equals(PropertiesUtils.APP.getProperty("app.swagger")))
                .apiInfo(apiInfo("基础业务7.0接口文档", "基础业务7.0接口文档", "hry", "7.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("hry.app"))
                .paths(PathSelectors.any())
                .build();//.globalOperationParameters(parameters);

    }

    @Bean
    @Order(value = 2)
    public Docket leverDocket() {
        //
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("lcqb7.0Api")
                .enable("true".equals(PropertiesUtils.APP.getProperty("app.swagger")))
                .apiInfo(apiInfo("李超钱包7.0接口文档", "李超钱包7.0接口文档", "", "7.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("hry.lcqb"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo(String name, String description, String contact, String version) {
        return new ApiInfoBuilder()
                .title(name)
                .termsOfServiceUrl(description)
                .contact(contact)
                .version(version)
                .build();
    }
}

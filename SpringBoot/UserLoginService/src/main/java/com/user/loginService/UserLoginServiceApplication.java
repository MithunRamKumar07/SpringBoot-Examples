package com.user.loginService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class UserLoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserLoginServiceApplication.class, args);
	}
	
	
	@Bean
    public Docket docket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(generateApiInfo());
        
    }
	
	 private ApiInfo generateApiInfo()
	    {
	        return new ApiInfo("User Login Service", "This micro service validates the user in the database using Spring Security Framework.", "Version 1.0 - mw",
	            "", "Mithun Ram Kumar", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	    }
	
	

}

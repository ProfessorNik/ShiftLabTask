package balyasnikov.nikolay.computerstore.config;

import balyasnikov.nikolay.computerstore.domain.entity.*;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        TypeResolver typeResolver = new TypeResolver();
        return new Docket(DocumentationType.SWAGGER_2)
                .additionalModels(
                        typeResolver.resolve(HardDrive.class),
                        typeResolver.resolve(DesktopComputer.class),
                        typeResolver.resolve(Laptop.class),
                        typeResolver.resolve(Monitor.class),
                        typeResolver.resolve(Product.class))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "Nikolay Balyasnikov",
                "https://github.com/ProfessorNik/ShiftLabTask",
                "nikolay.balyasnikov@gmail.com");
        return new ApiInfoBuilder()
                .title("Computer Shop")
                .version("1.0.0")
                .contact(contact)
                .build();
    }
}

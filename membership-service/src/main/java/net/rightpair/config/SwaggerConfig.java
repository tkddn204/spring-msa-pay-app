package net.rightpair.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(appInfo())
                ;
    }

    private Info appInfo() {
        return new Info()
                .title(appName);
    }
}

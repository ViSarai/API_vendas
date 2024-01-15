package com.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API de Vendas")
                        .version("v1")
                        .description("Rest Api com Auth Jwt para gestão de produtos, clientes e pedidos.")
                        .contact(new Contact()
                                .url(" Linkedin: http://www.linkedin.com/in/viniciussarai")
                                .url(" GitHub: https://github.com/ViSarai")
                        ));
    }
}


package com.epam.tr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

@Configuration
@EnableWebMvc
public class WebConfig {
    @Bean
    public WebMvcConfigurer contentNegotiationConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                configurer.favorPathExtension(false)
                        .favorParameter(true)
                        .parameterName("mediaType")
                        .ignoreAcceptHeader(true)
                        .useJaf(false)
                        .defaultContentType(APPLICATION_JSON)
                        .mediaType("xml", APPLICATION_XML)
                        .mediaType("json", APPLICATION_JSON);
            }
        };
    }
}

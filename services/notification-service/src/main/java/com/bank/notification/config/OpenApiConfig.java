package com.bank.notification.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI notificationApi() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title(
                                        "Notification Service API")
                                .version("1.0")
                                .description(
                                        """
                                        Banking Notification Service

                                        Features

                                        - Redis Stream Consumer
                                        - Notification Storage
                                        - Email Integration
                                        - Fraud Alerts
                                        - Low Balance Alerts
                                        """)
                                .contact(
                                        new Contact()
                                                .name(
                                                        "Rejeena Banu")
                                )
                );
    }
}
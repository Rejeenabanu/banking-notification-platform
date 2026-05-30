package com.bank.transaction.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI transactionApi() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title(
                                        "Transaction Service API")
                                .version("1.0")
                                .description(
                                        """
                                        Banking Transaction Microservice

                                        Features:

                                        - Deposit
                                        - Withdraw
                                        - Transfer
                                        - Outbox Pattern
                                        - Redis Streams
                                        - Fraud Detection

                                        """)
                                .contact(
                                        new Contact()
                                                .name(
                                                        "Rejeena Banu")
                                )
                );
    }
}
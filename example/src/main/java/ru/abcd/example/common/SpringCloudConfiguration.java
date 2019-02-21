package ru.abcd.example.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(name="spring.rabbitmq.host")
@Configuration
public class SpringCloudConfiguration {

}

package com.orange.jmswrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.orange.jmswrapper")
public class JmsWrapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsWrapperApplication.class, args);
    }

}

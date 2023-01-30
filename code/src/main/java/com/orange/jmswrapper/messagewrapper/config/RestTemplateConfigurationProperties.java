package com.orange.jmswrapper.messagewrapper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationProperties(prefix = "rest")
@ConfigurationPropertiesScan
@Data
public class RestTemplateConfigurationProperties {
    private Integer maxConnectionsTotal;
    private Integer maxConnectionsPerRoute;
    private Integer readTimeout;
    private Integer connectionTimeout;
}


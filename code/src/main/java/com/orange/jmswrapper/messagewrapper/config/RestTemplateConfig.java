package com.orange.jmswrapper.messagewrapper.config;

import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {
    private final RestTemplateConfigurationProperties properties;

    @Bean
    public RestTemplate getRestTemplate()
    {
        return createRestTemplate(new PoolingHttpClientConnectionManager());
    }

    private RestTemplate createRestTemplate(PoolingHttpClientConnectionManager cm) {
        HttpClientBuilder builder = HttpClientBuilder.create();
        cm.setMaxTotal(properties.getMaxConnectionsTotal());
        cm.setDefaultMaxPerRoute(properties.getMaxConnectionsPerRoute());
        builder.setConnectionManager(cm);

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(builder.build());
        factory.setReadTimeout(properties.getReadTimeout());
        factory.setConnectTimeout(properties.getConnectionTimeout());

        RestTemplate template = new RestTemplate(new BufferingClientHttpRequestFactory(factory));
        template.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return template;
    }
}

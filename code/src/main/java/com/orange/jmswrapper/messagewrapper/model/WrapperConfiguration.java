package com.orange.jmswrapper.messagewrapper.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import java.util.Collection;


@ConfigurationProperties(prefix = "wrapper-config")
@ConfigurationPropertiesScan
@Data
public class WrapperConfiguration {
    private Collection<Wrapper> wrappers;
}

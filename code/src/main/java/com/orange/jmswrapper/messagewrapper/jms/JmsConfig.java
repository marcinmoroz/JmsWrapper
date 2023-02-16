package com.orange.jmswrapper.messagewrapper.jms;

import com.orange.jmswrapper.messagewrapper.dto.MessageWrapDTO;
import com.orange.jmswrapper.messagewrapper.model.ChannelType;
import com.orange.jmswrapper.messagewrapper.model.Wrapper;
import com.orange.jmswrapper.messagewrapper.model.WrapperConfiguration;
import com.orange.jmswrapper.messagewrapper.service.MessageWrapperService;
import jakarta.jms.JMSException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
@RequiredArgsConstructor
@Log4j2
public class JmsConfig implements JmsListenerConfigurer {
    private final WrapperConfiguration configuration;
    private final MessageWrapperService messageWrapperService;

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
        for (Wrapper wrapper: configuration.getWrappers()) {
            if(wrapper.getInput().getType() == ChannelType.JMS){
                DefaultJmsListenerContainerFactory factory = createJmsListenerContainerFactory(wrapper.getInput().getUrl());

                SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
                endpoint.setId("jmsEndpoint-" + wrapper.getDestination());
                endpoint.setDestination(wrapper.getInput().getChannel());
                String messageDestination = wrapper.getDestination();
                endpoint.setMessageListener(message -> {
                    try {
                        MessageWrapDTO messageWrapDTO = new MessageWrapDTO();
                        messageWrapDTO.setMessage(message.getBody(String.class));
                        messageWrapDTO.setDestination(messageDestination);
                        messageWrapperService.processMessage(messageWrapDTO, ChannelType.JMS);
                    } catch (JMSException e) {
                        throw new RuntimeException(e);
                    }
                });
                registrar.registerEndpoint(endpoint, factory);
            }
        }
    }

    private DefaultJmsListenerContainerFactory createJmsListenerContainerFactory(String brokerUrl) {
        DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
        containerFactory.setPubSubDomain(true);
        containerFactory.setConnectionFactory(connectionFactory(brokerUrl));
        containerFactory.setMessageConverter(jacksonJmsMsgConverter());
        containerFactory.setSubscriptionDurable(true);
        return containerFactory;
    }

    private CachingConnectionFactory connectionFactory(String brokerUrl) {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        ActiveMQConnectionFactory activeMQConnFactory = new ActiveMQConnectionFactory();
        try {
            activeMQConnFactory.setBrokerURL(brokerUrl);
        } catch (JMSException e) {
            log.error("Error in connectionFactory : " +e);
        }
        factory.setTargetConnectionFactory(activeMQConnFactory);
        //factory.setClientId("client123");
        return factory;
    }

    private MessageConverter jacksonJmsMsgConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}

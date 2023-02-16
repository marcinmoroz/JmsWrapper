package com.orange.jmswrapper.messagewrapper.service;

import com.orange.jmswrapper.messagewrapper.dto.MessageWrapDTO;
import com.orange.jmswrapper.messagewrapper.model.ChannelType;
import com.orange.jmswrapper.messagewrapper.model.Wrapper;
import com.orange.jmswrapper.messagewrapper.model.WrapperConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor
public class MessageWrapperService {
    private final WrapperConfiguration configuration;

    public CompletableFuture<Void> processMessage(MessageWrapDTO message, ChannelType inputChannel) {
        log.trace("Processing message from channel : " + inputChannel +" || message : " + message);
        return null;
    }

    public CompletableFuture<String> listConfiguration() {
        return CompletableFuture.completedFuture(configuration.getWrappers().stream()
                .map(Wrapper::toString).collect(Collectors.joining(";;")));
    }
}

package com.orange.jmswrapper.messagewrapper.service;

import com.orange.jmswrapper.messagewrapper.dto.MessageWrapDTO;
import com.orange.jmswrapper.messagewrapper.model.ChannelType;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class MessageWrapperService {
    public CompletableFuture<Void> processMessage(MessageWrapDTO message, ChannelType inputChannel) {
        return null;
    }
}

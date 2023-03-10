package com.orange.jmswrapper.messagewrapper.dto;

import lombok.Data;

@Data
public class MessageWrapDTO {
    private String destination;
    private String message;

    @Override
    public String toString() {
        return "MessageWrapDTO{" +
                "destination='" + destination + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

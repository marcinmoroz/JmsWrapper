package com.orange.jmswrapper.messagewrapper.model;

import lombok.Data;

@Data
public class Wrapper {
    private String destination;
    private ChannelProperties input;
    private ChannelProperties output;

    @Override
    public String toString() {
        return "Wrapper{" +
                "channelName='" + destination + '\'' +
                ", input=" + input +
                ", output=" + output +
                '}';
    }
}

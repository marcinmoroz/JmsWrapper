package com.orange.jmswrapper.messagewrapper.model;

import lombok.Data;

@Data
public class Wrapper {
    private String channelName;
    private ChannelType inputChannel;
    private ChannelType outputChannel;

    @Override
    public String toString() {
        return "Wrapper{" +
                "channelName='" + channelName + '\'' +
                ", inputChannel=" + inputChannel +
                ", outputChannel=" + outputChannel +
                '}';
    }
}

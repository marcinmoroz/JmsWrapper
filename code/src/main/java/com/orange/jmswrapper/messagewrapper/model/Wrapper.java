package com.orange.jmswrapper.messagewrapper.model;

import lombok.Data;

@Data
public class Wrapper {
    private String name;
    private ChannelType inputChannel;
    private ChannelType outputChannel;
}

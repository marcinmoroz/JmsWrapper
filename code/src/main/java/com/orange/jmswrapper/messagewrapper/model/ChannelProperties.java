package com.orange.jmswrapper.messagewrapper.model;

import lombok.Data;

@Data
public class ChannelProperties {
    ChannelType type;
    String url;
    String user;
    String password;
    String channel;

    @Override
    public String toString() {
        return "ChannelProperties{" +
                "inputChannel=" + type +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }
}

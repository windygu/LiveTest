package com.readyidu.source.local.anhui;

import com.readyidu.source.base.Channel;
import com.readyidu.source.base.Local;
import com.readyidu.source.protocol.SourceUri;

import java.util.HashMap;

/**
 * Created by 123 on 2017/6/27.
 */
public class AnhuiLocal extends Local{
    private static final String MANAGER_ID = "anhui";
    public AnhuiLocal(){
        managerId = MANAGER_ID;
        channels = new HashMap<String, Channel>();
        Channel channel1=new Anhui1Channel();
        Channel channel2=new Anhui2Channel();
        Channel channel3=new Anhui3Channel();
        Channel channel4=new Anhui4Channel();
        Channel channel5=new Anhui5Channel();
        Channel channel6=new Anhui6Channel();
        channels.put(channel1.getId(),channel1);
        channels.put(channel2.getId(),channel2);
        channels.put(channel3.getId(),channel3);
        channels.put(channel4.getId(),channel4);
        channels.put(channel5.getId(),channel5);
        channels.put(channel6.getId(),channel6);
    }
    @Override
    public String getSource(SourceUri uri) {
        Channel channel = channels.get(uri.getChannel());
        return channel.getSource(uri).toString();
    }
}

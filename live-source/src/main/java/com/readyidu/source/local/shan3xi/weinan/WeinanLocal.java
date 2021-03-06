package com.readyidu.source.local.shan3xi.weinan;

import com.readyidu.source.base.Channel;
import com.readyidu.source.base.Local;
import com.readyidu.source.local.zhejiang.hangzhou.*;
import com.readyidu.source.protocol.SourceUri;

import java.util.HashMap;

/**
 * Created by yuzhang on 17/6/8.
 */
public class WeinanLocal extends Local {

    private static final String MANAGER_ID = "weinan";

    public WeinanLocal() {
        managerId = MANAGER_ID;
        channels = new HashMap<String, Channel>();
        Channel channel1 = new Weinan1Channel();
        Channel channel2 = new Weinan2Channel();
        Channel channel3 = new Weinan3Channel();
        Channel channel4 = new Weinan4Channel();

        channels.put(channel1.getId(), channel1);
        channels.put(channel2.getId(), channel2);
        channels.put(channel3.getId(), channel3);
        channels.put(channel4.getId(), channel4);
    }

    @Override
    public String getSource(SourceUri uri) {
        Channel channel = channels.get(uri.getChannel());
        return channel.getSource(uri).toString();
    }
}

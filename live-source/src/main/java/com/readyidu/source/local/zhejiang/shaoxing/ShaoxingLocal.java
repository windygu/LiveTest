package com.readyidu.source.local.zhejiang.shaoxing;

import com.readyidu.source.base.Channel;
import com.readyidu.source.base.Local;
import com.readyidu.source.protocol.SourceUri;

import java.util.HashMap;

/**
 * Created by yuzhang on 17/6/13.
 */
public class ShaoxingLocal extends Local {

    private static final String MANAGER_ID = "shaoxing";

    public ShaoxingLocal() {
        managerId = MANAGER_ID;
        channels = new HashMap<String, Channel>();
        Channel channel1 = new SXTV1Channel();
        Channel channel2 = new SXTV2Channel();
        Channel channel3 = new SXTV3Channel();

        channels.put(channel1.getId(), channel1);
        channels.put(channel2.getId(), channel2);
        channels.put(channel3.getId(), channel3);
    }

    @Override
    public String getSource(SourceUri uri) {
        Channel channel = channels.get(uri.getChannel());
        return channel.getSource(uri).toString();
    }
}

package com.readyidu.source.local.zhejiang.deqing;

import com.readyidu.source.base.Channel;
import com.readyidu.source.base.Source;
import com.readyidu.source.local.zhejiang.deqing.source.YingxiSource;
import com.readyidu.source.local.zhejiang.pinghu.source.XigoSource;
import com.readyidu.source.protocol.SourceUri;
import com.readyidu.util.NullUtil;

/**
 * Created by yuzhang on 17/6/8.
 */
public class YINGXI1Channel extends Channel {

    private static final String CHANNEL_ID = "yingxi1";

    public YINGXI1Channel() {
        channelId = CHANNEL_ID;
    }

    @Override
    public Source getSource(SourceUri uri) {
        Source source = new YingxiSource(uri.getSource());
        if (NullUtil.isNullObject(source.toString())) {
            return null;
        }
        return source;
    }
}

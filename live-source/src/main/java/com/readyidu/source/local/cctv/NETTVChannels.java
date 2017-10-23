package com.readyidu.source.local.cctv;

import com.readyidu.source.base.Channel;
import com.readyidu.source.base.Source;
import com.readyidu.source.local.cctv.source.NETTVSource;
import com.readyidu.source.local.cctv.source.YichengSource;
import com.readyidu.source.protocol.SourceUri;
import com.readyidu.util.NullUtil;

/**
 * Created by 123 on 2017/10/17.
 */
public class NETTVChannels extends Channel {
    public NETTVChannels() {
        channelId = "cctv_nettv";
    }
    @Override
    public Source getSource(SourceUri uri) {
        Source source= new NETTVSource(uri.getSource());
        if (NullUtil.isNullObject(source.toString())) {
            return null;
        }
        return source;
    }
}

package com.readyidu.source.local.stv.source;

import com.readyidu.source.base.Source;
import com.readyidu.source.protocol.SourceConstants;
import com.readyidu.util.HeaderUtil;
import com.readyidu.util.HttpUtil;

import java.util.HashMap;

/**
 * Created by yuzhang on 17/6/9.
 */
public class FjtvSource extends Source {

    public FjtvSource(String sourceId) {
        super(sourceId);
    }

    @Override
    protected String source() {
        String fjtvStv = null;
        switch (sourceId) {
            case SourceConstants.SOURCE_FJTV_HAIXIA_STV:
                fjtvStv = HttpUtil.httpGet("http://v.fjtv.net/m2o/player/drml.php?hash=3b1154fe4a5cfc79be5fa38cb124d8cf&url=http%3A%2F%2Fstream6%2Efjtv%2Enet%2Fhaixia%2Fsd%2Flive%2Em3u8&time=1498208959848&refererurl=http%3A%2F%2Fhxtv%2Efjtv%2Enet%2F&playerVersion=4%2E035","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36","http://hxtv.fjtv.net/");
                break;
            case SourceConstants.SOURCE_FJTV_DONGNAN_STV:
                fjtvStv = HttpUtil.httpGet("http://v.fjtv.net/m2o/player/drml.php?url=http%3A%2F%2Fstream5%2Efjtv%2Enet%2Fdnpd%2Fhd%2Flive%2Em3u8&time=1498209630269&refererurl=http%3A%2F%2Fwww%2Esetv%2Ecom%2Ecn%2Flive%2F&hash=ad2b24805b03d5ae88491e840cd60b82&playerVersion=4%2E03","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36","http://www.setv.com.cn/live/");
                break;
            default:
                break;
        }
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("Referer", "http://www.setv.com.cn/live/");
        header.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        return HeaderUtil.addHeader(fjtvStv, header);
    }
}
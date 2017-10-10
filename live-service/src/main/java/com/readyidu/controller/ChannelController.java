package com.readyidu.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.readyidu.constants.NetworkCode;
import com.readyidu.model.ChannelSource;
import com.readyidu.service.CacheService;
import com.readyidu.service.ChannelService;
import com.readyidu.model.Channel;
import com.readyidu.model.ChannelType;
import com.readyidu.tools.JPushTool;
import com.readyidu.util.CacheUtil;
import com.readyidu.util.HttpUtil;
import com.readyidu.util.JsonResult;
import com.readyidu.util.NullUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/channel")
public class ChannelController {
    private static final String MASTER_SECRET="ae03c3cd69069d05f04a0290";
    private static final String APP_KEY="e27c9e82155e29b33d01a9e3";

    private static final String MESSAGE="直播缓存失效";

    @Resource(name = "channelService")
    private ChannelService channelService;

    @RequestMapping(value = "/channel.do", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getChannel(HttpServletRequest request,
                             HttpServletResponse response) {
        try {
            List<Channel> channelInfoList = channelService
                    .getChannelList();
            channelInfoList.addAll(channelService.getMovieToSource());
            return JsonResult.toString(NetworkCode.CODE_SUCCESS, channelInfoList);
        } catch (Exception e) {
            return JsonResult.toString(NetworkCode.CODE_FAIL, "");
        }
    }
    @RequestMapping(value = "/channelType.do", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getChannelType(HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            List<ChannelType> channelInfoType = channelService
                    .getChannelType();
            return JsonResult.toString(NetworkCode.CODE_SUCCESS, channelInfoType);
        } catch (Exception e) {
            return JsonResult.toString(NetworkCode.CODE_FAIL, "");
        }
    }

    @RequestMapping(value = "/channel.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String postChannel(HttpServletRequest request,
                              HttpServletResponse response) {
        try {
            List<Channel> channelInfoList = channelService
                    .getChannelList();
            channelInfoList.addAll(channelService.getMovieToSource());
            return JsonResult.toString(NetworkCode.CODE_SUCCESS, channelInfoList);
        } catch (Exception e) {
            return JsonResult.toString(NetworkCode.CODE_FAIL, "");
        }
    }

    @RequestMapping(value = "/channelType.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String postChannelType(HttpServletRequest request,
                                  HttpServletResponse response) {
        try {
            List<ChannelType> channelInfoType = channelService
                    .getChannelType();
            return JsonResult.toString(NetworkCode.CODE_SUCCESS, channelInfoType);
        } catch (Exception e) {
            return JsonResult.toString(NetworkCode.CODE_FAIL, "");
        }
    }
    @RequestMapping(value = "/mapCacheExpire.do",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String mapCacheExpire(){
        try {
            JPushTool.sendPush(MASTER_SECRET,APP_KEY,MESSAGE);
            return JsonResult.toString(NetworkCode.CODE_SUCCESS,"");
        }
        catch (Exception e){
            return JsonResult.toString(NetworkCode.CODE_FAIL,"");
        }
    }
    @RequestMapping(value = "/channelPlaybill.do", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String channelPlaybill(HttpServletRequest request){
        try {
            Map<String, Object> playBill = channelService.channelPlaybill(request);

            return JsonResult.toString(NetworkCode.CODE_SUCCESS,playBill);
        }catch (Exception e){
            return JsonResult.toString(NetworkCode.CODE_FAIL,"");
        }
    }
}
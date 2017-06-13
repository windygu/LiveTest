package com.readyidu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.readyidu.constants.NetworkCode;
import com.readyidu.service.ChannelService;
import com.readyidu.model.Channel;
import com.readyidu.model.ChannelType;
import com.readyidu.util.CacheUtil;
import com.readyidu.util.HttpUtil;
import com.readyidu.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/channel")
public class ChannelController {

    @Resource(name = "channelService")
    private ChannelService channelService;

    @RequestMapping(value = "/channel.do", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getChannel(HttpServletRequest request,
                             HttpServletResponse response) {

        CacheUtil.get("aaa");

        try {
            List<Channel> channelInfoList = channelService
                    .getChannelList();
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
}
package com.readyidu.service;

import java.util.List;

import com.readyidu.model.ConfInfo;

public interface AppChannelService {

    ConfInfo selectByAcount(Integer acount);

    void insertConf(ConfInfo confInfo);

    void updateConfinfo(ConfInfo confInfo);
    int checkBinding(int account,String deviceId,String tvAlias,String appAlias);
    boolean checkUserId(int userId);
    List<String> getSourceList(String url);

    /*判定用户是否绑定  如果绑定获取自定义源*/
    String checkByUserId(int userId);
}
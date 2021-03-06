package com.readyidu.service;

import com.readyidu.model.Channel;
import com.readyidu.model.ChannelType;
import com.readyidu.model.DemandChannel;
import com.readyidu.model.NewChannel;

import java.util.List;
import java.util.Map;

public interface ChannelService {
    public List<Channel> getChannelList();
    public List<Channel> getChannelListWithDeathSource(String source);
    public List<ChannelType> getChannelType();

    public int addChannel(String name);
    public Channel getChannel(Integer id);
    public int updateSource(Integer channelId, String source);
    public int removeSource(Integer channelId, Integer sourceId);
    public int reinstateSource(Integer channelId, Integer sourceId);
    public int changeType(Integer channelId, String typeId);
    public int removeChannel(Integer channelId);
    public List<DemandChannel> getMovieToSource();
    public Map<String,Object> channelPlaybill(String channelId);
    public List<Channel> selectChannelByKey(String key);
//    public Channel selectChannelById(Integer id);
    public List<Channel> selectAllNew();
    public List<Channel> selectHotChannel();
    public List<Map> getAllChannel();
    public List<Channel> getChannelWithoutSource();
    public List<ChannelType> getTypeList();
    public List<NewChannel> selectAppChannelByKey(String key);
    List<Integer> selectChannelByTypeId(String typeid,Integer appTypeId);
    NewChannel selectNewChannelById(Integer id);
    String getTypeById(Integer id);
    List<String>selectChannelIdByKey(String key);
    Channel selectChannelByChannelName(String channelName);
}

package com.readyidu.mapper;

import com.readyidu.model.PlayBillInfo;
import com.readyidu.model.Program;

import java.util.List;

/**
 * Created by 123 on 2017/11/22.
 */
public interface PlayBillInfoMapper {
    int insertBillInfo(PlayBillInfo playBillInfo);
    int cleanBillInfo(Integer channelId);
    List<PlayBillInfo> selectBill(PlayBillInfo playBillInfo);
    List<Program> selectBillProgram(Program program);
}

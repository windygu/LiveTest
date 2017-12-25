package com.readyidu.mapper;

import com.readyidu.model.PhoneDevice;
import org.springframework.stereotype.Component;

/**
 * Created by ypf on 2017/12/25.
 */
@Component
public interface PhoneDeviceMapper {
    String selectDeviceIdByUserId(int userId);
    void delete(int userId);
    int getCountByDeviceId(String deviceId);
    void insertDevice(PhoneDevice phoneDevice);
}

package com.readyidu.service.impl;

import com.alibaba.fastjson.JSON;
import com.readyidu.constants.NetworkCode;
import com.readyidu.mapper.PhoneDeviceMapper;
import com.readyidu.mapper.PhoneServiceMapper;
import com.readyidu.mapper.TvDeviceMapper;
import com.readyidu.model.PhoneDevice;
import com.readyidu.model.PhoneService;
import com.readyidu.model.TvDevice;
import com.readyidu.service.CacheService;
import com.readyidu.service.TvSynchronizeService;
import com.readyidu.tools.QiNiuUploadTool;
import com.readyidu.util.JsonResult;
import com.readyidu.util.NullUtil;
import com.readyidu.util.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.MD5;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.util.*;


@Service(value = "tvSynchronizeService")
public class TvSynchronizeServiceImpl implements TvSynchronizeService{
    @Autowired
    private TvDeviceMapper tvDeviceMapper;

    @Autowired
    private PhoneDeviceMapper phoneDeviceMapper;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private  PhoneServiceMapper phoneServiceMapper;

    /*app端扫描后该访问的地址*/
    private static final String QRDecodeUrl = "app/bundling";

    /*获取绑定的设备列表*/
    @Override
    public String getDevices(String deviceId) {
        /*如果传参为空 返回请求成功代码 空数据*/
        if (NullUtil.isNullObject(deviceId)){
            return JsonResult.toString(NetworkCode.CODE_SUCCESS_NULL,new ArrayList<>());
        }
        List<PhoneDevice> phoneDevices = phoneDeviceMapper.listByDeviceId(deviceId);
        System.out.println(phoneDevices);
        if (phoneDevices.isEmpty()){
           return JsonResult.toString(NetworkCode.CODE_SUCCESS_NULL, new ArrayList<>());
        }
        return JsonResult.toString(NetworkCode.CODE_SUCCESS,phoneDevices);
    }

    /*通过穿过来的小益账号 解绑机顶盒*/
    @Transactional
    @Override
    public String removePhoneByDeviceId(String userId) {
        int i= phoneDeviceMapper.deleteByUserId(userId);
        if (i==0){
          return   JsonResult.toString(NetworkCode.CODE_FAIL,"");
        }
        return JsonResult.toString(NetworkCode.CODE_SUCCESS,"");
    }

    /*获得自定义源*/
    @Override
    public List<PhoneService> getCostomizeSourceList(String deviceId) {
        return phoneServiceMapper.getConfUrlAndUserIdByDeviceId(deviceId);
    }


    /*获取二维码*/
    @Override
    public String getQRCode(String tvDeviceId,String tvAlias,String contextPath) {
       /*1.业务要求：判断数据库是否存在该机顶盒数据  如果没有新增*/
        TvDevice tvDevice=tvDeviceMapper.getByDeviceId(tvDeviceId);
        if (NullUtil.isNullObject(tvDevice)){
          /*2.如果为空调用 其他接口方法生成新的设备数据*/
            int i = addTvDevice(tvDeviceId,tvAlias);
        }
        tvDevice=tvDeviceMapper.getByDeviceId(tvDeviceId);
        /*判断获取设备的别名是否与传递的一致，一致才继续执行*/
        if (tvAlias.equals(tvDevice.getTvAlias())) {
        /*3.标识唯一性，生成令牌*/
            String token = UUID.randomUUID().toString();
            System.out.println(token);
            Map<String, String> map = new HashMap<>();
            map.put("qRDecodeUrl", QRDecodeUrl);
            map.put("token", token);
            map.put("tvDeviceId", tvDeviceId);
            map.put("tvAlias", tvAlias);
            String tvDeviceContent = JSON.toJSONString(map);
        /*4.redis 作为定时器  设置为30 分钟 客户端扫描二维码后 获取令牌 后台验证如果存在此令牌则验证通过*/
            cacheService.set(token + tvDeviceId, "1", CacheService.CACHE_TIMEOUT30);
            try {
                QRCodeUtil.encode(tvDeviceContent,null,contextPath+"/img/",false,tvDeviceId);
                String data = QiNiuUploadTool.upLoad(contextPath +"/img/"+ tvDeviceId + ".jpg", "y"+tvDeviceId + "d.jpg");
                System.out.println(data);
                File file=new File(contextPath+"/img/"+tvDeviceId+".jpg");
                if (file.exists()){
                    file.delete();
                }
                return JsonResult.toString(NetworkCode.CODE_SUCCESS,data+"?"+new Random().nextInt(99999));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JsonResult.toString(NetworkCode.CODE_FAIL,"");
    }


    @Transactional
    @Override
    public int addTvDevice(String tvDeviceId,String tvAlias) {
        if (NullUtil.isNullObject(tvDeviceId)){
            return 0;
        }
        TvDevice tvDevice=new TvDevice();
        tvDevice.setTvAlias(tvAlias);
        tvDevice.setDeviceId(tvDeviceId);
        tvDevice.setCreareTime(new Date());
        tvDevice.setIsDelete(0);
        return tvDeviceMapper.insertTvDevice(tvDevice);
    }

    @Transactional
    @Override
    public int insertPhoneDevice(PhoneDevice phoneDevice) {
        return phoneDeviceMapper.insertDevice(phoneDevice);
    }
}
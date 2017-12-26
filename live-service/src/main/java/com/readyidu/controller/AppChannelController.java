package com.readyidu.controller;


import com.google.gson.Gson;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.readyidu.constants.NetworkCode;
import com.readyidu.filter.HeaderFilter;
import com.readyidu.mapper.ConfInfoMapper;
import com.readyidu.model.ConfInfo;
import com.readyidu.pojo.RequestParamModel;
import com.readyidu.service.AppChannelService;
import com.readyidu.service.CacheService;
import com.readyidu.service.impl.AppChannelServiceImpl;
import com.readyidu.tools.QiNiuUploadTool;
import com.readyidu.util.JsonResult;
import com.readyidu.util.NullUtil;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * App端数据绑定接口
 * Created by 123 on 2017/12/25.
 */
@Controller
@RequestMapping("/app")
public class AppChannelController {

    @Autowired
    private CacheService cacheService;
    @Autowired
    private AppChannelService appChannelService;


    @RequestMapping("/getQiniuToken")
    public String getQiNiuToken(){
        RequestParamModel requestParamModel = HeaderFilter.paramModel.get();
        if (getAccessTimes(requestParamModel.getRemoteHost(),"getQiniuToken")<=3)
            return JsonResult.toString(NetworkCode.CODE_SUCCESS, QiNiuUploadTool.getToken());
        return JsonResult.toString(NetworkCode.CODE_FAIL,"");
    }

    /**
     * 同步文件上传回调接口
     * @return
     */
    @RequestMapping("/callBackUpdate")
    public String callBackUpdate(HttpServletResponse response){
//        String accessKey = response
//
//        String secretKey = response.getParameter("QINIU_SECRET_KEY");
//        Auth auth = Auth.create(accessKey, secretKey);
//        //回调地址
//        String callbackUrl = "218.75.36.107:18911/app/callBackUpdate";
//        //定义回调内容的组织格式，与上传策略中的callbackBodyType要保持一致
//        //String callbackBodyType = "application/x-www-form-urlencoded"; //回调鉴权的签名包括请求内容callbackBody
//        String callbackBodyType = "application/json";//回调鉴权的签名不包括请求内容
//        /**
//         * 这两个参数根据实际所使用的HTTP框架进行获取
//         */
//        //通过获取请求的HTTP头部Authorization字段获得
//        String callbackAuthHeader = request.getHeader("Authorization");
//        //通过读取回调POST请求体获得，不要设置为null
//        byte[] callbackBody =  request.get
//        boolean validCallback = auth.isValidCallback(callbackAuthHeader, callbackUrl, callbackBody, callbackBodyType);
//        if(validCallback){
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            String version = String.valueOf(requestParamModel.getVersion());
//            int acount = requestParamModel.getAccount();
//            ConfInfo confInfo = new ConfInfo();
//            confInfo.setAcount(acount);
//            String confUrl = bucket + hash ;
//            confInfo.setConfUrl(confUrl);
//            confInfo.setHash(hash);
//            confInfo.setVersion(version);
//            if (NullUtil.isNullObject(appChannelService.selectByAcount(acount))){
//                appChannelService.insertConf(confInfo);
//            }else {
//                appChannelService.updateConfinfo(confInfo);
//            }
//            return JsonResult.toString(NetworkCode.CODE_SUCCESS,"");
//        }else {
//            return JsonResult.toString(NetworkCode.CODE_FAIL,"");
//        }
        return null;
    }

    /**
     * app端手机绑定
     * @return
     */
    @RequestMapping("/bundling")
    public String bundling(String token,String alias){
        RequestParamModel requestParamModel = HeaderFilter.paramModel.get();
        int account = requestParamModel.getAccount();
        String deviceId = requestParamModel.getDeviceId();
        if(!NullUtil.isNullObject(cacheService.get(token+deviceId))){
            appChannelService.checkBinding(account,deviceId,alias);
            return JsonResult.toString(NetworkCode.CODE_SUCCESS,"");
        }
        return JsonResult.toString(NetworkCode.CODE_FAIL,"");
    }

    /**
     * app端获取自定义频道列表
     * @return
     */
    @RequestMapping("/getCustomizedList")
    public String getCustomizedList(){
        return null;
    }

    private int getAccessTimes(String remoteHost,String interFaceName){
        String cacheKey = remoteHost + interFaceName + "countNum";
        String accessTimes = cacheService.get(cacheKey);
        if (!NullUtil.isNullObject(accessTimes)&&!accessTimes.equals("null"))
            return Integer.valueOf(accessTimes) + 1;
        return 0;
    }
}

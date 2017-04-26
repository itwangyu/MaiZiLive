package com.maizi.maizilive.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.HashMap;
import java.util.Map;

/**
 * 对鸿样大神的OkhttpUtils再次封装一层，添加一些全局的请求参数
 * okhttputils见 https://github.com/hongyangAndroid/okhttputils
 * Created by wangyu on 2017/3/10 0010.
 */

public class HttpUtils {
    public static RequestCall requestCall;
    public static void get(String url, Map<String,String> map,HttpCallback callback){
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("accessPort", "4");
        map.put("version","1.0");
        requestCall = OkHttpUtils.get().url(url).params(map).tag(url).build();
        requestCall. execute(callback);
    }

    public static void post(String url, Map<String,String> map,HttpCallback callback){
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("accessPort", "4");
        map.put("version","1.0");
        requestCall = OkHttpUtils.post().url(url).params(map).tag(url).build();
        requestCall.execute(callback);
    }
    public static void cancal() {
        if (requestCall != null) {
            requestCall.cancel();
        }
    }
}

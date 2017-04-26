package com.maizi.maizilive.utils;


import android.util.Log;

import com.google.gson.Gson;
import com.maizi.maizilive.bean.BaseBean;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * http请求回调类，在这里拦截一下所有的请求回调，便于对所有的请求结果进行统一处理
 * Created by wangyu on 2017/3/10 0010.
 */

public abstract class HttpCallback<T extends BaseBean> extends Callback<T> {
    private Class<T> entityClass = null;
    public HttpCallback() {
    }

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
//        baseView.showLoadingDialog();
    }

    @Override
    public void onAfter(int id) {
        super.onAfter(id);
//        baseView.dismissLoadingDialog();
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String s=response.body().string();
        return  new Gson().fromJson(s,getEntityClass());
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        e.printStackTrace();
        Log.i("wangyu","http请求异常:"+ e.toString());
        onFail(call,e,id);
    }

    @Override
    public void onResponse(T response, int id) {
        /**
         * 可以在这里进行一切全局的判断,比如返回code=-99，直接弹出登录页面
         */
        Log.i("wangyu",response.toString());
        onSuccess(response,id);
    }

    /**
     * 获取泛型T的Class
     * @return
     */
    public Class<T> getEntityClass() {
        Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            entityClass = (Class<T>)p[0];
        }
        return  entityClass;
    }
    public abstract void onSuccess(T response, int id);
    public abstract void onFail(Call call, Exception e, int id);
}

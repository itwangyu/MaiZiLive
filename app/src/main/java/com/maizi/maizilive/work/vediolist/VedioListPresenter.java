package com.maizi.maizilive.work.vediolist;

import com.maizi.maizilive.bean.PDResponse;
import com.maizi.maizilive.mvp.BasePresenterImpl;
import com.maizi.maizilive.utils.HttpCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Request;


/**
 * MVPPlugin
 */

public class VedioListPresenter extends BasePresenterImpl<VedioListContract.View> implements VedioListContract.Presenter{

    @Override
    public void getVedioList() {
        String url = "http://static.api.m.panda.tv/android_hd/alllist_.json";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("pageno", "1")
                .build()
                .execute(new HttpCallback<PDResponse>() {
                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        mView.showLoadingLayout();
                    }

                    @Override
                    public void onSuccess(PDResponse response, int id) {
                        mView.showVedioList(response.data.getItems());
                        mView.showContentLayout();
                    }

                    @Override
                    public void onFail(Call call, Exception e, int id) {
                        mView.showErrowLayout();
                    }
                });
    }
}

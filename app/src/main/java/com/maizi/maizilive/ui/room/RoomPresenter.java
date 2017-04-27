package com.maizi.maizilive.ui.room;

import com.maizi.maizilive.bean.RoomInfoEntity;
import com.maizi.maizilive.mvp.BasePresenterImpl;
import com.maizi.maizilive.utils.HttpCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Request;


/**
 * MVPPlugin
 */

public class RoomPresenter extends BasePresenterImpl<RoomContract.View> implements RoomContract.Presenter{

    @Override
    public void getRoomInfo(String id) {
        String url = "http://www.panda.tv/api_room_v2";
        OkHttpUtils.get()
                .addParams("roomid", id)
                .url(url)
                .build().execute(new HttpCallback<RoomInfoEntity>(){
            @Override
            public void onBefore(Request request, int id) {
                mView.showLoadingLayout();
            }

            @Override
            public void onSuccess(RoomInfoEntity pdDetails, int id) {
                mView.showContentLayout();
                String plflag = pdDetails.data.getVideoinfo().getPlflag();
                String url = "http://pl-hls" + plflag.substring(plflag.indexOf("_") + 1, plflag.length()) + ".live.panda.tv/live_panda/" + pdDetails.data.getVideoinfo().getRoom_key() + ".m3u8";
                mView.showVedio(url);
            }

            @Override
            public void onFail(Call call, Exception e, int id) {
                mView.showErrowLayout();
            }
        });
    }
}

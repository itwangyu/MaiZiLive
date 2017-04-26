package com.maizi.maizilive.mvp;


import com.maizi.maizilive.utils.HttpUtils;

/**
 * MVPPlugin
 */

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V>{
    protected V mView;
    @Override
    public void attachView(V view) {
        mView=view;
    }

    @Override
    public void detachView() {
        HttpUtils.cancal();
        mView=null;

    }
}

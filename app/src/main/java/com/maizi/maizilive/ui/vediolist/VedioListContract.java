package com.maizi.maizilive.ui.vediolist;

import com.maizi.maizilive.bean.PDRoom;
import com.maizi.maizilive.mvp.BasePresenter;
import com.maizi.maizilive.mvp.BaseView;

import java.util.List;


/**
 * MVPPlugin
 */

public class VedioListContract {
    interface View extends BaseView {
        void showVedioList(List<PDRoom.Room> list);
    }

    interface  Presenter extends BasePresenter<View> {
        void getVedioList();
    }
}

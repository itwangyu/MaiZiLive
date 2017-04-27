package com.maizi.maizilive.ui.room;

import com.maizi.maizilive.mvp.BasePresenter;
import com.maizi.maizilive.mvp.BaseView;


/**
 * MVPPlugin
 */

public class RoomContract {
    interface View extends BaseView {
        void showVedio(String url);
    }

    interface  Presenter extends BasePresenter<View> {
        void getRoomInfo(String id);
    }
}

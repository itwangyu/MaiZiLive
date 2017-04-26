package com.maizi.maizilive.mvp;

import android.content.Context;

/**
 * MVPPlugin
 */

public interface BaseView {
     Context getContext();
     int getContentLayout();
     void showLoadingLayout();
     void showContentLayout();
     void showErrowLayout();
}

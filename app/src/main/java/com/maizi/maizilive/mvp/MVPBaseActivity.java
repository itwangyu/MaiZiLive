package com.maizi.maizilive.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.maizi.maizilive.R;
import com.maizi.maizilive.view.BaseLayout;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;


/**
 * MVPPlugin
 */

public abstract class MVPBaseActivity<V extends BaseView,T extends BasePresenterImpl<V>> extends AppCompatActivity implements BaseView{
    public T mPresenter;
    private BaseLayout baseLayout;

    public abstract void init();
    public Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= getInstance(this,1);
        mPresenter.attachView((V) this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.themecolor));
        context=this;
        setView(getContentLayout());
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
        mPresenter.detachView();
    }

    protected void setView(int layoutId) {
        baseLayout = new BaseLayout(context, layoutId);
        setContentView(baseLayout);
    }

    @Override
    public Context getContext(){
        return this;
    }

    public  <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        if (baseLayout != null) {
//          baseLayout.startLoadingAnimation();
//        }
    }


    @Override
    public void showLoadingLayout() {
        if (baseLayout != null) {
            baseLayout.loadingLayout.setVisibility(View.VISIBLE);
            baseLayout.startLoadingAnimation();
            baseLayout.errorLayout.setVisibility(View.GONE);
            baseLayout.contentLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrowLayout() {
        if (baseLayout != null) {
            baseLayout.loadingLayout.setVisibility(View.GONE);
            baseLayout.stopLoadingAnimation();
            baseLayout.errorLayout.setVisibility(View.VISIBLE);
            baseLayout.contentLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showContentLayout() {
        if (baseLayout != null) {
            baseLayout.loadingLayout.setVisibility(View.GONE);
            baseLayout.stopLoadingAnimation();
            baseLayout.errorLayout.setVisibility(View.GONE);
            baseLayout.contentLayout.setVisibility(View.VISIBLE);
        }
    }


}

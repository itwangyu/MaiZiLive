package com.maizi.maizilive.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maizi.maizilive.view.BaseLayout;

import java.lang.reflect.ParameterizedType;

/**
 * MVPPlugin
 */

public abstract class MVPBaseFragment<V extends BaseView,T extends BasePresenterImpl<V>> extends Fragment implements BaseView{
    public T mPresenter;
    private BaseLayout baseLayout;
    public Context context;
    protected abstract int getLayoutId();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= getInstance(this,1);
        mPresenter.attachView((V) this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context=getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
            mPresenter.detachView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseLayout = new BaseLayout(context, getLayoutId());
        return baseLayout;
    }
    @Override
    public Context getContext() {
        return super.getContext();
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
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
            return null;
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

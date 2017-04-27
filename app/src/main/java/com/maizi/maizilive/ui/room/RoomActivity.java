package com.maizi.maizilive.ui.room;


import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.View;

import com.maizi.maizilive.R;
import com.maizi.maizilive.mvp.MVPBaseActivity;
import com.maizi.maizilive.view.DanmakuVideoPlayer;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.listener.SampleListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;

/**
 * MVPPlugin
 */

public class RoomActivity extends MVPBaseActivity<RoomContract.View, RoomPresenter> implements RoomContract.View {

    @BindView(R.id.videoplayer)
    DanmakuVideoPlayer videoplayer;
    private OrientationUtils orientationUtils;
    public boolean isPlay;
    private boolean isPause;

    @Override
    public void init() {
        String id = getIntent().getStringExtra("id");
        mPresenter.getRoomInfo(id);
        initVideoPlayer();
    }

    private void initVideoPlayer() {
        //增加封面
//        videoplayer.setThumbImageView(holder.imageView);
        //非全屏下，不显示title
        videoplayer.getTitleTextView().setVisibility(View.GONE);
        //非全屏下不显示返回键
        videoplayer.getBackButton().setVisibility(View.GONE);
        //打开非全屏下触摸效果
        videoplayer.setIsTouchChangePosition(false);
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, videoplayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        videoplayer.setIsTouchWiget(true);
        //关闭自动旋转
//        videoplayer.setRotateViewAuto(false);
//        videoplayer.setLockLand(false);
        videoplayer.setShowFullAnimation(false);
        videoplayer.setNeedLockFull(true);

        //detailPlayer.setOpenPreView(true);
        videoplayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                videoplayer.startWindowFullscreen(RoomActivity.this, true, true);
            }
        });

        videoplayer.setStandardVideoAllCallBack(new SampleListener() {

            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        videoplayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_vedio;
    }

    @Override
    public void showVedio(String url) {
        //设置播放url，第一个url，第二个开始缓存，第三个使用默认缓存路径，第四个设置title
        videoplayer.setUp(url, false, null, "这是title");
        //立即播放
        videoplayer.startPlayLogic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause=true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause=false;
    }

    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!videoplayer.isIfCurrentIsFullscreen()) {
                    videoplayer.startWindowFullscreen(RoomActivity.this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (videoplayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }
}

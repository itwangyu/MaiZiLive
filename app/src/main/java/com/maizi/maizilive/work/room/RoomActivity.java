package com.maizi.maizilive.work.room;


import android.view.WindowManager;

import com.maizi.maizilive.R;
import com.maizi.maizilive.ijkplayer.media.widget.media.IjkVideoView;
import com.maizi.maizilive.mvp.MVPBaseActivity;

import butterknife.BindView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * MVPPlugin
 */

public class RoomActivity extends MVPBaseActivity<RoomContract.View, RoomPresenter> implements RoomContract.View {

    @BindView(R.id.ijkvideoview)
    IjkVideoView ijkvideoview;
    private boolean mBackPressed;

    @Override
    public void init() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

       String id= getIntent().getStringExtra("id");
        mPresenter.getRoomInfo(id);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_vedio;
    }

    @Override
    public void showVedio(String url) {

        ijkvideoview.setVideoPath("http://flv.quanmin.tv/live/44397_L3.flv");
        ijkvideoview.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //点击返回或不允许后台播放时 释放资源
        if (mBackPressed || !ijkvideoview.isBackgroundPlayEnabled()) {
            ijkvideoview.stopPlayback();
            ijkvideoview.release(true);
            ijkvideoview.stopBackgroundPlay();
        } else {
            ijkvideoview.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mBackPressed = true;
    }
}

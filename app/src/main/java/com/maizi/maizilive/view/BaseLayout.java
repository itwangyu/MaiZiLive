package com.maizi.maizilive.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maizi.maizilive.R;


public class BaseLayout extends RelativeLayout {

	public TextView tv_header;
	private TextView tv_title_right;
    //标题栏
	public RelativeLayout header_bar;
	public ImageView iv_left,iv_right;
	public LinearLayout ll_title_right;
	public ImageView iv_title_pic;
    //网络错误布局
	public View errorLayout;
    //加载中的布局
    public View loadingLayout;
    //内容布局
    public View contentLayout;
    public TextView tv_loaning_again;
    private ProgressBar web_progressbar;
    public ImageView ivLoading;
    private AnimationDrawable anim;

    public BaseLayout(Context context, int layoutResourceId) {
		super(context);
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		header_bar = (RelativeLayout) layoutInflater.inflate(R.layout.titilebar, null);
		header_bar.setId(R.id.ll_title);

        iv_left = (ImageView) header_bar.findViewById(R.id.iv_back);
		iv_right = (ImageView) header_bar.findViewById(R.id.iv_finish);
		tv_header = (TextView) header_bar.findViewById(R.id.tv_title);
		tv_title_right = (TextView) header_bar.findViewById(R.id.tv_title_right);
		ll_title_right = (LinearLayout) header_bar.findViewById(R.id.ll_title_right);
		iv_title_pic = (ImageView) header_bar.findViewById(R.id.iv_title_pic);
//		web_progressbar = (ProgressBar) header_bar.findViewById(R.id.my_progress);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        //添加titilebar
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//		addView(header_bar, params);
        //添加网络错误布局
		errorLayout = layoutInflater.inflate(R.layout.layout_error, null);
		tv_loaning_again = (TextView) errorLayout.findViewById(R.id.tv_reload);
		params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.BELOW, R.id.ll_title);
		addView(errorLayout, params);

        //添加加载中布局
        loadingLayout = layoutInflater.inflate(R.layout.layout_loading, null);
        ivLoading = (ImageView) loadingLayout.findViewById(R.id.iv_loading);
        addView(loadingLayout,params);

        contentLayout = layoutInflater.inflate(layoutResourceId, null);
		params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//		params.addRule(RelativeLayout.BELOW, R.id.ll_error);
		addView(contentLayout, params);
	}

	public BaseLayout(Context context, View layoutView, boolean hasTitle) {
		super(context);
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        if (hasTitle) {
            //添加标题栏
            header_bar = (RelativeLayout) layoutInflater.inflate(R.layout.titilebar, null);
            header_bar.setId(R.id.ll_title);
//        if(Build.VERSION.SDK_INT >= 19) {
//            header_bar.addView(createStatusBarView(context,0x00fc5b3d),0);
//        }
            iv_left = (ImageView) header_bar.findViewById(R.id.iv_back);
            iv_right = (ImageView) header_bar.findViewById(R.id.iv_finish);
            tv_header = (TextView) header_bar.findViewById(R.id.tv_title);
            tv_title_right = (TextView) header_bar.findViewById(R.id.tv_title_right);
            ll_title_right = (LinearLayout) header_bar.findViewById(R.id.ll_title_right);
            iv_title_pic = (ImageView) header_bar.findViewById(R.id.iv_title_pic);

            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            addView(header_bar, params);
        }
        //添加网络错误页面
		errorLayout = layoutInflater.inflate(R.layout.layout_error, null);
		tv_loaning_again = (TextView) errorLayout.findViewById(R.id.tv_reload);
		params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.BELOW, R.id.ll_title);
		addView(errorLayout, params);
        //添加页面主布局
		params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.BELOW, R.id.ll_error);
		addView(layoutView, params);
//        //添加解锁页面
//        params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//        lockView = new LockView((BaseActivity) context);
//        lockView.setVisibility(View.GONE);
//        addView(lockView,params);
	}

	public void setTitle(String title) {
		if (title != null) {
			tv_header.setVisibility(View.VISIBLE);
			tv_header.setText(title);
		} else {
			tv_header.setVisibility(View.GONE);
		}
	}
	
	public void setRightText(String title) {
		if (title != null) {
			tv_title_right.setVisibility(View.VISIBLE);
			tv_title_right.setText(title);
		} else {
			tv_title_right.setVisibility(View.GONE);
		}
	}
	
	public void setRightPic(Drawable picDrawable){
		if (null == picDrawable) {
			iv_right.setVisibility(View.GONE);
		}else{
			iv_right.setVisibility(View.VISIBLE);
			iv_right.setImageDrawable(picDrawable);
		}
	}
	public void setLeftPic(Drawable picDrawable){
		if (null == picDrawable) {
			iv_left.setVisibility(View.GONE);
		}else{
			iv_left.setVisibility(View.VISIBLE);
			iv_left.setImageDrawable(picDrawable);
		}
	}
	public void setTitlePic(Drawable picDrawable){
		if (null == picDrawable) {
			iv_title_pic.setVisibility(View.GONE);
		}else{
			iv_title_pic.setVisibility(View.VISIBLE);
			iv_title_pic.setImageDrawable(picDrawable);
		}
	}
	public ProgressBar getProgressBar() {
		return web_progressbar;
	}

    public void startLoadingAnimation() {
        if (anim == null) {
           anim= (AnimationDrawable) getResources().getDrawable(R.drawable.animation_loading);
            ivLoading.setBackground(anim);
        }
        anim.start();
    }
    public  void stopLoadingAnimation() {
        if (anim == null) {
            anim= (AnimationDrawable) getResources().getDrawable(R.drawable.animation_loading);
            ivLoading.setBackground(anim);
        }
        anim.stop();
    }

    /**
	 * 生成一个和状态栏大小相同的彩色矩形条
	 *
	 * @param activity 需要设置的 activity
	 * @param color    状态栏颜色值
	 * @return 状态栏矩形条
	 */
	protected static View createStatusBarView(Context activity, int color) {
		// 绘制一个和状态栏一样高的矩形
		View statusBarView = new View(activity);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				getStatusBarHeight(activity));
		statusBarView.setLayoutParams(params);
		statusBarView.setBackgroundColor(color);
		return statusBarView;
	}

	/**
	 * 获取状态栏高度
	 *
	 * @param context context
	 * @return 状态栏高度
	 */
	private static int getStatusBarHeight(Context context) {
		// 获得状态栏高度
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		return context.getResources().getDimensionPixelSize(resourceId);
	}
}

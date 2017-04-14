package com.inititute.flabbybird.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.inititute.flabbybird.R;
import com.inititute.flabbybird.utils.ConastClassUtil;
import com.inititute.flabbybird.utils.PermissionHelper;
import com.orhanobut.logger.Logger;

import net.youmi.android.AdManager;
import net.youmi.android.normal.common.ErrorCode;
import net.youmi.android.normal.spot.SplashViewSettings;
import net.youmi.android.normal.spot.SpotListener;
import net.youmi.android.normal.spot.SpotManager;

/**
 * Created by LiRan on 2017-02-05.
 */

public class StartActivity extends BaseActivity implements SpotListener {


    private static final String TAG = "StartActivity";
    //    private Intent localIntent;
    private PermissionHelper mPermissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Logger.init("TAG");


        // 当系统为6.0以上时，需要申请权限
        mPermissionHelper = new PermissionHelper(this);
        mPermissionHelper.setOnApplyPermissionListener(new PermissionHelper.OnApplyPermissionListener() {
            @Override
            public void onAfterApplyAllPermission() {
                Log.i(TAG, "All of requested permissions has been granted, so run app logic.");
                runApp();
            }

        });
        if (Build.VERSION.SDK_INT < 23) {
            // 如果系统版本低于23，直接跑应用的逻辑
            Log.d(TAG, "The api level of system is lower than 23, so run app logic directly.");
            runApp();
        } else {
            // 如果权限全部申请了，那就直接跑应用逻辑
            if (mPermissionHelper.isAllRequestedPermissionGranted()) {
                Log.d(TAG, "All of requested permissions has been granted, so run app logic directly.");
                runApp();
            } else {
                // 如果还有权限为申请，而且系统版本大于23，执行申请权限逻辑
                Log.i(TAG, "Some of requested permissions hasn't been granted, so apply permissions first.");
                mPermissionHelper.applyPermissions();
            }
        }

       /* //停留一段时间启动登陆界面
        localIntent = new Intent(this, LoginActivity.class);*/

       /* Timer timer = new Timer();
        TimerTask tast = new TimerTask() {
            @Override
            public void run() {

                startActivity(localIntent);
                finish();

            }
        };
        timer.schedule(tast, 3000);*/


    }


    /**
     * 跑应用的逻辑
     */
    private void runApp() {

        //广告sdk初始化
        AdManager.getInstance(this).init(ConastClassUtil.appId, ConastClassUtil.appSecret, true);

        //设置开屏
        setupSplashAd();

    }

    /**
     * 设置开屏广告
     */
    private void setupSplashAd() {

        final RelativeLayout splashLayout = (RelativeLayout) findViewById(R.id.ad_relativelayout);

        SplashViewSettings splashViewSettings = new SplashViewSettings();
        splashViewSettings.setAutoJumpToTargetWhenShowFailed(true);
        splashViewSettings.setTargetClass(LoginActivity.class);
        splashViewSettings.setSplashViewContainer(splashLayout);

        SpotManager.getInstance(this).showSplash(this,
                splashViewSettings, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpotManager.getInstance(this).onDestroy();

        //SpotManager.getInstance(Context context).onAppExit(); 这一句在应用退出时调用

    }

    @Override
    public void onShowSuccess() {
        Logger.d("开屏展示成功");
    }

    @Override
    public void onShowFailed(int errorCode) {

        Logger.d("开屏展示失败");
        switch (errorCode) {
            case ErrorCode.NON_NETWORK:
                Logger.d("网络异常");
                break;
            case ErrorCode.NON_AD:
                Logger.d("暂无开屏广告");
                break;
            case ErrorCode.RESOURCE_NOT_READY:
                Logger.d("开屏资源还没准备好");
                break;
            case ErrorCode.SHOW_INTERVAL_LIMITED:
                Logger.d("开屏展示间隔限制");
                break;
            case ErrorCode.WIDGET_NOT_IN_VISIBILITY_STATE:
                Logger.d("开屏控件处在不可见状态");
                break;
            default:
                Logger.d("errorCode: %d", errorCode);
                break;
        }

    }

    @Override
    public void onSpotClosed() {
        Logger.d("开屏展示被关闭");
    }

    @Override
    public void onSpotClicked(boolean b) {
        Logger.d("开屏被点击");

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPermissionHelper.onActivityResult(requestCode, resultCode, data);
    }

}

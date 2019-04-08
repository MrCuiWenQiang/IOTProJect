package com.iot.manager.view.video;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iot.manager.R;
import com.iot.manager.lib.funsdk.support.FunError;
import com.iot.manager.lib.funsdk.support.FunSupport;
import com.iot.manager.lib.funsdk.support.models.FunDevice;
import com.iot.manager.lib.funsdk.support.models.FunStreamType;
import com.iot.manager.lib.funsdk.support.widget.FunVideoView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.activity.manager.ActivityManager;
import cn.faker.repaymodel.util.LocImageUtility;
import cn.faker.repaymodel.util.LogUtil;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.util.error.ErrorUtil;

/**
 * Function : 视频播放页面
 * Remarks  :
 * Created by Mr.C on 2019/4/5 0005.
 */
public class VideoPlayActivity extends BaseToolBarActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener {

    private LinearLayout ll_status;

    private FunDevice mFunDevice = null;
    private FunVideoView mFunVideoView = null;

    private TextView tv_status = null;
    private ImageView im_full = null;

    private Button bt_capture;
    private Button bt_record;
    private RelativeLayout layoutVideoControl;
    private RelativeLayout rl_vs;
    private RelativeLayout rl_open;

    private QMUIListPopup mListPopup;

    private int playType = 0;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_videoplay;
    }

    @Override
    protected void initWindow() {
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            //隐藏虚拟按键，并且全屏
            if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
                View v = this.getWindow().getDecorView();
                v.setSystemUiVisibility(View.GONE);
            } else if (Build.VERSION.SDK_INT >= 19) {
                //for new api versions.
                View decorView = getWindow().getDecorView();
                int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
                decorView.setSystemUiVisibility(uiOptions);
            }
        }
    }

    @Override
    protected void initContentView() {
        mFunVideoView = (FunVideoView) findViewById(R.id.funVideoView);
        bt_capture = findViewById(R.id.bt_capture);
        bt_record = findViewById(R.id.bt_record);
        ll_status = findViewById(R.id.ll_status);
        tv_status = findViewById(R.id.tv_status);
        im_full = findViewById(R.id.im_full);
        layoutVideoControl = findViewById(R.id.layoutVideoControl);
        rl_vs = findViewById(R.id.rl_vs);
        rl_open = findViewById(R.id.rl_open);
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            isShowToolView(false);
            ViewGroup.LayoutParams layoutParams = rl_vs.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            rl_open.setVisibility(View.GONE);
        }
        else if(getResources().getConfiguration().orientation
                ==Configuration.ORIENTATION_PORTRAIT) {
            isShowCut(false);
            setLeftTitle("实时播放");
            setToolBarBackgroundColor(R.color.white);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        mFunVideoView.setOnPreparedListener(this);
        mFunVideoView.setOnErrorListener(this);
        mFunVideoView.setOnInfoListener(this);
        mFunVideoView.setOnTouchListener(new OnVideoViewTouchListener());

        tv_status.setOnClickListener(this);
        im_full.setOnClickListener(this);
        bt_capture.setOnClickListener(this);
        bt_record.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        int devId = getIntent().getIntExtra("FUN_DEVICE_ID", 0);
        mFunDevice = FunSupport.getInstance().findDeviceById(devId);

        if (null == mFunDevice) {
            finish();
            return;
        }
        mFunVideoView.setStreamType(FunStreamType.STREAM_MAIN);
        play();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_capture: {
                tryToCapture();
                break;
            }
            case R.id.bt_record: {
                tryToRecord();
                break;
            }
            case R.id.tv_status: {
                showList(view);
                break;
            }
            case R.id.im_full: {
                tofull();
                break;
            }
        }
    }



    private void play() {
        if (mFunVideoView.isPlaying()){
            stopMedia();
        }
        ll_status.setVisibility(View.VISIBLE);
        if (mFunDevice.isRemote) {
            mFunVideoView.setRealDevice(mFunDevice.getDevSn(), mFunDevice.CurrChannel);
        } else {
            String deviceIp = FunSupport.getInstance().getDeviceWifiManager().getGatewayIp();
            mFunVideoView.setRealDevice(deviceIp, mFunDevice.CurrChannel);
        }
        // 打开声音
        mFunVideoView.setMediaSound(true);
    }

    private void stopMedia() {
        if (null != mFunVideoView) {
            mFunVideoView.stopPlayback();
            mFunVideoView.stopRecordVideo();
        }
    }

    private void tofull() {
        // 横竖屏切换
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                && getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
            // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void showList(View v) {
        if (mListPopup==null){
            String[] listItems = new String[]{
                    "高清",
                    "标清"
            };
            List<String> data = new ArrayList<>();

            Collections.addAll(data, listItems);

            ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, data);

            mListPopup = new QMUIListPopup(this, QMUIPopup.DIRECTION_NONE, adapter);
            mListPopup.create(QMUIDisplayHelper.dp2px(this, 80), QMUIDisplayHelper.dp2px(this, 200), new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i!=playType){
                        FunStreamType type = null;
                        if (i==0){
                            playType = 0;
                            tv_status.setText("高清");
                            type = FunStreamType.STREAM_MAIN;
                        }else  if (i==1){
                            playType = 1;
                            tv_status.setText("标清");
                            type = FunStreamType.STREAM_SECONDARY;
                        }
                        mFunVideoView.setStreamType(type);
                        play();
                    }
                    mListPopup.dismiss();
                }
            });
            mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
            mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_TOP);
        }
        mListPopup.show(v);
    }

    // TODO: 2019/4/5 0005 截图需要有个图片展示对话框
    //截图
    private void tryToCapture() {
        if (!mFunVideoView.isPlaying()) {
            ToastUtility.showToast("视频在播放状态下，才能截图");
            return;
        }

        String path = mFunVideoView.captureImage(null);
        if (!TextUtils.isEmpty(path)) {
            try {
                LocImageUtility.NotifyPhonePicture(this, new File(path));
            } catch (Exception e) {
                ErrorUtil.showError(e);
            } finally {
                ToastUtility.showToast("图片已保存到" + path+".可前往我的-我的抓图查看");
            }
        }
    }

    //录像
    private void tryToRecord() {
        if (!mFunVideoView.isPlaying() || mFunVideoView.isPaused()) {
            ToastUtility.showToast("视频在播放状态下，才能录像");
            return;
        }

        if (mFunVideoView.bRecord) {
            mFunVideoView.stopRecordVideo();
//            mLayoutRecording.setVisibility(View.INVISIBLE);
            toastRecordSucess(mFunVideoView.getFilePath());
            bt_record.setText("录像");
            bt_record.setTextColor(ContextCompat.getColor(this,R.color.black));
        } else {
            mFunVideoView.startRecordVideo(null);
//            mLayoutRecording.setVisibility(View.VISIBLE);
            ToastUtility.showToast("开始录像");

            bt_record.setText("录像中");
            bt_record.setTextColor(ContextCompat.getColor(this,R.color.red_primary_color));
        }
    }

    private void toastRecordSucess(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                LocImageUtility.NotifyPhonePicture(this, new File(filePath));
            } catch (Exception e) {
                ErrorUtil.showError(e);
            } finally {
                ToastUtility.showToast("录像成功,文件已保存到" + filePath+".可前往我的-我的录像查看");
            }
        }
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int extra) {
        LogUtil.e("s", getResources().getString(R.string.media_play_error)
                + " : "
                + FunError.getErrorStr(extra));
        if (FunError.EE_TPS_NOT_SUP_MAIN == extra
                || FunError.EE_DSS_NOT_SUP_MAIN == extra) {
            // 不支持高清码流,设置为标清码流重新播放
            if (null != mFunVideoView) {
                mFunVideoView.setStreamType(FunStreamType.STREAM_SECONDARY);
                play();
            }
        }else {
            ToastUtility.showToast("播放失败!");
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                ActivityManager.exit(this);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onInfo(MediaPlayer arg0, int what, int extra) {
        if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
           /* mTextVideoStat.setText(R.string.media_player_buffering);
            mTextVideoStat.setVisibility(View.VISIBLE);*/
//            ToastUtility.showToast("正在缓冲");
            ll_status.setVisibility(View.VISIBLE);
        } else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
            ll_status.setVisibility(View.GONE);
        }
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }



    private class OnVideoViewTouchListener implements View.OnTouchListener {

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // 显示或隐藏视频操作菜单
                if (layoutVideoControl.getVisibility() == View.VISIBLE) {
                    layoutVideoControl.setVisibility(View.GONE);
                } else {
                    layoutVideoControl.setVisibility(View.VISIBLE);
                    layoutVideoControl.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            layoutVideoControl.setVisibility(View.GONE);
                            if (mListPopup!=null){
                                mListPopup.dismiss();
                            }
                        }
                    },3000);
                }
            }

            return false;
        }

    }

    @Override
    protected void onDestroy() {
        stopMedia();
        super.onDestroy();
    }
}

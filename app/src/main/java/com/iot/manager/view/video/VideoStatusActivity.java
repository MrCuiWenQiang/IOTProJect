package com.iot.manager.view.video;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.iot.manager.R;
import com.iot.manager.lib.funsdk.support.FunDevicePassword;
import com.iot.manager.lib.funsdk.support.FunSupport;
import com.iot.manager.lib.funsdk.support.OnFunDeviceListener;
import com.iot.manager.lib.funsdk.support.models.FunDevStatus;
import com.iot.manager.lib.funsdk.support.models.FunDevType;
import com.iot.manager.lib.funsdk.support.models.FunDevice;
import com.lib.FunSDK;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * Function : 视频状态
 * Remarks  :
 * Created by Mr.C on 2019/4/5 0005.
 */
public class VideoStatusActivity extends BaseToolBarActivity implements OnFunDeviceListener {

    String devID = "f6b9521901edbb4a";
    String name = "admin";
    String passWord = "dong2018";

    Handler mainHandler = new Handler(Looper.getMainLooper());
    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_video;
    }


    @Override
    protected void initContentView() {

    }


    @Override
    public void initData(Bundle savedInstanceState) {
        FunSupport.getInstance().registerOnFunDeviceListener(this);
        status();
    }

    private void status(){
        FunSupport.getInstance().requestDeviceStatus(FunDevType.EE_DEV_NORMAL_MONITOR, devID);
    }

    private void login(FunDevice funDevice){
        funDevice.loginName = name;
        funDevice.loginPsw = passWord;
        funDevice.devSn = devID;
        FunDevicePassword.getInstance().saveDevicePassword(funDevice.getDevSn(), passWord);
        FunSDK.DevSetLocalPwd(funDevice.getDevSn(), name, passWord);
        Intent intent = new Intent();
        intent.setClass(this, VideoPlayActivity.class);
        intent.putExtra("FUN_DEVICE_ID", funDevice.getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDeviceListChanged() {

    }

    @Override
    public void onDeviceStatusChanged(final FunDevice funDevice) {
        if (funDevice.devStatus == FunDevStatus.STATUS_ONLINE) {
            // 如果设备在线,获取设备信息
            if ((funDevice.devType == null || funDevice.devType == FunDevType.EE_DEV_UNKNOWN)) {
                funDevice.devType = FunDevType.EE_DEV_NORMAL_MONITOR;
            }
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    login(funDevice);
                }
            });
        } else {
            // 设备不在线
            ToastUtility.showToast(R.string.device_stauts_offline);
            showDialog("设备不在线,无法实时预览", new QMUIDialogAction.ActionListener() {
                @Override
                public void onClick(QMUIDialog dialog, int index) {
                    dialog.dismiss();
                    finish();
                }
            });
        }
    }



    @Override
    public void onDeviceAddedSuccess() {

    }

    @Override
    public void onDeviceAddedFailed(Integer errCode) {

    }

    @Override
    public void onDeviceRemovedSuccess() {

    }

    @Override
    public void onDeviceRemovedFailed(Integer errCode) {

    }

    @Override
    public void onAPDeviceListChanged() {

    }

    @Override
    public void onLanDeviceListChanged() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FunSupport.getInstance().removeOnFunDeviceListener(this);
    }
}

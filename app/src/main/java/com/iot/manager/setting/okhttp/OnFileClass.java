package com.iot.manager.setting.okhttp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import com.iot.manager.view.LoginActivity;

import cn.faker.repaymodel.activity.manager.ActivityManager;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.util.error.ErrorUtil;

/**
 * Function : 错误信息处理类
 * Remarks  :
 * Created by Mr.C on 2019/1/24 0024.
 */
public class OnFileClass implements HttpResponseCallback.OnFailedAll {

    public static final String TOLOGINKEY="TOLOGINKEY";


    /**
     * 返回true则为已处理了不继续下发
     *
     * @param status
     * @param message
     * @return
     */
    @Override
    public boolean onFailed(int status, String message) {
      if (status==-2){
            Context context = ActivityManager.currentActivity();
            Intent intent = new Intent(context,LoginActivity.class);
            intent.putExtra(TOLOGINKEY,false);
          ActivityManager.exit(null);
          ToastUtility.showToast(message);
          ((Activity)context).startActivity(intent);
      }
        return false;
    }

    public void toAc(Class c){
        try {
            Context context = ActivityManager.currentActivity();
            Intent intent = new Intent(context,c);
            context.startActivity(intent);
        }catch (Exception e){
            ErrorUtil.showError(e);
        }

    }
}

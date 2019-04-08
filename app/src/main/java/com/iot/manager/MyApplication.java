package com.iot.manager;

import android.app.Application;

import com.iot.manager.lib.funsdk.support.FunPath;
import com.iot.manager.lib.funsdk.support.FunSupport;
import com.iot.manager.setting.okhttp.OnFileClass;
import com.iot.manager.setting.okhttp.TokenHeaderInterceptor;

import org.litepal.LitePal;

import cn.faker.repaymodel.BasicApplication;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.util.LocImageUtility;
import cn.faker.repaymodel.util.LogUtil;
import cn.faker.repaymodel.util.ToastUtility;
import okhttp3.OkHttpClient;


public class MyApplication extends BasicApplication {

    private static MyApplication application;

    public static MyApplication getInstance() {
        return application;
    }
	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	private void init(){
		HttpHelper.init(new HttpHelper.OnSettingClient() {
			@Override
			public void onInit(OkHttpClient.Builder builder) {
				builder.addNetworkInterceptor(new TokenHeaderInterceptor());//配置token拦截器
			}
		});
		ToastUtility.setToast(this);
		LitePal.initialize(this);
		LogUtil.isShow =true;
		LocImageUtility.setImageUtility(this);
		HttpHelper.setOnFailedAll(new OnFileClass());

		FunSupport.getInstance().init(this);
	}


	public void exit() {

		FunSupport.getInstance().term();
	}
	
}

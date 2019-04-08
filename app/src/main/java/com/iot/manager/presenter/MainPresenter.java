package com.iot.manager.presenter;


import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.iot.manager.R;
import com.iot.manager.contract.MainContract;
import com.iot.manager.entity.net.result.heweather.HeWeather;
import com.iot.manager.entity.net.result.heweather.Heweather5;
import com.iot.manager.entity.view.MainTableBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.util.LogUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class MainPresenter extends BaseMVPPresenter<MainContract.View> implements MainContract.Presenter {

    private final String weatherUrl = "https://free-api.heweather.com/v5/weather?key=4a555d4d1adc451d8ceeaa73869c9519&city=";
    Handler handler = new Handler(Looper.getMainLooper());
    /**
     * 获取天气数据
     */
    @Override
    public void giveWeatherData(String city) {

        HttpHelper.get(weatherUrl + city, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getView().giveWeatherData_fail("连接失败");

                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                String json = null;
                try {
                    json = response.body().string();
                } catch (IOException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (getView()!=null){
                                    getView().giveWeatherData_fail("暂无天气数据");
                                }
                            }
                        });
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(json)){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (getView()!=null){
                                getView().giveWeatherData_fail("暂无天气数据");
                            }
                        }
                    });

                }else {
                    final HeWeather heWeather = JsonUtil.convertJsonToObject(json,HeWeather.class);
                    if (heWeather!=null){
                        if (heWeather.getHeweather5()!=null&&heWeather.getHeweather5().size()>0){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (getView()!=null){
                                        getView().settingWeatherData(heWeather.getHeweather5().get(0));
                                    }
                                }
                            });

                        }
                    }
                }


            }
        });
    }

    /**
     * 设置标签
     */
    @Override
    public void giveTable() {
        List<MainTableBean> datas = new ArrayList<>();
        datas.add(new MainTableBean("设备中心", R.mipmap.device,0));
        datas.add(new MainTableBean("控制中心",R.mipmap.control,1));
        datas.add(new MainTableBean("监测中心",R.mipmap.monitor,2));
        datas.add(new MainTableBean("消息中心",R.mipmap.msg,3));
        getView().settingTable(datas);
    }
}

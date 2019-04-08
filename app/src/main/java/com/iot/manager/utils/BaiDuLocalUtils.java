package com.iot.manager.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Function :百度定位工具类
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class BaiDuLocalUtils {

    public static LocationClient mLocationClient = null;
//BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口
//原有BDLocationListener接口暂时同步保留。具体介绍请参考后文第四步的说明

    public static void init(Context context, BDAbstractLocationListener locationListener) {
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(context);
            mLocationClient.registerLocationListener(locationListener);
            setting();
        }
        mLocationClient.start();
    }

    public static void clean(){
        if (mLocationClient!=null){
            mLocationClient.stop();
            mLocationClient = null;
        }
    }

    public static void stop(){
        if (mLocationClient!=null){
            mLocationClient.stop();
        }
    }

    private static void setting() {
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        option.setIsNeedAddress(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(false);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false
        option.setIsNeedLocationDescribe(true);
        option.setIgnoreKillProcess(false);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
//可选，V7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位

        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        mLocationClient.setLocOption(option);
    }

    /**
     * d定位服务是否打开 true打开
     * @param mContext
     * @return
     */
    public boolean isLocationEnabled(Context mContext) {
             int locationMode = 0;
             String locationProviders;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                     try {
                            locationMode = Settings.Secure.getInt(mContext.getContentResolver(), Settings.Secure.LOCATION_MODE);
                         } catch (Settings.SettingNotFoundException e) {
                           e.printStackTrace();
                            return false;
                        }
                     return locationMode != Settings.Secure.LOCATION_MODE_OFF;
                 } else {
                     locationProviders = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
                     return !TextUtils.isEmpty(locationProviders);
                 }
         }
}

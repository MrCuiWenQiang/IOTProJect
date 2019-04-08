package com.iot.manager.presenter;

import android.text.TextUtils;

import com.iot.manager.URLs;
import com.iot.manager.contract.Fg_TemperatureMonitor;
import com.iot.manager.entity.net.result.TemperatureResultEntity;

import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/18 0018.
 */
public class FG_TemperatureMonitorPressenter extends BaseMVPPresenter<Fg_TemperatureMonitor.View> implements Fg_TemperatureMonitor.Presenter {

    @Override
    public void getList(String id) {
        HashMap hashMap = new HashMap<String,Object>();
        if (!TextUtils.isEmpty(id)){
            hashMap.put("placeId",id);
        }
        HttpHelper.get(URLs.GETSENSORLATESTDATA, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                List<TemperatureResultEntity> datas = JsonUtil.fromList(data,TemperatureResultEntity.class);
                if (getView()!=null){
                    getView().getList_Success(datas);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().getList_Fail(message);
                }
            }
        });
    }

}

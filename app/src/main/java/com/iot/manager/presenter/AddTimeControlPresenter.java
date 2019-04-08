package com.iot.manager.presenter;


import com.iot.manager.URLs;
import com.iot.manager.contract.AddTimeControlContract;
import com.iot.manager.entity.net.request.AddTimerRequestEntity;
import com.iot.manager.entity.net.result.TimerListResultEntity;

import java.util.HashMap;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;


/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class AddTimeControlPresenter extends BaseMVPPresenter<AddTimeControlContract.View> implements AddTimeControlContract.Presenter {



    @Override
    public void add(AddTimerRequestEntity entity) {
        if (entity.isNull()){
            getView().add_Fail("请填写完整数据");
            return;
        }

        HttpHelper.post(URLs.SAVE, entity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onMessage(String msg) {
                super.onMessage(msg);
                if (getView()!=null){
                    getView().add_Successs(msg);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().add_Fail(message);
                }
            }
        });


    }

    @Override
    public void info(String id) {
        HashMap hashMap = new HashMap<String,Object>();
        hashMap.put("id",id);
        HttpHelper.get(URLs.TASKDETAIL,hashMap , new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                TimerListResultEntity entity = JsonUtil.convertJsonToObject(data,TimerListResultEntity.class);
                if (getView()!=null){
                    getView().info(entity);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().info_fail(message);
                }
            }
        });
    }
}

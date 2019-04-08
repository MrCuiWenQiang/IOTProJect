package com.iot.manager.presenter;

import android.text.TextUtils;

import com.iot.manager.URLs;
import com.iot.manager.contract.Fg_MonitorContract;
import com.iot.manager.entity.net.result.DksResultEntity;

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
public class FG_MonitorPresenter extends BaseMVPPresenter<Fg_MonitorContract.View> implements Fg_MonitorContract.Presenter {


    @Override
    public void getDkList() {
        HttpHelper.get(URLs.GETALLPLACESDATA, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                List<DksResultEntity> datas = JsonUtil.fromList(data,DksResultEntity.class);
                if (getView()!=null){
                    getView().getList_success(datas);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().getList_fail(message);
                }
            }
        });
    }
}

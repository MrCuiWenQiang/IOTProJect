package com.iot.manager.presenter;



import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iot.manager.URLs;
import com.iot.manager.contract.ContentManagerContract;
import com.iot.manager.contract.DKEditContract;
import com.iot.manager.entity.net.request.AddDkEntity;
import com.iot.manager.entity.net.request.CMRequestEntity;
import com.iot.manager.entity.net.request.DeleteDkEntity;
import com.iot.manager.entity.net.result.DksResultEntity;
import com.iot.manager.entity.net.result.content.ContentInfoEntity;
import com.iot.manager.entity.net.result.content.Device;

import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;


/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class ContentManagerPresenter extends BaseMVPPresenter<ContentManagerContract.View> implements ContentManagerContract.Presenter {

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
    @Override
    public void init(String code) {
        if (TextUtils.isEmpty(code)){
            getView().init_fail("编号不能为空");
            return;
        }
        HashMap hashMap = new HashMap<String, Object>();
        hashMap.put("code", code);
        HttpHelper.get(URLs.GETDEVICELISTBYCONTROLLERCODE, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                List<Device> datas = JsonUtil.fromList(data, Device.class);

                if (getView() != null) {
                    getView().initSuccess(datas);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().init_fail(message);
                }
            }
        });
    }

    @Override
    public void info(String ID) {
        HashMap hashMap = new HashMap<String, Object>();
        hashMap.put("id", ID);
        HttpHelper.get(URLs.CONTROLLERDETAIL, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {

                ContentInfoEntity datas = JsonUtil.convertJsonToObject(data,ContentInfoEntity.class);
                if (datas==null) {
                    if (getView() != null) {
                        getView().info_fail("获取不到数据");
                    }
                    return;
                }
                if (getView() != null) {
                    getView().info_success(datas);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().info_fail(message);
                }
            }
        });
    }

    @Override
    public void save(CMRequestEntity data) {
        if (data.isNull()){
            if (getView() != null) {
                getView().savefail("请填写完整数据");
            }
            return;
        }
        String url = null;
        if (TextUtils.isEmpty(data.getId())){
            url = URLs.CONTROLSAVE;
        }else {
            url = URLs.CONTROLMODIFY;
        }

        HttpHelper.post(url, data, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onMessage(String msg) {
                super.onMessage(msg);
                if (getView() != null) {
                    getView().saveSuccess(msg);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().savefail(message);
                }
            }
        });
    }
}

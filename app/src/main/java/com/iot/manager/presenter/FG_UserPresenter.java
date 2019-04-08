package com.iot.manager.presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iot.manager.URLs;
import com.iot.manager.contract.Fg_UserContract;
import com.iot.manager.entity.net.result.UserInfoResultEntity;
import com.iot.manager.model.LoginModel;

import java.io.IOException;
import java.util.Map;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/18 0018.
 */
public class FG_UserPresenter extends BaseMVPPresenter<Fg_UserContract.View> implements Fg_UserContract.Presenter {

    @Override
    public void getUserInfo() {
        HttpHelper.get(URLs.GETBYTOKEN, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObj = JSON.parseObject(data);
                if (!jsonObj.containsKey("sysUser")){
                    if (getView()!=null){
                        getView().getUserInfo_fail("数据解析失败");
                    }
                    return;
                }
                JSONObject jsonObject = (JSONObject) jsonObj.get("sysUser");
                String datajson =jsonObject.toJSONString();
                if (TextUtils.isEmpty(datajson)){
                    if (getView()!=null){
                        getView().getUserInfo_fail("数据解析失败");
                    }
                    return;
                }
                UserInfoResultEntity userInfo = JsonUtil.convertJsonToObject(datajson,UserInfoResultEntity.class);
                if (userInfo!=null){
                    if (getView()!=null){
                        getView().getUserInfo_Success(userInfo);
                    }
                }else {
                    if (getView()!=null){
                        getView().getUserInfo_fail("无法获取用户信息");
                    }
                }

            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().getUserInfo_fail(message);
                }
            }
        });
    }

    @Override
    public void unbild() {
        HttpHelper.post(URLs.UNBIND, null, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                LoginModel loginModel = new LoginModel();
                loginModel.cleanAll();
                if (getView()!=null){
                    getView().unbild_success();
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().unbild_fail(message);
                }
            }
        });
    }
}

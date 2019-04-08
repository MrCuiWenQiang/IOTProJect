package com.iot.manager.presenter;

import android.text.Editable;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iot.manager.URLs;
import com.iot.manager.contract.Fg_MessageContract;
import com.iot.manager.entity.net.result.MessageRequestEntity;

import java.util.ArrayList;
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
public class FG_MessagePresenter extends BaseMVPPresenter<Fg_MessageContract.View> implements Fg_MessageContract.Presenter {

    @Override
    public void getMessageList(int page, Editable value) {
        HashMap hashMap = new HashMap<String,Object>();
        hashMap.put("pageNo",page);
        hashMap.put("pageSize",SIZE);
        if (!TextUtils.isEmpty(value)){
            hashMap.put("key",value.toString());
        }
        HttpHelper.get(URLs.GETPAGEDATA, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObj = JSON.parseObject(data);
                if (!jsonObj.containsKey("data")) {
                    if (getView() != null) {
                        getView().getMessageList_success(null);
                    }
                    return;
                } else {
                    JSONArray jsonObject = (JSONArray) jsonObj.get("data");
                    String datajson = jsonObject.toJSONString();
                   List<MessageRequestEntity> datas = JsonUtil.fromList(datajson, MessageRequestEntity.class);
                    if (getView() != null) {
                        getView().getMessageList_success(datas);
                    }
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().getMessageList_fail(message);
                }
            }
        });
    }
}

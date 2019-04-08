package com.iot.manager.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iot.manager.URLs;
import com.iot.manager.contract.Fg_CMContract;
import com.iot.manager.contract.Fg_DKContract;
import com.iot.manager.entity.net.result.ControlEntity;
import com.iot.manager.entity.net.result.DksResultEntity;
import com.iot.manager.entity.net.result.hand.HList;

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
public class FG_CMPresenter extends BaseMVPPresenter<Fg_CMContract.View> implements Fg_CMContract.Presenter {

    @Override
    public void getList(int page) {
        HashMap hashMap = new HashMap<String, Object>();
        hashMap.put("pageSize", SIZE);
        hashMap.put("pageNo", page);
        HttpHelper.get(URLs.CONTROLLERGETPAGEDATA, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObj = JSON.parseObject(data);
                if (!jsonObj.containsKey("data")) {
                    if (getView() != null) {
                        getView().getList_Success(null);
                    }
                    return;
                } else {
                    JSONArray jsonObject = (JSONArray) jsonObj.get("data");
                    String datajson = jsonObject.toJSONString();
                    List<ControlEntity> datas = JsonUtil.fromList(datajson,ControlEntity.class);
                    if (getView() != null) {
                        getView().getList_Success(datas);
                    }
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().getList_fail(message);
                }
            }
        });
    }
}

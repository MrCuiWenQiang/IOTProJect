package com.iot.manager.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iot.manager.URLs;
import com.iot.manager.contract.Fg_NexusControlContract;
import com.iot.manager.entity.net.result.NexusResultEntity;

import java.util.ArrayList;
import java.util.HashMap;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/18 0018.
 */
public class FG_NexusControlPresenter extends BaseMVPPresenter<Fg_NexusControlContract.View> implements Fg_NexusControlContract.Presenter {

    @Override
    public void list(int page) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pageNo", page);
        hashMap.put("pageSize", SIZE);
        HttpHelper.get(URLs.GETLINKDATA, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {

                JSONObject jsonObj = JSON.parseObject(data);
                if (!jsonObj.containsKey("list")) {
                    if (getView() != null) {
                        getView().list_Success(null);
                    }
                    return;
                } else {
                    JSONArray jsonObject = (JSONArray) jsonObj.get("list");
                    String datajson = jsonObject.toJSONString();
                    ArrayList<NexusResultEntity> datas = JsonUtil.fromList(datajson, NexusResultEntity.class);
                    if (getView() != null) {
                        getView().list_Success(datas);
                    }
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().list_Fail(message);
                }
            }
        });
    }
}

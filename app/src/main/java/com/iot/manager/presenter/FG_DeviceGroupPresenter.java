package com.iot.manager.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iot.manager.URLs;
import com.iot.manager.contract.FG_DeviceGroupContrat;
import com.iot.manager.contract.Fg_CMContract;
import com.iot.manager.entity.net.result.ControlEntity;
import com.iot.manager.entity.net.result.DeviceGroupResultEntitr;
import com.iot.manager.view.fragment.device.DeviceGroupFragment;

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
public class FG_DeviceGroupPresenter extends BaseMVPPresenter<FG_DeviceGroupContrat.View> implements FG_DeviceGroupContrat.Presenter {

    @Override
    public void getList(int page) {
        HashMap hashMap = new HashMap<String, Object>();
        hashMap.put("pageSize", SIZE);
        hashMap.put("pageNo", page);
        HttpHelper.get(URLs.GROUPPAGEDATA, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObj = JSON.parseObject(data);
                if (!jsonObj.containsKey("list")) {
                    if (getView() != null) {
                        getView().getList_Success(null);
                    }
                    return;
                } else {
                    JSONArray jsonObject = (JSONArray) jsonObj.get("list");
                    String datajson = jsonObject.toJSONString();
                    List<DeviceGroupResultEntitr> datas = JsonUtil.fromList(datajson,DeviceGroupResultEntitr.class);
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

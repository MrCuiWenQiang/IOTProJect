package com.iot.manager.presenter;

import com.iot.manager.URLs;
import com.iot.manager.contract.Fg_ControlContract;
import com.iot.manager.contract.Fg_TermControlContract;
import com.iot.manager.entity.net.result.termlist.TermEntity;

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
public class FG_TermControlPresenter extends BaseMVPPresenter<Fg_TermControlContract.View> implements Fg_TermControlContract.Presenter {

    @Override
    public void list(int page) {
        HashMap hashMap = new HashMap<String,Object>();
        hashMap.put("pageNo",page);
        hashMap.put("pageSize",SIZE);
        HttpHelper.get(URLs.CONDITIONGETRELAYDATA, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                    TermEntity  entity = JsonUtil.convertJsonToObject(data,TermEntity.class);
                    if (getView()!=null){
                        getView().list_success(entity.getList());
                    }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().list_finsh(message);
                }
            }
        });
    }
}

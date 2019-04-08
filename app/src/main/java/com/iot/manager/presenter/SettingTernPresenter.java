package com.iot.manager.presenter;



import com.iot.manager.URLs;
import com.iot.manager.contract.SettingTernContract;
import com.iot.manager.entity.net.request.DeleteDkEntity;
import com.iot.manager.entity.net.request.SaveTernEntity;
import com.iot.manager.entity.net.result.settingtern.TernEntity;

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
public class SettingTernPresenter extends BaseMVPPresenter<SettingTernContract.View> implements SettingTernContract.Presenter {


    @Override
    public void getInfo(String id) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("deviceId",id);
        HttpHelper.get(URLs.DETAIL, map, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                TernEntity ternEntity = JsonUtil.convertJsonToObject(data,TernEntity.class);
                if (getView()!=null){
                    getView().getInfo_Success(ternEntity);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().getInfo_fail(message);
                }
            }
        });
    }

    @Override
    public void save(SaveTernEntity data) {
        HttpHelper.post(URLs.CONDITIONSAVE, data, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onMessage(String msg) {
                super.onMessage(msg);
                if (getView()!=null){
                    getView().save_success(msg);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().savefail(message);
                }
            }
        });
    }

    @Override
    public void delete(String id) {
        DeleteDkEntity entity = new DeleteDkEntity();
        entity.setId(id);
        HttpHelper.post(URLs.CONDITIONDELETE + "/" + id, entity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onMessage(String msg) {
                super.onMessage(msg);
                if (getView()!=null){
                    getView().delete(msg);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().deletefail(message);
                }
            }
        });
    }
}

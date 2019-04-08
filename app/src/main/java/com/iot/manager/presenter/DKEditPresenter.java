package com.iot.manager.presenter;



import android.text.TextUtils;

import com.iot.manager.URLs;
import com.iot.manager.contract.DKEditContract;
import com.iot.manager.entity.net.request.AddDkEntity;
import com.iot.manager.entity.net.request.DeleteDkEntity;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;


/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class DKEditPresenter extends BaseMVPPresenter<DKEditContract.View> implements DKEditContract.Presenter {

    @Override
    public void update(AddDkEntity data) {
        if (data.isNull()){
            getView().update_Fail("请填写完整数据");
            return;
        }
        String url =null;
        if (TextUtils.isEmpty(data.getId())){
            url = URLs.DADD;
        }else {
            url = URLs.DKMODIFY;

        }
        HttpHelper.post(url, data, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onMessage(String msg) {
                super.onMessage(msg);
                if (getView()!=null){
                    getView().update_success(msg);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().update_Fail(message);
                }
            }
        });

    }

    @Override
    public void delete(String id) {
        DeleteDkEntity  entity = new DeleteDkEntity();
        entity.setId(id);
        HttpHelper.post(URLs.PLACEDELETE, entity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onMessage(String msg) {
                super.onMessage(msg);
                if (getView()!=null){
                    getView().delete_success(msg);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().delete_fail(message);
                }
            }
        });
    }
}

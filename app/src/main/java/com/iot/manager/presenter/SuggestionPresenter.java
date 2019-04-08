package com.iot.manager.presenter;


import android.text.TextUtils;

import com.iot.manager.URLs;
import com.iot.manager.contract.LoginContract;
import com.iot.manager.contract.SuggestionContract;
import com.iot.manager.entity.db.UserDBEntity;
import com.iot.manager.entity.net.request.SuggerRequestEntity;
import com.iot.manager.model.LoginModel;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;


/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class SuggestionPresenter extends BaseMVPPresenter<SuggestionContract.View> implements SuggestionContract.Presenter {

    private LoginModel loginModel = new LoginModel();

    @Override
    public void commitSuggestion(String content) {
        if (TextUtils.isEmpty(content)){
            commitFail("请填写您宝贵的意见");
            return;
        }
        SuggerRequestEntity entity = new SuggerRequestEntity(content);
        HttpHelper.post(URLs.INSERT, entity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onMessage(String msg) {
                super.onMessage(msg);
                commitSuccess(msg);
            }

            @Override
            public void onFailed(int status, String message) {
                commitFail(message);
            }
        });

    }

    private void commitFail(String msg){
        if (getView()!=null){
            getView().commit_Fail(msg);
        }
    }

    private void commitSuccess(String msg){
        if (getView()!=null){
            getView().commit_Success(msg);
        }
    }
}

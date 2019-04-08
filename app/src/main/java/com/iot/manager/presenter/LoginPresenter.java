package com.iot.manager.presenter;

import android.text.TextUtils;

import com.iot.manager.URLs;
import com.iot.manager.contract.LoginContract;
import com.iot.manager.entity.db.UserDBEntity;
import com.iot.manager.entity.net.request.LoginRequestEntity;
import com.iot.manager.entity.net.result.LoginResultEntity;
import com.iot.manager.model.LoginModel;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class LoginPresenter extends BaseMVPPresenter<LoginContract.View> implements LoginContract.Presenter {

    private LoginModel loginModel = new LoginModel();

    @Override
    public void attachView(LoginContract.View view) {
        super.attachView(view);
        giveUserInfo();
    }

    /**
     * 匿名登录
     */
    public void login() {
        this.login(null, null, true);
    }

    @Override
    public void login(String name, String passWord, final boolean isAnonymous) {
        if (!isAnonymous) {
            if (TextUtils.isEmpty(name)) {
                getView().login_Fail("请输入用户名!");
                return;
            }
            if (TextUtils.isEmpty(passWord)) {
                getView().login_Fail("请输入密码!");
                return;
            }
        }
        final LoginRequestEntity entity = new LoginRequestEntity();
        entity.setWxOpenId("oQ-mo5WAKpepWzYqgYra4QNRRjA4");
        if (isAnonymous) {
            entity.setName("1");
            entity.setPassword("1");
            entity.setLoginType("anonymous");
        } else {
            entity.setName(name);
            entity.setPassword(passWord);
        }

        HttpHelper.post(URLs.LOGIN, entity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                LoginResultEntity resultEntity = JsonUtil.convertJsonToObject(data, LoginResultEntity.class);
                if (getView() != null) {
                    if (resultEntity != null && resultEntity.getToken() != null) {
                            loginModel.saveUser(resultEntity.getUserId(), resultEntity.getToken(), entity);
                        getView().login_Success();
                    }else {
                        getView().login_Fail("数据解析失败");
                    }
                }

            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().login_Fail(message);
                }
            }
        });
    }

    @Override
    public void giveUserInfo() {
        loginModel.findUserByLimit(new LoginContract.Model.OnFindUser() {
            @Override
            public void onSuccess(UserDBEntity data) {
                if (getView()!=null&&data!=null){
                    if (!data.isIsanonymous()){
                        getView().settingUser(data.getName(),data.getPassword());
                    }
                }
            }

            @Override
            public void onfail() {

            }
        });
    }
}

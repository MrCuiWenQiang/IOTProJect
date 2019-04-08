package com.iot.manager.presenter;


import android.text.TextUtils;

import com.iot.manager.URLs;
import com.iot.manager.contract.EditPasswordContract;
import com.iot.manager.entity.net.request.UpdatePWRequestEntity;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;


/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class EditPasswordPresenter extends BaseMVPPresenter<EditPasswordContract.View> implements EditPasswordContract.Presenter {

    @Override
    public void srttingPassword(String oldPassword, String newPassword, String newPassword_k) {
        if (TextUtils.isEmpty(oldPassword)){
            getView().settingFail("请输入旧密码!");
            return;
        }
        if (TextUtils.isEmpty(newPassword)){
            getView().settingFail("请输入新密码!");
            return;
        }
        if (TextUtils.isEmpty(newPassword_k)){
            getView().settingFail("请重复新密码!");
            return;
        }
        if (oldPassword.equals(newPassword)){
            getView().settingFail("新密码与旧密码不能一致");
            return;
        }
        if (!newPassword_k.equals(newPassword)){
            getView().settingFail("新密码和重复新密码不一致");
            return;
        }
        UpdatePWRequestEntity pwData = new UpdatePWRequestEntity(oldPassword,newPassword,newPassword_k);

        HttpHelper.put(URLs.UPDATEPWD, pwData, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onMessage(String msg) {
                if (getView()!=null){
                    getView().settingSuccess(msg);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().settingFail(message);
                }
            }
        });
    }
}

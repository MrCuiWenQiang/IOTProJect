package com.iot.manager.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.contract.LoginContract;
import com.iot.manager.presenter.LoginPresenter;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;

/**
 * Function : 登录
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class LoginActivity extends BaseMVPAcivity<LoginContract.View, LoginPresenter> implements LoginContract.View, View.OnClickListener {

    private TextView et_name;
    private TextView et_password;
    private Button bt_login;

    private TextView tv_ton;//体验


    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_login;
    }


    @Override
    protected void initContentView() {
        isShowToolView(false);
        setStatusBar(R.color.white);
        changStatusIconCollor(true);

        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        bt_login = findViewById(R.id.bt_login);
        tv_ton = findViewById(R.id.tv_ton);

    }

    @Override
    protected void initListener() {
        super.initListener();
        bt_login.setOnClickListener(this);
        tv_ton.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login: {
                showLoading();
                mPresenter.login(et_name.getText().toString(), et_password.getText().toString(), false);
                break;
            }
            case R.id.tv_ton: {
                showLoading();
                mPresenter.login();
                break;
            }
        }
    }

    @Override
    public void login_Success() {
        dimiss();
        toAcitvity(MainActivity.class);
        finish();
    }

    @Override
    public void login_Fail(String msg) {
        dimiss();
        showDialog(msg);
    }

    @Override
    public void settingUser(String name, String password) {
        et_name.setText(name);
        et_password.setText(password);
    }
}

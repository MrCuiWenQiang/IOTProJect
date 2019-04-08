package com.iot.manager.view;

import android.os.Bundle;


import com.iot.manager.R;
import com.iot.manager.contract.StartContract;
import com.iot.manager.presenter.StartPresenter;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;

/**
 * Function :启动页
 * Remarks  :
 * Created by Mr.C on 2019/1/27 0027.
 */
public class StartActivity extends BaseMVPAcivity<StartContract.View, StartPresenter> implements StartContract.View {


    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_start;
    }


    @Override
    protected void initContentView() {
        isShowToolView(false);
    }


    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void toMain() {
        finish();
        toAcitvity(MainActivity.class);
    }

    @Override
    public void toLogin() {
        finish();
        toAcitvity(LoginActivity.class);
    }
}

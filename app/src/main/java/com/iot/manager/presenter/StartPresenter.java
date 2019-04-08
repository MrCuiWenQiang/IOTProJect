package com.iot.manager.presenter;


import com.iot.manager.contract.StartContract;
import com.iot.manager.model.StartModel;

import java.util.Timer;
import java.util.TimerTask;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/18 0018.
 */
public class StartPresenter extends BaseMVPPresenter<StartContract.View> implements StartContract.Presenter {

    private StartModel model = new StartModel();

    @Override
    public void attachView(StartContract.View view) {
        super.attachView(view);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                next();
            }
        },3000);//延时3s执行
    }

    @Override
    public void next() {
        model.isHaveUser(new StartModel.OnFindUser() {
            @Override
            public void onIsHave(Boolean value) {
                if (value){
                    getView().toMain();
                }else {
                    getView().toLogin();
                }
            }
        });
    }
}

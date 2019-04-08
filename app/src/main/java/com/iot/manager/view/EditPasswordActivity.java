package com.iot.manager.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iot.manager.R;
import com.iot.manager.contract.EditPasswordContract;
import com.iot.manager.presenter.EditPasswordPresenter;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * Function : 修改密码
 * Remarks  :
 * Created by Mr.C on 2019/3/22 0022.
 */
public class EditPasswordActivity extends BaseMVPAcivity<EditPasswordContract.View, EditPasswordPresenter> implements EditPasswordContract.View, View.OnClickListener {

    private EditText et_old_password;
    private EditText et_new_password;
    private EditText et_new_password_k;
    private Button bt_save;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_editpassword;
    }


    @Override
    protected void initContentView() {
        isShowCut(false);
        setLeftTitle("修改密码");
        setToolBarBackgroundColor(R.color.white);

        et_old_password = findViewById(R.id.et_old_password);
        et_new_password = findViewById(R.id.et_new_password);
        et_new_password_k = findViewById(R.id.et_new_password_k);
        bt_save = findViewById(R.id.bt_save);
    }


    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {
        super.initListener();
        bt_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_save: {
                showLoading();
                mPresenter.srttingPassword(getEditTextValue(et_old_password),
                        getEditTextValue(et_new_password),
                        getEditTextValue(et_new_password_k));
                break;
            }
        }
    }

    @Override
    public void settingSuccess(String msg) {
        dimiss();
        ToastUtility.showToast(msg);
        finish();
    }

    @Override
    public void settingFail(String msg) {
        dimiss();
        showDialog(msg);
    }
}

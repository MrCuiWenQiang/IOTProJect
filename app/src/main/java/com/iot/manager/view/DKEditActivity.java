package com.iot.manager.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.contract.DKEditContract;
import com.iot.manager.entity.net.request.AddDkEntity;
import com.iot.manager.entity.net.result.DksResultEntity;
import com.iot.manager.presenter.DKEditPresenter;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/30 0030.
 */
public class DKEditActivity extends BaseMVPAcivity<DKEditContract.View, DKEditPresenter> implements DKEditContract.View, View.OnClickListener {

    private EditText et_name;
    private TextView ed_model;
    private EditText et_mj;
    private EditText et_bz;
    private Button bt_save;
    private Button bt_delete;
    private String  id;
    public static final String  IDKEY = "IDKEY";

    private List<String> list = new ArrayList<>();
    private List<String> value = new ArrayList<>();

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_editdk;
    }


    @Override
    protected void initContentView() {
        isShowCut(false);
        setLeftTitle("添加场地");
        setToolBarBackgroundColor(R.color.white);

        et_name = findViewById(R.id.et_name);
        ed_model = findViewById(R.id.ed_model);
        et_mj = findViewById(R.id.et_mj);
        et_bz = findViewById(R.id.et_bz);
        bt_save = findViewById(R.id.bt_save);
        bt_delete = findViewById(R.id.bt_delete);
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        Serializable sz = getIntent().getSerializableExtra(IDKEY);
        if (sz!=null){
            setLeftTitle("修改场地");
            bt_delete.setVisibility(View.VISIBLE);
            DksResultEntity data = (DksResultEntity) sz;
            id = data.getId();
            et_name.setText(data.getName());
            et_mj.setText(data.getArea());
            et_bz.setText(data.getRemark());
            ed_model.setText(data.getTypeText());
            ed_model.setTag(data.getTypeKey());
        }

        list.add("大棚种植");
        list.add("户外种植");
        value.add("0");
        value.add("1");
    }

    @Override
    protected void initListener() {
        super.initListener();
        bt_save.setOnClickListener(this);
        ed_model.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_save: {
                update();
                break;
            }
            case R.id.ed_model: {
                showDk();
                break;
            }
            case R.id.bt_delete: {
                delete();
                break;
            }
        }
    }

    private void update() {
        AddDkEntity entity = new AddDkEntity();
        entity.setName(getValue(et_name));
        entity.setRemark(getValue(et_bz));
        entity.setArea(getValue(et_mj));
        if (ed_model.getTag()!=null){
            entity.setTypeKey((String) ed_model.getTag());
        }
        entity.setId(id);
        mPresenter.update(entity);
    }

    private void delete(){
        new QMUIDialog.MessageDialogBuilder(this).setMessage("是否要删除该地块").addAction("确定", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
                showLoading();
                mPresenter.delete(id);
            }
        }).addAction("取消", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        }).show();
    }

    protected String getValue(EditText text) {
        if (TextUtils.isEmpty(text.getText())) {
            return null;
        } else {
            return text.getText().toString();
        }
    }

    @Override
    public void update_success(String msg) {
        ToastUtility.showToast(msg);
        setResult(SUCCESSCODE);
        finish();
    }

    @Override
    public void update_Fail(String data) {
        showDialog(data);
    }

    @Override
    public void delete_success(String msg) {
        dimiss();
        ToastUtility.showToast(msg);
        setResult(SUCCESSCODE);
        finish();
    }

    @Override
    public void delete_fail(String msg) {
        dimiss();
        showDialog(msg);
    }


    private void showDk(){

        SinglePicker<String> picker = new SinglePicker<String>(this, list);
        picker.setCanceledOnTouchOutside(false);
        picker.setSelectedIndex(1);
        picker.setCycleDisable(true);
        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                ed_model.setText(item);
                ed_model.setTag(value.get(index));
            }
        });
        picker.show();
    }
}

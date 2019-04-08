package com.iot.manager.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.contract.SettingTernContract;
import com.iot.manager.entity.net.request.SaveTernEntity;
import com.iot.manager.entity.net.result.hand.Deviceslist;
import com.iot.manager.entity.net.result.settingtern.Closeconditiondetails;
import com.iot.manager.entity.net.result.settingtern.Openconditiondetails;
import com.iot.manager.entity.net.result.settingtern.TernEntity;
import com.iot.manager.presenter.SettingTernPresenter;

import java.io.Serializable;
import java.util.ArrayList;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * Function :条件控制编辑
 * Remarks  :
 * Created by Mr.C on 2019/3/27 0027.
 */
public class SettingTernActivity extends BaseMVPAcivity<SettingTernContract.View, SettingTernPresenter> implements SettingTernContract.View, View.OnClickListener {

    public static final String IDKEY = "IDKEY";

    private TextView tv_dk;
    private TextView tv_kzq;
    private TextView tv_bqsb;
    private TextView tv_sb;
    private EditText et_on;
    private EditText et_off;


    private TextView tv_sel_on;
    private TextView tv_sel_off;
    private Button bt_save;
    private Button bt_delete;

    private Deviceslist dv;


    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_settingtern;
    }


    @Override
    protected void initContentView() {
        isShowCut(false);
        setLeftTitle("条件配置");
        setToolBarBackgroundColor(R.color.white);

        tv_dk = findViewById(R.id.tv_dk);
        tv_kzq = findViewById(R.id.tv_kzq);
        tv_bqsb = findViewById(R.id.tv_bqsb);
        tv_sb = findViewById(R.id.tv_sb);
        et_on = findViewById(R.id.et_on);
        et_off = findViewById(R.id.et_off);
        bt_save = findViewById(R.id.bt_save);
        bt_delete = findViewById(R.id.bt_delete);


        tv_sel_on = findViewById(R.id.tv_sel_on);
        tv_sel_off = findViewById(R.id.tv_sel_off);

        tv_sel_on.setSelected(true);
        tv_sel_off.setSelected(false);
    }

    private String id;

    @Override
    public void initData(Bundle savedInstanceState) {
        id = getIntent().getStringExtra(IDKEY);
        if (TextUtils.isEmpty(id)) {
            ToastUtility.showToast("被控设备的ID不能为null");
            finish();
        } else {
            showLoading();
            mPresenter.getInfo(id);
            bt_delete.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        tv_sel_on.setOnClickListener(this);
        tv_sel_off.setOnClickListener(this);
        bt_save.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        tv_sb.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sel_on: {
                boolean v = tv_sel_on.isSelected();
                tv_sel_on.setSelected(!tv_sel_on.isSelected()); //取反
                tv_sel_off.setSelected(!tv_sel_on.isSelected());
                break;
            }
            case R.id.tv_sel_off: {
                tv_sel_off.setSelected(!tv_sel_off.isSelected());
                tv_sel_on.setSelected(!tv_sel_off.isSelected());
                break;
            }
            case R.id.bt_save: {
                save();
                break;
            }
            case R.id.bt_delete: {
                showLoading();
                mPresenter.delete(id);
                break;
            }
            case R.id.tv_sb: {
                Intent intent = new Intent(SettingTernActivity.this, SelectChlidDeviceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(SelectChlidDeviceActivity.DATAKEY, (Serializable) dv);
                intent.putExtra(SelectChlidDeviceActivity.AITEM,true);
                intent.putExtras(bundle);
                startActivityForResult(intent, 02);
                break;
            }
        }
    }

    private void save() {
        if (dv==null){
            showDialog("请选择设备");
            return;
        }

        SaveTernEntity data = new SaveTernEntity();
        data.setDeviceId((String) tv_bqsb.getTag());
        data.setSensorId(dv.getId());

        data.setSensorValueOpen(getValue(et_on));
        data.setSensorValueClose(getValue(et_off));

        if (tv_sel_on.isSelected()){
            data.setBranchJudgmentOpen("1");
            data.setBranchJudgmentClose("2");
            data.setOpenStateOpen("1");
            data.setOpenStateClose("2");
        }else {
            data.setBranchJudgmentOpen("2");
            data.setBranchJudgmentClose("1");
            data.setOpenStateOpen("2");
            data.setOpenStateClose("1");
        }

        showLoading();
        mPresenter.save(data);
    }

    @Override
    public void getInfo_Success(TernEntity data) {
        dimiss();
        this.id = data.getId();
        tv_dk.setText(data.getPlaceName());
        tv_kzq.setText(data.getControllerName());
        tv_bqsb.setText(data.getDeviceName());
        tv_bqsb.setTag(data.getConditionRelayId());
        if (data.getOpenConditionDetails() != null && data.getOpenConditionDetails().size() > 0) {

            Openconditiondetails o = data.getOpenConditionDetails().get(0);

            if (!TextUtils.isEmpty(o.getBranchJudgment())) {
                et_on.setText(String.valueOf(o.getSensorValue()));
                boolean isSelect = o.getBranchJudgment().equals("1");
                tv_sel_on.setSelected(isSelect);
            }
        }

        if (data.getCloseConditionDetails() != null && data.getCloseConditionDetails().size() > 0) {
            Closeconditiondetails c = data.getCloseConditionDetails().get(0);
            if (!TextUtils.isEmpty(c.getBranchJudgment())) {
                et_off.setText(String.valueOf(c.getSensorValue()));
                boolean isSelect = c.getBranchJudgment().equals("1");
                tv_sel_off.setSelected(isSelect);
            }

        }

        if (data.getControlConditionRelayDetailPo() != null) {
            tv_sb.setText(data.getControlConditionRelayDetailPo().getSensorName());
            dv = new Deviceslist();
            dv.setId(data.getControlConditionRelayDetailPo().getDeviceId());
            dv.setName(data.getControlConditionRelayDetailPo().getSensorName());
        }
    }

    @Override
    public void getInfo_fail(String msg) {
        dimiss();
        showDialog(msg);
    }

    @Override
    public void save_success(String msg) {
        dimiss();
        ToastUtility.showToast(msg);
        setResult(SUCCESSCODE);
        finish();
    }

    @Override
    public void savefail(String msg) {
        dimiss();
        ToastUtility.showToast(msg);
    }

    @Override
    public void delete(String msg) {
        dimiss();
        ToastUtility.showToast(msg);
        setResult(SUCCESSCODE);
        finish();
    }

    @Override
    public void deletefail(String msg) {
        dimiss();
        ToastUtility.showToast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SelectChlidDeviceActivity.DATACODE) {
            ArrayList<Deviceslist> s = (ArrayList<Deviceslist>) data.getSerializableExtra(SelectChlidDeviceActivity.DATAKEY);
            if (s!=null&&s.size()>0){
                dv = s.get(0);
                tv_sb.setText(dv.getName());
                tv_sb.setTextColor(ContextCompat.getColor(SettingTernActivity.this, R.color.title_text_bg_gray));
            }

        }

    }
}

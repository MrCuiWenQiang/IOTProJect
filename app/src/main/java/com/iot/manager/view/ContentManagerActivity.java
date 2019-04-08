package com.iot.manager.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.contract.ContentManagerContract;
import com.iot.manager.entity.net.request.CMRequestEntity;
import com.iot.manager.entity.net.request.SubDevices;
import com.iot.manager.entity.net.result.DksResultEntity;
import com.iot.manager.entity.net.result.content.ContentInfoEntity;
import com.iot.manager.entity.net.result.content.Device;
import com.iot.manager.presenter.ContentManagerPresenter;
import com.iot.manager.view.adapter.DevListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.SpaceItemDecoration;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.zxing.activity.CaptureActivity;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Function : 控制器添加与编辑
 * Remarks  :
 * Created by Mr.C on 2019/3/31 0031.
 */
public class ContentManagerActivity extends BaseMVPAcivity<ContentManagerContract.View, ContentManagerPresenter> implements ContentManagerContract.View
        , View.OnClickListener {
    public static String KEY = "DASDA";

    private EditText et_name;
    private TextView ed_model;
    private EditText et_code;
    private ImageView iv_code;

    private Button bt_init;
    private Button bt_save;

    private RecyclerView rv_ls;
    private DevListAdapter adapter = new DevListAdapter();
    private final int SCAN_RESULT_CODE = 0X36;//扫一扫回调

    private String id=null;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_editcm;
    }


    @Override
    protected void initContentView() {
        et_name = findViewById(R.id.et_name);
        ed_model = findViewById(R.id.ed_model);
        et_code = findViewById(R.id.et_code);
        iv_code = findViewById(R.id.iv_code);
        bt_init = findViewById(R.id.bt_init);
        bt_save = findViewById(R.id.bt_save);
        rv_ls = findViewById(R.id.rv_ls);
        rv_ls.setLayoutManager(new LinearLayoutManager(ContentManagerActivity.this,LinearLayoutManager.VERTICAL,false));
        rv_ls.setAdapter(adapter);
        rv_ls.addItemDecoration(new SpaceItemDecoration(0).setDivider(0.5F, ContextCompat.getColor(ContentManagerActivity.this,R.color.color_light),LinearLayoutManager.VERTICAL));
        isShowCut(false);
        setLeftTitle("添加控制器");
        setToolBarBackgroundColor(R.color.white);

        rv_ls.setNestedScrollingEnabled(false);
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getDkList();
         id = getIntent().getStringExtra(KEY);
        if (!TextUtils.isEmpty(id)) {
            showLoading();
            mPresenter.info(id);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        bt_init.setOnClickListener(this);
        bt_save.setOnClickListener(this);
        iv_code.setOnClickListener(this);
        ed_model.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_code: {
                scan();
                break;
            }
            case R.id.bt_init: {
                showLoading();
                mPresenter.init(getValue(et_code));
                break;
            }
            case R.id.bt_save: {
                save();
                break;
            }
            case R.id.ed_model: {
                showDk();
                break;
            }
        }
    }

    private void scan() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            Intent scanIntent = new Intent(this, CaptureActivity.class);
            startActivityForResult(scanIntent, SCAN_RESULT_CODE);
        }
    }

    private void save(){
        showLoading();
        CMRequestEntity data = new CMRequestEntity();
        data.setId(id);
        if (ed_model.getTag()!=null){
            data.setPlaceId((String) ed_model.getTag());
        }
        data.setDeviceCode(getValue(et_code));
        data.setName(getValue(et_name));
        List<Device> devicesList = adapter.getDevicesList();
        if (devicesList!=null){
            List<SubDevices> subDevices = new ArrayList<>();
            for (Device dv:devicesList) {
                SubDevices subDevices1 = new SubDevices();
                subDevices1.setName(dv.getName());
                subDevices1.setCode(dv.getCode());
                subDevices1.setType(dv.getType());
                subDevices.add(subDevices1);
            }
            data.setSubDevices(subDevices);
        }
        mPresenter.save(data);
    }

    private List<DksResultEntity> datas = null;
    private List<String> names = null;
    private HashMap<String, String> idmap = null;

    @Override
    public void getList_success(List<DksResultEntity> datas) {
        this.datas = datas;
        if (datas != null) {
            names = new ArrayList<>();
            idmap = new HashMap<>();
            for (DksResultEntity e : datas) {
                if (!TextUtils.isEmpty(e.getName())) {
                    names.add(e.getName());
                    idmap.put(e.getName(), e.getId());
                }
            }
        }
    }

    @Override
    public void getList_fail(String msg) {
        showDialog(msg);
    }

    @Override
    public void info_success(ContentInfoEntity entity) {
        dimiss();
        et_name.setText(entity.getName());
        ed_model.setText(entity.getPlaceName());
        ed_model.setTag(entity.getPlaceId());
        et_code.setText(entity.getDeviceCode());
        et_code.setFocusable(false);
        et_code.setKeyListener(null);
        adapter.setDevicesList(entity.getDevicesList());
        bt_save.setVisibility(View.VISIBLE);
    }

    @Override
    public void info_fail(String msg) {
        dimiss();
        showDialog(msg);
    }

    @Override
    public void initSuccess(List<Device> ds) {
        dimiss();
        adapter.setDevicesList(ds);
        bt_save.setVisibility(View.VISIBLE);
    }

    @Override
    public void init_fail(String msg) {
        dimiss();
        showDialog(msg);
    }

    @Override
    public void saveSuccess(String msg) {
        dimiss();
        ToastUtility.showToast(msg);
        setResult(SUCCESSCODE);
        finish();
    }

    @Override
    public void savefail(String msg) {
        dimiss();
        showDialog(msg);
    }

    private void showDk() {
        if (datas == null || datas.size() < 0) {
            ToastUtility.showToast("地块列表未加载完成");
            return;
        }
        SinglePicker<String> picker = new SinglePicker<String>(this, names);
        picker.setCanceledOnTouchOutside(false);
        picker.setSelectedIndex(1);
        picker.setCycleDisable(true);
        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                ed_model.setText(item);
                ed_model.setTag(idmap.get(item));
                ed_model.setTextColor(ContextCompat.getColor(ContentManagerActivity.this, R.color.text_title_color));
            }
        });
        picker.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SCAN_RESULT_CODE&&resultCode==CaptureActivity.CAPTURE_SCAN_CODE) {
            Bundle scanBundle = data.getExtras();
            if (scanBundle != null) {
                String codeData = scanBundle.getString(CaptureActivity.CAPTURE_SCAN_RESULT);
                et_code.setText(codeData);
                mPresenter.init(codeData);
            }
        }
    }
}

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
import com.iot.manager.URLs;
import com.iot.manager.entity.net.request.DeviceDeleteEntity;
import com.iot.manager.entity.net.result.AddDeviceEntity;
import com.iot.manager.entity.net.result.hand.Deviceslist;
import com.iot.manager.entity.net.result.hand.HList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * Function :添加分组 不使用mvp
 * Remarks  :
 * Created by Mr.C on 2019/3/31 0031.
 */
public class AddDeviceActivity extends BaseToolBarActivity{

    public static final String KEYID = "DSA";

    private String id;

    private EditText et_name;
    private EditText et_code;
    private TextView ed_dev;
    private Button bt_save;
    private Button bt_delete;
    private List<Deviceslist> clids =null;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_adddevice;
    }

    /**
     * 初始化布局控件
     *
     * @return
     */
    @Override
    protected void initContentView() {
        isShowCut(false);
        setLeftTitle("添加分组");
        setToolBarBackgroundColor(R.color.white);

        et_name = findViewById(R.id.et_name);
        et_code = findViewById(R.id.et_code);
        ed_dev = findViewById(R.id.ed_dev);
        bt_save = findViewById(R.id.bt_save);
        bt_delete = findViewById(R.id.bt_delete);
    }

    /**
     * 在此方法内为控件赋值 或 开启网络线程访问
     *
     * @param savedInstanceState
     */
    @Override
    public void initData(Bundle savedInstanceState) {
        id = getIntent().getStringExtra(KEYID);
        if (!TextUtils.isEmpty(id)){
            info();
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        ed_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceActivity.this, SelectChlidDeviceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(SelectChlidDeviceActivity.DATAKEY, (Serializable) clids);
                intent.putExtras(bundle);
                startActivityForResult(intent, 02);
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    public void save(){
        AddDeviceEntity addDeviceEntity = new AddDeviceEntity();
        addDeviceEntity.setId(id);
        addDeviceEntity.setName(getValue(et_name));
        addDeviceEntity.setSort(getValue(et_code));
        addDeviceEntity.setDevicesList(clids);
        showLoading();
        HttpHelper.post(URLs.GROUPSAVE, addDeviceEntity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
            }

            @Override
            public void onMessage(String msg) {
                super.onMessage(msg);
                dimiss();
                ToastUtility.showToast(msg);
                setResult(SUCCESSCODE);
                finish();
            }

            @Override
            public void onFailed(int status, String message) {
                dimiss();
                ToastUtility.showToast(message);
            }
        });
    }

    public void info(){
        HashMap hashMap = new HashMap<String,Object>();
        hashMap.put("id",id);
        showLoading();
        HttpHelper.get(URLs.GROUPDETAIL, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                dimiss();
                HList hList = JsonUtil.convertJsonToObject(data,HList.class);
                clids = hList.getDevicesList();
                et_name.setText(hList.getName());
                et_code.setText(hList.getSort());
                settingV();
                bt_delete.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailed(int status, String message) {
                dimiss();
            }
        });
    }

    public void  delete(){
        DeviceDeleteEntity entity = new DeviceDeleteEntity();
        entity.setId(id);
        showLoading();
        HttpHelper.post(URLs.GROUPDELETE, entity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onMessage(String msg) {
                super.onMessage(msg);
                dimiss();
                ToastUtility.showToast(msg);
                setResult(SUCCESSCODE);
                finish();
            }

            @Override
            public void onFailed(int status, String message) {
                dimiss();
                ToastUtility.showToast(message);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SelectChlidDeviceActivity.DATACODE) {
            clids = (ArrayList<Deviceslist>) data.getSerializableExtra(SelectChlidDeviceActivity.DATAKEY);
            settingV();
        }

    }

    private void settingV(){
        if (clids != null && clids.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Deviceslist item : clids) {
                sb.append(item.getName());
                sb.append(",");
            }
            String value = sb.substring(0, sb.length() - 1);
            ed_dev.setText(value);
            ed_dev.setTextColor(ContextCompat.getColor(AddDeviceActivity.this, R.color.title_text_bg_gray));
        } else {
            ed_dev.setText(null);
            ed_dev.setTextColor(ContextCompat.getColor(AddDeviceActivity.this, R.color.color_gray_close));
        }
    }
}

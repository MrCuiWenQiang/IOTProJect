package com.iot.manager.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.URLs;
import com.iot.manager.entity.net.request.DeviceDeleteEntity;
import com.iot.manager.entity.net.result.LinkControlConfigs;
import com.iot.manager.entity.net.result.NexusEditResultEntity;
import com.iot.manager.entity.net.result.hand.Deviceslist;

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
 * Function : 新增联动
 * Remarks  :
 * Created by Mr.C on 2019/4/1 0001.
 */
public class NexusControlEditActivity extends BaseToolBarActivity {

    private EditText et_name;
    private TextView tv_maindev;
    private TextView tv_childdev;

    private Button bt_save;
    private Button bt_delete;

    private String id;
    public static final String IDKEY="IDKEY";

    private RadioGroup radioGroup;
    private RadioButton rb_l,rb_j;
    private String actionState =null;

    private List<Deviceslist> mainclids = null;
    private List<Deviceslist> clids = null;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_nexuscontroledit;
    }

    /**
     * 初始化布局控件
     *
     * @return
     */
    @Override
    protected void initContentView() {
        isShowCut(false);
        setLeftTitle("修改联动控制");
        setToolBarBackgroundColor(R.color.white);

        et_name = findViewById(R.id.et_name);
        tv_maindev = findViewById(R.id.tv_maindev);
        tv_childdev = findViewById(R.id.tv_childdev);
        bt_save = findViewById(R.id.bt_save);
        bt_delete = findViewById(R.id.bt_delete);
        rb_l = findViewById(R.id.rb_l);
        rb_j = findViewById(R.id.rb_j);
        radioGroup = findViewById(R.id.radioGroup);
    }

    /**
     * 在此方法内为控件赋值 或 开启网络线程访问
     *
     * @param savedInstanceState
     */
    @Override
    public void initData(Bundle savedInstanceState) {
        id = getIntent().getStringExtra(IDKEY);
        if (!TextUtils.isEmpty(id)){
            info();
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        tv_maindev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NexusControlEditActivity.this, SelectChlidDeviceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(SelectChlidDeviceActivity.DATAKEY, (Serializable) mainclids);
                intent.putExtras(bundle);
                startActivityForResult(intent, 02);
            }
        });
        tv_childdev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NexusControlEditActivity.this, SelectChlidDeviceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(SelectChlidDeviceActivity.DATAKEY, (Serializable) clids);
                intent.putExtras(bundle);
                startActivityForResult(intent, 03);
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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_l){
                    actionState = "1";
                }else if (i == R.id.rb_j){
                    actionState = "0";
                }
            }
        });
    }

    private void save() {
        NexusEditResultEntity editResultEntity = new NexusEditResultEntity();
        editResultEntity.setId(id);
        editResultEntity.setName(getValue(et_name));
        editResultEntity.setMasterDeviceNames(getValue(tv_maindev.getText()));
        editResultEntity.setPassiveDeviceNames(getValue(tv_childdev.getText()));

        editResultEntity.setActionState(actionState);

        List<LinkControlConfigs> links = new ArrayList<>();

        LinkControlConfigs main = new LinkControlConfigs();
        main.setMasterDeviceIds(getValue(tv_maindev.getTag()));
        main.setMasterDeviceNames(getValue(tv_maindev.getText()));
        main.setPassiveDeviceNames(getValue(tv_childdev.getText()));
        main.setPassiveDeviceIds(getValue(tv_childdev.getTag()));
        main.setAction(actionState);

        links.add(main);
        editResultEntity.setLinkControlConfigs(links);
        showLoading();
        HttpHelper.post(URLs.LINKSAVE, main, new HttpResponseCallback() {
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

    private void info(){
        showLoading();
        HttpHelper.get(URLs.LINKDETAIL+"/"+id, null, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                dimiss();
                NexusEditResultEntity editResultEntity = JsonUtil.convertJsonToObject(data,NexusEditResultEntity.class);
                if (editResultEntity!=null){
                    et_name.setText(editResultEntity.getName());
                    List<LinkControlConfigs> linkControlConfigs = editResultEntity.getLinkControlConfigs();
                    if (linkControlConfigs!=null&&linkControlConfigs.size()>0){
                        LinkControlConfigs item = linkControlConfigs.get(0);
                        tv_maindev.setText(item.getMasterDeviceNames());
                        tv_maindev.setTag(item.getMasterDeviceIds());
                        tv_childdev.setText(item.getPassiveDeviceNames());
                        tv_childdev.setTag(item.getPassiveDeviceIds());
                        if ("1".equals(item.getAction())){
                            rb_l.setChecked(true);
                            actionState = "1";
                        }else {
                            rb_j.setChecked(true);
                            actionState = "0" ;
                        }
                    }
                }
                bt_delete.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailed(int status, String message) {
                dimiss();
                ToastUtility.showToast(message);
            }
        });
    }

    public void  delete(){
        DeviceDeleteEntity entity = new DeviceDeleteEntity();
        entity.setId(id);
        showLoading();
        HttpHelper.post(URLs.LINKDELETE+"/"+id, entity, new HttpResponseCallback() {
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
            ArrayList<Deviceslist> vs = (ArrayList<Deviceslist>) data.getSerializableExtra(SelectChlidDeviceActivity.DATAKEY);
            if (requestCode == 02) {
                mainclids = vs;
                settingV(tv_maindev,vs);
            } else {
                clids = vs;
                settingV(tv_childdev,vs);
            }
        }
    }

    private void settingV(TextView textView,ArrayList<Deviceslist> datas){
        if (datas != null && datas.size() > 0) {
            StringBuilder sb = new StringBuilder();
            StringBuilder idsb = new StringBuilder();
            for (Deviceslist item : datas) {
                sb.append(item.getName());
                sb.append(",");
                idsb.append(item.getId());
                idsb.append(",");
            }
            String value = sb.substring(0, sb.length() - 1);
            String idvalue = idsb.substring(0, idsb.length() - 1);
            textView.setText(value);
            textView.setTag(idvalue);
            textView.setTextColor(ContextCompat.getColor(NexusControlEditActivity.this, R.color.title_text_bg_gray));
        } else {
            textView.setText(null);
            textView.setTag(null);
            textView.setTextColor(ContextCompat.getColor(NexusControlEditActivity.this, R.color.color_gray_close));
        }
    }
}

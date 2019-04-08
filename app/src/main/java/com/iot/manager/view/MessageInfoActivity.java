package com.iot.manager.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.URLs;
import com.iot.manager.entity.net.request.DeviceDeleteEntity;
import com.iot.manager.entity.net.result.MessageRequestEntity;

import java.util.HashMap;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * Function : 消息详情
 * Remarks  :
 * Created by Mr.C on 2019/4/1 0001.
 */
public class MessageInfoActivity extends BaseToolBarActivity {
    public static final String KEY = "KEY";

    private TextView tv_name;
    private TextView tv_date;
    private TextView tv_msg;
    private Button bt_delete;

    private MessageRequestEntity data;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_messageinfo;
    }

    /**
     * 初始化布局控件
     *
     * @return
     */
    @Override
    protected void initContentView() {
        tv_name = findViewById(R.id.tv_name);
        tv_date = findViewById(R.id.tv_date);
        tv_msg = findViewById(R.id.tv_msg);
        bt_delete = findViewById(R.id.bt_delete);

        isShowCut(false);
        setLeftTitle("消息详情");
        setToolBarBackgroundColor(R.color.white);
    }

    /**
     * 在此方法内为控件赋值 或 开启网络线程访问
     *
     * @param savedInstanceState
     */
    @Override
    public void initData(Bundle savedInstanceState) {
        data = (MessageRequestEntity) getIntent().getSerializableExtra(KEY);
        tv_name.setText(data.getSubject());
        tv_msg.setText(data.getContent());
        tv_date.setText(data.getCreateTime());
    }

    @Override
    protected void initListener() {
        super.initListener();
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    private void delete() {
        DeviceDeleteEntity entity = new DeviceDeleteEntity();
        entity.setId(entity.getId());
        HashMap hashMap = new HashMap<String,Object>();
        hashMap.put("id",entity.getId());
        HttpHelper.get(URLs.DELETEMSG+"/"+entity.getId(), hashMap, new HttpResponseCallback() {
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
}

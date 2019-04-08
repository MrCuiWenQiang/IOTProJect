package com.iot.manager.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.URLs;
import com.iot.manager.entity.net.result.UserInfoResultEntity;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.net.loadimage.ImageLoadHelper;

/**
 * Function :个人资料
 * Remarks  :
 * Created by Mr.C on 2019/3/22 0022.
 */
public class UserActivity extends BaseToolBarActivity {

    public static final String USERKEY ="USERKEY";

    private TextView tv_name;
    private TextView tv_email;
    private TextView tv_phone;
    private TextView tv_level;
    private ImageView iv_code;
    private LinearLayout ll_code;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_userme;
    }


    @Override
    protected void initContentView() {
        isShowCut(false);
        setLeftTitle("个人资料");
        setToolBarBackgroundColor(R.color.white);

        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);
        tv_phone = findViewById(R.id.tv_phone);
        tv_level = findViewById(R.id.tv_level);
        iv_code = findViewById(R.id.iv_code);
        ll_code = findViewById(R.id.ll_code);
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        UserInfoResultEntity entity = (UserInfoResultEntity) getIntent().getSerializableExtra(USERKEY);
        tv_name.setText(entity.getName());
        tv_email.setText(entity.getEmail());
        tv_phone.setText(entity.getMobile());
        tv_level.setText(entity.getRolenames());
        if (TextUtils.isEmpty(URLs.BasicUrl+entity.getQrcode())){
            ll_code.setVisibility(View.GONE);
        }else {
            ImageLoadHelper.loadImage(this,iv_code, URLs.BasicUrl+entity.getQrcode());
        }
    }
}

package com.iot.manager.view;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.iot.manager.R;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.util.error.ErrorUtil;

/**
 * Function :关于我们
 * Remarks  :
 * Created by Mr.C on 2019/3/22 0022.
 */
public class MeActivity extends BaseToolBarActivity {
    private TextView tv_b;
    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_me;
    }


    @Override
    protected void initContentView() {
        isShowCut(false);
        setLeftTitle("关于我们");
        setToolBarBackgroundColor(R.color.white);

        tv_b = findViewById(R.id.tv_b);
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
            tv_b.setText(packInfo.versionName);
        }catch (Exception e){
            ErrorUtil.showError(e);
        }

    }
}

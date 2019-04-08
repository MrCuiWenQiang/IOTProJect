package com.iot.manager.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iot.manager.R;
import com.iot.manager.contract.SuggestionContract;
import com.iot.manager.presenter.SuggestionPresenter;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;

/**
 * Function : 意见反馈
 * Remarks  :
 * Created by Mr.C on 2019/3/22 0022.
 */
public class SuggestionActivity extends BaseMVPAcivity<SuggestionContract.View, SuggestionPresenter> implements SuggestionContract.View {

    private EditText et_content;
    private Button bt_tocreate;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_suggestion;
    }

    /**
     * 初始化布局控件
     *
     * @return
     */
    @Override
    protected void initContentView() {
        isShowCut(false);
        setLeftTitle("意见反馈");
        setToolBarBackgroundColor(R.color.white);

        et_content = findViewById(R.id.et_content);
        bt_tocreate = findViewById(R.id.bt_tocreate);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {
        super.initListener();
        bt_tocreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                mPresenter.commitSuggestion(et_content.getText().toString());
            }
        });
    }

    @Override
    public void commit_Success(String msg) {
        dimiss();
        showDialog("意见反馈成功", "谢谢您拿出宝贵让我们做的更好", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
                finish();
            }
        });
    }

    @Override
    public void commit_Fail(String msg) {
        dimiss();
        showDialog(msg, new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                finish();
            }
        });
    }
}

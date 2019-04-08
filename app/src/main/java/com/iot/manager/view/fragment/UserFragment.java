package com.iot.manager.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.contract.Fg_ControlContract;
import com.iot.manager.contract.Fg_UserContract;
import com.iot.manager.entity.net.result.UserInfoResultEntity;
import com.iot.manager.presenter.FG_ControlPresenter;
import com.iot.manager.presenter.FG_UserPresenter;
import com.iot.manager.view.EditPasswordActivity;
import com.iot.manager.view.LoginActivity;
import com.iot.manager.view.MeActivity;
import com.iot.manager.view.SuggestionActivity;
import com.iot.manager.view.UserActivity;
import com.iot.manager.view.video.RecordListActivity;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import cn.faker.repaymodel.activity.manager.ActivityManager;
import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.net.loadimage.ImageLoadHelper;

/**
 * Function :用户中心
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class UserFragment extends BaseMVPFragment<Fg_UserContract.View, FG_UserPresenter> implements Fg_UserContract.View, View.OnClickListener {

    public static UserFragment newInstance() {
        Bundle args = new Bundle();
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ImageView iv_user;
    private TextView tv_user_name;
    private TextView tv_user_tel;

    private TextView toolbar_tv_title;

    private RelativeLayout rl_editpassword;
    private RelativeLayout rl_suggestion;
    private RelativeLayout rl_me;
    private RelativeLayout ll_user;
    private RelativeLayout rl_cancel;

    private RelativeLayout rl_video;
    private RelativeLayout rl_photo;

    private UserInfoResultEntity userInfo;


    @Override
    public int getLayoutId() {
        return R.layout.fg_user;
    }

    /**
     * 在此方法内初始化控件
     *
     * @param v
     */
    @Override
    public void initview(View v) {


        iv_user = v.findViewById(R.id.iv_user);
        tv_user_name = v.findViewById(R.id.tv_user_name);
        tv_user_tel = v.findViewById(R.id.tv_user_tel);
        rl_cancel = v.findViewById(R.id.rl_cancel);
        rl_video = v.findViewById(R.id.rl_video);
        rl_photo = v.findViewById(R.id.rl_photo);

        rl_editpassword = v.findViewById(R.id.rl_editpassword);
        rl_suggestion = v.findViewById(R.id.rl_suggestion);
        ll_user = v.findViewById(R.id.ll_user);
        rl_me = v.findViewById(R.id.rl_me);
        toolbar_tv_title = v.findViewById(R.id.toolbar_tv_title);
        toolbar_tv_title.setVisibility(View.VISIBLE);
        toolbar_tv_title.setText("个人中心");
    }

    /**
     * 在此方法内为控件赋值 或 开启网络线程访问
     *
     * @param savedInstanceState
     */
    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getUserInfo();

    }

    @Override
    protected void initListener() {
        super.initListener();
        rl_editpassword.setOnClickListener(this);
        rl_suggestion.setOnClickListener(this);
        rl_me.setOnClickListener(this);
        ll_user.setOnClickListener(this);
        rl_cancel.setOnClickListener(this);
        rl_video.setOnClickListener(this);
        rl_photo.setOnClickListener(this);
    }

    @Override
    public void getUserInfo_fail(String msg) {
        showDialog(msg);
    }

    @Override
    public void getUserInfo_Success(UserInfoResultEntity userInfo) {
        tv_user_name.setText(userInfo.getName());
        tv_user_tel.setText(userInfo.getLoginname());
        ImageLoadHelper.loadImage(getContext(), iv_user, userInfo.getQrcode());
        this.userInfo = userInfo;
    }

    @Override
    public void unbild_success() {
        dimiss();
        ActivityManager.exit(getActivity());
        toAcitvity(LoginActivity.class);
    }

    @Override
    public void unbild_fail(String msg) {
        dimiss();
        showDialog(msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_editpassword: {
                toAcitvity(EditPasswordActivity.class);
                break;
            }
            case R.id.rl_suggestion: {
                toAcitvity(SuggestionActivity.class);
                break;
            }
            case R.id.rl_me: {
                toAcitvity(MeActivity.class);
                break;
            }
            case R.id.ll_user: {
                Intent intent = new Intent(getContext(), UserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(UserActivity.USERKEY, userInfo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            case R.id.rl_video: {
                Intent intent = new Intent(getContext(), RecordListActivity.class);
                intent.putExtra(RecordListActivity.TYPE_KEY, RecordListActivity.VOID_TYPE);
                startActivity(intent);
                break;
            }
            case R.id.rl_photo: {
                Intent intent = new Intent(getContext(), RecordListActivity.class);
                intent.putExtra(RecordListActivity.TYPE_KEY, RecordListActivity.PHOTO_TYPE);
                startActivity(intent);
                break;
            }
            case R.id.rl_cancel: {
                showDialogAddClean("是否退出登录?", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        showLoading();
                        mPresenter.unbild();
                    }
                });
                break;
            }
        }
    }
}

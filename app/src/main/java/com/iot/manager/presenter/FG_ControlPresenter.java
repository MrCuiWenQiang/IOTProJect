package com.iot.manager.presenter;

import android.support.v4.app.Fragment;

import com.iot.manager.contract.Fg_ControlContract;
import com.iot.manager.view.fragment.control.HandControlFragment;
import com.iot.manager.view.fragment.control.NexusControlFragment;
import com.iot.manager.view.fragment.control.TermControlFragment;
import com.iot.manager.view.fragment.control.TimeControlFragment;

import java.util.ArrayList;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/18 0018.
 */
public class FG_ControlPresenter extends BaseMVPPresenter<Fg_ControlContract.View> implements Fg_ControlContract.Presenter {

    @Override
    public void giveFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HandControlFragment.newInstance());
        fragments.add(TimeControlFragment.newInstance());
        fragments.add(TermControlFragment.newInstance());
        fragments.add(NexusControlFragment.newInstance());
        getView().settingFragments(fragments);
    }
}

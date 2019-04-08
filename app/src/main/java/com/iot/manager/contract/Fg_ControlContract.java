package com.iot.manager.contract;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class Fg_ControlContract {
    public interface View{
        void settingFragments(ArrayList<Fragment> fragments);
    }

    public interface Presenter{
        void giveFragments();
    }


}

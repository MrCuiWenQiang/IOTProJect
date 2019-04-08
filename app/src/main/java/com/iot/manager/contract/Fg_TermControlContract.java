package com.iot.manager.contract;

import com.iot.manager.entity.net.result.termlist.Lists;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class Fg_TermControlContract {
    public interface View{
        void list_success(List<Lists> list);
        void list_finsh(String msg);
    }

    public interface Presenter{
        void list(int page);
    }


}

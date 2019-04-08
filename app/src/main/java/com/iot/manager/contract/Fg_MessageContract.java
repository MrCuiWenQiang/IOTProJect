package com.iot.manager.contract;

import android.text.Editable;

import com.iot.manager.entity.net.result.MessageRequestEntity;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class Fg_MessageContract {
    public interface View{
        void getMessageList_success( List<MessageRequestEntity> datas);
        void getMessageList_fail(String msg);
    }

    public interface Presenter{
        void getMessageList(int page,Editable value);
    }


}

package com.iot.manager.model;



import com.iot.manager.contract.StartContract;
import com.iot.manager.entity.db.UserDBEntity;

import cn.faker.repaymodel.util.db.DBThreadHelper;
import cn.faker.repaymodel.util.db.litpal.LitPalUtils;


/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/1/11 0011.
 */
public class StartModel implements StartContract.Model {

    @Override
    public void  isHaveUser(final OnFindUser onFindUser) {
        DBThreadHelper.startThreadInPool(new DBThreadHelper.ThreadCallback<Boolean>() {

            @Override
            protected Boolean jobContent() throws Exception {
                int count = LitPalUtils.selectCount(UserDBEntity.class);
                    return count>0;
            }

            @Override
            protected void jobEnd(Boolean isHave) {
               onFindUser.onIsHave(isHave);
            }
        });
    }

    public interface OnFindUser {
        void onIsHave(Boolean value);

    }
}

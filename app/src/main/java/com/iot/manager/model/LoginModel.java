package com.iot.manager.model;

import android.text.TextUtils;

import com.iot.manager.contract.LoginContract;
import com.iot.manager.entity.db.UserDBEntity;
import com.iot.manager.entity.net.request.LoginRequestEntity;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

import cn.faker.repaymodel.util.db.DBThreadHelper;
import cn.faker.repaymodel.util.db.litpal.LitPalUtils;
import cn.faker.repaymodel.util.error.ErrorUtil;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class LoginModel implements LoginContract.Model{

    @Override
    public void saveUser(final String userId,final String token, final LoginRequestEntity data) {
        if (TextUtils.isEmpty(token)){
            return;
        }
        DBThreadHelper.startThreadInPool(new DBThreadHelper.ThreadCallback() {
            @Override
            protected Object jobContent() throws Exception {
                UserDBEntity entity = new UserDBEntity(token,new Date());
                entity.setName(data.getName());
                entity.setUserId(userId);
                entity.setPassword(data.getPassword());
                entity.setIsanonymous(!TextUtils.isEmpty(data.getLoginType()));
                int count = LitPalUtils.selectCount(UserDBEntity.class, "name =?", data.getName());
                if (count > 0) {
                    entity.updateAll("name =?", data.getName());
                } else {
                    entity.save();
                }
                return null;
            }

            @Override
            protected void jobEnd(Object o) {

            }
        });
    }

    @Override
    public void findUserByLimit(final OnFindUser onFindUser) {
        DBThreadHelper.startThreadInPool(new DBThreadHelper.ThreadCallback<UserDBEntity>() {

            @Override
            protected UserDBEntity jobContent() throws Exception {
                List<UserDBEntity> datas = null;
                try {
                    datas = DataSupport.limit(1).order("logtimer desc").find(UserDBEntity.class);
                }catch (Exception e){
                    ErrorUtil.showError(e);
                }
                if (datas != null &&datas.size()>0) {
                    return datas.get(0);
                }
                return null;
            }

            @Override
            protected void jobEnd(UserDBEntity data) {
                if (data!=null) {
                    onFindUser.onSuccess(data);
                    return;
                } else {
                    onFindUser.onfail();
                }
            }
        });
    }

    @Override
    public void cleanAll() {
        DataSupport.deleteAll(UserDBEntity.class);
    }

    @Override
    public String findTeleByToken() {
        List<UserDBEntity> datas = null;
        try {
            datas = DataSupport.limit(1).order("logtimer desc").find(UserDBEntity.class);
        } catch (Exception e) {
            ErrorUtil.showError(e);
        }
        if (datas != null && datas.size() > 0) {
            return datas.get(0).getToken();
        }
        return null;
    }
}

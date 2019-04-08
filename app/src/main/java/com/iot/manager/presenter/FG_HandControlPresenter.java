package com.iot.manager.presenter;

import android.text.TextUtils;

import com.iot.manager.URLs;
import com.iot.manager.contract.Fg_HandControlContract;
import com.iot.manager.entity.net.request.HandListRequestEntity;
import com.iot.manager.entity.net.request.HandStatusRequestEntity;
import com.iot.manager.entity.net.result.hand.HandRelayDataEntity;

import java.util.HashMap;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/18 0018.
 */
public class FG_HandControlPresenter extends BaseMVPPresenter<Fg_HandControlContract.View> implements Fg_HandControlContract.Presenter {
    public  final String MY_KEY_TYPE = "0";
    public  final String HE_KEY_TYPE = "1";
    /**
     * @param page
     * @param type 查询类型，0标识私有设备，1标识他人设备
     */
    @Override
    public void getRelayData(int page, final String type) {
        HashMap hashMap = new HashMap<String,Object>();
        hashMap.put("pageSize",SIZE);
        hashMap.put("pageNo",page);
        hashMap.put("searchType",type);
        HttpHelper.get(URLs.GETRELAYDATA, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                HandRelayDataEntity dataEntity = JsonUtil.convertJsonToObject(data,HandRelayDataEntity.class);
                if (dataEntity!=null){
                    if (getView()!=null){
                        getView().getRelayData(dataEntity.getList(),type);
                    }
                }else{
                    if (getView()!=null){
                        getView().fail("数据解析错误");
                    }
                }
            }
            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().fail(message);
                }
            }
        });
    }

    @Override
    public void settingStatus(final int position,final int childposition, String id, boolean value) {
        if (TextUtils.isEmpty(id)){
            getView().settingStatus_Fail(position,"设备标志为空");
        }

        final HandStatusRequestEntity entity = new HandStatusRequestEntity(id,value?"1":"0");
        HttpHelper.post(URLs.SETONOROFF, entity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onMessage(String msg) {
                super.onMessage(msg);
                if (getView()!=null){
                    getView().settingStatus_Success(position,childposition,entity.getOpenState(),msg);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().settingStatus_Fail(position,message);
                }
            }
        });
    }
}

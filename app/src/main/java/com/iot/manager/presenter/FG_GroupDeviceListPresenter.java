package com.iot.manager.presenter;

import com.iot.manager.URLs;
import com.iot.manager.contract.DeviceGroupListContract;
import com.iot.manager.contract.DeviceListContract;
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
public class FG_GroupDeviceListPresenter extends BaseMVPPresenter<DeviceGroupListContract.View> implements DeviceGroupListContract.Presenter {
    public  final String MY_KEY_TYPE = "0";
    public  final String HE_KEY_TYPE = "1";
    /**
     * @param page
     * @param type 查询类型，0标识私有设备，1标识他人设备
     */
    @Override
    public void getRelayData(int page) {
        HashMap hashMap = new HashMap<String,Object>();
        hashMap.put("pageSize",SIZE);
        hashMap.put("pageNo",page);
        hashMap.put("searchType",0);
        HttpHelper.get(URLs.GROUPPAGEDATA, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                HandRelayDataEntity dataEntity = JsonUtil.convertJsonToObject(data,HandRelayDataEntity.class);
                if (dataEntity!=null){
                    if (getView()!=null){
                        getView().getRelayData(dataEntity.getList());
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


}

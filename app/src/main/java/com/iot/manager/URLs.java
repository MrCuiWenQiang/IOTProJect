package com.iot.manager;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/1/10 0010.
 */
public class URLs {
    public static final String BasicUrl = "http://www.dfygiot.com/wlwbg";
    private static final String BaseUrl = BasicUrl+"/api";
    public static final String LOGIN = BaseUrl + "/wxapi/bind";
    public static final String GETBYTOKEN = BaseUrl + "/wxapi/getByToken";
    public static final String UPDATEPWD = BaseUrl + "/agri/account/updatePwd";
    public static final String UNBIND = BaseUrl + "/wxapi/unbind";
    public static final String INSERT = BaseUrl + "/agri/feedback/insert";
    public static final String GETRELAYDATA = BaseUrl + "/agri/control/manual/getRelayData";
    public static final String SETONOROFF = BaseUrl + "/agri/device/setOnOrOff" ;
    public static final String GETTASKDATA = BaseUrl + "/agri/control/task/getTaskData" ;
    public static final String PAUSEORRESTART = BaseUrl + "/agri/control/task/pauseOrRestart" ;
    public static final String SAVE = BaseUrl + "/agri/control/task/save" ;
    public static final String CONDITIONGETRELAYDATA = BaseUrl + "/agri/control/condition/getRelayData" ;
    public static final String DETAIL = BaseUrl + "/agri/control/condition/detail" ;
    public static final String GETLINKDATA = BaseUrl + "/agri/control/link/getLinkData" ;
    public static final String GETPAGEDATA = BaseUrl + "/agri/msg/getPageData" ;
    public static final String GETSENSORLATESTDATA = BaseUrl + "/agri/monitor/environment/getSensorLatestData" ;
    public static final String GETALLPLACESDATA = BaseUrl + "/agri/place/getAllPlacesData" ;
    public static final String PLACEGETPAGEDATA = BaseUrl + "/agri/place/getPageData" ;
    public static final String CONTROLLERGETPAGEDATA = BaseUrl + "/agri/controller/getPageData" ;
    public static final String GROUPPAGEDATA = BaseUrl + "/agri/device/group/getPageData" ;
    public static final String DEVICEGETPAGEDATA = BaseUrl + "/agri/device/getPageData" ;
    public static final String DADD = BaseUrl + "/agri/place/add" ;
    public static final String DKMODIFY = BaseUrl + "/agri/place/modify" ;
    public static final String PLACEDELETE = BaseUrl + "/agri/place/delete" ;
    public static final String GETDEVICELISTBYCONTROLLERCODE = BaseUrl + "/agri/controller/getDeviceListByControllerCode" ;
    public static final String CONTROLLERDETAIL = BaseUrl + "/agri/controller/detail" ;
    public static final String CONTROLSAVE = BaseUrl + "/agri/controller/save" ;
    public static final String CONTROLMODIFY = BaseUrl + "/agri/controller/modify" ;
    public static final String DEVICEMODIFY = BaseUrl + "/agri/device/modify" ;
    public static final String GETSENSORDATA = BaseUrl + "/agri/monitor/environment/getSensorData" ;
    public static final String GROUPSAVE = BaseUrl + "/agri/device/group/save" ;
    public static final String GROUPDETAIL = BaseUrl + "/agri/device/group/detail" ;
    public static final String GROUPDELETE = BaseUrl + "/agri/device/group/delete" ;
    public static final String DELETEMSG = BaseUrl + "/agri/msg/deleteMsg" ;
    public static final String TASKDETAIL = BaseUrl + "/agri/control/task/detail" ;
    public static final String CONDITIONSAVE = BaseUrl + "/agri/control/condition/save" ;
    public static final String CONDITIONDELETE = BaseUrl + "/agri/control/condition/delete" ;
    public static final String LINKSAVE = BaseUrl + "/agri/control/link/save" ;
    public static final String LINKDETAIL = BaseUrl + "/agri/control/link/detail" ;
    public static final String LINKDELETE = BaseUrl + "/agri/control/link/delete" ;
}

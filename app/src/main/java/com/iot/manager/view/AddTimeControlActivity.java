package com.iot.manager.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.contract.AddTimeControlContract;
import com.iot.manager.entity.net.request.AddTimerRequestEntity;
import com.iot.manager.entity.net.result.TimerListResultEntity;
import com.iot.manager.entity.net.result.hand.Deviceslist;
import com.iot.manager.presenter.AddTimeControlPresenter;
import com.iot.manager.view.widget.WeekCheckBoxView;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.DateUtils;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.TimePicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Function :添加定时控制任务
 * Remarks  :
 * Created by Mr.C on 2019/3/24 0024.
 */
public class AddTimeControlActivity extends BaseMVPAcivity<AddTimeControlContract.View, AddTimeControlPresenter> implements AddTimeControlContract.View, View.OnClickListener {

    private TextView tv_startdate;//开始日期
    private TextView tv_enddate;//结束日期
    private TextView tv_starttime;
    private TextView tv_device;
    private TextView tv_endtime;
    private TextView tv_time;//运行间隔
    private LinearLayout ll_dev;
    private Button bt_save;
    private WeekCheckBoxView v_makker;

    private EditText ed_name;
    private EditText tv_content;

    private ArrayList<Deviceslist> clids;

    private String id;
    public static final String IDKEY = "IDKEY";

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_addtimer;
    }

    @Override
    protected void initContentView() {
        isShowCut(false);
        setLeftTitle("添加定时任务");
        setToolBarBackgroundColor(R.color.white);

        tv_startdate = findViewById(R.id.tv_startdate);
        tv_enddate = findViewById(R.id.tv_enddate);
        tv_starttime = findViewById(R.id.tv_starttime);
        tv_endtime = findViewById(R.id.tv_endtime);
        tv_time = findViewById(R.id.tv_time);
        ll_dev = findViewById(R.id.ll_dev);
        tv_device = findViewById(R.id.tv_device);
        v_makker = findViewById(R.id.v_makker);
        bt_save = findViewById(R.id.bt_save);
        ed_name = findViewById(R.id.ed_name);
        tv_content = findViewById(R.id.tv_content);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        id = getIntent().getStringExtra(IDKEY);
        if (!TextUtils.isEmpty(id)){
//            showLoading();
            mPresenter.info(id);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        tv_startdate.setOnClickListener(this);
        tv_enddate.setOnClickListener(this);
        tv_starttime.setOnClickListener(this);
        tv_endtime.setOnClickListener(this);
        tv_time.setOnClickListener(this);
        ll_dev.setOnClickListener(this);
        bt_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_startdate: {
                onYearMonthDayPicker(tv_startdate);
                break;
            }
            case R.id.tv_enddate: {
                onYearMonthDayPicker(tv_enddate);
                break;
            }
            case R.id.tv_starttime: {
                onTimePicker(tv_starttime);
                break;
            }
            case R.id.tv_endtime: {
                onTimePicker(tv_endtime);
                break;
            }
            case R.id.tv_time: {
                onTimePicker(tv_time);
                break;
            }
            case R.id.bt_save: {
                save();
                break;
            }
            case R.id.ll_dev: {
                Intent intent = new Intent(this, SelectChlidDeviceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(SelectChlidDeviceActivity.DATAKEY, clids);
                intent.putExtras(bundle);
                startActivityForResult(intent, 02);
                break;
            }
        }
    }

    private void save() {
        AddTimerRequestEntity data = new AddTimerRequestEntity();
        data.setName(ed_name.getText().toString());
        data.setDeviceIds(getIds());
        data.setDeviceNames(getNames());
        data.setId(id);
        List<String> weekv = v_makker.getSelect();
        getweeks(weekv);

        data.setStartDate(getText(tv_startdate));
        data.setEndDate(getText(tv_enddate));
        data.setRemark(getText(tv_content));

        AddTimerRequestEntity.TimeEntity s_entity = new AddTimerRequestEntity.TimeEntity();
        s_entity.setExecAction("00");
        s_entity.setExecTime(getText(tv_starttime));

        AddTimerRequestEntity.TimeEntity e_entity = new AddTimerRequestEntity.TimeEntity();
        e_entity.setExecAction("01");
        e_entity.setExecTime(getText(tv_endtime));

        List<AddTimerRequestEntity.TimeEntity> taskDetailList = new ArrayList<>();
        taskDetailList.add(s_entity);
        taskDetailList.add(e_entity);
        data.setTaskDetailList(taskDetailList);
        data.setWeeks(v_makker.getSelectC());
        showLoading();
        mPresenter.add(data);
    }

    private String getText(TextView tv){
        CharSequence value = tv.getText();
        if (value!=null){
            return value.toString();
        }else {
            return null;
        }
    }    private String getText(EditText tv){
        CharSequence value = tv.getText();
        if (value!=null){
            return value.toString();
        }else {
            return null;
        }
    }

    private String getweeks( List<String> weekv){
        StringBuilder sb = new StringBuilder();
        if (weekv!=null){
            for (String value:
                    weekv) {
                sb.append(value);
                sb.append(",");
            }
            return sb.substring(0,sb.length()-1);
        }else {
            return null;
        }
    }

    private String getIds(){
        StringBuilder sb = new StringBuilder();
        if (clids!=null&&clids.size()>0){
            for (Deviceslist chlid:
                 clids) {
                sb.append(chlid.getId());
                sb.append(",");
            }
            return sb.substring(0,sb.length()-1);
        }else {
            return null;
        }
    }

    private String getNames(){
        StringBuilder sb = new StringBuilder();
        if (clids!=null&&clids.size()>0){
            for (Deviceslist chlid:
                    clids) {
                sb.append(chlid.getName());
                sb.append(",");
            }
            return sb.substring(0,sb.length()-1);
        }else {
            return null;
        }
    }

    public void onYearMonthDayPicker(final TextView view) {
        int year = DateUtils.getYear();
        int month = DateUtils.getMonth();
        int day = DateUtils.getDay();

        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setRangeEnd(year + 10, 12, 31);
        picker.setRangeStart(year, month, day);
        picker.setSelectedItem(year, month, day);
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                view.setText(year + "-" + month + "-" + day);
                settingTextColor(view);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }


    public void onTimePicker(final TextView view) {
        TimePicker picker = new TimePicker(this, TimePicker.HOUR_24);
        picker.setUseWeight(false);
        picker.setCycleDisable(false);
        picker.setRangeStart(0, 0);//00:00
        picker.setRangeEnd(23, 59);//23:59
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        picker.setSelectedItem(currentHour, currentMinute);
        picker.setTopLineVisible(false);
        picker.setTextPadding(ConvertUtils.toPx(this, 15));
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                if (view.equals(tv_time)) {
                    view.setText(hour + "小时" + minute + "分钟");
                } else {
                    view.setText(hour + ":" + minute);
                }
                settingTextColor(view);
            }
        });
        picker.show();
    }

    private void settingTextColor(TextView textView) {
        textView.setTextColor(ContextCompat.getColor(this, R.color.black));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SelectChlidDeviceActivity.DATACODE) {
            clids = (ArrayList<Deviceslist>) data.getSerializableExtra(SelectChlidDeviceActivity.DATAKEY);
            if (clids != null && clids.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (Deviceslist item : clids) {
                    sb.append(item.getName());
                    sb.append(",");
                }
                String value = sb.substring(0, sb.length() - 1);
                tv_device.setText(value);
                tv_device.setTextColor(ContextCompat.getColor(AddTimeControlActivity.this, R.color.title_text_bg_gray));
            } else {
                tv_device.setText(null);
                tv_device.setTextColor(ContextCompat.getColor(AddTimeControlActivity.this, R.color.color_gray_close));
            }
        }

    }

    @Override
    public void add_Successs(String msg) {
        dimiss();
        setResult(SUCCESSCODE);
        finish();
    }

    @Override
    public void add_Fail(String msg) {
        dimiss();
        showDialog(msg);
    }

    @Override
    public void info(TimerListResultEntity data) {
        dimiss();
        ed_name.setText(data.getName());
        tv_startdate.setText(DateUtils.dateToString(data.getStartDate()));
        tv_enddate.setText(DateUtils.dateToString(data.getEndDate()));

        v_makker.setValue(data.getWeeks());


        tv_starttime.setText(data.getStartTime());
        tv_endtime.setText(data.getEndTime());

        int time = data.getIntervalTime();
        int hours = (int) Math.floor(time / 60);
        int minute = time % 60;
        tv_time.setText(hours + "小时" + minute + "分钟");
        tv_content.setText(data.getRemark());
        tv_device.setText(data.getDeviceNames());

        if (!TextUtils.isEmpty(data.getDeviceNames())){
            String[] names = data.getDeviceNames().split(",");
            String[] ids = data.getDeviceIds().split(",");
            clids = new ArrayList<>();
            for (int i = 0; i < names.length; i++) {
                Deviceslist deviceslist = new Deviceslist();
                deviceslist.setName(names[i]);;
                deviceslist.setId(ids[i]);;
                clids.add(deviceslist);
            }
        }

    }

    @Override
    public void info_fail(String msg) {
        dimiss();
        showDialog(msg, new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                finish();
            }
        });
    }
}

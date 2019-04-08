package com.iot.manager.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iot.manager.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/26 0026.
 */
public class WeekCheckBoxView extends LinearLayout{
    Map<String,String> weekMap = new LinkedHashMap<>();


    public WeekCheckBoxView(Context context) {
        this(context,null);
    }

    public WeekCheckBoxView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WeekCheckBoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initValue();
        for (String key:weekMap.keySet()) {
            View view = createChilderView(weekMap.get(key),key);
            addView(view);
        }
    }

    private void initValue(){
        weekMap.put("7","日");
        weekMap.put("1","一");
        weekMap.put("2","二");
        weekMap.put("3","三");
        weekMap.put("4","四");
        weekMap.put("5","五");
        weekMap.put("6","六");
    }

    public void setValue(String value){
        if (TextUtils.isEmpty(value)){
            return;
        }
        String[] vs = value.split(",");
        ArrayList<String> ls = new ArrayList<>();
        int cpunt = getChildCount();
        for (int i = 0; i < cpunt ; i++) {
            LinearLayout ll = (LinearLayout) getChildAt(i);
            CheckBox checkBox = (CheckBox) ll.getChildAt(1);
            if (checkBox.isChecked()){
                String key = (String) checkBox.getTag();
                for (String ii:vs) {
                    if (key.equals(ii)){
                        checkBox.setChecked(true);
                    }
                }
            }
        }
    }

    private void clean(){
        ArrayList<String> ls = new ArrayList<>();
        int cpunt = getChildCount();
        for (int i = 0; i < cpunt ; i++) {
            LinearLayout ll = (LinearLayout) getChildAt(i);
            CheckBox checkBox = (CheckBox) ll.getChildAt(1);
            checkBox.setChecked(false);
        }
    }

    public ArrayList<String> getSelect(){
        ArrayList<String> ls = new ArrayList<>();
        int cpunt = getChildCount();
        for (int i = 0; i < cpunt ; i++) {
            LinearLayout ll = (LinearLayout) getChildAt(i);
            CheckBox checkBox = (CheckBox) ll.getChildAt(1);
            if (checkBox.isChecked()){
                String value = (String) checkBox.getTag();
                ls.add(value);
            }
        }
        return ls;
    }
    public String getSelectC(){
        StringBuilder stringBuilder = new StringBuilder();
        int cpunt = getChildCount();
        for (int i = 0; i < cpunt ; i++) {
            LinearLayout ll = (LinearLayout) getChildAt(i);
            CheckBox checkBox = (CheckBox) ll.getChildAt(1);
            if (checkBox.isChecked()){
                String value = (String) checkBox.getTag();
                stringBuilder.append(value);
                stringBuilder.append(",");
            }
        }
        return stringBuilder.substring(0,stringBuilder.length()-1);
    }

    private  View createChilderView(String name,String value){
        LinearLayout ll = new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT,1);
        ll.setGravity(Gravity.CENTER);
        ll.setLayoutParams(layout);

        TextView tv_name = new TextView(getContext());
        tv_name.setText(name);
        tv_name.setTextAppearance(getContext(), R.style.tx_name);
        LinearLayout.LayoutParams tv_p = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv_name.setLayoutParams(tv_p);

        CheckBox checkBox = new CheckBox(getContext());
        checkBox.setChecked(true);
        checkBox.setTag(value);
        LinearLayout.LayoutParams c_p = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        checkBox.setLayoutParams(c_p);

        ll.addView(tv_name);
        ll.addView(checkBox);
        return ll;
    }

}

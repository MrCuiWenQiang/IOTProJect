package com.iot.manager.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iot.manager.R;

import java.util.HashMap;

/**
 * Function : 一周日期呈现
 * Remarks  :
 * Created by Mr.C on 2019/3/25 0025.
 */
public class WeekView extends LinearLayout{

    HashMap<String,String> weekMap = new HashMap();

    public WeekView(Context context) {
        this(context,null);
    }

    public WeekView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WeekView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        weekMap.put("7","日");
        weekMap.put("1","一");
        weekMap.put("2","二");
        weekMap.put("3","三");
        weekMap.put("4","四");
        weekMap.put("5","五");
        weekMap.put("6","六");
    }

    public void setWeeks(String value){
        if (TextUtils.isEmpty(value)){
            return;
        }
        if (getChildCount()>0){
            removeAllViews();
        }
        String[] weekvas = getValues(value);
        for (String d:weekvas){
            String v = weekMap.get(d);
            if (!TextUtils.isEmpty(v)){
                createView(v);
            }
        }
    }


    public String[] getValues(String value){
        return value.split(",");
    }

    public TextView createView(String value){
        TextView tv = new TextView(getContext());
        tv.setText(value);
        tv.setTextSize(13);
        tv.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tv.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.login_text));
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setMargins(5,0,0,0);
        tv.setLayoutParams(layout);
        tv.setPadding(3,2,3,2);
        if (value.equals("日")){
            this.addView(tv,0);
        }else {
            this.addView(tv);
        }
        return tv;
    }

}

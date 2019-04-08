package com.iot.manager.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.heweather.DailyForecast;
import com.iot.manager.entity.net.result.heweather.Heweather5;
import com.iot.manager.entity.net.result.heweather.Wind;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import cn.faker.repaymodel.util.DateUtils;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class MainHeWeatherAdapter extends RecyclerView.Adapter<MainHeWeatherAdapter.ViewHolder> {

    private Heweather5 data;

    public void setData(Heweather5 data) {
        this.data = data;
    }

    public Heweather5 getData() {
        return data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_heweather, viewGroup, false);
        return new ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (data==null){
            return;
        }
        if (i != 0) {
            viewHolder.today.setVisibility(View.GONE);
        } else {
            viewHolder.today.setVisibility(View.VISIBLE);
        }
        if (data.getDailyForecast()!=null&&data.getDailyForecast().size()>i){
            settingOne(viewHolder,data.getDailyForecast().get(i));
        }
        switch (i){
            case 0:{
                viewHolder.iv_date.setImageResource(R.mipmap.one);
                break;
            }
            case 1:{
                viewHolder.iv_date.setImageResource(R.mipmap.two);
                break;
            }
            case 2:{
                viewHolder.iv_date.setImageResource(R.mipmap.ther);
                break;
            }
        }

    }
    private void settingOne(ViewHolder viewHolder,DailyForecast dailyForecast){

        viewHolder.today.setText(data.getNow().getTmp()+"°");

        viewHolder.tv_toup.setText(dailyForecast.getTmp().getMax()+"°");
        viewHolder.tv_topdown.setText(dailyForecast.getTmp().getMin()+"°");
        viewHolder.tv_tq.setText(dailyForecast.getCond().getTxt_d());

        Wind wind = dailyForecast.getWind();
        viewHolder.tv_wind.setText(wind.getDir()+" "+wind.getSc()+"级");
        viewHolder.tv_hum.setText("相对湿度 "+dailyForecast.getHum()+"%");
        viewHolder.tv_date.setText(DateUtils.dateToString(dailyForecast.getDate()));
    }


    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView today;//今日温度
        public TextView tv_toup;//最高气温
        public TextView tv_topdown;//最低气温
        public TextView tv_tq;//阴天晴天
        public TextView tv_wind;//风级
        public TextView tv_hum;//湿度
        public TextView tv_date;
        public ImageView iv_date;
        private MainHeWeatherAdapter mAdapter;

        public ViewHolder(View itemView, MainHeWeatherAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            today = (TextView) itemView.findViewById(R.id.today);
            tv_toup = (TextView) itemView.findViewById(R.id.tv_toup);
            tv_topdown = (TextView) itemView.findViewById(R.id.tv_topdown);
            tv_tq = (TextView) itemView.findViewById(R.id.tv_tq);
            tv_wind = (TextView) itemView.findViewById(R.id.tv_wind);
            tv_hum = (TextView) itemView.findViewById(R.id.tv_hum);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            iv_date = (ImageView) itemView.findViewById(R.id.iv_date);

        }


    }
}

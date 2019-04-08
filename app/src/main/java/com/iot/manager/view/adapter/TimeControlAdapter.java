package com.iot.manager.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.TimerListResultEntity;
import com.iot.manager.view.widget.WeekView;

import java.util.ArrayList;

import cn.faker.repaymodel.util.DateUtils;
import cn.faker.repaymodel.widget.view.button.SwitchButton;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/24 0024.
 */
public class TimeControlAdapter extends RecyclerView.Adapter<TimeControlAdapter.ViewHolder> {

    private ArrayList<TimerListResultEntity> datas;

    public ArrayList<TimerListResultEntity> getDatas() {
        return datas;
    }
    private OnItemCheckedChangeListener mOnItemCheckedChangeListener;

    public void clean(){
        if (this.datas != null) {
            this.datas.clear();
        }
    }

    public void addDatas(ArrayList<TimerListResultEntity> datas) {
        if (datas == null) {
            return;
        }
        if (this.datas != null) {
            this.datas.addAll(datas);
        } else {
            this.datas = datas;
        }
        notifyDataSetChanged();
    }

    public void setmOnItemCheckedChangeListener(OnItemCheckedChangeListener mOnItemCheckedChangeListener) {
        this.mOnItemCheckedChangeListener = mOnItemCheckedChangeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_timer, viewGroup, false);
        return new ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
         TimerListResultEntity data = datas.get(i);
        viewHolder.tv_name.setText(data.getName());

        viewHolder.tv_date.setText(DateUtils.dateToString(data.getStartDate())+"至"+DateUtils.dateToString(data.getEndDate()));
        viewHolder.tv_weeks.setWeeks(data.getWeeks());
        viewHolder.tv_time.setText(data.getStartTime()+"至"+data.getEndTime());
        viewHolder.tv_intervaltime.setText(data.getIntervalTime()+"分钟");
        viewHolder.tv_devicenames.setText(data.getDeviceNames());
        viewHolder.s_status.setOnCheckedChangeListener(null);
        viewHolder.s_status.setChecked(data.getState()==1);
        viewHolder.s_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mOnItemCheckedChangeListener!=null){
                    mOnItemCheckedChangeListener.onCheckedChanged(viewHolder.getAdapterPosition(),b);
                }
            }
        });
        viewHolder.im_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemCheckedChangeListener!=null){
                    mOnItemCheckedChangeListener.onCheckedEdit(viewHolder.getAdapterPosition(),datas.get(viewHolder.getAdapterPosition()));
                }
            }
        });
    }
    public interface OnItemCheckedChangeListener{
        void onCheckedChanged(int position, boolean b) ;
        void onCheckedEdit(int position,TimerListResultEntity entity) ;
    }
    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TimeControlAdapter adapter;

        public TextView tv_name;
        public TextView tv_date;
        public WeekView tv_weeks;
        public TextView tv_time;
        public TextView tv_intervaltime;
        public TextView tv_devicenames;
        public SwitchButton s_status;
        public ImageView im_edit;


        public ViewHolder(@NonNull View itemView,TimeControlAdapter adapter) {
            super(itemView);
            this.adapter = adapter;

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_weeks = itemView.findViewById(R.id.tv_weeks);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_intervaltime = itemView.findViewById(R.id.tv_intervaltime);
            tv_devicenames = itemView.findViewById(R.id.tv_devicenames);
            s_status = itemView.findViewById(R.id.s_status);
            im_edit = itemView.findViewById(R.id.im_edit);
        }

        @Override
        public void onClick(View view) {

        }
    }
}

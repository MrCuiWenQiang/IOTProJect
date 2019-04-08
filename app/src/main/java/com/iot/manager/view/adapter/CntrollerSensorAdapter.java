package com.iot.manager.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.SorDataSendEntity;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/31 0031.
 */
public class CntrollerSensorAdapter extends RecyclerView.Adapter<CntrollerSensorAdapter.ViweHolder>{

    private List<SorDataSendEntity> datas;

    public List<SorDataSendEntity> getDatas() {
        return datas;
    }
    public void clean(){
        if (this.datas != null) {
            this.datas.clear();
        }
    }
    public void setDatas(List<SorDataSendEntity> datas) {
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

    @NonNull
    @Override
    public ViweHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_cs, viewGroup, false);
        return new CntrollerSensorAdapter.ViweHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViweHolder viweHolder, int i) {
        SorDataSendEntity d = datas.get(i);
        viweHolder.tv_v.setText(d.getValue()+d.getUnitKey());
        viweHolder.tv_d.setText(d.getReadTime());
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    public class ViweHolder extends RecyclerView.ViewHolder{
        private TextView tv_v;
        private TextView tv_d;
        public ViweHolder(@NonNull View itemView) {
            super(itemView);
            tv_v = itemView.findViewById(R.id.tv_v);
            tv_d = itemView.findViewById(R.id.tv_d);
        }
    }
}

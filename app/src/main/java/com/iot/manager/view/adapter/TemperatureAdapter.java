package com.iot.manager.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.TemperatureResultEntity;

import java.util.List;

import cn.faker.repaymodel.net.loadimage.ImageLoadHelper;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/28 0028.
 */
public class TemperatureAdapter extends RecyclerView.Adapter<TemperatureAdapter.ViewHolder>{

    private List<TemperatureResultEntity> datas;
    private onClickLinear onClickLinear;

    public void setOnClickLinear(TemperatureAdapter.onClickLinear onClickLinear) {
        this.onClickLinear = onClickLinear;
    }

    public void setDatas(List<TemperatureResultEntity> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_temoerarurer, viewGroup, false);
        return new ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TemperatureResultEntity data = datas.get(i);
        viewHolder.tv_name.setText(data.getSensorName());
        viewHolder.tv_value.setText(data.getValue());
        viewHolder.tv_unitkey.setText(data.getUnitKey());
        viewHolder.tv_date.setText(data.getReadTime());
        ImageLoadHelper.loadImage(viewHolder.iv_icon.getContext(),viewHolder.iv_icon,data.getIcon());
    }

    public interface onClickLinear{
        void onClick(int position,TemperatureResultEntity data);
    }
    public void onEditClick(TemperatureAdapter.ViewHolder viewHolder) {
        if (onClickLinear!=null){
            onClickLinear.onClick(viewHolder.getAdapterPosition(),datas.get(viewHolder.getAdapterPosition()));
        }
    }
    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TemperatureAdapter adapter;

        private TextView tv_name;
        private TextView tv_value;
        private TextView tv_unitkey;
        private TextView tv_date;
        private ImageView iv_icon;

        public ViewHolder(@NonNull View itemView, final TemperatureAdapter adapter) {
            super(itemView);
            this.adapter = adapter;

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_value = itemView.findViewById(R.id.tv_value);
            tv_unitkey = itemView.findViewById(R.id.tv_unitkey);
            tv_date = itemView.findViewById(R.id.tv_date);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.onEditClick(ViewHolder.this);
                }
            });
        }
    }
}

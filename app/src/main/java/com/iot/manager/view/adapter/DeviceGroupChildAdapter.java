package com.iot.manager.view.adapter;

import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.DeviceGroupResultEntitr.Deviceslist;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/23 0023.
 */
public class DeviceGroupChildAdapter extends RecyclerView.Adapter<DeviceGroupChildAdapter.ViewHolder> {


    private List<Deviceslist> devicesList;

    public DeviceGroupChildAdapter(List<Deviceslist> devicesList) {
        this.devicesList = devicesList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_gchild, viewGroup, false);
        return new DeviceGroupChildAdapter.ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (devicesList == null||devicesList.size()==0){
            viewHolder.tv_state.setVisibility(View.VISIBLE);
            viewHolder.tv_state.setText("该分组下无设备");
            return;
        }
        viewHolder.tv_state.setVisibility(View.GONE);
        Deviceslist deviceslist = devicesList.get(i);
        viewHolder.tv_name.setText(deviceslist.getName());

            viewHolder.settingTextColor(viewHolder.tv_name,R.color.text_title_color);
    }

    @Override
    public int getItemCount() {
        return devicesList == null ? 1 : devicesList.size();
    }




    private void onItemCheckClick(DeviceGroupChildAdapter.ViewHolder itemHolder, View v) {

    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private DeviceGroupChildAdapter adapter;

        public TextView tv_name;
        public TextView tv_state;


        public ViewHolder(@NonNull View itemView, DeviceGroupChildAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_state = itemView.findViewById(R.id.tv_state);
            tv_name.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_name: {
                    adapter.onItemCheckClick(this, view);
                }
            }
        }

        public void settingTextColor(TextView tv, @ColorRes int color){
            tv.setTextColor(ContextCompat.getColor(tv.getContext(), color));
        }
    }


}

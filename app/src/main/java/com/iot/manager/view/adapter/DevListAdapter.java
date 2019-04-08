package com.iot.manager.view.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.content.ContentInfoEntity;
import com.iot.manager.entity.net.result.content.Device;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/31 0031.
 */
public class DevListAdapter extends RecyclerView.Adapter<DevListAdapter.ViewHolder> {

    private List<Device> devicesList;

    public void setDevicesList(List<Device> devicesList) {
        this.devicesList = devicesList;
        notifyDataSetChanged();
    }

    public List<Device> getDevicesList() {
        return devicesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_dev_i, viewGroup, false);
        return new DevListAdapter.ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Device data = devicesList.get(i);
        if (TextUtils.isEmpty(data.getName())){
            viewHolder.et_a.setText(data.getSubDefaultName());
        }else {
            viewHolder.et_a.setText(data.getName());
        }
        viewHolder.et_style.setText(data.getSubDefaultName());
        if ("1".equals(data.getType())){
            viewHolder.iv_icon.setBackground(ContextCompat.getDrawable(viewHolder.iv_icon.getContext(),R.mipmap.relay));
        }else {
            viewHolder.iv_icon.setBackground(ContextCompat.getDrawable(viewHolder.iv_icon.getContext(),R.mipmap.sensor));
        }
    }

    @Override
    public int getItemCount() {
        return devicesList==null?0:devicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private EditText et_style;
        private EditText et_a;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            et_style = itemView.findViewById(R.id.et_style);
            et_a = itemView.findViewById(R.id.et_a);
        }
    }
}

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
import com.iot.manager.entity.net.result.hand.Deviceslist;

import java.util.List;

import cn.faker.repaymodel.widget.view.button.SwitchButton;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/23 0023.
 */
public class DevManagerChildAdapter extends RecyclerView.Adapter<DevManagerChildAdapter.ViewHolder> {

    private OnItemCheckedChangeListener mOnItemCheckedChangeListener;

    private List<Deviceslist> devicesList;

    public DevManagerChildAdapter(List<Deviceslist> devicesList) {
        this.devicesList = devicesList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_dm_child, viewGroup, false);
        return new DevManagerChildAdapter.ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Deviceslist deviceslist = devicesList.get(i);
        viewHolder.tv_name.setText(deviceslist.getName());
        if ("1".equals(deviceslist.getType())) {
            viewHolder.s_status.setVisibility(View.VISIBLE);
            boolean checked = deviceslist.getOpenState().equals("1");
            viewHolder.s_status.setChecked(checked);
            viewHolder.s_status.setOnCheckedChangeListener(viewHolder);//设置check时候会回调状态方法
        } else {
            viewHolder.s_status.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return devicesList == null ? 0 : devicesList.size();
    }

    public void setmOnItemCheckedChangeListener(OnItemCheckedChangeListener mOnItemCheckedChangeListener) {
        this.mOnItemCheckedChangeListener = mOnItemCheckedChangeListener;
    }

    public Deviceslist getDevices(int count) {
        if (devicesList != null && count <= devicesList.size()) {
            return devicesList.get(count);
        } else {
            return null;
        }
    }

    private void onItemCheckClick(RecyclerView.ViewHolder itemHolder, boolean b) {
        if (mOnItemCheckedChangeListener != null) {
            mOnItemCheckedChangeListener.onCheckedChanged(itemHolder.getAdapterPosition(), b);
        }
    }
    private void onItemCheckClick(RecyclerView.ViewHolder itemHolder) {
        if (mOnItemCheckedChangeListener != null) {
            mOnItemCheckedChangeListener.onSetting(itemHolder.getAdapterPosition(), getDevices(itemHolder.getAdapterPosition()));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

        private DevManagerChildAdapter adapter;

        public TextView tv_name;
        public SwitchButton s_status;
        public ImageView im_setting;

        public ViewHolder(@NonNull View itemView, DevManagerChildAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            tv_name = itemView.findViewById(R.id.tv_name);
            s_status = itemView.findViewById(R.id.s_status);
            im_setting = itemView.findViewById(R.id.im_setting);
            im_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewHolder.this.adapter.onItemCheckClick(ViewHolder.this);
                }
            });
        }


        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            adapter.onItemCheckClick(this, b);
        }
    }


    public interface OnItemCheckedChangeListener {
        void onCheckedChanged(int position, boolean b);
        void onSetting(int position, Deviceslist b);
    }
}

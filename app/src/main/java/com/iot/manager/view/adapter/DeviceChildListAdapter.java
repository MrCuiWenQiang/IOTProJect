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
import com.iot.manager.entity.net.result.hand.Deviceslist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/23 0023.
 */
public class DeviceChildListAdapter extends RecyclerView.Adapter<DeviceChildListAdapter.ViewHolder> {


    private List<Deviceslist> devicesList;
    private Map<Integer, Boolean>  paramMap;;
    private ArrayList<Deviceslist> oldDatas;
    private Deviceslist oldItem;
    private boolean aItem = false;
    private int mporsion = -1;

    private DeviceListAdapter ll_adapter;

    public void setLl_adapter(DeviceListAdapter ll_adapter) {
        this.ll_adapter = ll_adapter;
    }

    public int getPorsion() {
        return mporsion;
    }

    public void setPorsion(int porsion) {
        this.mporsion = porsion;
    }

    public void setaItem(boolean aItem) {
        this.aItem = aItem;
    }

    public DeviceChildListAdapter(List<Deviceslist> devicesList) {
        this.devicesList = devicesList;
    }

    public void setParamMap(Map<Integer, Boolean> paramMap) {
        this.paramMap = paramMap;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_devchild, viewGroup, false);
        return new DeviceChildListAdapter.ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Deviceslist deviceslist = devicesList.get(i);
        viewHolder.tv_name.setText(deviceslist.getName());

        if (oldDatas!=null){
            for  (Deviceslist itemd:oldDatas){
                if (deviceslist.getId().equals(itemd.getId())){
                    paramMap.put(i,true);
                }
            }
        }
        if (oldItem!=null){
            if (deviceslist.getId().equals(oldItem.getId())){
                paramMap.put(i,true);
            }
        }

        if (paramMap.containsKey(i)){
            boolean value = paramMap.get(i);
            if (value){
                viewHolder.settingTextColor(viewHolder.tv_name,R.color.login_text);
            }else {
                viewHolder.settingTextColor(viewHolder.tv_name,R.color.text_title_color);
            }
        }else {
            viewHolder.settingTextColor(viewHolder.tv_name,R.color.text_title_color);
        }
    }

    @Override
    public int getItemCount() {
        return devicesList == null ? 0 : devicesList.size();
    }

    public void setOldDatas(ArrayList<Deviceslist> oldDatas) {
        this.oldDatas = oldDatas;
    }
    public void setoldItem(Deviceslist oldItem) {
        this.oldItem = oldItem;
    }
    public Deviceslist getDevices(int count) {
        if (devicesList != null && count <= devicesList.size()) {
            return devicesList.get(count);
        } else {
            return null;
        }
    }

    private void onItemCheckClick(DeviceChildListAdapter.ViewHolder itemHolder, View v) {
        int position = itemHolder.getAdapterPosition();
        if (paramMap.containsKey(position)){
            boolean value = paramMap.get(position);
            if (aItem){
                paramMap.clear();
            }
            /*if (value){
                itemHolder.settingTextColor(itemHolder.tv_name,R.color.text_title_color);
            }else {
                itemHolder.settingTextColor(itemHolder.tv_name,R.color.login_text);
            }*/
            paramMap.put(position,!value);
        }else {
            if (aItem){
                paramMap.clear();
            }
            paramMap.put(position,true);
//            itemHolder.settingTextColor(itemHolder.tv_name ,R.color.login_text);
        }
        if (aItem){
            ll_adapter.cleanhe(mporsion);
        }else {
        notifyDataSetChanged();
        }

    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private DeviceChildListAdapter adapter;

        public TextView tv_name;

        public ViewHolder(@NonNull View itemView, DeviceChildListAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            tv_name = itemView.findViewById(R.id.tv_name);
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

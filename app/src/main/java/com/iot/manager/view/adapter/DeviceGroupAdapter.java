package com.iot.manager.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.DeviceGroupResultEntitr;
import com.iot.manager.entity.net.result.hand.Deviceslist;
import com.iot.manager.entity.net.result.hand.HList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.faker.repaymodel.util.SpaceItemDecoration;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/23 0023.
 */
public class DeviceGroupAdapter extends RecyclerView.Adapter<DeviceGroupAdapter.ViewHolder> {

    private List<DeviceGroupResultEntitr> lists = null;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(AdapterView.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public DeviceGroupResultEntitr getItemData(int count) {
        if (lists != null && lists.size() >= count) {
            return lists.get(count);
        }
        return null;
    }

    public void addLists(List<DeviceGroupResultEntitr> lists) {
        if (lists == null) {
            return;
        }
        if (this.lists != null) {
            this.lists.addAll(lists);
        } else {
            this.lists = lists;
        }
        notifyDataSetChanged();
    }

    public void clean() {
        if (lists != null) {
            lists.clear();
        }
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_group, viewGroup, false);
        return new ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {


        final DeviceGroupResultEntitr datas = lists.get(i);
        viewHolder.tv_name.setText(datas.getName());
        final DeviceGroupChildAdapter adapter = new DeviceGroupChildAdapter(datas.getDevicesList());

        viewHolder.rv_item_child.setAdapter(adapter);
    }

    private void onItemHolderClick(RecyclerView.ViewHolder itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private DeviceGroupAdapter adapter;

        public TextView tv_name;
        public RecyclerView rv_item_child;
        public ImageView im_edit;

        public ViewHolder(@NonNull View itemView, DeviceGroupAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
            tv_name = itemView.findViewById(R.id.tv_name);
            im_edit = itemView.findViewById(R.id.im_edit);
            rv_item_child = itemView.findViewById(R.id.rv_item_child);
            rv_item_child.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            rv_item_child.addItemDecoration(new SpaceItemDecoration(10));
            im_edit.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            adapter.onItemHolderClick(this);
        }
    }
}

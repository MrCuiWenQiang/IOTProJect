package com.iot.manager.view.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.hand.Deviceslist;
import com.iot.manager.entity.net.result.hand.HList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cn.faker.repaymodel.util.SpaceItemDecoration;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/23 0023.
 */
public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.ViewHolder> {


    private ArrayList<Deviceslist> oldDatas;
    private Deviceslist oldItem;

    private AdapterView.OnItemClickListener mOnItemClickListener;

    private Map<Integer, Map<Integer, Boolean>> paramMap = new TreeMap<>();

    private List<HList> lists = null;
    private boolean aItem = false;



    public void setaItem(boolean aItem) {
        this.aItem = aItem;
    }
    public HList getItemData(int count) {
        if (lists != null && lists.size() >= count) {
            return lists.get(count);
        }
        return null;
    }

    public void addLists(List<HList> lists) {
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

    public ArrayList<Deviceslist> getOnClickData() {
        ArrayList<Deviceslist> datas = new ArrayList<>();

        for (Integer key : paramMap.keySet()) {
            Map<Integer, Boolean> childMap = paramMap.get(key);
            if (childMap.size() > 0) {
                HList f_d = getItemData(key);
                for (Integer childKey : childMap.keySet()) {
                    boolean value = childMap.get(childKey);
                    if (value) {
                        datas.add(f_d.getDevicesList().get(childKey));
                    }
                }
            }
        }
        return datas;
    }

    public void setOldDatas(ArrayList<Deviceslist> oldDatas) {
        this.oldDatas = oldDatas;
    }

    public void setoldItem(Deviceslist oldItem) {
        this.oldItem = oldItem;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_dev_con, viewGroup, false);
        return new ViewHolder(root, this);
    }

    /**
     * 单选清除其它的选择
     */
    void cleanhe(int i){
 /*       for (Map.Entry<Integer, Map<Integer, Boolean>> s:paramMap.entrySet()){
            int sv= s.getKey().intValue();
            if (sv!=i){
                paramMap.remove(s.getKey());
            }
        }*/
        Iterator<Map.Entry<Integer, Map<Integer, Boolean>>> it = paramMap.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Integer, Map<Integer, Boolean>> entry = it.next();
            int sv= entry.getKey().intValue();
            if (sv!=i){
               it.remove();
            }
        }

//        paramMap.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final HList datas = lists.get(i);
        viewHolder.tv_name.setText(datas.getName());
        final DeviceChildListAdapter adapter = new DeviceChildListAdapter(datas.getDevicesList());

        Map<Integer, Boolean> childParamMap;
        if (paramMap.containsKey(i)) {
            childParamMap = paramMap.get(i);
        } else {
            childParamMap = new TreeMap<>();
            paramMap.put(i, childParamMap);
        }
        adapter.setParamMap(childParamMap);
        adapter.setOldDatas(oldDatas);
        adapter.setPorsion(i);
        adapter.setoldItem(oldItem);
        adapter.setaItem(aItem);
        adapter.setLl_adapter(this);
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

        private DeviceListAdapter adapter;

        public TextView tv_name;
        public RecyclerView rv_item_child;

        public ViewHolder(@NonNull View itemView, DeviceListAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
            tv_name = itemView.findViewById(R.id.tv_name);
            rv_item_child = itemView.findViewById(R.id.rv_item_child);
            rv_item_child.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            rv_item_child.addItemDecoration(new SpaceItemDecoration(10));
        }

        @Override
        public void onClick(View view) {
            adapter.onItemHolderClick(this);
        }
    }
}

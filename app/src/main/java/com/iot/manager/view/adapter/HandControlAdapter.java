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
import com.iot.manager.entity.net.result.hand.Deviceslist;
import com.iot.manager.entity.net.result.hand.HList;

import java.util.List;

import cn.faker.repaymodel.util.SpaceItemDecoration;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/23 0023.
 */
public class HandControlAdapter extends RecyclerView.Adapter<HandControlAdapter.ViewHolder> {

    private onCheckItem onCheckItem;

    private AdapterView.OnItemClickListener mOnItemClickListener;

    private List<HList> lists = null;

    private boolean isShowSetting = false;

    public HList getItemData(int count) {
        if (lists!=null&&lists.size()>count){
            return lists.get(count);
        }
        return null;
    }

    public void addLists(List<HList> lists) {
        if (lists==null){
            return;
        }
        if (this.lists !=null){
            this.lists.addAll(lists);
        }else {
            this.lists = lists;
        }
        notifyDataSetChanged();
    }

    public void clean(){
        if (lists !=null){
            lists.clear();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_hand_con, viewGroup, false);
        return new ViewHolder(root, this);
    }

    /**
     * 设置为设备管理显示模式
     * @param showSetting
     */
    public void setShowSetting(boolean showSetting) {
        isShowSetting = showSetting;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final HList datas = lists.get(i);
        viewHolder.tv_name.setText(datas.getName());

        if (!isShowSetting){
            final HandChildListAdapter adapter =new HandChildListAdapter(datas.getDevicesList());
            adapter.setmOnItemCheckedChangeListener(new HandChildListAdapter.OnItemCheckedChangeListener() {
                @Override
                public void onCheckedChanged(int position, boolean b) {
                    Deviceslist deviceslist = adapter.getDevices(position);
                    if (deviceslist!=null){
                        if (onCheckItem!=null){
                            onCheckItem.onCheckItem(i,position,deviceslist.getId(),b);
                        }
                    }
                }
            });
            viewHolder.rv_item_child.setAdapter(adapter);

        }else {
            final DevManagerChildAdapter adapter = new DevManagerChildAdapter(datas.getDevicesList());
            viewHolder.rv_item_child.setAdapter(adapter);
            adapter.setmOnItemCheckedChangeListener(new DevManagerChildAdapter.OnItemCheckedChangeListener() {
                @Override
                public void onCheckedChanged(int position, boolean b) {
                    Deviceslist deviceslist = adapter.getDevices(position);
                    if (deviceslist!=null){
                        if (onCheckItem!=null){
                            onCheckItem.onCheckItem(i,position,deviceslist.getId(),b);
                        }
                    }
                }

                @Override
                public void onSetting(int position, Deviceslist b) {
                    Deviceslist deviceslist = adapter.getDevices(position);
                    if (deviceslist!=null){
                        onCheckItem.onSetting(i,position,deviceslist,b);
                    }
                }
            });
        }
    }

    private void onItemHolderClick(RecyclerView.ViewHolder itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    @Override
    public int getItemCount() {
        return lists==null?0:lists.size();
    }

    public void setmOnItemClickListener(AdapterView.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public void setOnCheckItem(HandControlAdapter.onCheckItem onCheckItem) {
        this.onCheckItem = onCheckItem;
    }

    public interface onCheckItem{
        void onCheckItem(int position,int childposition,String id,boolean value);
        void onSetting(int position,int childposition, Deviceslist dv,Deviceslist b);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private HandControlAdapter adapter;

        public TextView tv_name;
        public RecyclerView rv_item_child;

        public ViewHolder(@NonNull View itemView, HandControlAdapter adapter) {
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

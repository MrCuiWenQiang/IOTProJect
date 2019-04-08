package com.iot.manager.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.termlist.Deviceslist;
import com.iot.manager.entity.net.result.termlist.Lists;

import java.util.List;

import cn.faker.repaymodel.util.SpaceItemDecoration;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/23 0023.
 */
public class TermControlAdapter extends RecyclerView.Adapter<TermControlAdapter.ViewHolder> {

    private onCheckItem onCheckItem;

    private AdapterView.OnItemClickListener mOnItemClickListener;

    private List<Lists> lists = null;

    public Lists getItemData(int count) {
        if (lists!=null&&lists.size()<=count){
            return lists.get(count);
        }
        return null;
    }

    public void addLists(List<Lists> lists) {
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
        View root = inflater.inflate(R.layout.item_term_con, viewGroup, false);
        return new ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Lists datas = lists.get(i);
        viewHolder.tv_name.setText(datas.getName());
        final TermChildListAdapter adapter =new TermChildListAdapter(datas.getDevicesList());
        viewHolder.rv_item_child.setAdapter(adapter);
        adapter.setmOnItemCheckedChangeListener(new TermChildListAdapter.OnItemCheckedChangeListener() {
            @Override
            public void onCheckedChanged(int position) {
                Deviceslist deviceslist = adapter.getDevices(position);
                if (deviceslist!=null){
                    if (onCheckItem!=null){
                        onCheckItem.onCheckItem(i,position,deviceslist.getId());
                    }
                }
            }
        });

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


    public void setOnCheckItem(TermControlAdapter.onCheckItem onCheckItem) {
        this.onCheckItem = onCheckItem;
    }

    public interface onCheckItem{
        void onCheckItem(int position, int childposition, String id);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TermControlAdapter adapter;

        public TextView tv_name;
        public RecyclerView rv_item_child;

        public ViewHolder(@NonNull View itemView, TermControlAdapter adapter) {
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

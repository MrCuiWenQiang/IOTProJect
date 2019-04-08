package com.iot.manager.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.NexusResultEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/27 0027.
 */
public class NexusAdapter extends RecyclerView.Adapter<NexusAdapter.ViewHolder> {

    private ArrayList<NexusResultEntity> lists;
    private onClickEdit onClickEdit;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_nexus, viewGroup, false);
        return new ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NexusResultEntity data = lists.get(i);
        viewHolder.tv_name.setText(data.getName());
        viewHolder.tv_master.setText(data.getMasterDeviceNames());
        viewHolder.tv_passive.setText(data.getPassiveDeviceNames());
        if (data.getActionState().equals("1")){
            viewHolder.s_status.setText("正向动作");
        }else {
            viewHolder.s_status.setText("反向动作");
        }

    }

    @Override
    public int getItemCount() {
        return lists==null?0:lists.size();
    }

    public NexusResultEntity getItemData(int count) {
        if (lists != null && lists.size() >= count) {
            return lists.get(count);
        }
        return null;
    }

    public void addLists(ArrayList<NexusResultEntity> lists) {
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private NexusAdapter adapter;

        private TextView tv_name;
        private TextView tv_master;
        private TextView tv_passive;
        private TextView s_status;
        private ImageView im_edit;

        public ViewHolder(@NonNull View itemView,NexusAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_master = itemView.findViewById(R.id.tv_master);
            tv_passive = itemView.findViewById(R.id.tv_passive);
            s_status = itemView.findViewById(R.id.s_status);
            im_edit = itemView.findViewById(R.id.im_edit);
            im_edit.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.im_edit:{
                    adapter.onEditClick(this);
                    break;
                }
            }
        }
    }

    private void onEditClick(ViewHolder viewHolder) {
        if (onClickEdit!=null){
            onClickEdit.onEditClick(viewHolder.getAdapterPosition(),getItemData(viewHolder.getAdapterPosition()));
        }
    }

    public void setOnClickEdit(NexusAdapter.onClickEdit onClickEdit) {
        this.onClickEdit = onClickEdit;
    }


    public interface onClickEdit{
        void onEditClick(int position,NexusResultEntity data);
    }
}

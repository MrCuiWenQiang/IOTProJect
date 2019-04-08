package com.iot.manager.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.MessageRequestEntity;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/27 0027.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<MessageRequestEntity> lists;
    private OnClickItem onClickEdit;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_message, viewGroup, false);
        return new ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MessageRequestEntity data = lists.get(i);
        viewHolder.tv_name.setText(data.getSubject());
        viewHolder.tv_msg.setText(data.getContent());
        viewHolder.tv_date.setText(data.getCreateTime());
    }

    @Override
    public int getItemCount() {
        return lists==null?0:lists.size();
    }

    public MessageRequestEntity getItemData(int count) {
        if (lists != null && lists.size() >= count) {
            return lists.get(count);
        }
        return null;
    }

    public void addLists(List<MessageRequestEntity> lists) {
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

        private MessageAdapter adapter;
        private TextView tv_name;
        private TextView tv_date;
        private TextView tv_msg;


        public ViewHolder(@NonNull View itemView,MessageAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_msg = itemView.findViewById(R.id.tv_msg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            adapter.onEditClick(this);
        }
    }

    private void onEditClick(ViewHolder viewHolder) {
        if (onClickEdit!=null){
            onClickEdit.onClick(viewHolder.getAdapterPosition(),getItemData(viewHolder.getAdapterPosition()));
        }
    }

    public void setOnClickEdit(OnClickItem onClickEdit) {
        this.onClickEdit = onClickEdit;
    }


    public interface OnClickItem{
        void onClick(int position, MessageRequestEntity data);
    }
}

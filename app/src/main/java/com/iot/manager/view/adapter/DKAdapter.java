package com.iot.manager.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.DksResultEntity;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/29 0029.
 */
public class DKAdapter extends RecyclerView.Adapter<DKAdapter.ViewHolder>{

    private List<DksResultEntity> datas;
    private OnItemCheckedListener onItemCheckedListener;

    public void clean(){
        if (this.datas != null) {
            this.datas.clear();
        }
    }

    public void addDatas(List<DksResultEntity> datas) {
        if (datas == null) {
            return;
        }
        if (this.datas != null) {
            this.datas.addAll(datas);
        } else {
            this.datas = datas;
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_dk, viewGroup, false);
        return new ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DksResultEntity e = datas.get(i);
        viewHolder.tv_name.setText(e.getName());
    }

    private void seto(ViewHolder viewHolder){
        onItemCheckedListener.onChecked(viewHolder.getAdapterPosition(),datas.get(viewHolder.getAdapterPosition()));
    }

    public void setOnItemCheckedListener(OnItemCheckedListener onItemCheckedListener) {
        this.onItemCheckedListener = onItemCheckedListener;
    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }
    public interface OnItemCheckedListener{
        void onChecked(int position, DksResultEntity b) ;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private DKAdapter adapter;

        private TextView tv_name;
        private ImageView im_edit;
        public ViewHolder(@NonNull View itemView, final DKAdapter adapter) {
            super(itemView);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.im_edit = itemView.findViewById(R.id.im_edit);
            this.adapter = adapter;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.seto(ViewHolder.this);
                }
            });
        }
    }
}

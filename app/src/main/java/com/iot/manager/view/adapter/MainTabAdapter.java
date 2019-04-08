package com.iot.manager.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.entity.view.MainTableBean;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class MainTabAdapter extends RecyclerView.Adapter<MainTabAdapter.ViewHolder> {

    public void setmOnItemClickListener(AdapterView.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private AdapterView.OnItemClickListener mOnItemClickListener;



    private List<MainTableBean> datas;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_mainicon, null, false);
        return new ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MainTableBean data = datas.get(i);
        viewHolder.iv_icon.setImageResource(data.getRountIcon());
        viewHolder.tv_name.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    private void onItemHolderClick(RecyclerView.ViewHolder itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    public void setDatas(List<MainTableBean> datas) {
        this.datas = datas;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private MainTabAdapter adapter;

        public TextView tv_name;
        public ImageView iv_icon;

        public ViewHolder(@NonNull View itemView,MainTabAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            itemView.setOnClickListener(this);

            tv_name = itemView.findViewById(R.id.tv_name);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }

        @Override
        public void onClick(View view) {
            adapter.onItemHolderClick(this);
        }
    }
}

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
import com.iot.manager.entity.net.result.termlist.Deviceslist;

import java.util.List;

import cn.faker.repaymodel.widget.view.button.SwitchButton;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/23 0023.
 */
public class TermChildListAdapter extends RecyclerView.Adapter<TermChildListAdapter.ViewHolder> {

    private OnItemCheckedChangeListener mOnItemCheckedChangeListener;

    private List<Deviceslist> devicesList;

    public TermChildListAdapter(List<Deviceslist> devicesList) {
        this.devicesList = devicesList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_term_child, viewGroup, false);
        return new TermChildListAdapter.ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Deviceslist deviceslist = devicesList.get(i);
        viewHolder.tv_name.setText(deviceslist.getName());

        viewHolder.s_status.setText(deviceslist.getConditionSetState().equals("1")?"【已配置】":"【未配置】");

    }

    @Override
    public int getItemCount() {
        return devicesList==null?0:devicesList.size();
    }

    public void setmOnItemCheckedChangeListener(OnItemCheckedChangeListener mOnItemCheckedChangeListener) {
        this.mOnItemCheckedChangeListener = mOnItemCheckedChangeListener;
    }

    public Deviceslist getDevices(int count) {
        if (devicesList!=null&&count<=devicesList.size()){
            return devicesList.get(count);
        }else {
            return null;
        }
    }

    private void onItemCheckClick(RecyclerView.ViewHolder itemHolder){
        if (mOnItemCheckedChangeListener!=null){
            mOnItemCheckedChangeListener.onCheckedChanged(itemHolder.getAdapterPosition());
        }
    }


    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TermChildListAdapter adapter;

        public TextView tv_name;
        public TextView s_status;
        public ImageView i_setting;

        public ViewHolder(@NonNull View itemView, TermChildListAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            tv_name = itemView.findViewById(R.id.tv_name);
            s_status = itemView.findViewById(R.id.s_status);
            i_setting = itemView.findViewById(R.id.i_setting);

            i_setting.setOnClickListener(this);
        }




        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.i_setting:{
                    adapter.onItemCheckClick(this);
                    break;
                }
            }
        }
    }


    public interface OnItemCheckedChangeListener{
         void onCheckedChanged(int position) ;
    }
}

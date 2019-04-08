package com.iot.manager.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.iot.manager.R;
import com.iot.manager.entity.VideoFileEntity;

import java.io.File;
import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/4/5 0005.
 */
public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.ViewHolder>{

    private List<VideoFileEntity> fileEntities ;
    private OnItemCheckedChangeListener mOnItemCheckedChangeListener;

    public void setmOnItemCheckedChangeListener(OnItemCheckedChangeListener mOnItemCheckedChangeListener) {
        this.mOnItemCheckedChangeListener = mOnItemCheckedChangeListener;
    }

    public List<VideoFileEntity> getFileEntities() {
        return fileEntities;
    }

    public void setFileEntities(List<VideoFileEntity> fileEntities) {
        this.fileEntities = fileEntities;
        notifyDataSetChanged();
    }

    public void clean(){
        if (this.fileEntities != null) {
            this.fileEntities.clear();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.item_file, viewGroup, false);
        return new ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        VideoFileEntity data = fileEntities.get(i);
  /*      if (!data.getPath().equals(viewHolder.im_edit)){
            Glide.with(viewHolder.im_edit.getContext());
                    Glide.with(viewHolder.im_edit.getContext()).clear(holder.imageView);
        }*/
        Glide.with(viewHolder.im_edit.getContext()).load(new File(data.getPath())).into(viewHolder.im_edit);
        viewHolder.im_edit.setTag(data.getPath());
    }

    @Override
    public int getItemCount() {
        return fileEntities==null?0:fileEntities.size();
    }

    private void onItemCheckClick(RecyclerView.ViewHolder itemHolder) {
        if (mOnItemCheckedChangeListener != null) {
            mOnItemCheckedChangeListener.onItemCheck(itemHolder.getAdapterPosition(), fileEntities.get(itemHolder.getAdapterPosition()));
        }
    }
    public interface OnItemCheckedChangeListener{
        void onItemCheck(int position,VideoFileEntity entity);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private FilesAdapter adapter;
        private ImageView im_edit;

        public ViewHolder(@NonNull View itemView,FilesAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            im_edit = itemView.findViewById(R.id.im_edit);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            adapter.onItemCheckClick(this);
        }
    }
}

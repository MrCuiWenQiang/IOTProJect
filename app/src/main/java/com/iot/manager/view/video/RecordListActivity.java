package com.iot.manager.view.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iot.manager.R;
import com.iot.manager.entity.VideoFileEntity;
import com.iot.manager.utils.FileScanUtil;
import com.iot.manager.view.adapter.FilesAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.io.File;
import java.util.List;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.util.SpaceItemDecoration;

/**
 * Function : 录像/截图列表
 * Remarks  :
 * Created by Mr.C on 2019/4/5 0005.
 */
public class RecordListActivity extends BaseToolBarActivity {

    private RecyclerView rv_hands;
    private RefreshLayout mRefreshLayout;
    private FilesAdapter adapter = new FilesAdapter();

    public static final int VOID_TYPE = 0;
    public static final int PHOTO_TYPE = 1;
    public static final String TYPE_KEY = "TYPE_KEY";

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_record;
    }


    @Override
    protected void initContentView() {
        isShowCut(false);
        setToolBarBackgroundColor(R.color.white);

        rv_hands = findViewById(R.id.rv_hands);
        mRefreshLayout = findViewById(R.id.refreshLayout);

        mRefreshLayout.setEnableRefresh(false);//不启用刷新
        mRefreshLayout.setEnableLoadmore(false);//不启用加载
        rv_hands.setLayoutManager(new GridLayoutManager(this, 3));
        rv_hands.setAdapter(adapter);
        rv_hands.addItemDecoration(new SpaceItemDecoration(10));

    }


    @Override
    public void initData(Bundle savedInstanceState) {
        int type = getIntent().getIntExtra(TYPE_KEY, VOID_TYPE);
        showLoading();
        FileScanUtil.FileType t = null;
        if (type == VOID_TYPE) {
            t = FileScanUtil.FileType.VIDEO;
            setLeftTitle("我的录像");
        } else if (type == PHOTO_TYPE) {
            t = FileScanUtil.FileType.PHOTO;
            setLeftTitle("我的抓图");
        }

        FileScanUtil.scan(t, new FileScanUtil.FileScanListener() {
            @Override
            public void onScanList(List<VideoFileEntity> files) {
                adapter.setFileEntities(files);
                dimiss();
            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
        adapter.setmOnItemCheckedChangeListener(new FilesAdapter.OnItemCheckedChangeListener() {
            @Override
            public void onItemCheck(int position, VideoFileEntity entity) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String path = entity.getPath();
                File file = new File(path);
                Uri uri = null;
                String type = null;
                if (entity.getType().equals(".jpg")) {
                    type = "image/*";
                } else if (entity.getType().equals(".mp4")) {
                    type = "video/*";
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    uri= FileProvider.getUriForFile(RecordListActivity.this,"com.iot.manager.fileprovider",file);
                }else {
                    uri= Uri.fromFile(file);
                }
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.setDataAndType(uri, type);
                startActivity(intent);
            }
        });
    }
}

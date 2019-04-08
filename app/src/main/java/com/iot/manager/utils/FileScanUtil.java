package com.iot.manager.utils;

import android.os.Handler;
import android.os.Looper;

import com.iot.manager.entity.VideoFileEntity;
import com.iot.manager.lib.funsdk.support.FunPath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.util.error.ErrorUtil;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/4/6 0006.
 */
public class FileScanUtil {

    public static Handler handler = new Handler(Looper.getMainLooper());


    //搜索文件
    public static void scan(final FileType enumType, final FileScanListener listener) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                String path = null;
                String type = null;
                if (enumType.equals(FileType.VIDEO)) {
                    path = FunPath.getRecordBasePath();
                    type = ".mp4";
                } else if (enumType.equals(FileType.PHOTO)) {
                    path = FunPath.getCatureBasePath();
                    type = ".jpg";
                } else {
                    throw new RuntimeException("FileType can not if");
                }
                final List<VideoFileEntity> failes = new ArrayList<>();
                FileScanUtil util = new FileScanUtil();
                try {
                    ArrayList<File> fs = new ArrayList<>();
                    util.catalog(fs,path,type);
                    if (fs!=null){
                        for (File f :fs) {
                            VideoFileEntity entity = new VideoFileEntity();
                            entity.setName(f.getName());
                            entity.setPath(f.getCanonicalPath());
                            entity.setType(type);
                            failes.add(entity);
                        }
                    }
                } catch (IOException e) {
                    ErrorUtil.showError(e);
                }finally {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (listener!=null){
                                listener.onScanList(failes);
                            }
                        }
                    });

                }

            }
        };
        task.run();
    }



    /**
     * 根据路径筛选路径下所有文件
     * @param type 文件类型
     * @throws IOException
     */
    public  void catalog(ArrayList<File> fs ,String directoryPath,String type) throws IOException {
        File fileDirectory = new File(directoryPath);
//		if (type==null) {
//			throw new RuntimeException("请指定文件类型!");
//		}
        if (directoryPath==null) {
            throw new RuntimeException("请选择路径!");
        }

        if (!fileDirectory.exists()) {
            return;
        }
        if (!fileDirectory.isDirectory()) {
            fs.add(fileDirectory);
            return;
        }

        String[] files = fileDirectory.list();
        if(files==null ||files.length<=0){
            return;
        }
        for(int i=0;i<files.length;i++){
            String fileName = files[i];
            File file = new File(fileDirectory.getPath(),fileName);
            if (file.isDirectory()) {
                catalog(fs,file.getCanonicalPath(),type);
            }else{
                if (type!=null) {
                    String fileType = file.getName().substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
                    if (type.equals(fileType)) {
                        fs.add(file);
                    }
                }else {
                    fs.add(file);
                }

            }
        }
    }



    public enum FileType {
        VIDEO, PHOTO

    }

    public interface FileScanListener {
        void onScanList(List<VideoFileEntity> files);
    }
}

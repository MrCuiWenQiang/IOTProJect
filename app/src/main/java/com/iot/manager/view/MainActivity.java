package com.iot.manager.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.iot.manager.R;
import com.iot.manager.contract.MainContract;
import com.iot.manager.entity.net.result.heweather.Heweather5;
import com.iot.manager.entity.view.MainTableBean;
import com.iot.manager.presenter.MainPresenter;
import com.iot.manager.utils.BaiDuLocalUtils;
import com.iot.manager.view.adapter.MainHeWeatherAdapter;
import com.iot.manager.view.adapter.MainTabAdapter;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.List;

import cn.faker.repaymodel.activity.manager.ActivityManager;
import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.widget.view.AutoPollRecyclerView;

public class MainActivity extends BaseMVPAcivity<MainContract.View, MainPresenter> implements MainContract.View {

    private  final String[] PERMISSIONS_CONTACT = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE} ;

    private  final int REQUEST_CONTACTS = 1000;


    private TextView tv_city;

    private AutoPollRecyclerView vp_heweather;
    private RecyclerView rv_tab;

    private MainHeWeatherAdapter adapter;
    private MainTabAdapter tabAdapter = new MainTabAdapter();

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_main;
    }


    @Override
    protected void initContentView() {
        isShowToolView(false);
        setStatusBar(R.color.heweather_background);
        changStatusIconCollor(true);

        vp_heweather = findViewById(R.id.vp_heweather);
        rv_tab = findViewById(R.id.rv_tab);
        tv_city = findViewById(R.id.tv_city);

        LinearLayoutManager mPagerLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        vp_heweather.setLayoutManager(mPagerLayoutManager);
        PagerSnapHelper mSnapHelper = new PagerSnapHelper();
        mSnapHelper.attachToRecyclerView(vp_heweather);
        adapter = new MainHeWeatherAdapter();
        vp_heweather.setAdapter(adapter);

        GridLayoutManager mTabLayoutManager = new GridLayoutManager(this, 4);
        rv_tab.setLayoutManager(mTabLayoutManager);
        rv_tab.setAdapter(tabAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        tabAdapter.setmOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,MainTabActivity.class);
                intent.putExtra(MainTabActivity.POSITION_KEY,i);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.giveTable();
        /**
         * 权限处理
         */
        if (Build.VERSION.SDK_INT>=23)
        {
            showContacts();
        }else
        {
            startloc();
        }

    }


    @Override
    public void settingTable(List<MainTableBean> datas) {
        tabAdapter.setDatas(datas);
        tabAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if ( adapter.getData()!=null){
            vp_heweather.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        vp_heweather.stop();
    }

    @Override
    public void settingWeatherData(Heweather5 data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
        vp_heweather.start();
    }

    @Override
    public void giveWeatherData_fail(String msg) {
        ToastUtility.showToast(msg);
    }

    private void startloc(){
        BaiDuLocalUtils.init(getApplicationContext(), new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
        /*        double latitude = location.getLatitude();    //获取纬度信息
                double longitude = location.getLongitude();    //获取经度信息*/
                final String city = location.getCity();
                final String district = location.getDistrict();//区县
                if (!TextUtils.isEmpty(city)){
                    BaiDuLocalUtils.stop();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.giveWeatherData(city);
                            tv_city.setText(district==null?city:city+district);
                        }
                    });
                }
            }
        });
    }


    private void showContacts()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestSetPermissions();
        }else
        {
           startloc();
        }
    }

    /**
     * 提示用户给予权限
     */
    private void requestSetPermissions() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE))
        {
            showDialog("程序运行需要定位权限,请允许", new QMUIDialogAction.ActionListener() {
                @Override
                public void onClick(QMUIDialog dialog, int index) {
                    dialog.dismiss();
                    ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
                }
            });

        }else
        {
            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode==REQUEST_CONTACTS){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startloc();
            } else {
                Toast.makeText(getApplicationContext(),"授权不通过",Toast.LENGTH_SHORT).show();
            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityManager.exit(this);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        BaiDuLocalUtils.clean();
    }
}

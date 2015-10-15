package com.liran.baidumap.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LocationUtils.MyLocationInsterface {

    private String TAG = "MainActivity";
    private MapView mapView;
    private BaiduMap baiduMap;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();


    }


    private void initView() {
        mapView = (MapView) findViewById(R.id.mapview);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

    }

    private void initEvent() {
        baiduMap = mapView.getMap();
        floatingActionButton.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                LocationUtils.getInstance(getApplicationContext()).statLoc();
                LocationUtils.getInstance(getApplicationContext()).setMyLocationInsterface(this);
                break;


        }
    }

    @Override
    public void onReceivetion(BDLocation location, String address) {
        Toast.makeText(MainActivity.this, "定位成功  location= " + location.getLatitude() + "  " + location.getLongitude()
                + "  " + " 地址： " + address, Toast.LENGTH_SHORT).show();

//        Log.d(TAG, "onReceivetion() called with: " + "LAT= "+baiduMap.getLocationData().latitude+" LON= "+baiduMap.getLocationData().longitude);
    }
}



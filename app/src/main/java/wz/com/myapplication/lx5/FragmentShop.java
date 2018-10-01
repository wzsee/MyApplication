package wz.com.myapplication.lx5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wz.com.myapplication.R;
import wz.com.myapplication.lx4.MyRecyclerViewAdapter;

/**
 * Created by Administrator on 2018/9/1 0001.
 */

public class FragmentShop extends Fragment {

    private View view ;
    private PopupWindow popupWindow;

    Unbinder mUnbinder;
    @BindView(R.id.bt_shop)
    Button button ;
    @BindView(R.id.ll_shop)
    LinearLayout llShop;

    //获取地图控件引用
    @BindView(R.id.bmapView)
    MapView mMapView;

    BaiduMap mBaiduMap;

    private MyLocationListenner mLocationListenner = new MyLocationListenner();

    /**
     * 定位相关
     */
    BitmapDescriptor customMarker;
//    经纬度
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private MyLocationData mLocationData;
    private LocationClient mLocationClient;

    private int mCurrentDirection;
    /**
     * 是否第一次定位
     */
    private boolean isFirstLoc;
    BitmapDescriptor bdA ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(getContext().getApplicationContext());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        view = inflater.inflate(R.layout.fragment_shop, container,false);
        mUnbinder = ButterKnife.bind(this,view);
        bdA = BitmapDescriptorFactory
                .fromResource(R.drawable.main_waste_gas);
        initMap();
        return view;
    }

    private void initMap() {
        mBaiduMap = mMapView.getMap();
        //设置地图类型
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //自定义定位模式、图标、精确度颜色
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,
                true,bdA
                ));
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //初始化定位
        mLocationClient = new LocationClient(getActivity());
        mLocationClient.registerLocationListener(mLocationListenner);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(20000); // 定位频率
        Toast.makeText(getContext(),option.getAddrType(),Toast.LENGTH_SHORT);
        option.getAddrType();
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    private void guilin() {
        LatLng ll = new LatLng(25.273566,110.290195);
        MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
        mBaiduMap.animateMapStatus(status);
    }

    @OnClick({R.id.bt_shop, R.id.bt_guilin, R.id.bt_mode})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bt_shop :
                initPopupShop();
                break;
            case R.id.bt_guilin:
                guilin();
                break;
            case R.id.bt_mode:
                mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration
                        (MyLocationConfiguration.LocationMode.COMPASS, false, bdA));
                break;
        }
    }

    private void initPopupShop(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popup_shop, null);
        RecyclerView recyclerView =(RecyclerView) view.findViewById(R.id.rv_shop);
        LinearLayoutManager layoutManager =new LinearLayoutManager(getContext());
        String[] shops = {"京东","天猫"};
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(Arrays.asList(shops));
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        popupWindow = new PopupWindow(llShop, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(button);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    public class MyLocationListenner implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation location) {
            // 销毁后不在处理新接收的位置
            if(location == null || mMapView == null){
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mLocationData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(mLocationData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(25.273566,110.290195);
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(status);
                Toast.makeText(getContext(),location.getAddrStr(),Toast.LENGTH_SHORT);
            }
        }
    }

}

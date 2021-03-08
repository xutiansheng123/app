package com.ta.xutiansheng.xtsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.adapter.PoiResultAdapter;
import com.ta.xutiansheng.xtsapp.address.AddressContact;
import com.ta.xutiansheng.xtsapp.address.AddressPresent;
import com.ta.xutiansheng.xtsapp.bean.AddressResult;
import com.ta.xutiansheng.xtsapp.bean.BaiduResult;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.util.MyLocationListener;
import com.ta.xutiansheng.xtsapp.util.ShardPrenUtil;
import com.ta.xutiansheng.xtsapp.util.ToastUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MapActivity extends BaseMvpActivity<AddressPresent> implements AddressContact.AddressView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.top)
    ConstraintLayout top;
    @BindView(R.id.edt_seach)
    EditText edtSeach;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.con_seach)
    ConstraintLayout conSeach;
    @BindView(R.id.recycler_address)
    RecyclerView recyclerAddress;
    @BindView(R.id.baidumap)
    MapView baidumapView;
    @BindView(R.id.btnlocation)
    FloatingActionButton btnlocation;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private BaiduMap baiduMap;
    private LocationClient locationClient;
    PoiSearch poiSearch;
    private List<BaiduResult> list;
    private PoiResultAdapter adapter;
    Logger logger = LoggerFactory.getLogger(MapActivity.class);
    private Map<String, Object> map = new HashMap<>();
    int pageNum = 0;
    int pageSize = 20;
    private static final int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baiduMap = baidumapView.getMap();
        list = new ArrayList<>();
        adapter = new PoiResultAdapter(this, list);
        recyclerAddress.setAdapter(adapter);
        recyclerAddress.setLayoutManager(new LinearLayoutManager(this));
        poiSearch = PoiSearch.newInstance();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        baiduMap.setMyLocationEnabled(true);
        mPresenter.getSeachAddressList(map);
        //设置poi检索监听器
        poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {

            @Override
            public void onGetPoiResult(PoiResult poiResult) {
//                list.clear();
//                list.addAll(poiResult.getAllAddr());
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        });
    }

    @Override
    protected void onResume() {
        baidumapView.onResume();
        super.onResume();
        ivBack.setOnClickListener(view1 -> {
            finish();
        });
        seachForKeyWord("");
        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 0;
                map.put("page_num", pageNum);
                seachForKeyWord(edtSeach.getText().toString());
            }
        });
        refreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                map.put("page_num", pageNum);
                seachForKeyWord(edtSeach.getText().toString());
            }
        });
        edtSeach.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    pageNum = 0;
                    map.put("page_num", pageNum);
                    hideKeyInput();
                    seachForKeyWord(edtSeach.getText().toString());
                }
                return true;
            }
        });
        adapter.setOnItemClickListener(position -> {
            BaiduResult baiduResult = list.get(position);
            Intent intent = new Intent(MapActivity.this, CreateAddressActivity.class);
            intent.putExtra("bean", baiduResult);
            setResult(REQUEST_CODE, intent);
            finish();
        });
        locationClient = new LocationClient(this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(200);
        //设置locationClientOption
        locationClient.setLocOption(option);
        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener(baiduMap);
        locationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        locationClient.start();
        baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, false, BitmapDescriptorFactory.fromResource(R.mipmap.leo)));

    }


    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected AddressPresent createPresenter() {
        return new AddressPresent();
    }

    @Override
    protected void onPause() {
        baidumapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        baidumapView.onDestroy();
        poiSearch.destroy();
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    protected void initView() {

    }

    private void seachForKeyWord(String keyword) {
        String lat = (String) ShardPrenUtil.getIntance(MapActivity.this).GetParam(ShardPrenUtil.LATITUDE);
        String lon = (String) ShardPrenUtil.getIntance(MapActivity.this).GetParam(ShardPrenUtil.ALTITUDE);
        map.put("location", lat + "," + lon);
        map.put("radius", "6000");
        map.put("page_size", pageSize);
        map.put("page_num", pageNum);
        if (!keyword.equals("")) {
            map.put("query", keyword);
        } else {
            map.put("query", "住宅,公寓,酒店,公司");

        }
        logger.debug(new Gson().toJson(map));
        mPresenter.getSeachAddressList(map);
    }

    @Override
    public void onCreateAddressSuccess(String msg) {

    }

    @Override
    public void onDeleteAddressSuccess(String msg) {

    }

    @Override
    public void onGetAddressList(List<AddressResult> list) {
    }

    @Override
    public void onFaulire(String msg) {
        refreshlayout.finishLoadMore();
        refreshlayout.finishRefresh();
        ToastUtil.MakeToast(this, msg);
    }

    @Override
    public void getSeachAddressList(List<BaiduResult> list) {
        if (pageNum == 0) {
            this.list.clear();
        }
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
        refreshlayout.finishLoadMore();
        refreshlayout.finishRefresh();
    }
}
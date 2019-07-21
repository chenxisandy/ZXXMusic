package com.example.zxxmusic.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zxxmusic.R;
import com.example.zxxmusic.base.BaseActivity;
import com.example.zxxmusic.bean.MusicCollection;
import com.example.zxxmusic.bottomLayout.BottomLayoutFragment;
import com.example.zxxmusic.internetSearch.InternetSearchActivity;
import com.example.zxxmusic.list.ListActivity;
import com.example.zxxmusic.util.ActivityUtil;
import com.example.zxxmusic.util.ValueUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainContract.IView,
        MainPresenter> implements MainContract.IView, View.OnClickListener
        ,NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;

    private CollectionAdapter adapter;

    private static final int DOUBLE_CLICK_TIME_SUB = 1000;

    private long PreBackTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomLayoutFragment bottomLayoutFragment = new BottomLayoutFragment();
        bottomLayoutFragment.setType(ValueUtil.MAIN_UI);
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),
                bottomLayoutFragment, R.id.bottom_layout);

        initView();
        presenter.initListData();

    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.tool_bar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //other
        LinearLayout localList = findViewById(R.id.main_local_music);
        LinearLayout recentList = findViewById(R.id.main_last_music);
        LinearLayout downloadList = findViewById(R.id.main_download_list);
        LinearLayout starList = findViewById(R.id.main_star_list);
        localList.setOnClickListener(this);
        recentList.setOnClickListener(this);
        downloadList.setOnClickListener(this);
        starList.setOnClickListener(this);
    }

    private boolean checkPermissionsOK() {
        // 创建一个权限列表，把需要使用而没用授权的的权限存放在这里
        List<String> permissionList = new ArrayList<>();

        // 判断权限是否已经授予，没有就把该权限添加到列表中
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.CAMERA);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WAKE_LOCK);
        }

        // 如果列表为空，就是全部权限都获取了，不用再次获取了。不为空就去申请权限
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    permissionList.toArray(new String[permissionList.size()]), ValueUtil.REQUEST_PERMISSION);
            return false;
        } else {
            return true;
        }
    }

    //as BaseActivity
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    //as View
    @Override
    public void setRecyclerList(List<MusicCollection> list) {
        RecyclerView recyclerView = findViewById(R.id.collection_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CollectionAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void notifyList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_setting:
                // TODO: 2019/7/20
                break;
            case R.id.nav_alarm:
                // TODO: 2019/7/20
                break;
            case R.id.nav_other:
                // TODO: 2019/7/20
                break;
                default:
                    mDrawerLayout.closeDrawers();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                if (checkPermissionsOK()) {
                    ActivityUtil.GoActivity(this, InternetSearchActivity.class);
                } else {
                    Toast.makeText(this, "同意权限后再点击", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
        return true;
    }

    @Override
    public void onBackPressed() {   //双击退出应用
        if (System.currentTimeMillis() - PreBackTime < DOUBLE_CLICK_TIME_SUB){
            super.onBackPressed();
        }else {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            PreBackTime = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ValueUtil.REQUEST_PERMISSION:
                // 请求码对应的是申请多个权限
                if (grantResults.length > 0) {
                    // 因为是多个权限，所以需要一个循环获取每个权限的获取情况
                    for (int i = 0; i < grantResults.length; i++) {
                        // PERMISSION_DENIED 这个值代表是没有授权，我们可以把被拒绝授权的权限显示出来
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                            Toast.makeText(MainActivity.this, permissions[i] + "权限被拒绝了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_local_music:
                intentToList(ValueUtil.LOCAL_LIST);
                break;
            case R.id.main_last_music:
                intentToList(ValueUtil.RECENT_LIST);
                break;
            case R.id.main_download_list:
                intentToList(ValueUtil.DOWNLOAD_LIST);
                break;
            case R.id.main_star_list:
                intentToList(ValueUtil.STAR_LIST);
        }
    }

    private void intentToList(int type) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(ValueUtil.LIST_TYPE, type);
        startActivity(intent);
    }
}

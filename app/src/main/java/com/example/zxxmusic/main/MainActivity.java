package com.example.zxxmusic.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.zxxmusic.R;
import com.example.zxxmusic.base.BaseActivity;
import com.example.zxxmusic.bean.MusicCollection;
import com.example.zxxmusic.bottomLayout.BottomLayoutFragment;
import com.example.zxxmusic.internetSearch.InternetSearchActivity;
import com.example.zxxmusic.util.ActivityUtil;
import com.example.zxxmusic.util.ValueUtil;

import java.util.List;

public class MainActivity extends BaseActivity<MainContract.IView,
        MainPresenter> implements MainContract.IView, NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout mDrawerLayout;

    CollectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomLayoutFragment bottomLayoutFragment = new BottomLayoutFragment();
        bottomLayoutFragment.setType(ValueUtil.MAIN_UI);
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),
                bottomLayoutFragment, R.id.bottom_layout);

        presenter.initListData();

    }

    private void initDrawer() {
        Toolbar toolbar = findViewById(R.id.tool_bar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
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
                ActivityUtil.GoActivity(this, InternetSearchActivity.class);
                break;
            default:
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}

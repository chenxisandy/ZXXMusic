package com.example.zxxmusic.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zxxmusic.R;
import com.example.zxxmusic.base.BaseActivity;

public class ListActivity extends BaseActivity<ListContract.IView,
        ListPresenter> implements ListContract.IView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    protected ListPresenter createPresenter() {
        return new ListPresenter();
    }
}

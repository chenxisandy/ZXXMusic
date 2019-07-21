package com.example.zxxmusic.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zxxmusic.R;
import com.example.zxxmusic.base.BaseActivity;
import com.example.zxxmusic.util.ValueUtil;

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

    @Override
    public int getIndexFromIntent() {
        Intent intent = getIntent();    //-1则代表只是从主界面传过来
        return intent.getIntExtra(ValueUtil.INDEX_OF_LIST, -1);
    }
}

package com.example.zxxmusic.collection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zxxmusic.R;
import com.example.zxxmusic.base.BaseActivity;

public class CollectionActivity extends BaseActivity<CollectionContract.IView,
        CollectionPresenter> implements CollectionContract.IView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
    }

    @Override
    protected CollectionPresenter createPresenter() {
        return new CollectionPresenter();
    }
}

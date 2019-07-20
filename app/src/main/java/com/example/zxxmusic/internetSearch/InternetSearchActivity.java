package com.example.zxxmusic.internetSearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zxxmusic.R;
import com.example.zxxmusic.base.BaseActivity;

public class InternetSearchActivity extends BaseActivity<InternetSearchContract.IVew, InternetSearchPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_search);
    }

    @Override
    protected InternetSearchPresenter createPresenter() {
        return new InternetSearchPresenter();
    }
}

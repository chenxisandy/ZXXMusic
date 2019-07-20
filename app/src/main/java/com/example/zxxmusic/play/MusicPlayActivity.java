package com.example.zxxmusic.play;

import android.os.Bundle;

import com.example.zxxmusic.R;
import com.example.zxxmusic.base.BaseActivity;
import com.example.zxxmusic.base.BasePresenter;

public class MusicPlayActivity extends BaseActivity<MusicPlayContract.IView,
        MusicPlayPresenter> implements MusicPlayContract.IView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    @Override
    protected MusicPlayPresenter createPresenter() {
        return new MusicPlayPresenter();
    }
}

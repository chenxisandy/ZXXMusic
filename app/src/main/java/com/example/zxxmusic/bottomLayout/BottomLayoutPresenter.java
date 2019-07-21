package com.example.zxxmusic.bottomLayout;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.zxxmusic.base.BasePresenter;
import com.example.zxxmusic.bean.Music;
import com.example.zxxmusic.musicService.MusicPlayerListener;
import com.example.zxxmusic.musicService.MusicService;
import com.example.zxxmusic.play.MusicPlayActivity;

public class BottomLayoutPresenter extends BasePresenter<BottomLayoutContract.IView>
        implements BottomLayoutContract.IPresenter, MusicPlayerListener {

    private Context mContext;

    private MusicService.MusicPlayer musicPlayer;

    private int type;

    public BottomLayoutPresenter(Context mContext, MusicService.MusicPlayer musicPlayer, int type) {
        this.mContext = mContext;
        this.musicPlayer = musicPlayer;
        this.type = type;
        musicPlayer.addListener(this);
    }

    @Override
    public void detachView() {
        super.detachView();
        musicPlayer.removeListener(this);
    }

    //listener
    @Override
    public void onMusicPlay() {
        mViewRef.get().play();
    }

    @Override
    public void onMusicPause() {
        mViewRef.get().pause();
    }

    @Override
    public void onMusicFinish() {
        Music music = musicPlayer.getMusic();
        BottomLayoutContract.IView fragView = mViewRef.get();
    }

    @Override
    public void playOrPause() {
        if (musicPlayer.isPlay()) {
            musicPlayer.PauseMusic();
            mViewRef.get().pause();
        } else {
            musicPlayer.playMusic();
            mViewRef.get().play();
        }
    }

    @Override
    public void openListDialog() {
        Toast.makeText(mContext, "暂时还没有该功能", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openMusicPlay() {
        Intent intent = new Intent(mContext, MusicPlayActivity.class);
        //from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}

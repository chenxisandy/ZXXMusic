package com.example.zxxmusic.musicService;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.example.zxxmusic.bean.Music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.zxxmusic.util.ValueUtil.*;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;

    private NotificationManager notificationManager;

    private MusicPlayer musicPlayer;

    public MusicService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        musicPlayer = new MusicPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();  //记得释放
    }

    @Override
    public IBinder onBind(Intent intent) {
        return musicPlayer;
    }

    public class MusicPlayer extends Binder {

        private List<MusicPlayerListener> listenerList = new ArrayList<>();

        private List<Music> musicList;

        //params
        private int playStyle = LIST_LOOP;

        private int id = 0;

        private Music CurrentMusic;

        private MusicPlayer() {
            mediaPlayer = new MediaPlayer();
        }

        public void playMusic() {
            if (mediaPlayer.isPlaying())
                return;
            mediaPlayer.start();
        }

        public void PauseMusic() {
            if (!mediaPlayer.isPlaying())
                return;
            mediaPlayer.pause();
        }

        public boolean isPlay() {
            return mediaPlayer.isPlaying();
        }

        public int getCurrentProgress() {
            return mediaPlayer.getCurrentPosition();
        }

        public int getDuration() {
            return mediaPlayer.getDuration();
        }

        public void setPlayStyle(int style) {
            playStyle = style;
        }

        public void addListener(MusicPlayerListener listener) {
            listenerList.add(listener);
        }

        public void removeListener(MusicPlayerListener musicPlayerListener) {
            listenerList.remove(musicPlayerListener);
        }

        public void seekTo(int progress) {
            mediaPlayer.seekTo(progress);
        }

        public void playNext() {
            if (playStyle == LIST_LOOP) {
                id ++;
                if (id >= musicList.size())
                    id = 0;
            } else if (playStyle == LIST_SHUFFLE) {
                id = (int) (Math.random() * musicList.size() + 0.5) - 1;
            }
            playMusic();
        }

        public void playLast() {
            if (playStyle == LIST_LOOP) {
                id--;
                if (id < 0) id = musicList.size() - 1;
            } else if (playStyle == LIST_SHUFFLE)
                id = (int) (Math.random() * musicList.size() + 0.5) - 1;
            playMusic();
        }

        public Music getMusic() {
            return musicList.get(id);
        }

        public void sendUnlockNotification() {}

        private void PlayNewMusic() {
            mediaPlayer.reset();
            initMediaPlayer();
            mediaPlayer.start();
            for (MusicPlayerListener listener :
                    listenerList) {
                if (listener != null) {
                    listener.onMusicFinish();// TODO: 2019/7/18
                }
            }

            // TODO: 2019/7/18 to add notification
        }

        private void sentCustomNotification() {}

        private void cancelNotification() {}

        private void initMediaPlayer() {
            try {
                mediaPlayer.setDataSource(musicList.get(id).getPath());
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public List<Music> getMusicList() {
            return musicList;
        }

        public void setMusicList(List<Music> musicList) {
            this.musicList = musicList;
        }

        public void setId(int ID) {
            id = ID;
        }

        public int getId() {
            return id;
        }
    }

}

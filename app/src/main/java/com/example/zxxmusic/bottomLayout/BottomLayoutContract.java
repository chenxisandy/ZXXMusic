package com.example.zxxmusic.bottomLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public interface BottomLayoutContract {
    interface IView {
        void setTitle(String title);

        void setArtist(String artist);

        CircleImageView getTitleImv();

        void play();

        void pause();

    }

    interface IPresenter {
        void playOrPause();

        void openListDialog();

        void openMusicPlay();
    }
}

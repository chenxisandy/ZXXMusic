package com.example.zxxmusic.main;

import com.example.zxxmusic.bean.MusicCollection;

import java.util.List;

public interface MainContract {

    interface IView{
        void setRecyclerList(List<MusicCollection> list);

        void notifyList();
    }

    interface IPresenter{
        void initListData();
    }
}

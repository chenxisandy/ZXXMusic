package com.example.zxxmusic.model;

import com.example.zxxmusic.bean.CollectionFace;
import com.example.zxxmusic.bean.MusicCollection;
import com.example.zxxmusic.bean.Music;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class DataRepo {

    private List<Music> wholeMusicList = new ArrayList<>();

    private List<MusicCollection> collectionList = new ArrayList<>();

    private List<Music> recentList = new ArrayList<>();

    private List<Music> localList = new ArrayList<>();

    private static class RepoHolder {
        private static final DataRepo INSTANCE = new DataRepo();
    }

    private DataRepo() {
    }

    public static final DataRepo getInstance() {
        return RepoHolder.INSTANCE;
    }

    //public

    //repo for main
    public void initData() {
        LitePal.getDatabase();
        wholeMusicList.clear();
        wholeMusicList.addAll(LitePal.findAll(Music.class));
        initCollectionList();
        initRecentAndLocalList();
    }

    //get list
    public List<MusicCollection> getCollectionList() {
        return collectionList;
    }

    public List<Music> getRecentList() {
        return recentList;
    }

    public List<Music> getLocalList() {
        return localList;
    }


    //private

    //init
    private void initCollectionList() {
        for (Music music:
             wholeMusicList) {
            for (CollectionFace collectionFace :
                    music.getCollectionFaceList()) {
                boolean hasExist = false;
                for (MusicCollection collection :
                        collectionList) {
                    if (collectionFace.equals(collection.getCollectionFace())) {
                        hasExist = true;
                        collection.getMusicList().add(music);
                    }
                }
                if (!hasExist) {
                    //如果不存在现有的collection就新建一个
                    MusicCollection newCollection = new MusicCollection();
                    newCollection.setCollectionFace(collectionFace);
                    newCollection.getMusicList().add(music);
                }
            }
        }
    }

    private void initRecentAndLocalList() {
        for (Music m :
                wholeMusicList) {
            if (m.isRecent()) {
                recentList.add(m);
            }
            if (m.isDownloaded()) {
                localList.add(m);
            }
        }
    }



}

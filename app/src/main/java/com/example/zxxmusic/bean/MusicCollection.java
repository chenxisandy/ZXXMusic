package com.example.zxxmusic.bean;

import java.util.ArrayList;
import java.util.List;

public class MusicCollection {

    private CollectionFace collectionFace;

    public MusicCollection() {
        musicList = new ArrayList<>();
    }

    private List<Music> musicList;

    public CollectionFace getCollectionFace() {
        return collectionFace;
    }

    public void setCollectionFace(CollectionFace collectionFace) {
        this.collectionFace = collectionFace;
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }
}

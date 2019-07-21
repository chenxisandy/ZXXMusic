package com.example.zxxmusic.bean;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Music extends LitePalSupport {

    //被收藏的列表的表层
    private List<CollectionFace> collectionFaceList;

    private int id;

    private String path;

    private String PicPath;

    private String title;

    private String artist;

    public Music() {
        collectionFaceList = new ArrayList<>();
    }

    private boolean isDownloaded;

    private boolean isRecent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPicPath() {
        return PicPath;
    }

    public void setPicPath(String picPath) {
        PicPath = picPath;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public List<CollectionFace> getCollectionFaceList() {
        return collectionFaceList;
    }

    public void setCollectionFaceList(List<CollectionFace> collectionFaceList) {
        this.collectionFaceList = collectionFaceList;
    }

    public boolean isRecent() {
        return isRecent;
    }

    public void setRecent(boolean recent) {
        isRecent = recent;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

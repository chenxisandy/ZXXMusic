package com.example.zxxmusic.bean;

public class CollectionFace {   //歌单列表封面
    private String title;

    private String id;

    private boolean isOnline;   //是否网上的歌单

    private String imgPath;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public boolean equals(Object obj) { //重写equal
        return this == ((CollectionFace)obj)
                || (this.id.equals(((CollectionFace) obj).getId())
                && this.title.equals(((CollectionFace) obj).title)
                && this.isOnline == ((CollectionFace) obj).isOnline()
                && this.imgPath.equals(((CollectionFace) obj).getImgPath()));
    }
}

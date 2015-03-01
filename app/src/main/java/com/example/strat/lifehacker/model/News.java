package com.example.strat.lifehacker.model;

import android.graphics.Bitmap;

/**
 * Created by strat on 22.02.15.
 */
public class News {
    private String title;
    private String imgUrl;
    private String text;
    private int id;
    private Bitmap img;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Bitmap getImg() {
        return img;
    }
}


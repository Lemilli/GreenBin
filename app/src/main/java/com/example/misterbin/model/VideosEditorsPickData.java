package com.example.misterbin.model;

import android.content.Intent;

public class VideosEditorsPickData {
    String videoName;
    String videoDescription;
    Integer imageUrl;
    Integer choices;

    public Integer getImageUrl() {
        return imageUrl;
    }
    public Integer getImagePosition(){return choices;}

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public VideosEditorsPickData(String videoName, String videoDescription, Integer imageUrl, Integer choices)
    {
        this.videoName = videoName;
        this.videoDescription = videoDescription;
        this.imageUrl = imageUrl;
        this.choices = choices;
    }

    public String getVideoName()
    {
        return videoName;
    }

    public void setVideoName(String videoName)
    {
        this.videoName = videoName;
    }

    public String getVideoDescription()
    {
        return videoDescription;
    }


    public void setVideoDescription (String videoDescription)
    {
        this.videoDescription = videoDescription;
    }
}

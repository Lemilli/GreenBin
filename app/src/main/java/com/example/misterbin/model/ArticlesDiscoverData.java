package com.example.misterbin.model;

import android.content.Intent;

public class ArticlesDiscoverData {
    String articleName;
    String authorName;
    Integer imageUrl;
    Integer choices;

    public Integer getImageUrl() {
        return imageUrl;
    }
    public Integer getImagePosition(){return choices;}

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArticlesDiscoverData(String articleName, String authorName, Integer imageUrl, Integer choices)
    {
        this.articleName = articleName;
        this.authorName = authorName;
        this.imageUrl = imageUrl;
        this.choices = choices;
    }

    public String getArticleName()
    {
        return articleName;
    }

    public void setArticleName(String articleName)
    {
        this.articleName = articleName;
    }

    public String getAuthorName()
    {
        return authorName;
    }

    public void setAuthorName (String authorName)
    {
        this.authorName = authorName;
    }
}

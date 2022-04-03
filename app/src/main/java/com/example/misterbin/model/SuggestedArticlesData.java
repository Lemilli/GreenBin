package com.example.misterbin.model;

import android.content.Intent;

public class SuggestedArticlesData {
    String articleName;
    String authorName;
    Integer imageUrl;

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SuggestedArticlesData(String articleName, String authorName, Integer imageUrl)
    {
        this.articleName = articleName;
        this.authorName = authorName;
        this.imageUrl = imageUrl;
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

package com.example.misterbin.model;

import android.content.Intent;

public class LocationData {
    String locationName;
    String stateName;
    String distanceName;
    Integer imageUrl;

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocationData(String locationName, String stateName, String distanceName, Integer imageUrl)
    {
        this.locationName = locationName;
        this.stateName = stateName;
        this.distanceName = distanceName;
        this.imageUrl = imageUrl;
    }

    public String getLocationName()
    {
        return locationName;
    }

    public void setLocationName(String locationName)
    {
        this.locationName = locationName;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void getStateName (String authorName)
    {
        this.stateName = stateName;
    }

    public String getDistanceName()
    {
        return distanceName;
    }

    public void getDistanceName (String distanceName)
    {
        this.distanceName = distanceName;
    }
}

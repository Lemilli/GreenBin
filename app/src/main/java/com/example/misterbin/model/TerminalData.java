package com.example.misterbin.model;

// Data class for each Terminal we get from rest API
public class TerminalData {
    public double lat, lng;
    public String name, street;
    public int fullness;
    public boolean isAvailable;
    public String image_url;

    // Class Constructor
    public TerminalData(double latitude, double longitude, String terminal_name, String street_name,
                        int how_full, boolean availability, String img_url) {
        lat = latitude;
        lng = longitude;
        name = terminal_name;
        fullness = how_full;
        street = street_name;
        isAvailable = availability;
        image_url = img_url;
    }
}

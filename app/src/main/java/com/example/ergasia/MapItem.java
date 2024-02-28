package com.example.ergasia;

import org.osmdroid.util.GeoPoint;

public class MapItem {
    private String title;
    private GeoPoint geoPoint;
    private int pinResourceID;
    private int imageResourceID;

    public MapItem(String title, GeoPoint geopoint, int pinResourceID, int imageResourceID){
        this.title = title;
        this.geoPoint = geopoint;
        this.pinResourceID = pinResourceID;
        this.imageResourceID = imageResourceID;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public int getPinResourceID() {
        return pinResourceID;
    }

    public void setPinResourceID(int pinResourceID) {
        this.pinResourceID = pinResourceID;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public void setImageResourceID(int imageResourceID) {
        this.imageResourceID = imageResourceID;
    }
}


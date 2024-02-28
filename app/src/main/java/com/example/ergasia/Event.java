package com.example.ergasia;

public class Event {

    private String title;
    private String date;
    private String type;
    private String time;
    private String location;
    private String details;
    private String disclaimer;
    private int image;
    private int icon;

    private boolean shortlist;
    private static int index = 1000;
    private int ID;

    public Event(String title, String date, String type, String time, String location, String details, String disclaimer, int image, int icon) {
        this.title = title;
        this.date = date;
        this.type = type;
        this.time = time;
        this.location = location;
        this.details = details;
        this.disclaimer = disclaimer;
        this.image = image;
        this.icon = icon;
        this.ID = ID;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isShortlist() {
        return shortlist;
    }

    public void setShortlist(boolean shortlist) {
        this.shortlist = shortlist;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

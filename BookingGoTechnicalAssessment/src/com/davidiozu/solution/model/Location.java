package com.davidiozu.solution.model;

public class Location {

    private String latitude;
    private String longitude;
    
    public Location(String latitude, String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public String getLatitude(){
        return latitude;
    }
    
    public String getLongitude(){
        return longitude;
    }
    
    @Override
    public String toString(){
        return latitude + ", " + longitude;
    }
    
}

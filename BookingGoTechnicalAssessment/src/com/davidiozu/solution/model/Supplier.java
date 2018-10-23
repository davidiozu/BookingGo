package com.davidiozu.solution.model;

public class Supplier {

    public static Supplier dave = new Supplier("Dave's Taxis", "https://techtest.rideways.com/dave");
    public static Supplier eric = new Supplier("Eric's Taxis", "https://techtest.rideways.com/eric");
    public static Supplier jeff = new Supplier("Jeff's Taxis", "https://techtest.rideways.com/jeff");
    
    private String name;
    private String url;
    
    /**
     * Private constructor as we have only 3 suppliers.
     * Make this public if you want to create other suppliers than the already known ones.
     * For the purpose of this exercise, I will leave it private. 
     * @param name
     * @param url
     */
    private Supplier(String name, String url) {
        this.name = name;
        this.url = url;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getUrl(){
        return this.url;
    }
    
    
    
}

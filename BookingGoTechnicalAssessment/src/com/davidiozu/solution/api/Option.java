package com.davidiozu.solution.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class for Json data encapsulation.
 * @author david
 *
 */
public class Option {

    @SerializedName("car_type")
    @Expose
    private String carCategory;
    
    @SerializedName("price")
    @Expose
    private long price;

    public String getCarCategory(){
        return carCategory;
    }
    
    public void setCarCategory(String c){
        this.carCategory = c;
    }
    
    public long getPrice(){
        return price;
    }
    
    public void setPrice(long p){
        this.price = p;
    }
    
    
}

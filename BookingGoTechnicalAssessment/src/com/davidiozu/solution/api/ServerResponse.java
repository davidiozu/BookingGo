package com.davidiozu.solution.api;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class for Json data encapsulation.
 * @author david
 *
 */
public class ServerResponse {

    @SerializedName("supplier_id")
    @Expose
    private String supplierID;

    @SerializedName("pickup")
    @Expose
    private String pickup;

    @SerializedName("dropoff")
    @Expose
    private String dropoff;
    
    @SerializedName("options")
    @Expose
    private ArrayList<Option> options = null;

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDropoff() {
        return dropoff;
    }

    public void setDropoff(String dropoff) {
        this.dropoff = dropoff;
    }
    
    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }
    
}

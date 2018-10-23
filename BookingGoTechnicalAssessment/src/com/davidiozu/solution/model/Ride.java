package com.davidiozu.solution.model;

import com.davidiozu.solution.util.Utils;

public class Ride {

    private CarCategory selectedCar;
    private long price;
    private int maxNoOfPassengers;
    private Supplier supplier;
    
    public Ride(CarCategory car, long price, Supplier supplier){
        this.selectedCar = car;
        this.price = price;
        this.maxNoOfPassengers = Utils.getMaxNoOfPassengers(car);
        this.supplier = supplier;
    }
    
    public CarCategory getCar(){
        return this.selectedCar;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public int getMaxNoOfPassengers(){
        return maxNoOfPassengers;
    }
    
    public String getSupplier(){
        return supplier.getName();
    }
    
}

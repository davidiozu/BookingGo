package com.davidiozu.solution.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.davidiozu.solution.model.CarCategory;
import com.davidiozu.solution.model.Ride;
import com.davidiozu.solution.util.Utils;

public class SearchResults {

    private ArrayList<Ride> response;
    private int noOfPassengers;
    
    public SearchResults(int passengers){
        this.response = new ArrayList<>();
        this.noOfPassengers = passengers;
    }
    
    public void addRide(Ride r){
        response.add(r);
    }
    
    /**
     * Print only Dave's options for ride.
     * Rides are ordered in a descendent order.
     * Rides with not enough space for passengers are removed.
     */
    public ArrayList<Ride> getDaveResults(){
        
        if(this.noOfPassengers != Utils.UNSPECIFIED_NO_OF_PASSENGERS){
            response.removeIf(s -> s.getMaxNoOfPassengers()<this.noOfPassengers);
        }
        
        if(response.size()==0){
            System.out.println("Unfortunately, we couldn't find a ride for you!\nTry again later!");
            System.exit(0);
        }
        
        System.out.println("Rides found for you: ");
        
        Collections.sort(response, new Comparator<Ride>() {
            
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return (ride1.getPrice() < ride2.getPrice() ? 1 : ride1.getPrice() > ride2.getPrice() ? -1 : 0);     
            }
        });
        
        return response;
    }
    
    public void printDaveResults(){
        for(Ride r : response){
            System.out.println(r.getCar() + " - " + r.getPrice());
        }
    }
    
    /**
     * Print rides from all suppliers.
     * Uses {@link SearchResults#getCheapestRides()} to choose the cheapest rides.
     * Rides are ordered in a descending order.
     * Rides with not enough space for passengers are removed.
     */
    public ArrayList<Ride> getAllResults(){
        
        if(this.noOfPassengers != Utils.UNSPECIFIED_NO_OF_PASSENGERS){
            response.removeIf(s -> s.getMaxNoOfPassengers()<this.noOfPassengers);
        }
        
        if(response.size()==0){
            System.out.println("Unfortunately, we couldn't find a ride for you!\nTry again later!");
            System.exit(0);
        }
        
        ArrayList<Ride> cheapestRides = this.getCheapestRides();
        
        if(cheapestRides.size()==0){
            System.out.println("Unfortunately, we couldn't find a ride for you!\nTry again later!");
            System.exit(0);
        }
        
        System.out.println("Rides found for you: ");

        Collections.sort(cheapestRides, new Comparator<Ride>() {
            
            @Override
            public int compare(Ride ride1, Ride ride2) {
                return (ride1.getPrice() < ride2.getPrice() ? 1 : ride1.getPrice() > ride2.getPrice() ? -1 : 0);     
            }
        });
        
        return cheapestRides;
       
    }
    
    /**
     * Print results for part1b
     * @param rides - rides to be printed to the user
     */
    public void printAllResults(ArrayList<Ride> rides){
        for(Ride r : rides){
            System.out.println(r.getCar() + " - " + r.getSupplier() + " - " + r.getPrice());
        }
    }
    
    
    
    /**
     * For each car category retain only one which is the cheapest.
     * @return Filtered rides.
     */
    private ArrayList<Ride> getCheapestRides(){
        ArrayList<Ride> cheapest = new ArrayList<>();
        for(CarCategory cat : CarCategory.values()){
            Ride r = this.getCheapestRideOf(cat);
            if(r==null)
                continue;
            cheapest.add(r);
        }
        return cheapest;
    }
    
    /**
     * 
     * @param cat - Car category to look for the cheapest ride.
     * @return The cheapest ride from the given category.
     */
    private Ride getCheapestRideOf(CarCategory cat){
        double price = Double.MAX_VALUE;
        Ride ride = null;
        
        for(Ride r : this.response){
            if(r.getCar().equals(cat) && price>r.getPrice()){
                price = r.getPrice();
                ride = r;
            }
        }
        
        return ride;
        
    }
    
    
}

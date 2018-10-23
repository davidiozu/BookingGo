package com.davidiozu.solution;

import com.davidiozu.solution.model.Supplier;
import com.davidiozu.solution.parser.InputParser;
import com.davidiozu.solution.response.SearchResults;

import com.davidiozu.solution.response.RequestRide;

public class Part1A {
    
    private static InputParser parsedInput;
    
    public static void main(String[] args){
        try {
            parsedInput = new InputParser(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        
        handshake(parsedInput);
        
        RequestRide request = new RequestRide(parsedInput.getPickup(), parsedInput.getDropoff(), parsedInput.getNoOfPassengers());
        request.addSupplier(Supplier.dave);
        
        SearchResults results;
        try {
            results = request.getAvailableRides();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        
        results.getDaveResults();
        results.printDaveResults();
        
    }
    
    /**
     * This is an initial message for the user.
     * Several prints to let him know about the order's status.
     * @param parsedInput - User's requirements for the ride.
     */
    public static void handshake(InputParser parsedInput){
        System.out.println("Hi, thank you for using our product.");
        System.out.println("In order to ensure that you have chosen the desired order, here are your requirements:");
        System.out.println("Depart:      " + parsedInput.getPickup().toString());
        System.out.println("Destination: " + parsedInput.getDropoff().toString());
        if(parsedInput.getNoOfPassengers() != -1)
            System.out.println("Passengers:  " + parsedInput.getNoOfPassengers());
        System.out.println("We are looking for your best ride!");
        System.out.println("This might take a while, hence we want to thank you for your patience!");
    }
    
}

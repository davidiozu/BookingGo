package com.davidiozu.solution;

import com.davidiozu.solution.model.Supplier;
import com.davidiozu.solution.parser.InputParser;
import com.davidiozu.solution.response.RequestRide;
import com.davidiozu.solution.response.SearchResults;

public class Part1B {

private static InputParser parsedInput;
    
    public static void main(String[] args){
        try {
            parsedInput = new InputParser(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        
        Part1A.handshake(parsedInput);
        
        RequestRide request = new RequestRide(parsedInput.getPickup(), parsedInput.getDropoff(), parsedInput.getNoOfPassengers());
        request.addSupplier(Supplier.dave);
        request.addSupplier(Supplier.eric);
        request.addSupplier(Supplier.jeff);
        
        SearchResults results;
        try {
            results = request.getAvailableRides();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        
        results.printAllResults(results.getAllResults());
        
    }
    
}

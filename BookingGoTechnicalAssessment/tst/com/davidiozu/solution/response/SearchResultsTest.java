package com.davidiozu.solution.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.davidiozu.solution.model.CarCategory;
import com.davidiozu.solution.model.Ride;
import com.davidiozu.solution.model.Supplier;

public class SearchResultsTest {
    
    private SearchResults results;
    
    @Before
    public void setUp(){
        results = new SearchResults(5);
    }
    
    @Test
    public void get_dave_results_on_regular_input(){
        
        results.addRide(new Ride(CarCategory.EXECUTIVE, 42100, Supplier.dave));
        results.addRide(new Ride(CarCategory.PEOPLE_CARRIER, 1200, Supplier.dave));
        results.addRide(new Ride(CarCategory.LUXURY_PEOPLE_CARRIER, 2119, Supplier.dave));
        results.addRide(new Ride(CarCategory.MINIBUS, 8510, Supplier.dave));
        results.addRide(new Ride(CarCategory.MINIBUS, 1029, Supplier.dave));
        results.addRide(new Ride(CarCategory.STANDARD, 12100, Supplier.dave));
        results.addRide(new Ride(CarCategory.LUXURY, 12131, Supplier.dave));
        
        ArrayList<Ride> foundRides = results.getDaveResults();
        
        //check rides filter by the number of passengers
        assertEquals(4, foundRides.size());
        
        //check rides order
        assertEquals(CarCategory.MINIBUS, foundRides.get(0).getCar());
        assertTrue(8510 == foundRides.get(0).getPrice());
        
        assertEquals(CarCategory.LUXURY_PEOPLE_CARRIER, foundRides.get(1).getCar());
        assertTrue(2119 == foundRides.get(1).getPrice());
        
        assertEquals(CarCategory.PEOPLE_CARRIER, foundRides.get(2).getCar());
        assertTrue(1200 == foundRides.get(2).getPrice());
        
        assertEquals(CarCategory.MINIBUS, foundRides.get(3).getCar());
        assertTrue(1029 == foundRides.get(3).getPrice());
        
    }
    
    @Test
    public void get_all_results_on_regular_input(){
        
        results = new SearchResults(2);
        
        results.addRide(new Ride(CarCategory.EXECUTIVE, 42100, Supplier.dave));
        results.addRide(new Ride(CarCategory.EXECUTIVE, 442100, Supplier.eric));
        results.addRide(new Ride(CarCategory.MINIBUS, 85110, Supplier.dave));
        results.addRide(new Ride(CarCategory.MINIBUS, 84291, Supplier.jeff));
        results.addRide(new Ride(CarCategory.STANDARD, 12100, Supplier.eric));
        results.addRide(new Ride(CarCategory.STANDARD, 12131, Supplier.dave));
        results.addRide(new Ride(CarCategory.STANDARD, 19123, Supplier.jeff));
        
        ArrayList<Ride> foundRides = results.getAllResults();
        
        //check rides filter by the cheapest rides
        assertEquals(3, foundRides.size());
        
        //check rides order
        
        assertEquals(CarCategory.MINIBUS, foundRides.get(0).getCar());
        assertTrue(84291 == foundRides.get(0).getPrice());
        assertEquals(Supplier.jeff.getName(), foundRides.get(0).getSupplier());

        assertEquals(CarCategory.EXECUTIVE, foundRides.get(1).getCar());
        assertTrue(42100 == foundRides.get(1).getPrice());
        assertEquals(Supplier.dave.getName(), foundRides.get(1).getSupplier());   

        assertEquals(CarCategory.STANDARD, foundRides.get(2).getCar());
        assertTrue(12100 == foundRides.get(2).getPrice());
        assertEquals(Supplier.eric.getName(), foundRides.get(2).getSupplier());

        
    }
    
}

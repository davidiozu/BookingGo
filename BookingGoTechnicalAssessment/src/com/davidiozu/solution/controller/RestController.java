package com.davidiozu.solution.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.davidiozu.solution.exception.ExceptionMessages;
import com.davidiozu.solution.model.Location;
import com.davidiozu.solution.model.Supplier;
import com.davidiozu.solution.parser.InputParser;
import com.davidiozu.solution.response.RequestRide;
import com.davidiozu.solution.response.SearchResults;
import com.davidiozu.solution.util.Utils;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class RestController {
    
    public void start(){
        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);
        setPort(Utils.serverPort);
        Route goodRoute = getValidRoute();
        Route wrongRoute = getWrongRoute();
        Spark.get(Utils.rootPath, Utils.acceptedType, goodRoute);
        Spark.notFound(wrongRoute);
    }
    
    /**
     * Set the port number for server to run.
     * @param portNo - port number
     */
    private void setPort(int portNo){
        Spark.port(portNo);
    }
    
    /**
     * 
     * @return route on wrong path
     */
    private Route getWrongRoute(){
        return new Route(){

            @Override
            public Object handle(Request request, Response response)
                    throws Exception {
                response.status(Utils.SERVER_RESPONSE_WRONG_CODE);
                return Utils.formatServerResponse("warn", ExceptionMessages.INVALID_URL);
            }
            
        };
    }
    
    /**
     * Takes all parameters from url and uses them to access suppliers' apis for finding available rides.
     * @return route on right path
     */
    private Route getValidRoute(){
        return new Route() {
            
            @Override
            public Object handle(Request request, Response response) throws Exception {
                
                response.type(Utils.acceptedType);
                
                String pickUpLoc = request.queryParams("pickup");
                String dropOffLoc = request.queryParams("dropoff");
                String noOfPassengers = request.queryParams("passengers");
                
                Location pickupLocation = null;
                Location dropoffLocation = null;
                int noOfPass = -1;
                
                try{
                    pickupLocation  = checkLocation(pickUpLoc);
                    dropoffLocation = checkLocation(dropOffLoc);
                }catch(Exception e){
                    response.status(Utils.SERVER_RESPONSE_WRONG_CODE);
                    return Utils.formatServerResponse("warn", e.getMessage());
                }
                
                try{
                    noOfPass = checkNoOfPassengers(noOfPassengers);
                } catch(Exception e){
                    response.status(Utils.SERVER_RESPONSE_WRONG_CODE);
                    return Utils.formatServerResponse("warn", e.getMessage());
                }
                
                RequestRide req = new RequestRide(pickupLocation, dropoffLocation, noOfPass);
                req.addSupplier(Supplier.dave);
                req.addSupplier(Supplier.eric);
                req.addSupplier(Supplier.jeff);
                
                SearchResults results;
                try {
                    results = req.getAvailableRides();
                } catch (Exception e) {
                    response.status(Utils.SERVER_RESPONSE_WRONG_CODE);
                    return Utils.formatServerResponse("warn", e.getMessage());
                }
                
                Gson serverResponse = new Gson();
                
                return serverResponse.toJson(results.getAllResults());
            }
        };
    }
    
    /**
     * Uses {@link com.davidiozu.solution.parser.InputParser#parseLocation(String)}
     * @param loc - location from input
     * @return parsed location
     * @throws Exception
     */
    private Location checkLocation(String loc) throws Exception{
        if(loc == null)
            throw new Exception(ExceptionMessages.MISSING_LOCATION);
        Location parsedLoc = null;
        try{
            parsedLoc = InputParser.parseLocation(loc);
        }catch(Exception e){
            throw new Exception(ExceptionMessages.INVALID_INPUT);
        }
        return parsedLoc;
        
    }
    
    /**
     * Uses {@link  com.davidiozu.solution.parser.InputParser#parsePassengerNo(String)}
     * @param pass - number of passengers from input
     * @return parsed number of passengers
     * @throws Exception
     */
    private int checkNoOfPassengers(String pass) throws Exception{
        if(pass == null)
            return -1;
        int parsedNoOfPass = -1;
        try{
            parsedNoOfPass = InputParser.parsePassengerNo(pass);
        }catch(Exception e){
            throw new Exception(ExceptionMessages.INVALID_NO_OF_PASSENGERS);
        }
        return parsedNoOfPass;
        
    }
    
}

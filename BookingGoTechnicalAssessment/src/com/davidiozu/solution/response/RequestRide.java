package com.davidiozu.solution.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;

import com.davidiozu.solution.api.Option;
import com.davidiozu.solution.api.ServerResponse;
import com.davidiozu.solution.exception.ExceptionMessages;
import com.davidiozu.solution.model.CarCategory;
import com.davidiozu.solution.model.Location;
import com.davidiozu.solution.model.Ride;
import com.davidiozu.solution.model.Supplier;
import com.davidiozu.solution.util.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RequestRide {

    private ArrayList<Supplier> suppliers;
    private Location pickup;
    private Location dropoff;
    private int noOfPassengers;
    
    public RequestRide(Location pickup, Location dropOff, int passengers) {
        this.pickup = pickup;
        this.dropoff = dropOff;
        this.suppliers = new ArrayList<>();
        this.noOfPassengers = passengers;
    }
    
    /**
     * For each supplier, rides are searched
     * @return all rides found matching the criteria
     * @throws Exception 
     */
    public SearchResults getAvailableRides() throws Exception{
        
        URL url;
        SearchResults results = new SearchResults(noOfPassengers);
        
        for (Supplier currentSupplier : this.suppliers) {
            
            try {
                url = new URL(constructURL(currentSupplier));
            } catch (MalformedURLException e1) {
                System.out.println(currentSupplier.getName() + ": " + ExceptionMessages.SERVER_PROBLEM);
                continue;
            }

            String response;

            try {
                response = startRequest(url);
            } catch (IOException e) {
                System.out.println(currentSupplier.getName() + ": " +ExceptionMessages.SERVER_PROBLEM);
                continue;
            }

            ServerResponse resp = this.getResponse(response);

            for(Option opt : resp.getOptions()){
                String carCategory = opt.getCarCategory();
                Ride newRide = new Ride(CarCategory.valueOf(carCategory), opt.getPrice(), currentSupplier);
                results.addRide(newRide);
            }
       
        }
        
        return results;
    }
    
    /**
     * 
     * @param currentSupplier - supplier's name to construct its url
     * @return the supplier's url
     */
    private String constructURL(Supplier currentSupplier){
        String requestURL = currentSupplier.getUrl();
        requestURL += String.format("?pickup=%s,%s",pickup.getLatitude(), pickup.getLongitude());
        requestURL += String.format("&dropoff=%s,%s",dropoff.getLatitude(), dropoff.getLongitude());
        return requestURL;
        
    }
    
    /**
     * Performs a request to a given server url.
     * @param url - server URL to request data from
     * @return server output for the given request
     * @throws Exception if server fails to respond/not active
     */
    private String startRequest(URL url) throws Exception{
        try {
            StringBuilder result = new StringBuilder();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(Utils.SERVER_TIMEOUT);
            conn.setReadTimeout(Utils.SERVER_TIMEOUT);
            int responseCode = conn.getResponseCode();

            if (responseCode != Utils.SERVER_RESPONSE_OK_CODE) {
                System.out.println(url.toString() + ": "
                        + ExceptionMessages.SUPPLIER_NOT_RESPONDING);
                throw new IOException();
            }

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            return result.toString();
        } catch (SocketTimeoutException e) {
            System.out.println(url.toString() + ": "
                    + ExceptionMessages.SERVER_TIMEOUT);
            throw new IOException();
        }
    }
    
    /**
     * Convert from string response to an object response to manipulate it easier
     * @param serverResponse - server output
     * @return the response of the server as a {@link com.davidiozu.solution.api.ServerResponse}
     */
    private ServerResponse getResponse(String serverResponse){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        ServerResponse response = gson.fromJson(serverResponse, ServerResponse.class);
        
        return response;
    }
    
    /**
     * Ensures that taxis are search from supplier's taxi as well
     * @param s - supplier to search taxi from
     */
    public void addSupplier(Supplier s){
        this.suppliers.add(s);
    }
    
}

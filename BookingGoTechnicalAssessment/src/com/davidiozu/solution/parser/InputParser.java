package com.davidiozu.solution.parser;

import com.davidiozu.solution.exception.ExceptionMessages;
import com.davidiozu.solution.model.Location;
import com.davidiozu.solution.util.Utils;

public class InputParser {

    private Location pickupLocation;
    private Location dropoffLocation;
    private int noOfPassengers;
    
    public InputParser(String[] userInput) throws Exception{
        noOfPassengers = Utils.UNSPECIFIED_NO_OF_PASSENGERS;
        getLocation(userInput);
    }
    
    /**
     * Extracts the pickup and drop off locations.
     * Extracts maximum number of passengers if given.
     * @param userInput - User's ride requirements.
     * @throws Exception when invalid input is given
     */
    private void getLocation(String[] userInput) throws Exception{
        if(userInput.length != 2)
            if(userInput.length != 3)
            throw new Exception(ExceptionMessages.INVALID_INPUT);

        String pickupLocation = userInput[0];
        String dropoffLocation = userInput[1];

        this.pickupLocation = parseLocation(pickupLocation);
        this.dropoffLocation = parseLocation(dropoffLocation);
        
        if(userInput.length == 3){
            String noOfPassengers = userInput[2];
            this.noOfPassengers = parsePassengerNo(noOfPassengers);
        }
        
    }
    
    /**
     * Parses location from user input to Location object
     * @param locationString - location taken from input
     * @return the encapsulated parsed location
     * @throws Exception on invalid number or input format given
     */
    public static Location parseLocation(String locationString) throws Exception{
        String[] locationLongLat = locationString.split(",");
        if(locationLongLat.length != 2)
            throw new Exception(ExceptionMessages.INVALID_INPUT);
        
        String latitude = locationLongLat[0];
        String longitude = locationLongLat[1];
        
        double longi, lat;
        
        try{
            longi = Double.parseDouble(longitude);
            lat  = Double.parseDouble(latitude);
        } catch(NumberFormatException e){
            throw new Exception(ExceptionMessages.INVALID_NUMBER);
        }
        
        if(longi < Utils.minLongitude || longi > Utils.MAX_LONGITUDE)
            throw new Exception(ExceptionMessages.INVALID_LONGITUDE);
            
        if(lat < Utils.minLatitude || lat > Utils.maxLatitude)
            throw new Exception(ExceptionMessages.INVALID_LATITUDE);
        
        return new Location(latitude, longitude);
        
    }
    
    /**
     * Parses passenger number from user input to int
     * @param pass - number taken from input
     * @return the encapsulated parsed number
     * @throws Exception on invalid number or input format given
     */
    public static int parsePassengerNo(String pass) throws Exception{
        int check = -1;
        try{
            check = Integer.parseInt(pass);
        } catch(NumberFormatException e){
            throw new Exception(ExceptionMessages.INVALID_NO_OF_PASSENGERS);
        }
        if(check<1 || check>16)
            throw new Exception(ExceptionMessages.INVALID_NO_OF_PASSENGERS);
        return check;
    }
    
    public Location getPickup(){
        return pickupLocation;
    }
    
    public Location getDropoff(){
        return dropoffLocation;
    }
    
    public int getNoOfPassengers(){
        return noOfPassengers;
    }
    
}

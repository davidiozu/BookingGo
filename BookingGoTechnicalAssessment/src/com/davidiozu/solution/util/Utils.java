package com.davidiozu.solution.util;

import java.util.HashMap;

import com.davidiozu.solution.model.CarCategory;

public class Utils {
    
    /**
     * Server data type
     */
    public static final String acceptedType = "application/json";
    
    
    /**
     * Path used by rest server
     */
    public static final String rootPath = "DIbookinggo";
    
    /**
     * Port used by rest server
     */
    public static final int serverPort = 10000;
    
    /**
     * Take -1 as no number of passengers specified.
     */
    public static final int UNSPECIFIED_NO_OF_PASSENGERS = -1;
    
    /**
     * Server code when problem encountered.
     */
    public static final int SERVER_RESPONSE_WRONG_CODE = 400;
    
    /**
     * Server code when no problem encountered.
     */
    public static final int SERVER_RESPONSE_OK_CODE = 200;
    
    /**
     * Timeout in milliseconds for the server response.
     */
    public static final int SERVER_TIMEOUT = 2000;
    
    /**
     * Bounds for longitude.
     */
    public static final int MAX_LONGITUDE = 180, minLongitude = -180;
    
    /**
     * Bounds for latitude.
     */
    public static final int maxLatitude = 90, minLatitude = -90;
    
    private static HashMap<CarCategory, Integer> maxNoOfPassengers;
    static {
        maxNoOfPassengers = new HashMap<>();
        maxNoOfPassengers.put(CarCategory.STANDARD, 4);
        maxNoOfPassengers.put(CarCategory.EXECUTIVE, 4);
        maxNoOfPassengers.put(CarCategory.LUXURY, 4);
        maxNoOfPassengers.put(CarCategory.PEOPLE_CARRIER, 6);
        maxNoOfPassengers.put(CarCategory.LUXURY_PEOPLE_CARRIER, 6);
        maxNoOfPassengers.put(CarCategory.MINIBUS, 16);
    }
    
    /**
     * 
     * @param car - A car category
     * @return Maximum numbers of passengers for the given category
     */
    public static Integer getMaxNoOfPassengers(CarCategory car){
        if(maxNoOfPassengers.containsKey(car))
            return maxNoOfPassengers.get(car);
        return -1;
    }
    
    /**
     * Json formatter of the message
     * @param type - message type
     * @param message - message to display
     * @return message in Json format
     */
    public static String formatServerResponse(String type, String message){
        return "{\"" + type + "\":" + "\"" + message + "\"}";
    }
    
    
}

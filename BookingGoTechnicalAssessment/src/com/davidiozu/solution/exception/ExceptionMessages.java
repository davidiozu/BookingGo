package com.davidiozu.solution.exception;
/**
 * A static class which contains all error messages the program outputs.
 * @author david
 *
 */
public class ExceptionMessages {
    
    public final static String INVALID_INPUT = "Invalid input! Please follow the right input conventions:\n "
            + "<pickUpLatitude>,<pickUpLongitude> <dropoffLatitude>,<dropoffLongitude>\n"
            + "or\n <pickUpLatitude>,<pickUpLongitude> <dropoffLatitude>,<dropoffLongitude> <noOfPassengers>";
    
    public final static String INVALID_NUMBER = "Invalid input! Please insert only numbers. Separate decimal point using '.'";
    
    public final static String INVALID_LONGITUDE = "The longitude value needs to be between -180 and 180.";
    
    public final static String INVALID_LATITUDE = "The latitude value needs to be between -90 and 90.";
    
    public final static String SERVER_PROBLEM = "The server has encountered a problem. Please try again later.";
    
    public final static String SUPPLIER_NOT_RESPONDING = "Supplier is not responding.";
    
    public final static String INVALID_NO_OF_PASSENGERS = "Invalid input! Please follow the right number of passenger conventions:\n"
            + "It should be a number\n"
            + "It should be at least 1 and at most 16";

    public final static String MISSING_LOCATION = "Ensure you enclude your pickup and dropoff locations";
    
    public final static String INVALID_URL = "You have inserted an invalid URL.";
    
    public final static String SERVER_TIMEOUT = "Server timeout!";
    
}

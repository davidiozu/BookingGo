package com.davidiozu.solution.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.davidiozu.solution.exception.ExceptionMessages;

public class InputParserTest {

    
    @Test
    public void parse_passenger_no_on_invalid_input(){

        try {
            InputParser.parsePassengerNo("a");
        } catch (Exception e) {
            assertEquals(e.getMessage(), ExceptionMessages.INVALID_NO_OF_PASSENGERS);
        }
        
    }
    
    @Test
    public void parse_passenger_no_on_outrange_number(){

        try {
            InputParser.parsePassengerNo("-10");
        } catch (Exception e) {
            assertEquals(e.getMessage(), ExceptionMessages.INVALID_NO_OF_PASSENGERS);
        }
       
    }
    
    @Test
    public void parse_passenger_no_on_good_input() throws Exception{
    
        assertEquals(2, InputParser.parsePassengerNo("2"));

    }
    
    @Test
    public void parse_location_no_on_invalid_input(){

        try {
            InputParser.parseLocation("a");
        } catch (Exception e) {
            assertEquals(e.getMessage(), ExceptionMessages.INVALID_INPUT);
        }
        
    }
    
    @Test
    public void parse_location_no_on_outrange_coordinate(){

        try {
            InputParser.parseLocation("200,10");
        } catch (Exception e) {
            assertEquals(e.getMessage(), ExceptionMessages.INVALID_LATITUDE);
        }
       
    }
    
    @Test
    public void parse_location_no_on_good_input() throws Exception{
    
        assertEquals("20", InputParser.parseLocation("20,10").getLatitude());
        assertEquals("10", InputParser.parseLocation("20,10").getLongitude());

    }
    
}

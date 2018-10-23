package com.davidiozu.solution.response;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import com.davidiozu.solution.model.Location;
import com.davidiozu.solution.model.Supplier;


public class RequestRideTest {

    private RequestRide r;
    
    @Before
    public void setUp(){
        r = new RequestRide(new Location("10", "10"), new Location("10", "10"), 2);
    }
    
    @Test
    public void construct_url() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Method method = RequestRide.class.getDeclaredMethod("constructURL", Supplier.class);
        method.setAccessible(true);
        assertEquals("https://techtest.rideways.com/dave?pickup=10,10&dropoff=10,10", method.invoke(r, Supplier.dave));
    }
    
}

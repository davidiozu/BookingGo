package com.davidiozu.solution;

import com.davidiozu.solution.controller.RestController;

public class Part2 {
    
    public static void main(String[] args){
        
        RestController restServer = new RestController();
        restServer.start();
        
    }
    
}

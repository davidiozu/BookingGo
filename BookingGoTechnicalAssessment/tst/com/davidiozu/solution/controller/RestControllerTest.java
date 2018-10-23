package com.davidiozu.solution.controller;

import static org.junit.Assert.assertEquals;

import org.junit.ClassRule;
import org.junit.Test;

import com.davidiozu.solution.util.Utils;
import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpResponse;
import com.despegar.sparkjava.test.SparkServer;

import spark.servlet.SparkApplication;

public class RestControllerTest {

    public static class ContollerTest  implements SparkApplication {
        @Override
        public void init() {
            RestController rest = new RestController();
            rest.start();
        }
    }

    @ClassRule
    public static SparkServer<ContollerTest> testServer = new SparkServer<>(RestControllerTest.ContollerTest.class, Utils.serverPort);

    @Test
    public void invalid_input_request() throws Exception {

        String request = "/" + Utils.rootPath + "?pickup=10,10&dropoff=aaa,bbb";

        GetMethod get = testServer.get(request, false);

        HttpResponse httpResponse = testServer.execute(get);
        System.out.println(new String(httpResponse.body()));
        assertEquals(Utils.SERVER_RESPONSE_WRONG_CODE, httpResponse.code());
    }
    
}
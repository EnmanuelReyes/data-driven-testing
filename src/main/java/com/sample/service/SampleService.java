package com.sample.service;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/16/18
 * Time: 4:48 PM
 */
public class SampleService {

    public static void main(String[] args) {
        port(4999);
        get("/test", (req, res) -> "Crewe\ttrue\rGlasgow\tfalse");
    }
}

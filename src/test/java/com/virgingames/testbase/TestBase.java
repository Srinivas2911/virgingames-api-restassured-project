package com.virgingames.testbase;

/*
Created by SP
*/

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {

    /*
     * Setting up the baseURI and the basePath
     */

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://www.virgingames.com";
        RestAssured.basePath = "/bingo";

    }
}

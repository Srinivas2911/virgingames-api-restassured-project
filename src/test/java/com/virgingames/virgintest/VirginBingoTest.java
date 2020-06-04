package com.virgingames.virgintest;

/*
Created by SP
*/

import com.virgingames.constants.EndPoints;
import com.virgingames.testbase.TestBase;
import io.restassured.parsing.Parser;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class VirginBingoTest extends TestBase {

    /*
     * This test will verify that all Games extracted has default
     * game frequency = 300000 with HashMap
     */

    @Test
    public void verifyThatGamesExtractedHasDefaultGameFrequencyIs300000() {

        List<HashMap<String, Object>> defaultGameFrequency = given()
                .when()
                .get(EndPoints.GET_ALL_BINGO)
                .then()
                .parser("text/plain;charset=UTF-8", Parser.JSON)
                .log().all()
                .extract().path("bingoLobbyInfoResource.streams.findAll{it.defaultGameFrequency='300000'}.defaultGameFrequency");

        assertTrue(defaultGameFrequency.contains("300000"));
        assertTrue(defaultGameFrequency.containsAll(Collections.singleton("300000")));

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The defaultGameFrequency values are: " + defaultGameFrequency);
        System.out.println("------------------End of Test---------------------------");
    }

    /*
     * This test will also verify that all Games extracted has default
     * game frequency = 300000 in the String formats.
     */

    @Test
    public void verifyThatAllGamesExtractedHasDefaultGameFrequencyIs30000() {

        String defaultGameFrequency = given()
                .when()
                .get(EndPoints.GET_ALL_BINGO)
                .then()
                .parser("text/plain;charset=UTF-8", Parser.JSON)
                .log().all()
                .extract().response().body().path("bingoLobbyInfoResource.streams.findAll{it.defaultGameFrequency='300000'}").toString();
        assertTrue(defaultGameFrequency.contains("defaultGameFrequency=300000"));
        assertFalse(defaultGameFrequency.contains("defaultGameFrequency=null"));

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The defaultGameFrequency for the Games are: " + defaultGameFrequency);
        System.out.println("------------------End of Test---------------------------");
    }

    /*
     * This test will verify that the startTime for all Games is always future timestamp.
     * i.e. startTimes is greater than timeStamp.
     */

    @Test
    public void verifyThatStartTimeIsAlwaysGreaterThanTimeStamp() {

        String timestamp = given()
                .when()
                .get(EndPoints.GET_ALL_BINGO)
                .then()
                .parser("text/plain;charset=UTF-8", Parser.JSON)
                .log().all()
                .extract().path("timestamp").toString();

        String startTime = given()
                .when()
                .get(EndPoints.GET_ALL_BINGO)
                .then()
                .parser("text/plain;charset=UTF-8", Parser.JSON)
                .extract().path("bingoLobbyInfoResource.streams.startTime").toString();

        assertThat(startTime, greaterThan(timestamp));

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The timestamp is : " + timestamp);
        System.out.println("The startTimes are :" + startTime);
        System.out.println("------------------End of Test---------------------------");
    }

}
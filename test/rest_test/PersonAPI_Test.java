/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest_test;

import org.junit.Test;
import static org.junit.Assert.*;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import com.jayway.restassured.parsing.Parser;
import dk.cphbusiness.entity.Person;
import javax.ws.rs.core.MediaType;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;

/**
 *
 * @author Bancho
 */
public class PersonAPI_Test {
    
    public PersonAPI_Test() {
    }
    
    @BeforeClass
    public static void setUpClass(){
        baseURI = "http://localhost:8084/CA2";
        defaultParser = Parser.JSON;
        basePath = "/api/person";
    }
    
    @Test
    public void addPersonTest(){
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Person("Bancho", "Petrov"))
        .when()
                .post()
        .then()
                .statusCode(201)
                .body("firstName", equalTo("Bancho"));
    }
    
    @Test
    public void PersonNotFoundException(){
        given()
                .contentType(MediaType.APPLICATION_JSON)
        .when()
                .get("/10000")
        .then()
                .statusCode(404)
                .body("msg", equalTo("There is no person with the info"));
    }
    
}

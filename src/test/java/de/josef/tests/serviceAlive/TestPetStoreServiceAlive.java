package de.josef.tests.serviceAlive;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestPetStoreServiceAlive {

    @BeforeMethod
    public void setUp() {

        RestAssured.baseURI = "https://petstore.swagger.io/";


    }

    @Test
    public void isServiceAliveTest(){

        given()
        .when()
                .get()
        .then()
        .assertThat()
        .statusCode(200);
    }

}

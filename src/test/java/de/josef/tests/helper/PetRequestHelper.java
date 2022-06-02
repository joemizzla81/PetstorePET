package de.josef.tests.helper;

import de.josef.tests.domain.PetEntry;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PetRequestHelper {

    public PetRequestHelper() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    public void createPetEntry(PetEntry petEntry){

        Response response = (Response) given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(petEntry)
                .when()
                .post("/pet/");

                 response.then()
                .assertThat()
                .statusCode(200);
    }

    public void assertPetEntryMethod1justIDPresent(PetEntry petEntry){

        Response response = (Response) given()
                .when()
                .get("/pet/" + petEntry.getId());

                 response.then()
                .assertThat()
                .statusCode(200)
                .body("name", is(nullValue()))
                .body("id", equalTo(petEntry.getId()))
                .body("photoUrls", is(nullValue()))
                .body("tags", is(nullValue()));
    }

    public void assertPetEntryMethod2namePresentAndIDPresent(PetEntry petEntry){

        Response response = (Response) given()
                .when()
                .get("/pet/" + petEntry.getId());

                response.then()
                .assertThat()
                .statusCode(200)
                .body("name",containsString("New Pet Only Name Field And ID And Status"))
                .body("id",equalTo(88971123));
    }

    public void assertPetEntryMethod3namePresentAndIDAndPhotoUrlsPresent(PetEntry petEntry){

        Response response = (Response) given()
                .when()
                .get("/pet/" + petEntry.getId());

                 response.then()
                .assertThat()
                .statusCode(200)
                .body("name",containsString("New Pet with Photo Urls"))
                .body("id",equalTo(petEntry.getId()))
                .body("photoUrls",hasItem("https://www.photopage.com"))
                .body("photoUrls",hasItem("https://bing.com"));
    }

    public void assertPetEntryMethod4NotExistingPet(PetEntry petEntry){

        Response response = (Response) given()
                .when()
                .get("/pet/" + petEntry.getId());

                 response.then()
                .assertThat()
                .statusCode(404)
                .body("message",containsString("Pet not found"));

    }


    public PetEntry getPetEntry(int id){

       Response response = (Response) given()
                .when()
                .get("/pet/"+id);

       response.then().assertThat().statusCode(200).body("id",not(is(nullValue())));

       return response.getBody().as(PetEntry.class);
    }


    public void findPetByStatusSold(PetEntry petEntry){

        Response response = (Response) given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("status","sold")
                .when()
                .get("/pet/findByStatus");

                 response.then()
                .assertThat()
                .statusCode(200)
                .body("status[0]",equalTo("sold"));
    }

    public void findPetByStatusAvailable(PetEntry petEntry){

        Response response = (Response) given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("status","available")
                .when()
                .get("/pet/findByStatus");

                 response.then()
                .assertThat()
                .statusCode(200)
                .body("status[0]",equalTo("available"))
                .body("status[1]",equalTo("available"))
                .body("status[2]",not(equalTo("sold")));
    }

    public void findPetByStatusPending(PetEntry petEntry){

        Response response = (Response) given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("status","pending")
                .when()
                .get("/pet/findByStatus");

                 response.then()
                .assertThat()
                .statusCode(200)
                .body("status", hasItem("pending"))
                .body("status", not(hasItem("available")))
                .body("status", not(hasItem("sold")))
                .body("id", not(hasItem(818181)));
    }

    public void findPetByStatusInvalidStatus(PetEntry petEntry){

        Response response = (Response) given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("status","Invalid")
                .when()
                .get("/pet/findByStatus");

                 response.then()
                .assertThat()
                .statusCode(200)
                .body("status", not(hasItem("pending")))
                .body("status", not(hasItem("available")))
                .body("status", not(hasItem("sold")));
    }

    public void putUpdatePetExistingPet(PetEntry petEntry){

        Response response = (Response) given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(petEntry)
                .when()
                .put("/pet");

                 response.then()
                .assertThat()
                .statusCode(200)
                .body("name",containsString(petEntry.getName()))
                .body("id",equalTo(petEntry.getId()));
    }

    public void postUpdatePetNotExistingPet(PetEntry petEntry){

        Response response = (Response) given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(petEntry)
                .when()
                .post("/pet/"+ petEntry.getId());

                 response.then()
                .assertThat()
                .statusCode(415);
    }



    public void deleteExistingPet(PetEntry petEntry){

        Response response = (Response) given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .delete("/pet/" + petEntry.getId());

                 response.then()
                .assertThat()
                .statusCode(200)
                .body("code",equalTo(200))
                .body("message",containsString(String.valueOf(petEntry.getId())));


    }

    public void deleteNotExistingPet(PetEntry petEntry){

        Response response = (Response) given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .delete("/pet/" + petEntry.getId());

                response.then()
                .assertThat()
                .statusCode(404);

    }


}

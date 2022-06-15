package de.josef.tests.helper;

import de.josef.tests.domain.Pet;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetRequestHelper {

    public PetRequestHelper(String environment) {

        RestAssured.baseURI = environment;


    }

    public Response createPetEntry(Pet pet){

        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet/");

    }


    public Response getPetEntry(int id){

        return given()
                .when()
                .get("/pet/"+id);

    }


    public Response findPetByStatus(Pet pet){

        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("status",pet.getStatus())
                .when()
                .get("/pet/findByStatus");

    }


    public Response putUpdatePet(Pet pet){

        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .put("/pet");

    }


    public Response postUpdatePet(Pet pet){

        return given()
                .formParam("name","post update name")
                .formParam("status","sold")
                .when()
                .post("/pet/"+ pet.getId());

    }


    public File getImageFile(){

        File file = new File(System.getProperty("user.dir") + "/src/test/java/de/josef/tests/domain/fileio/Bild1png.png");

        return file;
    }



    public Response deletePet(int petId){

        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .delete("/pet/" + petId);

    }



}

package de.josef.tests.petRessource;

import de.josef.tests.domain.Pet;
import de.josef.tests.helper.PetRequestHelper;
import de.josef.tests.testData.DefaultTestData;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostUploadImagePetTest {

    private Pet pet;
    private Pet createdPet;
    private PetRequestHelper petRequestHelper;

    @BeforeMethod
    public void setUp() {

        String environment = DefaultTestData.environment;

        pet = new Pet();

        petRequestHelper = new PetRequestHelper(environment);

        pet.setId(DefaultTestData.validId1);
        pet.setName(DefaultTestData.petName2);
        pet.setStatus(DefaultTestData.statusSold);
        petRequestHelper.createPetEntry(pet);

    }

    @Test
    public void postUploadValidImagePet(){


        
        Response response =  given()

                .multiPart("file", petRequestHelper.getImageFile(), "multipart/form-data")
                .when()
                .post("/pet/"+ pet.getId()+"/uploadImage");


        response.then()
                .assertThat()
                .statusCode(200);

    }

       @Test
       public void postUploadInvalidMethodsPet(){

        Response response =  given()

                .multiPart("file", petRequestHelper.getImageFile(), "multipart/form-data")
                .when()
                .put("/pet/"+ pet.getId()+"/uploadImage");


        response.then()
                .assertThat()
                .statusCode(405);

        Response response2 =  given()

                .multiPart("file2", petRequestHelper.getImageFile(), "multipart/form-data")
                .when()
                .post("/pet/"+ pet.getId()+"/uploadImage");


        response2.then()
                .assertThat()
                .statusCode(500);

    }

    @AfterMethod
    public void teardown(){

        petRequestHelper.deletePet(pet.getId());

    }
}

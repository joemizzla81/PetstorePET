package de.josef.tests.petRessource;

import de.josef.tests.domain.Pet;
import de.josef.tests.helper.PetRequestHelper;
import de.josef.tests.testData.DefaultTestData;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeletePetTest {
    private Pet pet;
    private Pet createdPet;
    private PetRequestHelper petRequestHelper;

    @BeforeMethod
    public void setUp() {

        String environment = DefaultTestData.environment;

        pet = new Pet();

        petRequestHelper = new PetRequestHelper(environment);

        pet.setId(DefaultTestData.validId1);
        pet.setStatus(DefaultTestData.statusPending);
        petRequestHelper.createPetEntry(pet);

        pet.setId(DefaultTestData.validId2);
        pet.setName(DefaultTestData.petName1);
        pet.setPhotoUrls(DefaultTestData.stringArrayPhotoUrls);
        pet.setStatus(DefaultTestData.statusPending);
        petRequestHelper.createPetEntry(pet);

        pet.setId(DefaultTestData.validId3);
        pet.setStatus(DefaultTestData.statusAvailable);
        petRequestHelper.createPetEntry(pet);

        pet.setId(DefaultTestData.validId4);
        pet.setName(DefaultTestData.petName2);
        pet.setStatus(DefaultTestData.statusSold);
        petRequestHelper.createPetEntry(pet);

    }

    @Test
    public void deleteExistingPets(){

        pet.setId(DefaultTestData.validId1);
        Response response = petRequestHelper.deletePet(pet.getId());

        response.then()
                .assertThat()
                .statusCode(200)
                .body("code",equalTo(200))
                .body("message",containsString(String.valueOf(pet.getId())));


       pet.setId(DefaultTestData.validId2);
       Response response2 = petRequestHelper.deletePet(pet.getId());

       response2.then()
                .assertThat()
                .statusCode(200)
                .body("code",equalTo(200))
                .body("message",containsString(String.valueOf(pet.getId())));


        pet.setId(DefaultTestData.validId3);
        Response response3 = petRequestHelper.deletePet(pet.getId());

        response3.then()
                .assertThat()
                .statusCode(200)
                .body("code",equalTo(200))
                .body("message",containsString(String.valueOf(pet.getId())));


        pet.setId(DefaultTestData.validId4);
        Response response4 = petRequestHelper.deletePet(pet.getId());
        response4.then()
                .assertThat()
                .statusCode(200)
                .body("code",equalTo(200))
                .body("message",containsString(String.valueOf(pet.getId())));
    }

    @Test
    public void deleteNotExistingPet(){

        pet.setId(DefaultTestData.invalidId1);
        Response response = petRequestHelper.deletePet(pet.getId());

        response.then()
                .assertThat()
                .statusCode(404);

    }

}

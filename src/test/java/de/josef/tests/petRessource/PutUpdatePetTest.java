package de.josef.tests.petRessource;

import de.josef.tests.domain.Pet;
import de.josef.tests.helper.PetRequestHelper;
import de.josef.tests.testData.DefaultTestData;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class PutUpdatePetTest {

    private Pet pet;
    private Pet createdPet;
    private PetRequestHelper petRequestHelper;

    @BeforeMethod
    public void setUp() {

        String environment = DefaultTestData.environment;

        pet = new Pet();

        petRequestHelper = new PetRequestHelper(environment);

        pet.setId(DefaultTestData.validId2);
        pet.setName(DefaultTestData.petName2);
        pet.setStatus(DefaultTestData.statusSold);
        petRequestHelper.createPetEntry(pet);

    }

    @Test
    public void putUpdateExistingPet(){

        pet.setId(DefaultTestData.validId2);
        pet.setName(DefaultTestData.petNameForPutUpdate);

        Response response = petRequestHelper.putUpdatePet(pet);
        createdPet = response.body().as(Pet.class);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("name",containsString(pet.getName()))
                .body("id",equalTo(pet.getId()));

    }

    @Test
    public void putUpdateNotExistingPet(){

        pet.setId(DefaultTestData.invalidId2);
        pet.setName(DefaultTestData.petName2ForPutUpdateNotExistingPet);
        pet.setStatus(DefaultTestData.statusAvailable);

        Response response = petRequestHelper.putUpdatePet(pet);
        createdPet = response.body().as(Pet.class);

         response.then()
                .assertThat()
                .statusCode(200)
                .body("name",containsString(pet.getName()))
                .body("id",equalTo(pet.getId()));
    }

}

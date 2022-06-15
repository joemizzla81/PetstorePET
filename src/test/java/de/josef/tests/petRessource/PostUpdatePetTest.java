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
import static org.junit.Assert.assertThat;

public class PostUpdatePetTest {

    private Pet pet;
    private Pet createdPet;
    private PetRequestHelper petRequestHelper;

    @BeforeMethod
    public void setUp() {

        String environment = DefaultTestData.environment;

        pet = new Pet();

        petRequestHelper = new PetRequestHelper(environment);

        pet.setId(DefaultTestData.validId4);
        pet.setName(DefaultTestData.petName2);
        pet.setStatus(DefaultTestData.statusSold);
        petRequestHelper.createPetEntry(pet);

    }

    @Test
    public void postUpdateNotExistingPet(){

        pet.setId(DefaultTestData.invalidId1);

        Response response = petRequestHelper.postUpdatePet(pet);
        createdPet = response.body().as(Pet.class);

        response.then()
                .assertThat()
                .statusCode(404);

    }

    @Test
    public void postUpdateExistingPet(){

        pet.setId(DefaultTestData.validId4);

        Response response = petRequestHelper.postUpdatePet(pet);
        createdPet = response.body().as(Pet.class);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("code",equalTo(200))
                .body("message",containsString(String.valueOf(pet.getId())));

    }

    @AfterMethod
    public void teardown(){

        petRequestHelper.deletePet(pet.getId());

    }

}

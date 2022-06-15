package de.josef.tests.petRessource;

import de.josef.tests.domain.Pet;
import de.josef.tests.helper.PetRequestHelper;
import de.josef.tests.testData.DefaultTestData;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class GetFindPetByStatusTest {

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
    public void findPetsWithStatusPending(){

        pet.setStatus(DefaultTestData.statusPending);

        Response response = petRequestHelper.findPetByStatus(pet);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("status", hasItem("pending"))
                .body("status", not(hasItem("available")))
                .body("status", not(hasItem("sold")))
                .body("id", not(hasItem(DefaultTestData.validId3)));

    }

    @Test
    public void findPetsWithStatusSold(){

        pet.setStatus(DefaultTestData.statusSold);

        Response response = petRequestHelper.findPetByStatus(pet);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("status", hasItem("sold"))
                .body("status", not(hasItem("pending")))
                .body("status", not(hasItem("available")))
                .body("id", hasItem(DefaultTestData.validId4));

    }

    @Test
    public void findPetsWithStatusAvailable(){

        pet.setStatus(DefaultTestData.statusAvailable);

        Response response = petRequestHelper.findPetByStatus(pet);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("status", hasItem("available"))
                .body("status", not(hasItem("pending")))
                .body("status", not(hasItem("sold")))
                .body("id", hasItem(DefaultTestData.validId3))
                .body("id", not(hasItem(DefaultTestData.validId4)));

    }

    @Test
    public void findPetsWithInvalidStatus(){

        pet.setStatus(DefaultTestData.statusInvalid);

        Response response = petRequestHelper.findPetByStatus(pet);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("status", not(hasItem("available")))
                .body("status", not(hasItem("pending")))
                .body("status", not(hasItem("sold")))
                .body("id", not(hasItem(DefaultTestData.validId1)))
                .body("id", not(hasItem(DefaultTestData.validId2)))
                .body("id", not(hasItem(DefaultTestData.validId3)))
                .body("id", not(hasItem(DefaultTestData.validId4)));

    }

    @Test
    public void findPet3(){

        pet.setId(DefaultTestData.validId3);

        Response response = petRequestHelper.getPetEntry(pet.getId());
        createdPet = response.body().as(Pet.class);

        assertThat(createdPet.getId(),equalTo(pet.getId()));

        response.then()
                .assertThat()
                .statusCode(200)
                .body("status", is("available"))
                .body("id", equalTo(pet.getId()))
                .body("tags", is(nullValue()));

    }

    @Test
    public void findPet4(){

        pet.setId(DefaultTestData.validId4);

        Response response = petRequestHelper.getPetEntry(pet.getId());
        createdPet = response.body().as(Pet.class);

        assertThat(createdPet.getId(),equalTo(pet.getId()));

        response.then()
                .assertThat()
                .statusCode(200)
                .body("status", is("sold"))
                .body("id", equalTo(pet.getId()))
                .body("name",containsString("New Pet Only Name Field And ID And Status"))
                .body("tags", is(nullValue()));

    }

    @Test
    public void findNotExistingPet(){

        pet.setId(DefaultTestData.invalidId1);

        Response response = petRequestHelper.getPetEntry(pet.getId());
        createdPet = response.body().as(Pet.class);

        response.then()
                .assertThat()
                .statusCode(404)
                .body("code", is(1))
                .body("type", is("error"))
                .body("message",containsString("Pet not found"))
                .body("id", is(nullValue()));

    }

    @AfterMethod
    public void teardown(){

        petRequestHelper.deletePet(pet.getId());

    }

}

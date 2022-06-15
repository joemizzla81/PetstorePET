package de.josef.tests.petRessource;

import de.josef.tests.domain.Pet;
import de.josef.tests.helper.PetRequestHelper;
import de.josef.tests.testData.DefaultTestData;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PostCreatePetTest {

    private Pet pet;
    private Pet createdPet;
    private PetRequestHelper petRequestHelper;

    @BeforeMethod
    public void setUp() {

        String environment = DefaultTestData.environment;

        pet = new Pet();

        petRequestHelper = new PetRequestHelper(environment);

    }


    @Test
    public void createPetWithIdAndStatusPending(){

        pet.setId(DefaultTestData.validId1);
        pet.setStatus(DefaultTestData.statusPending);
        petRequestHelper.createPetEntry(pet);

        Response response = petRequestHelper.getPetEntry(pet.getId());
        createdPet = response.body().as(Pet.class);

        assertThat(createdPet.getId(),equalTo(pet.getId()));

    }

   @Test
    public void createPetWithNameIdStatusPendingAndPhotoUrls(){

        pet.setId(DefaultTestData.validId2);
        pet.setName(DefaultTestData.petName1);
        pet.setPhotoUrls(DefaultTestData.stringArrayPhotoUrls);
        pet.setStatus(DefaultTestData.statusPending);
        petRequestHelper.createPetEntry(pet);

       Response response = petRequestHelper.getPetEntry(pet.getId());
       createdPet = response.body().as(Pet.class);

       assertThat(createdPet.getId(),equalTo(pet.getId()));
       assertThat(createdPet.getName(),equalTo(pet.getName()));
       assertThat(createdPet.getStatus(),equalTo(pet.getStatus()));
       assertThat(createdPet.getPhotoUrls(),equalTo(pet.getPhotoUrls()));

    }


    @Test
    public void createPetWithStatusPendingOnly(){

        pet.setStatus(DefaultTestData.statusPending);
        petRequestHelper.createPetEntry(pet);

        Response response = petRequestHelper.getPetEntry(pet.getId());
        createdPet = response.body().as(Pet.class);

        assertThat(createdPet.getId(),equalTo(pet.getId()));
        assertThat(createdPet.getName(),equalTo(pet.getName()));

    }


    @Test
    public void createPetWithIdAndStatusAvailable(){

        pet.setId(DefaultTestData.validId3);
        pet.setStatus(DefaultTestData.statusAvailable);
        petRequestHelper.createPetEntry(pet);

        Response response = petRequestHelper.getPetEntry(pet.getId());
        createdPet = response.body().as(Pet.class);

        assertThat(createdPet.getId(),equalTo(pet.getId()));
        assertThat(createdPet.getName(),equalTo(pet.getName()));
        assertThat(createdPet.getStatus(),equalTo(pet.getStatus()));

    }

    @Test
    public void createPetWithNameIdAndStatusSold(){

        pet.setId(DefaultTestData.validId4);
        pet.setName(DefaultTestData.petName2);
        pet.setStatus(DefaultTestData.statusSold);
        petRequestHelper.createPetEntry(pet);

        Response response = petRequestHelper.getPetEntry(pet.getId());
        createdPet = response.body().as(Pet.class);

        assertThat(createdPet.getId(),equalTo(pet.getId()));
        assertThat(createdPet.getName(),equalTo(pet.getName()));
        assertThat(createdPet.getStatus(),equalTo(pet.getStatus()));
    }

    @AfterMethod
    public void teardown(){

        petRequestHelper.deletePet(pet.getId());

    }

}

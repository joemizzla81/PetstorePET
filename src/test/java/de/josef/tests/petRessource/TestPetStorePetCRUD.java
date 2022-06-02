package de.josef.tests.petRessource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.josef.tests.domain.PetEntry;
import de.josef.tests.helper.PetRequestHelper;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@JsonIgnoreProperties(ignoreUnknown = true)

public class TestPetStorePetCRUD {

    PetEntry petEntry;
    PetEntry createdPetEntry;
    PetRequestHelper petRequestHelper;

    @BeforeMethod
    public void setUp() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        petEntry = new PetEntry();

        petRequestHelper = new PetRequestHelper();

    }

    @Test (priority=1)
    public void testPetStoreCreateNewPetWitStatusPendingOnlyForCreatedPETMethod(){

        petEntry.setId(petEntry.getValidId1());
        petEntry.setStatus(petEntry.getStatusPending());
        petRequestHelper.createPetEntry(petEntry);

        createdPetEntry = petRequestHelper.getPetEntry(petEntry.getId());

        assertThat(createdPetEntry.getId(),equalTo(petEntry.getId()));

    }

    @Test (priority=1)
    public void testPetStoreCreateNewPetWithNameAndIDAndStatusAndPhotoUrls(){

        petEntry.setId(petEntry.getValidId2());
        petEntry.setName(petEntry.getPetName1());
        petEntry.setPhotoUrls(petEntry.getStringArrayPhotoUrls());
        petEntry.setStatus(petEntry.getStatusPending());
        petRequestHelper.createPetEntry(petEntry);

    }


    @Test (priority=1)
    public void testPetStoreCreateNewPetWitStatusPendingOnly(){

        petEntry.setStatus(petEntry.getStatusPending());
        petRequestHelper.createPetEntry(petEntry);

    }


    @Test (priority=1)
    public void testPetStoreCreateNewPetWithOnlyIDAndStatusAvailable(){

        petEntry.setId(petEntry.getValidId3());
        petEntry.setStatus(petEntry.getStatusAvailable());
        petRequestHelper.createPetEntry(petEntry);

    }

    @Test (priority=1)
    public void testPetStoreCreateNewPetOnlyNameFieldAndIDAndStatusSold(){

        petEntry.setId(petEntry.getValidId4());
        petEntry.setName(petEntry.getPetName2());
        petEntry.setStatus(petEntry.getStatusSold());
        petRequestHelper.createPetEntry(petEntry);

    }


    @Test (priority=2)
    public void testPetStorePETGetSinglePreexistingPetIDOnlyAndStatusAvailable(){

        petEntry.setId(petEntry.getValidId3());
        petRequestHelper.assertPetEntryMethod1justIDPresent(petEntry);
    }

    @Test (priority=2)
    public void testPetStorePETGetSingleOnlyNameFieldAndIDAndStatusSold(){

        petEntry.setId(petEntry.getValidId4());
        petRequestHelper.assertPetEntryMethod2namePresentAndIDPresent(petEntry);
    }

    @Test (priority=2)
    public void testPetStoreGetNewlyCreatedPetsWithPhotoUrls() {

        petEntry.setId(createdPetEntry.getValidId2());
        petRequestHelper.assertPetEntryMethod3namePresentAndIDAndPhotoUrlsPresent(petEntry);

    }

    @Test (priority=2)
    public void testPetStoreGetNOTExistingPets() {

        petEntry.setId(petEntry.getInvalidId1());
        petRequestHelper.assertPetEntryMethod4NotExistingPet(petEntry);

    }

    @Test (priority=3)
    public void testPetStorePuTUpdatePreexistingPet(){

        petEntry.setId(petEntry.getValidId2());
        petEntry.setName(petEntry.getPetNameForPutUpdate());
        petRequestHelper.putUpdatePetExistingPet(petEntry);

    }

    @Test (priority=3)
    public void testPetStorePuTUpdateNOTexistingPet(){

        petEntry.setId(petEntry.getInvalidId2());
        petEntry.setName(petEntry.getPetName2ForPutUpdateNotExistingPet());
        petEntry.setStatus(petEntry.getStatusAvailable());
        petRequestHelper.putUpdatePetExistingPet(petEntry);
    }

    @Test (priority=3)
    public void testPetStorePOSTUpdateNOTExistingPet(){

        petEntry.setId(petEntry.getInvalidId1());
        petRequestHelper.postUpdatePetNotExistingPet(petEntry);

    }

   /* @Test
    public void testPetStorePOSTUpdateExistingPet(){

        petEntry.setId(1);
        petEntry.setName("Pet Update via POST for existing Pet");
        petEntry.setStatus("sold");

        given()

                .header("Accept", "application/json")
                .header("Content-type", "multipart/form-data")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(petEntry)
                .when()
                .post("/pet/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name",containsString("Pet Update via POST for existing Pet"))
                .body("id",equalTo(1));

    } */

    @Test (priority=4)
    public void testPetStoreGetFindPetByStatusSold(){

       petRequestHelper.findPetByStatusSold(petEntry);

    }

    @Test (priority=4)
    public void testPetStoreGetFindPetByStatusAvailable(){

        petRequestHelper.findPetByStatusAvailable(petEntry);

    }


    @Test (priority=4)
    public void testPetStoreGetFindPetByStatusPending(){

        petRequestHelper.findPetByStatusPending(petEntry);
    }

    @Test (priority=4)
    public void testPetStoreGetFindPetByStatusInvalidQueryParam(){

        petRequestHelper.findPetByStatusInvalidStatus(petEntry);

    }
    /*
    @Test
    public void testPetStorePostUploadImageForPet(){

        RequestSpecification httpRequest;

        File file = new File(System.getProperty("user.dir") + "/src/test/java/de/josef/tests/domain/fileio/Bild1png.png");
        Header acceptHeader = new Header("Accept", "multipart/form-data");
        httpRequest = RestAssured.given().header(acceptHeader);
        Response response = (Response) httpRequest

                .multiPart("file", file, "multipart/form-data")

                .when()
                .post("/pet/findByStatus/1")
                .then()
                .assertThat()
                .statusCode(200);

    }
    */

    @Test (priority=5)
    public void testPetStoreDeleteNotExistingPet(){

        petEntry.setId(petEntry.getInvalidId1());
        petRequestHelper.deleteNotExistingPet(petEntry);

    }

    @Test (priority=5)
    public void testPetStoreDeleteExistingPet1(){

        petEntry.setId(petEntry.getValidId2());
        petRequestHelper.createPetEntry(petEntry);
        petRequestHelper.deleteExistingPet(petEntry);

    }

    @Test (priority=5)
    public void testPetStoreDeleteExistingPet2(){


        petEntry.setId(petEntry.getValidId3());
        petRequestHelper.createPetEntry(petEntry);
        petRequestHelper.deleteExistingPet(petEntry);

    }

    @Test (priority=5)
    public void testPetStoreDeleteExistingPet3(){

        petEntry.setId(petEntry.getValidId4());
        petRequestHelper.createPetEntry(petEntry);
        petRequestHelper.deleteExistingPet(petEntry);

    }

    @Test (priority=5)
    public void testPetStoreDeleteExistingPet4(){

        petEntry.setId(petEntry.getValidId1());
        petRequestHelper.createPetEntry(petEntry);
        petRequestHelper.deleteExistingPet(petEntry);

    }

}





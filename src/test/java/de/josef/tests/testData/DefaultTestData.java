package de.josef.tests.testData;

public class DefaultTestData {

    public final static String environment = "https://petstore.swagger.io/v2";

    public final static int validId1 = 9991;
    public final static int validId2 = 818;
    public final static int validId3 = 915;
    public final static int validId4 = 88971123;
    public final static int invalidId1 = 818999911;
    public final static int invalidId2 = 547414441;

    public final static String petName1 = "New Pet with Photo Urls";
    public final static String petName2 = "New Pet Only Name Field And ID And Status";
    public final static String petNameForPutUpdate = "New Pet with Photo Urls Pet818 Name Updated VIA PUT RestAssured using a very long name in this case but not too extreme";


    public final static String petName2ForPutUpdateNotExistingPet = "Pet Update via PUT for not existing Pet";

    public final static String statusSold = "sold";
    public final static String statusAvailable = "available";
    public final static String statusPending = "pending";
    public final static String statusInvalid = "quatsch";


    public final static String[] stringArrayPhotoUrls = new String[]{"https://www.photopage.com", "https://bing.com"};
}

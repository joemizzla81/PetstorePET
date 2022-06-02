package de.josef.tests.domain;

public class PetEntry {

    int id;
    String name;
    String[] photoUrls;
    String[] tags;
    String status;
    String nametag;
    int tagid;

    int validId1 = 9991;
    int validId2 = 818;
    int validId3= 818181;
    int validId4 = 88971123;
    int invalidId1 = 818999911;
    int invalidId2 = 547414441;

    String petName1 = "New Pet with Photo Urls";
    String petName2 = "New Pet Only Name Field And ID And Status";
    String petNameForPutUpdate = "New Pet with Photo Urls Pet818 Name Updated VIA PUT RestAssured using a very long name in this case but not too extreme";



    String petName2ForPutUpdateNotExistingPet ="Pet Update via PUT for not existing Pet";

    String statusSold = "sold";
    String statusAvailable = "available";
    String statusPending = "pending";
    String statusInvalid = "quatsch";

    public String[] getStringArrayPhotoUrls() {
        return stringArrayPhotoUrls;
    }

    public void setStringArrayPhotoUrls(String[] stringArrayPhotoUrls) {
        this.stringArrayPhotoUrls = stringArrayPhotoUrls;
    }

    String[] stringArrayPhotoUrls = new String[] {"https://www.photopage.com", "https://bing.com"} ;

    public String getPetName2ForPutUpdateNotExistingPet() {
        return petName2ForPutUpdateNotExistingPet;
    }

    public void setPetName2ForPutUpdateNotExistingPet(String petName2ForPutUpdateNotExistingPet) {
        this.petName2ForPutUpdateNotExistingPet = petName2ForPutUpdateNotExistingPet;
    }

    public int getValidId1() {
        return validId1;
    }

    public void setValidId1(int validId1) {
        this.validId1 = validId1;
    }

    public int getValidId2() {
        return validId2;
    }

    public void setValidId2(int validId2) {
        this.validId2 = validId2;
    }

    public int getValidId3() {
        return validId3;
    }

    public void setValidId3(int validId3) {
        this.validId3 = validId3;
    }

    public int getValidId4() {
        return validId4;
    }

    public void setValidId4(int validId4) {
        this.validId4 = validId4;
    }

    public int getInvalidId1() {
        return invalidId1;
    }

    public void setInvalidId1(int invalidId1) {
        this.invalidId1 = invalidId1;
    }

    public String getPetName1() {
        return petName1;
    }

    public void setPetName1(String petName1) {
        this.petName1 = petName1;
    }

    public String getPetName2() {
        return petName2;
    }

    public void setPetName2(String petName2) {
        this.petName2 = petName2;
    }

    public String getPetNameForPutUpdate() {
        return petNameForPutUpdate;
    }

    public void setPetNameForPutUpdate(String petNameForPutUpdate) {
        this.petNameForPutUpdate = petNameForPutUpdate;
    }

    public String getStatusSold() {
        return statusSold;
    }

    public void setStatusSold(String statusSold) {
        this.statusSold = statusSold;
    }

    public String getStatusAvailable() {
        return statusAvailable;
    }

    public void setStatusAvailable(String statusAvailable) {
        this.statusAvailable = statusAvailable;
    }

    public String getStatusPending() {
        return statusPending;
    }

    public void setStatusPending(String statusPending) {
        this.statusPending = statusPending;
    }

    public String getStatusInvalid() {
        return statusInvalid;
    }

    public void setStatusInvalid(String statusInvalid) {
        this.statusInvalid = statusInvalid;
    }

    public int getInvalidId2() {
        return invalidId2;
    }

    public void setInvalidId2(int invalidId2) {
        this.invalidId2 = invalidId2;
    }

    public void setId(int id){

        this.id = id;
    }

    public int getId(){

        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }

    public String[] getTags() {
        return tags;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   /* public void setTags (int cid, String tag){
        tagid = cid;
        nametag = tag;

    }*/  // kriege ich nicht hin -> da name + id bereits als Variabeln vorhanden

}

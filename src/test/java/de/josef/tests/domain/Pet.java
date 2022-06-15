package de.josef.tests.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Pet {

    private int id;
    private String name;
    private String[] photoUrls;
    private String[] tags;

    public String getStatus() {
        return status;
    }

    private String status;

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

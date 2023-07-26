package com.ghanshyam.datastore;

public class DataClass {

    private String dataName;
    private String dataEmail;
    private String dataPhone;
    private String dataImage;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataEmail() {
        return dataEmail;
    }

    public String getDataPhone() {
        return dataPhone;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass(String dateName, String dataEmail, String dataPhone, String dataImage) {
        this.dataName = dateName;
        this.dataEmail = dataEmail;
        this.dataPhone = dataPhone;
        this.dataImage = dataImage;
    }

    public DataClass(){

    }

}

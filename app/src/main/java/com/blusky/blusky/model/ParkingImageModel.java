package com.blusky.blusky.model;

public class ParkingImageModel {

    private String id;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public ParkingImageModel(String id, String image) {
        this.id = id;
        this.image = image;
    }


}

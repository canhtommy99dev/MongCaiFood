package com.alexmedia.mongcaifood.Model;

public class ModelInfoCuaHang {
    String id,image;

    public ModelInfoCuaHang() {
    }

    public ModelInfoCuaHang(String id, String image) {
        this.id = id;
        this.image = image;
    }

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
}

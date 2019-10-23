package com.alexmedia.mongcaifood.Model;

public class ViewPagerModel {
    private int image;
    private String title;

    public ViewPagerModel(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public ViewPagerModel(int btnansang) {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

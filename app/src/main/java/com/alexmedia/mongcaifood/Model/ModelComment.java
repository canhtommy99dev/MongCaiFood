package com.alexmedia.mongcaifood.Model;

public class ModelComment {
    String id,name,comment,imageComment;
    int seek;

    public ModelComment() {
    }

    public ModelComment(String id, String name, String comment,int seek,String imageComment) {
        if(imageComment.trim().equals("")){
            imageComment = "No Name";
        }
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.seek = seek;
        this.imageComment = imageComment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSeek() {
        return seek;
    }

    public void setSeek(int seek) {
        this.seek = seek;
    }

    public String getImageComment() {
        return imageComment;
    }

    public void setImageComment(String imageComment) {
        this.imageComment = imageComment;
    }
}


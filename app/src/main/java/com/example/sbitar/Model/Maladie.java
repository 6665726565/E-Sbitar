package com.example.sbitar.Model;

public class Maladie {
    public String key , uid;
    public String title;
    public String description ;
    public String symptomes;

    public Maladie() {
    }

    public Maladie(String key, String uid, String title, String description, String symptomes) {
        this.key = key;
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.symptomes = symptomes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymptomes() {
        return symptomes;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSymptomes(String symptomes) {
        this.symptomes = symptomes;
    }
}

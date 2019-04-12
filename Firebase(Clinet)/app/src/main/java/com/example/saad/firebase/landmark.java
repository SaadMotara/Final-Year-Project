package com.example.saad.firebase;

public class landmark {

    String landmarkid;
    String landmarkname;

    public landmark() {

    }

    public landmark(String landmarkid, String landmarkname) {
        this.landmarkid = landmarkid;
        this.landmarkname = landmarkname;
    }

    public String getLandmarkid() {
        return landmarkid;
    }

    public String getLandmarkname() {
        return landmarkname;
    }
}



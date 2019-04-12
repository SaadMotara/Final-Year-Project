package com.example.saad.afinal;

public class LandMark {

    String landmarkid;
    String landmarkname;

    public LandMark() {

    }

    public LandMark(String landmarkid, String landmarkname) {
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

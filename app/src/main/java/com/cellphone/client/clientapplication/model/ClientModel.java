package com.cellphone.client.clientapplication.model;

/**
 *  firebase에서 데이터를 불러올때 사용하는 Model Class
 */

public class ClientModel {

    private String mPass;
    private String mName;
    private double mPoint;
    private String mRating;

    public ClientModel() {
    }

    public ClientModel(String mPass, String mName, double mPoint, String mRating) {
        this.mPass = mPass;
        this.mName = mName;
        this.mPoint = mPoint;
        this.mRating = mRating;
    }

    public String getmPass() {
        return mPass;
    }

    public void setmPass(String mPass) {
        this.mPass = mPass;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public double getmPoint() {
        return mPoint;
    }

    public void setmPoint(double mPoint) {
        this.mPoint = mPoint;
    }

    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }
}

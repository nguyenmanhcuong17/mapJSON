package com.cuong.model;

public class North {
    private double lat;
    private double lng;

    public North() {
    }

    public North(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return  "\t\t\t"+"Northeast: " + "\n"+
                "\t\t\t\tlat: \t" +this.lat + "\n" +
                "\t\t\t\tlat: \t" +this.lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}

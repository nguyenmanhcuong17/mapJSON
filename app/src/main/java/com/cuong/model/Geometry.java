package com.cuong.model;

public class Geometry {
    private Location location;
    private String location_type;
    private Viewport viewport;

    @Override
    public String toString() {
        return  "\t\t\tLocation: \t" + "\n"+ this.location  + "\n" +
                "\t\t\tLocation type:" + "\n" +
                "\t\t\t\t" +this.location_type + "\n"+
                "\t\t\tView Port:" + "\n"
                +this.viewport;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }
}

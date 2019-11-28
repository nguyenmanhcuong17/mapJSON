package com.cuong.model;

public class Viewport {
    private North northeast;
    private South southwest;

    @Override
    public String toString() {
        return  this.northeast + "\n"
                + this.southwest;
    }

    public North getNortheast() {
        return northeast;
    }

    public void setNortheast(North northeast) {
        this.northeast = northeast;
    }

    public South getSouthwest() {
        return southwest;
    }

    public void setSouthwest(South southwest) {
        this.southwest = southwest;
    }
}

package com.cuong.model;

import java.util.ArrayList;

public class Address {
    private String long_name;
    private String short_name;

    public Address() {
    }

    public Address(String long_name, String short_name, ArrayList<String> types) {
        this.long_name = long_name;
        this.short_name = short_name;
        this.types = types;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    private ArrayList<String>types;

    public String arrToString(ArrayList<String> types)
    {
        String s ="";
        for (String a: types)
        {

            s +=  a + ",\t";
        }
        return s;
    }


    @Override
    public String toString() {
        return "\t\t\t"+"Long Name: " + this.long_name + "\n" +
                "\t\t\t"+"Short Name: " +this.short_name+"\n" +
                "\t\t\t"+ "Type: " + arrToString(this.types) + "\n";
    }

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

//    public ArrayList<String> getTypes() {
//        return types;
//    }
//
//    public void setTypes(ArrayList<String> types) {
//        this.types = types;
//    }
}

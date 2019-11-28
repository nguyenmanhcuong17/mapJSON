package com.cuong.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Result {
    private ArrayList<Address>address_components;
    private String formatted_address;
    private Geometry geometry;
    private String place_id;
    private PlusCode plus_code;
    private ArrayList<String>types;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public PlusCode getPlus_code() {
        return plus_code;
    }

    public void setPlus_code(PlusCode plus_code) {
        this.plus_code = plus_code;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public Result() {
    }

    public String arrToString(ArrayList<Address> address_components)
    {
        String s ="";
        for (Address addresses: address_components)
        {

            s +=  addresses +"\n";
        }
        return s;
    }

    public String arrToString2(ArrayList<String> types)
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
        return "\n\t\t"+"Address Component: \t " +"\n"+
               // Arrays.toString(this.address_components.toArray()) +"\n" +
                arrToString(this.address_components) + "\n" +
                "\t\t"+"Formatted Address: \n " +
                "\t\t\t"+this.formatted_address +"\n" +
                "\t\t"+"Geometry: " +"\n" +this.geometry +"\n" +
                "\t\t"+"Place ID: \t" + this.place_id +"\n" +
                "\t\t"+"Plus Code: \t" + this.plus_code + "\n" +
                "\t\t"+"Type: \t" + arrToString2(this.types)  +"\n";
    }
//Arrays.toString(this.types.toArray())
//    public Result(ArrayList<Address> address_components, String formatted_address) {
//        this.address_components = address_components;
//        this.formatted_address = formatted_address;
//    }


    public Result(ArrayList<Address> address_components, String formatted_address, Geometry geometry, String place_id, PlusCode plus_code, ArrayList<String> types) {
        this.address_components = address_components;
        this.formatted_address = formatted_address;
        this.geometry = geometry;
        this.place_id = place_id;
        this.plus_code = plus_code;
        this.types = types;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public ArrayList<Address> getAddress_components() {
        return address_components;
    }

    public void setAddress_components(ArrayList<Address> address_components) {
        this.address_components = address_components;
    }




    //    private String formatted_address;
//    private Geometry geometry;
//    private String place_id;
//    private PlusCode plus_code;
//    private ArrayList<String>types;
//
//    @Override
//    public String toString() {
//        return "Result: " + "\n" +
//                this.address_components + "\n" +
//                this.formatted_address + "\n" +
//                this.geometry + "\n" +
//                this.place_id + "\n" +
//                this.plus_code + "\n" +
//                this.types;
//    }
//
//    public ArrayList<Address> getAddress_components() {
//        return address_components;
//    }
//
//    public void setAddress_components(ArrayList<Address> address_components) {
//        this.address_components = address_components;
//    }
//
//    public String getFormatted_address() {
//        return formatted_address;
//    }
//
//    public void setFormatted_address(String formatted_address) {
//        this.formatted_address = formatted_address;
//    }
//
//    public Geometry getGeometry() {
//        return geometry;
//    }
//
//    public void setGeometry(Geometry geometry) {
//        this.geometry = geometry;
//    }
//
//    public String getPlace_id() {
//        return place_id;
//    }
//
//    public void setPlace_id(String place_id) {
//        this.place_id = place_id;
//    }
//
//    public PlusCode getPlus_code() {
//        return plus_code;
//    }
//
//    public void setPlus_code(PlusCode plus_code) {
//        this.plus_code = plus_code;
//    }
//
//    public ArrayList<String> getTypes() {
//        return types;
//    }
//
//    public void setTypes(ArrayList<String> types) {
//        this.types = types;
//    }
}

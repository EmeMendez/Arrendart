package com.miarrendart.arrendart_v01.Classes;

import java.util.ArrayList;

public class City {
    private String city_id;
    private String city_description;
    private ArrayList<City> array_city;

    public City() {
    }

    public City(String city_id, String city_description) {
        this.city_id = city_id;
        this.city_description = city_description;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_description() {
        return city_description;
    }

    public void setCity_description(String city_description) {
        this.city_description = city_description;
    }

    public ArrayList<City> getArray_city() {
        array_city = new ArrayList<City>();
        array_city.add(new City("1","La Serena"));
        array_city.add(new City("2","Coquimbo"));
        return array_city;
    }

    @Override
    public String toString() {
        return city_description;
    }
}

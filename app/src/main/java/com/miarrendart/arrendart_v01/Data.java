package com.miarrendart.arrendart_v01;

import com.miarrendart.arrendart_v01.Classes.City;

public class Data {
    private String data_title;
    private City data_city;
    private String description;
    private String phone;
    private String address;
    private  int img;
    private boolean star;
    private String distance;

    public Data() {
    }

    public Data(String data_title, City data_city, String description, String phone, String address, int img, boolean star, String distance) {
        this.data_title = data_title;
        this.data_city = data_city;
        this.description = description;
        this.phone = phone;
        this.address = address;
        this.img = img;
        this.star = star;
        this.distance = distance;
    }

    public String getData_title() {
        return data_title;
    }

    public City getData_city() {
        return data_city;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getImg() {
        return img;
    }

    public boolean isStar() {
        return star;
    }

    public String getDistance() {
        return distance;
    }
}

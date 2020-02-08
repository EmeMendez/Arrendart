package com.miarrendart.arrendart_v01.Classes;

public class Service {
    private String ser_id;
    private Boolean ser_check;
    private String ser_description;
    private int ser_img;

    public Service() {
    }

    public Service(String ser_id, Boolean ser_check, String ser_description, int ser_img) {
        this.ser_id = ser_id;
        this.ser_check = ser_check;
        this.ser_description = ser_description;
        this.ser_img = ser_img;
    }

    public String getSer_id() {
        return ser_id;
    }

    public void setSer_id(String ser_id) {
        this.ser_id = ser_id;
    }

    public Boolean getSer_check() {
        return ser_check;
    }

    public void setSer_check(Boolean ser_check) {
        this.ser_check = ser_check;
    }

    public String getSer_description() {
        return ser_description;
    }

    public void setSer_description(String ser_description) {
        this.ser_description = ser_description;
    }

    public int getSer_img() {
        return ser_img;
    }

    public void setSer_img(int ser_img) {
        this.ser_img = ser_img;
    }
}

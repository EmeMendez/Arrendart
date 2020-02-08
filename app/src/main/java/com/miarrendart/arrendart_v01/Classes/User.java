package com.miarrendart.arrendart_v01.Classes;

import android.graphics.Bitmap;

public class User {
    private String user_id;
    private String user_email;
    private String user_pass;
    private String user_name;
    private String user_lastnames;
    private String user_date;
    private String user_address;
    private String user_phone;
    private Bitmap user_img;


    public User() {

    }

    public User(String user_email, String user_pass) {
        this.user_email = user_email;
        this.user_pass = user_pass;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_lastnames() {
        return user_lastnames;
    }

    public void setUser_lastnames(String user_lastnames) {
        this.user_lastnames = user_lastnames;
    }

    public String getUser_date() {
        return user_date;
    }

    public void setUser_date(String user_date) {
        this.user_date = user_date;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public Bitmap getUser_img() {
        return user_img;
    }

    public void setUser_img(Bitmap user_img) {
        this.user_img = user_img;
    }
}

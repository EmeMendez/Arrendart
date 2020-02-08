package com.miarrendart.arrendart_v01.Classes;

public class Publication {
    private String pub_id;
    private State pub_state;
    private User pub_user	;
    private City pub_city;
    private  SubCity pub_subcity;
    private  Type pub_type;
    private Period pub_period;
    private String pub_name;
    private String pub_description;
    private String pub_address;
    private double pub_latitude;
    private  double pub_longitude;
    private String pub_numerroom;
    private String pub_numberbath;
    private String pub_price;
    private String pub_surface;
    private String pub_date;
    private String pub_numerfloor;
    private String pub_forniture;
    private String pub_block;
    private String pub_numberblock;
    private int pub_img;

    private int star;


    public Publication() {
    }

    public String getPub_id() {
        return pub_id;
    }

    public void setPub_id(String pub_id) {
        this.pub_id = pub_id;
    }

    public State getPub_state() {
        return pub_state;
    }

    public void setPub_state(State pub_state) {
        this.pub_state = pub_state;
    }

    public User getPub_user() {
        return pub_user;
    }

    public void setPub_user(User pub_user) {
        this.pub_user = pub_user;
    }

    public City getPub_city() {
        return pub_city;
    }

    public void setPub_city(City pub_city) {
        this.pub_city = pub_city;
    }

    public SubCity getPub_subcity() {
        return pub_subcity;
    }

    public void setPub_subcity(SubCity pub_subcity) {
        this.pub_subcity = pub_subcity;
    }

    public Type getPub_type() {
        return pub_type;
    }

    public void setPub_type(Type pub_type) {
        this.pub_type = pub_type;
    }

    public Period getPub_period() {
        return pub_period;
    }

    public void setPub_period(Period pub_period) {
        this.pub_period = pub_period;
    }

    public String getPub_name() {
        return pub_name;
    }

    public void setPub_name(String pub_name) {
        this.pub_name = pub_name;
    }

    public String getPub_description() {
        return pub_description;
    }

    public void setPub_description(String pub_description) {
        this.pub_description = pub_description;
    }

    public String getPub_address() {
        return pub_address;
    }

    public void setPub_address(String pub_address) {
        this.pub_address = pub_address;
    }

    public double getPub_latitude() {
        return pub_latitude;
    }

    public void setPub_latitude(double pub_latitude) {
        this.pub_latitude = pub_latitude;
    }

    public double getPub_longitude() {
        return pub_longitude;
    }

    public void setPub_longitude(double pub_longitude) {
        this.pub_longitude = pub_longitude;
    }

    public String getPub_numerroom() {
        return pub_numerroom;
    }

    public void setPub_numerroom(String pub_numerroom) {
        this.pub_numerroom = pub_numerroom;
    }

    public String getPub_numberbath() {
        return pub_numberbath;
    }

    public void setPub_numberbath(String pub_numberbath) {
        this.pub_numberbath = pub_numberbath;
    }

    public String getPub_price() {
        return pub_price;
    }

    public void setPub_price(String pub_price) {
        this.pub_price = pub_price;
    }

    public String getPub_surface() {
        return pub_surface;
    }

    public void setPub_surface(String pub_surface) {
        this.pub_surface = pub_surface;
    }

    public String getPub_numerfloor() {
        return pub_numerfloor;
    }

    public void setPub_numerfloor(String pub_numerfloor) {
        this.pub_numerfloor = pub_numerfloor;
    }

    public String getPub_forniture() {
        return pub_forniture;
    }

    public void setPub_forniture(String pub_forniture) {
        this.pub_forniture = pub_forniture;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public String getPub_block() {
        return pub_block;
    }

    public void setPub_block(String pub_block) {
        this.pub_block = pub_block;
    }

    public String getPub_numberblock() {
        return pub_numberblock;
    }

    public void setPub_numberblock(String pub_numberblock) {
        this.pub_numberblock = pub_numberblock;
    }

    public int getPub_img() {
        return pub_img;
    }

    public void setPub_img(int pub_img) {
        this.pub_img = pub_img;
    }



    ///////////////////////////
    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}

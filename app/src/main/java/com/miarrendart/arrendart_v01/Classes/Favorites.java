package com.miarrendart.arrendart_v01.Classes;

public class Favorites {
    private String fav_id;
    private String fav_pub;
    private String fav_user;
    private String fav_state;


    public Favorites(){}

    public String getFav_id() {
        return fav_id;
    }

    public void setFav_id(String fav_id) {
        this.fav_id = fav_id;
    }

    public String getFav_pub() {
        return fav_pub;
    }

    public void setFav_pub(String fav_pub) {
        this.fav_pub = fav_pub;
    }

    public String getFav_state() {
        return fav_state;
    }

    public void setFav_state(String fav_state) {
        this.fav_state = fav_state;
    }

    public String getFav_user() {
        return fav_user;
    }

    public void setFav_user(String fav_user) {
        this.fav_user = fav_user;
    }
}

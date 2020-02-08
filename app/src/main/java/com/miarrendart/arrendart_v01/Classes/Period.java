package com.miarrendart.arrendart_v01.Classes;

import java.util.ArrayList;

public class Period {
    private String period_id;
    private String period_description;
    private ArrayList<String> period_rent;

    public Period() {
    }

    public String getPeriod_id() {
        return period_id;
    }

    public void setPeriod_id(String period_id) {
        this.period_id = period_id;
    }

    public String getPerdio_description() {
        return period_description;
    }

    public void setPerdio_description(String perdio_description) {
        this.period_description = perdio_description;
    }

    public ArrayList<String> getPeriod_rent() {
        period_rent = new ArrayList<String>();
        period_rent.add("Diario");
        period_rent.add("3 meses/mensual ");
        period_rent.add("1 año/mensual");
        period_rent.add("2 años/mensual");
        period_rent.add("3 años/mensual");
        period_rent.add("Indefinido/mensual");
        return period_rent;
    }

    @Override
    public String toString() {
        return period_description ;
    }
}


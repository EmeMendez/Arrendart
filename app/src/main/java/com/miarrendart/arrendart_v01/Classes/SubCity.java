package com.miarrendart.arrendart_v01.Classes;


import java.util.ArrayList;

public class SubCity {
    private String subcity_id;
    private String subcity_description;
    private City subcity_city;
    private ArrayList<SubCity> array_subcity;

    public SubCity() {
    }

    public SubCity(String subcity_id, String subcity_description, City subcity_city) {
        this.subcity_id = subcity_id;
        this.subcity_description = subcity_description;
        this.subcity_city = subcity_city;
    }

    public String getSubcity_id() {
        return subcity_id;
    }

    public void setSubcity_id(String subcity_id) {
        this.subcity_id = subcity_id;
    }

    public String getSubcity_description() {
        return subcity_description;
    }

    public void setSubcity_description(String subcity_description) {
        this.subcity_description = subcity_description;
    }

    public City getSubcity_city() {
        return subcity_city;
    }

    public void setSubcity_city(City subcity_city) {
        this.subcity_city = subcity_city;
    }

    public ArrayList<SubCity> getArray_subcity(String id) {
        array_subcity=new ArrayList<SubCity>();
        City ls = new City("1","La Serena");
        City cqb = new City("2","Coquimbo");

        array_subcity.add(new SubCity("1","Las compañias",ls));
        array_subcity.add(new SubCity("2","La Antena",ls));
        array_subcity.add(new SubCity("3","Serena Centro",ls));

        array_subcity.add(new SubCity("4","Peñuelas",cqb));
        array_subcity.add(new SubCity("5","El llano",cqb));
        array_subcity.add(new SubCity("6","San Juan",cqb));

        ArrayList<SubCity> filter = new ArrayList<SubCity>();

        for (SubCity sub : array_subcity ){
            if(sub.getSubcity_city().getCity_id().equals(id)){
                filter.add(sub);
            }
        }



        return filter;
    }

    @Override
    public String toString() {
        return subcity_description;
    }
}

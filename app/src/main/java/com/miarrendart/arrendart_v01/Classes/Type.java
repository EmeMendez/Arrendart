package com.miarrendart.arrendart_v01.Classes;

import java.util.ArrayList;

public class Type {
    private String type_id;
    private String type_description;
    ArrayList<Type> array_type ;

    public Type() {

    }

    public Type(String type_id, String type_description) {
        this.type_id = type_id;
        this.type_description = type_description;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_description() {
        return type_description;
    }

    public void setType_description(String type_description) {
        this.type_description = type_description;
    }

    public ArrayList<Type> getArray_type() {
        array_type = new ArrayList<Type>();
        array_type.add(new Type("1","Casa"));
        array_type.add(new Type("2","Departamento"));
        array_type.add(new Type("3","Habitación"));
        return array_type;
    }

    @Override
    public String toString() {
        return type_description;
    }
}

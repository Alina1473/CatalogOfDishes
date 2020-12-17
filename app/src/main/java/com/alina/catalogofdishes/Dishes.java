package com.alina.catalogofdishes;

import java.lang.ref.SoftReference;

public class Dishes {
    public int id;
    public String name, country, components;

    public Dishes(int id, String name, String country, String components) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.components = components;
    }

    public Dishes(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }
}


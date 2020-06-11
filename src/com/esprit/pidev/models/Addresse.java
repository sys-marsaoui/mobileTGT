/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

/**
 *
 * @author ajmim_9xsk8tf
 */
public class Addresse {
    private final String country_code;
    private final String country;
    private final String postcode;
    private final String state;
    private final String county;
    private final String city;
    private final String road;

    public Addresse(String country_code, String country, String postcode, String state, String county, String city, String road) {
        this.country_code = country_code;
        this.country = country;
        this.postcode = postcode;
        this.state = state;
        this.county = county;
        this.city = city;
        this.road = road;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getCountry() {
        return country;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getState() {
        return state;
    }

    public String getCounty() {
        return county;
    }

    public String getCity() {
        return city;
    }

    public String getRoad() {
        return road;
    }

    @Override
    public String toString() {
        return "Addresse{" + "country_code=" + country_code + ", country=" + country + ", postcode=" + postcode + ", state=" + state + ", county=" + county + ", city=" + city + ", road=" + road + '}';
    }

    
}

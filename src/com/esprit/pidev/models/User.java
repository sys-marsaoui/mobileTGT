/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

/**
 *
 * @author islem
 */
public class User {
    private String username;
    private String emai;
    //private String role;

    public User(String username, String emai) {
        this.username = username;
        this.emai = emai;
    }

  
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", emai=" + emai + '}';
    }

 
   
}

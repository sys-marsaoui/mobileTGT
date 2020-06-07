/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;



/**
 *
 * @author Malek
 */
public class CategoriePublication {
     private int id_cat;
    private String nomCat;

    public CategoriePublication(int id_cat, String nomCat) {
        this.id_cat = id_cat;
        this.nomCat = nomCat;
    }

    public CategoriePublication(int id_cat) {
        this.id_cat = id_cat;
    }
    
    public CategoriePublication(String nomCat) {
        this.nomCat = nomCat;
    }


    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    

    @Override
    public String toString() {
        return "CatégoriePublication{" + "id_cat=" + id_cat + ", nomCat=" + nomCat + '}';
    }
      
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

import java.util.Date;

/**
 *
 * @author Malek
 */
public class Publication {
    
    private int id_pub;
    private String contenu;
    private String video;
    private String localisation;
    private Date datePub;
    private double ratingPub;
    private int id_cat;
 
    //Show Query
    public Publication(int id_pub,String contenu, String video, String localisation,Date datePub,double ratingPub,int id_cat) {
        this.id_pub = id_pub;
        this.contenu = contenu;
        this.video = video;
        this.localisation = localisation;
        this.ratingPub = ratingPub;
        this.datePub = datePub;
        this.id_cat = id_cat;
    }
    
    
    //Update Query
    public Publication(String contenu, String video, String localisation,int id_cat) {
        this.contenu = contenu;
        this.video = video;
        this.localisation = localisation;
        this.id_cat = id_cat;
    }
    
    //Delete Query
    public Publication(int id_pub) {
        this.id_pub = id_pub;
    }
    
    
    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Date getDatePub() {
        return datePub;
    }

    public double getRatingPub() {
        return ratingPub;
    }

    public void setRatingPub(double ratingPub) {
        this.ratingPub = ratingPub;
    }

    public int getId_cat() {
        return id_cat;
    }
       
    @Override
    public String toString() {
        return "Publication{" + "id_pub=" + id_pub + ", contenu=" + contenu + ", video=" + video + ", localisation=" + localisation + ", datePub=" + datePub + ", ratingPub=" + ratingPub + '}';
    }
    
  }

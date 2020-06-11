/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

/**
 *
 * @author Nadhem
 */
public class Formation {

private int formation_id;
private int id;
private String titre_formation;
private String description_formation;
private String image;
private int duree_formation;
private int likes;
private int nolikes;

    public Formation(int formation_id, int id, String titre_formation, String description_formation, String image, int duree_formation) {
        this.formation_id = formation_id;
        this.id = id;
        this.titre_formation = titre_formation;
        this.description_formation = description_formation;
        this.image = image;
        this.duree_formation = duree_formation;

    }

    public Formation(String titre_formation,int id,  String description_formation, String image, int duree_formation, int likes, int nolikes) {
        this.formation_id = formation_id;
        this.id = id;
        this.titre_formation = titre_formation;
        this.description_formation = description_formation;
        this.image = image;
        this.duree_formation = duree_formation;
        this.likes = likes;
        this.nolikes = nolikes;
    }

    public Formation(int id, String titre_formation, String description_formation, String image, int duree_formation) {
        this.id = id;
        this.titre_formation = titre_formation;
        this.description_formation = description_formation;
        this.image = image;
        this.duree_formation = duree_formation;

    }

    public int getFormation_id() {
        return formation_id;
    }

    public void setFormation_id(int formation_id) {
        this.formation_id = formation_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre_formation() {
        return titre_formation;
    }

    public void setTitre_formation(String titre_formation) {
        this.titre_formation = titre_formation;
    }

    public String getDescription_formation() {
        return description_formation;
    }

    public void setDescription_formation(String description_formation) {
        this.description_formation = description_formation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDuree_formation() {
        return duree_formation;
    }

    public void setDuree_formation(int duree_formation) {
        this.duree_formation = duree_formation;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getNolikes() {
        return nolikes;
    }

    public void setNolikes(int nolikes) {
        this.nolikes = nolikes;
    }

    @Override
    public String toString() {
        return "Formation{" + "formation_id=" + formation_id + ", id=" + id + ", titre_formation=" + titre_formation + ", description_formation=" + description_formation + ", image=" + image + ", duree_formation=" + duree_formation + ", likes=" + likes + ", nolikes=" + nolikes + '}';
    }



}

   
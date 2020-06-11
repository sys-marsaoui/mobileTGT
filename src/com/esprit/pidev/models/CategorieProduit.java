/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

import java.util.Objects;

/**
 *
 * @author Haykel M'kaddem
 */
public class CategorieProduit {
    
    private int id;
    private String nomcategorie;
    
    public CategorieProduit() {
    }
    
    public CategorieProduit(int id) {
        this.id = id;
    }

    public CategorieProduit(int id, String nomcategorie) {
        this.id = id;
        this.nomcategorie = nomcategorie;
    }
    
    public CategorieProduit(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    @Override
    public String toString() {
        return "CategorieProduit{" + "id=" + id + ", nomcategorie=" + nomcategorie + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.nomcategorie);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CategorieProduit other = (CategorieProduit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nomcategorie, other.nomcategorie)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}

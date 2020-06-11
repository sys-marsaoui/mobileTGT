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
public class Panier {
    
    private int id;
    private Produit produit_id;

    public Panier(int id, Produit produit_id) {
        this.id = id;
        this.produit_id = produit_id;
    }
    
    public Panier(Produit produit_id) {
        this.produit_id = produit_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produit getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(Produit produit_id) {
        this.produit_id = produit_id;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", produit_id=" + produit_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.produit_id);
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
        final Panier other = (Panier) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.produit_id, other.produit_id)) {
            return false;
        }
        return true;
    }
    
    
    
}

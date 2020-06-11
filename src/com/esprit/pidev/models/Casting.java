/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

import java.util.Objects;

/**
 *
 * @author islem
 */
public class Casting {

    private int id;
    private int organisationId;
    private String TitreCasting;
    private String DescriptionCasting;
    private String DateCasting;
    private String DateLP;
    private String AdresseCasting;
    private String ImageCasting;
    private String ThemeCasting;

    public Casting() {
    }

    public Casting(int id, int organisationId, String TitreCasting, String DescriptionCasting, String DateCasting, String DateLP, String AdresseCasting, String ImageCasting, String ThemeCasting) {
        this.id = id;
        this.TitreCasting = TitreCasting;
        this.organisationId = organisationId;
        this.DescriptionCasting = DescriptionCasting;
        this.DateCasting = DateCasting;
        this.DateLP = DateLP;
        this.AdresseCasting = AdresseCasting;
        this.ImageCasting = ImageCasting;
        this.ThemeCasting = ThemeCasting;
    }

    public Casting(int id) {
        this.id = id;
    }

    public Casting(int id, String TitreCasting) {
        this.id = id;
        this.TitreCasting = TitreCasting;
    }

    public Casting(String TitreCasting, String DescriptionCasting, String DateCasting, String DateLP, String AdresseCasting, String ImageCasting, String ThemeCasting) {
        this.TitreCasting = TitreCasting;
        this.DescriptionCasting = DescriptionCasting;
        this.DateCasting = DateCasting;
        this.DateLP = DateLP;
        this.AdresseCasting = AdresseCasting;
        this.ImageCasting = ImageCasting;
        this.ThemeCasting = ThemeCasting;
    }

    public Casting(int id, String TitreCasting, String DescriptionCasting, String DateCasting, String DateLP, String AdresseCasting, String ImageCasting, String ThemeCasting) {
        this.id = id;
        this.TitreCasting = TitreCasting;
        this.DescriptionCasting = DescriptionCasting;
        this.DateCasting = DateCasting;
        this.DateLP = DateLP;
        this.AdresseCasting = AdresseCasting;
        this.ImageCasting = ImageCasting;
        this.ThemeCasting = ThemeCasting;
    }

    public Casting(int id, String TitreCasting, String DateCasting, String AdresseCasting) {
        this.id = id;
        this.TitreCasting = TitreCasting;
        this.DateCasting = DateCasting;
        this.AdresseCasting = AdresseCasting;
    }



    public Casting(int id, String TitreCasting, String AdresseCasting) {
        this.id = id;
        this.TitreCasting = TitreCasting;
        this.AdresseCasting = AdresseCasting;
    }

    //TitreCasting.getText(), DescriptionCasting.getText(), 
    //DateCasting, DateLP, AdresseCasting.getText(), ThemeCasting.getText()
    public Casting(String TitreCasting, String DescriptionCasting, String DateCasting, String DateLP, String AdresseCasting, String ThemeCasting) {
        this.TitreCasting = TitreCasting;
        this.DescriptionCasting = DescriptionCasting;
        this.DateCasting = DateCasting;
        this.DateLP = DateLP;
        this.AdresseCasting = AdresseCasting;
        this.ThemeCasting = ThemeCasting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitreCasting() {
        return TitreCasting;
    }

    public void setTitreCasting(String TitreCasting) {
        this.TitreCasting = TitreCasting;
    }

    public String getDescriptionCasting() {
        return DescriptionCasting;
    }

    public void setDescriptionCasting(String DescriptionCasting) {
        this.DescriptionCasting = DescriptionCasting;
    }

    public String getDateCasting() {
        return DateCasting;
    }

    public void setDateCasting(String DateCasting) {
        this.DateCasting = DateCasting;
    }

    public String getDateLP() {
        return DateLP;
    }

    public void setDateLP(String DateLP) {
        this.DateLP = DateLP;
    }

    public String getAdresseCasting() {
        return AdresseCasting;
    }

    public void setAdresseCasting(String AdresseCasting) {
        this.AdresseCasting = AdresseCasting;
    }

    public String getImageCasting() {
        return ImageCasting;
    }

    public void setImageCasting(String ImageCasting) {
        this.ImageCasting = ImageCasting;
    }

    public String getThemeCasting() {
        return ThemeCasting;
    }

    public void setThemeCasting(String ThemeCasting) {
        this.ThemeCasting = ThemeCasting;
    }

    @Override
    public String toString() {
        return "casting{" + "id=" + id + ", TitreCasting=" + TitreCasting + ", DescriptionCasting=" + DescriptionCasting + ", DateCasting=" + DateCasting + ", DateLP=" + DateLP + ", AdresseCasting=" + AdresseCasting + ", ImageCasting=" + ImageCasting + ", ThemeCasting=" + ThemeCasting + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.TitreCasting);
        hash = 37 * hash + Objects.hashCode(this.DescriptionCasting);
        hash = 37 * hash + Objects.hashCode(this.DateCasting);
        hash = 37 * hash + Objects.hashCode(this.DateLP);
        hash = 37 * hash + Objects.hashCode(this.AdresseCasting);
        hash = 37 * hash + Objects.hashCode(this.ImageCasting);
        hash = 37 * hash + Objects.hashCode(this.ThemeCasting);
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
        final Casting other = (Casting) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}

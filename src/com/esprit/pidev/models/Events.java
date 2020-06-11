/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;





import com.codename1.ui.spinner.Picker;
import java.util.Date;
import java.util.Objects;


/**
 *
 * @author islem
 */
public class Events {
     private int id;
     private int organisationId;
     private String TitreEvents;
     private String DescriptionEvents;
     private String ThemeEvents;
     private String AdresseEvents;
     private String DateEvents;
     private int prixEvents;
     private String ImageEvents;

    public Events() {
    }

    public Events(int id, int organisationId, String TitreEvents, String DescriptionEvents, String ThemeEvents, String AdresseEvents, String DateEvents, int prixEvents, String ImageEvents) {
        this.id = id;
        this.organisationId = organisationId;
        this.TitreEvents = TitreEvents;
        this.DescriptionEvents = DescriptionEvents;
        this.ThemeEvents = ThemeEvents;
        this.AdresseEvents = AdresseEvents;
        this.DateEvents = DateEvents;
        this.prixEvents = prixEvents;
        this.ImageEvents = ImageEvents;
    }

    public Events(String TitreEvents, String DescriptionEvents, String ThemeEvents, String AdresseEvents, String DateEvents, int prixEvents, String ImageEvents) {
        this.TitreEvents = TitreEvents;
        this.DescriptionEvents = DescriptionEvents;
        this.ThemeEvents = ThemeEvents;
        this.AdresseEvents = AdresseEvents;
        this.DateEvents = DateEvents;
        this.prixEvents = prixEvents;
        this.ImageEvents = ImageEvents;
    }

   

   

    public Events(int id, String AdresseEvents) {
        this.id = id;
        this.AdresseEvents = AdresseEvents;
    }

    public Events(int id, String TitreEvents, String DescriptionEvents, String ThemeEvents, String AdresseEvents, String DateEvents, int prixEvents, String ImageEvents) {
        this.id = id;
        this.TitreEvents = TitreEvents;
        this.DescriptionEvents = DescriptionEvents;
        this.ThemeEvents = ThemeEvents;
        this.AdresseEvents = AdresseEvents;
        this.DateEvents = DateEvents;
        this.prixEvents = prixEvents;
        this.ImageEvents = ImageEvents;
    }

    
    
    public Events(int id) {
        this.id = id;
    }

    public Events(int id, Picker DateEventsPicker) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitreEvents() {
        return TitreEvents;
    }

    public void setTitreEvents(String TitreEvents) {
        this.TitreEvents = TitreEvents;
    }

    public String getDescriptionEvents() {
        return DescriptionEvents;
    }

    public void setDescriptionEvents(String DescriptionEvents) {
        this.DescriptionEvents = DescriptionEvents;
    }

    public String getThemeEvents() {
        return ThemeEvents;
    }

    public void setThemeEvents(String ThemeEvents) {
        this.ThemeEvents = ThemeEvents;
    }

    public String getAdresseEvents() {
        return AdresseEvents;
    }

    public void setAdresseEvents(String AdresseEvents) {
        this.AdresseEvents = AdresseEvents;
    }

    public String getDateEvents() {
        return DateEvents;
    }

    public void setDateEvents(String DateEvents) {
        this.DateEvents = DateEvents;
    }

    public int getPrixEvents() {
        return prixEvents;
    }

    public void setPrixEvents(int prixEvents) {
        this.prixEvents = prixEvents;
    }

   

    public String getImageEvents() {
        return ImageEvents;
    }

    public void setImageEvents(String ImageEvents) {
        this.ImageEvents = ImageEvents;
    }

    @Override
    public String toString() {
        return "events{" + "id=" + id + ", TitreEvents=" + TitreEvents + ", DescriptionEvents=" + DescriptionEvents + ", ThemeEvents=" + ThemeEvents + ", AdresseEvents=" + AdresseEvents + ", DateEvents=" + DateEvents + ", prixEvents=" + prixEvents + ", ImageEvents=" + ImageEvents + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.TitreEvents);
        hash = 97 * hash + Objects.hashCode(this.DescriptionEvents);
        hash = 97 * hash + Objects.hashCode(this.ThemeEvents);
        hash = 97 * hash + Objects.hashCode(this.AdresseEvents);
        hash = 97 * hash + Objects.hashCode(this.DateEvents);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.prixEvents) ^ (Double.doubleToLongBits(this.prixEvents) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.ImageEvents);
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
        final Events other = (Events) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
                 
}
